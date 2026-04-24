<template>
  <div class="announcement-detail-page">
    <div class="container">

      <!-- 右侧：详情内容 -->
      <section class="detail-content">
        <!-- 上层：标题 -->
        <div class="top-info">
          <h2>{{ gonggaoShow.gonggaoName }}</h2>
          <p class="date">
            发布：ADMIN
            &nbsp;|&nbsp;
            {{ webJcpeizhi.ggtypeBieming }}：{{ gonggaoShow.ggtypeName }}
          </p>
        </div>

        <!-- 中层：详情正文 -->
        <div class="middle-info">
          <div class="content" v-html="gonggaoShow.gonggaoMark"></div>
        </div>
      </section>
      <!-- 左侧：分类 + 搜索 -->
      <aside class="sidebar">
        <h3>{{ webJcpeizhi.gonggaoBieming }}{{ webJcpeizhi.ggtypeBieming }}</h3>
        <ul class="category-list">
          <li
            v-for="ggtype in ggtypeList"
            :key="ggtype.ggtypeId"
            @click="goToType(ggtype.ggtypeId)"
          >
            {{ ggtype.ggtypeName }}
          </li>
        </ul>
        <div class="search-box">
          <input type="text" :placeholder="webJcpeizhi.gonggaoBieming + '名称'" v-model="formData.gonggaoName" />
          <button @click="query">
            <img src="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/icons/search.svg" alt="搜索" />
          </button>
        </div>
      </aside>
    </div>
  </div>
</template>

<script>
import request, { base } from "../../../../utils/http";
import pagination from "../../../components/Pagination";

export default {
  components: { pagination },
  data() {
    return {
      webJcpeizhi: [],
      gonggaoList: "",
      gonggaoShow: [],
      ggtypeList: "",
      formData: {},
      gonggaoId: "",
      num: 0,
      limit: 10,
      currentPage: 1,
    };
  },
  created() {
    this.gonggaoId = this.$route.query.gonggaoId;
    this.getJcpeizhi();
    this.getGonggaoList();
    this.getGgtypeList();
  },
  methods: {
    getGonggaoList() {
      const para = { gonggaoId: this.gonggaoId };
      request.post(`${base}/getGonggaos`, para).then((res) => {
        if (res.code === 200) {
          this.gonggaoList = res.resdata;
          this.gonggaoShow = this.gonggaoList[0];
        }
      });
    },
    getGgtypeList() {
      request.post(`${base}/getGgtypes`, {}).then((res) => {
        if (res.code === 200) {
          this.ggtypeList = res.resdata;
        }
      });
    },
    getJcpeizhi() {
      request.post(`${base}/jcpeizhiComboList`, {}).then((res) => {
        this.webJcpeizhi = res.resdata;
      });
    },
    query() {
      const gonggaoName = this.formData.gonggaoName;
      if (gonggaoName) {
        this.$router.push(`/gonggaoList?gonggaoName=${gonggaoName}`);
      } else {
        this.$router.push(`/gonggaoList`);
      }
    },
    addBuyuan(gonggaoId) {
      const role = sessionStorage.getItem("role");
      if (role === "user") {
        const user = JSON.parse(sessionStorage.getItem("currentUser"));
        const para = {
          buyuanType1: gonggaoId,
          buyuanType: user.userId,
        };
        request.post(`${base}/addBuyuan`, para).then((res) => {
          this.$message({ message: res.msg || "操作成功" });
          if (res.code === 200) this.getGonggaoList();
        });
      } else {
        this.$message({ message: "请登陆后操作" });
      }
    },
    goToType(ggtypeId) {
      this.$router.push(`/gonggaoList?ggtypeId=${ggtypeId}`);
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
  margin: 0 auto;
  gap: 48px;
  align-items: flex-start;
  box-sizing: border-box;
}

/* 左侧：分类 + 搜索 */
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
  margin-bottom: 26px;
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

/* 搜索框 */
.search-box {
  display: flex;
  width: 100%;
  overflow: hidden;
  border: 1.5px solid #d4efde;
  border-radius: 8px;
  background: #f8fbff;
  margin-bottom: 2px;
  box-shadow: 0 1px 4px #2a8f2a0a;
}
.search-box input {
  width: 0;
  flex: 1;
  min-width: 0;
  padding: 7px 10px;
  border: none;
  font-size: 15px;
  background: transparent;
  outline: none;
  color: #145732;
}
.search-box button {
  width: 38px;
  background: #2a8f2a;
  display: flex;
  align-items: center;
  justify-content: center;
  border: none;
  cursor: pointer;
  border-radius: 0 8px 8px 0;
  transition: background 0.2s;
}
.search-box button:hover {
  background: #19762e;
}
.search-box button img {
  width: 18px;
  height: 18px;
  filter: brightness(0) invert(1) sepia(1) hue-rotate(60deg) saturate(900%);
}

/* 右侧详情 */
.detail-content {
  flex: 1;
  min-width: 0;
  display: flex;
  flex-direction: column;
  gap: 26px;
}

/* 上层：标题卡片 */
.top-info {
  background: #fff;
  padding: 32px 34px 18px 34px;
  border-radius: 18px;
  border: 1.5px solid #d4efde;
  box-shadow: 0 8px 34px #38c3671e;
}
.top-info h2 {
  font-size: 27px;
  margin: 0 0 10px 0;
  color: #2a8f2a;
  font-weight: 700;
  letter-spacing: 2px;
}
.top-info .date {
  font-size: 15.5px;
  color: #4bc46d;
  letter-spacing: 1px;
}

/* 中层：正文卡片 */
.middle-info {
  background: #fff;
  padding: 36px 34px 30px 34px;
  border-radius: 18px;
  border: 1.5px solid #d4efde;
  box-shadow: 0 8px 34px #38c3671e;
}
.middle-info .content {
  font-size: 17px;
  line-height: 2;
  color: #237a30;
  word-break: break-all;
}

/* 评论与按钮(如果用到) */
.bottom-info {
  margin-top: 30px;
  background: #fff;
  padding: 22px;
  border-radius: 16px;
  border: 1.2px solid #d4efde;
  box-shadow: 0 4px 16px #38c36715;
}
.bottom-info h3 {
  margin-bottom: 10px;
}
.comment-list {
  list-style: none;
  padding: 0;
  margin-bottom: 20px;
}
.comment-list li {
  padding: 6px 0;
  border-bottom: 1px dashed #b2f3d6;
}
.comment-list .user {
  font-weight: bold;
  margin-right: 8px;
  color: #2a8f2a;
}
.comment-form textarea {
  width: 100%;
  height: 80px;
  padding: 8px;
  font-size: 15px;
  resize: vertical;
  border: 1.5px solid #d4efde;
  border-radius: 6px;
  margin-bottom: 10px;
  background: #f7fbff;
}
.comment-form button {
  background: #2a8f2a;
  color: white;
  padding: 8px 16px;
  border: none;
  border-radius: 7px;
  cursor: pointer;
  font-weight: 600;
  transition: background 0.2s;
}
.comment-form button:hover {
  background: #19762e;
}
.like-btn {
  background: #ffcb05;
  border: none;
  padding: 6px 12px;
  border-radius: 8px;
  cursor: pointer;
  font-weight: bold;
  margin-left: 10px;
  color: #333;
  transition: background 0.2s;
}
.like-btn:hover {
  background: #ffe16e;
}

/* 通用链接样式 */
a {
  color: #278b2a;
  text-decoration: none;
  transition: color 0.18s;
}
a:hover {
  color: #158c45;
}

/* 响应式 */
@media (max-width: 1200px) {
  .container {
    max-width: 98vw;
    gap: 22px;
  }
}
@media (max-width: 950px) {
  .container {
    flex-direction: column;
    gap: 16px;
  }
  .sidebar {
    width: 100%;
    margin-bottom: 24px;
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
  .top-info, .middle-info, .bottom-info {
    padding: 14px 4px 12px 4px;
    border-radius: 8px;
  }
  .sidebar {
    padding: 12px 8px;
    border-radius: 8px;
  }
}
</style>
