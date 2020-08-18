package com.jf.controller.async;

import com.jf.common.base.ResponseMsg;
import com.jf.common.utils.StringUtil;
import com.jf.controller.base.BaseController;
import com.jf.service.async.ProductAsyncService;
import com.jf.vo.request.PageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author luoyb
 * Created on 2019/12/7
 */
@Controller
public class ProductAsyncController extends BaseController {

    @Autowired
    private ProductAsyncService productAsyncService;

    /**
     * 初始化商品最小价格冗余字段接口（可重复执行）（慎用，数据量较大）
     */
    @ResponseBody
    @RequestMapping(value = "/async/product/minAmountColumns/fix", method = RequestMethod.PUT)
    public ResponseMsg fixProductMinAmountColumns() {
        int totalCount = productAsyncService.countOnlineProduct();
        if (totalCount <= 0) {
            return ResponseMsg.success("操作成功，当前无数据需要修复");
        }
        int perSearchCount = 5000;
        PageRequest pageRequest = new PageRequest(0, perSearchCount);
        int fixCount = 0;
        do {
            List<Integer> productIds = productAsyncService.findOnlineProductIds(pageRequest);
            fixCount += productAsyncService.bulkUpdateProductMinAmountColumns(productIds);
            totalCount -= perSearchCount;
            pageRequest.setCurrentPage(pageRequest.getCurrentPage() + 1);
        } while (totalCount > 0);
        return ResponseMsg.success(StringUtil.buildMsg("操作成功，本次修复商品数：{}个", fixCount));
    }

}
