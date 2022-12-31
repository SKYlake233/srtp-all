<template>
  <div style="padding: 10px">

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
      <el-table-column prop="itemId" label="商品id" sortable />
      <el-table-column prop="marketName" label="超市名称"  />
      <el-table-column prop="itemName" label="商品名称" />
      <el-table-column prop="itemCount" label="商品数量" />
      <el-table-column fixed="right" label="操作" width="120">
        <template #default="scope">
          <el-button @click="handleEdit(scope.row)"  size="small" type="primary" >兑换</el-button>
        </template>
      </el-table-column>
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
        <el-dialog v-model="dialogVisible" title="提示" width="30%">
          <!--  添加数据的弹窗      -->
          <el-form model="form" label-width="120px">
            <el-form-item label="您的用户id">
              <el-input v-model=this.userId  readonly="readonly" style="width: 80%"/>
            </el-form-item>
            <el-form-item label="商品数量">
              <el-input v-model=this.form.itemCount  readonly="readonly" style="width: 80%"/>
            </el-form-item>
            <el-form-item label="爱心币数量">
              <el-input v-model=this.coins  readonly="readonly" style="width: 80%"/>
            </el-form-item>
            消耗一个爱心币，确认兑换？
          </el-form>
          <template #footer>
      <span class="dialog-footer">
        <el-button @click="dialogVisible = false">取消</el-button>
        <el-button type="primary" @click="save ">确定</el-button>
      </span>
          </template>
        </el-dialog>
      </div>
    </div>
  </div>
</template>

<script>

import request from "@/utils/requests";

export default {
  name: 'user',
  components: {
  },

  data(){
    return{
      coins:0,
      userId:JSON.parse(sessionStorage.getItem("user_data")).userId,
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
    save(){
      //联网进行操作
      if(this.coins <= 0 || this.form.itemCount <= 0)
        this.$message({
          type:"error",
          message:"爱心币不足或商品已经兑换完全"
        })
      request.get("/user/reward/"+this.userId+"/"+this.form.itemId).then(res =>{
        if(res.code == 200){
          this.$message({
            type:"success",
            message:"兑换成功"
          })}
        else{
          this.$message({
            type:"error",
            message:res.msg
          })
        }
      })
      this.load();  //添加完成后刷新表单
      this.dialogVisible=false//关闭弹窗
    },
    handleEdit(row){//编辑
      this.getCoins()
      this.form=JSON.parse(JSON.stringify(row))
      this.dialogVisible=true
    },
    getCoins(){
      request.get("/user/coins/"+this.userId).then(res =>{
        this.coins = res.data;
      })
    },
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