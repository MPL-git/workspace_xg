package com.jf.entity;

import com.jf.entity.PlatformContactExample.Criteria;


/**
 * 
 * @ClassName MchtPlatformContactCustomExample
 * @Description TODO(商家平台对接人表)
 * @author pengl
 * @date 2017年9月27日 下午5:07:18
 */
public class MchtPlatformContactCustomExample extends MchtPlatformContactExample {
	
	@Override
	public MchtPlatformContactCustomCriteria createCriteria() {
		MchtPlatformContactCustomCriteria criteria = new MchtPlatformContactCustomCriteria();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
	}

	/**
	 * 
	 * @ClassName MchtPlatformContactCustomCriteria
	 * @Description TODO(联系人类型条件)
	 * @author pengl
	 * @date 2017年9月27日 下午5:09:13
	 */
	public class MchtPlatformContactCustomCriteria extends MchtPlatformContactExample.Criteria{
		public Criteria andPlatformContactTypeEqualTo(String contactType) {
			addCriterion(" EXISTS (SELECT pc.id  FROM bu_platform_contact pc where pc.id = platform_contact_id "
					+ "AND pc.del_flag = '0' AND pc.contact_type = '" + contactType + "' ) ");
			return this;
		}
		
		public Criteria andPlatformContactTypeIn(String contactTypes) {
			addCriterion(" EXISTS (SELECT pc.id  FROM bu_platform_contact pc where pc.id = platform_contact_id "
					+ "AND pc.del_flag = '0' AND pc.contact_type in '(" + contactTypes + ")' ) ");
			return this;
		}
		
		public Criteria andMchtIdEqualTo(Integer mchtid) {
			addCriterion(" EXISTS (SELECT mcht_id FROM bu_platform_contact pc where pc.id =platform_contact_id and pc.contact_type='5' and pc.del_flag='0' and mcht_id="+mchtid+")");
			return this;
		}
		
		public Criteria andplatContactStaffId(Integer platContactStaffId) {//对接人的商家
			addCriterion(" EXISTS (select mcht_id from bu_platform_contact pc where pc.del_flag = '0' and pc.id=platform_contact_id and pc.staff_id = "+platContactStaffId+" )");
			return this;
		}
		
	}
	
}