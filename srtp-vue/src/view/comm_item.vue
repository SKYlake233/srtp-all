<template>
  <div style="padding: 10px">

    <div style="margin: 10px 0">
    </div>

    <div style="margin: 10px 0">
      <el-input v-model="search" placeholder="请输入关键字（物品名）" clearable style="width: 20%"/>
      <el-button type="success" style="margin-left: 5px" @click="load">查询</el-button>
      <el-select v-model=marketId >
        <el-option label="请选择" value=""></el-option>
        <el-option v-for="item in marketIds"  :label="item.value" :value="item.id">
        </el-option>
      </el-select>
    </div>

    <el-table :data="tableData" border style="width: 100%">
      <el-table-column prop="itemId" label="商品编号" sortable />
      <el-table-column prop="marketName" label="超市名称"  />
      <el-table-column prop="itemName" label="商品名称" />
      <el-table-column prop="itemCount" label="商品数量" />
      <el-table-column prop="itemValue" label="商品价值" />
    </el-table>
    <div style="margin:10px 0">
      <div class="demo-pagination-block">
        <el-pagination
            v-model:currentPage="currentPage"
            :page-sizes="[5, 10, 15, 20]"
            :page-size="pageSize"
            layout="total, sizes, prev, pager, next, jumper"
            :total="total"
            @size-change="handleSizeChange"
            @current-change="handleCurrentChange"/>
      </div>
    </div>
  </div>
</template>

<script>

import request from "@/utils/requests";

export default {
  name: 'Item',
  components: {
  },

  data(){
    return{
      marketIds: [{
        value: '一号超市',
        id: 1
      }, {
        value: '二号超市',
        id: 2
      }, {
        value: '三号超市',
        id: 3
      },{
        value: '全部',
        id: -1
      }],
      marketId : -1,
      form:{},
      dialogVisible:false,
      search:'',
      currentPage:1,//当前页
      total:0,
      pageSize:10,//每页显示数据条数
      tableData:[
      ]
    }

  },
  created() {//加载页面时调用load
    this.load()
  },
  methods:{
    load(){//将后端查询到的数据渲染到web表格
      request.post("/item/page",{
        "pageSize":this.pageSize,
        "pageNum":this.currentPage,
        "param":{
          "marketId": this.marketId,
          "search":this.search
        }
      }).then(res => {
        this.tableData=res.data.data
        this.total=res.data.total
      })
    },
    handleSizeChange(pageSize){//每页显示多少条数据
      this.pageSize=pageSize
      this.load()
    },
    handleCurrentChange(pageNum){//跳转到第几页
      this.currentPage=pageNum
      this.load()
    }


  }}
</script>