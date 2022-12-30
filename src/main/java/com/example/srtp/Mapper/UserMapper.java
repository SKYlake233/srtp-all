package com.example.srtp.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.srtp.Entity.UserData;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<UserData> {
}
