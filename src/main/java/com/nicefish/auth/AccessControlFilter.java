package com.nicefish.auth;

import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by zhongzhong on 2017/5/14.
 */
public class AccessControlFilter extends org.apache.shiro.web.filter.AccessControlFilter {
    private static final Logger log = LoggerFactory.getLogger(AccessControlFilter.class);

    private static final String AUTHORIZATION_HEADER = "Authorization";

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        return false;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest req = WebUtils.toHttp(request);

        String userId = req.getParameter("userId");
        String token = req.getHeader(AUTHORIZATION_HEADER);

        try {
            JwtAuthenticationToken jwtToken = new JwtAuthenticationToken(userId, token);

            getSubject(request, response).login(jwtToken);
        } catch (Exception e) {
            String msg = userId + ":登录失败，token:" + token;
            log.error(msg);
            loginFail(response);
            return false;
        }

        return true;
    }

    private void loginFail(ServletResponse response) throws IOException {
        HttpServletResponse httpResponse = WebUtils.toHttp(response);
        httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    }
}
