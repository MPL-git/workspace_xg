package com.jf.entity;

/**
 * @author Pengl
 * @create 2019-09-29 下午 6:20
 */
public class SvipPracticeRecordCustomExample extends SvipPracticeRecordExample {

    @Override
    public SvipPracticeRecordCustomExample.SvipPracticeRecordCustomCriteria createCriteria() {
        SvipPracticeRecordCustomExample.SvipPracticeRecordCustomCriteria criteria = new SvipPracticeRecordCustomCriteria();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    public class SvipPracticeRecordCustomCriteria extends SvipPracticeRecordExample.Criteria{



    }

}
