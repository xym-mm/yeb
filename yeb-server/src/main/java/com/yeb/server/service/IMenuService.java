package com.yeb.server.service;

import com.yeb.server.pojo.Menu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xym
 * @since 2021-03-09
 */
public interface IMenuService extends IService<Menu> {

    /**
     * 根据用户id获取菜单列表
     */
    List<Menu> getMenuByAdminId();

    /**
     * 根据角色获取菜单列表
     */
    List<Menu> getMenuByRole();
}
