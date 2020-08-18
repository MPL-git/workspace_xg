package com.jf.service;

import com.jf.dao.AndroidChannelGroupSetDtlCustomMapper;
import com.jf.dao.AndroidChannelGroupSetDtlMapper;
import com.jf.entity.AndroidChannelGroupSetDtl;
import com.jf.entity.AndroidChannelGroupSetDtlCustom;
import com.jf.entity.AndroidChannelGroupSetDtlCustomExample;
import com.jf.entity.AndroidChannelGroupSetDtlExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Pengl
 * @create 2019-10-18 下午 2:05
 */

@Service
@Transactional
public class AndroidChannelGroupSetDtlService extends BaseService<AndroidChannelGroupSetDtl, AndroidChannelGroupSetDtlExample> {

    @Autowired
    private AndroidChannelGroupSetDtlMapper androidChannelGroupSetDtlMapper;

    @Autowired
    private AndroidChannelGroupSetDtlCustomMapper androidChannelGroupSetDtlCustomMapper;

    @Autowired
    public void setAndroidChannelGroupSetDtlMapper(AndroidChannelGroupSetDtlMapper androidChannelGroupSetDtlMapper) {
        super.setDao(androidChannelGroupSetDtlMapper);
        this.androidChannelGroupSetDtlMapper = androidChannelGroupSetDtlMapper;
    }

    public int countByCustomExample(AndroidChannelGroupSetDtlCustomExample example) {
        return androidChannelGroupSetDtlCustomMapper.countByCustomExample(example);
    }

    public List<AndroidChannelGroupSetDtlCustom> selectByCustomExample(AndroidChannelGroupSetDtlCustomExample example) {
        return androidChannelGroupSetDtlCustomMapper.selectByCustomExample(example);
    }

    public AndroidChannelGroupSetDtlCustom selectByCustomPrimaryKey(Integer id) {
        return androidChannelGroupSetDtlCustomMapper.selectByCustomPrimaryKey(id);
    }

    public int updateByCustomExampleSelective(@Param("record") AndroidChannelGroupSetDtl record, @Param("example") AndroidChannelGroupSetDtlCustomExample example) {
        return androidChannelGroupSetDtlCustomMapper.updateByCustomExampleSelective(record, example);
    }


}
