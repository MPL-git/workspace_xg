package com.jf.dao;

import com.jf.entity.MchtInformation;
import com.jf.entity.MchtInformationCustomExample;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MchtInformationCustomMapper {
    public List<MchtInformation> selectByCustomExample(MchtInformationCustomExample mchtInformationCustomExample);

    Integer countByCustomExample(MchtInformationCustomExample mchtInformationCustomExample);
}
