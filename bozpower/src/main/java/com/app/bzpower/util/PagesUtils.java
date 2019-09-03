package com.app.bzpower.util;

import com.app.bzpower.entity.PageData;

public class PagesUtils {
	
	//分页数默认为10页
	public static long limit = 10;
	
	public static PageData getPageData(PageData pageData) {
		if(pageData.getLimit() == 0) {//
//			pageData.setCurrentPage(1);
			pageData.setLimit(limit);
		}
		if(pageData.getCurrentPage() == 0)
			pageData.setCurrentPage(1);
		//设置分页的开始查询数
		long startPage = (pageData.getCurrentPage() - 1) * pageData.getLimit();
		pageData.setStartPage(startPage);
		return pageData;
	}
	
	
	/**
	 * 计算分页WeatherController
	 * @param limit
	 * @param count
	 * @return
	 */
	public static long getPages(long limit, long count) {
		
		if(limit == 0) {
			limit = 10;
		}
		long pages =  (count / limit);
		if(count % limit != 0)
			pages ++;
		return pages;
	}
	


}
