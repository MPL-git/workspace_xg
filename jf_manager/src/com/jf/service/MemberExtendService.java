package com.jf.service;

import com.jf.dao.MemberExtendCustomMapper;
import com.jf.dao.MemberExtendMapper;
import com.jf.entity.MemberExtend;
import com.jf.entity.MemberExtendCustom;
import com.jf.entity.MemberExtendCustomExample;
import com.jf.entity.MemberExtendExample;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MemberExtendService extends BaseService<MemberExtend, MemberExtendExample>
{

  @Autowired
  private MemberExtendMapper memberExtendMapper;

  @Autowired
  private MemberExtendCustomMapper memberExtendCustomMapper;

  @Autowired
  public void setMemberCouponMapper(MemberExtendMapper memberExtendMapper){
    super.setDao(memberExtendMapper);
    this.memberExtendMapper = memberExtendMapper;
  }

  public List<MemberExtendCustom> selectByCustomExample(MemberExtendCustomExample example){
    return this.memberExtendCustomMapper.selectByCustomExample(example);
  }
  
  public  int countByCustomExample(MemberExtendCustomExample example){
	  return this.memberExtendCustomMapper.countByCustomExample(example);
  };
  
}