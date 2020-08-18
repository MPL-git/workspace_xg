package com.jf.dao;

import com.jf.entity.CooperationChangeUploadPic;
import org.springframework.stereotype.Repository;

@Repository
public interface CooperationChangeUploadPicCustomMapper  {

    int updateByCooperationChangeApplyIdSelective(CooperationChangeUploadPic record);
}
