package com.jf.entity;

public class CommentCustomExample extends CommentExample {

	@Override
    public CommentCustomCriteria createCriteria() {
		CommentCustomCriteria criteria = new CommentCustomCriteria();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

	public class CommentCustomCriteria extends Criteria{
		
		/**
		 * 
		 * @Title andCommentPicExists   
		 * @Description TODO(评价类型-有图-无图)   
		 * @author pengl
		 * @date 2018年6月27日 下午6:17:16
		 */
		public Criteria andCommentPicExists(String commentPicStatus){
			if("1".equals(commentPicStatus)) { //有图
				addCriterion(" EXISTS(select cp.id from bu_comment_pic cp where cp.del_flag = '0' and cp.pic_type = '1' and cp.comment_id = t.id)");
			}else { //无图
				addCriterion(" NOT EXISTS(select cp.id from bu_comment_pic cp where cp.del_flag = '0' and cp.pic_type = '1' and cp.comment_id = t.id)");
			}
			return this;
		}
		
		/**
		 * 
		 * @Title andMchtCodeEqualTo   
		 * @Description TODO(商家序号)   
		 * @author pengl
		 * @date 2018年6月27日 下午6:17:19
		 */
		public Criteria andMchtCodeEqualTo(String mchtCode){
			addCriterion(" t.mcht_id IN(select mi.id from bu_mcht_info mi where mi.del_flag = '0' and mi.mcht_code = '"+mchtCode+"')");
			return this;
		}
		
		/**
		 * 
		 * @Title andShopNameLike   
		 * @Description TODO(店铺名称)   
		 * @author pengl
		 * @date 2018年6月27日 下午6:17:23
		 */
		public Criteria andShopNameLike(String shopName){
			addCriterion(" t.mcht_id IN(select mi.id from bu_mcht_info mi where mi.del_flag = '0' and mi.shop_name like '"+shopName+"')");
			return this;
		}
		
		/**
		 * 
		 * @Title andProductCodeEqualTo   
		 * @Description TODO(商品ID)   
		 * @author pengl
		 * @date 2018年6月27日 下午6:17:26
		 */
		public Criteria andProductCodeEqualTo(String productCode){
			addCriterion(" t.product_id IN(select p.id from bu_product p where p.del_flag = '0' and p.code = '"+productCode+"' )");
			return this;
		}
		
		/**
		 * 
		 * @Title andBrandNameLike   
		 * @Description TODO(品牌名称)   
		 * @author pengl
		 * @date 2018年6月27日 下午6:17:29
		 */
		public Criteria andBrandNameLike(String brandName){
			addCriterion(" EXISTS(select od.id from bu_order_dtl od where od.del_flag = '0' and od.id = t.order_dtl_id and od.brand_name like '"+brandName+"' )");
			return this;
		}
		
		/**
		 * 
		 * @Title andMchtNameLike   
		 * @Description TODO(商家名称)   
		 * @author pengl
		 * @date 2018年9月25日 下午4:34:06
		 */
		public Criteria andMchtNameLike(String mchtName){
			addCriterion(" t.mcht_id IN(select mi.id from bu_mcht_info mi where mi.del_flag = '0' and (mi.shop_name like '"+mchtName+"' or mi.company_name like '"+mchtName+"'))");
			return this;
		}
		
		/**
		 * 
		 * @Title andProductTypeAll   
		 * @Description TODO(分类)   
		 * @author pengl
		 * @date 2018年9月25日 下午5:09:22
		 */
		public Criteria andProductTypeAll(String productTypeIds){
			addCriterion(" t.product_id IN(select p.id from bu_product p where p.del_flag = '0' and p.product_type_id in ("+productTypeIds+"))");
			return this;
		}
		
		/**
		 * 
		 * @Title andSubOrderCodeEqualTo   
		 * @Description TODO(订单编号)   
		 * @author pengl
		 * @date 2018年9月25日 下午5:16:30
		 */
		public Criteria andSubOrderCodeEqualTo(String subOrderCode){
			addCriterion(" EXISTS(select so.id from bu_sub_order so where so.del_flag = '0' and so.id = t.sub_order_id and so.sub_order_code = '"+subOrderCode+"')");
			return this;
		}
		
		/**
		 * 
		 * @Title andCommentStatus   
		 * @Description TODO(评价类型-有效评价-有图-有追评)   
		 * @author pengl
		 * @date 2018年9月29日 上午11:04:41
		 */
		public Criteria andCommentStatus(String commentStatus){
			if("1".equals(commentStatus)) { //有效评价
				addCriterion(" t.content != '默认好评！' and t.content != '' and t.content is not null");
			}else if("2".equals(commentStatus)){ //有图
				addCriterion(" EXISTS(select cp.id from bu_comment_pic cp where cp.del_flag = '0' and cp.pic_type = '1' and cp.comment_id = t.id)");
			}else { //有追评
				addCriterion(" EXISTS(select bc.id from bu_comment bc where bc.del_flag = '0' and bc.is_append_comment = '1' and bc.mcht_id = t.mcht_id and bc.sub_order_id = t.sub_order_id)");
			}
			return this;
		}
		
		/**
		 * 
		 * @Title andCommentGrade   
		 * @Description TODO(评价等级-好评-中评-差评)   
		 * @author pengl
		 * @date 2018年9月29日 上午11:17:23
		 */
		public Criteria andCommentGrade(String commentGrade){
			if("1".equals(commentGrade)) { //好评
				addCriterion(" (t.product_score = 4 or t.product_score = 5)");
			}else if("2".equals(commentGrade)){ //中评
				addCriterion(" t.product_score = 3");
			}else { //差评
				addCriterion(" (t.product_score = 1 or t.product_score = 2)");
			}
			return this;
		}
		
	}
	
}
