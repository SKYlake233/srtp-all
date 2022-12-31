package com.example.srtp.Controller;

import com.example.srtp.Common.DataPage;
import com.example.srtp.Common.Result;
import com.example.srtp.Entity.Item;
import com.example.srtp.Service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/item")
@CrossOrigin
public class ItemController {


    @Autowired
    private ItemService itemService;

    @PostMapping("/page")
    public Result page(@RequestBody DataPage dataPage){
        return Result.success(itemService.page(dataPage));
    }

    @PostMapping("/add")
    public Result add(@RequestBody Item item){
        itemService.add(item);
        return Result.success();
    }

    @PostMapping("update")
    public Result upd(@RequestBody Item item){
        itemService.upd(item);
        return Result.success();
    }

    @RequestMapping("/delete/{itemId}")
    public Result del(@PathVariable("itemId") int itemId){
        itemService.del(itemId);
        return Result.success();
    }
}
