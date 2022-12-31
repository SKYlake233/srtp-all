<template>
  <div>
    <!--  :default-active="$route.path.substr(1)"左边导航栏实时高亮-->
    <el-menu
        style=" width: 200px;min-height: calc(100vh - 50px)"
        :default-active="$route.path.substr(1)"
        router
        class="el-menu-vertical-demo">
      <el-sub-menu index="1">
        <template #title>商品管理</template>
        <el-menu-item index="market" >捐赠品管理</el-menu-item>
      </el-sub-menu>
    </el-menu>
  </div>
</template>
<script>
export default {
  name: "Aside",

}
</script>

<style scoped>

</style>