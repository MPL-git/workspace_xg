package com.jf.service;

import com.jf.common.base.BaseService;
import com.jf.dao.ProductTypeCustomMapper;
import com.jf.dao.ProductTypeStatisticsMapper;
import com.jf.entity.ProductTypeCustom;
import com.jf.entity.ProductTypeStatistics;
import com.jf.entity.ProductTypeStatisticsExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class ProductTypeStatisticsService extends BaseService<ProductTypeStatistics, ProductTypeStatisticsExample> {

    @Autowired
    private ProductTypeStatisticsMapper productTypeStatisticsMapper;

    @Autowired
    private ProductTypeCustomMapper productTypeCustomMapper;

    @Autowired
    public void setActivityMapper(ProductTypeStatisticsMapper productTypeStatisticsMapper) {
        this.setDao(productTypeStatisticsMapper);
        this.productTypeStatisticsMapper = productTypeStatisticsMapper;
    }

    public void updateProductNumOfType(){

        ProductTypeStatisticsExample productTypeStatisticsExample = new ProductTypeStatisticsExample();
        productTypeStatisticsExample.createCriteria().andDelFlagEqualTo("0");
        ProductTypeStatistics productTypeStatistics4Update = new ProductTypeStatistics();
        productTypeStatistics4Update.setDelFlag("1");
        productTypeStatisticsMapper.updateByExampleSelective(productTypeStatistics4Update,productTypeStatisticsExample);

        List<ProductTypeCustom> numberOfProductTypeList = productTypeCustomMapper.getNumberOfProductType();
        if(numberOfProductTypeList != null){
            for (ProductTypeCustom productTypeCustom : numberOfProductTypeList) {
                productTypeStatisticsExample = new ProductTypeStatisticsExample();
                productTypeStatisticsExample.createCriteria().andProductTypeIdEqualTo(productTypeCustom.getId());
                productTypeStatistics4Update = new ProductTypeStatistics();
                productTypeStatistics4Update.setDelFlag("0");
                productTypeStatistics4Update.setUpdateDate(new Date());
                productTypeStatistics4Update.setProductNum(productTypeCustom.getNumber());
                int count = productTypeStatisticsMapper.updateByExampleSelective(productTypeStatistics4Update, productTypeStatisticsExample);
                if(count>0){
                    continue;
                }

                ProductTypeStatistics productTypeStatistics = new ProductTypeStatistics();
                productTypeStatistics.setDelFlag("0");
                productTypeStatistics.setCreateDate(new Date());
                productTypeStatistics.setProductTypeId(productTypeCustom.getId());
                if(productTypeCustom.getNumber()==null){
                    productTypeStatistics.setProductNum(0);
                }else {
                    productTypeStatistics.setProductNum(productTypeCustom.getNumber());
                }
                productTypeStatisticsMapper.insertSelective(productTypeStatistics);

            }
        }

    }
}
