package com.jf.entity;



public class SysStaffInfoCustomExample extends SysStaffInfoExample {

	@Override
    public SysStaffInfoCustomCriteria createCriteria() {
		SysStaffInfoCustomCriteria criteria = new SysStaffInfoCustomCriteria();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }
	
	public class SysStaffInfoCustomCriteria extends SysStaffInfoExample.Criteria{
		
		/**
		 * 
		 * @Title andGetSubordinateStaffIds   
		 * @Description TODO(对接人的下拉选项：（我可查看的人员，及他们的下级人员 比如：我可查看小李，而小李可以查看小王和小陈）)   
		 * @author pengl
		 * @date 2018年10月18日 上午8:53:50
		 */
		public Criteria andGetSubordinateStaffIds(String staffID) {
			addCriterion(" (FIND_IN_SET(t.STAFF_ID, (select GROUP_CONCAT(ss.SUBORDINATE_STAFF_IDS) from sys_staff_info ss where FIND_IN_SET(ss.STAFF_ID, (select si.SUBORDINATE_STAFF_IDS from sys_staff_info si where si.STAFF_ID = " + staffID + ")) ))"
					+ " or FIND_IN_SET(t.STAFF_ID, (select si.SUBORDINATE_STAFF_IDS from sys_staff_info si where si.STAFF_ID = " + staffID + ")) )");
			return this;
		}
		
	}
	
}
