package com.hq.financial.web.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.hq.financial.web.dao.SysMenuMapper;
import com.hq.financial.web.dao.SysRoleMenuMapper;
import com.hq.financial.web.entity.SysRoleMenu;
import com.hq.financial.web.entity.SysUserRole;
import com.hq.financial.web.service.ISysRoleMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <p>
 * 角色菜单关联表 服务实现类
 * </p>
 *
 * @author qiang.H
 * @since 2018-10-18
 */
@Service
public class SysRoleMenuServiceImpl extends ServiceImpl<SysRoleMenuMapper, SysRoleMenu> implements ISysRoleMenuService {

    @Autowired
    private SysMenuMapper sysMenuMapper;

    @Override
    public Set<String> findMenuByUid(String id) {
        List<String> list = sysMenuMapper.selectResourceByUid(id);
        return new HashSet<>(list);
    }
}
