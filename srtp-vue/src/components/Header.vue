<template>
  <div style="height: 50px;line-height: 50px;border-bottom: 1px solid springgreen;display: flex">
    <div style="width: 200px;padding-left: 30px;font-weight: bold;color: deepskyblue">商户端</div>
    <div style="flex: 1"></div>
    <div style="width: 100px;font-weight: bold;padding-top: 14px">
      <el-dropdown>
    <span class="el-dropdown-link">
      {{market_data.marketName}}
      <i class="el-icon-arrow-down el-icon--right"></i>
    </span>
        <template #dropdown>
          <el-dropdown-menu>
            <el-dropdown-item @click="$router.push('/person')">商户信息</el-dropdown-item>
            <el-dropdown-item @click="$router.push('/login')">退出系统</el-dropdown-item>
          </el-dropdown-menu>
        </template>
      </el-dropdown>
    </div>
  </div>

</template>

<script>

import {ArrowDown} from "@element-plus/icons-vue";
export default {
  name: "Header",
  props:['market_data'],
  components:{
    ArrowDown
  },
  data(){
    return{
      market_data:{}
    }
  },
  created() {
    let managerStr=sessionStorage.getItem("market_data")||"{}"
    this.market_data=JSON.parse(managerStr)
  }
}
</script>

<style scoped>

</style>

