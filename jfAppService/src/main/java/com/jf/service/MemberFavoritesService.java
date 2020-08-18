package com.jf.service;

import com.jf.common.base.BaseService;
import com.jf.dao.MemberFavoritesMapper;
import com.jf.entity.MemberFavorites;
import com.jf.entity.MemberFavoritesExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
  * @author  chenwf: 
  * @date 创建时间：2017年5月12日 下午3:05:55 
  * @version 1.0 
  * @parameter  
  * @return  
*/
@Service
@Transactional
public class MemberFavoritesService extends BaseService<MemberFavorites, MemberFavoritesExample> {
	
	@Autowired
	private MemberFavoritesMapper memberFavoritesMapper;

	@Autowired
	public void setMemberFavoritesMapper(MemberFavoritesMapper memberFavoritesMapper) {
		this.setDao(memberFavoritesMapper);
		this.memberFavoritesMapper = memberFavoritesMapper;
	}
	
}
