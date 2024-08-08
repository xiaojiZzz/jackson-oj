package com.jackson.ojquestionservice.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jackson.ojmodel.model.entity.Question;
import org.apache.ibatis.annotations.Mapper;

/**
 * 问题数据库操作
 *
 * @author jackson
 */
@Mapper
public interface QuestionMapper extends BaseMapper<Question> {
}
