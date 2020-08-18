package com.jf.dao;

import com.jf.entity.dto.MemberLotteryDTO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author luoyb
 * Created on 2020/7/21
 */
@Repository
public interface MemberLotteryCustomMapper {

    List<MemberLotteryDTO> findLatest20WinningLog();
}
