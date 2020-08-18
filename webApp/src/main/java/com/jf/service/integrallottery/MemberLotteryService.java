package com.jf.service.integrallottery;

import com.google.common.collect.Lists;
import com.jf.common.base.BaseService;
import com.jf.common.constant.StateConst;
import com.jf.common.utils.DateUtil;
import com.jf.common.utils.StringUtil;
import com.jf.dao.MemberLotteryCustomMapper;
import com.jf.dao.MemberLotteryMapper;
import com.jf.entity.MemberLottery;
import com.jf.entity.MemberLotteryExample;
import com.jf.entity.dto.MemberLotteryDTO;
import com.jf.vo.request.PageRequest;
import com.jf.vo.response.integrallottery.MemberLotteryView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author luoyb
 * Created on 2020/7/21
 */
@Service
public class MemberLotteryService extends BaseService<MemberLottery, MemberLotteryExample> {

    @Autowired
    private MemberLotteryMapper memberLotteryMapper;
    @Autowired
    private MemberLotteryCustomMapper memberLotteryCustomMapper;

    @Autowired
    public void setMapper() {
        super.setDao(memberLotteryMapper);
    }


    public List<MemberLotteryDTO> findLatest20WinningLog() {
        return memberLotteryCustomMapper.findLatest20WinningLog();
    }

    public int countMemberFreeTimes(int memberId) {
        MemberLotteryExample example = new MemberLotteryExample();
        example.createCriteria()
                .andMemberIdEqualTo(memberId)
                .andConsumeIntegralEqualTo(0)
                .andCreateDateGreaterThanOrEqualTo(DateUtil.getDateAfterAndBeginTime(new Date(), 0))
                .andDelFlagEqualTo(StateConst.FALSE);
        return this.countByExample(example);
    }

    public List<MemberLotteryView> findMemberLottery(Integer memberId, PageRequest pageRequest) {
        MemberLotteryExample example = new MemberLotteryExample();
        example.createCriteria()
                .andMemberIdEqualTo(memberId)
                .andDelFlagEqualTo(StateConst.FALSE)
                .andTypeLessThan("4");
        example.setLimitStart(pageRequest.getOffset());
        example.setLimitSize(pageRequest.getPageSize());
        example.setOrderByClause("create_date desc");
        List<MemberLottery> list = this.selectByExample(example);

        List<MemberLotteryView> viewList = Lists.newArrayList();
        for (MemberLottery memberLottery : list) {
            MemberLotteryView view = new MemberLotteryView();
            view.setId(memberLottery.getId());
            view.setCreateDate(DateUtil.getFormatDate(memberLottery.getCreateDate(), DateUtil.DATE_TIME_FORMAT_SHORT));
            view.setType(memberLottery.getType());
            view.setPrizeName(getPrizeName(memberLottery));
            view.setReceived(true);
            if ("3".equals(memberLottery.getType())) {
                view.setProductId(memberLottery.getProductId());
                if (memberLottery.getRelevantId() == null) {
                    view.setReceived(false);
                }
            }
            viewList.add(view);
        }
        return viewList;
    }

    public String getPrizeName(MemberLottery memberLottery) {
        String prizeName = "";
        if ("1".equals(memberLottery.getType())) { //积分
            prizeName = StringUtil.buildMsg("{}积分", memberLottery.getIntegral());
        } else if ("2".equals(memberLottery.getType())) { //优惠券
            prizeName = StringUtil.buildMsg("{}元优惠券", memberLottery.getCouponAmount().stripTrailingZeros().toPlainString());
        } else if ("3".equals(memberLottery.getType())) { //商品
            prizeName = decorateProductName(memberLottery.getProductName());
        }
        return prizeName;
    }

    public String decorateProductName(String productName) {
        if (productName.length() <= 5) {
            return productName;
        }
        return StringUtil.buildMsg("{}...", productName.substring(0, 5));
    }
}
