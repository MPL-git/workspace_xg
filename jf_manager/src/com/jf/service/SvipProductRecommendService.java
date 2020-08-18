package com.jf.service;

import com.jf.dao.SvipProductRecommendCustomMapper;
import com.jf.dao.SvipProductRecommendMapper;
import com.jf.entity.SvipProductRecommend;
import com.jf.entity.SvipProductRecommendCustom;
import com.jf.entity.SvipProductRecommendCustomExample;
import com.jf.entity.SvipProductRecommendExample;
import com.jf.vo.SvipProductAutomaticGrab;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

@Service
@Transactional
public class SvipProductRecommendService extends BaseService<SvipProductRecommend, SvipProductRecommendExample> {
	
	@Autowired
	private SvipProductRecommendCustomMapper svipProductRecommendCustomMapper;
	
	@Autowired
	private SvipProductRecommendMapper svipProductRecommendMapper;
	
	@Autowired
	private ProductService productService;

	@Autowired
	private ProductTypeService productTypeService;
	
	@Autowired
	public void setSvipProductRecommendMapper(SvipProductRecommendMapper svipProductRecommendMapper) {
		super.setDao(svipProductRecommendMapper);
		this.svipProductRecommendMapper = svipProductRecommendMapper;
	}
	
	public int countSvipProductRecommendCustomByExample(SvipProductRecommendCustomExample example) {
		return svipProductRecommendCustomMapper.countSvipProductRecommendCustomByExample(example);
	}

	public ArrayList<SvipProductRecommendCustom> selectSvipProductRecommendCustomByExample(SvipProductRecommendCustomExample example) {
		return svipProductRecommendCustomMapper.selectSvipProductRecommendCustomByExample(example);
	}

	public ArrayList<SvipProductRecommendCustom> selectAutomaticGrabList(List<Integer> ids) {
		return svipProductRecommendCustomMapper.selectAutomaticGrabList(ids);
	}

	public ArrayList<Integer> getAutomaticGrabProductIds() {
		ArrayList<Integer> resList = new ArrayList<>();
		//3个月内销量前500有SVIP折扣的商品,销量升序排列，再根据优惠力度降序排列
		List<SvipProductAutomaticGrab> svipProductAutomaticGrabs = svipProductRecommendCustomMapper.getAutomaticGrabProductIds();
		//筛选一级品类销量前20个商品
		HashMap<Integer, List<SvipProductAutomaticGrab>> productTypeMap = new HashMap<>();
		Iterator<SvipProductAutomaticGrab> iterator = svipProductAutomaticGrabs.iterator();
		while (iterator.hasNext()) {
			SvipProductAutomaticGrab svipProductAutomaticGrab = iterator.next();
			if(productTypeMap.containsKey(svipProductAutomaticGrab.getProductType1Id())){
				List<SvipProductAutomaticGrab> idList = productTypeMap.get(svipProductAutomaticGrab.getProductType1Id());
				if (idList.size() < 20) {
					idList.add(svipProductAutomaticGrab);
					iterator.remove();
				}
			}else {
				ArrayList<SvipProductAutomaticGrab> idList = new ArrayList<>();
				idList.add(svipProductAutomaticGrab);
				productTypeMap.put(svipProductAutomaticGrab.getProductType1Id(), idList);
				iterator.remove();
			}
		}
		//品类销量前20个商品根据销量降序排列，再根据优惠力度降序排列
		ArrayList<SvipProductAutomaticGrab> sortByProductTypeList = new ArrayList<>();
		for (List<SvipProductAutomaticGrab> idList : productTypeMap.values()) {
			sortByProductTypeList.addAll(idList);
		}
		Collections.sort(sortByProductTypeList, new Comparator<SvipProductAutomaticGrab>() {
			@Override
			public int compare(SvipProductAutomaticGrab o1, SvipProductAutomaticGrab o2) {
				Integer s1 = o1.getSalesVolume();
				Integer s2 = o2.getSalesVolume();
				if(s1.equals(s2)){
					return o2.getDiscountAmount().compareTo(o1.getDiscountAmount());
				}
				return s2-s1;
			}
		});
		for (SvipProductAutomaticGrab svipProductAutomaticGrab : sortByProductTypeList) {
			resList.add(svipProductAutomaticGrab.getProductId());
		}
		//剩下的320个商品中筛选出折SVIP价格和活动价较大的前120个商品按权重排序
		if(CollectionUtils.isEmpty(svipProductAutomaticGrabs)){
			return resList;
		}
		Collections.sort(svipProductAutomaticGrabs, new Comparator<SvipProductAutomaticGrab>() {
			@Override
			public int compare(SvipProductAutomaticGrab o1, SvipProductAutomaticGrab o2) {
				return o2.getDiscountAmount().compareTo(o1.getDiscountAmount());
			}
		});
		List<SvipProductAutomaticGrab> sortByProductList;
		if(svipProductAutomaticGrabs.size() >= 120){
			sortByProductList = svipProductAutomaticGrabs.subList(0, 120);
		}else {
			sortByProductList = svipProductAutomaticGrabs.subList(0, svipProductAutomaticGrabs.size());
		}
		Collections.sort(sortByProductList, new Comparator<SvipProductAutomaticGrab>() {
			@Override
			public int compare(SvipProductAutomaticGrab o1, SvipProductAutomaticGrab o2) {
				return o2.getSaleWeight()-o1.getSaleWeight();
			}
		});
		for (SvipProductAutomaticGrab svipProductAutomaticGrab : sortByProductList) {
			resList.add(svipProductAutomaticGrab.getProductId());
		}
		return resList;
	}
	
	public void updateSeqNoIsNull(Integer id,Integer staffID,String date) {
		svipProductRecommendCustomMapper.updateSeqNoIsNull(id,staffID,date);
	}
	
	public ArrayList<HashMap<String,Object>> selectProductByCodeList(HashSet<String> productCodesSet){
		return svipProductRecommendCustomMapper.selectProductByCodeList(productCodesSet);
	}
	
	//保存手动新增
	public void saveManuallyAdd(ArrayList<Integer> productIds,Integer staffID) {
		SvipProductRecommendExample svipProductRecommendExample = new SvipProductRecommendExample();
		svipProductRecommendExample.createCriteria().andDelFlagEqualTo("0");
		List<SvipProductRecommend> SvipProductRecommends = selectByExample(svipProductRecommendExample);

		ArrayList<Integer> rejectList = new ArrayList<Integer>();
		for (Integer productId : productIds) {
			for (SvipProductRecommend svipProductRecommend : SvipProductRecommends) {
				if(productId.equals(svipProductRecommend.getProductId())){
					rejectList.add(productId);
					break;
				}
			}
		}
		for (Integer rejectId : rejectList) {
			productIds.remove(rejectId);
		}
		if(productIds.size()!=0){
			SvipProductRecommend svipProductRecommend = new SvipProductRecommend();
			svipProductRecommend.setSource("1");
			svipProductRecommend.setCreateBy(staffID);
			svipProductRecommend.setCreateDate(new Date());
			svipProductRecommend.setDelFlag("0");
			svipProductRecommendCustomMapper.batchInsertSvipProductRecommend(productIds, svipProductRecommend);
		}
		
	}
	
	//保存自动抓取
	public void saveAutomaticGrab(ArrayList<Integer> productIdsList,Integer staffID) {
		ArrayList<Integer> automaticGrabProductIds = this.getAutomaticGrabProductIds();
		//得到手动插入数据
		SvipProductRecommendExample svipProductRecommendExample2 = new SvipProductRecommendExample();
		svipProductRecommendExample2.createCriteria().andDelFlagEqualTo("0").andSourceEqualTo("1");
		List<SvipProductRecommend> svipProductRecommendList = this.selectByExample(svipProductRecommendExample2);
		//手动插入数据加入删除数据集合
		for (SvipProductRecommend svipProductRecommend : svipProductRecommendList) {
			productIdsList.add(svipProductRecommend.getProductId());
		}
		//自动插入中存在数据集合中的数据时,删除该数据
		for(int i = productIdsList.size() - 1; i >= 0; i--){
	        Integer item = productIdsList.get(i);
	        for(int i1 = automaticGrabProductIds.size() - 1; i1 >= 0; i1--){
		        if(item.equals(automaticGrabProductIds.get(i1))){
					automaticGrabProductIds.remove(automaticGrabProductIds.get(i1));
		        	break;
		        }
		    }
	    }
		if(automaticGrabProductIds.size()!=0){
			//插入前删除表中类型为自动抓取的旧数据
			SvipProductRecommendExample svipProductRecommendExample = new SvipProductRecommendExample();
			svipProductRecommendExample.createCriteria().andDelFlagEqualTo("0").andSourceEqualTo("0");
			//this.deleteByExample(svipProductRecommendExample);
			SvipProductRecommend updateSvipProductRecommend = new SvipProductRecommend();
			updateSvipProductRecommend.setDelFlag("1");
			updateByExampleSelective(updateSvipProductRecommend, svipProductRecommendExample);
			//批量插入自动抓取的的数据
			SvipProductRecommend svipProductRecommend = new SvipProductRecommend();
			svipProductRecommend.setSource("0");
			svipProductRecommend.setCreateBy(staffID);
			svipProductRecommend.setCreateDate(new Date());
			svipProductRecommend.setDelFlag("0");
			svipProductRecommendCustomMapper.batchInsertSvipProductRecommend(automaticGrabProductIds, svipProductRecommend);
		}
	}
	
}
