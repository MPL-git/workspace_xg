package com.jf.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.jf.entity.ProductExample.Criteria;

public class MchtInfoCustomExample extends MchtInfoExample {

	@Override
	public MchtInfoCustomCriteria createCriteria() {
		MchtInfoCustomCriteria criteria = new MchtInfoCustomCriteria();
		if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
	}
	
	
	public class MchtInfoCustomCriteria extends MchtInfoExample.Criteria {
		
		public Criteria andMchtNameLike(String mchtName) {
			addCriterion(" EXISTS(select x.id from bu_mcht_info x where x.del_flag = '0' and x.id = a.id and (x.short_name like '%" + mchtName + "%' or x.shop_name like '%" + mchtName + "%' or x.company_name like '%" + mchtName + "%' ))");
	    	return this;
		}
		
		public Criteria andEndYearEqualTo(String endYear) {
			addCriterion(" EXISTS(select x.id from bu_mcht_info x where x.del_flag = '0' and x.id = a.id and DATE_FORMAT(x.agreement_end_date, '%Y') = '" + endYear + "' )");
			return this;
		}
		
		public Criteria andEndMonthEqualTo(String endYearMonth) {
			addCriterion(" EXISTS(select x.id from bu_mcht_info x where x.del_flag = '0' and x.id = a.id and DATE_FORMAT(x.agreement_end_date, '%m') = '" + endYearMonth + "' )");
			return this;
		}
		
		public Criteria andContactTypeEqualTo(String contactType, Integer staffId) {
			addCriterion(" EXISTS(select bp.id from bu_mcht_platform_contact bmp, bu_platform_contact bp where bmp.del_flag = '0' and bmp.status = '1' and bp.del_flag = '0' and bp.status = '0' "
					+ "and bmp.mcht_id = a.id and bmp.platform_contact_id = bp.id and bp.staff_id = " + staffId + " and bp.contact_type = '" + contactType + "' )");
			return this;
		}
		
		public Criteria andBrandNameEqualTo(String brandName) {
			addCriterion(" EXISTS(select mpb.id from bu_mcht_product_brand mpb where mpb.del_flag = '0' and mpb.mcht_id = a.id and mpb.product_brand_name = '" + brandName + "' )");
			return this;
		}
		
		public Criteria andProductTypeIdEqualTo(String productTypeId) {
			addCriterion(" EXISTS(select mpt.id from bu_mcht_product_type mpt where mpt.del_flag = '0' and mpt.mcht_id = a.id and mpt.status = '1' "
					+ "and mpt.product_type_id = " + productTypeId + ")" );
			return this;
		}
		
		public Criteria andProductTypeIdEqualTo(Integer productTypeId) {
			addCriterion(" EXISTS(select mpt.id from bu_mcht_product_type mpt where mpt.del_flag = '0' and mpt.mcht_id = a.id "
					+ "and mpt.product_type_id = " + productTypeId + " and mpt.is_main = '1')" );
			return this;
		}
		
		/**
		 * 
		 * @Title andProductTypeIdIsMainEqualTo   
		 * @Description TODO(主营类目)   
		 * @author pengl
		 * @date 2018年6月29日 下午6:15:58
		 */
		public Criteria andProductTypeIdIsMainEqualTo(String productTypeId) {
			addCriterion(" EXISTS(select mpt.id from bu_mcht_product_type mpt where mpt.del_flag = '0' and mpt.mcht_id = a.id and mpt.status = '1' "
					+ "and mpt.is_main = '1' and mpt.product_type_id = " + productTypeId + ")" );
			return this;
		}
		
		public Criteria andPlatformContactEqualTo(String contactType, String platformContactId) {
			addCriterion(" EXISTS(select pc.id from bu_mcht_platform_contact mpc, bu_platform_contact pc where mpc.mcht_id = a.id and mpc.del_flag = '0' and mpc.status = '1' and pc.del_flag = '0' and pc.status = '0' "
					+ "and mpc.platform_contact_id = pc.id and pc.contact_type = '" + contactType + "' and pc.id = " + platformContactId + " )");
			return this;
		}
		
		public Criteria andProductisspeciallyapprovedEqualTo(String isSpeciallyApprovedDesc) {
			addCriterion(" EXISTS(select mpt.mcht_id from bu_mcht_product_brand mpt where mpt.del_flag = '0' and mpt.mcht_id = a.id "
					+ "and mpt.is_specially_approved = " + isSpeciallyApprovedDesc + ")" );
			return this;
		}
		
		public Criteria andCompanyOrShopNameLike(String mchtName) {
			addCriterion(" (shop_name like '%"+mchtName+"%' or company_name like '%"+mchtName+"%')");
	    	return this;
		}
		
		public Criteria andXiaonengStatusEqualTo(String xiaonengStatus) {
			addCriterion(" EXISTS(select xcs.id from bu_xiaoneng_customer_service xcs where xcs.del_flag = '0' and xcs.id = a.xiaoneng_id and xcs.status = '" + xiaonengStatus + "' )");
	    	return this;
		}
		
		public Criteria andXiaonengCodeEqualTo(String xiaonengCode) {
			addCriterion(" EXISTS(select xcs.id from bu_xiaoneng_customer_service xcs where xcs.del_flag = '0' and xcs.id = a.xiaoneng_id and xcs.code = '" + xiaonengCode + "' )");
			return this;
		}
		
		public Criteria andXiaonengBusIdEqualTo(Integer xiaonengBusId) {
			addCriterion(" EXISTS(select xcs.id from bu_xiaoneng_customer_service xcs where xcs.del_flag = '0' and xcs.id = a.xiaoneng_id and xcs.bus_id = " + xiaonengBusId + " )");
			return this;
		}
		
		public Criteria andSourceNicheShopNotEqualTo(){//是否存在好店
			addCriterion(" NOT EXISTS(select sn.id from bu_source_niche sn where sn.link_id=a.id and sn.del_flag='0' and sn.status='0' and sn.type='2')");
			return this;
		}
		
		public Criteria andMchtnameLike(String mchtName) {
			addCriterion("(a.short_name like '%" + mchtName + "%' or a.shop_name like '%" + mchtName + "%' or a.company_name like '%" + mchtName + "%' )");
	    	return this;
		}
		
		public Criteria andproductBrandNameEqualTo(String productBrandName) {
			addCriterion(" a.id in (select mpb.mcht_id from bu_product_brand pb,bu_mcht_product_brand mpb where mpb.product_brand_id=pb.id and a.id =mpb.mcht_id and mpb.del_flag='0' and pb.del_flag='0' and (pb.name='"+productBrandName+"' or pb.name_zh='"+productBrandName+"' or pb.name_en='"+productBrandName+"'))");
			return this;
		}
    	
		public Criteria andauditriskDescEqualTo(String auditriskDesc){
			if (auditriskDesc.equals("1")) {
				addCriterion(" a.id in(select mo.mcht_id from bu_mcht_optimize mo where mo.mcht_id=a.id and mo.del_flag='0' and mo.audit_risk='"+1+"')");	
			}else if (auditriskDesc.equals("2")) {
				addCriterion(" a.id in(select mo.mcht_id from bu_mcht_optimize mo where mo.mcht_id=a.id and mo.del_flag='0' and mo.audit_risk='"+2+"')");
			}else {
				addCriterion(" a.id in(select mo.mcht_id from bu_mcht_optimize mo where mo.mcht_id=a.id and mo.del_flag='0' and mo.audit_risk='"+3+"')");
			}
			return this;
		}
		
		public Criteria anddegreeAdaptabilityDescEqualTo(String degreeAdaptabilityDesc){
			if (degreeAdaptabilityDesc.equals("1")) {
				addCriterion(" a.id in(select mo.mcht_id from bu_mcht_optimize mo where mo.mcht_id=a.id and mo.del_flag='0' and mo.degree_adaptability='"+1+"')");	
			}else if (degreeAdaptabilityDesc.equals("2")) {
				addCriterion(" a.id in(select mo.mcht_id from bu_mcht_optimize mo where mo.mcht_id=a.id and mo.del_flag='0' and mo.degree_adaptability='"+2+"')");
			}else if (degreeAdaptabilityDesc.equals("3")) {
				addCriterion(" a.id in(select mo.mcht_id from bu_mcht_optimize mo where mo.mcht_id=a.id and mo.del_flag='0' and mo.degree_adaptability='"+3+"')");
			}
			return this;
		}
		
		public Criteria andproductTypeIdEqualTo(String productTypeId) {
			addCriterion(" a.id in (select mpt.mcht_id from bu_mcht_product_type mpt where mpt.mcht_id =a.id and mpt.is_main = '1' and mpt.del_flag = '0'  and mpt.product_type_id='"+productTypeId+"')");
	        return this;
	    }
		
		/**
		 * 
		 * @Title andProductBrandNameLike   
		 * @Description TODO(招商入驻进度-->品牌)   
		 * @author pengl
		 * @date 2018年5月10日 下午5:55:27
		 */
		public Criteria andProductBrandNameLike(String productBrandName) {
			addCriterion(" EXISTS(SELECT mpb.id FROM bu_mcht_product_brand mpb WHERE mpb.del_flag = '0' AND mpb.mcht_id = t.id AND mpb.product_brand_name LIKE '" + productBrandName + "' )");
			return this;
		}
		
		/**
		 * 
		 * @Title andMchtSettledContactNameLike   
		 * @Description TODO(招商入驻进度-->联系人)   
		 * @author pengl
		 * @date 2018年5月10日 下午5:58:28
		 */
		public Criteria andMchtSettledContactNameLike(String mchtSettledContactName) {
			addCriterion(" EXISTS(SELECT msa.id FROM bu_mcht_settled_apply msa WHERE msa.del_flag = '0' AND msa.mcht_id = t.id AND msa.contact_name LIKE '" + mchtSettledContactName + "' )");
			return this;
		}
		
		/**
		 * 
		 * @Title andMchtProductTypeIdEqualTo   
		 * @Description TODO(招商入驻进度-->主营类目)   
		 * @author pengl
		 * @date 2018年5月10日 下午6:04:22
		 */
		public Criteria andMchtProductTypeIdEqualTo(String productTypeId) {
			addCriterion(" EXISTS(SELECT mpt.id FROM bu_mcht_product_type mpt WHERE mpt.del_flag = '0' AND mpt.is_main = '1' AND mpt.mcht_id = t.id AND mpt.product_type_id = " + productTypeId + " )");
			return this;
		}
		
		/**
		 * 
		 * @Title andMchtContractAuditStatusEqualTo   
		 * @Description TODO(招商入驻进度-->线上合同)   
		 * @author pengl
		 * @date 2018年5月10日 下午6:11:12
		 */
		public Criteria andMchtContractAuditStatusEqualTo(String mchtContractAuditStatus) {
			addCriterion(" EXISTS(SELECT mc.id FROM bu_mcht_contract mc WHERE mc.del_flag = '0' AND mc.mcht_id = t.id AND mc.audit_status = '" + mchtContractAuditStatus + "' )");
			return this;
		}
		
		/**
		 * 
		 * @Title andMchtContractIsMchtSendEqualTo   
		 * @Description TODO(招商入驻进度-->商家合同寄出)   
		 * @author pengl
		 * @date 2018年5月10日 下午6:13:26
		 */
		public Criteria andMchtContractIsMchtSendEqualTo(String mchtContractIsMchtSend) {
			addCriterion(" EXISTS(SELECT mc.id FROM bu_mcht_contract mc WHERE mc.del_flag = '0' AND mc.mcht_id = t.id AND mc.is_mcht_send = '" + mchtContractIsMchtSend + "' )");
			return this;
		}
		
		/**
		 * 
		 * @Title andMchtContractIsPlatformSendEqualTo   
		 * @Description TODO(招商入驻进度-->平台合同寄出)   
		 * @author pengl
		 * @date 2018年5月10日 下午6:14:48
		 */
		public Criteria andMchtContractIsPlatformSendEqualTo(String mchtContractIsPlatformSend) {
			addCriterion(" EXISTS(SELECT mc.id FROM bu_mcht_contract mc WHERE mc.del_flag = '0' AND mc.mcht_id = t.id AND mc.is_platform_send = '" + mchtContractIsPlatformSend + "' )");
			return this;
		}
		
		/**
		 * 
		 * @Title andMchtContractArchiveStatusEqualTo   
		 * @Description TODO(招商入驻进度-->合同归档)   
		 * @author pengl
		 * @date 2018年5月10日 下午6:23:04
		 */
		public Criteria andMchtContractArchiveStatusEqualTo(String mchtContractArchiveStatus) {
			addCriterion(" EXISTS(SELECT mc.id FROM bu_mcht_contract mc WHERE mc.del_flag = '0' AND mc.mcht_id = t.id AND mc.archive_status = '" + mchtContractArchiveStatus + "' )");
			return this;
		}
		
		/**
		 * 
		 * @Title andPlatformContactEqualTo   
		 * @Description TODO(招商入驻进度-->对接人)   
		 * @author pengl
		 * @date 2018年5月10日 下午6:30:48
		 */
		public Criteria andPlatformContactIdEqualTo(String platformContactId) {
			addCriterion(" EXISTS(SELECT mpc.mcht_id FROM bu_mcht_platform_contact mpc WHERE mpc.del_flag = '0' AND mpc.status = '1' AND mpc.mcht_id = t.id AND mpc.platform_contact_id = " + Integer.parseInt(platformContactId) + " )");
			return this;
		}

		public Criteria andPlatformContactIdEqualTo(int platformContactId) {
			addCriterion(" EXISTS(SELECT id FROM bu_mcht_platform_contact mpc WHERE mpc.del_flag = '0' AND mpc.status = '1' AND mpc.mcht_id = a.id AND mpc.platform_contact_id = " + platformContactId + " )");
			return this;
		}
		
		//搜索商品描述评分
		public Criteria andavgProductScoreBeginEqualTo(BigDecimal avgProductScoreBegin){
			addCriterion("(select TRUNCATE(AVG(c.product_score), 2) from bu_comment c where c.del_flag = '0' and c.mcht_id = a.id )>="+avgProductScoreBegin);
			return this;		
		}
		public Criteria andavgProductScoreEndEqualTo(BigDecimal avgProductScoreEnd){
			addCriterion("(select TRUNCATE(AVG(c.product_score), 2) from bu_comment c where c.del_flag = '0' and c.mcht_id = a.id )<="+avgProductScoreEnd);
			return this;		
		}
		
		//搜索卖家服务评分
		public Criteria andavgMchtScoreBeginEqualTo(BigDecimal avgMchtScoreBegin){
			addCriterion("(select TRUNCATE(AVG(s.mcht_score), 2) from bu_shop_score s where s.del_flag = '0' and s.mcht_id = a.id )>="+avgMchtScoreBegin);
			return this;		
		}
		public Criteria andavgMchtScoreEndTo(BigDecimal avgMchtScoreEnd){
			addCriterion("(select TRUNCATE(AVG(s.mcht_score), 2) from bu_shop_score s where s.del_flag = '0' and s.mcht_id = a.id )<="+avgMchtScoreEnd);
			return this;		
		}
	    
		//搜索物流服务评分
		public Criteria andavgWuliuScoreBeginEqualTo(BigDecimal avgWuliuScoreBegin){
			addCriterion("(select TRUNCATE(AVG(ss.wuliu_score), 2) from bu_shop_score ss where ss.del_flag = '0' and ss.mcht_id = a.id )>="+avgWuliuScoreBegin);
			return this;		
		}
		public Criteria andavgWuliuScoreEndEndTo(BigDecimal avgWuliuScoreEnd){
			addCriterion("(select TRUNCATE(AVG(ss.wuliu_score), 2) from bu_shop_score ss where ss.del_flag = '0' and ss.mcht_id = a.id)<="+avgWuliuScoreEnd);
			return this;		
		}
		
		//合作开始时间和创建时间
		public Criteria andStartCooperateBeginDate(String value) {
			addCriterion("(IFNULL(a.cooperate_begin_date,a.create_date) >='"+value+"')");
			return this;
		}
		
		public Criteria andEndCooperateBeginDate(String value) {
			addCriterion("(IFNULL(a.cooperate_begin_date,a.create_date) <='"+value+"')");
			return this;
		}
		
		/**
		 * 
		 * @Title andMchtProductBrandNameZhEnLike   
		 * @Description TODO(评价数据-->商家信息-->品牌名称、中文名称、英文名称)   
		 * @author pengl
		 * @date 2018年9月20日 下午2:53:16
		 */
		public Criteria andMchtProductBrandNameZhEnLike(String brandNameZhEn) {
			addCriterion(" EXISTS(SELECT mpb.id FROM bu_mcht_product_brand mpb, bu_product_brand pb WHERE mpb.del_flag = '0' AND mpb.audit_status = '3'"
					+ " AND mpb.status != '3' AND mpb.mcht_id = a.id AND mpb.product_brand_id = pb.id AND pb.del_flag = '0'"
					+ " AND (pb.name like '" + brandNameZhEn + "' OR pb.name_zh like '" + brandNameZhEn + "' OR pb.name_en like '" + brandNameZhEn + "' ) )");
			return this;
		}

		/**
		 * @MethodName andcompanyOrShopNameLike
		 * @Description TODO
		 * @author chengh
		 * @date 2019年8月22日 上午11:50:41
		 */
		public Criteria andcompanyOrShopNameLike(String companyOrShop) {
			// TODO Auto-generated method stub
			addCriterion(" (a.company_name like '%"+companyOrShop+"%' or a.shop_name like '%"+companyOrShop+"%')");
		    return this;
		}

		/**
		 * @MethodName andPopSettlePlanTypeEqualTo
		 * @Description TODO
		 * @author chengh
		 * @date 2019年8月22日 上午11:54:12
		 */
		public Criteria andPopSettlePlanTypeEqualTo(String type) {
			// TODO Auto-generated method stub
			addCriterion(" a.id =(select b.mcht_id from bu_pop_settle_plan b where b.del_flag = '0' and b.type = '"+type+"')");
		    return this;
		}

		/**
		 * @MethodName andDelFlagCustomEqualTo
		 * @Description TODO
		 * @author chengh
		 * @date 2019年8月23日 上午10:58:53
		 */
		public Criteria andDelFlagCustomEqualTo() {
			// TODO Auto-generated method stub
			addCriterion(" a.status = '1' and a.del_flag = '0' and a.mcht_type = '2' and a.settled_type = '1'");
		    return this;
			
		}

		/**
		 * @MethodName andRenewProStatus
		 * @Description TODO
		 * @author chengh
		 * @date 2019年7月30日 下午8:14:35
		 */
		public Criteria andRenewProStatus(String date) {
			// TODO Auto-generated method stub
			addCriterion(" a.id in (select s.mcht_id from (select t.* from (select * from bu_mcht_contract d where d.del_flag = '0' order by d.id desc) t group by t.mcht_id) s where s.end_date <='"+date+"' and (s.renew_pro_status in('0','6') or s.renew_status = '0'))");
			return this;
		}

		/**
		 * @MethodName andPlatContactStaffIdEqualTo
		 * @Description TODO
		 * @author chengh
		 * @date 2019年7月31日 上午8:51:32
		 */
		public Criteria andPlatContactStaffIdEqualTo(Integer platContactStaffId) {
			// TODO Auto-generated method stub
			addCriterion(" a.id IN(select mpc.mcht_id from bu_platform_contact pc, bu_mcht_platform_contact mpc where pc.del_flag = '0' and pc.status = '0' and (pc.staff_id = "+platContactStaffId+" or pc.assistant_id in(select bpc.id from bu_platform_contact bpc where bpc.del_flag = '0' and bpc.status = '0' and bpc.staff_id = "+platContactStaffId+")) and mpc.del_flag = '0' and mpc.status = '1' and mpc.platform_contact_id = pc.id )");
		    return this;
		}

		/**
		 * @MethodName andAgreementBeginDateCustomGreaterThan
		 * @Description TODO
		 * @author chengh
		 * @date 2019年7月31日 上午9:13:17
		 */
		public Criteria andAgreementBeginDateCustomGreaterThan(String day,String nowDate) {
			// TODO Auto-generated method stub
			addCriterion(" a.id in(select s.mcht_id from (select t.* from (select * from bu_mcht_contract d where d.del_flag = '0' order by id desc) t group by t.mcht_id) s where DATEDIFF(s.end_date,'"+nowDate+"') >= "+day+")");
			return this;
		}

		/**
		 * @MethodName andAgreementBeginDateCustomLessThan
		 * @Description TODO
		 * @author chengh
		 * @date 2019年7月31日 上午9:13:21
		 */
		public Criteria andAgreementBeginDateCustomLessThan(String day,String nowDate) {
			// TODO Auto-generated method stub
			addCriterion(" a.id in(select s.mcht_id from (select t.* from (select * from bu_mcht_contract d where d.del_flag = '0' order by id desc) t group by t.mcht_id) s where DATEDIFF(s.end_date,'"+nowDate+"') <= "+day+")");
			return this;
		}

		/**
		 *
		 * @Title   商家流量统计报表栏目
		 * @Description 主营类目
		 */
		public Criteria andMchtMainTypeIdEqualTo(String productTypeId) {
			addCriterion(" EXISTS(SELECT mpt.id FROM bu_mcht_product_type mpt WHERE mpt.del_flag = '0' AND mpt.is_main = '1' AND mpt.mcht_id = a.id AND mpt.product_type_id = " + productTypeId + " )");
			return this;
		}

		/**
		 *
		 * @Title  商家流量统计报表栏目
		 * @Description 报表类目
		 */
		public Criteria andReportColumnIdEqualTo(Integer columnTypeId){
			addCriterion("EXISTS (select bmp.value_id from bu_member_pv bmp where bmp.mcht_id = a.id and bmp.column_type = '"+columnTypeId+"')");
			return this;
		}

		public Criteria andBrandIdEqualTo(String brandId) {
			addCriterion(" EXISTS(select mpb.id from bu_mcht_product_brand mpb where mpb.del_flag = '0' and mpb.mcht_id = a.id and mpb.product_brand_id = '" + brandId + "' )");
			return this;
		}

	}
}
