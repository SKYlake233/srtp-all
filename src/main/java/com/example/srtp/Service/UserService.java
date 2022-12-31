package com.example.srtp.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.srtp.Common.Constants;
import com.example.srtp.Common.DataPage;
import com.example.srtp.Common.Exception.MyException;
import com.example.srtp.Entity.Item;
import com.example.srtp.Entity.UserData;
import com.example.srtp.Mapper.ItemMapper;
import com.example.srtp.Mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ItemMapper itemMapper;

    public UserData login(String userName , String userPass) {
        QueryWrapper<UserData> userLoginWrapper = new QueryWrapper<>();
        userLoginWrapper.eq("user_name", userName);
        userLoginWrapper.eq("user_pass", userPass);
        UserData userData = userMapper.selectOne(userLoginWrapper);
        if (userData == null)
            throw new MyException(Constants.CODE_500,"账号密码错误");
        return userData;
    }

    public Integer getCoins(int userId) {
        QueryWrapper<UserData> userUpdateWrapper = new QueryWrapper<>();
        userUpdateWrapper.eq("user_id",userId);
        UserData userData =  userMapper.selectOne(userUpdateWrapper);
        return (int) userData.getCoins();
    }

    public void reward(int userId, int itemId) {
        if(getCoins(userId) <= 0 )
            throw new MyException(Constants.CODE_500 , "用户的爱心币数量太少");
        QueryWrapper queryWrapper = new QueryWrapper<Item>().eq("item_id" , itemId);
        int count= (int) itemMapper.selectOne(queryWrapper).getItemCount();
        if(count <= 0)
            throw new MyException(Constants.CODE_500 , "物品数量太少");

        //开始两个减一
        UpdateWrapper userWrapper = new UpdateWrapper();
        userWrapper.eq("user_id",userId);
        userWrapper.setSql("coins = coins - 1");
        userMapper.update(null,userWrapper);

        UpdateWrapper itemWrapper = new UpdateWrapper();
        itemWrapper.eq("item_id",userId);
        itemWrapper.setSql("item_count = item_count - 1");
        itemMapper.update(null,itemWrapper);
    }

    public HashMap<String, Object> page(DataPage dataPage) {
        String search = "";
        if(dataPage.getParam().containsKey("search"))
            search = (String) dataPage.getParam().get("search");
        Page<UserData> page = new Page<UserData>();
        page.setCurrent(dataPage.getPageNum());
        page.setSize(dataPage.getPageSize());
        QueryWrapper<UserData> userPageWrapper = new QueryWrapper<>();
        userPageWrapper.like("user_name",search);
        HashMap<String , Object> map = new HashMap<String, Object>();
        map.put("total",userMapper.selectPage(page , userPageWrapper).getTotal());
        map.put("data",userMapper.selectPage(page , userPageWrapper).getRecords());
        return map;
    }

    public int addCoins(int userId, int count) {
        UpdateWrapper userWrapper = new UpdateWrapper();
        userWrapper.eq("user_id",userId);
        userWrapper.setSql("coins = coins + "+count);
        return userMapper.update(null,userWrapper);
    }
}
