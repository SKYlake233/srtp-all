package com.example.srtp.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.srtp.Common.Constants;
import com.example.srtp.Common.Exception.MyException;
import com.example.srtp.Entity.UserData;
import com.example.srtp.Mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public UserData login(String userName , String userPass) {
        QueryWrapper<UserData> userLoginWrapper = new QueryWrapper<>();
        userLoginWrapper.eq("user_name", userName);
        userLoginWrapper.eq("user_pass", userPass);
        UserData userData = userMapper.selectOne(userLoginWrapper);
        if (userData == null)
            throw new MyException(Constants.CODE_500,"账号密码错误");
        return userData;
    }
}
