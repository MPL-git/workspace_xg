package com.jf.vo.request.stockclearance;

import javax.validation.constraints.NotNull;

/**
 * @author Pengl
 * @create 2020-08-06 下午 5:55
 */
public class SelectSingleProductActivityProductRequest {

	@NotNull(message = "页码不能为空")
	private Integer currentPage;
	private Integer pageSize;

	public Integer getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
}
