package com.jf.entity;

/**
 * @author Pengl
 * @create 2020-03-16 下午 2:51
 */
public class DesignTaskOrderCustomExample extends DesignTaskOrderExample {

	@Override
	public DesignTaskOrderCustomExample.DesignTaskOrderCustomCriteria createCriteria() {
		DesignTaskOrderCustomExample.DesignTaskOrderCustomCriteria criteria = new DesignTaskOrderCustomExample.DesignTaskOrderCustomCriteria();
		if (oredCriteria.size() == 0) {
			oredCriteria.add(criteria);
		}
		return criteria;
	}

	public class DesignTaskOrderCustomCriteria extends DesignTaskOrderExample.Criteria{
		public Criteria andPayStatusCustomEqualTo(String payStatus) {
			if("1".equals(payStatus)) {
				addCriterion(" t.pay_status = '1' and t.id not in(select a.design_task_order_id from bu_design_task_refund_order a where a.del_flag = '0')");
			}else if("2".equals(payStatus)) {
				addCriterion(" t.id in(select a.design_task_order_id from bu_design_task_refund_order a where a.del_flag = '0' and a.status != '2' and a.status != '3')");
			}else if("3".equals(payStatus)) {
				addCriterion(" t.id in(select a.design_task_order_id from bu_design_task_refund_order a where a.del_flag = '0' and a.status = '2')");
			}else if("4".equals(payStatus)) {
				addCriterion(" t.id in(select a.design_task_order_id from bu_design_task_refund_order a where a.del_flag = '0' and a.status = '3')");
			}
			return this;
		}

	}

}
