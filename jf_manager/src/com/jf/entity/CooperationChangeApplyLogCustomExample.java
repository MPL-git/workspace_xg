package com.jf.entity;

public class CooperationChangeApplyLogCustomExample extends CooperationChangeApplyLogExample {

    @Override
    public CooperationChangeApplyLogCustomCriteria createCriteria(){
        CooperationChangeApplyLogCustomCriteria criteria =  new CooperationChangeApplyLogCustomCriteria();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }



    public class CooperationChangeApplyLogCustomCriteria extends CooperationChangeApplyLogExample.Criteria {

    }

}
