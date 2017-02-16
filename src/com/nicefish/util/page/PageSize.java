package com.nicefish.util.page;

/**
 * @Description 分页参数
 * @author Lord  
 * @date 2017-02-15
 * @version V1.0
 */
public enum PageSize {
    
    ONE(1),ThREE(3),FIVE(5),SIX(6),EIGHT(8),TEN(10);
    
    private int size;
    
    PageSize(int size) {
        this.size = size;
    }
    
    public int getSize() {
        return size;
    }

}
