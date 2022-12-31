package com.example.srtp.Controller;

import com.example.srtp.Common.Result;
import com.example.srtp.Entity.Community;
import com.example.srtp.Service.CommService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/comm")
public class CommController {

    @Autowired
    CommService commService;

    @PostMapping("/login")
    public Result login(@RequestBody Community community){
        return Result.success(commService.commLogin(community.getAccountName() , community.getAccountPass()));
    }
}
