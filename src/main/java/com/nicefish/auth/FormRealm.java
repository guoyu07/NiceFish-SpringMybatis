package com.nicefish.auth;

import com.nicefish.po.POUser;
import org.apache.shiro.authc.*;
import org.apache.shiro.util.ByteSource;

/**
 * <p>
 * 基于用户名密码的Realm，
 * 只用于登录
 * </p>
 *
 * @author zhongzhong
 */
public class FormRealm extends AbstractRealm {

    /**
     * 认证
     *
     * @param authenticationToken token
     * @return AuthenticationInfo
     * @throws AuthenticationException 抛出异常
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String userName = String.valueOf(authenticationToken.getPrincipal());
        POUser user = userService.findByEmail(userName);
        if (user == null) {
            user = userService.findByUserName(userName);
        }

        if (user == null) {
            throw new UnknownAccountException();
        }
        if (2 == user.getStatus()) {
            throw new LockedAccountException();
        }
        return new SimpleAuthenticationInfo(
                user.getEmail(), //用户名
                user.getPassword(), //密码
                ByteSource.Util.bytes(user.getEmail()),//salt=username
                getName()  //realm name
        );
    }
}
