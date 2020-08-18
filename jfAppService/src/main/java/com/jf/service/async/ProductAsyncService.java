package com.jf.service.async;

import com.google.common.collect.Maps;
import com.jf.common.constant.StateConst;
import com.jf.dao.ProductCustomMapper;
import com.jf.entity.ProductExample;
import com.jf.service.ProductItemService;
import com.jf.service.ProductService;
import com.jf.vo.request.PageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;

/**
 * @author luoyb
 * Created on 2019/12/7
 */
@Service
public class ProductAsyncService {

    @Autowired
    private ProductService productService;
    @Autowired
    private ProductCustomMapper productCustomMapper;
    @Autowired
    private ProductItemService productItemService;

    public int countOnlineProduct() {
        ProductExample example = new ProductExample();
        example.createCriteria().andStatusEqualTo("1").andDelFlagEqualTo(StateConst.FALSE).andIsSinglePropEqualTo(StateConst.FALSE);
        return productService.countByExample(example);
    }

    public List<Integer> findOnlineProductIds(PageRequest pageRequest) {
        Map<String, Object> params = Maps.newHashMap();
        params.put("offset", pageRequest.getOffset());
        params.put("fetchSize", pageRequest.getPageSize());
        return productCustomMapper.findOnlineProductIds(params);
    }

    public int bulkUpdateProductMinAmountColumns(List<Integer> productIds) {
        if (CollectionUtils.isEmpty(productIds)) return 0;

        int count = 0;
        for (Integer productId : productIds) {
            if (productItemService.updateMinProductItemPrice(productId) == 1) {
                count++;
            }
        }
        return count;
    }
}
