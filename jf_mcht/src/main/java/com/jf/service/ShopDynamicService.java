package com.jf.service;

import com.jf.common.base.BaseService;
import com.jf.dao.MchtShopDynamicCustomMapper;
import com.jf.dao.MchtShopDynamicMapper;
import com.jf.entity.MchtShopDynamic;
import com.jf.entity.MchtShopDynamicCustom;
import com.jf.entity.MchtShopDynamicCustomExample;
import com.jf.entity.MchtShopDynamicExample;
import com.jf.entity.MemberDynamic;
import com.jf.entity.MemberDynamicExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;


@Service("shopDynamicService")
@Transactional
public class ShopDynamicService extends BaseService<MchtShopDynamic,MchtShopDynamicExample> {
	@Autowired
	private MchtShopDynamicMapper dao;
	
	@Autowired
	private MchtShopDynamicCustomMapper mchtShopDynamicCustomMapper;
	
	@Resource
	private MemberDynamicService memberDynamicService;

	@Autowired
	public void setMchtShopDynamicMapper(MchtShopDynamicMapper mchtShopDynamicMapper) {
		super.setDao(mchtShopDynamicMapper);
		this.dao = mchtShopDynamicMapper;
	}
	
	public void deleteDynamicById(HttpServletRequest request,Integer updateBy){
		String id = request.getParameter("id");
		MchtShopDynamic mchtShopDynamic = new MchtShopDynamic();
		mchtShopDynamic.setUpdateDate(new Date());
		mchtShopDynamic.setUpdateBy(updateBy);
		mchtShopDynamic.setDelFlag("1");//删除
		mchtShopDynamic.setId(Integer.parseInt(id));
		updateByPrimaryKeySelective(mchtShopDynamic);
		
		MemberDynamic memberDynamic2 = new MemberDynamic();
		memberDynamic2.setUpdateBy(updateBy);
		memberDynamic2.setUpdateDate(new Date());
		memberDynamic2.setDelFlag("1");
		MemberDynamicExample memberDynamicExample = new MemberDynamicExample();
		memberDynamicExample.createCriteria().andMchtShopDynamicIdEqualTo(Integer.parseInt(id));
		memberDynamicService.updateByExampleSelective(memberDynamic2,memberDynamicExample);
		
	}
	
	public void setProductIdsNull(Integer id){
		 mchtShopDynamicCustomMapper.setProductIdsNull(id);
	}
	
	public int countShopDynamicCustomByExample(MchtShopDynamicCustomExample example){
		return mchtShopDynamicCustomMapper.countByExample(example);
	}
	
	public List<MchtShopDynamicCustom> selectShopDynamicCustomByExample(MchtShopDynamicCustomExample example){
		return mchtShopDynamicCustomMapper.selectByExample(example);
	}

}
