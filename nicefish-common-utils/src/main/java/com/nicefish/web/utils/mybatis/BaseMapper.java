package com.nicefish.web.utils.mybatis;

import java.io.Serializable;

public interface BaseMapper<T, PK extends Serializable> {
    
    int insertSelective(T entity);

    int deleteByPrimaryKey(PK id);

    int updateByPrimaryKeySelective(T entity);

    T selectByPrimaryKey(PK id);

}
