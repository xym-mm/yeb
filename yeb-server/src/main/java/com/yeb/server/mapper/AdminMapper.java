package com.yeb.server.mapper;

import com.yeb.server.pojo.Admin;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.mybatis.spring.annotation.MapperScan;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author xym
 * @since 2021-03-09
 */
@MapperScan
public interface AdminMapper extends BaseMapper<Admin> {

}
