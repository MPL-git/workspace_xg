package com.jf.service;

import com.jf.common.base.ArgException;
import com.jf.common.base.BaseService;
import com.jf.common.constant.Const;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.MchtTaxInvoiceInfoCustomMapper;
import com.jf.dao.MchtTaxInvoiceInfoMapper;
import com.jf.entity.MchtTaxInvoiceInfo;
import com.jf.entity.MchtTaxInvoiceInfoCustom;
import com.jf.entity.MchtTaxInvoiceInfoExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class MchtTaxInvoiceInfoService extends BaseService<MchtTaxInvoiceInfo,MchtTaxInvoiceInfoExample> {
	@Autowired
	private MchtTaxInvoiceInfoMapper mchtTaxInvoiceInfoMapper;
	
	@Resource
	private MchtTaxInvoiceInfoCustomMapper mchtTaxInvoiceInfoCustomMapper;
	
	@Autowired
	public void setMchtTaxInvoiceInfoMapper(MchtTaxInvoiceInfoMapper mchtTaxInvoiceInfoMapper) {
		super.setDao(mchtTaxInvoiceInfoMapper);
		this.mchtTaxInvoiceInfoMapper = mchtTaxInvoiceInfoMapper;
	}
	
	public int countMchtTaxInvoiceInfoCustomByExample(MchtTaxInvoiceInfoExample example){
    	return mchtTaxInvoiceInfoCustomMapper.countByExample(example);
    	
    }
	public List<MchtTaxInvoiceInfoCustom> selectMchtTaxInvoiceInfoCustomByExample(MchtTaxInvoiceInfoExample example){
    	return mchtTaxInvoiceInfoCustomMapper.selectByExample(example);
    }
	public MchtTaxInvoiceInfoCustom selectMchtTaxInvoiceInfoCustomByPrimaryKey(Integer id){
    	return mchtTaxInvoiceInfoCustomMapper.selectByPrimaryKey(id);
    }
	
	
	/**
	 * 公司信息保存暂不提审
	 * @param mchtInfo
	 * @throws ArgException
	 */
	public void mchtTaxInvoiceInfoCommitSave(MchtTaxInvoiceInfo mchtTaxInvoiceInfo) throws ArgException{
		if(mchtTaxInvoiceInfo.getId()!=null){
			MchtTaxInvoiceInfo oldMchtTaxInvoiceInfo=mchtTaxInvoiceInfoMapper.selectByPrimaryKey(mchtTaxInvoiceInfo.getId());
			if(oldMchtTaxInvoiceInfo.getAuditStatus().equals("1")||oldMchtTaxInvoiceInfo.getAuditStatus().equals("2")||oldMchtTaxInvoiceInfo.getAuditStatus().equals("3")){
				throw new ArgException("信息审核中或已审核通过，不可修改");
			}
			mchtTaxInvoiceInfo.setAuditStatus("0");
			mchtTaxInvoiceInfoMapper.updateByPrimaryKeySelective(mchtTaxInvoiceInfo);
		}else{
			mchtTaxInvoiceInfo.setCreateDate(mchtTaxInvoiceInfo.getCreateDate());
			mchtTaxInvoiceInfo.setCreateBy(mchtTaxInvoiceInfo.getCreateBy());
			mchtTaxInvoiceInfo.setAuditStatus("0");
			mchtTaxInvoiceInfoMapper.insertSelective(mchtTaxInvoiceInfo);
		}

	}
	
	/**
	 * 公司信息保存，提审
	 * @param mchtInfo
	 * @throws ArgException
	 */
	public void mchtTaxInvoiceInfoCommitAudit(MchtTaxInvoiceInfo mchtTaxInvoiceInfo) throws ArgException{
		MchtTaxInvoiceInfo oldMchtTaxInvoiceInfo=mchtTaxInvoiceInfoMapper.selectByPrimaryKey(mchtTaxInvoiceInfo.getId());
		if(oldMchtTaxInvoiceInfo.getAuditStatus().equals("2")){
			throw new ArgException("您的信息已在审核中，不能重新提交！");
		}
		if(oldMchtTaxInvoiceInfo.getAuditStatus().equals("3")){
			throw new ArgException("您的信息审核通过，无需重新提交。");
		}
		mchtTaxInvoiceInfo.setAuditStatus("1");
		mchtTaxInvoiceInfoMapper.updateByPrimaryKeySelective(mchtTaxInvoiceInfo);
	}





	public MchtTaxInvoiceInfo findByMchtId(int mchtId){
		QueryObject queryObject = new QueryObject();
		queryObject.addQuery("mchtId", mchtId);
		List<MchtTaxInvoiceInfo> list = list(queryObject);
		return list.size() > 0 ? list.get(0) : null;
	}

	public List<MchtTaxInvoiceInfo> list(QueryObject queryObject) {
		MchtTaxInvoiceInfoExample example = builderQuery(queryObject);
		if(queryObject.getLimitSize() > 0){
			example.setLimitStart(0);
			example.setLimitSize(queryObject.getLimitSize());
		}
		return dao.selectByExample(example);
	}

	private MchtTaxInvoiceInfoExample builderQuery(QueryObject queryObject) {
		MchtTaxInvoiceInfoExample example = new MchtTaxInvoiceInfoExample();
		MchtTaxInvoiceInfoExample.Criteria criteria = example.createCriteria();
		criteria.andDelFlagEqualTo(Const.FLAG_FALSE);
		if(queryObject.getQuery("mchtId") != null){
			criteria.andMchtIdEqualTo(queryObject.getQueryToInt("mchtId"));
		}
		if(queryObject.getSort().size() > 0){
			example.setOrderByClause(queryObject.getSortString());
		}
		return example;
	}
	
}
