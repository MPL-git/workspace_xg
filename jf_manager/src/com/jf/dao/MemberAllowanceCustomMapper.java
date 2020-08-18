package com.jf.dao;

import com.jf.entity.MemberAllowanceCustom;
import com.jf.entity.MemberAllowanceExample;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberAllowanceCustomMapper {
    public List<MemberAllowanceCustom> selectByCustomExample(MemberAllowanceExample memberAllowanceExample);
}
