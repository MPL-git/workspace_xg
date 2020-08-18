package com.jf.service;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Const;
import com.jf.dao.ShopProductCustomTypeCustomMapper;
import com.jf.dao.ShopProductCustomTypeMapper;
import com.jf.entity.ShopProductCustomType;
import com.jf.entity.ShopProductCustomTypeCustom;
import com.jf.entity.ShopProductCustomTypeExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;

@Service
@Transactional
public class ShopProductCustomTypeService extends BaseService<ShopProductCustomType,ShopProductCustomTypeExample> {
	@Autowired
	private ShopProductCustomTypeMapper dao;
	
	@Autowired
	private ShopProductCustomTypeCustomMapper shopProductCustomTypeCustomMapper;
	
	@Autowired
	public void setShopProductCustomType(ShopProductCustomTypeMapper shopProductCustomTypeMapper) {
		super.setDao(shopProductCustomTypeMapper);
		this.dao = shopProductCustomTypeMapper;
	}

	public List<ShopProductCustomTypeCustom> selectCustomByExample(ShopProductCustomTypeExample e) {
		return shopProductCustomTypeCustomMapper.selectByExample(e);
	}

	public int getMaxSeqNo(Integer mchtId) {
		return shopProductCustomTypeCustomMapper.getMaxSeqNo(mchtId);
	}

	public void saveSeqNo(ShopProductCustomType shopProductCustomType,int seqNo) {
		int maxSeqNo = shopProductCustomTypeCustomMapper.getMaxSeqNo(shopProductCustomType.getMchtId());
		int oldSeqNo=shopProductCustomType.getSeqNo();
		if(maxSeqNo>=seqNo){
			HashMap<String,Object> map = new HashMap<String,Object>();
			map.put("mchtId", shopProductCustomType.getMchtId());
			map.put("seqNo", seqNo);
			map.put("oldSeqNo", oldSeqNo);
			if(oldSeqNo<seqNo){
				shopProductCustomTypeCustomMapper.batchExecute(map);
				shopProductCustomType.setSeqNo(seqNo);
			}else{
				shopProductCustomTypeCustomMapper.batchExecute2(map);
				shopProductCustomType.setSeqNo(seqNo);
			}
		}else{
			HashMap<String,Object> map = new HashMap<String,Object>();
			map.put("mchtId", shopProductCustomType.getMchtId());
			map.put("oldSeqNo", oldSeqNo);
			shopProductCustomTypeCustomMapper.batchExecute3(map);
			shopProductCustomType.setSeqNo(maxSeqNo);
		}
		this.updateByPrimaryKeySelective(shopProductCustomType);
	}

	public List<ShopProductCustomType> findByMchtId(int mechtId) {
		ShopProductCustomTypeExample example = new ShopProductCustomTypeExample();
		example.createCriteria().andDelFlagEqualTo(Const.FLAG_FALSE)
				.andMchtIdEqualTo(mechtId)
				.andStatusEqualTo("1"); //显示状态
		return selectByExample(example);
	}
}
