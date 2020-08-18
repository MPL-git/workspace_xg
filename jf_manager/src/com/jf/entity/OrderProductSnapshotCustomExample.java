package com.jf.entity;


public class OrderProductSnapshotCustomExample extends OrderProductSnapshotExample {

	@Override
    public OrderProductSnapshotCustomCriteria createCriteria() {
		OrderProductSnapshotCustomCriteria criteria = new OrderProductSnapshotCustomCriteria();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }
	
	public class OrderProductSnapshotCustomCriteria extends OrderProductSnapshotExample.Criteria {
		
		
		
	}
	
}
