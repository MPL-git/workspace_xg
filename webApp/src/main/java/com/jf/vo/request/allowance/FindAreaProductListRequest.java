package com.jf.vo.request.allowance;

import javax.validation.constraints.NotNull;

/**
 * @author Pengl
 * @create 2020-07-22 下午 2:25
 */
public class FindAreaProductListRequest {

	@NotNull(message = "活动专区ID不能为空")
	private Integer activityAreaId;
	@NotNull(message = "商品类型ID不能为空")
	private Integer productTypeId;
	@NotNull(message = "当前页不能为空")
	private Integer currentPage;
	private Integer pagesize;

	public Integer getActivityAreaId() {
		return activityAreaId;
	}

	public void setActivityAreaId(Integer activityAreaId) {
		this.activityAreaId = activityAreaId;
	}

	public Integer getProductTypeId() {
		return productTypeId;
	}

	public void setProductTypeId(Integer productTypeId) {
		this.productTypeId = productTypeId;
	}

	public Integer getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	public Integer getPagesize() {
		return pagesize;
	}

	public void setPagesize(Integer pagesize) {
		this.pagesize = pagesize;
	}
}
