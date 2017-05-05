package com.nicefish.auth;

import com.nicefish.po.POUser;
import com.nicefish.service.RoleService;
import com.nicefish.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import javax.annotation.Resource;
import java.util.Set;

/**
 * Created by zhongzhong on 2017/5/5.
 */
public class AuthRealm extends AuthorizingRealm {

    @Resource
    private UserService userService;

    @Resource
    private RoleService roleService;

    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String username = String.valueOf(principals.getPrimaryPrincipal());

        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        Set<String> roles = roleService.findRoles(username);
        authorizationInfo.setRoles(roles);
        return authorizationInfo;
    }

    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String username = String.valueOf(authenticationToken.getPrincipal());
        POUser user = userService.findByEmail(username);
        if(user == null){
            user = userService.findByUserName(username);
        }
        if(user == null){
            user = userService.findByCode(username);
        }

        if(user == null){
            throw new UnknownAccountException();
        }
        if(2 == user.getStatus()){
            throw new LockedAccountException();
        }
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                user.getUserName(), //用户名
                user.getPassword(), //密码
                ByteSource.Util.bytes(user.getUserName()),//salt=username
                getName()  //realm name
        );
        return authenticationInfo;
    }
}
