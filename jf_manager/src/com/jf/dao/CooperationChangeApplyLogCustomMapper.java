package com.jf.dao;


import com.jf.entity.CooperationChangeApplyLogCustom;
import com.jf.entity.CooperationChangeApplyLogCustomExample;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CooperationChangeApplyLogCustomMapper{


    int countByCustomExample(CooperationChangeApplyLogCustomExample example);

    List<CooperationChangeApplyLogCustom> selectByCustomExample(CooperationChangeApplyLogCustomExample example);
}