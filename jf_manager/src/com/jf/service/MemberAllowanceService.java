package com.jf.service;

import com.jf.dao.MemberAllowanceCustomMapper;
import com.jf.dao.MemberAllowanceMapper;
import com.jf.entity.MemberAllowance;
import com.jf.entity.MemberAllowanceCustom;
import com.jf.entity.MemberAllowanceExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class MemberAllowanceService  extends  BaseService<MemberAllowance, MemberAllowanceExample> {

    @Autowired
    private MemberAllowanceMapper memberAllowanceMapper;
    @Autowired
    private MemberAllowanceCustomMapper memberAllowanceCustomMapper;

    @Autowired
    public void setMemberAllowanceMapper(MemberAllowanceMapper memberAllowanceMapper) {
        super.setDao(memberAllowanceMapper);
        this.memberAllowanceMapper = memberAllowanceMapper;
    }


    public List<MemberAllowanceCustom> selectByCustomExample(MemberAllowanceExample memberAllowanceExample) {
        return memberAllowanceCustomMapper.selectByCustomExample(memberAllowanceExample);
    }
}
