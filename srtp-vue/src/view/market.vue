<template>
  <div style="padding: 10px">

    <div style="margin: 10px 0">
      <el-button color="#909399" @click="add">发布新捐赠商品</el-button>
      <el-button color="#909399">导出数据</el-button>
    </div>

    <div style="margin: 10px 0">
      <el-input v-model="search" placeholder="请输入关键字（物品名）" clearable style="width: 20%"/>
      <el-button type="success" style="margin-left: 5px" @click="load">查询</el-button>
    </div>

    <el-table :data="tableData" border style="width: 100%">
      <el-table-column prop="itemId" label="商品编号" sortable />
      <el-table-column prop="marketName" label="超市名称"  />
      <el-table-column prop="itemName" label="商品名称" />
      <el-table-column prop="itemCount" label="商品数量" />

      <el-table-column align="center" label="操作" width="110">
        <template v-slot="scope">
          <el-button  @click="handleEdit(scope.row)"  size="small" type="primary" >编辑</el-button>
          <el-popconfirm title="Are you sure to delete this?" @confirm="handleDelete(scope.row.itemId)">
            <template #reference>
              <el-button size="small" type="danger">删除</el-button>
            </template>
          </el-popconfirm>
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
        <!--      添加-->
        <el-dialog v-model="dialogVisible" title="请输入" width="30%">
          <!--  添加数据的弹窗      -->
          <el-form model="form" label-width="120px">
            <el-form-item label="商品名">
              <el-input v-model="form.itemName" style="width: 80%"/>
            </el-form-item>
            <el-form-item label="商品数量">
              <el-input v-model="form.itemCount" style="width: 80%"/>
            </el-form-item>
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
  name: 'market',
  components: {
  },

  data(){
    return{
      marketId : 0,
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
    add(){
      this.dialogVisible=true;
      this.form={};
      this.form.marketId = this.marketId;

    },
    save(){
      if(this.form.itemId){//如果存在此ID，更新，，，否则插入
        request.post("/item/update",this.form).then(res=>{
          console.log(res)
          if(res.code == 200){
            this.$message({
              type:"success",
              message:"修改成功"
            })}else{
            this.$message({
              type:"success",
              message:"修改失败"
            })
          }
          this.load();  //添加完成后刷新表单
          this.dialogVisible=false//关闭弹窗
        })}else{
        request.post("/item/add",this.form).then(res=>{
          console.log(res)
          if(res.code == 200){
            this.$message({
              type:"success",
              message:"添加成功"
            })}else{
            this.$message({
              type:"success",
              message:"添加失败"
            })
          }
          this.load();  //添加完成后刷新表单
          this.dialogVisible=false//关闭弹窗
        })}
    },

    load(){//将后端查询到的数据渲染到web表格
      this.marketId = JSON.parse(sessionStorage.getItem("market_data")).marketId;
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
    handleEdit(row){//编辑
      this.form = {};
      this.form=JSON.parse(JSON.stringify(row))
      this.dialogVisible=true
    },
    handleDelete(id){
      request.get("/item/delete/"+id).then(res=>{
        if(res.code == 200){
          this.$message({
            type:"success",
            message:"删除成功"
          })}else{
          this.$message({
            type:"success",
            message:"删除失败"
          })
        }
        this.load();  //添加完成后刷新表单
        this.dialogVisible=false//关闭弹窗
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