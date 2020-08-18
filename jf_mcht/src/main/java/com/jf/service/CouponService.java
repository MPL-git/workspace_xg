package com.jf.service;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Const;
import com.jf.common.ext.exception.BizException;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.CouponCustomMapper;
import com.jf.dao.CouponMapper;
import com.jf.dao.MchtInfoMapper;
import com.jf.entity.Coupon;
import com.jf.entity.CouponCustom;
import com.jf.entity.CouponExample;
import com.jf.entity.MchtInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class CouponService extends BaseService<Coupon,CouponExample> {

	@Autowired
	private CouponMapper dao;
	
	@Autowired
	private CouponCustomMapper couponCustomMapper;

	@Autowired
	private MchtInfoMapper mchtInfoMapper;
	
	@Autowired
	public void setCouponMapper(CouponMapper couponMapper) {
		super.setDao(couponMapper);
		this.dao = couponMapper;
	}


	public Coupon save(int mchtId,String mchtType,int activityAreaId, String name, BigDecimal money, BigDecimal minimum, Integer grantQuantity){
		MchtInfo mchtInfo = mchtInfoMapper.selectByPrimaryKey(mchtId);

		Coupon model = new Coupon();
		model.setName(name);
		model.setActivityAreaId(activityAreaId);
		model.setMoney(money);
		model.setMinimum(minimum);
		model.setGrantQuantity(grantQuantity);

		model.setRang(Const.PREFERENTIAL_RANG_BRAND);
		// 自营spop的belong归属于平台，其它都归于商家
		if("1".equals(mchtInfo.getIsManageSelf())){
			model.setBelong(Const.PREFERENTIAL_BELONG_PLAT);
		}else{
			model.setBelong(Const.PREFERENTIAL_BELONG_MCHT);
		}

		model.setStatus("0");	//启用状态(0未启用 1启用 2停用)
		model.setRecType("1");	//领取方式(1免费 2金币兑换)
		model.setRecLimitType("1");	//限领类型(1每人每天限领1张 2每人限领指定数量 3不限)
		model.setRecEach(1);		//限领数量
		model.setExpiryType("1");	//有效期类型（1绝对时间 2相对时间）
		model.setMinMemberGroup(1);	//最小会员组
		model.setPreferentialType("1");//1.固定面额
		model.setMchtId(mchtId);//商家ID
		return save(model);
	}

	public Coupon findById(int id){
		return dao.selectByPrimaryKey(id);
	}

	public Coupon save(Coupon model){
		model.setCreateDate(new Date());
		dao.insertSelective(model);
		return model;
	}

	public Coupon update(Coupon model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		Coupon model = findById(id);
		if (model == null || model.getDelFlag().equals(Const.FLAG_TRUE)) {
			throw new BizException("ID为[" + id + "]的数据不存在");
		}
		model.setDelFlag(Const.FLAG_TRUE);
		update(model);
	}

	public void deleteByActivityAreaId(int activityAreaId){
		List<Coupon> list = listByActivityAreaId(activityAreaId);
		for(Coupon coupon : list){
			delete(coupon.getId());
		}
	}

	public int count(QueryObject queryObject) {
		return dao.countByExample(builderQuery(queryObject));
	}

	public List<Coupon> list(QueryObject queryObject) {
		return dao.selectByExample(builderQuery(queryObject));
	}

	public List<Coupon> listByActivityAreaId(int activityAreaId){
		QueryObject queryObject = new QueryObject(1,1);
		queryObject.addQuery("activityAreaId", activityAreaId);
		queryObject.addQuery("delFlag", Const.FLAG_FALSE);
		return list(queryObject);
	}

	public Page<Coupon> paginate(QueryObject queryObject) {
		CouponExample example = builderQuery(queryObject);
		example.setLimitStart(queryObject.getLimitStart());
		example.setLimitSize(queryObject.getPageSize());

		List<Coupon> list = dao.selectByExample(example);
		int totalCount = dao.countByExample(example);
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

	private CouponExample builderQuery(QueryObject queryObject) {
		CouponExample example = new CouponExample();
		CouponExample.Criteria criteria = example.createCriteria();
		if(queryObject.getQuery("delFlag") != null){
			criteria.andDelFlagEqualTo(queryObject.getQueryToStr("delFlag"));
		}
		if(queryObject.getQuery("id") != null){
			criteria.andIdEqualTo(queryObject.getQueryToInt("id"));
		}
		if(queryObject.getQuery("activityAreaId") != null){
			criteria.andActivityAreaIdEqualTo(queryObject.getQueryToInt("activityAreaId"));
		}
		if(queryObject.getQuery("idsIn") != null){
			List<Integer> ids = queryObject.getQuery("idsIn");
			criteria.andIdIn(ids);
		}
		if(queryObject.getSort().size() > 0){
			example.setOrderByClause(queryObject.getSortString());
		}
		return example;
	}
	
	
	public void saveCoupon(Coupon coupon){
		if(coupon.getId()!=null){
			this.updateByPrimaryKeySelective(coupon);
		}else{
			this.insertSelective(coupon);
		}
	}
	
	public List<CouponCustom> selectCouponCustomByExample(CouponExample example){
		return couponCustomMapper.selectCouponCustomByExample(example);
	};
	
	public 	int countCouponCustomByExample(CouponExample example){
		return couponCustomMapper.countCouponCustomByExample(example);
	};
	
	public int updateByPrimaryKeySelective(Coupon record){
		return dao.updateByPrimaryKeySelective(record);
	}
}
