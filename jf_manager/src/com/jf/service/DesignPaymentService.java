package com.jf.service;

import com.jf.dao.DesignTaskOrderCustomMapper;
import com.jf.dao.DesignTaskOrderMapper;
import com.jf.dao.DesignTaskRefundOrderCustomMapper;
import com.jf.entity.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;

@Service
@Transactional
public class DesignPaymentService extends BaseService<DesignTaskOrder, DesignTaskOrderExample> {

    @Autowired
    private DesignTaskOrderMapper designTaskOrderMapper;

    @Autowired
    private DesignTaskOrderCustomMapper designTaskOrderCustomMapper;

    @Autowired
    private DesignTaskRefundOrderCustomMapper designTaskRefundOrderCustomMapper;

    @Autowired
    public void setDesignTaskOrderMapper(DesignTaskOrderMapper designTaskOrderMapper) {
        super.setDao(designTaskOrderMapper);
        this.designTaskOrderMapper = designTaskOrderMapper;
    }

    public List<DesignTaskOrderCustom> selectByCustomExample(DesignTaskOrderExample example){
        return designTaskOrderCustomMapper.selectByCustomExample(example);
    }

    public DesignTaskOrder selectByCustomPrimaryKey(Integer id) {

        return designTaskOrderCustomMapper.selectByCustomPrimaryKey(id);
    }

    public int countByCustomExample(DesignTaskOrderExample example){
        return designTaskOrderCustomMapper.countByCustomExample(example);
    }

    public int updateByCustomExampleSelective(@Param("record") DesignTaskOrder record, @Param("example") DesignTaskOrderCustomExample example) {
        return designTaskOrderCustomMapper.updateByCustomExampleSelective(record, example);
    }


    public DesignTaskOrderCustom totalPaymentByCustomExample(DesignTaskOrderExample example){
        return designTaskOrderCustomMapper.totalPaymentByCustomExample(example);
    }

    public List<DesignTaskOrderCustom> designRefundOrderStatistics(HashMap<String, Object> paramMap) {
        return designTaskOrderCustomMapper.designRefundOrderStatistics(paramMap);
    }

    public List<DesignTaskOrderCustom> designReceivablesStatistics(HashMap<String, Object> paramMap) {
        return designTaskOrderCustomMapper.designReceivablesStatistics(paramMap);
    }
    //设计退款

    public List<DesignTaskRefundOrderCustom> selectRefByCustomExample(DesignTaskRefundOrderCustomExample example){
        return designTaskRefundOrderCustomMapper.selectRefByCustomExample(example);
    }

    public int designTaskRefundOrderCountByExample(DesignTaskRefundOrderCustomExample example){
        return designTaskRefundOrderCustomMapper.designTaskRefundOrderCountByExample(example);
    }

    public DesignTaskRefundOrderCustom totalRefundByCustomExample(DesignTaskRefundOrderCustomExample example){
        return designTaskRefundOrderCustomMapper.totalRefundByCustomExample(example);
    }
}
