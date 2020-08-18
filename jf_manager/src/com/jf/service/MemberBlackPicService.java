package com.jf.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.MemberBlackPicMapper;
import com.jf.entity.MemberBlackPic;
import com.jf.entity.MemberBlackPicExample;

@Service
@Transactional
public class MemberBlackPicService extends BaseService<MemberBlackPic, MemberBlackPicExample> {
	@Autowired
	private MemberBlackPicMapper memberBlackPicMapper;
	
	@Autowired
	public void setMapper(MemberBlackPicMapper memberBlackPicMapper) {
		super.setDao(memberBlackPicMapper);
		this.memberBlackPicMapper = memberBlackPicMapper;
	}
	
	/**
	 * 保存图片 先删后增
	 * 
	 * @param pics
	 * @param memberId
	 */
	public void save(String pics, Integer memberId, Integer staffId) {
		MemberBlackPicExample memberBlackPicExample = new MemberBlackPicExample();
		MemberBlackPicExample.Criteria criteria = memberBlackPicExample.createCriteria();
		criteria.andMemberIdEqualTo(memberId);
		criteria.andDelFlagEqualTo("0");
		MemberBlackPic deleteMemberBlackPic = new MemberBlackPic();
		deleteMemberBlackPic.setDelFlag("1");
		memberBlackPicMapper.updateByExampleSelective(deleteMemberBlackPic, memberBlackPicExample);
		
		String[] picArr = pics.split(",");
		// 判断是否多图上传
		if (picArr.length > 0) {
			for (int i = 0; i < picArr.length; i++) {
				MemberBlackPic memberBlackPic = new MemberBlackPic();
				memberBlackPic.setMemberId(memberId);
				memberBlackPic.setPic(picArr[i]);
				memberBlackPic.setCreateBy(staffId);
				memberBlackPic.setCreateDate(new Date());
				memberBlackPic.setDelFlag("0");
				memberBlackPicMapper.insert(memberBlackPic);
			}
		} else {
			MemberBlackPic memberBlackPic = new MemberBlackPic();
			memberBlackPic.setMemberId(memberId);
			memberBlackPic.setPic(pics);
			memberBlackPic.setCreateBy(staffId);
			memberBlackPic.setCreateDate(new Date());
			memberBlackPic.setDelFlag("0");
			memberBlackPicMapper.insert(memberBlackPic);
		}
	}
	
}
