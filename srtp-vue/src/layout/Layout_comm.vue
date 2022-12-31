<!--超市的用户布局文件-->
<template>
  <div>
    <!--  <Header/>头部-->
    <Header_comm/>
    <!--  主体-->
    <div style="display: flex">
      <!--  <Aside/>侧边栏-->
      <Aside_comm/>
      <!--    内容-->
      <router-view style="flex: 1"/>
    </div>
  </div>
</template>

<script>
import Header_comm from "@/components/Header_comm.vue";
import Aside_comm from "@/components/Aside_comm.vue";

export default {
  name: "Layout_comm",
  components:{
    Header_comm,
    Aside_comm,
  }
}
</script>

<style scoped>

</style>