<template>
  <div class="announcement-detail-page">
    <div class="container">

      <!-- 右侧：详情内容 -->
      <section class="detail-content">
        <!-- 上层：标题 -->
        <div class="top-info">
          <h2>{{ sjduochuShow.sjduochuName }}</h2>
          <p class="date">
            发布：{{ sjduochuShow.userName }}
            &nbsp;|&nbsp;
            <button class="like-btn" @click="addBuyuan(sjduochuShow.sjduochuId)">
              👍 点赞 {{ sjduochuShow.sjduochuZong }}
            </button>
            &nbsp;|&nbsp;
            {{ webJcpeizhi.sjxingtaiBieming }}：{{ sjduochuShow.sjxingtaiName }}
          </p>
        </div>

        <!-- 中层：详情正文 -->
        <div class="middle-info">
          <div class="content" v-html="sjduochuShow.sjduochuMark"></div>
        </div>

        <!-- 下层：评论 -->
        <div class="bottom-info">
          <h3>评论信息</h3>
          <ul class="comment-list">
            <li v-for="sjqita in sjqitaList" :key="sjqita.sjqitaId">
              <span class="user">{{ sjqita.sjqitaPhone }}：</span>
              <span class="text">{{ sjqita.sjqitaName }}</span>
            </li>
          </ul>
          <div class="pagebox">
            <pagination :num="num" :limit="limit" @getList="getSjqitaList" />
          </div>
          <div class="comment-form">
            <textarea v-model="addSjqitaData.sjqitaName" placeholder="请输入评论内容..."></textarea>
            <button @click="addSjqita(sjduochuShow.sjduochuId)">提交评论</button>
          </div>
        </div>
      </section>
      <!-- 左侧：分类 + 搜索 -->
      <aside class="sidebar">
        <h3>{{ webJcpeizhi.sjduochuBieming }}{{ webJcpeizhi.sjxingtaiBieming }}</h3>
        <ul class="category-list">
          <li
            v-for="sjxingtai in sjxingtaiList"
            :key="sjxingtai.sjxingtaiId"
            @click="goToType(sjxingtai.sjxingtaiId)"
          >
            {{ sjxingtai.sjxingtaiName }}
          </li>
        </ul>
        <div class="search-box">
          <input type="text" :placeholder="webJcpeizhi.sjduochuBieming + '名称'" v-model="formData.sjduochuName" />
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
      sjduochuList: "",
      sjduochuShow: [],
      sjxingtaiList: "",
      formData: {},
      sjduochuId: "",
      sjqitaList: "",
      addSjqitaData: {},
      num: 0,
      limit: 10,
      currentPage: 1,
    };
  },
  created() {
    this.sjduochuId = this.$route.query.sjduochuId;
    this.getJcpeizhi();
    this.getSjduochuList();
    this.getSjqitaList();
    this.getSjxingtaiList();
  },
  methods: {
    getSjduochuList() {
      const para = { sjduochuId: this.sjduochuId };
      request.post(`${base}/getSjduochus`, para).then((res) => {
        if (res.code === 200) {
          this.sjduochuList = res.resdata;
          this.sjduochuShow = this.sjduochuList[0];
        }
      });
    },
    getSjqitaList(value) {
      this.currentPage = value ? (value + this.limit) / this.limit : 1;
      const para = { sjqitaType1: this.sjduochuId };
      const url = `${base}/getSjqitas?page=${this.currentPage}&rows=${this.limit}`;
      request.post(url, para).then((res) => {
        if (res.code === 200) {
          this.sjqitaList = res.resdata;
          this.num = res.count;
        }
      });
    },
    getSjxingtaiList() {
      request.post(`${base}/getSjxingtais`, {}).then((res) => {
        if (res.code === 200) {
          this.sjxingtaiList = res.resdata;
        }
      });
    },
    getJcpeizhi() {
      request.post(`${base}/jcpeizhiComboList`, {}).then((res) => {
        this.webJcpeizhi = res.resdata;
      });
    },
    query() {
      const sjduochuName = this.formData.sjduochuName;
      if (sjduochuName) {
        this.$router.push(`/sjduochuList?sjduochuName=${sjduochuName}`);
      } else {
        this.$router.push(`/sjduochuList`);
      }
    },
    addBuyuan(sjduochuId) {
      const role = sessionStorage.getItem("role");
      if (role === "user") {
        const user = JSON.parse(sessionStorage.getItem("currentUser"));
        const para = {
          buyuanType1: sjduochuId,
          buyuanType: user.userId,
        };
        request.post(`${base}/addBuyuan`, para).then((res) => {
          this.$message({ message: res.msg || "操作成功" });
          if (res.code === 200) this.getSjduochuList();
        });
      } else {
        this.$message({ message: "请登陆后操作" });
      }
    },
    addSjqita(sjduochuId) {
      const role = sessionStorage.getItem("role");
      const content = this.addSjqitaData.sjqitaName;
      if (!content) {
        this.$message({ message: "请填写评论内容" });
        return;
      }
      if (role === "user") {
        const user = JSON.parse(sessionStorage.getItem("currentUser"));
        const para = {
          sjqitaType1: sjduochuId,
          sjqitaType: user.userId,
          sjqitaName: content,
        };
        request.post(`${base}/addSjqita`, para).then((res) => {
          this.$message({ message: res.msg || "操作成功" });
          if (res.code === 200) {
            this.addSjqitaData.sjqitaName = "";
            this.getSjqitaList();
          }
        });
      } else {
        this.$message({ message: "请登陆后操作" });
      }
    },
    goToType(sjxingtaiId) {
      this.$router.push(`/sjduochuList?sjxingtaiId=${sjxingtaiId}`);
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
.top-info .date {
  font-size: 16px;
  color: #36a05c;
  letter-spacing: 1px;
  display: flex;
  flex-wrap: wrap;
  align-items: center;
  gap: 10px;
}

/* 点赞按钮 */
.like-btn {
  background: #ffeaab;
  border: none;
  padding: 6px 15px;
  border-radius: 8px;
  cursor: pointer;
  font-weight: 700;
  color: #735c00;
  transition: background 0.22s;
  margin: 0 4px;
  box-shadow: 0 2px 10px #ffe16e20;
  font-size: 15px;
}
.like-btn:hover {
  background: #ffe16e;
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

/* 评论区卡片 */
.bottom-info {
  margin-top: 10px;
  background: #fff;
  padding: 26px 32px;
  border-radius: 16px;
  border: 1.5px solid #d4efde;
  box-shadow: 0 4px 18px #38c36718;
}
.bottom-info h3 {
  margin-bottom: 16px;
  color: #2a8f2a;
  font-weight: 700;
  font-size: 20px;
}
.comment-list {
  list-style: none;
  padding: 0;
  margin-bottom: 20px;
}
.comment-list li {
  padding: 8px 0;
  border-bottom: 1px dashed #bfe6cc;
  font-size: 15px;
  color: #237a30;
}
.comment-list .user {
  font-weight: bold;
  margin-right: 8px;
  color: #2a8f2a;
}
.pagebox {
  margin-bottom: 18px;
}
.comment-form textarea {
  width: 100%;
  height: 88px;
  padding: 8px;
  font-size: 15px;
  resize: vertical;
  border: 1.5px solid #d4efde;
  border-radius: 8px;
  margin-bottom: 12px;
  background: #f8fbff;
  color: #145732;
}
.comment-form button {
  background: #2a8f2a;
  color: #fff;
  padding: 8px 24px;
  border: none;
  border-radius: 8px;
  cursor: pointer;
  font-weight: 700;
  font-size: 15px;
  box-shadow: 0 2px 10px #2a8f2a18;
  transition: background 0.22s;
}
.comment-form button:hover {
  background: #1a7031;
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
  .top-info, .middle-info, .bottom-info {
    padding: 14px 4px;
    border-radius: 8px;
  }
  .sidebar {
    padding: 10px 6px;
    border-radius: 8px;
  }
}
</style>
