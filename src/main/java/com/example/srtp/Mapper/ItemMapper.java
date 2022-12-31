package com.example.srtp.Mapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.srtp.Controller.VO.ItemVO;
import com.example.srtp.Entity.Item;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ItemMapper extends BaseMapper<Item> {

    @Select("select item.*,market.market_name from item,market  ${ew.customSqlSegment} AND item.market_id = market.market_id ")
    public List<ItemVO> getItemInfo(IPage page , @Param("ew") QueryWrapper queryWrapper);
}
