package com.jf.task;

import com.jf.common.ext.RegCondition;
import com.jf.common.utils.DateUtil;
import com.jf.common.utils.FileUtil;
import com.jf.common.utils.StringUtil;
import com.jf.dao.ProductCustomMapper;
import com.jf.entity.ActivityExample;
import com.jf.entity.ActivityProduct;
import com.jf.entity.SysParamCfg;
import com.jf.entity.SysParamCfgExample;
import com.jf.service.*;
import net.coobird.thumbnailator.geometry.Positions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author chenwf:
 * @version 1.0
 * @date 创建时间：2018年6月1日 上午9:52:21
 * @parameter
 * @return
 */
@RegCondition
@Component
public class ProductTask {

	private static Logger logger = LoggerFactory.getLogger(ProductTask.class);
	@Resource
	private ProductService productService;
	@Resource
	private SysParamCfgService sysParamCfgService;
	@Resource
	private ActivityAreaService activityAreaService;

	@Autowired
	private ActivityService activityService;

	@Autowired
	private ActivityProductService activityProductService;

	@Autowired
	private ProductCustomMapper productCustomMapper;
	@Autowired
	private SingleProductActivityService singleProductActivityService;

	@Autowired
	private ProductTypeStatisticsService productTypeStatisticsService;

	@Autowired
	private ProductStatisticsService productStatisticsService;

	/**
	 * 方法描述 会场
	 *
	 * @author chenwf:
	 * @date 创建时间：2018年6月5日 下午1:57:17
	 * @version
	 */
	// @Scheduled(cron = "0 10 6-20 * * ?")
	public void updateActivityAreaProductInfoTask() {
		try {
			activityAreaService.updateActivityAreaProductInfoTask();
		} catch (Exception e) {
			logger.info("推送异常", e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * 方法描述 ：活动
	 *
	 * @author chenwf:
	 * @date 创建时间：2018年6月5日 下午1:57:35
	 * @version
	 */
	// @Scheduled(cron = "0 10 6-20 * * ?")
	public void updateActivityProductInfoTask() {
		try {
			activityAreaService.updateActivityProductInfoTask();
		} catch (Exception e) {
			logger.info("推送异常", e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * 方法描述 ：单品活动
	 *
	 * @author chenwf:
	 * @date 创建时间：2018年6月5日 下午1:57:42
	 * @version
	 */
	// @Scheduled(cron = "0 10 6-20 * * ?")
	public void updateSingleActivityProductInfoTask() {
		try {
			activityAreaService.updateSingleActivityProductInfoTask();
		} catch (Exception e) {
			logger.info("推送异常", e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * 商城的商品，上架状态=上架,库存为零的商品，批量下架
	 * <p>
	 * 每小时执行一次
	 */
	@Scheduled(cron = "0 5 0/1 * * ?")
	public void offProductWhileNoStock() {
		logger.info(DateUtil.getStandardDateTime() + "批量下架商城商品:start");
		productService.offProductWhileNoStock();
		logger.info(DateUtil.getStandardDateTime() + "批量下架商城商品:end");
	}

	/**
	 * 方法描述 ：活动商品加水印
	 *
	 * @author
	 * @date
	 * @version
	 */
	// @Scheduled(cron = "0 0/30 * * * ?")
	public void updateActivityProductPicWatermark() {
		SysParamCfgExample sysParamCfgExample = new SysParamCfgExample();
		sysParamCfgExample.createCriteria().andParamCodeEqualTo("ACTIVITY_IDS_2_WATERMARK");
		List<SysParamCfg> sysParamCfgs = sysParamCfgService.selectByExample(sysParamCfgExample);
		if (sysParamCfgs != null && sysParamCfgs.size() > 0 && !StringUtil.isEmpty(sysParamCfgs.get(0).getParamValue())) {
			String watermarkPicPath = sysParamCfgs.get(0).getParamDesc();
			String[] activityAreaIdArr = sysParamCfgs.get(0).getParamValue().split(",");
			for (int i = 0; i < activityAreaIdArr.length; i++) {
				watermarkProductMainPic(Integer.valueOf(activityAreaIdArr[i]), watermarkPicPath);
			}
		}

	}

	public synchronized void watermarkProductMainPic(Integer activityAreaId, String watermarkPicPath) {

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
						// activityProduct4Update.setCreateBy(1);
						activityProduct4Update.setId((Integer) productPicMap.get("activity_product_id"));
						activityProductService.updateByPrimaryKeySelective(activityProduct4Update);
					} catch (IOException e) {
						e.printStackTrace();
					}

				}
			}
		}

	}

	/**
	 * 方法描述 ：单品活动加水印
	 *
	 * @author
	 * @date
	 * @version
	 */
//	 @Scheduled(cron = "0 30 0/1 * * ?")
	public void updateSingleActivityProductPicWatermark() {
		SysParamCfgExample sysParamCfgExample = new SysParamCfgExample();
		sysParamCfgExample.createCriteria().andParamCodeEqualTo("SINGLE_ACTIVITY_TYPE_2_WATERMARK");
		List<SysParamCfg> sysParamCfgs = sysParamCfgService.selectByExample(sysParamCfgExample);
		if (sysParamCfgs != null && sysParamCfgs.size() > 0) {
			for (SysParamCfg sysParamCfg : sysParamCfgs) {
				watermarkSingleActivityProductMainPic(sysParamCfg.getParamValue(), sysParamCfg.getParamDesc());
			}
		}

	}

	public synchronized void watermarkSingleActivityProductMainPic(String activityType, String watermarkPicPath) {

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("activityType", activityType);

		List<Map<String, Object>> productPicMaps = productCustomMapper.getNoWatermarkSingleActivityProductPic(params);
		List<Integer> singleActivityIdList = new ArrayList<Integer>();
		int totalcount = productPicMaps.size();
		int currentIndex = 0;
		if (productPicMaps != null && productPicMaps.size() > 0) {
			for (Map<String, Object> productPicMap : productPicMaps) {
				String pic = (String) productPicMap.get("pic");
				String sourcePic = pic.substring(0, pic.lastIndexOf(".")) + "_" + "70Q" + pic.substring(pic.lastIndexOf("."));
				String sourcePicM = pic.substring(0, pic.lastIndexOf(".")) + "_" + "M" + pic.substring(pic.lastIndexOf("."));
				String target70QMkPic = pic.substring(0, pic.lastIndexOf(".")) + "_70Q_WM_" + pic.substring(pic.lastIndexOf("."));
				String targetMMkPic = pic.substring(0, pic.lastIndexOf(".")) + "_M_WM_" + pic.substring(pic.lastIndexOf("."));
				String watermarkPic = watermarkPicPath + "aa_" + activityType + ".png";
				String watermarkPicM = watermarkPicPath + "aa_m_" + activityType + ".png";
				try {
					FileUtil.imgWatermark(sourcePic, target70QMkPic, Positions.TOP_RIGHT, watermarkPic, 1);
					FileUtil.imgWatermark(sourcePicM, targetMMkPic, Positions.BOTTOM_LEFT, watermarkPicM, 1);
				} catch (IOException e) {
					e.printStackTrace();
				}
				if (!singleActivityIdList.contains((Integer) productPicMap.get("id"))) {
					singleActivityIdList.add((Integer) productPicMap.get("id"));
				}
				currentIndex++;
				System.out.println("单品活动打标" + currentIndex + "/" + totalcount + "-----" + pic);
			}

			params.put("singleAtivityIds", singleActivityIdList);
			productCustomMapper.batchUpdateSingleActivityWatermark(params);

		}

	}

	/**
	 * 方法描述 ：每天更新一次单品活动（砍价，营销方案）虚拟数 (每天凌晨3:10执行一次)
	 *
	 * @author chenwf:
	 * @date 创建时间：2018年8月10日 上午11:38:28
	 * @version
	 */
	@Scheduled(cron = "0 10 3 * * ?")
	public synchronized void updateSingleActivityUnrealityNum() {
		logger.info("更新虚拟数:start");
		try {
			singleProductActivityService.updateSingleActivityUnrealityNum();
		} catch (Exception e) {
			logger.info("更新虚拟数:异常", e.getMessage());
			e.printStackTrace();
		}
		logger.info("更新虚拟数:end");
	}

	/**
	 * 单品款在没活动时，上架状态要更改为下架（商家自主下架）
	 * 每天1:00 和 10:15 各执行一次
	 */
	@Scheduled(cron = "0 0 1 * * ?")
	public void offlineClosedSingleProduct1() {
		doOfflineClosedSingleProduct();
	}

	/**
	 * 单品款在没活动时，上架状态要更改为下架（商家自主下架）
	 * 每天1:00 和 10:15 各执行一次
	 */
	@Scheduled(cron = "0 15 10 * * ?")
	public void offlineClosedSingleProduct2() {
		doOfflineClosedSingleProduct();
	}

	private void doOfflineClosedSingleProduct() {
		logger.info("下架活动结束单品款:start");
		try {
			singleProductActivityService.offlineClosedSingleProduct();
		} catch (Exception e) {
			logger.error("下架活动结束单品款异常:{}", e.getMessage(), e);
		}
		logger.info("下架活动结束单品款:end");
	}


	/**
	 * 每天凌晨00:00统计每个类目下的商品数量
	 */
	@Scheduled(cron = "0 0 0 * * ?")
	public synchronized void getNumberOfProductTypeTask() {
		logger.info(DateUtil.getStandardDateTime() + "开始统计每个类目下商品数量:start");
		productTypeStatisticsService.updateProductNumOfType();
		logger.info(DateUtil.getStandardDateTime() + "结束统计每个类目下商品数量:end");
	}


	/**
	 * 每天凌晨03:32执行商品统计
	 */
	@Scheduled(cron = "0 32 3 * * ?")
	public synchronized void productStatisticsInfo() {
		logger.info(DateUtil.getStandardDateTime() + "开始商品统计:start");
		try {
			countNewProduct();
			updateProductStatisticsList();
		} catch (Exception e) {
			logger.error("商品统计异常:{}", e.getMessage(), e);
		}
		logger.info(DateUtil.getStandardDateTime() + "结束商品统计:start");
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

	/**
	 * 商家推荐
	 */
/*	@Scheduled(cron = "0 0 3 * * ?")
	public synchronized void addMchtShopDynamic() {
		logger.info(DateUtil.getStandardDateTime() + "开始商家推荐:start");
		try {
			productService.addMchtShopDynamic();
		} catch (Exception e) {
			logger.error("商家推荐异常:{}", e.getMessage(), e);
		}
		logger.info(DateUtil.getStandardDateTime() + "结束商家推荐:start");
	}*/


}
