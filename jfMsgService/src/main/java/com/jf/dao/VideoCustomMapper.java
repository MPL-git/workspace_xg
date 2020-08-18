package com.jf.dao;

import com.jf.entity.VideoPvStatistical;
import com.jf.entity.dto.SimpleCountDTO;
import com.jf.entity.dto.Top10VideoLast7DayCount;
import com.jf.entity.dto.VideoWeightDTO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * @author luoyb
 * Created on 2019/9/11
 */
@Repository
public interface VideoCustomMapper {

    List<SimpleCountDTO> statisticDailyPlayCount(@Param("dateBegin") Date dateBegin,@Param("dateEnd") Date dateEnd);

    List<SimpleCountDTO> statisticDailyLikeCount(@Param("dateBegin") Date dateBegin,@Param("dateEnd") Date dateEnd);

    List<SimpleCountDTO> statisticDailyCommentCount(@Param("dateBegin") Date dateBegin,@Param("dateEnd") Date dateEnd);

    List<SimpleCountDTO> statisticDailyCollectCount(@Param("dateBegin") Date dateBegin,@Param("dateEnd") Date dateEnd);

    List<Integer> findPlayTop10Video(@Param("dateBegin")String beginDateStr,@Param("endDateStr") String endDateStr);

    Top10VideoLast7DayCount getTop10VideoLast7DayCount(@Param("videoIds") List<Integer> videoIds,@Param("dateBegin") String beginDateStr, @Param("endDateStr") String endDateStr);

    List<VideoPvStatistical> countVideoLast7Day(@Param("dateBegin")String beginDateStr,@Param("endDateStr") String endDateStr);

    List<VideoWeightDTO> findVideoSeasonAndManualWeight();
}
