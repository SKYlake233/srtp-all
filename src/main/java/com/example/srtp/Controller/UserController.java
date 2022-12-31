package com.example.srtp.Controller;

import com.example.srtp.Common.Result;
import com.example.srtp.Entity.UserData;
import com.example.srtp.Service.UserService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/user")
@RestController
@CrossOrigin
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/login")
    public Result login(@RequestBody UserData userData){
        return Result.success(userService.login(userData.getUserName() , userData.getUserPass()));
    }

    @RequestMapping("/coins/{userId}")
    public Result getCoins(@PathVariable("userId") int userId){
        return Result.success(userService.getCoins(userId));
    }

    @Transactional
    @RequestMapping("/reward/{userId}/{itemId}")
    public Result reward(@PathVariable("userId") int userId,@PathVariable("itemId") int itemId){
        userService.reward(userId , itemId);
        return Result.success();
    }

}
