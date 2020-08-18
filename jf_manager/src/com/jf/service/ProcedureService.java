package com.jf.service;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;











import com.gzs.common.utils.StringUtil;
import com.jf.dao.ProcedureCustomMapper;
import com.jf.dao.ProcedureMapper;
import com.jf.entity.Procedure;
import com.jf.entity.ProcedureCustom;
import com.jf.entity.ProcedureExample;

@Service
@Transactional
public class ProcedureService extends BaseService<Procedure, ProcedureExample> {
	
	@Autowired
	private ProcedureMapper procedureMapper;
	
	@Autowired
	private ProcedureCustomMapper procedureCustomMapper;
	
	@Autowired
	public void setProcedureMapper(ProcedureMapper procedureMapper) {
		super.setDao(procedureMapper);
		this.procedureMapper = procedureMapper;
	}
	
	 public  int countCustomByExample(ProcedureExample example){
		 return procedureCustomMapper.countCustomByExample(example);
	 };
	
	 
	 public  List<ProcedureCustom> selectCustomsByExample(ProcedureExample example){
		 return procedureCustomMapper.selectCustomsByExample(example);
	 };

	 public ProcedureCustom selectCustomByPrimaryKey(Integer id){
		 return procedureCustomMapper.selectCustomByPrimaryKey(id);
	 };
	 
	 public void saveProcedure(HttpServletRequest request,String staffID){
		 	String id = request.getParameter("procedureId");
			String staffIds = request.getParameter("staffIds");
			String staIdsString="";
			if(!StringUtil.isEmpty(staffIds)){
				JSONArray staffIdsList = JSONArray.fromObject(staffIds);				
				List<String> staffList = (List<String>) JSONArray.toCollection(staffIdsList, String.class);
				for (int i = 0; i < staffList.size(); i++) {
					if(i==0){
						staIdsString+=staffList.get(i);
					}else{
						staIdsString+=","+staffList.get(i);
					}
				}
			}
		 	if(!StringUtil.isEmpty(id)){//修改
		 		Procedure procedure = procedureMapper.selectByPrimaryKey(Integer.parseInt(id));
		 		if(!StringUtil.isEmpty(request.getParameter("name"))){
		 			procedure.setName(request.getParameter("name"));
		 		}
		 		procedure.setCopiers(staIdsString);
		 		procedure.setUpdateBy(Integer.parseInt(staffID));
		 		procedure.setUpdateDate(new Date());
		 		procedureMapper.updateByPrimaryKeySelective(procedure);
		 	}else{//保存
		 		Procedure p = new Procedure();
		 		p.setCopiers(staIdsString);
		 		p.setCreateBy(Integer.parseInt(staffID));
		 		p.setCreateDate(new Date());
		 		p.setStatus("0");
		 		if(!StringUtil.isEmpty(request.getParameter("name"))){
		 			p.setName(request.getParameter("name"));
		 		}
		 		procedureMapper.insertSelective(p);
		 	}
	 }
	 
	 
	 
	 
}
