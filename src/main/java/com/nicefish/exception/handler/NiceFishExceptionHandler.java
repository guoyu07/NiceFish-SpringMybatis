package com.nicefish.exception.handler;

import com.nicefish.controller.BaseController;
import com.nicefish.exception.EmailConflictException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 统一异常处理类
 */
@RestControllerAdvice
public class NiceFishExceptionHandler extends BaseController{
    private static Logger log = LoggerFactory.getLogger(NiceFishExceptionHandler.class);

    @ExceptionHandler(UnknownAccountException.class)
    @ResponseStatus(HttpStatus.OK)
    public Object handlerUnknownAccountException(UnknownAccountException e){
        log.error("无效的用户名或密码。",e);
        return this.ajaxFailureResponse("无效的用户名或密码。");
    }

    @ExceptionHandler(IncorrectCredentialsException.class)
    @ResponseStatus(HttpStatus.OK)
    public Object handlerIncorrectCredentialsException(IncorrectCredentialsException e){
        log.error("无效的用户名或密码。",e);
        return this.ajaxFailureResponse("无效的用户名或密码。");
    }

    @ExceptionHandler(EmailConflictException.class)
    @ResponseStatus(HttpStatus.OK)
    public Object handlerEmailConflictException(EmailConflictException e){
        log.error("邮箱已经被注册了。",e);
        return this.ajaxFailureResponse("邮箱已经被注册了。");
    }

    @ExceptionHandler(AuthenticationException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public Object handlerAuthenticationException(AuthenticationException e){
        log.error("认证失败。",e);
        return this.ajaxFailureResponse("认证失败。");
    }

    @ExceptionHandler(UnauthorizedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public Object handlerUnauthorizedException(UnauthorizedException e){
        log.error("没有访问权限。",e);
        return this.ajaxFailureResponse("没有访问权限");
    }

    @ExceptionHandler(Exception.class)
    public Object handlerException(Exception e){
        log.error("服务器错误。",e);
        return this.ajaxFailureResponse("服务器错误。");
    }
}
