<template>
  <div style="padding: 10px">

    <div style="margin: 10px 0">
    </div>

    <div style="margin: 10px 0">
      <el-input v-model="search" placeholder="请输入关键字（用户名）" clearable style="width: 20%"/>
      <el-button type="success" style="margin-left: 5px" @click="load">查询</el-button>
    </div>

    <el-table :data="tableData" border style="width: 100%">
      <el-table-column prop="userName" label="用户名"  />
      <el-table-column prop="userPhoneNumber" label="用户联系方式" />
      <el-table-column prop="coins" label="爱心币数量" />
      <el-table-column fixed="right" label="操作" width="120">
        <template #default="scope">
          <el-button @click="handleEdit(scope.row)"  size="small" type="primary" >奖励</el-button>
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
            <el-form-item label="奖励爱心币数量">
              <el-input v-model=this.coins   style="width: 80%"/>
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
  name: 'person',
  components: {
  },

  data(){
    return{
      coins:0,
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
      request.get("/user/addCoins/"+this.form.userId+"/"+this.coins).then(res =>{
        if(res.code == 200){
          this.$message({
            type:"success",
            message:"添加成功"
          })}
        else{
          this.$message({
            type:"error",
            message:"添加失败"
          })
        }
        this.load();
      })
      this.load();  //添加完成后刷新表单
      this.dialogVisible=false//关闭弹窗
    },
    handleEdit(row){//编辑
      this.form=JSON.parse(JSON.stringify(row))
      this.dialogVisible=true
    },
    load(){//将后端查询到的数据渲染到web表格
      request.post("/user/page",{
        "pageSize":this.pageSize,
        "pageNum":this.currentPage,
        "param":{
          "search":this.search
        }
      }).then(res => {
        this.tableData=res.data
        this.total=res.total
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