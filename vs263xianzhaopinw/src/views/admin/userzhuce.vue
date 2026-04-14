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
          <h3 class="title">{{loginJcpeizhi.userBieming}}注册</h3>
          <el-form-item prop="account">
            <el-input
              type="text"
              v-model="loginMode.userName"
              auto-complete="off"
              placeholder="用户名"
            ></el-input>
          </el-form-item>
          <el-form-item prop="checkPass">
            <el-input
              type="password"
              v-model="loginMode.userPassword"
              auto-complete="off"
              placeholder="密码"
            ></el-input>
          </el-form-item>
          <el-form-item prop="account">
            <el-input
              type="text"
              v-model="loginMode.userXingming"
              auto-complete="off"
              placeholder="姓名"
            ></el-input>
          </el-form-item>
          <el-form-item prop="checkPass">
            <el-select style="width:288px;" v-model="loginMode.userSex" placeholder="性别">
              <el-option label="男" value="0"> </el-option>
              <el-option label="女" value="1"> </el-option>
            </el-select>
          </el-form-item>
          <el-form-item prop="account">
            <el-input
              type="text"
              v-model="loginMode.userAge"
              auto-complete="off"
              placeholder="年龄"
            ></el-input>
          </el-form-item>
          <el-form-item prop="account">
            <el-input
              type="text"
              v-model="loginMode.userPhone"
              auto-complete="off"
              placeholder="电话"
            ></el-input>
          </el-form-item>
          <el-form-item prop="account">
            <el-input
              type="text"
              v-model="loginMode.userMark"
              auto-complete="off"
              placeholder="学校"
            ></el-input>
          </el-form-item>
          <el-form-item prop="account">
            <el-input
              type="text"
              v-model="loginMode.userMark1"
              auto-complete="off"
              placeholder="学历"
            ></el-input>
          </el-form-item>
          <el-form-item prop="account">
            <el-input
              type="text"
              v-model="loginMode.userMark2"
              auto-complete="off"
              placeholder="专业"
            ></el-input>
          </el-form-item>
        
          <el-form-item style="width: 100%">
            <el-button type="primary" style="width: 100%" @click="login" >
              注册
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
      buzhiList: "",//下拉列表
    };
  },
  created() {
    this.getJcpeizhi();
    this.getBuzhiList();
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
    //获取buzhi
    getBuzhiList() {
      let para = {};
      this.listLoading = true;
      let url = base + "/buzhiComboList";
      request.post(url, para).then((res) => {
        if (res.code == 200) {
          this.buzhiList = res.resdata;
        }
      });
    },
    login() {
      let userName = this.loginMode.userName;
      let userPassword = this.loginMode.userPassword;
      let userXingming = this.loginMode.userXingming;
      let userAge = this.loginMode.userAge;
      let userPhone = this.loginMode.userPhone;
      let userMark = this.loginMode.userMark;
      let userMark1 = this.loginMode.userMark1;
      let userMark2 = this.loginMode.userMark2;
      if (!userName) {
        this.$message({
          message: "请输入用户名",
          type: "error",
        });
        return false;
      }
      if (!userPassword) {
        this.$message({
          message: "请输入密码",
          type: "error",
        });
        return false;
      }
      if (!userXingming) {
        this.$message({
          message: "请输入姓名",
          type: "error",
        });
        return false;
      }
      if (!userMark) {
        this.$message({
          message: "请输入学校",
          type: "error",
        });
        return false;
      }
      if (!userMark1) {
        this.$message({
          message: "请输入学历",
          type: "error",
        });
        return false;
      }
      if (!userMark2) {
        this.$message({
          message: "请输入专业",
          type: "error",
        });
        return false;
      }
      //年龄验证
			var reg1 = /^[0-9]{0,}$/;
			var reg2 = /^[1-9]{1}[0-9]{0,}$/;
      if(userAge ==null || userAge.length==0 || userAge.length>2){
				this.$message({
          message: "请输入有效的年龄！",
          type: "error",
        });
        return false;
			}
			if((!reg1.test(userAge))||(!reg2.test(userAge))){
				this.$message({
          message: "请输入有效的年龄！",
          type: "error",
        });
        return false;
			}
      //定义正则表达式部分手机号码
			var regphone = /^[1][0-9]{10}$/;
      if (!regphone.test(userPhone)) {
				this.$message({
          message: "请输入有效的手机号码！",
          type: "error",
        });
        return false;
			}


        let url = base + "/zhuceUser";
        this.loading = true;
        request.post(url, this.loginMode).then((res) => {
          this.loading = false;
          if (res.code == 200) {
            this.$message({
              message: "注册成功，请登陆！",
              type: "success",
            });
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
  margin: 80px auto;
  width: 360px;
  height: 750px;
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
  overflow: auto; /* 允许垂直和水平滚动 */
  width: 100vw;
  height: 100vh;
}

.login {
  width: 100vw;
  min-height: 100vh; /* 使内容超出时滚动 */
  padding: 0;
  margin: 0;
  font-size: 16px;
  background-position: left top;
  background-color: #242645;
  color: #fff;
  font-family: "Source Sans Pro";
  position: relative;
  background-image: url("../../assets/shouye/image/shouye.jpg");
  background-repeat: no-repeat;
  background-size: 100% 100%;
  overflow: auto; /* 确保内容过长时可滚动 */
}
/* 注册按钮统一配色（绿色主题） */
.el-button--primary,
.btn_zhuce {
  background-color: #21792c !important;
  border-color: #21792c !important;
  color: #fff !important;
  transition: 0.3s;
}

.el-button--primary:hover,
.btn_zhuce:hover {
  background-color: #14531f !important; /* 悬停时稍深 */
  border-color: #14531f !important;
}

/* 如果有次级按钮（例如用户 / 企业） */
.el-button--default {
  border-color: #21792c !important;
  color: #21792c !important;
  background-color: #fff !important;
}
.el-button--default:hover {
  background-color: #21792c !important;
  color: #fff !important;
}

</style>