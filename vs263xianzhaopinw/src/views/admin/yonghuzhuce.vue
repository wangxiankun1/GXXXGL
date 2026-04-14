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
          <h3 class="title">{{loginJcpeizhi.yonghuBieming}}注册</h3>
          <el-form-item prop="account">
            <el-input
              type="text"
              v-model="loginMode.yonghuName"
              auto-complete="off"
              placeholder="用户名"
            ></el-input>
          </el-form-item>
          <el-form-item prop="checkPass">
            <el-input
              type="password"
              v-model="loginMode.yonghuPassword"
              auto-complete="off"
              placeholder="密码"
            ></el-input>
          </el-form-item>
          <el-form-item prop="account">
            <el-input
              type="text"
              v-model="loginMode.yonghuMark1"
              auto-complete="off"
              placeholder="公司"
            ></el-input>
          </el-form-item>
          <el-form-item prop="account">
            <el-input
              type="text"
              v-model="loginMode.yonghuMark2"
              auto-complete="off"
              placeholder="地址"
            ></el-input>
          </el-form-item>
          <el-form-item prop="account">
            <el-input
              type="text"
              v-model="loginMode.yonghuXingming"
              auto-complete="off"
              placeholder="联系人"
            ></el-input>
          </el-form-item>
          <el-form-item prop="checkPass">
            <el-select style="width:288px;" v-model="loginMode.yonghuSex" placeholder="性别">
              <el-option label="男" value="0"> </el-option>
              <el-option label="女" value="1"> </el-option>
            </el-select>
          </el-form-item>
          <el-form-item prop="account">
            <el-input
              type="text"
              v-model="loginMode.yonghuAge"
              auto-complete="off"
              placeholder="年龄"
            ></el-input>
          </el-form-item>
          <el-form-item prop="account">
            <el-input
              type="text"
              v-model="loginMode.yonghuPhone"
              auto-complete="off"
              placeholder="电话"
            ></el-input>
          </el-form-item>
          <el-form-item prop="buzhiId">
            <el-select v-model="loginMode.buzhiId" :placeholder=loginJcpeizhi.buzhiBieming>
              <el-option label="请选择" value="0"> </el-option>
              <el-option v-for="item in buzhiList" :key="item.buzhiId" :label="item.buzhiName" :value="item.buzhiId"></el-option>
            </el-select>
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
      let yonghuName = this.loginMode.yonghuName;
      let yonghuPassword = this.loginMode.yonghuPassword;
      let yonghuXingming = this.loginMode.yonghuXingming;
      let yonghuAge = this.loginMode.yonghuAge;
      let yonghuPhone = this.loginMode.yonghuPhone;
      let yonghuMark1 = this.loginMode.yonghuMark1;
      let yonghuMark2 = this.loginMode.yonghuMark2;
      if (!yonghuName) {
        this.$message({
          message: "请输入用户名",
          type: "error",
        });
        return false;
      }
      if (!yonghuPassword) {
        this.$message({
          message: "请输入密码",
          type: "error",
        });
        return false;
      }
      if (!yonghuXingming) {
        this.$message({
          message: "请输入联系人",
          type: "error",
        });
        return false;
      }
      if (!yonghuMark1) {
        this.$message({
          message: "请输入公司名",
          type: "error",
        });
        return false;
      }
      if (!yonghuMark2) {
        this.$message({
          message: "请输入地址",
          type: "error",
        });
        return false;
      }
      //年龄验证
			var reg1 = /^[0-9]{0,}$/;
			var reg2 = /^[1-9]{1}[0-9]{0,}$/;
      if(yonghuAge ==null || yonghuAge.length==0 || yonghuAge.length>2){
				this.$message({
          message: "请输入有效的年龄！",
          type: "error",
        });
        return false;
			}
			if((!reg1.test(yonghuAge))||(!reg2.test(yonghuAge))){
				this.$message({
          message: "请输入有效的年龄！",
          type: "error",
        });
        return false;
			}
      //定义正则表达式部分手机号码
			var regphone = /^[1][0-9]{10}$/;
      if (!regphone.test(yonghuPhone)) {
				this.$message({
          message: "请输入有效的手机号码！",
          type: "error",
        });
        return false;
			}


        let url = base + "/zhuceYonghu";
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