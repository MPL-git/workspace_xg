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

	public class MchtContractCustomCriteria extends Criteria{

		/**
		 * @MethodName andTotalStatusEqualTo
		 * @Description TODO
		 * @author chengh
		 * @date 2019年8月19日 下午5:16:38
		 */
		public Criteria andTotalStatusEqualTo() {
			// TODO Auto-generated method stub
			addCriterion(" (a.renew_pro_status in('0','6') or a.renew_status = '0')");
			return this;
		}

	}
}