package com.nicefish.auth.shiro;

import com.nicefish.auth.service.PermissionService;
import com.nicefish.auth.service.RoleService;
import com.nicefish.auth.service.TokenService;
import com.nicefish.auth.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.*;
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

    @Resource
    private PermissionService permissionService;

    @Resource
    private UserService userService;

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
        Set<String> permissions = permissionService.findPermissions();
        //登录成功之后，都有该权限
        permissions.add("auth:login");
        authorizationInfo.setRoles(roles);
        authorizationInfo.setStringPermissions(permissions);
        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        JwtAuthenticationToken jwtToken = (JwtAuthenticationToken) token;

        if(StringUtils.isEmpty(jwtToken.getUserId())){
            //抛出账号不存在的异常
            throw new UnknownAccountException();
        }
        this.userService.findById(jwtToken.getUserId());

        //验证token是否有效
        tokenService.validateToken(jwtToken.getToken());

        return new SimpleAccount(jwtToken.getUserId(), jwtToken.getToken(), getName());
    }
}
