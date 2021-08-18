package com.yeb.server.controller;

import com.yeb.server.pojo.Admin;
import com.yeb.server.pojo.AdminLoginParam;
import com.yeb.server.pojo.RespBean;
import com.yeb.server.service.IAdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@RestController
@Api(tags = "AdminLoginController")
public class AdminLoginController {

    @Autowired
    private IAdminService iAdminService;

    @ApiOperation(value = "登录后返回token")
    @PostMapping("/login")
    public RespBean AdminLogin(@RequestBody  AdminLoginParam adminLoginParam, HttpServletRequest request) {
       return iAdminService.AdminLogin(adminLoginParam.getUsername(),adminLoginParam.getPassword(),request);
    }

    @ApiOperation(value = "获取登陆后的用户信息")
    @GetMapping("/admin/info")
    public Admin getAdminInfo(Principal principal){
        if(principal == null){
            return null;
        }
        //获取用户名
        String userName = principal.getName();
        Admin admin = iAdminService.getUserByUserName(userName);
        return admin;
    }

    @ApiOperation(value = "退出登录")
    @PostMapping("/logout")
    public RespBean LoginOut(){
        return RespBean.success("注销成功");
    }
}
