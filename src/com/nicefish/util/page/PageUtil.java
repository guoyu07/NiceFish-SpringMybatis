package com.nicefish.util.page;


public class PageUtil {
	/**
	 * 计算分页时的起始行号
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	public static int calcStartRow(int currentPage,int pageSize){		
		return (currentPage-1)*pageSize;
	}
	
	/**
	 * 计算总页数
	 * @param cNum
	 * @param pageSize
	 * @return
	 */
	public static int calcPages(int cNum,int pageSize){
		int pageNum=(int) Math.ceil(cNum*1.0/pageSize);
		return pageNum;
	}
}
