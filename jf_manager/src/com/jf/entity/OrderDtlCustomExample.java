package com.jf.entity;

public class OrderDtlCustomExample extends OrderDtlExample{
	
	@Override
    public OrderDtlCustomCriteria createCriteria() {
		OrderDtlCustomCriteria criteria = new OrderDtlCustomCriteria();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }
	
	public class OrderDtlCustomCriteria extends OrderDtlExample.Criteria{
		
		public Criteria andCombineOrderIdEqualTo(Integer combineOrderId) {
			addCriterion(" EXISTS(select mi.id from bu_sub_order mi where mi.id=a.sub_order_id and mi.combine_order_id="+combineOrderId+")");
	        return this;
	    }
		
		public Criteria andMchtTypeEqualTo(String mchtType) {
//			addCriterion(" EXISTS(select mp.id from bu_sub_order mi, bu_mcht_info mp where mi.id=a.sub_order_id and mp.id=mi.mcht_id and mp.mcht_type='"+mchtType+"')");
			addCriterion(" EXISTS(select mi.id from bu_sub_order mi where mi.id=a.sub_order_id and mi.mcht_type='"+mchtType+"')");
	        return this;
	    }

		public Criteria andMchtIdEqualTo(Integer mchtId) {
			addCriterion(" EXISTS(select mi.id from bu_sub_order mi where mi.id=a.sub_order_id and mi.mcht_id='"+mchtId+"')");
	        return this;
	    }
		
		public Criteria andMchtCodeEqualTo(String mchtCode) {
			addCriterion(" EXISTS(select mi.id from bu_sub_order mi,bu_mcht_info b where mi.id=a.sub_order_id and mi.mcht_id=b.id and b.mcht_code='"+mchtCode+"')");
	        return this;
	    }
		
		public Criteria andSubOrderStatusEqualTo(String status) {
			addCriterion(" EXISTS(select mi.id from bu_sub_order mi where mi.id=a.sub_order_id and mi.status='"+status+"')");
			return this;
		}
		
		public Criteria andSubOrderCodeEqualTo(String subOrderCode) {
			addCriterion(" EXISTS(select mi.id from bu_sub_order mi where mi.id=a.sub_order_id and mi.sub_order_code='"+subOrderCode+"')");
			return this;
		}

		public Criteria andPayDateBeginGreaterThanOrEqualTo(String payDateBegin) {
			//addCriterion(" EXISTS(select mi.id from bu_sub_order mi,bu_combine_order b where mi.id=a.sub_order_id and mi.combine_order_id = b.id and b.pay_date >='"+payDateBegin+"')");
			// 上面查询到导致数据中心-运营数据-订单导出时对bu_order_dtl整表查询，速度很慢
			addCriterion(" a.sub_order_id in(select mi.id from bu_sub_order mi,bu_combine_order b where mi.combine_order_id = b.id and b.pay_date >='"+payDateBegin+"')");
			return this;
		}

		public Criteria andPayDateEndLessThanOrEqualTo(String payDateEnd) {
			//addCriterion(" EXISTS(select mi.id from bu_sub_order mi,bu_combine_order b where mi.id=a.sub_order_id and mi.combine_order_id = b.id and b.pay_date <='"+payDateEnd+"')");
			// 上面查询到导致数据中心-运营数据-订单导出时对bu_order_dtl整表查询，速度很慢
			addCriterion(" a.sub_order_id in(select mi.id from bu_sub_order mi,bu_combine_order b where mi.combine_order_id = b.id and b.pay_date <='"+payDateEnd+"')");
			return this;
		}
		
		public Criteria andCombineOrderStatusEqualTo(String status) {
			addCriterion(" EXISTS(select mi.id from bu_sub_order mi,bu_combine_order b where mi.id=a.sub_order_id and mi.combine_order_id = b.id and b.status='"+status+"')");
	        return this;
	    }
		
		public Criteria andProductStatusIsNullOrComplete() {
			addCriterion(" a.id in ((select t.id from bu_order_dtl t where t.product_status IS NULL or t.product_status = '1'))");
			return this;
		}
		
		public Criteria andCompleteDateGreaterThanOrEqualTo(String completeDateBegin) {
			addCriterion(" EXISTS(select so.id from bu_sub_order so where so.id=a.sub_order_id and so.complete_date >='"+completeDateBegin+"')");
			return this;
		}
		
		public Criteria andCompleteDateLessThanOrEqualTo(String completeDateEnd) {
			addCriterion(" EXISTS(select so.id from bu_sub_order so where so.id=a.sub_order_id and so.complete_date <='"+completeDateEnd+"')");
			return this;
		}

		/*2017-11-21 added by lzw for POP product tot*/
		public Criteria andDelFlagAndCombineOrderStatusAndMchtTypeEqualTo(String delFlag, String status, String mchtType) {
			String sql = " a.del_flag = '"+delFlag+"' AND b.id = a.sub_order_id AND b.combine_order_id = c.id AND c.STATUS = '"+status+"' AND d.id = b.mcht_id AND a.product_id = p.id";
			if(mchtType != null){
				sql +=  "AND d.mcht_type = '"+mchtType+"'";
			}
			addCriterion(sql);
			return this;
		}
		
		public Criteria andMchtCodeEqualTo2(String mchtCode) {
			addCriterion(" d.mcht_code='"+mchtCode+"'");
	        return this;
	    }
		public Criteria andMchtNameLike(String mchtName) {
			addCriterion("(d.shop_name like '%"+mchtName+"%' or d.company_name like '%"+mchtName+"%')");
	        return this;
	    }
		
		public Criteria andSubOrderCodeEqualTo2(String subOrderCode) {
			addCriterion(" b.sub_order_code='"+subOrderCode+"'");
			return this;
		}
		
		public Criteria andPayDateBeginGreaterThanOrEqualTo2(String payDateBegin) {
			addCriterion(" c.pay_date >='"+payDateBegin+"'");
			return this;
		}

		public Criteria andPayDateEndLessThanOrEqualTo2(String payDateEnd) {
			addCriterion(" c.pay_date <='"+payDateEnd+"'");
			return this;
		}

		public Criteria andProductCodeEqual(String productCode) {
			addCriterion(" p.code = '"+productCode+"'");
			return this;
		}
		
		public Criteria andOrderTypeEqual(String orderType) {
			addCriterion(" c.order_type = '"+orderType+"'");
			return this;
		}
		
		public Criteria andIsSpecialEqualTo(String isSpecial) {
			addCriterion(" b.is_special='"+isSpecial+"'");
	        return this;
	    }
		
		public Criteria andPromotionTypeEqual(String promotionType) {
			addCriterion(" c.promotion_type = '"+promotionType+"'");
			return this;
		}
		
		/**
		 * 
		 * @Title andArtNoLikeCustom   
		 * @Description TODO(这里用一句话描述这个方法的作用)   
		 * @author pengl
		 * @date 2018年8月30日 上午11:57:13
		 */
		public Criteria andArtNoLikeCustom(String artNo) {
			addCriterion(" a.art_no like '"+artNo+"'");
			return this;
		}
		
		public Criteria andProductTypeIdEqualTo(String productTypeId) {//类目
			addCriterion("b.mcht_id IN (select mpt.mcht_id from bu_mcht_product_type mpt where mpt.del_flag = '0' and mpt.mcht_id = b.mcht_id and mpt.status = '1' and mpt.product_type_id = " + productTypeId + ")" );
			return this;
		}
		
	
		public Criteria andNotMchtCodeEqualTo(String notMchtid) {//指定商家序号不在统计内
			addCriterion(" Not EXISTS(select so.mcht_id from bu_sub_order so where so.id=a.sub_order_id and so.mcht_id in ('"+notMchtid+"'))");
	        return this;
	    }
		
	}
}