package com.hq.financial.web.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.hq.financial.web.entity.SysRole;
import com.hq.financial.web.entity.SysUserRole;
import com.hq.financial.web.dao.SysUserRoleMapper;
import com.hq.financial.web.service.ISysUserRoleService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <p>
 * 用户角色关联表 服务实现类
 * </p>
 *
 * @author qiang.H
 * @since 2018-10-18
 */
@Service
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleMapper, SysUserRole> implements ISysUserRoleService {

    @Override
    public Set<String> findRolesByUid(String id) {
        List<SysUserRole> list = this.selectList(new EntityWrapper<SysUserRole>().eq("userId", id));
        Set<String> sets = new HashSet<>(list.size());
        for (SysUserRole role : list){
            sets.add(role.getRoleId());
        }
        return sets;
    }
}
