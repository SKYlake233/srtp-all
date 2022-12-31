package com.example.srtp.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.srtp.Common.Constants;
import com.example.srtp.Common.Exception.MyException;
import com.example.srtp.Controller.CommController;
import com.example.srtp.Entity.Community;
import com.example.srtp.Mapper.CommMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletionStage;

@Service
public class CommService {

    @Autowired
    CommMapper commMapper;

    public Community commLogin(String accountName, String accountPass) {
        QueryWrapper<Community> commLoginWrapper = new QueryWrapper<>();
        commLoginWrapper.eq("account_name" , accountName);
        commLoginWrapper.eq("account_pass" , accountPass);
        Community community =  commMapper.selectOne(commLoginWrapper);
        if(community == null)
            throw new MyException(Constants.CODE_500 , "用户名或密码错误");
        return community;
    }
}
