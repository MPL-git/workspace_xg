package com.jf.dao;

import com.jf.entity.MemberExtendCustom;
import com.jf.entity.MemberExtendCustomExample;
import com.jf.entity.MemberExtendExample;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public abstract interface MemberExtendCustomMapper
{
  public abstract int countByCustomExample(MemberExtendCustomExample memberExtendCustomExample);

  public abstract List<MemberExtendCustom> selectByCustomExample(MemberExtendCustomExample memberExtendCustomExample);
}