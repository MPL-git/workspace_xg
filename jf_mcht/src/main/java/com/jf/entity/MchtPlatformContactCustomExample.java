package com.jf.entity;


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

	
	public class MchtPlatformContactCustomCriteria extends MchtPlatformContactExample.Criteria{
		
		/**
		 * 
		 * @ClassName MchtPlatformContactCustomCriteria
		 * @Description TODO(联系人类型条件)
		 * @author pengl
		 * @date 2017年9月27日 下午5:09:13
		 */
		public Criteria andPlatformContactTypeIn(String contactTypes) {
			addCriterion(" EXISTS (SELECT pc.id  FROM bu_platform_contact pc where pc.id = platform_contact_id "
					+ "AND pc.del_flag = '0' AND pc.contact_type in (" + contactTypes + ") ) ");
			return this;
		}
		
		public Criteria andPlatformContactTypeInTwo(String contactTypes) {
			addCriterion(" EXISTS (SELECT pc.id  FROM bu_platform_contact pc where pc.id = platform_contact_id "
					+ "AND pc.del_flag = '0' AND pc.status = '0' AND pc.contact_type in (" + contactTypes + ") ) ");
			return this;
		}
		
		
		
	}
	
}