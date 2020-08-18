package com.jf.service;

import com.jf.common.base.ArgException;
import com.jf.common.base.BaseService;
import com.jf.common.base.ResponseMsg;
import com.jf.common.constant.Const;
import com.jf.common.ext.query.QueryObject;
import com.jf.common.utils.StringUtil;
import com.jf.dao.SingleProductActivityCnfMapper;
import com.jf.entity.MemberAddress;
import com.jf.entity.MemberAddressExample;
import com.jf.entity.MemberInfo;
import com.jf.entity.MemberInfoExample;
import com.jf.entity.ProductCustom;
import com.jf.entity.SingleProductActivity;
import com.jf.entity.SingleProductActivityCnf;
import com.jf.entity.SingleProductActivityCnfExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
@Transactional
public class SingleProductActivityCnfService extends BaseService<SingleProductActivityCnf, SingleProductActivityCnfExample> {
	
	@Autowired
	private SingleProductActivityCnfMapper singleProductActivityCnfMapper;
	@Autowired
	private SingleProductActivityService singleProductActivityService;
	@Autowired
	private ProductService productService;
	@Autowired
	private MemberInfoService memberInfoService;
	@Autowired
	private MemberAddressService memberAddressService;
	@Autowired
	public void setSingleProductActivityCnfMapper(SingleProductActivityCnfMapper singleProductActivityCnfMapper) {
		this.setDao(singleProductActivityCnfMapper);
		this.singleProductActivityCnfMapper = singleProductActivityCnfMapper;
	}
	public void updateByModel(SingleProductActivityCnf singleProductActivityCnf) {
		
		singleProductActivityCnfMapper.updateByPrimaryKeySelective(singleProductActivityCnf);
	}
	public List<SingleProductActivityCnf> findListQuery(QueryObject queryObject) {
		
		return singleProductActivityCnfMapper.selectByExample(builderQuery(queryObject));
	}
	private SingleProductActivityCnfExample builderQuery(QueryObject queryObject) {
		SingleProductActivityCnfExample example = new SingleProductActivityCnfExample();
		SingleProductActivityCnfExample.Criteria criteria = example.createCriteria();
		criteria.andDelFlagEqualTo(Const.FLAG_FALSE);
		if(queryObject.getQuery("activityType") != null){
			criteria.andActivityTypeEqualTo(queryObject.getQueryToStr("activityType"));
		}
		if(queryObject.getQuery("sort") != null){
			example.setOrderByClause(queryObject.getQueryToStr("sort"));
		}
		return example;
	}
	
	public void getSingleActivityLimitBuy(SingleProductActivity singleProductActivity, Integer singleProductActivityId,
										  Integer quantity, Integer productId, Integer memberId, Integer version, String system) {
		Map<String, Object> map = singleActivityLimitBuy(singleProductActivity, singleProductActivityId, quantity, productId, memberId);
		if(!(boolean) map.get("success")){
			if((system.equals(Const.ANDROID) && version >= 47) || (system.equals(Const.IOS) && version >= 50)){
				throw new ArgException(map.get("errorMsg").toString(),ResponseMsg.ERROR_4005);
			}else{
				throw new ArgException(map.get("errorMsg").toString());
			}
		}
	}
	
	/**单品限购判断
	 * @return 
	 */
	public Map<String, Object> singleActivityLimitBuy(SingleProductActivity singleProductActivity,Integer singleProductActivityId,Integer quantity,Integer productId,Integer memberId){
		boolean success = true;
		String errorMsg = "";
		if(quantity > 0){
			if(singleProductActivityId != null){
				singleProductActivity = singleProductActivityService.selectByPrimaryKey(singleProductActivityId);
			}
			String activityType = singleProductActivity.getType();
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("memberId", memberId);
			params.put("productId", productId);
			//获取添加购物车的件数
			ProductCustom productCustom = productService.getUserBuyCount(params,activityType);
			//单品活动限购控制
			SingleProductActivityCnfExample singleProductActivityCnfExample=new SingleProductActivityCnfExample();
			singleProductActivityCnfExample.createCriteria().andDelFlagEqualTo("0").andActivityTypeEqualTo(activityType);
			List<SingleProductActivityCnf> singleProductActivityCnfs = selectByExample(singleProductActivityCnfExample);
			if(singleProductActivityCnfs!=null&&singleProductActivityCnfs.size()>0){
				SingleProductActivityCnf singleProductActivityCnf=singleProductActivityCnfs.get(0);
				if(singleProductActivityCnf.getLimitBuy()!=null&&singleProductActivityCnf.getLimitBuy()>0){
					int activityLimitBuy=singleProductActivityCnf.getLimitBuy();
					//获取用户在此类型活动已下订单（除了取消状态，关闭外的所有订单）的数量
					if(activityLimitBuy>0){
						Map<String, Object> p = new HashMap<String, Object>();
						//查出用户id（包括同一设备号的用户）
						MemberInfo member=memberInfoService.selectByPrimaryKey(memberId);
						List<Integer> memberIds=new ArrayList<Integer>();
						memberIds.add(member.getId());
						String regImei=member.getReqImei();
						if(regImei!=null&&!"00000000-0000-0000-0000-000000000000".equals(regImei)&&!"".equals(regImei)&&!"unknown".equals(regImei)){
							MemberInfoExample memberInfoExample=new MemberInfoExample();
							memberInfoExample.createCriteria().andDelFlagEqualTo("0").andReqImeiEqualTo(regImei);
							List<MemberInfo> memberInfos=memberInfoService.selectByExample(memberInfoExample);
							if(memberInfos!=null&&memberInfos.size()>0){
								for(MemberInfo m:memberInfos){
									memberIds.add(m.getId());
								}
							}
						}
						
						
						//收货地址中手机号跟此会员绑定的手机号相同的会员id
						if(!StringUtil.isEmpty(member.getMobile())){
							MemberAddressExample memberAddressExample=new MemberAddressExample();
							memberAddressExample.createCriteria().andDelFlagEqualTo("0").andPhoneEqualTo(member.getMobile());
							List<MemberAddress> memberAddressList=memberAddressService.selectByExample(memberAddressExample);
							for(MemberAddress memberAddress:memberAddressList){
								memberIds.add(memberAddress.getMemberId());
							}
						}
						
						
						//查出收货地址中有相同手机号的用户id
						MemberAddressExample memberAddressExample=new MemberAddressExample();
						memberAddressExample.createCriteria().andDelFlagEqualTo("0").andMemberIdEqualTo(memberId);
						List<MemberAddress> memberAddressList=memberAddressService.selectByExample(memberAddressExample);
						if(memberAddressList!=null&&memberAddressList.size()>0){
							List<String> mobiles=new ArrayList<String>();
							for(MemberAddress memberAddress:memberAddressList){
								mobiles.add(memberAddress.getPhone());
							}
							
							memberAddressExample=new MemberAddressExample();
							memberAddressExample.createCriteria().andDelFlagEqualTo("0").andPhoneIn(mobiles);
							memberAddressList=memberAddressService.selectByExample(memberAddressExample);
							for(MemberAddress memberAddress:memberAddressList){
								memberIds.add(memberAddress.getMemberId());
							}
						}

						p.put("memberIds", memberIds);
						p.put("activityType", activityType);
						if(singleProductActivityCnf.getLimitTime()!=null&&singleProductActivityCnf.getLimitTime()>0){
					    	Calendar calendar = Calendar.getInstance();
							calendar.add(Calendar.DATE, -singleProductActivityCnf.getLimitTime());
							p.put("limitDate", calendar.getTime());
						}
						int userHasBuyCount=productService.getUserBuyCountBySingleActivityType(p);
						int userShoppingCartCount=productService.getUserShoppingCartCountBySingleActivityType(p);
						
						if(quantity+userHasBuyCount+userShoppingCartCount>activityLimitBuy){
							Integer limitb = activityLimitBuy;
							if(productCustom.getLimitBuy() > 0 && activityLimitBuy > productCustom.getLimitBuy()){
								limitb = productCustom.getLimitBuy();
							}
							success = false;
							errorMsg = "每人限购"+limitb+"件";
							if(Const.PRODUCT_ACTIVITY_TYPE_NEWUSERSECKILL.equals(activityType)){
								errorMsg = "《新人秒杀专区》每人限购一件，不可重复购买哦~";
							}
						}
					}
				}
			}
		}
		Map<String, Object> map = new HashMap<>();
		map.put("success", success);
		map.put("errorMsg", errorMsg);
		return map;
	}
}
