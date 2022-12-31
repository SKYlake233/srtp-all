<!--超市的用户布局文件-->
<template>
  <div>
    <!--  <Header/>头部-->
    <Header/>
    <!--  主体-->
    <div style="display: flex">
      <!--  <Aside/>侧边栏-->
      <Aside/>
      <!--    内容-->
      <router-view style="flex: 1"/>
    </div>
  </div>
</template>

<script>
import Header from "@/components/Header.vue"
import Aside from "@/components/Aside.vue";

export default {
  name: "Layout",
  components:{
    Header,
    Aside,
  }
}
</script>

<style scoped>

</style>