package com.yeb.server.service.impl;

import com.yeb.server.pojo.Role;
import com.yeb.server.mapper.RoleMapper;
import com.yeb.server.service.IRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xym
 * @since 2021-03-09
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

}