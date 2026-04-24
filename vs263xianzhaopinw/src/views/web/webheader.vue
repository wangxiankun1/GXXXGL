<template>
  <header class="site-header-green">
    <!-- 第一层：欢迎条 -->
<!--    <div class="header-bar-green">-->
<!--      <div class="header-bar-inner">-->
<!--        <span>欢迎来到高校就业网站！</span>-->
<!--        <span>电话：400-8888-8888</span>-->
<!--        <span>邮箱：info@example.com</span>-->
<!--      </div>-->
<!--    </div>-->

    <!-- 第二层：Logo+标题+搜索 -->
    <div class="header-main-green">
      <div class="container-1600 header-main-inner">
        <div class="logo-title-green">
          <a href="/webindex" class="logo-title-green" style="text-decoration:none;">
            <img :src="logoUrl" alt="Logo" class="logo-green" />
            <span class="site-title-green">{{ webJcpeizhi.jcpeizhiName }}</span>
          </a>
        </div>
        <div class="header-search-green">
          <input type="text" :placeholder="webJcpeizhi.shujuBieming + '名称'" v-model="formData.shujuName">
          <button @click="query">
            <img src="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/icons/search.svg" alt="搜索" />
          </button>
        </div>
      </div>
    </div>

    <!-- 第三层：导航栏 -->
    <div class="header-nav-green">
      <nav class="navbar-green container-1600">
        <a href="/webindex">网站首页</a>
        <a href="/sjxiaoxiShow?sjxiaoxiId=1">关于我们</a>
        <a href="/sjxiaoxiShow?sjxiaoxiId=2">联系我们</a>
        <a href="/gonggaoList">{{ webJcpeizhi.gonggaoBieming }}信息</a>
        <a href="/yonghuList">{{ webJcpeizhi.yonghuBieming }}信息</a>
        <a href="/shujuList">{{ webJcpeizhi.shujuBieming }}信息</a>
        <a href="/shujuTuijian">推荐{{ webJcpeizhi.shujuBieming }}</a>
        <a href="/sjduochuList">{{ webJcpeizhi.sjduochuBieming }}交流</a>
        <a href="/login" v-if="role != 'user'">登录注册</a>
        <a href="/houtaimain" v-if="role == 'user'">个人中心</a>
      </nav>
    </div>

    <!-- 第四层：全屏轮播图 -->
    <div class="header-banner-green">
      <img :src="bannerImages[currentIndex]" alt="轮播图" />
      <div class="banner-dots">
        <span
          v-for="(img, idx) in bannerImages"
          :key="idx"
          :class="{ active: idx === currentIndex }"
        ></span>
      </div>
    </div>
    <!-- 悬浮图片按钮：AI智能问答 -->
    <a
      class="float-side-btn"
      href="/aichat"
      target="_blank"
      rel="noopener noreferrer"
      aria-label="AI智能助手"
      title="AI智能助手"
    >
      <img class="float-side-img" src="../../assets/ai.png" alt="AI智能助手" />
    </a>
  </header>
</template>


<script>
import request, { base } from "../../../utils/http";

export default {
  data() {
    return {
      searchInput: '',
      currentIndex: 0,
      bannerImages: [
        require('@/assets/daohang/daohang1.jpg'),
        require('@/assets/daohang/daohang2.jpg'),
        require('@/assets/daohang/daohang3.jpg')
      ],
      webJcpeizhi: [],
      role: "",
      webUser: [],
      formData: {},//搜索条件
      logoUrl: require('@/assets/logo.jpg')  // ✅ 修改为 Webpack 兼容方式
    };
  },
  created() {
    this.getJcpeizhi();
  },
  mounted() {
    // 轮播图定时器
    setInterval(() => {
      this.currentIndex = (this.currentIndex + 1) % this.bannerImages.length;
    }, 3000);

    const role = sessionStorage.getItem("role");
    this.role = role || "";
    if (role === "user") {
      const user = sessionStorage.getItem("currentUser");
      this.webUser = JSON.parse(user);
    }
  },
  methods: {
    getJcpeizhi() {
      let url = base + "/jcpeizhiComboList";
      request.post(url, {}).then((res) => {
        this.webJcpeizhi = res.resdata;
      });
    },
    //搜索
    query() {
      let shujuName = this.formData.shujuName;
      if (shujuName != null && shujuName != '') {
        this.$router.push("/shujuList?shujuName=" + shujuName);
      } else {
        this.$router.push("/shujuList?shujuName=" + "");
      }
    },
  }
};
</script>

<style scoped>
.site-header-green {
  font-family: 'Segoe UI', 'Arial', sans-serif;
  background: #f9fcf7;
  box-shadow: 0 2px 12px #165c2320;
}

/* 第一层：欢迎条 */
.header-bar-green {
  width: 100%;
  background: #e6fbe8;
  color: #21792c;
  font-size: 15px;
  border-bottom: 1px solid #c6ebcf;
}
.header-bar-inner {
  max-width: 1600px;
  margin: 0 auto;
  padding: 0 32px;
  height: 38px;
  display: flex;
  align-items: center;
  justify-content: flex-end;
  gap: 32px;
  letter-spacing: 0.5px;
}
.header-bar-inner span:first-child {
  margin-right: auto;
  font-weight: bold;
}
.site-title-green .site-home-link {
  color: #2a8f2a;
  font-size: 1.1em;
  font-weight: 700;
  margin-right: 10px;
  text-decoration: none;
  border-radius: 12px;
  padding: 3px 10px 2px 10px;
  background: #e6fbe8;
  transition: background .18s, color .18s;
}
.site-title-green .site-home-link:hover {
  background: #2a8f2a;
  color: #fff;
}


/* 通用1600px宽度限制 */
.container-1600 {
  max-width: 1600px;
  margin: 0 auto;
  padding: 0 32px;
  box-sizing: border-box;
}

/* 第二层：Logo+标题+搜索 */
.header-main-green {
  background: #fff;
  border-bottom: 1.5px solid #e7f2e5;
  box-shadow: 0 1px 6px #14691d13;
}
.header-main-inner {
  display: flex;
  justify-content: space-between;
  align-items: center;
  min-height: 92px;
}
.logo-title-green {
  display: flex;
  align-items: center;
  gap: 16px;
}
.logo-green {
  width: 62px;
  height: 62px;
  border-radius: 50%;
  background: #eaf9ec;
  border: 2px solid #2a8f2a;
  box-shadow: 0 0 8px #2a8f2a30;
}
.site-title-green {
  font-size: 2.4rem;
  font-weight: 800;
  color: #278b2a;
  letter-spacing: 3px;
  white-space: nowrap;
}

/* 搜索框 */
.header-search-green {
  display: flex;
  width: 290px;
  background: #f3faf2;
  border: 1.5px solid #2a8f2a66;
  border-radius: 30px;
  box-shadow: 0 2px 8px #38c3670a;
  overflow: hidden;
}
.header-search-green input {
  flex: 1;
  border: none;
  outline: none;
  background: transparent;
  padding: 13px 16px;
  font-size: 16px;
  color: #22722a;
}
.header-search-green button {
  width: 45px;
  background: #2a8f2a;
  border: none;
  display: flex;
  align-items: center;
  justify-content: center;
  cursor: pointer;
  transition: background .2s;
}
.header-search-green button:hover {
  background: #368b36;
}
.header-search-green img {
  width: 21px;
  height: 21px;
  filter: brightness(0) invert(1);
}

/* 第三层：导航栏 */
.header-nav-green {
  width: 100%;
  background: #2a8f2a;
  border-bottom: 2px solid #368b36;
}
.navbar-green {
  display: flex;
  justify-content: center;
  align-items: center;
  gap: 48px;
  min-height: 52px;
}
.navbar-green a {
  color: #fff;
  font-size: 1.15rem;
  font-weight: 600;
  padding: 6px 18px 4px 18px;
  text-decoration: none;
  border-radius: 4px;
  transition: background .18s, color .18s;
  letter-spacing: 1.5px;
}
.navbar-green a:hover, .navbar-green a.router-link-active {
  background: #e6fbe8;
  color: #22722a;
}

/* 第四层：全屏轮播图 */
.header-banner-green {
  width: 100vw;
  min-width: 100vw;
  position: relative;
  left: 50%;
  right: 50%;
  margin-left: -50vw;
  margin-right: -50vw;
  height: 410px;
  background: #d6f9e3;
  overflow: hidden;
  border-bottom-left-radius: 16px;
  border-bottom-right-radius: 16px;
  box-shadow: 0 8px 28px #2a8f2a16;
}
.header-banner-green img {
  width: 100vw;
  height: 100%;
  object-fit: cover;
  display: block;
  transition: opacity .5s;
}

/* 轮播指示点 */
.banner-dots {
  position: absolute;
  left: 50%;
  bottom: 26px;
  transform: translateX(-50%);
  display: flex;
  gap: 12px;
}
.banner-dots span {
  width: 16px;
  height: 16px;
  border-radius: 50%;
  background: #fff;
  opacity: 0.55;
  border: 3px solid #2a8f2a;
  transition: all .25s;
}
.banner-dots span.active {
  background: #2a8f2a;
  opacity: 1;
  border-color: #e7ffe5;
}

@media (max-width: 1700px) {
  .header-bar-inner,
  .container-1600,
  .navbar-green {
    padding-left: 16px;
    padding-right: 16px;
  }
}
@media (max-width: 1100px) {
  .header-main-inner, .navbar-green, .header-bar-inner {
    flex-direction: column;
    align-items: flex-start;
    gap: 12px;
  }
  .header-main-inner {
    min-height: 110px;
  }
  .header-banner-green {
    height: 180px;
  }
  .site-title-green {
    font-size: 1.4rem;
  }
}
@media (max-width: 700px) {
  .header-banner-green {
    height: 96px;
    border-radius: 0;
  }
  .header-search-green {
    width: 100%;
    margin-top: 8px;
  }
  .navbar-green {
    gap: 10px;
    font-size: 0.98rem;
    flex-wrap: wrap;
    justify-content: flex-start;
    min-height: 38px;
  }
}
/* 悬浮图片按钮（右侧中部，通用） */
.float-side-btn {
  position: fixed;
  top: 40%;
  right: 20px;
  transform: translateY(-50%);
  display: inline-flex;
  align-items: center;
  justify-content: center;

  /* ========================================= */
  /* 核心修改：改为正方形，且尺寸大幅缩小 */
  /* ========================================= */
  width: 70px;           /* 从 120px 缩小到 70px */
  height: 70px;          /* 从 280px 缩小到 70px，保持正方形 */

  /* 调整内边距，让图片在圈内有呼吸感 */
  padding: 6px;

  /* ========================================= */
  /* 视觉样式修改：模仿图二的青色光圈效果 */
  /* ========================================= */
  background: #0b1220;    /* 保持你的深色底框 */

  /* 将边框改为青色，并加粗，模拟光圈 */
  border: 2px solid #00f2fe;

  /* 必须是 50% 才能实现完美的圆形 */
  border-radius: 50%;

  /* 增强阴影，加入青色外发光，更有 AI 科技感 */
  box-shadow: 0 4px 15px rgba(0,0,0,0.5),
  0 0 15px rgba(0,242,254,0.4); /* 青色发光 */

  text-decoration: none;
  z-index: 9999;
  transition: all .2s ease; /* 简化 transition */
}

.float-side-btn:hover {
  /* hover 时轻微放大和增强发光 */
  transform: translateY(-50%) scale(1.1);
  box-shadow: 0 6px 20px rgba(0,0,0,0.6),
  0 0 25px rgba(0,242,254,0.6);
  background: #142039;
}

/* 图片在容器内居中并裁剪为圆形 */
.float-side-img {
  width: 100%;
  height: 100%;

  /* ========================================= */
  /* 核心修改： object-fit 必须为 cover */
  /* 这样图片虽然是竖图，但会截取中间正方形区域填满容器 */
  /* ========================================= */
  object-fit: cover;

  /* 必须也是 50%，保证裁剪出来是圆形的 */
  border-radius: 50%;
  display: block;
}

/* 移动端适配：缩小，避免遮挡 */
@media (max-width: 700px) {
  .float-side-btn {
    right: 10px;
    width: 50px;  /* 手机端进一步缩小 */
    height: 50px;
    padding: 4px;
    border-width: 1.5px; /* 边框也相应变细 */
  }
}

</style>
