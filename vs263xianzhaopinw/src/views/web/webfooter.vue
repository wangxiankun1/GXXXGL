<template>
  <footer class="footer">
    <div class="footer-container">

      <div class="footer-row links-row">
<!--        <div class="row-label">友情链接</div>-->
        <div class="links-wrapper">
          <a
              v-for="item in sjlianjieList"
              :key="item.sjlianjieId"
              :href="item.sjlianjieMark"
              target="_blank"
              class="link-item"
          >
            {{ item.sjlianjieName }}
          </a>
        </div>
      </div>

<!--      <hr class="footer-divider" />-->

<!--      <div class="footer-row brand-row">-->
<!--        <div class="footer-logo">-->
<!--          <img :src="logoUrl" alt="logo" />-->
<!--          <span class="brand-name">{{ webJcpeizhi.jcpeizhiName }}</span>-->
<!--        </div>-->
<!--        <div class="footer-contact-simple">-->
<!--          <span>西安市碑林区幸福街道001号</span>-->
<!--          <span class="dot">·</span>-->
<!--          <span>info@example.com</span>-->
<!--        </div>-->
<!--      </div>-->

      <div class="footer-row bottom-row">
        <div class="copyright-info">
          <span>© 2026 高校就业信息管理 保留所有权利</span>
          <span class="sep">|</span>
          <a href="https://beian.miit.gov.cn/" target="_blank">ICP备案号：xxxx ICP备xxx-xxxx号</a>
        </div>
      </div>

    </div>
  </footer>
</template>

<script>
import request, { base } from "../../../utils/http";

export default {
  data() {
    return {
      webJcpeizhi: {},
      sjlianjieList: [],
      logoUrl: require('@/assets/logo.jpg')
    };
  },
  created() {
    this.getJcpeizhi();
    this.getSjlianjieList();
  },
  methods: {
    getJcpeizhi() {
      let url = base + "/jcpeizhiComboList";
      request.post(url, {}).then((res) => {
        this.webJcpeizhi = res.resdata;
      });
    },
    getSjlianjieList() {
      let url = base + "/getSjlianjies";
      request.post(url, {}).then((res) => {
        if (res.code === 200) {
          this.sjlianjieList = res.resdata;
        }
      });
    }
  }
};
</script>

<style scoped>
.footer {
  background-color: #f8fdf6;
  border-top: 3px solid #368b36;
  padding: 40px 20px 20px 20px;
  color: #225e2a;
  font-family: 'PingFang SC', 'Microsoft YaHei', sans-serif;
}

.footer-container {
  max-width: 1200px;
  margin: 0 auto;
  display: flex;
  flex-direction: column;
  gap: 10px;
}

/* 通用行样式 */
.footer-row {
  display: flex;
  align-items: center;
  width: 100%;
}

/* 友情链接行 */
.links-row {
  flex-wrap: wrap;
  justify-content: center;
  gap: 15px;
}
.row-label {
  font-weight: bold;
  font-size: 15px;
  color: #368b36;
  margin-right: 10px;
  white-space: nowrap;
}
.links-wrapper {
  display: flex;
  flex-wrap: wrap;
  gap: 12px;
}
.link-item {
  text-decoration: none;
  color: #558b55;
  font-size: 14px;
  padding: 4px 12px;
  background: #fff;
  border: 1px solid #e0eee0;
  border-radius: 20px;
  transition: all 0.3s ease;
}
.link-item:hover {
  background: #368b36;
  color: #fff;
  border-color: #368b36;
  transform: translateY(-2px);
}

/* 分割线 */
.footer-divider {
  border: none;
  border-top: 1px solid #e6f2e6;
  margin: 0;
}

/* Brand 行 */
.brand-row {
  justify-content: space-between;
}
.footer-logo {
  display: flex;
  align-items: center;
  gap: 12px;
}
.footer-logo img {
  width: 45px;
  height: 45px;
  border-radius: 8px;
}
.brand-name {
  font-size: 20px;
  font-weight: bold;
  color: #278b2a;
}
.footer-contact-simple {
  font-size: 14px;
  color: #668b66;
}
.dot {
  margin: 0 10px;
  font-weight: bold;
}

/* 备案行 */
.bottom-row {
  justify-content: center;
  margin-top: 10px;
  padding-top: 20px;
  border-top: 1px solid #e6f2e6;
}
.copyright-info {
  font-size: 13px;
  color: #8da48d;
  display: flex;
  align-items: center;
  gap: 10px;
}
.copyright-info a {
  color: #8da48d;
  text-decoration: none;
}
.copyright-info a:hover {
  text-decoration: underline;
}
.sep {
  color: #d0ded0;
}

/* 响应式适配 */
@media (max-width: 768px) {
  .brand-row {
    flex-direction: column;
    gap: 15px;
    align-items: flex-start;
  }
  .links-row {
    flex-direction: column;
    align-items: flex-start;
  }
  .copyright-info {
    flex-direction: column;
    text-align: center;
    gap: 5px;
  }
  .sep {
    display: none;
  }
}
</style>