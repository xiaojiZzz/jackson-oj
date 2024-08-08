package com.jackson.ojquestionservice.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jackson.ojmodel.model.entity.QuestionSubmit;
import org.apache.ibatis.annotations.Mapper;

/**
 * 问题提交数据库操作
 *
 * @author jackson
 */
@Mapper
public interface QuestionSubmitMapper extends BaseMapper<QuestionSubmit> {
}
