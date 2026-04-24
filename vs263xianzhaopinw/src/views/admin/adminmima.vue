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
          <h3 class="title">修改密码</h3>
          <el-form-item prop="checkPass">
            <el-input
              type="password"
              v-model="loginMode.adminPassword"
              auto-complete="off"
              placeholder="原密码"
            ></el-input>
          </el-form-item>
          <el-form-item prop="checkPass">
            <el-input
              type="password"
              v-model="loginMode.adminPassword1"
              auto-complete="off"
              placeholder="新密码"
            ></el-input>
          </el-form-item>
          <el-form-item prop="checkPass">
            <el-input
              type="password"
              v-model="loginMode.adminPassword2"
              auto-complete="off"
              placeholder="新密码"
            ></el-input>
          </el-form-item>
        
          <el-form-item style="width: 100%">
            <el-button type="primary" style="width: 100%" @click="login" >
              修改
            </el-button>
          </el-form-item>
        </el-form>
       
      </div>
    </div>
  </div>
</template>

<script>

import request, { base } from "../../../utils/http";
import store from '../../store'

export default {
  data() {
     
    return {    
      btnLoading: false, //保存按钮加载状态
      loginMode: {},
      loading: false,
      loginJcpeizhi:[], //基础配置数据
      adminId: "",
    };
  },
  created() {
    this.getJcpeizhi();
  },
  mounted() {
    var user = sessionStorage.getItem("currentUser");
    user = JSON.parse(user);
    this.adminId = user.adminId || "";
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
      let adminPassword = this.loginMode.adminPassword;
      let adminPassword1 = this.loginMode.adminPassword1;
      let adminPassword2 = this.loginMode.adminPassword2;
      
      if (!adminPassword) {
        this.$message({
          message: "请输入原密码",
          type: "error",
          offset: 320,
        });
        return false;
      }
      
      if (!adminPassword1) {
        this.$message({
          message: "请输入新密码",
          type: "error",
          offset: 320,
        });
        return false;
      }
      
      if (!adminPassword2) {
        this.$message({
          message: "请再次输入新密码",
          type: "error",
          offset: 320,
        });
        return false;
      }
      
      if (adminPassword1 != adminPassword2) {
        this.$message({
          message: "两次输入密码不一致",
          type: "error",
          offset: 320,
        });
        return false;
			}


        let url = base + "/mimaAdmin?adminId="+this.adminId;
        this.loading = true;
        request.post(url, this.loginMode).then((res) => {
          this.loading = false;
          if (res.code == 200) {
            this.$message({
              message: "密码修改成功，请重新登陆",
              type: "success",
              offset: 320,
            });
            sessionStorage.removeItem("currentUser");
            sessionStorage.removeItem("jcpeizhi");
            sessionStorage.removeItem("jcbiaotis");
            sessionStorage.removeItem("jcdaohangslist");
            sessionStorage.removeItem("role");
            store.commit('setAsyncRoutestMark', false); // 退出登陆将动态路由标志设为空
            this.$router.push("/login");
          } else {
            this.$message({
              message: res.msg,
              type: "error",
            });
          }
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
  height: 380px;
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