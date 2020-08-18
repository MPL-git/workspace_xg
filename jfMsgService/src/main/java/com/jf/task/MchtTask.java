package com.jf.task;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import com.jf.common.ext.RegCondition;
import net.coobird.thumbnailator.geometry.Positions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.jf.common.utils.DateUtil;
import com.jf.common.utils.FileUtil;
import com.jf.entity.MchtBlPic;
import com.jf.entity.MchtBlPicExample;
import com.jf.entity.MchtInfo;
import com.jf.entity.MchtInfoExample;
import com.jf.entity.MchtProductBrand;
import com.jf.entity.MchtProductBrandExample;
import com.jf.service.MchtBlPicService;
import com.jf.service.MchtInfoService;
import com.jf.service.MchtProductBrandService;

@RegCondition
@Component
public class MchtTask {
    private static Logger logger = LoggerFactory.getLogger(MchtTask.class);
    
    @Resource
    private MchtBlPicService mchtBlPicService;
    @Resource
    private MchtInfoService mchtInfoService;
    @Resource
    private MchtProductBrandService mchtProductBrandService;
    
    @Scheduled(cron="0 15 2 * * ?")
    public synchronized void waterMarkBlPic(){
        logger.info("商家营业执照生成水印图 :start");
        
        List<MchtBlPic> mchtBlPics =  mchtBlPicService.selectNoWatermarkBlPic();
        List<Integer> idList = new ArrayList<Integer>();
        for (MchtBlPic mchtBlPic:mchtBlPics) {
        	
        	try {
				String pic = mchtBlPic.getPic();
				String sourcePic = pic;
				String targetMkPic = pic.substring(0, pic.lastIndexOf(".")) + "_WM" + pic.substring(pic.lastIndexOf("."));
				String watermarkPic = "/watermark/mcht_bl_pic_WK.png";
				FileUtil.imgWatermarkAndReSize(sourcePic, targetMkPic, Positions.TOP_LEFT, watermarkPic, 1,750,2000);
				idList.add(mchtBlPic.getId());
			} catch (Exception e) {
				e.printStackTrace();
			}
        	
		}

    	if(idList.size()>0){
    		MchtBlPicExample mchtBlPicExample = new MchtBlPicExample();
    		mchtBlPicExample.createCriteria().andIdIn(idList);
    		MchtBlPic mchtBlPic4Update = new MchtBlPic();
    		mchtBlPic4Update.setIsWatermark("1");
    		mchtBlPicService.updateByExampleSelective(mchtBlPic4Update, mchtBlPicExample);
    	}
    	
        logger.info("商家营业执照生成水印图 :end");
    }
    
    /**
     * 营业执照有效期<=当前时间+35天时，自动触发一站内信及短信   每周一给符合该条件发送短信及站内信
     *
     *
     */
//    @Scheduled(cron="0 0 3 ? * MON")
    public void sendMchtMsg(){
    	logger.info("营业执照有效期<=当前时间+35天时，自动触发一站内信及短信   每周一给符合该条件发送短信及站内信,任务:start");
    	Date limitDate = DateUtil.getDateAfter(new Date(), 35);
    	MchtInfoExample e = new MchtInfoExample();
    	e.createCriteria().andDelFlagEqualTo("0").andStatusEqualTo("1").andDelayStatusEqualTo("0").andYearlyInspectionDateLessThanOrEqualTo(limitDate);
    	List<MchtInfo> list = mchtInfoService.selectByExample(e);
    	for(MchtInfo mchtInfo:list){
    		mchtInfoService.sendMsg(mchtInfo);
    	}
    	logger.info("营业执照有效期<=当前时间+35天时，自动触发一站内信及短信   每周一给符合该条件发送短信及站内信,任务:end");
    }
    
    /**
     * 营业执照有效期到+延期天数到期后，需自动将店铺的合作状态更改为【暂停】：每天晚上2点自动运行一次
     *
     *
     */
//    @Scheduled(cron="0 0 2 * * ?")
    public void pauseMchtStatus(){
        logger.info("营业执照有效期到+延期天数到期后，需自动将店铺的合作状态更改为【暂停】任务:start");
        Date limitDate = DateUtil.getDateAfter(new Date(), -30);
        MchtInfoExample e = new MchtInfoExample();
        e.createCriteria().andDelFlagEqualTo("0").andStatusEqualTo("1").andDelayStatusEqualTo("1").andYearlyInspectionDateLessThan(limitDate);
        List<MchtInfo> list = mchtInfoService.selectByExample(e);
        for(MchtInfo mchtInfo:list){
        	mchtInfo.setUpdateDate(new Date());
        	mchtInfo.setStatus("2");
        	mchtInfo.setStatusDate(new Date());
        	mchtInfoService.updateByPrimaryKeySelective(mchtInfo);
        }
        Date limitDateNow = new Date();
        MchtInfoExample mie = new MchtInfoExample();
        mie.createCriteria().andDelFlagEqualTo("0").andStatusEqualTo("1").andDelayStatusEqualTo("0").andYearlyInspectionDateLessThan(limitDateNow);
        List<MchtInfo> mchtInfoList = mchtInfoService.selectByExample(mie);
        for(MchtInfo mchtInfo:mchtInfoList){
        	mchtInfo.setStatus("2");
        	mchtInfo.setStatusDate(limitDateNow);
        	mchtInfo.setUpdateDate(limitDateNow);
        	mchtInfoService.updateByPrimaryKeySelective(mchtInfo);
        }
        logger.info("营业执照有效期到+延期天数到期后，需自动将店铺的合作状态更改为【暂停】任务:end");
    }
    
    /**
     * 授权有效期<=当前时间+35天时，自动触发一站内信及短信, 每周一自动触发短信及站内信发送
     *
     *
     */
//    @Scheduled(cron="0 0 3 ? * MON")
    public void sendMchtBrandMsg(){
    	logger.info("授权有效期<=当前时间+35天时，自动触发一站内信及短信, 每周一自动触发短信及站内信发送,任务:start");
    	Date limitDate = DateUtil.getDateAfter(new Date(), 35);
    	MchtProductBrandExample e = new MchtProductBrandExample();
    	e.createCriteria().andDelFlagEqualTo("0").andStatusEqualTo("1").andDelayStatusEqualTo("0").andPlatformAuthExpDateLessThanOrEqualTo(limitDate);
    	List<MchtProductBrand> list = mchtProductBrandService.selectByExample(e);
    	for(MchtProductBrand mchtProductBrand:list){
    		mchtProductBrandService.sendMsg(mchtProductBrand);
    	}
    	logger.info("授权有效期<=当前时间+35天时，自动触发一站内信及短信, 每周一自动触发短信及站内信发送,任务:end");
    }
    
    /**
     * 品牌的申请有效期+延期时间到后，需自动将品牌的运营状态更改为暂停   每天晚上2点自动运行一次
     *
     *
     */
//    @Scheduled(cron="0 0 2 * * ?")
    public void pauseMchtBrandStatus(){
    	logger.info("品牌的申请有效期+延期时间到后，需自动将品牌的运营状态更改为【暂停】任务:start");
    	Date limitDate = DateUtil.getDateAfter(new Date(), -30);
    	MchtProductBrandExample e = new MchtProductBrandExample();
    	e.createCriteria().andDelFlagEqualTo("0").andStatusEqualTo("1").andDelayStatusEqualTo("1").andPlatformAuthExpDateLessThan(limitDate);
    	List<MchtProductBrand> list = mchtProductBrandService.selectByExample(e);
    	for(MchtProductBrand mchtProductBrand:list){
    		mchtProductBrand.setUpdateDate(new Date());
    		mchtProductBrand.setStatus("2");
    		mchtProductBrandService.updateByPrimaryKeySelective(mchtProductBrand);
    	}
    	Date limitDateNow = new Date();
    	MchtProductBrandExample mpbe = new MchtProductBrandExample();
    	mpbe.createCriteria().andDelFlagEqualTo("0").andStatusEqualTo("1").andDelayStatusEqualTo("0").andPlatformAuthExpDateLessThan(limitDateNow);
    	List<MchtProductBrand> mchtProductBrandList = mchtProductBrandService.selectByExample(mpbe);
    	Date now = new Date();
    	for(MchtProductBrand mchtProductBrand:mchtProductBrandList){
    		mchtProductBrand.setStatus("2");
    		mchtProductBrand.setStatusDate(now);
    		mchtProductBrand.setUpdateDate(now);
    		mchtProductBrandService.updateByPrimaryKeySelective(mchtProductBrand);
    	}
    	logger.info("品牌的申请有效期+延期时间到后，需自动将品牌的运营状态更改为【暂停】任务:end");
    }
}
