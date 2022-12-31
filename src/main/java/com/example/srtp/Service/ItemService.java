package com.example.srtp.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.srtp.Common.DataPage;
import com.example.srtp.Controller.VO.ItemVO;
import com.example.srtp.Entity.Item;
import com.example.srtp.Mapper.ItemMapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class ItemService {

    @Autowired
    ItemMapper itemMapper;


    public HashMap<String, Object> page(DataPage dataPage) {
        int marketId = -1;
        String search = "";
        if(dataPage.getParam().containsKey("marketId"))
            marketId = (int) dataPage.getParam().get("marketId");
        if(dataPage.getParam().containsKey("search"))
            search = (String) dataPage.getParam().get("search");
        Page<Item> page = new Page<Item>();
        page.setCurrent(dataPage.getPageNum());
        page.setSize(dataPage.getPageSize());
        QueryWrapper<Item> itemPageWrapper = new QueryWrapper<>();
        if(marketId != -1)
            itemPageWrapper.eq("item.market_id",marketId);
        itemPageWrapper.like("item_name",search);

        HashMap<String , Object> map = new HashMap<String, Object>();

        map.put("total",itemMapper.getItemInfo(page , itemPageWrapper).size());
        map.put("data",itemMapper.getItemInfo(page , itemPageWrapper));
        return map;
    }

    public void add(Item item) {
        itemMapper.insert(item);
    }

    public void upd(Item item) {
        itemMapper.updateById(item);
    }

    public int del(int itemId) {
        QueryWrapper<Item> itemDelWrapper = new QueryWrapper<>();
        itemDelWrapper.eq("item_id" , itemId);
        return itemMapper.delete(itemDelWrapper);
    }
}
