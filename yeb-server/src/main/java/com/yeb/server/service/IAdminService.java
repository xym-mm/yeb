package com.yeb.server.service;

import com.yeb.server.pojo.Admin;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yeb.server.pojo.Menu;
import com.yeb.server.pojo.RespBean;
import org.springframework.http.HttpRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xym
 * @since 2021-03-09
 */
public interface IAdminService extends IService<Admin> {
    /**
     * 用户登录
     */
    public RespBean AdminLogin(String userName, String passWord, String code,HttpServletRequest request);

    /**
     * 根据用户名获取用户信息
     * @param userName
     * @return
     */
    Admin getUserByUserName(String userName);

    /**
     * 根据用户id获取菜单列表
     * @return
     */
    List<Menu> getMenuByAdminId();
}
