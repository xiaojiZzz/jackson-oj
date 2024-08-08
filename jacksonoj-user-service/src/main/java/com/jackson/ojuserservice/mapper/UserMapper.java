package com.jackson.ojuserservice.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jackson.ojmodel.model.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户数据库操作
 *
 * @author jackson
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
