package com.yeb.server.mapper;

import com.yeb.server.pojo.Menu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author xym
 * @since 2021-03-09
 */
public interface MenuMapper extends BaseMapper<Menu> {

    /**
     * 根据用户id获取菜单列表
     */
    List<Menu> getMenuByAdminId(Integer id);


    /**
     * 根据角色获取菜单列表
     * @return
     */
    List<Menu> getMenuByRole();
}
