package com.jf.entity;

import com.jf.common.ext.query.QueryObject;
import com.jf.common.ext.util.StrKit;

import java.util.List;

public class VideoExtExample extends VideoExample{

	private QueryObject queryObject;
	private ProductExtExample productExtExample;

	public QueryObject getQueryObject() {
		if(queryObject == null)	queryObject = new QueryObject();
		return queryObject;
	}

	public VideoExtExample fill(){
		if(queryObject == null)	return this;

		if(StrKit.notBlank(queryObject.getSortString())){
			setOrderByClause(queryObject.getSortString());
		}
		if(queryObject.getLimitSize() > 0){
			setLimitStart(0);
			setLimitSize(queryObject.getLimitSize());
		}
		return this;
	}

	public VideoExtExample fillPage(){
		if(queryObject == null)	queryObject = new QueryObject();
		if(StrKit.notBlank(queryObject.getSortString())){
			setOrderByClause(queryObject.getSortString());
		}
		setLimitStart(queryObject.getLimitStart());
		setLimitSize(queryObject.getPageSize());
		return this;
	}

	@Override
	public VideoExtCriteria createCriteria() {
		VideoExtCriteria criteria = new VideoExtCriteria();
		if (oredCriteria.size() == 0) {
			oredCriteria.add(criteria);
		}
		return criteria;
	}

	public class VideoExtCriteria extends Criteria{

		public Criteria andProductIdIn(List<Integer> productIdList) {
			addCriterion("EXISTS(select b.id FROM bu_video_product b WHERE b.video_id = a.id and del_flag = '0'" +
					" and b.product_id in( " + StrKit.paramToIn(productIdList) + "))");
			return this;
		}

	}


	public ProductExtExample getProductExtExample() {
		return productExtExample;
	}

	public void setProductExtExample(ProductExtExample productExtExample) {
		this.productExtExample = productExtExample;
	}
}
