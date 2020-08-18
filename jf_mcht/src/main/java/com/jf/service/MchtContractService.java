package com.jf.service;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Const;
import com.jf.common.ext.exception.BizException;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.MchtContractCustomMapper;
import com.jf.dao.MchtContractMapper;
import com.jf.entity.MchtContract;
import com.jf.entity.MchtContractCustomExample;
import com.jf.entity.MchtContractExample;
import com.jf.entity.MchtInfo;
import com.jf.entity.MchtProductBrand;
import com.jf.entity.MchtProductBrandExample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class MchtContractService extends BaseService<MchtContract, MchtContractExample> {

	@Autowired
	private MchtContractMapper dao;

	@Autowired
	private MchtInfoService mchtInfoService;

	@Autowired
	private MchtProductBrandService mchtProductBrandService;

	@Autowired
	private MchtContractCustomMapper mchtContractCustomMapper;
	@Autowired
	public void setMchtContractMapper(MchtContractMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}


	/**
	 * 合同提审
     */
	public MchtContract commit(int id) {
		MchtContract model = findById(id);
		model.setAuditStatus(Const.MCHT_CONTRACT_AUDIT_STATUS_WAIT);
		model.setUploadDate(new Date());
		return update(model);
	}

	/**
	 * 商家寄出合同相关附件
     */
	public MchtContract mchtSend(int id, int expressId, String expressNo) {
		MchtContract model = findById(id);
		model.setMchtExpressId(expressId);
		model.setMchtExpressNo(expressNo);
		model.setMchtSendDate(new Date());
		model.setIsMchtSend(Const.MCHT_CONTRACT_SEND_STATUS_YES);
		return update(model);
	}

	/**
	 * 获取商家合同
	 */
	public MchtContract findByMchtId(int mchtId) {
		QueryObject queryObject = new QueryObject();
		queryObject.addQuery("mchtId", mchtId);
		queryObject.addQuery("del_flag", Const.FLAG_FALSE);
		queryObject.addSort("create_date", QueryObject.SORT_DESC);
		queryObject.setLimitSize(1);
		List<MchtContract> list = list(queryObject);
		return list.size() > 0 ? list.get(0) : null;
	}


	public MchtContract findById(int id){
		return dao.selectByPrimaryKey(id);
	}

	public MchtContract save(MchtContract model){
		model.setCreateDate(new Date());
		dao.insertSelective(model);
		return model;
	}

	public MchtContract update(MchtContract model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKeySelective(model);
		return model;
	}

	public void delete(int id){
		MchtContract model = findById(id);
		if (model == null || model.getDelFlag().equals(Const.FLAG_TRUE)) {
			throw new BizException("ID为[" + id + "]的数据不存在");
		}
		model.setDelFlag(Const.FLAG_TRUE);
		update(model);
	}

	public int count(QueryObject queryObject) {
		return dao.countByExample(builderQuery(queryObject));
	}

	public List<MchtContract> list(QueryObject queryObject) {
		MchtContractExample example = builderQuery(queryObject);
		if(queryObject.getLimitSize() > 0){
			example.setLimitStart(0);
			example.setLimitSize(queryObject.getLimitSize());
		}
		return dao.selectByExample(example);
	}

	public Page<MchtContract> paginate(QueryObject queryObject) {
		MchtContractExample example = builderQuery(queryObject);
		example.setLimitStart(queryObject.getLimitStart());
		example.setLimitSize(queryObject.getPageSize());

		List<MchtContract> list = dao.selectByExample(example);
		int totalCount = dao.countByExample(example);
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

	private MchtContractExample builderQuery(QueryObject queryObject) {
		MchtContractExample example = new MchtContractExample();
		MchtContractExample.Criteria criteria = example.createCriteria();
		if(queryObject.getQuery("delFlag") != null){
			criteria.andDelFlagEqualTo(queryObject.getQueryToStr("delFlag"));
		}
		if(queryObject.getQuery("mchtId") != null){
			criteria.andMchtIdEqualTo(queryObject.getQueryToInt("mchtId"));
		}
		if(queryObject.getSort().size() > 0){
			example.setOrderByClause(queryObject.getSortString());
		}
		return example;
	}

	/**
	 * 获取商家资料归档情况
	 *
	 * @param mchtId
	 * @return
	 */
    public List<Map<String, Object>> getArchiveSituationData(Integer mchtId) {
		List<Map<String, Object>> resultList = new ArrayList<>();
		MchtContractExample mchtContractExample = new MchtContractExample();
		MchtContractExample.Criteria mchtCriteria = mchtContractExample.createCriteria();
		mchtCriteria.andMchtIdEqualTo(mchtId);
		mchtCriteria.andDelFlagEqualTo("0");
		mchtContractExample.setOrderByClause("id desc");
		mchtContractExample.setLimitStart(0);
		mchtContractExample.setLimitSize(1);
		List<MchtContract> mchtContracts = dao.selectByExample(mchtContractExample);
		String sendStatusDesc = "未寄出";
		if (!mchtContracts.isEmpty()) {
			MchtContract mchtContract = mchtContracts.get(0);
			Map<String, Object> lastContractMap = new HashMap<>();
			lastContractMap.put("proName", "最新合同");
			// type为页面展示类型 1:最新合同，2:公司资料 3:品牌名称
			lastContractMap.put("type", 1);
			// 合同寄件状态
			sendStatusDesc = mchtContract.getIsMchtSend() != null && "1".equals(mchtContract.getIsMchtSend())? "已寄出": sendStatusDesc;
			lastContractMap.put("sendStatusDesc", sendStatusDesc);
			// 合同归档状态
			String archiveStatusDesc = "未处理";
			if ("1".equals(mchtContract.getArchiveStatus())) {
				archiveStatusDesc = "通过已归档";
			} else if ("2".equals(mchtContract.getArchiveStatus())) {
				archiveStatusDesc = "不通过驳回";
			} else if ("4".equals(mchtContract.getArchiveStatus())) {
				archiveStatusDesc = "不签约";
			}
			lastContractMap.put("archiveStatusDesc", archiveStatusDesc);
			lastContractMap.put("contractId", mchtContract.getId());
			resultList.add(lastContractMap);
		}

		// 公司资料
		MchtInfo mchtInfo = mchtInfoService.selectByPrimaryKey(mchtId);
		if (mchtInfo != null) {
			Map<String, Object> companyMap = new HashMap<>();
			companyMap.put("proName", "公司资料");
			companyMap.put("type", 2);
			companyMap.put("sendStatusDesc", sendStatusDesc);
			String archiveStatusDesc = mchtInfo.getArchiveStatus() != null && "1".equals(mchtInfo.getArchiveStatus())? "已齐全": "未齐全";
			companyMap.put("archiveStatusDesc", archiveStatusDesc);
			companyMap.put("mchtId", mchtId);
			resultList.add(companyMap);
		}

		// 品牌
		MchtProductBrandExample mchtProductBrandExample = new MchtProductBrandExample();
		MchtProductBrandExample.Criteria brandCriteria = mchtProductBrandExample.createCriteria();
		brandCriteria.andMchtIdEqualTo(mchtId);
		brandCriteria.andStatusEqualTo("1");
		brandCriteria.andDelFlagEqualTo("0");
		mchtProductBrandExample.setOrderByClause("id desc");
		List<MchtProductBrand> mchtProductBrandList = mchtProductBrandService.selectByExample(mchtProductBrandExample);
		if (!mchtProductBrandList.isEmpty()) {
			for (MchtProductBrand mchtProductBrand: mchtProductBrandList) {
				if (mchtProductBrand.getProductBrandId() != null && mchtProductBrand.getProductBrandId() == 0) {
					continue;
				}
				Map<String, Object> productBrandMap = new HashMap<>();
				productBrandMap.put("proName", mchtProductBrand.getProductBrandName());
				productBrandMap.put("type", 3);
				// 通过品牌的归档状态判断是否已寄出  当状态为 1、2、3时表示已寄出 状态为0即为未寄出
				if ("1".equals(mchtProductBrand.getArchiveStatus()) || "2".equals(mchtProductBrand.getArchiveStatus()) || "3".equals(mchtProductBrand.getArchiveStatus())) {
					productBrandMap.put("sendStatusDesc", "已寄出");
				} else {
					productBrandMap.put("sendStatusDesc", "未寄出");
				}
				String archiveStatusDesc = mchtProductBrand.getArchiveStatus() != null && "3".equals(mchtProductBrand.getArchiveStatus())? "已齐全": "未齐全";
				productBrandMap.put("archiveStatusDesc", archiveStatusDesc);
				productBrandMap.put("brandId", mchtProductBrand.getId());
				resultList.add(productBrandMap);
			}
		}

		return resultList;
	}


	/**
	 * @MethodName selectByCustomExample
	 * @Description TODO
	 * @author chengh
	 * @date 2019年8月9日 下午4:49:37
	 */
	public List<MchtContract> selectByCustomExample(
			MchtContractCustomExample mchtContractCustomExample) {
		// TODO Auto-generated method stub
		return mchtContractCustomMapper.selectByCustomExample(mchtContractCustomExample);
	}
}
