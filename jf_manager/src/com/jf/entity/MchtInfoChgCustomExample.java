package com.jf.entity;

import org.apache.commons.lang.StringUtils;

public class MchtInfoChgCustomExample extends MchtInfoChgExample{
	
	@Override
    public MchtInfoChgCustomCriteria createCriteria() {
		MchtInfoChgCustomCriteria criteria = new MchtInfoChgCustomCriteria();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }
	
	public class MchtInfoChgCustomCriteria extends MchtInfoChgExample.Criteria{
		public Criteria andMchtTypeEqualTo(String mchtType) {
			addCriterion(" EXISTS(select id from bu_mcht_info mi where mi.id=a.mcht_id and mi.del_flag='0' and mi.mcht_type='"+mchtType+"')");
	        return this;
	    }
		
		public Criteria andMchtStatusEqualTo(String mchtStatus) {
			addCriterion(" EXISTS(select id from bu_mcht_info mi where mi.id=a.mcht_id and mi.del_flag='0' and mi.status='"+mchtStatus+"')");
			return this;
		}
		
		public Criteria andMchtIsManageSelfEqualTo(String mchtIsManageSelf) {
			addCriterion(" EXISTS(select id from bu_mcht_info mi where mi.id=a.mcht_id and mi.del_flag='0' and mi.is_manage_self='"+mchtIsManageSelf+"')");
			return this;
		}
		
		public Criteria andPlatformContactIdEqualTo(Integer platformContactId) {
			addCriterion(" EXISTS (select mi.mcht_id from bu_mcht_platform_contact mi where mi.mcht_id=a.mcht_id and mi.platform_contact_id="+platformContactId+")");
			return this;
		}
		
		public Criteria andWhereClause(String WhereClause) {
			addCriterion(WhereClause);
			return this;
		}
		
		public Criteria andSettledTypeEqualTo(String settledType) {
			addCriterion(" EXISTS(select id from bu_mcht_info mi where mi.id=a.mcht_id and mi.del_flag='0' and mi.settled_type='"+settledType+"')");
	        return this;
	    }

		/**
		 * @MethodName andcompanyOrShopNameLike
		 * @Description TODO
		 * @author chengh
		 * @date 2019年7月19日 下午4:28:49
		 */
		public Criteria andcompanyOrShopNameLike(String companyOrShop) {
			// TODO Auto-generated method stub
			addCriterion(" (a.company_name like "+companyOrShop+" or EXISTS(select b.id from bu_mcht_info b where b.id = a.mcht_id and b.shop_name like "+companyOrShop+"))");
	        return this;
		}

		/**
		 * @MethodName andPlatContactStaffIdEqualTo
		 * @Description TODO
		 * @author chengh
		 * @date 2019年7月19日 下午4:58:51
		 */
		public Criteria andPlatContactStaffIdEqualTo(Integer platContactStaffId) {
			// TODO Auto-generated method stub
			addCriterion(" a.mcht_id IN(select mpc.mcht_id from bu_platform_contact pc, bu_mcht_platform_contact mpc where pc.del_flag = '0' and pc.status = '0' and (pc.staff_id = "+platContactStaffId+" or pc.assistant_id in(select bpc.id from bu_platform_contact bpc where bpc.del_flag = '0' and bpc.status = '0' and bpc.staff_id = "+platContactStaffId+")) and mpc.del_flag = '0' and mpc.status = '1' and mpc.platform_contact_id = pc.id )");
	        return this;
		}

		/**
		 * @MethodName andProductTypeIdEqualTo
		 * @Description TODO
		 * @author chengh
		 * @date 2019年7月19日 下午5:11:18
		 */
		public Criteria andProductTypeIdEqualTo(Integer productTypeId) {
			// TODO Auto-generated method stub
			addCriterion(" EXISTS (select mpt.id from bu_mcht_product_type mpt where mpt.mcht_id = a.mcht_id and mpt.is_main = '1' and mpt.del_flag = '0'  and mpt.product_type_id ="+productTypeId+")");
			return this;
		}

		/**
		 * @MethodName andCompanyInfoArchiveStatusCustomEqualTo
		 * @Description TODO
		 * @author chengh
		 * @date 2019年7月23日 上午9:00:27
		 */
		public Criteria andCompanyInfoArchiveStatusCustomEqualTo(String companyInfoArchiveStatus) {
			// TODO Auto-generated method stub
			if(StringUtils.equals("0",companyInfoArchiveStatus)){
				addCriterion(" (a.company_info_archive_status = '0' or a.company_info_archive_status is NULL)");
			}
			if(StringUtils.equals("1",companyInfoArchiveStatus)){
				addCriterion(" a.company_info_archive_status in('1','2','3')");
			}
			return this;
		}

		/**
		 * @MethodName andArchiveDealStatusEqualToCustom
		 * @Description TODO
		 * @author chengh
		 * @date 2019年8月13日 下午4:20:59
		 */
		public Criteria andArchiveDealStatusEqualToCustom(String archiveDealStatus) {
			// TODO Auto-generated method stub
			addCriterion(" (a.archive_deal_status = '0' or a.archive_deal_status is null)");
			return this;
		}
	}
}