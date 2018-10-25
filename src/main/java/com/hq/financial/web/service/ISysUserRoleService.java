package com.hq.financial.web.service;

import com.hq.financial.web.entity.SysUserRole;
import com.baomidou.mybatisplus.service.IService;

import java.util.Set;

/**
 * <p>
 * 用户角色关联表 服务类
 * </p>
 *
 * @author qiang.H
 * @since 2018-10-18
 */
public interface ISysUserRoleService extends IService<SysUserRole> {

    Set<String> findRolesByUid(String id);
}
