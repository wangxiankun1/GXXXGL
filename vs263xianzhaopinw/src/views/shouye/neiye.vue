<template>
  <div class="login">
    <div class="welcome-title">
      欢迎使用{{ jcpeizhi.jcpeizhiName }}
    </div>
  </div>
</template>

<script>
export default {
  name: 'neiye',
  data() {
    return {
      jcpeizhiName: "",
      jcpeizhi:{}, //基础配置数据
    };
  },
  mounted() {
    this.jcpeizhi = sessionStorage.getItem("jcpeizhi");
    this.jcpeizhi = JSON.parse(this.jcpeizhi);
    this.jcpeizhiName = this.jcpeizhi.jcpeizhiName || "";
  },
  components: {

  },

}
</script>

<style scoped>
.login {
  width: 100%;
  padding: 0;
  margin: 0;
  min-height: 100%;
  font-size: 16px;
  background-position: left top;
  background-color: #242645;
  color: #fff;
  font-family: "Source Sans Pro";
  position: relative;
  background-image: url("../../assets/shouye/image/shouye.jpg");
  background-repeat: no-repeat;
  background-size: 100% 100%;
  /* 新增：给父容器开启Flex布局，实现子元素水平+垂直居中 */
  display: flex;
  justify-content: center; /* 水平居中 */
  align-items: flex-start;  /* 顶部对齐，保留你原来的margin-top效果 */
  padding-top: 50px;        /* 替代原来的margin-top，更规范 */
}

.welcome-title {
  font-size: 50px;
  color: #ffffff;
  /* 移除了错误的margin-left:40% */
}
</style>