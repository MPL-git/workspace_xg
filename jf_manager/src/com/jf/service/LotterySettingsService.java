package com.jf.service;

import com.jf.dao.LotterySettingsMapper;
import com.jf.entity.LotterySettings;
import com.jf.entity.LotterySettingsExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class LotterySettingsService extends BaseService<LotterySettings, LotterySettingsExample>{
	@Autowired
	private LotterySettingsMapper lotterySettingsMapper;

	@Autowired
	public void setLotterySettingsMapper(LotterySettingsMapper lotterySettingsMapper) {
		super.setDao(lotterySettingsMapper);
		this.lotterySettingsMapper = lotterySettingsMapper;
	}

}
