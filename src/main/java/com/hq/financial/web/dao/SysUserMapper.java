package com.hq.financial.web.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.hq.financial.web.entity.SysUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author qiang.H
 * @since 2018-10-18
 */
public interface SysUserMapper extends BaseMapper<SysUser> {
    List<Map<Object, Object>> selectUserList(Page<Map<Object, Object>> page, @Param("search") String search);
}
