package com.app.bzpower.entity;

/**
 * 分页参数
 * @author 123
 *'count', 'prev', 'page', 'next', 'limit', 'refresh', 'skip'
 */
public class PageData {
	private long count;//总条数
	private long currentPage;//当前页
	private long prev;//上一页
	private long pages;//显示总页数
	private long next;//下一页
	private long limit;//分页数
	private long refresh;//刷新
	private long skip;//确定
	private long startPage ;
	
	
	public long getStartPage() {
		return startPage;
	}
	public void setStartPage(long startPage) {
		this.startPage = startPage;
	}
	public long getCount() {
		return count;
	}
	public void setCount(long count) {
		this.count = count;
	}
	public long getPrev() {
		return prev;
	}
	public void setPrev(long prev) {
		this.prev = prev;
	}
	public long getPages() {
		return pages;
	}
	public void setPages(long pages) {
		this.pages = pages;
	}
	public long getNext() {
		return next;
	}
	public void setNext(long next) {
		this.next = next;
	}
	public long getLimit() {
		return limit;
	}
	public void setLimit(long limit) {
		this.limit = limit;
	}
	public long getRefresh() {
		return refresh;
	}
	public void setRefresh(long refresh) {
		this.refresh = refresh;
	}
	public long getSkip() {
		return skip;
	}
	public void setSkip(long skip) {
		this.skip = skip;
	}
	
	
	public long getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(long currentPage) {
		
		this.currentPage = currentPage;
	}
	@Override
	public String toString() {
		return "PageData [count=" + count + ", currentPage=" + currentPage + ", prev=" + prev + ", pages=" + pages
				+ ", next=" + next + ", limit=" + limit + ", refresh=" + refresh + ", skip=" + skip + ", startPage="
				+ startPage + "]";
	}
	
	
	

}
