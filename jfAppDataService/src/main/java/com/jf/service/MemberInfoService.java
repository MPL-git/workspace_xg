package com.jf.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.MemberInfoMapper;
import com.jf.entity.MemberInfo;
import com.jf.entity.MemberInfoExample;

@Service
@Transactional
public class MemberInfoService extends BaseService<MemberInfo,MemberInfoExample,MemberInfoMapper> {

}
