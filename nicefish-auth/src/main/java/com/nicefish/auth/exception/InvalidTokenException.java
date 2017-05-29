package com.nicefish.auth.exception;

/**
 * Created by zhongzhong on 2017/5/16.
 */
public class InvalidTokenException extends RuntimeException {

    public InvalidTokenException(){
        super();
    }
    public InvalidTokenException(Throwable t){
        super(t);
    }
    public InvalidTokenException(String msg){
        super(msg);
    }
}
