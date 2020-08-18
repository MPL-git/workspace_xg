package com.jf.vo;

public class Page {
	Integer page;
	Integer pagesize;
	Integer pageSizeSearch;
	Integer defaultPage=1;
	Integer defaultPagesize=20;
	
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
			if (pageSizeSearch==null){
				pagesize=defaultPagesize;
			}else{
				pagesize=pageSizeSearch;
			}	
		}
		return pagesize;
	}
	public void setPagesize(Integer pagesize) {
		this.pagesize = pagesize;
	}
	
	public Integer getPageSizeSearch() {
		if(pageSizeSearch==null){
			pageSizeSearch=defaultPagesize;
		}
		return pageSizeSearch;
	}
	
	public void setPageSizeSearch(Integer pageSizeSearch) {
		this.pageSizeSearch = pageSizeSearch;
	}
	
	public Integer getLimitStart() {
		if(page==null){
			page=defaultPage;
		}
		if(pagesize==null){
			if (pageSizeSearch==null){
				pagesize=defaultPagesize;
			}else{
				pagesize=pageSizeSearch;
			}	
		}
		return (page-1)*pagesize;
	}
	public Integer getLimitSize() {
		if(pagesize==null){
			if (pageSizeSearch==null){
				pagesize=defaultPagesize;
			}else{
				pagesize=pageSizeSearch;
			}	
		}
		return pagesize;
	}
}
