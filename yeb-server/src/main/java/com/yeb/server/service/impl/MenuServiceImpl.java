package com.yeb.server.service.impl;

import com.yeb.server.mapper.AdminMapper;
import com.yeb.server.pojo.Admin;
import com.yeb.server.pojo.Menu;
import com.yeb.server.mapper.MenuMapper;
import com.yeb.server.service.IMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xym
 * @since 2021-03-09
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService {

    @Autowired
    private MenuMapper menuMapper;

    /**
     * 根据用户id获取菜单列表
     * 为了保证安全，不从前端传参数回来，利用springsecurity拿到adminId
     * @return
     */
    @Override
    public List<Menu> getMenuByAdminId() {
        return menuMapper.getMenuByAdminId(((Admin)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId());
    }
}
