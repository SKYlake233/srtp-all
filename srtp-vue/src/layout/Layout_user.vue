<!--超市的用户布局文件-->
<template>
  <div>
    <!--  <Header/>头部-->
    <Header_user/>
    <!--  主体-->
    <div style="display: flex">
      <!--  <Aside/>侧边栏-->
      <Aside_user/>
      <!--    内容-->
      <router-view style="flex: 1"/>
    </div>
  </div>
</template>

<script>
import Header_user from "@/components/Header_user.vue";
import Aside_user from "@/components/Aside_user.vue";

export default {
  name: "Layout_user",
  components:{
    Header_user,
    Aside_user,
  }
}
</script>

<style scoped>

</style>