package com.nicefish.auth;

import com.nicefish.service.RoleService;
import com.nicefish.service.TokenService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAccount;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import javax.annotation.Resource;
import java.util.Set;


/**
 * <p>
 * 基于Jwt的认证Realm，用于除登录之外的其他请求
 * </p>
 *
 * @author zhongzhong
 */
public class JwtRealm extends AuthorizingRealm {

    @Resource
    private TokenService tokenService;

    @Resource
    private RoleService roleService;

    @Override
    public boolean supports(AuthenticationToken token) {
        return token != null && token instanceof JwtAuthenticationToken;
    }

    /**
     * 授权
     *
     * @param principals PrincipalCollection
     * @return AuthorizationInfo
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String userId = String.valueOf(principals.getPrimaryPrincipal());

        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        Set<String> roles = roleService.findRoles(userId);
        authorizationInfo.setRoles(roles);
        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        JwtAuthenticationToken jwtToken = (JwtAuthenticationToken) token;

        //验证token是否有效
        tokenService.validateToken(jwtToken.getToken());

        return new SimpleAccount(jwtToken.getUserId(), jwtToken.getToken(), getName());
    }
}
