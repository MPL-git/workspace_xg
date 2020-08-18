package com.jf.service;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Const;
import com.jf.common.ext.exception.BizException;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.common.ext.util.StrKit;
import com.jf.common.utils.SecurityUtil;
import com.jf.dao.MchtUserMapper;
import com.jf.entity.MchtUser;
import com.jf.entity.MchtUserExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class MchtUserService extends BaseService<MchtUser,MchtUserExample> {
	@Autowired
	private MchtUserMapper dao;
	@Autowired
	public void setMchtUserMapper(MchtUserMapper mchtUserMapper) {
		super.setDao(mchtUserMapper);
		this.dao = mchtUserMapper;
	}
	
	public void bindMobile(Integer userId,String mobile,boolean isValidate){
		MchtUser mchtUser4Update=new MchtUser();
		mchtUser4Update.setId(userId);
		mchtUser4Update.setMobile(mobile);
		if(isValidate){
			mchtUser4Update.setIsMobileValidate("1");
		}else{
			mchtUser4Update.setIsMobileValidate("0");
		}

		dao.updateByPrimaryKeySelective(mchtUser4Update);
	}


	/**
	 * 保存子账号
	 *
	 * @param operator 操作人
	 * @param mchtUser	账号信息
     * @return
     */
	public MchtUser save(int operator, MchtUser mchtUser) {
		// 操作人
		MchtUser operatorUser = findById(operator);
		// 主账号
		MchtUser primaryUser = findPrimaryByMchtId(operatorUser.getMchtId());

		boolean isUpdate = mchtUser.getId() != null && mchtUser.getId() > 0;
		// 子账号用户名
		String userCode = primaryUser.getUserCode() + "_" + mchtUser.getUserCode();
		MchtUser model;
		if(isUpdate){
			model = findById(mchtUser.getId());
			if(model == null || !model.getMchtId().equals(operatorUser.getMchtId())){
				throw new BizException("找不到id为" + mchtUser.getId() + "的用户信息");
			}
			if(model.getIsPrimary().equals(Const.FLAG_TRUE)){
				throw new BizException("不能修改主账号的信息");
			}
			// 判断用户名有没有重复
			if(!model.getUserCode().equals(userCode) && findByUserCode(userCode) != null){
				throw new BizException("该用户名已被注册，请重新注册");
			}
			if(StrKit.notBlank(mchtUser.getPassword())){
				model.setPassword(SecurityUtil.md5Encode(mchtUser.getPassword()));
			}
		}else{
			// 判断用户名有没有重复
			if(findByUserCode(userCode) != null){
				throw new BizException("该用户名已被注册，请重新注册");
			}
			model = new MchtUser();
			model.setPassword(SecurityUtil.md5Encode(mchtUser.getPassword()));
		}

		model.setUserCode(userCode);
		model.setUserName(mchtUser.getUserName());
		model.setMobile(mchtUser.getMobile());

		if(isUpdate){
			if(mchtUser.getStatus() != null){
				model.setStatus(mchtUser.getStatus());
			}else{
				model.setStatus("0");
			}
			model.setUpdateBy(operatorUser.getId());
			update(model);
		}else{
			model.setMchtId(operatorUser.getMchtId());
			model.setIsPrimary(Const.FLAG_FALSE);
			model.setStatus("0");
			model.setCreateBy(operatorUser.getId());
			save(model);
		}

		return model;
	}

	public MchtUser modifyPassword(int id, String oldPassword, String newPassword){
		oldPassword = SecurityUtil.md5Encode(oldPassword);
		MchtUser model = findById(id);
		if(!oldPassword.toLowerCase().equals(model.getPassword())){
			throw new BizException("旧密码错误");
		}

		model.setPassword(SecurityUtil.md5Encode(newPassword));
		model.setUpdateBy(id);
		update(model);
		return model;
	}

	public MchtUser findById(int id){
		return dao.selectByPrimaryKey(id);
	}

	public MchtUser findByUserCode(String userCode){
		QueryObject queryObject = new QueryObject(1, 1);
		queryObject.addQuery("userCode", userCode);
		List<MchtUser> list = findList(queryObject);
		return list.size() > 0 ? list.get(0) : null;
	}

	public MchtUser findPrimaryByMchtId(Integer mchtId) {
		QueryObject queryObject = new QueryObject(1, 1);
		queryObject.addQuery("mchtId", mchtId);
		queryObject.addQuery("isPrimary", Const.FLAG_TRUE);
		List<MchtUser> list = findList(queryObject);
		return list.size() > 0 ? list.get(0) : null;
	}

	public MchtUser save(MchtUser model){
		model.setCreateDate(new Date());
		dao.insertSelective(model);
		return model;
	}

	public MchtUser update(MchtUser model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKeySelective(model);
		return model;
	}

	public void delete(int id){
		MchtUser model = findById(id);
		if (model == null || model.getDelFlag().equals(Const.FLAG_TRUE)) {
			throw new BizException("ID为[" + id + "]的数据不存在");
		}
		model.setDelFlag(Const.FLAG_TRUE);
		update(model);
	}

	public int count(QueryObject queryObject) {
		return dao.countByExample(builderQuery(queryObject));
	}

	public List<MchtUser> findList(QueryObject queryObject) {
		return dao.selectByExample(builderQuery(queryObject));
	}

	public Page<MchtUser> paginate(QueryObject queryObject) {
		MchtUserExample example = builderQuery(queryObject);
		example.setLimitStart(queryObject.getLimitStart());
		example.setLimitSize(queryObject.getPageSize());

		List<MchtUser> list = dao.selectByExample(example);
		int totalCount = dao.countByExample(example);
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

	private MchtUserExample builderQuery(QueryObject queryObject) {
		MchtUserExample example = new MchtUserExample();
		MchtUserExample.Criteria criteria = example.createCriteria();
		criteria.andDelFlagEqualTo(Const.FLAG_FALSE);
		if(queryObject.getQuery("id") != null){
			criteria.andIdEqualTo(queryObject.getQueryToInt("id"));
		}
		if(queryObject.getQuery("ids") != null){
			List<Integer> ids = queryObject.getQuery("ids");
			criteria.andIdIn(ids);
		}
		if(queryObject.getQuery("mchtId") != null){
			criteria.andMchtIdEqualTo(queryObject.getQueryToInt("mchtId"));
		}
		if(queryObject.getQuery("status") != null){
			criteria.andStatusEqualTo(queryObject.getQueryToStr("status"));
		}
		if(queryObject.getQuery("isPrimary") != null){
			criteria.andIsPrimaryEqualTo(queryObject.getQueryToStr("isPrimary"));
		}
		if(queryObject.getQuery("userCode") != null){
			criteria.andUserCodeEqualTo(queryObject.getQueryToStr("userCode"));
		}
		if(queryObject.getQuery("userName") != null){
			criteria.andUserNameLike("%" + queryObject.getQueryToStr("userName") + "%");
		}
		if(queryObject.getSort().size() > 0){
			example.setOrderByClause(queryObject.getSortString());
		}
		return example;
	}

}
