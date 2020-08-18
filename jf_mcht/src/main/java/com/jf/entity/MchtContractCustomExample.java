package com.jf.entity;


public class MchtContractCustomExample extends MchtContractExample{
	@Override
    public MchtContractCustomCriteria createCriteria() {
		MchtContractCustomCriteria criteria = new MchtContractCustomCriteria();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }
	
	public class MchtContractCustomCriteria extends MchtContractExample.Criteria{

		/**
		 * @MethodName andRenewProStatusOrRenewStatus
		 * @Description TODO
		 * @author chengh
		 * @date 2019年8月9日 下午4:42:26
		 */
		public Criteria andRenewProStatusOrRenewStatus() {
			// TODO Auto-generated method stub
			addCriterion(" (renew_pro_status in('0','6') or renew_status = '0')");
		    return this;
		}

		/**
		 * @MethodName andRenewProStatusOrRenewStatusStop
		 * @Description TODO
		 * @author chengh
		 * @date 2019年8月12日 下午4:49:05
		 */
		public Criteria andRenewProStatusOrRenewStatusStop() {
			// TODO Auto-generated method stub
			addCriterion(" (renew_pro_status = '5' or renew_status = '0')");
		    return this;
		}		
	}
}