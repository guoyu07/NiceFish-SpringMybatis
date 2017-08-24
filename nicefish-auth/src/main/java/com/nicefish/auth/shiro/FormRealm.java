package com.nicefish.auth.shiro;

import com.nicefish.auth.po.POUser;
import com.nicefish.auth.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import javax.annotation.Resource;

/**
 * <p>
 * 基于用户名密码的Realm，只用于登录
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
     * 读取数据库记录，比对认证
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
                user.getUserName(), //用户名
                user.getPassword(), //密码
                ByteSource.Util.bytes(user.getUserName()),//salt=userName
                getName()  //realm name
        );
    }

    @Override
    protected void assertCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) throws AuthenticationException {
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
        String tokenUserName=usernamePasswordToken.getUsername();
        String tokenPassword=String.valueOf(usernamePasswordToken.getPassword());

        POUser poUser=new POUser();
        poUser.setUserName(tokenUserName);
        poUser.setPassword(tokenPassword);
        tokenPassword=new SimpleHash("sha-512",
                poUser.getPassword(),
                ByteSource.Util.bytes(poUser.getUserName()),
                1024
        ).toHex();

        String infoUserName=info.getPrincipals().toString();
        String infoPassword=info.getCredentials().toString();

        if(!tokenUserName.equals(infoUserName)||!tokenPassword.equals(infoPassword)){
            String msg = "Submitted credentials for token [" + token + "] did not match the expected credentials.";
            throw new IncorrectCredentialsException(msg);
        }
    }
}