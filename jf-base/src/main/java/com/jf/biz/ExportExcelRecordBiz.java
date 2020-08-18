package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.ExportExcelRecordExtMapper;
import com.jf.dao.ExportExcelRecordMapper;
import com.jf.entity.ExportExcelRecord;
import com.jf.entity.ExportExcelRecordExample;
import com.jf.entity.ExportExcelRecordExt;
import com.jf.entity.ExportExcelRecordExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ExportExcelRecordBiz extends BaseService<ExportExcelRecord, ExportExcelRecordExample> {

	@Autowired
	private ExportExcelRecordMapper dao;
	@Autowired
	private ExportExcelRecordExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setExportExcelRecordMapper(ExportExcelRecordMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public ExportExcelRecordExt save(ExportExcelRecordExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public ExportExcelRecord update(ExportExcelRecord model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		ExportExcelRecord model = new ExportExcelRecord();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public ExportExcelRecordExt findById(int id){
		return extDao.findById(id);
	}

	public ExportExcelRecordExt find(ExportExcelRecordExtExample example){
		return extDao.find(example.fill());
	}

	public int count(ExportExcelRecordExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, ExportExcelRecordExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, ExportExcelRecordExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, ExportExcelRecordExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(ExportExcelRecordExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<ExportExcelRecordExt> list(ExportExcelRecordExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<ExportExcelRecordExt> paginate(ExportExcelRecordExtExample example) {
		List<ExportExcelRecordExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
