package com.jf.service;

import com.jf.dao.CooperationChangeApplyLogCustomMapper;
import com.jf.dao.CooperationChangeApplyLogMapper;
import com.jf.entity.CooperationChangeApplyLog;
import com.jf.entity.CooperationChangeApplyLogExample;
import com.jf.entity.CooperationChangeApplyLogCustom;
import com.jf.entity.CooperationChangeApplyLogCustomExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CooperationChangeApplyLogService extends BaseService<CooperationChangeApplyLog,CooperationChangeApplyLogExample> {

    @Autowired
    private CooperationChangeApplyLogMapper cooperationChangeApplyLogMapper;

    @Autowired
    private CooperationChangeApplyLogCustomMapper cooperationChangeApplyLogCustomMapper;

    @Autowired
    public void setCooperationChangeApplyLogMapper(CooperationChangeApplyLogMapper cooperationChangeApplyLogMapper){
        super.setDao(cooperationChangeApplyLogMapper);
        this.cooperationChangeApplyLogMapper = cooperationChangeApplyLogMapper;
    }

    public List<CooperationChangeApplyLogCustom> selectByCustomExample(CooperationChangeApplyLogCustomExample example){
        return cooperationChangeApplyLogCustomMapper.selectByCustomExample(example);
    }


}
