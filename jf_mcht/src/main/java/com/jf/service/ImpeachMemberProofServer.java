package com.jf.service;

import com.jf.common.base.BaseService;
import com.jf.dao.ImpeachMemberProofMapper;
import com.jf.entity.ImpeachMember;
import com.jf.entity.ImpeachMemberProof;
import com.jf.entity.ImpeachMemberProofExample;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;


//举报会员凭证
@Service
@Transactional
public class ImpeachMemberProofServer  extends BaseService<ImpeachMemberProof, ImpeachMemberProofExample> {

	@Autowired
	private ImpeachMemberProofMapper dao;
	
	
	@Autowired
	public void setProvinceFreightMapper(ImpeachMemberProofMapper impeachMemberProofMapper) {
		super.setDao(impeachMemberProofMapper);
		this.dao = impeachMemberProofMapper;
	}

	//添加举报会员凭证表
	public void saveImpeachMemberProof(ImpeachMember impeachMember,String fileNames,String filePaths) {
		// TODO Auto-generated method stub
		 JSONArray fileNamesArray = JSONArray.fromObject(fileNames);
		 JSONArray filePathsArray = JSONArray.fromObject(filePaths);
		 List<String> fileNameList = (List<String>) JSONArray.toCollection(fileNamesArray, String.class);
		 List<String> filePathList = (List<String>) JSONArray.toCollection(filePathsArray, String.class);
		 for (int i = 0; i < fileNameList.size(); i++) {
			 ImpeachMemberProof impeachMemberProof = new ImpeachMemberProof();
			 impeachMemberProof.setImpeachMemberId(impeachMember.getId());//举报id
			 impeachMemberProof.setUploadSource("0");//文件来源
			 impeachMemberProof.setFileName(fileNameList.get(i));
			 impeachMemberProof.setFilePath(filePathList.get(i));
			 impeachMemberProof.setCreateDate(impeachMember.getCreateDate());//创建时间
			 impeachMemberProof.setCreateBy(0);
			 impeachMemberProof.setDelFlag("0");
			 impeachMemberProof.setIsAdd("0");
			 dao.insertSelective(impeachMemberProof);
		}

	}
	
	//修改举报会员凭证表
	public void updateImpeachMemberProof(ImpeachMember impeachMember,String fileNames,String filePaths){
		
		//更新举报凭证图
		ImpeachMemberProofExample impeachMemberProofExample = new ImpeachMemberProofExample();
		impeachMemberProofExample.createCriteria().andDelFlagEqualTo("0").andImpeachMemberIdEqualTo(impeachMember.getId());
		ImpeachMemberProof impeachMemberProof4Update = new ImpeachMemberProof();
		impeachMemberProof4Update.setDelFlag("1");
		impeachMemberProof4Update.setIsAdd("0");
		dao.updateByExampleSelective(impeachMemberProof4Update, impeachMemberProofExample );
		
		 JSONArray fileNamesArray = JSONArray.fromObject(fileNames);
		 JSONArray filePathsArray = JSONArray.fromObject(filePaths);
		 List<String> fileNameList = (List<String>) JSONArray.toCollection(fileNamesArray, String.class);
		 List<String> filePathList = (List<String>) JSONArray.toCollection(filePathsArray, String.class);
		 for (int i = 0; i < fileNameList.size(); i++) {
			String fName=fileNameList.get(i);
			String fPath=filePathList.get(i);
			 impeachMemberProofExample = new ImpeachMemberProofExample();
			 impeachMemberProofExample.createCriteria().andFilePathEqualTo(fPath).andImpeachMemberIdEqualTo(impeachMember.getId());
			 impeachMemberProof4Update = new ImpeachMemberProof();
			 impeachMemberProof4Update.setDelFlag("0");
			 int updateCount = dao.updateByExampleSelective(impeachMemberProof4Update, impeachMemberProofExample);
			 if(updateCount>0){
				 continue;
			 }
			 ImpeachMemberProof impeachMemberProof = new ImpeachMemberProof();
			 impeachMemberProof.setImpeachMemberId(impeachMember.getId());//举报id
			 impeachMemberProof.setUploadSource("0");//文件来源
			 impeachMemberProof.setFileName(fileNameList.get(i));
			 impeachMemberProof.setFilePath(filePathList.get(i));
			 impeachMemberProof.setUpdateDate(new Date());
			 impeachMemberProof.setCreateBy(0);
			 impeachMemberProof.setDelFlag("0");
			 impeachMemberProof.setIsAdd("1");
			 dao.insertSelective(impeachMemberProof);
			 
		 }

		
	}

	
	
}
