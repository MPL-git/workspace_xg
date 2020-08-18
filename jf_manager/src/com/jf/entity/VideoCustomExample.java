package com.jf.entity;


public class VideoCustomExample extends VideoExample {
	@Override
    public VideoCustomCriteria createCriteria() {
		VideoCustomCriteria criteria = new VideoCustomCriteria();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }
	
	public class VideoCustomCriteria extends VideoCustomExample.Criteria{
		
		public Criteria andMchtCodeEqualTo(String mchtCode) {//商家序号
			addCriterion(" EXISTS(select mi.id from bu_mcht_info mi where mi.id=v.mcht_id and mi.del_flag='0' and mi.mcht_code='"+mchtCode+"')");
	        return this;
	    }
		
		public Criteria andcompanyNameLike(String companyName) {//商家名称
			addCriterion(" EXISTS(select mi.id from bu_mcht_info mi where mi.id=v.mcht_id and mi.del_flag='0' and (mi.company_name like '" + companyName + "' or mi.shop_name like '" + companyName + "'))");
	        return this;
	    }
		
		public Criteria andProductCodeCodeEqualTo(String code) {//商品ID
			addCriterion(" EXISTS(select vp.video_id from bu_product p,bu_video_product vp where vp.product_id=p.id and v.id=vp.video_id and vp.del_flag='0' and p.del_flag='0' and p.code='"+code+"')");
	        return this;
	    }
		
		public Criteria andmchtProductTypeIdEqualTo(String productTypeId) {//商家主营类目
			addCriterion(" EXISTS(select mpt.id from bu_mcht_product_type mpt where mpt.del_flag = '0' and mpt.status = '1' and mpt.is_main = '1' and mpt.mcht_id = v.mcht_id and mpt.product_type_id = "+productTypeId+")");
			return this;
		}
		
		public Criteria andProductTypeIdIn(String sysStaffProductType) {//当前账号复制类目
			addCriterion(" EXISTS(select mpt.mcht_id from bu_mcht_product_type mpt where mpt.del_flag = '0' and mpt.status = '1' and mpt.is_main = '1' and mpt.mcht_id =v.mcht_id and mpt.product_type_id in("+sysStaffProductType+") )");
			return this;
		}

		public Criteria andIsrecommendCustomEqualTo() {//是否精选
			addCriterion(" (isRecommend is null or isRecommend = '0')");
			return this;
		}
	}
}