package com.jf.service.video;

import com.jf.common.AppBaseService;
import com.jf.common.constant.StateConst;
import com.jf.dao.VideoColumnMapper;
import com.jf.entity.VideoColumn;
import com.jf.entity.VideoColumnExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author luoyb
 * Created on 2019/9/7
 */
@Service
public class VideoColumnService extends AppBaseService<VideoColumn, VideoColumnExample> {

    @Autowired
    private VideoColumnMapper videoColumnMapper;

    @Autowired
    public void setMapper() {
        super.setDao(videoColumnMapper);
    }

    public List<VideoColumn> findAll() {
        VideoColumnExample example = new VideoColumnExample();
        example.createCriteria().andStatusEqualTo(StateConst.ONLINE).andDelFlagEqualTo(StateConst.FALSE);
        example.setOrderByClause("ifnull(seq_no,99999), create_date");
        return selectByExample(example);
    }
}
