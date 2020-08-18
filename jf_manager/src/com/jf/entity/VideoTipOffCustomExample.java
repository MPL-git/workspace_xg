package com.jf.entity;

import com.jf.entity.VideoExample.Criteria;


public class VideoTipOffCustomExample extends VideoTipOffExample {
	@Override
    public VideoTipOffCustomCriteria createCriteria() {
		VideoTipOffCustomCriteria criteria = new VideoTipOffCustomCriteria();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }
	
	public class VideoTipOffCustomCriteria extends VideoTipOffCustomExample.Criteria{
		
		public Criteria andMchtCodeEqualTo(String mchtCode) {//商家序号
			addCriterion(" EXISTS(select mi.id from bu_mcht_info mi where mi.id=vt.mcht_id and mi.del_flag='0' and mi.mcht_code='"+mchtCode+"')");
	        return this;
	    }
		
		public Criteria andMchtNameEqualTo(String mchtName) {//商家名称
			addCriterion(" EXISTS(select mi.id from bu_mcht_info mi where mi.id=vt.mcht_id and mi.del_flag='0' and mi.short_name='"+mchtName+"')");
	        return this;
	    }
		
		public Criteria andProductCodeCodeEqualTo(String code) {//商品ID
			addCriterion(" EXISTS(select p.id from bu_product p where p.mcht_id=vt.mcht_id and p.del_flag='0' and p.code='"+code+"')");
	        return this;
	    }
		
		public Criteria andmchtProductTypeIdEqualTo(String productTypeId) {//商家主营类目
			addCriterion(" EXISTS(select mpt.id from bu_mcht_product_type mpt where mpt.del_flag = '0' and mpt.status = '1' and mpt.is_main = '1' and mpt.mcht_id = vt.mcht_id and mpt.product_type_id = "+productTypeId+")");
			return this;
		}
		
		public Criteria andVideoTitleEqualTo(String title) {//视频标题
			addCriterion(" vt.video_id in(select v.id from bu_video v where vt.video_id=v.id and v.del_flag='0' and v.title='"+title+"')");
	        return this;
	    }
		
		public Criteria andVideoStatusEqualTo(String videoStatus) {//视频上下架状态
			addCriterion(" vt.video_id in(select v.id from bu_video v where vt.video_id=v.id and v.del_flag='0' and v.status='"+videoStatus+"')");
	        return this;
	    }
		
		public Criteria andProductTypeIdIn(String sysStaffProductType) {//当前账号复制类目
			addCriterion(" EXISTS(select mpt.mcht_id from bu_mcht_product_type mpt where mpt.del_flag = '0' and mpt.status = '1' and mpt.is_main = '1' and mpt.mcht_id =vt.mcht_id and mpt.product_type_id in("+sysStaffProductType+") )");
			return this;
		}
		
	}
}