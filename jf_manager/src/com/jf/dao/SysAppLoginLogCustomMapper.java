package com.jf.dao;

import com.jf.entity.SysAppLoginLogExample;
import org.springframework.stereotype.Repository;

@Repository
public interface SysAppLoginLogCustomMapper {

    int countByExampleGroupByMemberId(SysAppLoginLogExample example);
    
}