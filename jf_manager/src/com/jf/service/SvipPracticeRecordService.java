package com.jf.service;

import com.jf.dao.SvipPracticeRecordCustomMapper;
import com.jf.dao.SvipPracticeRecordMapper;
import com.jf.entity.SvipPracticeRecord;
import com.jf.entity.SvipPracticeRecordCustom;
import com.jf.entity.SvipPracticeRecordCustomExample;
import com.jf.entity.SvipPracticeRecordExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Pengl
 * @create 2019-09-29 下午 6:23
 */
@Service
@Transactional
public class SvipPracticeRecordService extends BaseService<SvipPracticeRecord, SvipPracticeRecordExample> {

    @Autowired
    private SvipPracticeRecordMapper svipPracticeRecordMapper;

    @Autowired
    private SvipPracticeRecordCustomMapper svipPracticeRecordCustomMapper;

    @Autowired
    public void setSvipPracticeRecordMapper(SvipPracticeRecordMapper svipPracticeRecordMapper) {
        super.setDao(svipPracticeRecordMapper);
        this.svipPracticeRecordMapper = svipPracticeRecordMapper;
    }

    public int countByCustomExample(SvipPracticeRecordExample example) {
        return svipPracticeRecordCustomMapper.countByCustomExample(example);
    }

    public List<SvipPracticeRecordCustom> selectByCustomExample(SvipPracticeRecordCustomExample example) {
        return svipPracticeRecordCustomMapper.selectByCustomExample(example);
    }

    public SvipPracticeRecordCustom selectByCustomPrimaryKey(Integer id) {
        return svipPracticeRecordCustomMapper.selectByCustomPrimaryKey(id);
    }

    public int updateByCustomExampleSelective(@Param("record") SvipPracticeRecord record, @Param("example") SvipPracticeRecordCustomExample example) {
        return svipPracticeRecordCustomMapper.updateByCustomExampleSelective(record, example);
    }


}
