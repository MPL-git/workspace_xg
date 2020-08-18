package com.jf.dao;

import com.jf.entity.SvipPracticeRecord;
import com.jf.entity.SvipPracticeRecordCustom;
import com.jf.entity.SvipPracticeRecordCustomExample;
import com.jf.entity.SvipPracticeRecordExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Pengl
 * @create 2019-09-29 下午 6:18
 */
@Repository
public interface SvipPracticeRecordCustomMapper {

    public int countByCustomExample(SvipPracticeRecordExample example);

    public List<SvipPracticeRecordCustom> selectByCustomExample(SvipPracticeRecordCustomExample example);

    public SvipPracticeRecordCustom selectByCustomPrimaryKey(Integer id);

    public int updateByCustomExampleSelective(@Param("record") SvipPracticeRecord record, @Param("example") SvipPracticeRecordCustomExample example);

}
