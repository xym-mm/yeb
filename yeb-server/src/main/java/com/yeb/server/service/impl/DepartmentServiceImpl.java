package com.yeb.server.service.impl;

import com.yeb.server.pojo.Department;
import com.yeb.server.mapper.DepartmentMapper;
import com.yeb.server.service.IDepartmentService;
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
public class DepartmentServiceImpl extends ServiceImpl<DepartmentMapper, Department> implements IDepartmentService {

}
