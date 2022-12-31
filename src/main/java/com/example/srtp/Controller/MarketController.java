package com.example.srtp.Controller;

import com.example.srtp.Common.Result;
import com.example.srtp.Entity.Market;
import com.example.srtp.Service.MarketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/market")
@RestController
@CrossOrigin
public class MarketController {
    @Autowired
    MarketService marketService;

    @PostMapping("/login")
    public Result login(@RequestBody Market market){
        return Result.success(marketService.login(market.getMarketAccountName(),market.getMarketAccountPass()));
    }
}
