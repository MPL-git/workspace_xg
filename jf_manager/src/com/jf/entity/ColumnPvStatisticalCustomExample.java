package com.jf.entity;

public class ColumnPvStatisticalCustomExample extends ColumnPvStatisticalExample {

    @Override
    public ColumnPvStatisticalCustomCriteria createCriteria() {
        ColumnPvStatisticalCustomCriteria criteria = new ColumnPvStatisticalCustomCriteria();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    public class ColumnPvStatisticalCustomCriteria extends ColumnPvStatisticalExample.Criteria{


    }

}