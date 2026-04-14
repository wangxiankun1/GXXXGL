<template>
  <div class="announcement-detail-page">
    <div class="container">
      <!-- 右侧：详情内容 -->
      <section class="detail-content">
        <div class="top-info">
          <h2>{{ sjxiaoxiShow.sjxiaoxiName }}</h2>
        </div>
        <div class="middle-info">
          <div class="content" v-html="sjxiaoxiShow.sjxiaoxiMark"></div>
        </div>
      </section>
      <!-- 左侧：分类 -->
      <aside class="sidebar">
        <h3>基础信息</h3>
        <ul class="category-list">
          <li @click="goToType(1)">关于我们</li>
          <li @click="goToType(2)">联系我们</li>
        </ul>
      </aside>
    </div>
  </div>
</template>

<script>
import request, { base } from "../../../../utils/http";

export default {
  data() {
    return {
      webJcpeizhi: [],
      sjxiaoxiList: [],
      sjxiaoxiShow: {},
      formData: {},
      sjxiaoxiId: "",
    };
  },
  created() {
    this.sjxiaoxiId = this.$route.query.sjxiaoxiId;
    this.getJcpeizhi();
  },
  watch: {
    '$route.query.sjxiaoxiId': {
      immediate: true,
      handler(newVal) {
        this.sjxiaoxiId = newVal;
        this.getSjxiaoxiList();
      }
    }
  },
  methods: {
    getSjxiaoxiList() {
      const para = { sjxiaoxiId: this.sjxiaoxiId };
      request.post(`${base}/getSjxiaoxis`, para).then((res) => {
        if (res.code === 200) {
          this.sjxiaoxiList = res.resdata;
          this.sjxiaoxiShow = this.sjxiaoxiList[0] || {};
        }
      });
    },
    getJcpeizhi() {
      request.post(`${base}/jcpeizhiComboList`, {}).then((res) => {
        this.webJcpeizhi = res.resdata;
      });
    },
    goToType(sjxiaoxiId) {
      this.$router.push(`/sjxiaoxiShow?sjxiaoxiId=${sjxiaoxiId}`);
    },
  },
};
</script>

<style scoped>
.announcement-detail-page {
  background: linear-gradient(120deg, #e6fbe8 0%, #f8fdf6 100%);
  padding: 48px 0 48px 0;
  font-family: 'Segoe UI', 'Arial', sans-serif;
  min-height: 100vh;
}
.container {
  display: flex;
  max-width: 1400px;
  margin: auto;
  gap: 48px;
  align-items: flex-start;
  box-sizing: border-box;
}

/* 左侧侧边栏 */
.sidebar {
  flex: 0 0 250px;
  background: #fff;
  padding: 28px 18px 28px 18px;
  border-radius: 18px;
  border: 1.5px solid #d4efde;
  box-shadow: 0 4px 18px #38c36718;
}
.sidebar h3 {
  font-size: 20px;
  margin-bottom: 20px;
  color: #2a8f2a;
  letter-spacing: 1px;
  font-weight: 700;
}
.category-list {
  list-style: none;
  padding: 0;
  margin-bottom: 28px;
}
.category-list li {
  background: linear-gradient(90deg, #f7fbff 80%, #eaf9ec 100%);
  color: #2a8f2a;
  font-weight: 600;
  padding: 10px 14px;
  margin-bottom: 14px;
  border-radius: 9px;
  border: 1.2px solid #d4efde;
  cursor: pointer;
  transition: background 0.22s, color 0.22s, box-shadow 0.18s;
  box-shadow: 0 2px 6px #38c36710;
}
.category-list li:hover,
.category-list li.active {
  background: linear-gradient(90deg, #e3f6ea 70%, #d3f6dd 100%);
  color: #158c45;
  box-shadow: 0 4px 16px #38c36720;
}

/* 右侧详情内容 */
.detail-content {
  flex: 1;
  min-width: 0;
  display: flex;
  flex-direction: column;
  gap: 28px;
}

/* 上层：标题卡片 */
.top-info {
  background: #fff;
  padding: 28px 32px 18px 32px;
  border-radius: 16px;
  border: 1.5px solid #d4efde;
  box-shadow: 0 6px 26px #38c36716;
}
.top-info h2 {
  font-size: 26px;
  margin: 0 0 10px 0;
  color: #2a8f2a;
  font-weight: 700;
  letter-spacing: 2px;
}

/* 中层：正文卡片 */
.middle-info {
  background: #fff;
  padding: 32px 32px 26px 32px;
  border-radius: 16px;
  border: 1.5px solid #d4efde;
  box-shadow: 0 4px 18px #38c36718;
}
.middle-info .content {
  font-size: 17px;
  line-height: 2;
  color: #237a30;
  word-break: break-all;
}

/* 通用链接样式 */
a {
  color: #24913e;
  text-decoration: none;
  transition: color 0.2s;
  font-weight: 600;
}
a:hover {
  color: #127427;
}

/* 响应式 */
@media (max-width: 1400px) {
  .container {
    max-width: 98vw;
    gap: 18px;
  }
}
@media (max-width: 950px) {
  .container {
    flex-direction: column;
    gap: 18px;
  }
  .sidebar {
    width: 100%;
    margin-bottom: 18px;
    min-width: 0;
    max-width: 100vw;
  }
  .detail-content {
    width: 100%;
    min-width: 0;
    max-width: 100vw;
  }
}
@media (max-width: 700px) {
  .top-info, .middle-info {
    padding: 14px 4px;
    border-radius: 8px;
  }
  .sidebar {
    padding: 10px 6px;
    border-radius: 8px;
  }
}
</style>
