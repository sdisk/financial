package com.hq.financial.shiro;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.hq.financial.web.entity.SysUser;
import com.hq.financial.web.service.ISysRoleMenuService;
import com.hq.financial.web.service.ISysUserRoleService;
import com.hq.financial.web.service.ISysUserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

/**
 * @program: financial
 * @description: shiro Realm
 * @author: Mr.Huang
 * @create: 2018-10-22 11:25
 **/
public class MyRealm extends AuthorizingRealm {

    /**
     * 用户服务
     */
    @Autowired
    private ISysUserService userService;
    /**
     * 用户角色服务
     */
    @Autowired private ISysUserRoleService sysUserRoleService;
    /**
     * 角色菜单服务
     */
    @Autowired private ISysRoleMenuService sysRoleMenuService;



    /**
     * 认证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken user = (UsernamePasswordToken) token;
        SysUser sysUser = userService.selectOne(new EntityWrapper<SysUser>().eq("userName", user.getUsername()));

        if (null == sysUser){
            throw new UnknownAccountException();
        }
        if (sysUser.getUserState() == SysUser._0){
            throw new LockedAccountException();
        }
       // SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user.getUsername() ,sysUser.getPassword(),getName());
        //盐值加密
        ByteSource byteSource = ByteSource.Util.bytes(user.getUsername());
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(sysUser, sysUser.getPassword(), byteSource, getName());
        return info;
    }

    /**
     * 授权
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SysUser sysUser = (SysUser) principals.getPrimaryPrincipal();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        Set<String> roles =  sysUserRoleService.findRolesByUid(sysUser.getId());
        Set<String> permissions =  sysRoleMenuService.findMenuByUid(sysUser.getId());
        info.setRoles(roles);
        info.setStringPermissions(permissions);
        return info;
    }

    public static void main(String[] args) {
        String pwd =  new SimpleHash("MD5", "123456", "admin", 1024).toString();
        System.out.println(pwd);//038bdaf98f2037b31f1e75b5b4c9b26e
    }
}
