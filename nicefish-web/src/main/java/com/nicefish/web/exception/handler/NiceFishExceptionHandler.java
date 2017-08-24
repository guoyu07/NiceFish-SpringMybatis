package com.nicefish.web.exception.handler;

import com.nicefish.auth.exception.InvalidTokenException;
import com.nicefish.auth.exception.TokenExpiredException;
import com.nicefish.web.controller.BaseController;
import com.nicefish.web.exception.*;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationException;
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

    @ExceptionHandler(TokenExpiredException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Object handlerTokenExpiredException(TokenExpiredException e){
        log.error("token已经过期.",e);
        return this.ajaxFailureResponse("token已经过期。");
    }

    @ExceptionHandler(InvalidTokenException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Object handlerInvalidTokenException(InvalidTokenException e){
        log.error("token无效.",e);
        return this.ajaxFailureResponse("token无效。");
    }

    @ExceptionHandler(UserNameRequiredException.class)
    @ResponseStatus(HttpStatus.OK)
    public Object handlerUserNameRequiredException(){
        return this.ajaxFailureResponse("用户名不能为空。");
    }

    @ExceptionHandler(PasswordRequiredException.class)
    @ResponseStatus(HttpStatus.OK)
    public Object handlerPasswordRequiredException(){
        return this.ajaxFailureResponse("密码不能为空。");
    }

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

    @ExceptionHandler(AuthorizationException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public Object handlerAuthorizationException(AuthorizationException e){
       log.error("授权异常。",e);
       return this.ajaxFailureResponse("授权异常。");
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
