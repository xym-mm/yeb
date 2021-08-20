package com.yeb.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.yeb.server.config.security.JWTokenUtil;
import com.yeb.server.pojo.Admin;
import com.yeb.server.mapper.AdminMapper;
import com.yeb.server.pojo.RespBean;
import com.yeb.server.service.IAdminService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xym
 * @since 2021-03-09
 */
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements IAdminService {

    @Autowired
    private AdminMapper adminMapper;
   //用户信息
    @Autowired
    private UserDetailsService userDetailsService;

    //密码匹配
    @Autowired
    private PasswordEncoder passwordEncoder;

    //token工具
    @Autowired
    JWTokenUtil jwTokenUtil;

    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @Override
    public RespBean AdminLogin(String userName, String passWord, String code,HttpServletRequest request) {
        //获取session中的验证码
        String captcha = (String) request.getSession().getAttribute("captcha");
        //比较code是否正确
        if(StringUtils.isBlank(code)|| !code.equals(captcha) ) {
            return RespBean.error("验证码输入错误，请重新输入");
        }
        //登录
        UserDetails userDetails = userDetailsService.loadUserByUsername(userName);
        //如果用户名为空或者密码匹配失败
        if (userDetails == null || passwordEncoder.matches(passWord,userDetails.getPassword())) {
            return RespBean.error("用户名或密码不正确");
        }
        //判断用户是否被禁用
        if (!userDetails.isEnabled()) {
            return RespBean.error("用户已被禁用");
        }
        //更新security登录用户对象
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        //登陆成功返回token
        String token = jwTokenUtil.generatorToken(userDetails);
        //获取token的具体信息
        Map<String,String> tokenMap = new HashMap<>();
        tokenMap.put("token",token);
        tokenMap.put("tokenHead",tokenHead);
        return RespBean.success("登录成功",tokenMap);
    }

    /**
     * 根据用户名获取用户信息
     * @param userName
     * @return
     */
    @Override
    public Admin getUserByUserName(String userName) {
        return adminMapper.selectOne(new QueryWrapper<Admin>().eq("username",userName).eq("enabled",true));
    }




}
