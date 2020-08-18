package com.jf.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jf.common.constant.Const;
import com.jf.common.utils.StringUtil;
import com.jf.common.utils.mwsms.MwSmsUtil;
import com.jf.dao.TaskSendMemberCustomMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 发送营销短信
 */
@Service
public class TaskSendSmsService {

    private static Logger logger = LoggerFactory.getLogger(TaskSendSmsService.class);

    @Autowired
    private TaskSendMemberCustomMapper taskSendMemberCustomMapper;

    public void sendTaskMwSmsList(List<Map<String, Object>> resultMapList, String seqNum) {
        String taskSendIds = "";
        String mobiles = "";
        for (Map<String, Object> stringObjectMap : resultMapList) {
            taskSendIds += stringObjectMap.get("taskSendId")+",";
            mobiles += stringObjectMap.get("mobile")+",";
        }
        String content = resultMapList.get(0).get("content").toString();
        String result = MwSmsUtil.sendMwSms(mobiles, content, seqNum, Const.MW_TASK_ACCOUNT, Const.MW_BATCH_SEND);
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("taskSendIdList", taskSendIds.substring(0, taskSendIds.length() - 1).split(","));
        if (!StringUtil.isEmpty(result)) {
            JSONObject resultObj = JSON.parseObject(result);
            if(resultObj.getInteger("result") == 0){//成功
                paramMap.put("status", "1");
                paramMap.put("seqNum", resultObj.getString("custid"));
            }else {
                paramMap.put("status", "3");
                logger.info("梦网短信发送失败 返回值:" + result);
            }
        }else {
            logger.info("梦网短信发送失败 不存在返回值!");
        }
        taskSendMemberCustomMapper.updateTaskSendMemberList(paramMap);
    }

}
