<template>
  <div class="loginbody">
    <div class="loginback">

    </div>
    <div class="zhuce_body">
    <div class="zhuce_kong login_kuang">
    	<div class="zc">
        	<div class="bj_bai">
            <h3>{{loginJcpeizhi.jcpeizhiName}}</h3>
            <el-form :mode="loginMode" ref="loginForm" label-position="left" label-width="0px" class="demo-ruleForm">
            <ul>
              <li>
                <el-form-item>
                  <el-input
                    style="width:80%;"
                    type="text"
                    v-model="loginMode.userName"
                    auto-complete="off"
                    placeholder="用户名"
                  ></el-input>
                </el-form-item>
              </li>
              <li>
                <el-form-item>
                  <el-input
                    style="width:80%;"
                    type="password"
                    v-model="loginMode.password"
                    auto-complete="off"
                    placeholder="密码"
                  ></el-input>
                </el-form-item>
              </li>
              <li>
                <el-form-item>
                  <el-select
                    style="width:80%;"
                    v-model="loginMode.loginType"
                    placeholder="角色"
                
                  >
                  <el-option :label=loginJcpeizhi.userBieming value="user"> </el-option>
                  <el-option :label=loginJcpeizhi.yonghuBieming value="yonghu"> </el-option>
                  <el-option label="管理员" value="admin"> </el-option>
                </el-select>
                </el-form-item>
              </li>
              <li><el-button type="primary" value="登 录" class="btn_zhuce" style="width:80%"  @click="login">登录</el-button></li>
            </ul>
          </el-form>
            </div>
        	<div class="bj_right">
            	<p>如果没有账号请点击</p>
                <a :href="'/userzhuce'" class="zhuce_qq">{{loginJcpeizhi.userBieming}}</a>
                <a :href="'/yonghuzhuce'" class="zhuce_qq">{{loginJcpeizhi.yonghuBieming}}</a>
            
            </div>
        </div>
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
      let userName = this.loginMode.userName;
      let password = this.loginMode.password;
      let loginType = this.loginMode.loginType;
      if (!userName) {
        this.$message({
          message: "请输入用户名",
          type: "error",
          offset: 320,
        });
        return false;
      }
      if (!password) {
        this.$message({
          message: "请输入密码",
          type: "error",
          offset: 320,
        });
        return false;
      }

       if (!loginType) {
        this.$message({
          message: "请选择用户类型",
          type: "error",
          offset: 320,
        });
        return false;
      }


        let url = base + "/login";
        this.loading = true;
        // 输出这句话：
        //console.log("提交前测试");

        request.post(url, this.loginMode).then((res) => {
          // 输出这句话：
          //console.log("返回值测试");
          this.loading = false;
          if (res.code == 200) {
            sessionStorage.setItem("currentUser", JSON.stringify(res.resdata));
            //console.log("res.resdata：");
            //console.log(JSON.stringify(res.resdata));
            sessionStorage.setItem("role",res.role);
            //console.log("res.role");
            //console.log(JSON.stringify(res.role));
            sessionStorage.setItem("jcpeizhi",JSON.stringify(res.jcpeizhi));
            console.log("res.jcpeizhi");
            console.log(JSON.stringify(res.jcpeizhi));
            sessionStorage.setItem("jcbiaotis",JSON.stringify(res.jcbiaotis));
            //console.log("res.jcbiaotis");
            //console.log(JSON.stringify(res.jcbiaotis));
            sessionStorage.setItem("jcdaohangslist",JSON.stringify(res.jcdaohangslist));
            //console.log("res.jcdaohangslist");
            //console.log(JSON.stringify(res.jcdaohangslist));
            if(res.role=="user"){
              this.$router.push("/webindex");
            }else{
              this.$router.push("/houtaimain");
            }
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

<style scoped>
html, body {
  height: 100%;
  margin: 0;
  padding: 0;
  font-family: "Microsoft YaHei", sans-serif;
}

/* 整个页面容器 */
.loginbody {
  display: flex;
  justify-content: center;  /* 水平居中 */
  align-items: center;      /* 垂直居中 */
  min-height: 100vh;
  background: url("../../assets/shouye/image/shouye.jpg") no-repeat center center;
  background-size: cover;
  overflow: hidden;
}

/* 登录卡片整体（登录 + 注册右侧） */
.zhuce_kong {
  width: 640px;
  display: flex;
  border-radius: 12px;
  overflow: hidden;
  background: rgba(255, 255, 255, 0.96);
  box-shadow: 0 12px 32px rgba(0, 0, 0, 0.28);
  backdrop-filter: saturate(140%) blur(4px);
}

/* 左侧登录块 */
.bj_bai {
  flex: 1;
  background: transparent;
  padding: 36px 40px;
  display: flex;
  flex-direction: column;
  justify-content: center;
}

.bj_bai h3 {
  color: #21792c;
  font-size: 20px;
  margin-bottom: 20px;
  font-weight: 600;
}

/* 去掉 ul 的默认圆点 */
.bj_bai ul {
  list-style: none;
  padding: 0;
  margin: 0;
}
.bj_bai ul li {
  margin-bottom: 14px;
}
.bj_bai ul li:last-child {
  margin-bottom: 0;
}

/* Element 输入框和选择框 */
.el-input,
.el-select {
  width: 100% !important;
}

/* 聚焦时主色高亮 */
:deep(.el-input__wrapper),
:deep(.el-select .el-select__wrapper) {
  box-shadow: 0 0 0 1px #e5e7eb inset;
  border-radius: 6px;
  transition: box-shadow .2s ease;
}
:deep(.el-input__wrapper.is-focus),
:deep(.el-select .el-select__wrapper.is-focused) {
  box-shadow: 0 0 0 2px #21792c inset;
}

/* 登录按钮 */
.btn_zhuce {
  width: 100%;
  height: 42px;
  background-color: #21792c !important;
  border: none;
  color: #fff;
  font-size: 15px;
  border-radius: 6px;
  cursor: pointer;
  transition: background-color .2s ease, transform .05s ease;
}
.btn_zhuce:hover {
  background-color: #14531f !important;
}
.btn_zhuce:active {
  transform: translateY(1px);
}

/* 右侧注册区 */
.bj_right {
  width: 220px;
  background: #f8f8f8;
  padding: 40px 25px;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  text-align: center;
}
.bj_right p {
  color: #666;
  font-size: 13px;
  margin-bottom: 12px;
}
.bj_right .zhuce_qq {
  display: block;
  width: 150px;
  height: 36px;
  line-height: 36px;
  margin: 8px auto;
  border: 1px solid #21792c;
  color: #21792c;
  border-radius: 6px;
  background-color: #fff;
  transition: 0.3s;
}
.bj_right .zhuce_qq:hover {
  background-color: #21792c;
  color: #fff;
}

/* 小屏适配 */
@media (max-width: 640px) {
  .zhuce_kong {
    width: 90vw;
    flex-direction: column;
  }
  .bj_right {
    width: 100%;
    padding: 20px 16px 24px;
    border-top: 1px solid #eee;
  }
}

/* 让中间容器成为横向弹性布局，左右分栏 */
.zhuce_kong .zc {
  display: flex;
  flex-direction: row;
  align-items: stretch;   /* 左右等高 */
  width: 100%;
}

/* 取消旧的 float，启用弹性宽度 */
.zhuce_kong .zc .bj_bai,
.zhuce_kong .zc .bj_right {
  float: none !important;
}

/* 左侧：自适应填充；右侧：固定 220px */
.zhuce_kong .zc .bj_bai {
  flex: 1 1 auto;
  min-width: 0;           /* 防止子元素把容器挤爆 */
}

.zhuce_kong .zc .bj_right {
  flex: 0 0 220px;        /* 右侧固定宽度 */
  width: 220px;           /* 双保险 */
  border-left: 1px solid #eee;  /* 视觉分隔，可选 */
}

/* 如果小屏需要上下堆叠 */
@media (max-width: 640px) {
  .zhuce_kong .zc { flex-direction: column; }
  .zhuce_kong .zc .bj_right {
    flex: 0 0 auto;
    width: 100%;
    border-left: 0;
    border-top: 1px solid #eee;
  }
}

/* 让表单里的所有控件占满同一宽度（覆盖内联的 80%） */
.bj_bai ul,
.bj_bai ul li { padding: 0; margin: 0; }

.bj_bai :deep(.el-form-item),
.bj_bai :deep(.el-form-item__content) {
  width: 100% !important;
}

/* 输入框、下拉统一 100% */
.bj_bai .el-input,
.bj_bai .el-select {
  width: 100% !important;
}

/* 登录按钮也统一 100% 宽度（覆盖内联样式） */
.bj_bai .btn_zhuce {
  width: 100% !important;
}

/* 让每一行之间间距一致 */
.bj_bai ul li { margin-bottom: 14px; }
.bj_bai ul li:last-child { margin-bottom: 0; }

/* 如果按钮看起来仍有轻微缩进，去掉 li 的文本对齐影响 */
.bj_bai ul li { text-align: left; }


</style>
