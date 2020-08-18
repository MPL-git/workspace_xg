package com.jf.dao;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.jf.vo.ActivityDateSchedule;

@Repository
public interface ActivityDateScheduleMapper {

	public List<ActivityDateSchedule> getActivityDateSchedule(Date activityDate);
}
