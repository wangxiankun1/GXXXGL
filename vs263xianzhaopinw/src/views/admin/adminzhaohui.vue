<template>
  <div class="loginbody">
    <div class="login">
      <div class="login-container">
        <el-form
          :mode="loginMode"
          ref="loginForm"
          label-position="left"
          label-width="0px"
          class="demo-ruleForm"
        >
          <h3 class="title">找回密码</h3>
          <el-form-item prop="account">
            <el-input
              type="text"
              v-model="loginMode.adminName"
              auto-complete="off"
              placeholder="用户名"
            ></el-input>
          </el-form-item>
          <el-form-item prop="account">
            <el-input
              type="text"
              v-model="loginMode.adminPhone"
              auto-complete="off"
              placeholder="电话"
            ></el-input>
          </el-form-item>
        
          <el-form-item style="width: 100%">
            <el-button type="primary" style="width: 100%" @click="login" >
              找回密码
            </el-button>
          </el-form-item>
        </el-form>
        <p class="lianjie"><a :href="'/login'" style="color: #0509eb;">返回登陆</a></p>
       
      </div>
    </div>
  </div>
</template>

<script>

import request, { base } from "../../../utils/http";

export default {
  data() {
     
    return {    
      btnLoading: false, //保存按钮加载状态
      loginMode: {},
      loading: false,
      loginJcpeizhi:[], //基础配置数据
    };
  },
  created() {
    this.getJcpeizhi();
  },
  methods: {  
    //获取jcpeizhi
    getJcpeizhi() {
      let para = {};
      this.listLoading = true;
      let url = base + "/jcpeizhiComboList";
      request.post(url, para).then((res) => {
        this.loginJcpeizhi = res.resdata;
        //console.log(this.loginJcpeizhi);
      });
    },
    login() {
      let adminName = this.loginMode.adminName;
      let adminPhone = this.loginMode.adminPhone;
      if (!adminName) {
        this.$message({
          message: "请输入用户名",
          type: "error",
          offset: 320,
        });
        return false;
      }
      //定义正则表达式部分手机号码
			var regphone = /^[1][0-9]{10}$/;
      if (!regphone.test(adminPhone)) {
				this.$message({
          message: "请输入有效的手机号码！",
          type: "error",
          offset: 320,
        });
        return false;
			}


      let url = base + "/zhaohuiAdmin";
      this.loading = true;
      request.post(url, this.loginMode).then((res) => {
        this.loading = false;
        this.$message({
          message: res.msg,
          type: "error",
          offset: 320,
        });
      });
      //
    },
  },
};
</script>

<style   scoped>
.login-container {
  /*box-shadow: 0 0px 8px 0 rgba(0, 0, 0, 0.06), 0 1px 0px 0 rgba(0, 0, 0, 0.02);*/
  -webkit-border-radius: 5px;
  border-radius: 5px;
  -moz-border-radius: 5px;
  background-clip: padding-box;
  margin: 180px auto;
  width: 360px;
  height: 350px;
  padding: 35px 35px 15px 35px;
  background: #fff;
  position: absolute;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  border: 1px solid #eaeaea;
  box-shadow: 0 0 25px #cac6c6;
}

.title {
  margin: 0px auto 40px auto;
  text-align: center;
  color: #505458;
}
.lianjie {
  font-size:12px;
}

.loginbody {
  overflow: scroll;
  overflow-y: hidden;
  overflow-x: hidden;
}

.login {
  width: 100vw;
  padding: 0;
  margin: 0;
  height: 100vh;
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