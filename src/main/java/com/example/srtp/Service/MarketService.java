package com.example.srtp.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.srtp.Common.Constants;
import com.example.srtp.Common.Exception.MyException;
import com.example.srtp.Entity.Market;
import com.example.srtp.Entity.UserData;
import com.example.srtp.Mapper.MarketMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MarketService {

    @Autowired
    MarketMapper marketMapper;


    public Market login(String marketAccountName, String marketAccountPass) {
        QueryWrapper<Market> marketLoginWrapper = new QueryWrapper<>();
        marketLoginWrapper.eq("market_account_name" , marketAccountName);
        marketLoginWrapper.eq("market_account_pass" , marketAccountPass);
        Market market = marketMapper.selectOne(marketLoginWrapper);
        if(market == null)
            throw new MyException(Constants.CODE_500 , "账号或密码错误");
        return market;
    }
}
