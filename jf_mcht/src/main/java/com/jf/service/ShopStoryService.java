package com.jf.service;

import java.util.Date;
import java.util.List;

import javax.json.Json;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.common.base.BaseService;
import com.jf.common.utils.StringUtil;
import com.jf.dao.ShopStoryDetailMapper;
import com.jf.dao.ShopStoryMapper;
import com.jf.entity.ShopStory;
import com.jf.entity.ShopStoryDetail;
import com.jf.entity.ShopStoryDetailExample;
import com.jf.entity.ShopStoryDetailExample.Criteria;
import com.jf.entity.ShopStoryExample;


@Service
@Transactional
public class ShopStoryService extends BaseService<ShopStory, ShopStoryExample> {
	

	@Autowired
	private ShopStoryMapper shopStoryMapper;
	
	@Autowired
	private ShopStoryDetailMapper shopStoryDetailMapper;
	
	
	@Autowired
	public void setShopStoryMapper(ShopStoryMapper shopStoryMapper) {
		super.setDao(shopStoryMapper);
		this.shopStoryMapper = shopStoryMapper;
	}
	
	
	public void updateWordAndPic(Integer mchtId,String shopStoryId,String storyBrief,String picArrayJsonStr){
		ShopStory shopStory = shopStoryMapper.selectByPrimaryKey(Integer.parseInt(shopStoryId));
		if(!StringUtil.isEmpty(storyBrief)){
			shopStory.setStoryIntroduction(storyBrief);//故事简介
		}
		shopStory.setUpdateBy(mchtId);
		shopStory.setUpdateDate(new Date());
		shopStoryMapper.updateByPrimaryKeySelective(shopStory);//修改故事表
		
		//批量更新故事详情表
		ShopStoryDetailExample shopStoryDetailExample = new ShopStoryDetailExample();
		shopStoryDetailExample.createCriteria().andDelFlagEqualTo("0").andShopStoryIdEqualTo(Integer.parseInt(shopStoryId)).andMchtIdEqualTo(mchtId);
		ShopStoryDetail ssd4Update = new ShopStoryDetail();
		ssd4Update.setDelFlag("1");
		shopStoryDetailMapper.updateByExampleSelective(ssd4Update, shopStoryDetailExample);
		
		if(!StringUtil.isEmpty(picArrayJsonStr)){//修改故事详情表
			JSONArray picArrayList = JSONArray.fromObject(picArrayJsonStr);
			for (int i = 0; i < picArrayList.size(); i++) {
				JSONObject picArray = (JSONObject)picArrayList.get(i);
				String word = picArray.getString("word");
				String picPath = picArray.getString("picPath");
				String picUrl = picArray.getString("picUrl");
				ShopStoryDetail shopStoryDetail4Update = new ShopStoryDetail();
				shopStoryDetail4Update.setDelFlag("0");
				int wordCount = -1;
				int picCount = -1;
				
				if(!StringUtil.isEmpty(picPath)){//div为图片模块
				shopStoryDetail4Update.setPic(picPath);
				if(!StringUtil.isEmpty(picUrl)){
					shopStoryDetail4Update.setPicUrl(picUrl);
				}
				shopStoryDetail4Update.setUpdateBy(mchtId);
				shopStoryDetail4Update.setUpdateDate(new Date());
				ShopStoryDetailExample  shopStoryDetailPicExample= new ShopStoryDetailExample();
				shopStoryDetailPicExample.createCriteria().andShopStoryIdEqualTo(Integer.parseInt(shopStoryId)).andPicEqualTo(picPath);
				picCount = shopStoryDetailMapper.updateByExampleSelective(shopStoryDetail4Update, shopStoryDetailPicExample);
				}
				
				//都不存在
				if(wordCount>0||picCount>0){
					continue;
				}
				
				ShopStoryDetail ssd = new ShopStoryDetail();//插入新增加的
				if(!StringUtil.isEmpty(word)){
					ssd.setType("0");
					ssd.setContent(word);//文字描述
				}
				if(!StringUtil.isEmpty(picPath)){
					ssd.setType("1");
					ssd.setPic(picPath);//图片				
				}
				if(!StringUtil.isEmpty(picUrl)){
					ssd.setType("1");
					ssd.setPicUrl(picUrl);//图片路径
				}
				ssd.setCreateBy(mchtId);
				ssd.setCreateDate(new Date());
				ssd.setDelFlag("0");
				ssd.setMchtId(mchtId);
				ssd.setShopStoryId(shopStory.getId());
				shopStoryDetailMapper.insertSelective(ssd);
			}
		}
		
		
		
	}
	
	
	public void insertWordAndPic(Integer mchtId, String storyBrief,String picArrayJsonStr){
		ShopStory ss = new ShopStory();//故事表
		if(!StringUtil.isEmpty(storyBrief)){
			ss.setStoryIntroduction(storyBrief);//故事简介
		}
		ss.setCreateBy(mchtId);
		ss.setCreateDate(new Date());
		ss.setDelFlag("0");
		ss.setMchtId(mchtId);
		ss.setStoryContent("暂不存");//暂时不存
		shopStoryMapper.insertSelective(ss);
		
		if(!StringUtil.isEmpty(picArrayJsonStr)){
			JSONArray picArrayList = JSONArray.fromObject(picArrayJsonStr);
			for (int i = 0; i < picArrayList.size(); i++) {
				ShopStoryDetail ssd = new ShopStoryDetail();//故事详情表
				JSONObject picArray = (JSONObject)picArrayList.get(i);
				String word = picArray.getString("word");
				String picPath = picArray.getString("picPath");
				String picUrl = picArray.getString("picUrl");
				if(!StringUtil.isEmpty(word)){
					ssd.setType("0");
					ssd.setContent(word);//文字描述
				}
				if(!StringUtil.isEmpty(picPath)){
					ssd.setType("1");
					ssd.setPic(picPath);//图片				
				}
				if(!StringUtil.isEmpty(picUrl)){
					ssd.setType("1");
					ssd.setPicUrl(picUrl);//图片路径
				}
				ssd.setCreateBy(mchtId);
				ssd.setCreateDate(new Date());
				ssd.setDelFlag("0");
				ssd.setMchtId(mchtId);
				ssd.setShopStoryId(ss.getId());
				shopStoryDetailMapper.insertSelective(ssd);
				
			}
		}
		
		
	}

}
