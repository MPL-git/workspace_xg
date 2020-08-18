package com.jf.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.CutPriceCnfTplCustomMapper;
import com.jf.dao.CutPriceCnfTplDtlCustomMapper;
import com.jf.dao.CutPriceCnfTplMapper;
import com.jf.entity.CutPriceCnfTpl;
import com.jf.entity.CutPriceCnfTplCustom;
import com.jf.entity.CutPriceCnfTplCustomExample;
import com.jf.entity.CutPriceCnfTplDtl;
import com.jf.entity.CutPriceCnfTplExample;

@Service
@Transactional
public class CutPriceCnfTplService extends BaseService<CutPriceCnfTpl, CutPriceCnfTplExample> {

	@Autowired
	private CutPriceCnfTplMapper cutPriceCnfTplMapper;
	
	@Autowired
	private CutPriceCnfTplCustomMapper cutPriceCnfTplCustomMapper;
	
	@Autowired
	private CutPriceCnfTplDtlCustomMapper cutPriceCnfTplDtlCustomMapper;
	
	@Autowired
	public void setCutPriceCnfTplMapper(CutPriceCnfTplMapper cutPriceCnfTplMapper) {
		super.setDao(cutPriceCnfTplMapper);
		this.cutPriceCnfTplMapper = cutPriceCnfTplMapper;
	}
	
	public int countByCustomExample(CutPriceCnfTplCustomExample example) {
		return cutPriceCnfTplCustomMapper.countByCustomExample(example);
	}

    public List<CutPriceCnfTplCustom> selectByCustomExample(CutPriceCnfTplCustomExample example) {
    	return cutPriceCnfTplCustomMapper.selectByCustomExample(example);
    }

    public CutPriceCnfTpl selectByCustomPrimaryKey(Integer id) {
    	return cutPriceCnfTplCustomMapper.selectByCustomPrimaryKey(id);
    }

    public int updateByCustomExampleSelective(CutPriceCnfTpl record, CutPriceCnfTplCustomExample example) {
    	return cutPriceCnfTplCustomMapper.updateByCustomExampleSelective(record, example);
    }

    /**
     * 
     * @Title addOrUpdateCutPriceCnfTpl   
     * @Description TODO(这里用一句话描述这个方法的作用)   
     * @author pengl
     * @date 2018年6月8日 上午11:53:12
     */
    public void addOrUpdateCutPriceCnfTpl(CutPriceCnfTpl cutPriceCnfTpl, List<CutPriceCnfTplDtl> cutPriceCnfTplDtlList) {
    	if(cutPriceCnfTpl.getId() == null) {
    		cutPriceCnfTplMapper.insertSelective(cutPriceCnfTpl);
    		for(CutPriceCnfTplDtl cutPriceCnfTplDtl : cutPriceCnfTplDtlList) {
    			cutPriceCnfTplDtl.setCutPriceCnfTplId(cutPriceCnfTpl.getId());
    		}
    		if(cutPriceCnfTplDtlList != null && cutPriceCnfTplDtlList.size() > 0) {
    			cutPriceCnfTplDtlCustomMapper.insertCutPriceCnfTplDtlList(cutPriceCnfTplDtlList);
			}
    	}else {
    		cutPriceCnfTplMapper.updateByPrimaryKeySelective(cutPriceCnfTpl);
    		cutPriceCnfTplDtlCustomMapper.updateCutPriceCnfTplDtlIdListSelective(cutPriceCnfTplDtlList);
		}
    }
	
}
