package com.nicefish.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.OncePerRequestFilter;

import com.nicefish.po.POUser;
import com.nicefish.utils.ConstSessionName;

/**
 * @Description 用户信息请求过滤
 * @author WangShiCong  
 * @date 2017-03-11
 * @version V1.0
 */
public class UserInfoFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request,
			HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		//获取URI地址            
		String uri=request.getRequestURI();        
		String ctx=request.getContextPath();        
		uri=uri.substring(ctx.length());
		
		//从session中获取用户登录者实体
		POUser user=(POUser)request.getSession().getAttribute(ConstSessionName.UserInfo);
		
		if(user !=null ){
            filterChain.doFilter(request, response);
        }else{
            response.sendRedirect(request.getContextPath()+"/login?returnUrl="+uri);
        }
		
	}

}
