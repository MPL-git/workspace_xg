package com.jf.service;

import com.jf.common.base.BaseService;
import com.jf.dao.*;
import com.jf.entity.*;
import com.jf.entity.SourceNicheExample.Criteria;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class SourceNicheService extends BaseService<SourceNiche, SourceNicheExample> {

    @Autowired
    private SourceNicheMapper sourceNicheMapper;

    @Autowired
    private OrderDtlMapper orderDtlMapper;

    @Autowired
    private OrderDtlCustomMapper orderDtlCustomMapper;

    @Autowired
    private MchtInfoService mchtInfoService;

    @Autowired
    private SourceNicheCustomMapper sourceNicheCustomMapper;

    @Autowired
    private ProductTypeMapper productTypeMapper;


    @Autowired
    public void setSourceNicheMapper(SourceNicheMapper sourceNicheMapper) {
        super.setDao(sourceNicheMapper);
        this.sourceNicheMapper = sourceNicheMapper;
    }

    /**
     * @throws ParseException
     * @MethodName operate
     * @Description TODO
     * @author chengh
     * @date 2019年7月5日 下午2:35:12
     */
    public void operate() throws ParseException {
        // TODO Auto-generated method stub
        Calendar now = Calendar.getInstance();
        now.add(Calendar.DAY_OF_MONTH, -30);
        String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(now.getTime());
        Date compareDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(date);
        SourceNicheExample sourceNicheExample = new SourceNicheExample();
        List<String> asList = Arrays.asList("1", "4", "5", "6", "7", "8", "9");
        sourceNicheExample.createCriteria().andStatusEqualTo("0").andAuditStatusEqualTo("1").andDelFlagEqualTo("0").andTypeIn(asList);
        List<SourceNiche> sourceNiches = sourceNicheMapper.selectByExample(sourceNicheExample);

        List<Integer> lessIds = new ArrayList<>();
        List<SourceNiche> sourceNicheList = new ArrayList<>();
        //获取营销活动权重更新时间小于当前时间-30天的数据并把权重值更新为0且更新时间设置为当前时间
        for (SourceNiche sourceNiche : sourceNiches) {
            if (sourceNiche.getWeightUpdateDate() != null) {
                if (compareDate.after(sourceNiche.getWeightUpdateDate()) || compareDate.compareTo(sourceNiche.getWeightUpdateDate()) == 0) {
                    lessIds.add(sourceNiche.getId());
                } else {
                    sourceNicheList.add(sourceNiche);
                }
            }
        }
        //批量设置权重更新时间小于当前时间-30天的数据
        if (!lessIds.isEmpty()) {
            SourceNiche sourceNiche = new SourceNiche();
            sourceNiche.setWeightUpdateDate(new Date());
            sourceNiche.setWeight(0);
            sourceNicheExample = new SourceNicheExample();
            sourceNicheExample.createCriteria().andIdIn(lessIds);
            sourceNicheMapper.updateByExampleSelective(sourceNiche, sourceNicheExample);
        }

        //权重更新时间大于当前时间-30天的数据
        List<SourceNiche> list = new ArrayList<>();
        for (SourceNiche sourceNiche1 : sourceNicheList) {
            SourceNiche sourceNiche = new SourceNiche();
            sourceNiche.setLinkId(sourceNiche1.getLinkId());
            sourceNiche.setWeightUpdateDate(sourceNiche1.getWeightUpdateDate());
            list.add(sourceNiche);
        }
        List<OrderDtlCustom> orderDtlCustom = new ArrayList<>();
        if (!list.isEmpty()) {
            orderDtlCustom = orderDtlCustomMapper.selectSalesVolume(list);
        }

        for (OrderDtlCustom orderDtlCustom2 : orderDtlCustom) {
            for (SourceNiche sourceNiche : sourceNicheList) {
                if (sourceNiche.getLinkId().equals(orderDtlCustom2.getProductId())) {
                    sourceNiche.setWeight(orderDtlCustom2.getSalesVolume() + orderDtlCustom2.getSalesDegree() / 200);
                    sourceNicheMapper.updateByPrimaryKeySelective(sourceNiche);
                }
            }
        }
    }

    public void UpdateSourceNicheAuditStatus() {
        SourceNicheExample sourceNicheExample = new SourceNicheExample();
        Criteria createCriteria = sourceNicheExample.createCriteria();
        List<String> asList = Arrays.asList("1", "4", "5", "6", "7", "8", "9");
        createCriteria.andTypeIn(asList);
        createCriteria.andDelFlagEqualTo("0");//未删除
        createCriteria.andStatusEqualTo("0");//添加
        createCriteria.andAuditStatusEqualTo("0");//未审核
        createCriteria.andUpDateLessThan(new Date());//上线时间小于当前时间
        List<SourceNiche> sourceNicheList = sourceNicheMapper.selectByExample(sourceNicheExample);
        List<Integer> idList = new ArrayList<>();
        SourceNicheExample updateSourceExample = new SourceNicheExample();
        if (sourceNicheList != null && sourceNicheList.size() > 0) {
            for (SourceNiche s : sourceNicheList) {
                idList.add(s.getId());
            }
            updateSourceExample.createCriteria().andIdIn(idList);
        }

        SourceNiche sourceNiche4Update = new SourceNiche();
        sourceNiche4Update.setAuditStatus("2");
        sourceNiche4Update.setAuditDate(new Date());
        if (idList != null && idList.size() > 0) {
            sourceNicheMapper.updateByExampleSelective(sourceNiche4Update, updateSourceExample);
        }
    }


    /**
     * @MethodName shopWeightOperate
     * @Description TODO(每天零点执行一次资源位店铺权重计算)
     * @author yinrd
     * @date 2019年8月8日 下午17:47:39
     */
    public void shopWeightOperate() throws ParseException {
        Calendar now = Calendar.getInstance();
        now.add(Calendar.DAY_OF_MONTH, -300);
        String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(now.getTime());
        Date compareDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(date);
        SourceNicheExample sourceNicheExample = new SourceNicheExample();
        List<String> asList = Arrays.asList("2", "10");
        sourceNicheExample.createCriteria().andStatusEqualTo("0").andAuditStatusEqualTo("1").andDelFlagEqualTo("0").andTypeIn(asList);
        List<SourceNiche> sourceNiches = sourceNicheMapper.selectByExample(sourceNicheExample);//资源位每日好店和大学生创业的所有店铺

        Map<Integer, Object> map = new HashMap<>();
        List<Integer> mchtIdList = new ArrayList<>();
        for (SourceNiche sn : sourceNiches) {
            List<SourceNiche> sourceList = new ArrayList<>();
            Integer mchtId = sn.getMchtId();
            if (mchtId != null) {
                if (mchtIdList.contains(mchtId)) {//商家已经存过
                    List<SourceNiche> list = (List<SourceNiche>) map.get(mchtId);
                    list.add(sn);
                    map.put(mchtId, list);
                    continue;
                }

                mchtIdList.add(mchtId);//商家ID集合
                sourceList.add(sn);//商家对应的资源位
                map.put(mchtId, sourceList);
            }
        }

        //每个店铺的DRS
        Map<Integer, Double> mchtMap = new HashMap<>();
        List<MchtInfoCustom> MchtInfoCustomList = mchtInfoService.findMchtDrs(mchtIdList);
        for (MchtInfoCustom mcht : MchtInfoCustomList) {
            mchtMap.put(mcht.getMchtId(), mcht.getAvgDrs());
        }


        List<OrderDtlCustom> orderDtlCustomList = orderDtlCustomMapper.findSumMoneyAndCustomer(mchtIdList);
        for (OrderDtlCustom odc : orderDtlCustomList) {
            Integer mchtId = odc.getMchtId();

            Integer sumQuantity = 0;
            BigDecimal sumAmount = new BigDecimal("0");
            if (odc.getSumQuantity() != null) {
                sumQuantity = odc.getSumQuantity();//客户数(销量)
            }

            if (odc.getSumAmount() != null) {
                sumAmount = odc.getSumAmount();//销售额
            }
    		
    /*		Integer sumQuantity = odc.getSumQuantity();//客户数(销量)
    		BigDecimal sumAmount = odc.getSumAmount();//销售额
*/
            Integer xInteger = sumQuantity / 10000 > 300 ? 300 : sumQuantity / 10000;//x
            BigDecimal yInteger = (sumAmount.divide(new BigDecimal("100"))).compareTo(new BigDecimal("100")) == 1 ? new BigDecimal("100") : (sumAmount.divide(new BigDecimal("100")));//y
            int intValue = yInteger.setScale(0, BigDecimal.ROUND_DOWN).intValue();
            Double avgDrs = mchtMap.get(mchtId);
            Integer aWeight = (int) ((xInteger + intValue) * avgDrs);
            List<SourceNiche> list = (List<SourceNiche>) map.get(mchtId);
            for (SourceNiche sn : list) {
                sn.setWeight(aWeight);
                sourceNicheMapper.updateByPrimaryKeySelective(sn);
            }
        }
    }

    /**
     * 有好货上线时间超过90天的活动下线
     */
    public void sourceNicheTimingOfTheShelves() {
        Calendar now = Calendar.getInstance();
        now.add(Calendar.DAY_OF_MONTH, -90);
        SourceNicheExample sourceNicheExample = new SourceNicheExample();
        Criteria criteria = sourceNicheExample.createCriteria();
        criteria.andDelFlagEqualTo("0");
        criteria.andTypeEqualTo("1");
        criteria.andStatusEqualTo("0");
        criteria.andAuditStatusEqualTo("1");
        criteria.andUpDateLessThan(now.getTime());
        List<SourceNiche> sourceNiches = sourceNicheMapper.selectByExample(sourceNicheExample);
        if (sourceNiches != null && sourceNiches.size() > 0) {
            ArrayList<Integer> idList = new ArrayList<>();
            for (SourceNiche sourceNich : sourceNiches) {
                idList.add(sourceNich.getId());
            }
            SourceNicheExample sourceNicheExample1 = new SourceNicheExample();
            sourceNicheExample1.createCriteria().andIdIn(idList);
            SourceNiche sourceNiche1 = new SourceNiche();
            sourceNiche1.setStatus("1");
            sourceNicheMapper.updateByExampleSelective(sourceNiche1, sourceNicheExample1);
        }
    }

    /**
     * 添加每个类目权重前300的商品到bu_source_niche
     */
    public void addSourceNicheProduct() {
        //先根据source=3的删除所有定时权重的商品,
        SourceNicheExample record = new SourceNicheExample();
        record.createCriteria().andSourceEqualTo("3");
        SourceNiche delSourceNiche = new SourceNiche();
        delSourceNiche.setDelFlag("1");
        sourceNicheMapper.updateByExampleSelective(delSourceNiche, record);
        //保留历史数据,比如想看前几天的推荐的,按日期和权重和del_flag = 1
        //sourceNicheMapper.deleteByExample(record);
        //然后查询每个类目权重前300的商品
        ProductTypeExample productTypeExample = new ProductTypeExample();
        productTypeExample.createCriteria().andTLevelEqualTo(1);
        //类目等级为1的类目
        List<ProductType> productTypes = productTypeMapper.selectByExample(productTypeExample);
        for (ProductType productType : productTypes) {
            Integer productTypeId = productType.getId();//获取类目的id做条件
            //每个类目权重前三百的商品
            List<Map<String, Object>> weightsProducts = sourceNicheCustomMapper.queryWeightsProduct(productTypeId);
            // 再根据商品id到bu_source_niche取数据,进行修改
            for (Map<String, Object> weightsProduct : weightsProducts) {
                Integer id = (Integer) weightsProduct.get("id");
                Double weights = (Double) weightsProduct.get("newWeights");
                Integer newWeights = weights.intValue();
                Integer mchtId = (Integer) weightsProduct.get("mcht_id");
                SourceNicheExample where = new SourceNicheExample();
                where.createCriteria().andTypeEqualTo("12").andLinkIdEqualTo(id).andDelFlagEqualTo("0");
                List<SourceNiche> sourceNiches = sourceNicheMapper.selectByExample(where);
                if (sourceNiches.size() > 0) {
                    // 如果取到,则存在手动权重,把定时权重的值赋值到手动权重
                    for (SourceNiche sourceNiche : sourceNiches) {
                        sourceNiche.setWeight(newWeights);
                        sourceNicheMapper.updateByPrimaryKeySelective(sourceNiche);
                    }
                } else {
                    // 如果不存在则按定时权重添加进去.
                    SourceNiche sourceNiche = new SourceNiche();
                    sourceNiche.setType("12");
                    sourceNiche.setLinkId(id);
                    sourceNiche.setStatus("0");
                    sourceNiche.setSource("3");
                    sourceNiche.setMchtId(mchtId);
                    sourceNiche.setWeightUpdateDate(new Date());
                    sourceNiche.setWeight(newWeights);
                    sourceNiche.setCreateDate(new Date());
                    sourceNiche.setDelFlag("0");
                    sourceNicheMapper.insertSelective(sourceNiche);
                }
            }
        }
    }

    public void recycleTurntableProduct() {
        List<Map<String, Object>> sourceNicheList = sourceNicheCustomMapper.turntableProductList();
        ArrayList<Integer> recycleList = new ArrayList<Integer>();
        ArrayList<Integer> retainList = new ArrayList<Integer>();
        for (Map<String, Object> sourceNiche : sourceNicheList) {
            int stock = Integer.parseInt(sourceNiche.get("stock").toString());
            int stockSum = Integer.parseInt(sourceNiche.get("stock_sum").toString());
            if (stockSum < 5 || stock > stockSum) {
                recycleList.add(Integer.valueOf(sourceNiche.get("id").toString()));
            }else {
                retainList.add(Integer.valueOf(sourceNiche.get("id").toString()));
            }
        }
        if(CollectionUtils.isNotEmpty(recycleList)){
            sourceNicheCustomMapper.recycleTurntableProduct(recycleList);
            if(CollectionUtils.isNotEmpty(retainList)){
                int i = 1;
                Date date = new Date();
                for (Integer id : retainList) {
                    SourceNiche sourceNiche = new SourceNiche();
                    sourceNiche.setId(id);
                    sourceNiche.setSeqNo(i++);
                    sourceNiche.setUpdateBy(1);
                    sourceNiche.setUpdateDate(date);
                    sourceNicheMapper.updateByPrimaryKeySelective(sourceNiche);
                }
            }
        }
    }
}
