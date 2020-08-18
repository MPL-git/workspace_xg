package com.jf.controller;

import com.jf.common.base.ResponseMsg;
import com.jf.common.utils.FileUtil;
import com.jf.common.utils.StringUtil;
import com.jf.dao.ProductCustomMapper;
import com.jf.entity.ActivityExample;
import com.jf.entity.ActivityProduct;
import com.jf.entity.SysParamCfg;
import com.jf.entity.SysParamCfgExample;
import com.jf.service.*;
import com.jf.task.ProductTask;
import net.coobird.thumbnailator.geometry.Positions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ProductController {


	@Resource
	private ProductService productService;

	@Resource
	private SysParamCfgService sysParamCfgService;

	@Autowired
	private ActivityService activityService;

	@Autowired
	private ActivityProductService activityProductService;

	@Autowired
	private ProductCustomMapper productCustomMapper;

	@Autowired
	private ProductTask productTask;

	@Autowired
	private ProductStatisticsService productStatisticsService;

	@ResponseBody
	@RequestMapping("/product/watermarkProductMainPic")
	public ResponseMsg watermarkProductMainPic(HttpServletRequest request) {
		SysParamCfgExample sysParamCfgExample = new SysParamCfgExample();
		sysParamCfgExample.createCriteria().andParamCodeEqualTo("ACTIVITY_IDS_2_WATERMARK");
		List<SysParamCfg> sysParamCfgs = sysParamCfgService.selectByExample(sysParamCfgExample);
		if (sysParamCfgs != null && sysParamCfgs.size() > 0 && !StringUtil.isEmpty(sysParamCfgs.get(0).getParamValue())) {
			watermarkProductMainPic(Integer.valueOf(request.getParameter("activityAreaId")), sysParamCfgs.get(0).getParamDesc());
		}
		return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG);

	}

	@ResponseBody
	@RequestMapping("/product/watermarkSingleActivityProductMainPic")
	public ResponseMsg watermarkSingleActivityProductMainPic(HttpServletRequest request) {
		SysParamCfgExample sysParamCfgExample = new SysParamCfgExample();
		sysParamCfgExample.createCriteria().andParamCodeEqualTo("SINGLE_ACTIVITY_TYPE_2_WATERMARK");
		List<SysParamCfg> sysParamCfgs = sysParamCfgService.selectByExample(sysParamCfgExample);
		if (sysParamCfgs != null && sysParamCfgs.size() > 0) {
			for (SysParamCfg sysParamCfg : sysParamCfgs) {
				productTask.watermarkSingleActivityProductMainPic(sysParamCfg.getParamValue(), sysParamCfg.getParamDesc());
			}
		}
		return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG);

	}

	public void watermarkProductMainPic(Integer activityAreaId, String watermarkPicPath) {

		ActivityExample activityExample = new ActivityExample();
		activityExample.createCriteria().andDelFlagEqualTo("0").andActivityAreaIdEqualTo(activityAreaId);

		if (activityService.countByExample(activityExample) > 0) {
			Map<String, Object> params = new HashMap<String, Object>();
			List<Integer> areaIds = new ArrayList<Integer>();
			areaIds.add(activityAreaId);
			params.put("activityAreaIds", areaIds);
			List<Map<String, Object>> productPicMaps = productCustomMapper.getNoWatermarkProductPic(params);
			if (productPicMaps != null && productPicMaps.size() > 0) {
				for (Map<String, Object> productPicMap : productPicMaps) {
					String pic = (String) productPicMap.get("pic");
					String sourcePic = pic.substring(0, pic.lastIndexOf(".")) + "_" + "70Q" + pic.substring(pic.lastIndexOf("."));
					String sourcePicM = pic.substring(0, pic.lastIndexOf(".")) + "_" + "M" + pic.substring(pic.lastIndexOf("."));
					String target70QMkPic = pic.substring(0, pic.lastIndexOf(".")) + "_70Q_WM_" + activityAreaId + pic.substring(pic.lastIndexOf("."));
					String targetMMkPic = pic.substring(0, pic.lastIndexOf(".")) + "_M_WM_" + activityAreaId + pic.substring(pic.lastIndexOf("."));
					String watermarkPic = watermarkPicPath + "aa_" + activityAreaId + ".png";
					String watermarkPicM = watermarkPicPath + "aa_m_" + activityAreaId + ".png";
					try {
						FileUtil.imgWatermark(sourcePic, target70QMkPic, Positions.BOTTOM_RIGHT, watermarkPic, 1);
						FileUtil.imgWatermark(sourcePicM, targetMMkPic, Positions.BOTTOM_RIGHT, watermarkPicM, 1);
						ActivityProduct activityProduct4Update = new ActivityProduct();
						activityProduct4Update.setIsWatermark("1");
//						activityProduct4Update.setCreateBy(1);
						activityProduct4Update.setId((Integer) productPicMap.get("activity_product_id"));
						activityProductService.updateByPrimaryKeySelective(activityProduct4Update);
					} catch (IOException e) {
						e.printStackTrace();
					}

				}
			}
		}

	}

	@ResponseBody
	@RequestMapping("/product/productStatisticsInfo")
	public ResponseMsg productStatisticsInfo(HttpServletRequest request) {
		try {
			countNewProduct();
			updateProductStatisticsList();
		} catch (Exception e) {
			return new ResponseMsg(ResponseMsg.ERROR, e.getMessage());
		}
		return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG);

	}

	public void countNewProduct() {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("limitStart", 0);
		paramMap.put("limitSize", 5000);
		for (; ; ) {
			if (productStatisticsService.countNewProduct() > 0) {
				List<Map<String, Object>> mapList = productStatisticsService.selectIdProductStatistics(paramMap);
				productStatisticsService.insertProductStatisticsList(mapList);
			} else {
				break;
			}
		}
	}

	public void updateProductStatisticsList() {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("limitStart", 0);
		paramMap.put("limitSize", 5000);
		for (; ; ) {
			if (productStatisticsService.countProductStatistics() > 0) {
				List<Map<String, Object>> mapList = productStatisticsService.selectProductIdProductStatistics(paramMap);
				productStatisticsService.updateProductStatisticsList(mapList);
			} else {
				break;
			}
		}
	}

	@ResponseBody
	@RequestMapping("/product/addMchtShopDynamic")
	public synchronized ResponseMsg addMchtShopDynamic(HttpServletRequest request) {
		try {
			productService.addMchtShopDynamic();
		} catch (Exception e) {
			return new ResponseMsg(ResponseMsg.ERROR, e.getMessage());
		}
		return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG);

	}


}
