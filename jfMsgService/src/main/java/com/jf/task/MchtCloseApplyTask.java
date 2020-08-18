package com.jf.task;

import com.jf.common.constant.Const;
import com.jf.common.ext.RegCondition;
import com.jf.common.ext.query.QueryObject;
import com.jf.entity.MchtCloseApply;
import com.jf.entity.MchtContract;
import com.jf.service.MchtCloseApplyService;
import com.jf.service.MchtContractService;
import com.jf.service.MchtInfoService;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 自动关店相关任务

 * @auther hdl
 */
@RegCondition
@Component
public class MchtCloseApplyTask {

    private static Logger logger = LoggerFactory.getLogger(MchtCloseApplyTask.class);

    @Resource
    private MchtContractService mchtContractService;
    @Resource
    private MchtInfoService mchtInfoService;
    @Resource
    private MchtCloseApplyService mchtCloseApplyService;

    /**
     * 自动关店任务
     * 执行时间：每天凌晨4:15分
     *
     * 商家开店日期 小于 今天 减30天 且 商家合作状态 = 正常  且  本合同分类=新签 且 本合同归档状态=未处理
     * 执行：本合同归档状态=不签约，商家合作状态 = 暂停
     * 同时 创建一条 关店申请：
     *
     */
//    @Scheduled(cron="0 15 4 * * ?")
    public void autoCloseTask(){
        logger.info("自动关店任务:start");

        QueryObject queryObject = new QueryObject();
        //查询合同
        queryObject.addQuery("contractType", Const.MCHT_CONTRACT_TYPE_NEW);
        queryObject.addQuery("archiveStatus", Const.MCHT_CONTRACT_ARCHIVE_STATUS_WAIT);
        //查询商家
        queryObject.addQuery("mchtInfoStatus", Const.MCHT_STATUS_NORMAL);
        queryObject.addQuery("mchtInfoCooperateBeginDateBefore", DateTime.now().minusDays(30).toDate());

        List<MchtContract> list = mchtContractService.list(queryObject);
        for(MchtContract contract : list){
            mchtInfoService.stop(null, contract.getMchtId(), "开店30天，合同未归档");
            mchtCloseApplyService.autoCreate(contract.getMchtId());
        }

        logger.info("自动关店任务:end");
    }

    /**
     * 关店申请执行任务
     * 执行时间：每天凌晨4:30分
     */
    @Scheduled(cron="0 30 4 * * ?")
    public void integralDailyStatisticTask(){
        logger.info("关店申请执行任务:start");

        Date todayBegin = DateTime.now().millisOfDay().withMinimumValue().toDate();
        Date todayEnd = DateTime.now().millisOfDay().withMaximumValue().toDate();

        /**
         * 1.执行：暂停
         *  前提：关店申请-运营招商都同意  且 商家合作状态=正常 且 暂停日期1=今天
         *  执行  商家合作状态=暂停，执行成功后：  暂停日期1 更为 已执行
         */
        QueryObject queryObject = new QueryObject();
        queryObject.addQuery("operateAuditStatus", Const.MCHT_CLOSE_APPLY_AUDIT_STATUS_PASS);   //运营同意
        queryObject.addQuery("merchantsAuditStatus", Const.MCHT_CLOSE_APPLY_AUDIT_STATUS_PASS); //招商同意
        queryObject.addQuery("stopBeginStatus", Const.MCHT_CLOSE_APPLY_EXE_STATUS_NO); //暂停日期1未执行
        queryObject.addQuery("stopBeginDateAfter", todayBegin); // 暂停日期1=今天
        queryObject.addQuery("stopBeginDateBefore", todayEnd);
        queryObject.addQuery("mchtInfoStatus", Const.MCHT_STATUS_NORMAL); //商家合作状态=正常
        List<MchtCloseApply> list = mchtCloseApplyService.list(queryObject);
        for(MchtCloseApply mchtCloseApply : list){
            mchtCloseApplyService.exeStopBegin(mchtCloseApply.getId());
        }


        /**
         * 2.执行：暂停 变为 正常
         * 前提：关店申请-运营招商都同意  且 商家合作状态=暂停 且 暂停日期2=今天
         * 执行  商家合作状态=正常，执行成功后：  暂停日期2 更为 已执行
         */
        queryObject = new QueryObject();
        queryObject.addQuery("operateAuditStatus", Const.MCHT_CLOSE_APPLY_AUDIT_STATUS_PASS);   //运营同意
        queryObject.addQuery("merchantsAuditStatus", Const.MCHT_CLOSE_APPLY_AUDIT_STATUS_PASS); //招商同意
        queryObject.addQuery("stopEndStatus", Const.MCHT_CLOSE_APPLY_EXE_STATUS_NO); //暂停日期2未执行
        queryObject.addQuery("stopEndDateAfter", todayBegin);    // 暂停日期2=今天
        queryObject.addQuery("stopEndDateBefore", todayEnd);
        queryObject.addQuery("mchtInfoStatus", Const.MCHT_STATUS_STOP); //商家合作状态=暂停
        list = mchtCloseApplyService.list(queryObject);
        for(MchtCloseApply mchtCloseApply : list){
            mchtCloseApplyService.exeStopEnd(mchtCloseApply.getId());
        }


        /**
         * 3.执行：暂停 变为 关闭
         前提：关店申请-运营招商都同意  且 （商家合作状态=暂停 OR 正常） 且 关闭日期1=今天
         执行  商家合作状态=关闭，执行成功后：  关闭日期1 更为 已执行
         */
        queryObject = new QueryObject();
        queryObject.addQuery("operateAuditStatus", Const.MCHT_CLOSE_APPLY_AUDIT_STATUS_PASS);   //运营同意
        queryObject.addQuery("merchantsAuditStatus", Const.MCHT_CLOSE_APPLY_AUDIT_STATUS_PASS); //招商同意
        queryObject.addQuery("closeBeginStatus", Const.MCHT_CLOSE_APPLY_EXE_STATUS_NO); //关店日期1未执行
        queryObject.addQuery("closeBeginDateAfter", todayBegin); // 关店日期1=今天
        queryObject.addQuery("closeBeginDateBefore", todayEnd);
        List<String> mchtInfoStatusList = new ArrayList<>();
        mchtInfoStatusList.add(Const.MCHT_STATUS_STOP);
        mchtInfoStatusList.add(Const.MCHT_STATUS_NORMAL);
        queryObject.addQuery("mchtInfoStatusIn", mchtInfoStatusList);
        list = mchtCloseApplyService.list(queryObject);
        for(MchtCloseApply mchtCloseApply : list){
            mchtCloseApplyService.exeCloseBegin(mchtCloseApply.getId());
        }


        /**
         * 4.执行：关闭 变为 正常
         * 前提：关店申请-运营招商都同意  且 商家合作状态=关闭 且 关闭日期2=今天
         * 执行  商家合作状态=正常，执行成功后：  关闭日期2 更为 已执行
         */
        queryObject = new QueryObject();
        queryObject.addQuery("operateAuditStatus", Const.MCHT_CLOSE_APPLY_AUDIT_STATUS_PASS);   //运营同意
        queryObject.addQuery("merchantsAuditStatus", Const.MCHT_CLOSE_APPLY_AUDIT_STATUS_PASS); //招商同意
        queryObject.addQuery("stopEndStatus", Const.MCHT_CLOSE_APPLY_EXE_STATUS_NO); //关店日期2未执行
        queryObject.addQuery("stopEndDateAfter", todayBegin); // 关店日期2=今天
        queryObject.addQuery("stopEndDateBefore", todayEnd);
        queryObject.addQuery("mchtInfoStatus", Const.MCHT_STATUS_CLOSED);
        list = mchtCloseApplyService.list(queryObject);
        for(MchtCloseApply mchtCloseApply : list){
            mchtCloseApplyService.exeCloseEnd(mchtCloseApply.getId());
        }

        logger.info("关店申请执行任务:end");
    }


}
