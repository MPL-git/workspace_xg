package com.jf.controller;

import com.jf.common.base.ResponseMsg;
import com.jf.common.enums.CatalogEnum;
import com.jf.common.ext.exception.Assert;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.common.ext.util.StrKit;
import com.jf.entity.Catalog;
import com.jf.entity.Information;
import com.jf.entity.InformationCustom;
import com.jf.entity.MchtInformation;
import com.jf.service.CatalogService;
import com.jf.service.InformationService;
import com.jf.service.MchtInformationService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 信息Controller
 */
@Controller
@RequestMapping("info")
public class InfoController extends BaseController {

	private static Logger logger = LoggerFactory.getLogger(InfoController.class);

	@Resource
	private InformationService informationService;
	@Resource
	private CatalogService catalogService;
	@Resource
	private MchtInformationService mchtInformationService;


	@RequestMapping
	public String index() {
		Map<String, Object> data = new HashMap<>();

		QueryObject queryObject = new QueryObject();
		queryObject.addQuery("ids", CatalogEnum.valueList);
		queryObject.addSort("seq_no", QueryObject.SORT_ASC);
		List<Catalog> catalogList = catalogService.findList(queryObject);
		List<Integer> catalogIds;
		List<Catalog> secondCatalogList;
		List<Information> infoList;

		for(Catalog catalog : catalogList){
			catalogIds = new ArrayList<>();
			catalogIds.add(catalog.getId());

			queryObject = new QueryObject();
			queryObject.addQuery("parentId", catalog.getId());
			queryObject.addSort("seq_no", QueryObject.SORT_ASC);
			secondCatalogList = catalogService.findList(queryObject);
			for(Catalog secondCatalog : secondCatalogList){
				catalogIds.add(secondCatalog.getId());
			}

			queryObject = new QueryObject();
			queryObject.addQuery("catalogIds", catalogIds);
			queryObject.addSort("create_date", QueryObject.SORT_DESC);
			infoList = informationService.findList(queryObject);

			catalog.put("secondCatalogList", secondCatalogList);	//子栏目
			catalog.put("infoList", infoList);	//信息列表
		}
		data.put("catalogList", catalogList);
		return page(data,"info/index");
	}

	@RequestMapping("listPage")
	public String listPage() {
		int catalogId = getParaToInt("catalogId");
		Assert.notZero(catalogId, "分类不能为空");

		Catalog catalog = catalogService.findById(catalogId);
		Assert.notNull(catalog, "没有找到对应的分类");

//		if(catalog.getParentId() != 0){
//			catalog = catalogService.findById(catalog.getParentId());
//		}

		Map<String, Object> data = new HashMap<>();
		data.put("catalog", catalog);
		return page(data,"info/list");
	}

	@ResponseBody
	@RequestMapping("list")
	public String list(QueryObject queryObject) {
		Integer catalogId = getParaToInt("catalogId");
		Assert.moreThanZero(catalogId, "信息分类不能为空");

		List<Integer> catalogIds = new ArrayList<>();
		catalogIds.add(catalogId);


//		QueryObject catalogQueryObject = new QueryObject();
//
//		catalogQueryObject.addQuery("parentId", catalogId);
//		List<Catalog> secondCatalogList = catalogService.findList(catalogQueryObject);
//		for(Catalog secondCatalog : secondCatalogList){
//			catalogIds.add(secondCatalog.getId());
//		}

		Map<String,Object> data = new HashMap();
		if(catalogId == 3){
			Map<String,Object> map = new HashMap();
			if(StrKit.notBlank(getPara("title"))){
				map.put("title", getPara("title").trim());
			}
			if(getPara("id") != null){
				map.put("id",getParaToInt("id"));
			}
			map.put("catalogId",catalogId);
			map.put("mchtId",getUserInfo().getMchtId());
			map.put("limitStart",queryObject.getLimitStart());
			map.put("limitSize",queryObject.getPageSize());
			Integer totalCount = informationService.selectCountCustom(map);
			List<InformationCustom> list = informationService.selectByExampleCustom(map);
			Page<InformationCustom> page = new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
			data.put("page", page);
		}else {
			queryObject.addQuery("catalogIds", catalogIds);
			if(StrKit.notBlank(getPara("title"))){
				queryObject.addQuery("title", getPara("title").trim());
			}
			queryObject.addSort("create_date", QueryObject.SORT_DESC);

			Page<Information> page = informationService.paginate(queryObject);
			data.put("page", page);
		}

		return json(data);
	}

	@RequestMapping("content")
	public String content() {
		int id = getParaToInt("id");
		Assert.notZero(id, "id不能为空");

		Information info = informationService.findById(id);
		Assert.notNull(info, "没有找到此条信息");

		Map<String, Object> data = new HashMap<>();
		data.put("info", info);
		return page(data,"info/content");
	}

	@ResponseBody
	@RequestMapping("insert/{id}")
	public ResponseMsg insert(@PathVariable("id") Integer id) {
		MchtInformation mchtInformation = new MchtInformation();
		mchtInformation.setMchtId(getMchtId());
		mchtInformation.setInformationId(id);
		mchtInformation.setCreateBy(getUserInfo().getId());
		mchtInformation.setCreateDate(new Date());
		try {
			mchtInformationService.insertSelective(mchtInformation);
		}catch (Exception e){
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR, ResponseMsg.ERROR_MSG);
		}
		return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG);
	}

}
