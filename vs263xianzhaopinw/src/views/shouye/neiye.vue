<template>
     <div class="login">
          <div style="margin-left:40%;margin-top:50px;font-size:50px;color:#ffffff;">
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
   
   <style   scoped>   
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
   }
   </style>