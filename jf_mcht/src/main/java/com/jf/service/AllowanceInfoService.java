package com.jf.service;

import com.jf.common.ext.query.QueryObject;
import com.jf.dao.AllowanceInfoMapper;
import com.jf.entity.AllowanceInfo;
import com.jf.entity.AllowanceInfoExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AllowanceInfoService {
    @Autowired
    private AllowanceInfoMapper dao;

    public AllowanceInfo findByActivityAreaId(int activityAreaId){
        QueryObject queryObject = new QueryObject(1,1);
        queryObject.addQuery("activityAreaId", activityAreaId);
        List<AllowanceInfo> list = list(queryObject);
        return list.size() > 0 ? list.get(0) : null;
    }


    public List<AllowanceInfo> list(QueryObject queryObject) {

        return dao.selectByExample(builderQuery(queryObject));
    }

    private AllowanceInfoExample builderQuery(QueryObject queryObject) {
        AllowanceInfoExample example = new AllowanceInfoExample();
        AllowanceInfoExample.Criteria criteria = example.createCriteria();

        if(queryObject.getQuery("id") != null){
            criteria.andIdEqualTo(queryObject.getQueryToInt("id"));
        }
        if(queryObject.getQuery("activityAreaId") != null){
            criteria.andActivityAreaIdEqualTo(queryObject.getQueryToInt("activityAreaId"));
        }
        if(queryObject.getSort().size() > 0){
            example.setOrderByClause(queryObject.getSortString());
        }
        return example;
    }

}
