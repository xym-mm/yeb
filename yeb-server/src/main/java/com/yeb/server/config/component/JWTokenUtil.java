package com.yeb.server.config.component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * JWT Token 工具类
 */
@Component
public class JWTokenUtil {
    //用户名
    private static final String CLAIM_KEY_USERNAME="sub";
    //创建时间
    private static final String CLAIM_KEY_CREATED="created";

    //秘钥，在yml文件配置过
    @Value("${jwt.secret}")
    private String secret;
    //失效时间，在yml文件配置过
    @Value("${jwt.expiration}")
    private Long expiration;

    /**
     * 根据用户信息生成token
     */
    public String generatorToken(UserDetails userDetails) {
        Map<String,Object> claims = new HashMap<>();
        claims.put(CLAIM_KEY_USERNAME,userDetails.getUsername());
        claims.put(CLAIM_KEY_CREATED,new Date());
        String jwToken = getJWToken(claims);
        return jwToken;
    }

    /**
     * 从token中获取用户名
     * @param token
     * @return
     */
    public String getUserNameByToken(String token) {
        String username;
        try {
            Claims claim = getClaimByToken(token);
            username = claim.getSubject();
        }catch (Exception e) {
            username = null;
        }
         return username;
    }

    /**
     * 根据荷载生成JWT TOKEN
     * @param claims
     * @return
     */
    private String getJWToken(Map<String,Object> claims) {
        return Jwts.builder()
                //设置荷载
                .setClaims(claims)
                //设置失效时间
                .setExpiration(generateExpirationDate())
                //设置签名和秘钥
                .signWith(SignatureAlgorithm.HS512,secret)
                .compact();
    }

    /**
     * 验证token是否有效
     * @param token
     * @param userDetails
     * @return
     */
    public boolean validateToken(String token,UserDetails userDetails) {
        //从token里面获取username
        String username = getUserNameByToken(token);
        //判断token中的username和用户信息中的username是否一致
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    /**
     * 判断token是否可以被刷新(过期可被刷新)
     * @param token
     * @return
     */
    public boolean canRefresh(String token) {
        return !isTokenExpired(token);
    }

    /**
     * 刷新token
     * @param token
     * @return
     */
    public String refreshToken(String token) {
        Claims claimByToken = getClaimByToken(token);
        claimByToken.put(CLAIM_KEY_CREATED,new Date());
        return getJWToken(claimByToken);
    }
    /**
     * 判断token是否失效
     * @param token
     * @return
     */
    private boolean isTokenExpired(String token) {
        //获取失效时间
        Date expireDate = getExpriedDateByToken(token);
        //如果失效时间在当前时间之前，则token已经失效
        return expireDate.before(new Date());
    }

    /**
     * 从token中获取失效时间
     * @param token
     * @return
     */
    private Date getExpriedDateByToken(String token) {
        //从token中获取荷载
        Claims claimByToken = getClaimByToken(token);
        //从荷载中获取失效时间
        return claimByToken.getExpiration();
    }

    /**
     * 生成Token失效时间
     * @return
     */
    private Date generateExpirationDate() {
        return new Date(System.currentTimeMillis()+expiration*1000);
    }

    /**
     * 从token中获取荷载
     */
    private Claims getClaimByToken(String token) {
        Claims claim = null;
        try {
          claim =  Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        }catch (Exception e) {
            e.getStackTrace();
        }
        return claim;
    }
}


