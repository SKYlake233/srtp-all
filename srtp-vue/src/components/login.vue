<template>
  <div style="width: 100%; height: 100vh; background: paleturquoise; overflow: hidden">
    <div style="width: 400px;margin: 150px auto">
      <div style="color: red;font-size: 30px;text-align: center;padding: 30px" >商店慈善物品管理系统</div>
      <el-form ref="form" :model="form" size="normal" :rules="rules">
        <el-form-item prop="marketAccountName">
          <el-input  :prefix-icon="Avatar" v-model="form.username"></el-input>
        </el-form-item>
        <el-form-item prop="marketAccountPass">
          <el-input   :prefix-icon="Lock" v-model="form.password" show-password></el-input>
        </el-form-item>

        <el-form-item label="账户类型">
          <el-select v-model=userType >
            <el-option label="请选择" value=""></el-option>
            <el-option v-for="item in userTypes"  :label="item.value" :value="item.id">
            </el-option>
          </el-select>
        </el-form-item>

        <el-form-item>
          <el-button style="width: 20%;margin: 20px auto;" type="primary" @click="login">登录</el-button>
          <el-button style="width: 20%;margin: 20px auto;" type="primary" @click="$router.push('/register')">注册</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>

import {Avatar, Lock} from "@element-plus/icons-vue";
import request from "@/utils/requests";

export default {
  name: "Login",
  data(){
    return{
      login_data:[],
      userNameType:[{
        username:"accountName",
        password:"accountPass"
      },{
        username:"marketAccountName",
        password:"marketAccountPass"
      },{
        username:"userName",
        password:"userPass"
      }],
      userTypes: [{
        value: '社区用户',
        id: 0
      }, {
        value: '商户登录',
        id: 1
      }, {
        value: '个人用户',
        id: 2
      }],
      urls:["/comm/login","/market/login","/user/login"],
      userType : 0,
      form:{
        username:"",
        password:"",
      },
      rules:{
        username:[
          {required:true , message:"请输入用户名",trigger:'blur'}
        ],
        password:[
          {required:true , message:"请输入密码",trigger:'blur'}
        ]

      }
    }
  },
  setup(){
    return{
      Lock,
      Avatar,
    }
  },
  methods:{//登录
    login(key){
      this.$refs['form'].validate((valid)=>{ //当用户名和密码栏都有数据时才会发送请求
        if(valid){
          const str_u = this.userNameType[this.userType].username;
          const str_p = this.userNameType[this.userType].password;
          this.login_data={
            [str_u]:this.form.username,
            [str_p]:this.form.password
          }
          //send data of user account
          request.post(this.urls[this.userType],
              this.login_data).then(res=>{
            console.log(res)
            if(res.code == 200){
              this.$message({
                type:"success",
                message:"登录成功"
              })
              if(this.userType == 1){
                this.$router.push("/market")//主页界面跳转
                sessionStorage.setItem("market_data", JSON.stringify(res.data))//缓存用户信息
              }
              else if(this.userType == 0)
                this.$router.push("/comm/item")//主页界面跳转

              else if(this.userType == 2){
                this.$router.push("/user")//主页界面跳转
                sessionStorage.setItem("user_data", JSON.stringify(res.data))//缓存用户信息
              }
            }else{
              this.$message({
                type:"error",
                message:res.msg
              })
            }
          })
        }
      })
    },

  }
}
</script>

<style scoped>

</style>