<template>
  <div style="width: 100%; height: 100vh; background: paleturquoise; overflow: hidden">
    <div style="width: 400px;margin: 150px auto">
      <div style="color: red;font-size: 30px;text-align: center;padding: 30px">XXXX管理系统</div>
      <el-form ref="form" :model="form" size="normal">
        <el-form-item prop="username">
          <el-input v-model="form.userName" :prefix-icon="Avatar"></el-input>
        </el-form-item>
        <el-form-item prop="userPhoneNumber">
          <el-input v-model="form.userPhoneNumber" :prefix-icon="Phone"></el-input>
        </el-form-item>
        <el-form-item prop="password">
          <el-input v-model="form.userPass" :prefix-icon="Lock" show-password></el-input>
        </el-form-item>
        <el-form-item prop="confirm">
          <el-input v-model="this.confirm" :prefix-icon="Lock" show-password></el-input>
        </el-form-item>
        <el-form-item>
          <el-button style="width: 20%;margin: 20px auto;" type="primary" @click="register">注册</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>

import {Avatar, Lock , Phone} from "@element-plus/icons-vue";
import request from "@/utils/requests"

export default {
  name: "Register",
  data() {
    return {
      confirm:'',
      form: {},
      rules: {
        username: [
          {required: true, message: "请输入用户名", trigger: 'blur'}
        ],
        userPhoneNumber: [
          {required: true, message: "请输入手机号", trigger: 'blur'}
        ],
        userPass: [
          {required: true, message: "请输入密码", trigger: 'blur'}
        ],
        confirm: [
          {required: true, message: "请确认密码", trigger: 'blur'}
        ]
      }
    }
  },
  setup() {
    return {
      Lock,
      Avatar,
      Phone
    }
  },
  methods: {//注册
    register() {
      if (this.form.password !== this.form.confirm) {
        this.$message({
          type: "error",
          message: '密码不一致，请重试'
        })
      }
      this.$refs['form'].validate((valid) => { //当用户名和密码栏都有数据时才会发送请求
        if (valid) {
          request.post("/user/regis", this.form).then(res => {
            if (res.code == 200) {
              this.$message({
                type: "success",
                message: "注册成功"
              });
              this.$router.push("/login")
            } else {
              this.$message({
                type: "error",
                message: res.msg
              })
            }
          })
        }
      })
    }
  }
}
</script>

<style scoped>

</style>