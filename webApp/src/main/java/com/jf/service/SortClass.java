package com.jf.service;

import java.util.Comparator;

import com.jf.common.utils.DateUtil;
import com.jf.entity.dto.TactInfoModel;

/**
  * @author  chenwf: 
  * @date 创建时间：2017年6月7日 下午5:38:45 
  * @version 1.0 
  * @parameter  
  * @return  
*/
public class SortClass implements Comparator<TactInfoModel>{

	@Override
	public int compare(TactInfoModel o1, TactInfoModel o2) {
		long m1 = DateUtil.getFormatString(o1.getAcceptTime(), "yyyy-MM-dd HH:mm:ss").getTime();
		long m2 = DateUtil.getFormatString(o2.getAcceptTime(), "yyyy-MM-dd HH:mm:ss").getTime();
		int result = 0;
        if (m1 > m2) {
            result = -1;
        }
        if (m1 < m2) {
            result = +1;
        }
        return result;
	}

}
