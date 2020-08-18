package com.jf.service;

import com.jf.common.base.ArgException;
import com.jf.common.base.BaseService;
import com.jf.dao.MchtBlPicChgMapper;
import com.jf.dao.MchtInfoChgCustomMapper;
import com.jf.dao.MchtInfoChgMapper;
import com.jf.dao.MchtInfoMapper;
import com.jf.entity.MchtBlPicChg;
import com.jf.entity.MchtBlPicChgExample;
import com.jf.entity.MchtInfo;
import com.jf.entity.MchtInfoChg;
import com.jf.entity.MchtInfoChgCustom;
import com.jf.entity.MchtInfoChgExample;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Iterator;
import java.util.List;

@Service
@Transactional
public class MchtInfoChgService extends BaseService<MchtInfoChg, MchtInfoChgExample> {
	@Autowired
	private MchtInfoChgMapper mchtInfoChgMapper;

	@Autowired
	private MchtInfoChgCustomMapper mchtInfoChgCustomMapper;

	@Autowired
	private MchtInfoMapper mchtInfoMapper;

	@Autowired
	private MchtBlPicChgMapper mchtBlPicChgMapper;

	@Autowired
	public void setMchtInfoChgMapper(MchtInfoChgMapper mchtInfoChgMapper) {
		super.setDao(mchtInfoChgMapper);
		this.mchtInfoChgMapper = mchtInfoChgMapper;
	}

	public List<MchtInfoChgCustom> selectMchtInfoChgCustomByExample(MchtInfoChgExample example) {
		return mchtInfoChgCustomMapper.selectByExample(example);
	}

	public MchtInfoChgCustom selectMchtInfoChgCustomByPrimaryKey(Integer id) {
		return mchtInfoChgCustomMapper.selectByPrimaryKey(id);
	}

	public void mchtInfoChgCommitSave(MchtInfoChg mchtInfoChg,String mchtBlPicChgs) throws ArgException {

		MchtInfo mchtInfo = mchtInfoMapper.selectByPrimaryKey(mchtInfoChg.getMchtId());
		if (mchtInfo == null) {
			throw new ArgException("公司信息不存在！");
		}
		if (!mchtInfo.getIsCompanyInfPerfect().equals("1")) {
			throw new ArgException("公司信息未完善，请先完善！");
		}

		if (mchtInfoChg.getId() != null) {
			MchtInfoChg oldMchtInfoChg = mchtInfoChgMapper.selectByPrimaryKey(mchtInfoChg.getId());
			if (oldMchtInfoChg.getStatus().equals("2") || oldMchtInfoChg.getStatus().equals("3")) {
				throw new ArgException("您的信息在审核或已审核通过，暂不可修改！");
			}
			mchtInfoChgMapper.updateByPrimaryKeySelective(mchtInfoChg);
		} else {
			mchtInfoChg.setStatus("0");
			mchtInfoChg.setMchtCode(mchtInfo.getMchtCode());
			mchtInfoChgMapper.insertSelective(mchtInfoChg);
		}
		
		//更新公司营业执照图
		MchtBlPicChgExample mchtBlPicChgExample=new MchtBlPicChgExample();
		mchtBlPicChgExample.createCriteria().andMchtInfoChgIdEqualTo(mchtInfoChg.getId()).andDelFlagEqualTo("0");
		MchtBlPicChg mchtBlPicChg4Update=new MchtBlPicChg();
		mchtBlPicChg4Update.setDelFlag("1");
		mchtBlPicChgMapper.updateByExampleSelective(mchtBlPicChg4Update, mchtBlPicChgExample);
	
	JSONArray blPicArray=JSONArray.fromObject(mchtBlPicChgs);
	Iterator<JSONObject> it= blPicArray.iterator();
	while (it.hasNext()) {
		JSONObject mchtBlPicChgObject=it.next();
		
		mchtBlPicChgExample=new MchtBlPicChgExample();
		mchtBlPicChgExample.createCriteria().andMchtInfoChgIdEqualTo(mchtInfoChg.getId()).andPicEqualTo(mchtBlPicChgObject.getString("pic"));
		mchtBlPicChg4Update=new MchtBlPicChg();
		mchtBlPicChg4Update.setDelFlag("0");
		int updateCount=mchtBlPicChgMapper.updateByExampleSelective(mchtBlPicChg4Update, mchtBlPicChgExample);
		if(updateCount>0){
			continue;
		}
		MchtBlPicChg mchtBlPicChg=new MchtBlPicChg();
		mchtBlPicChg.setMchtInfoChgId(mchtInfoChg.getId());
		mchtBlPicChg.setPic(mchtBlPicChgObject.getString("pic"));
		mchtBlPicChg.setCreateBy(mchtInfo.getUpdateBy());
		mchtBlPicChg.setCreateDate(mchtInfo.getUpdateDate());
		mchtBlPicChg.setUpdateBy(mchtInfo.getUpdateBy());
		mchtBlPicChg.setUpdateDate(mchtInfo.getUpdateDate());
		mchtBlPicChgMapper.insertSelective(mchtBlPicChg);
	}
		
	}

	public void mchtInfoChgCommitAudit(MchtInfoChg mchtInfoChg) throws ArgException {
		
		MchtInfo mchtInfo = mchtInfoMapper.selectByPrimaryKey(mchtInfoChg.getMchtId());
		if (mchtInfo == null) {
			throw new ArgException("公司信息不存在！");
		}
		if (!mchtInfo.getIsCompanyInfPerfect().equals("1")) {
			throw new ArgException("公司信息未完善，请先完善！");
		}
		
		if (mchtInfoChg.getId() != null) {
			MchtInfoChg oldMchtInfoChg = mchtInfoChgMapper.selectByPrimaryKey(mchtInfoChg.getId());
			if (oldMchtInfoChg.getStatus().equals("2") || oldMchtInfoChg.getStatus().equals("3")) {
				throw new ArgException("您的信息在审核或已审核通过，暂不可修改！");
			}
			mchtInfoChg.setStatus("1");//置为待审状态
			mchtInfoChgMapper.updateByPrimaryKeySelective(mchtInfoChg);
		} else {
			mchtInfoChg.setStatus("1");//置为待审状态
			mchtInfoChg.setMchtCode(mchtInfo.getMchtCode());
			mchtInfoChgMapper.insertSelective(mchtInfoChg);
		}
		
	}
	public void mchtInfoChgCommitAudit(MchtInfoChg mchtInfoChg,String mchtBlPicChgs) throws ArgException {

		MchtInfo mchtInfo = mchtInfoMapper.selectByPrimaryKey(mchtInfoChg.getMchtId());
		if (mchtInfo == null) {
			throw new ArgException("公司信息不存在！");
		}
		if (!mchtInfo.getIsCompanyInfPerfect().equals("1")) {
			throw new ArgException("公司信息未完善，请先完善！");
		}

		if (mchtInfoChg.getId() != null) {
			MchtInfoChg oldMchtInfoChg = mchtInfoChgMapper.selectByPrimaryKey(mchtInfoChg.getId());
			if (oldMchtInfoChg.getStatus().equals("2") || oldMchtInfoChg.getStatus().equals("3")) {
				throw new ArgException("您的信息在审核或已审核通过，暂不可修改！");
			}
			mchtInfoChg.setStatus("2");//置为待审状态
			mchtInfoChgMapper.updateByPrimaryKeySelective(mchtInfoChg);
		} else {
			mchtInfoChg.setStatus("2");//置为待审状态
			mchtInfoChg.setMchtCode(mchtInfo.getMchtCode());
			mchtInfoChgMapper.insertSelective(mchtInfoChg);
		}
		
		//更新公司营业执照图
		MchtBlPicChgExample mchtBlPicChgExample=new MchtBlPicChgExample();
		mchtBlPicChgExample.createCriteria().andMchtInfoChgIdEqualTo(mchtInfoChg.getId()).andDelFlagEqualTo("0");
		MchtBlPicChg mchtBlPicChg4Update=new MchtBlPicChg();
		mchtBlPicChg4Update.setDelFlag("1");
		mchtBlPicChgMapper.updateByExampleSelective(mchtBlPicChg4Update, mchtBlPicChgExample);
	
	JSONArray blPicArray=JSONArray.fromObject(mchtBlPicChgs);
	Iterator<JSONObject> it= blPicArray.iterator();
	while (it.hasNext()) {
		JSONObject mchtBlPicChgObject=it.next();
		
		mchtBlPicChgExample=new MchtBlPicChgExample();
		mchtBlPicChgExample.createCriteria().andMchtInfoChgIdEqualTo(mchtInfoChg.getId()).andPicEqualTo(mchtBlPicChgObject.getString("pic"));
		mchtBlPicChg4Update=new MchtBlPicChg();
		mchtBlPicChg4Update.setDelFlag("0");
		int updateCount=mchtBlPicChgMapper.updateByExampleSelective(mchtBlPicChg4Update, mchtBlPicChgExample);
		if(updateCount>0){
			continue;
		}
		MchtBlPicChg mchtBlPicChg=new MchtBlPicChg();
		mchtBlPicChg.setMchtInfoChgId(mchtInfoChg.getId());
		mchtBlPicChg.setPic(mchtBlPicChgObject.getString("pic"));
		mchtBlPicChg.setCreateBy(mchtInfo.getUpdateBy());
		mchtBlPicChg.setCreateDate(mchtInfo.getUpdateDate());
		mchtBlPicChg.setUpdateBy(mchtInfo.getUpdateBy());
		mchtBlPicChg.setUpdateDate(mchtInfo.getUpdateDate());
		mchtBlPicChgMapper.insertSelective(mchtBlPicChg);
	}
	}

}
