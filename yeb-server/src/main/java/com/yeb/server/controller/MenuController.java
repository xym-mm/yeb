package com.yeb.server.controller;


import com.yeb.server.pojo.Menu;
import com.yeb.server.service.IAdminService;
import com.yeb.server.service.IMenuService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author xym
 * @since 2021-03-09
 */
@RestController
@RequestMapping("/system/cfg")
public class MenuController {

    @Autowired
    private IMenuService menuService;

    @ApiOperation(value = "根据用户id获取菜单列表")
    @GetMapping("/menu")
    public List<Menu> getMenuByAdminId() {
        return menuService.getMenuByAdminId();
    }
}
