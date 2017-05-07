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

    /**
     * 授权
     * @param principals
     * @return
     */
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String username = String.valueOf(principals.getPrimaryPrincipal());

        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        Set<String> roles = roleService.findRoles(username);
        authorizationInfo.setRoles(roles);
        return authorizationInfo;
    }

    /**
     * 认证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String userName = String.valueOf(authenticationToken.getPrincipal());
        POUser user = userService.findByEmail(userName);
        if(user == null) {
            user = userService.findByUserName(userName);
        }

        if(user == null){
            throw new UnknownAccountException();
        }
        if(2 == user.getStatus()){
            throw new LockedAccountException();
        }
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                user.getEmail(), //用户名
                user.getPassword(), //密码
                ByteSource.Util.bytes(user.getEmail()),//salt=username
                getName()  //realm name
        );
        return authenticationInfo;
    }
}
