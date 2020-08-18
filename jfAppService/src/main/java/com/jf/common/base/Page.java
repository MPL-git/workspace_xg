package com.jf.common.base;

public class Page {
	Integer page;
	Integer pagesize;
	Integer defaultPage=1;
	Integer defaultPagesize=10;
	Integer totalPage=0;
	Integer count;
	public Page(Integer page,Integer pagesize, Integer count){
		this.page = page;
		this.pagesize = pagesize;
		this.count = count;
	}
	
	public Integer getPage() {
		if(page==null){
			page=defaultPage;
		}
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public Integer getPagesize() {
		if(pagesize==null){
			pagesize=defaultPagesize;
		}
		return pagesize;
	}
	public void setPagesize(Integer pagesize) {
		this.pagesize = pagesize;
	}
	public Integer getLimitStart() {
		if(page==null||page.intValue()<=0){
			page=defaultPage;
		}
		if(pagesize==null||pagesize.intValue()<=0){
			pagesize=defaultPagesize;
		}
		return (page-1)*pagesize;
	}
	public Integer getLimitSize() {
		if(pagesize==null||pagesize.intValue()<=0){
			pagesize=defaultPagesize;
		}
		return pagesize;
	}
	
	
	public Integer getTotalPage() {
		if(totalPage == 0){
			this.totalPage = (count+pagesize-1)/pagesize;
		}
		return totalPage;
	}

	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}
	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}
	
	
}
