package com.jf.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.NovaStrategyCustomMapper;
import com.jf.dao.NovaStrategyMapper;
import com.jf.dao.OrderLogCustomMapper;
import com.jf.dao.OrderLogMapper;
import com.jf.entity.NovaStrategy;
import com.jf.entity.NovaStrategyCustom;
import com.jf.entity.NovaStrategyExample;
import com.jf.entity.StaffBean;
import com.jf.entity.StateCode;

@Service
@Transactional
public class NovaStrategyService extends BaseService<NovaStrategy, NovaStrategyExample>{
	@Autowired
	private NovaStrategyMapper novaStrategyMapper;

	@Autowired
	private NovaStrategyCustomMapper novaStrategyCustomMapper;
	
	
	@Autowired
	public void setNovaStrategyMapper(NovaStrategyMapper novaStrategyMapper) {
		super.setDao(novaStrategyMapper);
		this.novaStrategyMapper = novaStrategyMapper;
	}

	public List<NovaStrategyCustom> selectByCustomExample(
			NovaStrategyExample novaStrategyExample) {
		// TODO Auto-generated method stub
		 List<NovaStrategyCustom> novaStrategyCustoms = novaStrategyCustomMapper.selectByExampleCustom(novaStrategyExample);
		return novaStrategyCustoms;
	}

	//新星攻略信息编辑或新增
	public Map<String, Object> editOrAdd(HttpServletRequest request) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();
		NovaStrategy novaStrategy = new NovaStrategy();
		String code = StateCode.JSON_AJAX_SUCCESS.getStateCode();
		String msg = null;
		try {
			
			if(StringUtils.isNotBlank(request.getParameter("id"))){
				//更新
				novaStrategy = novaStrategyMapper.selectByPrimaryKey(Integer.parseInt(request.getParameter("id")));
				novaStrategy.setTitle(request.getParameter("title"));
				if(StringUtils.isNotBlank(request.getParameter("seqNo"))){
					novaStrategy.setSeqNo(Integer.parseInt(request.getParameter("seqNo")));
				}else {
					novaStrategy.setSeqNo(null);
				}
				novaStrategy.setContent(request.getParameter("content"));
				novaStrategy.setUpdateBy((Integer) request.getAttribute("staffId"));
				novaStrategy.setUpdateDate(new Date());
				novaStrategy.setDelFlag("0");
				novaStrategyMapper.updateByPrimaryKey(novaStrategy);
				msg = "编辑成功！";
			}else {
				//新增
				novaStrategy.setTitle(request.getParameter("title"));
				novaStrategy.setContent(request.getParameter("content"));
				if(StringUtils.isNotBlank(request.getParameter("seqNo"))){
					novaStrategy.setSeqNo(Integer.parseInt(request.getParameter("seqNo")));
				}	
				novaStrategy.setType(request.getParameter("type"));
				novaStrategy.setCreateBy((Integer) request.getAttribute("staffId"));
				novaStrategy.setCreateDate(new Date());
				novaStrategy.setUpdateBy((Integer) request.getAttribute("staffId"));
				novaStrategy.setUpdateDate(new Date());
				novaStrategy.setDelFlag("0");
				novaStrategyMapper.insertSelective(novaStrategy);
				msg = "添加成功！";
			}
		} catch (Exception e) {
			// TODO: handle exception 
			e.printStackTrace();
			code = StateCode.JSON_AJAX_ERROR.getStateCode();
			msg = StateCode.ERR_DB_INVOKE.getStateMessage();
		}
		map.put("statusCode", code);
		map.put("message", msg);
		return map;
	}
}
