package com.nicefish.auth;

import com.nicefish.po.POUser;
import com.nicefish.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import javax.annotation.Resource;

/**
 * <p>
 * 基于用户名密码的Realm，
 * 只用于登录
 * </p>
 *
 * @author zhongzhong
 */
public class FormRealm extends AuthorizingRealm {

    @Resource
    UserService userService;

    @Override
    public boolean supports(AuthenticationToken token) {
        return token !=null && token instanceof UsernamePasswordToken;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return new SimpleAuthorizationInfo();
    }

    /**
     * 认证
     *
     * @param authenticationToken token
     * @return AuthenticationInfo
     * @throws AuthenticationException 抛出异常
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String username = String.valueOf(authenticationToken.getPrincipal());
        POUser user = userService.findByEmail(username);
        if (user == null) {
            user = userService.findByUserName(username);
        }

        if (user == null) {
            throw new UnknownAccountException();
        }
        if (2 == user.getStatus()) {
            throw new LockedAccountException();
        }
        return new SimpleAuthenticationInfo(
                user.getUserId(), //用户名
                user.getPassword(), //密码
                ByteSource.Util.bytes(user.getUserId()),//salt=userId
                getName()  //realm name
        );
    }

}
