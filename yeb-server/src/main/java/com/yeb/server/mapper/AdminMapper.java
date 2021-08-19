package com.yeb.server.mapper;

import com.yeb.server.pojo.Admin;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yeb.server.pojo.Menu;
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

    /**
     * 根据用户id获取菜单列表
     * @param id
     * @return
     */
    List<Menu> getMenuByAdminId(Integer id);
}
