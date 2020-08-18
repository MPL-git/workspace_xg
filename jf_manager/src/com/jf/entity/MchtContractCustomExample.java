package com.jf.entity;

public class MchtContractCustomExample extends MchtContractExample {

	@Override
	public MchtContractCustomCriteria createCriteria() {
		MchtContractCustomCriteria criteria = new MchtContractCustomCriteria();
		if(oredCriteria.size() == 0) {
			oredCriteria.add(criteria);
		}
		return criteria;
	}
	
	public class MchtContractCustomCriteria extends MchtContractExample.Criteria {
		
		public Criteria andMchtNameLike(String mchtName) {
			addCriterion(" EXISTS(select x.id from bu_mcht_info x where x.del_flag = '0' and x.id = mcht_id and (x.short_name like '%" + mchtName + "%' or x.shop_name like '%" + mchtName + "%' or x.company_name like '%" + mchtName + "%' ))");
	    	return this;
		}
		
		public Criteria andMchtStatusByEqualTo(String status) {
			addCriterion(" EXISTS(select x.id from bu_mcht_info x where x.del_flag = '0' and x.id = mcht_id and x.status = '" + status + "')");
			return this;
		} 
		
		public Criteria andMchtStatusNotEqualTo(String status) {
			addCriterion(" EXISTS(select x.id from bu_mcht_info x where x.del_flag = '0' and x.id = mcht_id and x.status != '" + status + "')");
			return this;
		} 
		
		public Criteria andMchtStatusIn(String statusIn) {
			addCriterion(" EXISTS(select x.id from bu_mcht_info x where x.del_flag = '0' and x.id = mcht_id and x.status in(" + statusIn + "))");
			return this;
		}
		
		public Criteria andMchtCodeByEqualTo(String mchtCode) {
			addCriterion(" EXISTS(select x.id from bu_mcht_info x where x.del_flag = '0' and x.id = mcht_id and x.mcht_code = '" + mchtCode + "')");
			return this;
		}
		
		public Criteria andMchtTypeByEqualTo(String mchtType) {
			addCriterion(" EXISTS(select x.id from bu_mcht_info x where x.del_flag = '0' and x.id = mcht_id and x.mcht_type = '" + mchtType + "')");
			return this;
		}
		public Criteria andMchtDelFlagEqualTo(String delFlag) {
			addCriterion(" EXISTS(select x.id from bu_mcht_info x where x.del_flag = '"+delFlag+"' and x.id = mcht_id)");
			return this;
		}	
		
		public Criteria andMchtArchiveStatusEqualTo(String archiveStatus) {
			addCriterion(" EXISTS(select x.id from bu_mcht_info x where x.del_flag = '0' and x.id = mcht_id and (x.archive_status = '" + archiveStatus + "' or x.archive_status IS NULL))");
			return this;
		}
		
		public Criteria andCompanyInfoArchiveStatusEqualTo(String companyInfoArchiveStatus) {
			if(companyInfoArchiveStatus.equals("0")){
				addCriterion(" EXISTS(select x.id from bu_mcht_info x where x.del_flag = '0' and x.id = mcht_id and (x.company_info_archive_status = '" + companyInfoArchiveStatus + "' or x.company_info_archive_status is null))");
			}else{
				addCriterion(" EXISTS(select x.id from bu_mcht_info x where x.del_flag = '0' and x.id = mcht_id and (x.company_info_archive_status = '" + companyInfoArchiveStatus + "'))");
			}
			return this;
		}
		
		public Criteria andMchtBrandArchiveStatusEqualTo(String mchtBrandArchiveStatus) {
			if(mchtBrandArchiveStatus.equals("0")){
				addCriterion(" EXISTS(select x.id from bu_mcht_info x where x.del_flag = '0' and x.id = mcht_id and (x.mcht_brand_archive_status = '" + mchtBrandArchiveStatus + "' or x.mcht_brand_archive_status is null))");
			}else{
				addCriterion(" EXISTS(select x.id from bu_mcht_info x where x.del_flag = '0' and x.id = mcht_id and (x.mcht_brand_archive_status = '" + mchtBrandArchiveStatus + "'))");
			}
			return this;
		}
		
		public Criteria andNotArchiveEqualTo() {
			addCriterion("NOT EXISTS(select x.id from bu_mcht_info x where x.del_flag = '0' and x.id = t.mcht_id and x.mcht_brand_archive_status = '1' and x.company_info_archive_status = '1' and t.archive_status = '1')");
			return this;
		}

		public Criteria andPlatformContactIdEqualTo(Integer platformContactId) {
			addCriterion(" EXISTS(select mpc.id from bu_mcht_platform_contact mpc where mpc.del_flag = '0' and mpc.mcht_id = mcht_id and mpc.status = '1' and mpc.platform_contact_id="+platformContactId+")");
			return this;
		}

		public Criteria andIsMyFawuEqualTo(Integer staffID) {
			addCriterion(" EXISTS(select pc.id from bu_mcht_platform_contact mpc,bu_platform_contact pc where mpc.del_flag = '0' and mpc.mcht_id = t.mcht_id and mpc.platform_contact_id = pc.id and mpc.status = '1' and pc.del_flag='0' and pc.status = '0' and pc.contact_type='7' and pc.staff_id = "+staffID+")");
			return this;
		}

		public Criteria andSettledTypeEqualTo(String settledType) {
			addCriterion(" EXISTS(select x.id from bu_mcht_info x where x.del_flag = '0' and x.id = mcht_id and x.settled_type = '" + settledType + "')");
			return this;
		}

		//线上合同审核是否自营
		public Criteria andIsManageSelfEqualTo(String isManageSelf) {
			addCriterion(" EXISTS(select x.id from bu_mcht_info x where x.del_flag = '0' and x.id = mcht_id and x.is_manage_self = '" + isManageSelf + "')");
			return this;
		}

		
		public Criteria andProductTypeIdTo(int productTypeId) {
			addCriterion(" EXISTS(select mpt.id from bu_mcht_product_type mpt where mpt.mcht_id = bu_mcht_contract.mcht_id and mpt.del_flag = '0' and mpt.product_type_id = "+productTypeId+" and mpt.is_main='1' and mpt.status='1')");
	    	return this;
		}
		
		public Criteria andPlatContactStaffIdEqualTo(Integer platContactStaffId) {
			addCriterion(" mcht_id IN(select mpc.mcht_id from bu_platform_contact pc, bu_mcht_platform_contact mpc where pc.del_flag = '0' and pc.status = '0' and (pc.staff_id = "+platContactStaffId+" or pc.assistant_id in(select bpc.id from bu_platform_contact bpc where bpc.del_flag = '0' and bpc.status = '0' and bpc.staff_id = "+platContactStaffId+")) and mpc.del_flag = '0' and mpc.status = '1' and mpc.platform_contact_id = pc.id )");
			return this;
		}
	
		/**
		 * @MethodName andMchtCodeEqualTo
		 * @Description TODO
		 * @author chengh
		 * @date 2019年7月31日 下午4:01:01
		 */
		public Criteria andMchtCodeEqualTo(String mchtCode) {
			// TODO Auto-generated method stub
			addCriterion(" EXISTS(select id from bu_mcht_info mi where mi.id = t.mcht_id and mi.mcht_code = '"+mchtCode+"' and mi.del_flag = '0')");
			return this;
		}

		/**
		 * @MethodName andcompanyOrShopNameLike
		 * @Description TODO
		 * @author chengh
		 * @date 2019年7月31日 下午5:38:58
		 */
		public Criteria andcompanyOrShopNameLike(String companyOrShop) {
			// TODO Auto-generated method stub
			// TODO Auto-generated method stub
			addCriterion(" EXISTS(select mi.id from bu_mcht_info mi where mi.id = t.mcht_id and (mi.company_name like '%"+companyOrShop+"%' or mi.shop_name like '%"+companyOrShop+"%') and mi.del_flag='0')");
		    return this;
		}

		/**
		 * @MethodName andProductTypeIdEqualTo
		 * @Description TODO
		 * @author chengh
		 * @date 2019年7月31日 下午5:39:52
		 */
		public Criteria andProductTypeIdEqualTo(Integer productTypeId) {
			addCriterion(" EXISTS(select mpt.id from bu_mcht_product_type mpt where mpt.del_flag = '0' and mpt.mcht_id = t.mcht_id "
					+ "and mpt.product_type_id = " + productTypeId + " AND mpt.is_main = '1')" );
			return this;
		}

		/**
		 * @MethodName andPlatContactStaffIdEqualTo
		 * @Description TODO
		 * @author chengh
		 * @date 2019年7月31日 下午5:41:07
		 */
		public Criteria andPlatContactStaffIdEqualTos(Integer platContactStaffId) {
			// TODO Auto-generated method stub
			addCriterion(" t.mcht_id IN(select mpc.mcht_id from bu_platform_contact pc, bu_mcht_platform_contact mpc where pc.del_flag = '0' and pc.status = '0' and pc.staff_id = "+platContactStaffId+" and mpc.del_flag = '0' and mpc.status = '1' and mpc.platform_contact_id = pc.id )");
		    return this;
		}

		/**
		 * @MethodName andMchtInfoStatusEqualTo
		 * @Description TODO
		 * @author chengh
		 * @date 2019年7月31日 下午5:49:12
		 */
		public Criteria andMchtInfoStatusEqualTo(String mchtInfoStatus) {
			// TODO Auto-generated method stub
			addCriterion(" EXISTS(select id from bu_mcht_info mi where mi.id = t.mcht_id and mi.status = '"+mchtInfoStatus+"' and mi.del_flag = '0')");
			 return this;
		}
	}	
}
