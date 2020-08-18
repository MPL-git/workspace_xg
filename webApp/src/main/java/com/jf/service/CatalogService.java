package com.jf.service;

import com.jf.common.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.CatalogMapper;
import com.jf.entity.Catalog;
import com.jf.entity.CatalogExample;

@Service
@Transactional
public class CatalogService extends BaseService<Catalog, CatalogExample> {

	@Autowired
	private CatalogMapper catalogMapper;
	
	@Autowired
	public void setCatalogCatalogMapper(CatalogMapper catalogMapper) {
		this.setDao(catalogMapper);
		this.catalogMapper = catalogMapper;
	}
	
	
	
	
	
}
