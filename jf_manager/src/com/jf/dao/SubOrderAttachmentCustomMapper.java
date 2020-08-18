package com.jf.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jf.entity.SubOrderAttachmentCustom;
import com.jf.entity.SubOrderAttachmentExample;
@Repository
public interface SubOrderAttachmentCustomMapper{
    List<SubOrderAttachmentCustom> selectByExample(SubOrderAttachmentExample example);
}