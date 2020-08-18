package com.jf.service;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Const;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.CooperationChangeUploadPicMapper;
import com.jf.entity.CooperationChangeUploadPic;
import com.jf.entity.CooperationChangeUploadPicExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CooperationChangeUploadPicService extends BaseService<CooperationChangeUploadPic, CooperationChangeUploadPicExample> {
    @Autowired
    private CooperationChangeUploadPicMapper dao;

    @Autowired
    public void setCooperationChangeUploadPicMapper(CooperationChangeUploadPicMapper cooperationChangeUploadPicMapper){
        super.setDao(cooperationChangeUploadPicMapper);
        this.dao = cooperationChangeUploadPicMapper;
    }

    public CooperationChangeUploadPic findMainByCooperationChangeApplyId(Integer cooperationChangeApplyId){
        QueryObject queryObject = new QueryObject(1, 1);
        queryObject.addQuery("cooperationChangeApplyId",cooperationChangeApplyId);
        queryObject.addQuery("is_default",QueryObject.SORT_DESC);
        List<CooperationChangeUploadPic> list = list(queryObject);
        return list.size() > 0 ? list.get(0) : null;
    }

    public List<CooperationChangeUploadPic> list(QueryObject queryObject){
        return dao.selectByExample(builderQuery(queryObject));
    }

    private CooperationChangeUploadPicExample builderQuery(QueryObject queryObject){
        CooperationChangeUploadPicExample example = new CooperationChangeUploadPicExample();
        CooperationChangeUploadPicExample.Criteria criteria = example.createCriteria();
        criteria.andDelFlagEqualTo(Const.FLAG_FALSE);
        if (queryObject.getQuery("cooperationChangeApplyId") != null){
            criteria.andCooperationChangeApplyIdEqualTo(queryObject.getQueryToInt("cooperationChangeApplyId"));
        }
        if (queryObject.getSort().size() > 0){
            example.setOrderByClause(queryObject.getSortString());
        }
        return example;
    }
}
