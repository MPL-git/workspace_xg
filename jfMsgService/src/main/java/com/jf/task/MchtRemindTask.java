package com.jf.task;

import com.jf.common.constant.Const;
import com.jf.common.ext.RegCondition;
import com.jf.common.ext.query.QueryObject;
import com.jf.common.ext.util.StrKit;
import com.jf.entity.MchtBrandChg;
import com.jf.entity.MchtInfo;
import com.jf.entity.MchtInfoChg;
import com.jf.entity.MchtInfoExample;
import com.jf.entity.MchtProductBrandExt;
import com.jf.entity.MchtProductBrandExtExample;
import com.jf.service.MchtBrandChgService;
import com.jf.service.MchtContactService;
import com.jf.service.MchtInfoChgService;
import com.jf.service.MchtInfoService;
import com.jf.service.MchtPlatformContactService;
import com.jf.service.MchtProductBrandService;
import com.jf.service.MchtUserService;
import com.jf.service.PlatformMsgService;
import com.jf.service.SmsService;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * 商家提醒任务
 *
 * @auther hdl
 */
@RegCondition
@Component
public class MchtRemindTask {

    private static Logger logger = LoggerFactory.getLogger(MchtRemindTask.class);

    @Autowired
    private MchtInfoService mchtInfoService;
    @Autowired
    private MchtProductBrandService mchtProductBrandService;
    @Autowired
    private PlatformMsgService platformMsgService;
    @Autowired
    private MchtUserService mchtUserService;
    @Autowired
    private MchtContactService mchtContactService;
    @Autowired
    private MchtPlatformContactService mchtPlatformContactService;
    @Autowired
    private SmsService smsService;
    @Autowired
    private MchtInfoChgService mchtInfoChgService;
    @Autowired
    private MchtBrandChgService mchtBrandChgService;



    /**
     * 资质到期提醒
     *
     * 每天15:00触发
     *
     *
     * 测试用时间0 0/30 9-19 * * ? 9点-19点内每隔5分钟
     */
    @Scheduled(cron="0 0 15 * * ?")
    //@Scheduled(cron="0 0/20 9-19 * * ?")
    public void mchtBrandExpiredRemind(){
        String task = "品牌资质距离到期前30天提醒：授权有效期=（当前时间+30天） && 未申请更新 && 商家合作状态=正常";
        logger.info("任务{}:开始", task);

        mchtBrandWillExpiredRemind(1, 30);
        mchtBrandWillExpiredRemind(2, 30);
        mchtBrandWillExpiredRemind(1, 20);
        mchtBrandWillExpiredRemind(2, 20);
        mchtBrandWillExpiredRemind(1, 15);
        mchtBrandWillExpiredRemind(2, 15);
        mchtBrandWillExpiredRemind(1, 7);
        mchtBrandWillExpiredRemind(2, 7);

        mchtBrandExpiredRemind(1, 1);
        mchtBrandExpiredRemind(2, 1);
        mchtBrandExpiredRemind(1, 8);
        mchtBrandExpiredRemind(2, 8);
        mchtBrandExpiredRemind(1, 15);
        mchtBrandExpiredRemind(2, 15);
        mchtBrandExpiredRemind(1, 22);
        mchtBrandExpiredRemind(2, 22);


        mchtInfoWillExpiredRemind(1, 30);
        mchtInfoWillExpiredRemind(2, 30);
        mchtInfoWillExpiredRemind(1, 20);
        mchtInfoWillExpiredRemind(2, 20);
        mchtInfoWillExpiredRemind(1, 15);
        mchtInfoWillExpiredRemind(2, 15);
        mchtInfoWillExpiredRemind(1, 7);
        mchtInfoWillExpiredRemind(2, 7);

        mchtInfoExpiredRemind(1, 1);
        mchtInfoExpiredRemind(2, 1);
        mchtInfoExpiredRemind(1, 8);
        mchtInfoExpiredRemind(2, 8);
        mchtInfoExpiredRemind(1, 15);
        mchtInfoExpiredRemind(2, 15);
        mchtInfoExpiredRemind(1, 22);
        mchtInfoExpiredRemind(2, 22);

        logger.info("任务{}:结束", task);
    }












    /**
     * 商家材料到期前提醒
     *
     * @param remindType 提醒类型：1、经营许可证到期提醒 2、法人身份证到期提醒
     * @param day   距离到期天数
     */
    private void mchtInfoWillExpiredRemind(int remindType, int day){
        if(remindType != 1 && remindType !=2)   return;

        Date expiredDate = DateTime.now().plusDays(day).withTimeAtStartOfDay().toDate();
        String expiredName = remindType == 1 ? "营业执照" : "法人身份证";

        MchtInfoExample example = new MchtInfoExample();
        MchtInfoExample.Criteria criteria = example.createCriteria();
        if(remindType == 1){
            criteria.andYearlyInspectionDateEqualTo(expiredDate);
        }else if(remindType == 2){
            criteria.andCorporationIdcardDateEqualTo(expiredDate);
        }
        criteria.andStatusEqualTo(Const.MCHT_STATUS_NORMAL);
        criteria.andDelFlagEqualTo(Const.FLAG_FALSE);
        long count = mchtInfoService.countByExample(example);
        if(count == 0){
            logger.info("需要处理的记录为0，退出任务。");
            return;
        }

        logger.info("需要处理的记录为" + count);

        QueryObject queryObject = new QueryObject();
        queryObject.addQuery("example", example);
        List<MchtInfo> list = mchtInfoService.list(queryObject);
        MchtInfoChg mchtInfoChg;
        for(MchtInfo info : list){
            mchtInfoChg = mchtInfoChgService.findLatest(info.getId());
            if(mchtInfoChg != null && !mchtInfoChg.getStatus().equals("3")){
                logger.info("id为【{}】的商家品牌已经申请更新，不用发送提醒。", info.getId());
                continue;
            }

            // 发送站内信
            String title = "关于公司"+ expiredName + "即将到期通知";
            StringBuffer content = new StringBuffer("您的【" + expiredName + "】有效期将于");
            content.append(new DateTime(remindType == 1 ? info.getYearlyInspectionDate() : info.getCorporationIdcardDate()).toString("MM月dd日") +"到期，");
            content.append("请及时更新，逾期将影响店铺的运营");
            platformMsgService.save(info.getId(), title, content.toString(), "TZ");

            // 发送短信给商家主账号
            String mobile = mchtUserService.findMobileByMchtId(info.getId());
            if(StrKit.notBlank(mobile)){
                smsService.sendImmediately(mobile, content.toString(), "4");
            }

            // 发送短信给商家店铺负责人
            mobile = mchtContactService.findMobile(info.getId(), "1");
            if(StrKit.notBlank(mobile)){
                smsService.sendImmediately(mobile, content.toString(), "4");
            }

            // 发送短信给商家运营专员
            mobile = mchtContactService.findMobile(info.getId(), "2");
            if(StrKit.notBlank(mobile)){
                smsService.sendImmediately(mobile, content.toString(), "4");
            }

            // 发送短信给平台招商对接人
            mobile = mchtPlatformContactService.findMobile(info.getId(), Const.PLAT_CONTACT_TYPE_MERCHANTS);
            content = new StringBuffer("您的对接的商家【").append(info.getMchtCode()).append("】").append(expiredName);
            content.append("有效期将于");
            content.append(new DateTime(remindType == 1 ? info.getYearlyInspectionDate() : info.getCorporationIdcardDate()).toString("MM月dd日") +"到期，");
            content.append("即将到期，请尽快联系商家完成更新事宜。");
            if(StrKit.notBlank(mobile)){
                smsService.sendImmediately(mobile, content.toString(), "4");
            }

            if(day == 7){
                // 发送短信给平台运营对接人
                mobile = mchtPlatformContactService.findMobile(info.getId(), Const.PLAT_CONTACT_TYPE_OPERATION);
                if(StrKit.notBlank(mobile)){
                    smsService.sendImmediately(mobile, content.toString(), "4");
                }
            }

            logger.info("处理id[" + info.getId() + "]的数据成功");
        }

    }

    /**
     * 商家材料到期后提醒
     *
     * @param remindType 提醒类型：1、经营许可证到期提醒 2、法人身份证到期提醒
     * @param day   到期天数
     */
    private void mchtInfoExpiredRemind(int remindType, int day){
        if(remindType != 1 && remindType !=2)   return;

        Date expiredDate = DateTime.now().minusDays(day).withTimeAtStartOfDay().toDate();
        String expiredName = remindType == 1 ? "营业执照" : "法人身份证";

        MchtInfoExample example = new MchtInfoExample();
        MchtInfoExample.Criteria criteria = example.createCriteria();
        if(remindType == 1){
            criteria.andYearlyInspectionDateEqualTo(expiredDate);
        }else if(remindType == 2){
            criteria.andCorporationIdcardDateEqualTo(expiredDate);
        }
        criteria.andStatusEqualTo(Const.MCHT_STATUS_NORMAL);
        criteria.andDelFlagEqualTo(Const.FLAG_FALSE);
        long count = mchtInfoService.countByExample(example);
        if(count == 0){
            logger.info("需要处理的记录为0，退出任务。");
            return;
        }

        logger.info("需要处理的记录为" + count);

        QueryObject queryObject = new QueryObject();
        queryObject.addQuery("example", example);
        List<MchtInfo> list = mchtInfoService.list(queryObject);
        MchtInfoChg mchtInfoChg;
        for(MchtInfo info : list){
            mchtInfoChg = mchtInfoChgService.findLatest(info.getId());
            if(mchtInfoChg != null && !mchtInfoChg.getStatus().equals("3")){
                logger.info("id为【{}】的商家品牌已经申请更新，不用发送提醒。", info.getId());
                continue;
            }

            // 发送站内信
            String title = "关于公司" + expiredName + "到期通知";
            StringBuffer content = new StringBuffer("您的").append(expiredName);
            content.append("有效期已于").append(new DateTime(remindType == 1 ? info.getYearlyInspectionDate() : info.getCorporationIdcardDate()).toString("MM月dd日") +"过期，");
            content.append("，根据平台规定相关规定将暂停店铺运营");
            platformMsgService.save(info.getId(), title, content.toString(), "TZ");

            // 发送短信给商家主账号
            String mobile = mchtUserService.findMobileByMchtId(info.getId());
            if(StrKit.notBlank(mobile)){
                smsService.sendImmediately(mobile, content.toString(), "4");
            }

            // 发送短信给商家店铺负责人
            mobile = mchtContactService.findMobile(info.getId(), "1");
            if(StrKit.notBlank(mobile)){
                smsService.sendImmediately(mobile, content.toString(), "4");
            }

            // 发送短信给商家运营专员
            mobile = mchtContactService.findMobile(info.getId(), "2");
            if(StrKit.notBlank(mobile)){
                smsService.sendImmediately(mobile, content.toString(), "4");
            }

            // 发送短信给平台运营对接人
            mobile = mchtPlatformContactService.findMobile(info.getId(), Const.PLAT_CONTACT_TYPE_OPERATION);
            content = new StringBuffer("您的对接的商家【").append(info.getMchtCode()).append("】的").append(expiredName);
            content.append("有效期已于").append(new DateTime(remindType == 1 ? info.getYearlyInspectionDate() : info.getCorporationIdcardDate()).toString("MM月dd日") +"过期，");
            content.append("请联系商家更新信息或者暂停店铺。");
            if(StrKit.notBlank(mobile)){
                smsService.sendImmediately(mobile, content.toString(), "4");
            }


            logger.info("处理id[" + info.getId() + "]的数据成功");
        }

    }

    /**
     * 品牌资质到期前提醒
     *
     * @param remindType 提醒类型：1、平台授权到期提醒 2、其他许可证到期提醒
     * @param day   距离到期天数
     */
    private void mchtBrandWillExpiredRemind(int remindType, int day){
        if(remindType != 1 && remindType !=2)   return;

        Date expiredDate = DateTime.now().plusDays(day).toDate();
        String expiredName = remindType == 1 ? "授权" : "其他许可证";

        MchtProductBrandExtExample example = new MchtProductBrandExtExample();
        MchtProductBrandExtExample.MchtProductBrandExtCriteria criteria = example.createCriteria();
        if(remindType == 1){
            criteria.andPlatformAuthExpDateEqualTo(expiredDate);
        }else if(remindType == 2){
            criteria.andOtherExpDateEqualTo(expiredDate);
        }

        criteria.andMchtStatusEquals(Const.MCHT_STATUS_NORMAL);
        criteria.andDelFlagEqualTo(Const.FLAG_FALSE);
        long count = mchtProductBrandService.count(example);
        if(count == 0){
            logger.info("需要处理的记录为0，退出任务。");
            return;
        }

        logger.info("需要处理的记录为" + count);

        List<MchtProductBrandExt> list = mchtProductBrandService.list(example);
        MchtInfo mchtInfo;
        MchtBrandChg mchtBrandChg;
        for(MchtProductBrandExt info : list){
            mchtBrandChg = mchtBrandChgService.findLatest(info.getId());
            if(mchtBrandChg != null && !mchtBrandChg.getAuditStatus().equals("3")){
                logger.info("id为【{}】的商家品牌已经申请更新，不用发送提醒。", info.getId());
                continue;
            }

            mchtInfo = mchtInfoService.findById(info.getMchtId());
            // 发送站内信
            String title = "关于" + info.getProductBrandName() + expiredName + "即将到期通知";
            StringBuffer content = new StringBuffer("您的【" + info.getProductBrandName() + "】" + expiredName + "有效期将于");
            content.append(new DateTime(remindType == 1 ? info.getPlatformAuthExpDate() : info.getOtherExpDate()).toString("MM月dd日") +"到期，");
            content.append("请及时更新，逾期将影响品牌的运营");
            platformMsgService.save(info.getMchtId(), title, content.toString(), "TZ");

            // 发送短信给商家主账号
            String mobile = mchtUserService.findMobileByMchtId(info.getMchtId());
            content = new StringBuffer("您的【" + mchtInfo.getMchtCode() + "】的");
            content.append("【" + info.getProductBrandName() + "】" + expiredName + "有效期将于");
            content.append(new DateTime(remindType == 1 ? info.getPlatformAuthExpDate() : info.getOtherExpDate()).toString("MM月dd日") +"到期，");
            content.append("请及时更新，逾期将影响品牌的运营");
            if(StrKit.notBlank(mobile)){
                smsService.sendImmediately(mobile, content.toString(), "4");
            }

            // 发送短信给商家店铺负责人
            mobile = mchtContactService.findMobile(info.getMchtId(), "1");
            if(StrKit.notBlank(mobile)){
                smsService.sendImmediately(mobile, content.toString(), "4");
            }

            // 发送短信给商家运营专员
            mobile = mchtContactService.findMobile(info.getMchtId(), "2");
            if(StrKit.notBlank(mobile)){
                smsService.sendImmediately(mobile, content.toString(), "4");
            }

            // 发送短信给平台招商对接人
            mobile = mchtPlatformContactService.findMobile(info.getMchtId(), Const.PLAT_CONTACT_TYPE_MERCHANTS);
            content = new StringBuffer("您的对接的商家【").append(mchtInfo.getMchtCode()).append("】");
            content.append("的【").append(info.getProductBrandName()).append("】").append(expiredName);
            content.append("即将到期，请及时的联系商家更新品牌信息");
            if(StrKit.notBlank(mobile)){
                smsService.sendImmediately(mobile, content.toString(), "4");
            }

            if(day == 7){
                // 发送短信给平台运营对接人
                mobile = mchtPlatformContactService.findMobile(info.getMchtId(), Const.PLAT_CONTACT_TYPE_OPERATION);
                content = new StringBuffer("您的对接的商家【").append(mchtInfo.getMchtCode()).append("】");
                content.append("的【").append(info.getProductBrandName()).append("】").append(expiredName);
                content.append("将于").append(new DateTime(remindType == 1 ? info.getPlatformAuthExpDate() : info.getOtherExpDate()).toString("MM月dd日"));
                content.append("过期，请尽快联系商家更新品牌信息。");
                if(StrKit.notBlank(mobile)){
                    smsService.sendImmediately(mobile, content.toString(), "4");
                }
            }

            logger.info("处理id[" + info.getId() + "]的数据成功");
        }

    }

    /**
     * 品牌资质到期后提醒
     *
     * @param remindType 提醒类型：1、平台授权到期提醒 2、其他许可证到期提醒
     * @param day   到期天数
     */
    private void mchtBrandExpiredRemind(int remindType, int day){
        if(remindType != 1 && remindType !=2)   return;

        Date expiredDate = DateTime.now().minusDays(day).toDate();
        String expiredName = remindType == 1 ? "授权" : "其他许可证";

        MchtProductBrandExtExample example = new MchtProductBrandExtExample();
        MchtProductBrandExtExample.MchtProductBrandExtCriteria criteria = example.createCriteria();
        if(remindType == 1){
            criteria.andPlatformAuthExpDateEqualTo(expiredDate);
        }else if(remindType == 2){
            criteria.andOtherExpDateEqualTo(expiredDate);
        }

        criteria.andMchtStatusEquals(Const.MCHT_STATUS_NORMAL);
        criteria.andDelFlagEqualTo(Const.FLAG_FALSE);
        long count = mchtProductBrandService.count(example);
        if(count == 0){
            logger.info("需要处理的记录为0，退出任务。");
            return;
        }

        logger.info("需要处理的记录为" + count);

        List<MchtProductBrandExt> list = mchtProductBrandService.list(example);
        MchtInfo mchtInfo;
        MchtBrandChg mchtBrandChg;
        for(MchtProductBrandExt info : list){
            mchtBrandChg = mchtBrandChgService.findLatest(info.getId());
            if(mchtBrandChg != null && !mchtBrandChg.getAuditStatus().equals("3")){
                logger.info("id为【{}】的商家品牌已经申请更新，不用发送提醒。", info.getId());
                continue;
            }

            mchtInfo = mchtInfoService.findById(info.getMchtId());
            // 发送站内信
            String title = "关于" + info.getProductBrandName() + expiredName + "到期通知";
            StringBuffer content = new StringBuffer("您的【" + info.getProductBrandName() + "】").append(expiredName);
            content.append("有效期已于").append(new DateTime(remindType == 1 ? info.getPlatformAuthExpDate() : info.getOtherExpDate()).toString("MM月dd日") +"过期，");
            content.append("根据平台相关规定将暂停品牌运营");
            platformMsgService.save(info.getMchtId(), title, content.toString(), "TZ");

            // 发送短信给商家主账号
            String mobile = mchtUserService.findMobileByMchtId(info.getMchtId());
            if(StrKit.notBlank(mobile)){
                smsService.sendImmediately(mobile, content.toString(), "4");
            }

            // 发送短信给商家店铺负责人
            mobile = mchtContactService.findMobile(info.getMchtId(), "1");
            if(StrKit.notBlank(mobile)){
                smsService.sendImmediately(mobile, content.toString(), "4");
            }

            // 发送短信给商家运营专员
            mobile = mchtContactService.findMobile(info.getMchtId(), "2");
            if(StrKit.notBlank(mobile)){
                smsService.sendImmediately(mobile, content.toString(), "4");
            }

            // 发送短信给平台运营对接人
            mobile = mchtPlatformContactService.findMobile(info.getMchtId(), Const.PLAT_CONTACT_TYPE_OPERATION);
            content = new StringBuffer("您的对接的商家【").append(mchtInfo.getMchtCode()).append("】");
            content.append("的【").append(info.getProductBrandName()).append("】").append(expiredName);
            content.append("已到期，请及时的联系商家更新品牌信息或操停品牌。");
            if(StrKit.notBlank(mobile)){
                smsService.sendImmediately(mobile, content.toString(), "4");
            }


            logger.info("处理id[" + info.getId() + "]的数据成功");
        }

    }



    /**
     * 模板
     */
    /*public void template(){
        String task = "任务名称";
        logger.info("任务{}:开始", task);

        long count = 10;
        if(count == 0){
            logger.info("需要处理的记录为0，退出任务。");
            return;
        }

        logger.info("需要处理的记录为" + count);
        while (true){
            List<MchtInfo> list = new ArrayList<>();
            if(list.size() == 0)    break;

            for(MchtInfo info : list){
                // 在这里处理任务

                logger.info("处理id[" + info.getId() + "]的数据成功");
            }
        }

        logger.info("任务{}:结束", task);
    }*/

}
