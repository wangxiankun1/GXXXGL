<template>
  <div class="announcement-page">
    <div class="container">

      <!-- 右侧：公告列表 -->
      <section class="announcement-list">
        <h2>{{ webJcpeizhi.gonggaoBieming }}信息</h2>
        <div class="announcement-cards">
          <div 
            v-for="gonggao in gonggaoList" 
            :key="gonggao.gonggaoId" 
            class="announcement-card">
            <img :src="gonggao.gonggaoImg" :alt="gonggao.gonggaoName" class="card-image" />
            <div class="card-content">
              <h3>
                <a :href="'/gonggaoShow?gonggaoId=' + gonggao.gonggaoId" class="gonggao-title-link">
                  {{ gonggao.gonggaoName }}
                </a>
              </h3>
              <p class="content">
                <div v-html="jiequString(gonggao.gonggaoMark, 90)"></div>
              </p>
              <p class="date">{{ gonggao.ggtypeName }}</p>
            </div>
          </div>
        </div>

        <!-- 分页组件 -->
        <pagination :num="num" :limit="limit" @getList="getGonggaoList"></pagination>
      </section>
      <!-- 左侧：公告分类 + 搜索 -->
      <aside class="sidebar">
        <h3>{{ webJcpeizhi.gonggaoBieming }}{{ webJcpeizhi.ggtypeBieming }}</h3>
        <ul class="category-list">
          <li 
            v-for="(cat, index) in ggtypeList" 
            :key="index" 
            @click="ggtypeId = cat.ggtypeId; getGonggaoList(0)">
            {{ cat.ggtypeName }}
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
      webJcpeizhi: {},
      gonggaoList: [],
      ggtypeList: [],
      formData: {
        gonggaoName: ''
      },
      gonggaoName: "",
      ggtypeId: "",
      num: 0,
      limit: 6,
      currentPage: 1,
    };
  },
  created() {
    this.gonggaoName = this.$route.query.gonggaoName;
    this.ggtypeId = this.$route.query.ggtypeId;
    this.getJcpeizhi();
    this.getGonggaoList();
    this.getGgtypeList();
  },
  methods: {
    getGonggaoList(value) {
      this.currentPage = value ? (value + this.limit) / this.limit : 1;
      const para = {
        gonggaoName: this.gonggaoName || this.formData.gonggaoName,
        ggtypeId: this.ggtypeId,
      };
      const url = `${base}/getGonggaos?page=${this.currentPage}&rows=${this.limit}`;
      request.post(url, para).then((res) => {
        if (res.code === 200) {
          this.gonggaoList = res.resdata;
          this.num = res.count;
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
        if (res.code === 200) {
          this.webJcpeizhi = res.resdata;
        }
      });
    },
    formatDate(dateStr) {
      if (!dateStr) return '';
      const dt = new Date(dateStr);
      return `${dt.getFullYear()}-${dt.getMonth() + 1}-${dt.getDate()}`;
    },
    jiequString(str, n) {
      return str ? str.substring(0, n) : '';
    },
    query() {
      this.gonggaoName = this.formData.gonggaoName;
      this.getGonggaoList(0);
    }
  }
};
</script>

<style scoped>
.announcement-page {
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

/* 左侧分类 + 搜索 */
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
.search-box input,
.el-input__inner {
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

/* 右侧列表 */
.announcement-list {
  flex: 1;
}
.announcement-list h2 {
  font-size: 25px;
  color: #2a8f2a;
  margin-bottom: 28px;
  font-weight: 700;
  letter-spacing: 2px;
  text-align: center;
}
.announcement-cards {
  display: flex;
  flex-direction: column;
  gap: 28px;
}
.announcement-card {
  display: flex;
  background: #fff;
  border: 1.8px solid #d4efde;
  border-radius: 16px;
  overflow: hidden;
  padding: 22px 22px;
  gap: 26px;
  box-shadow: 0 8px 34px #38c3671e;
  transition: box-shadow 0.24s, transform 0.22s;
  align-items: flex-start;
}
.announcement-card:hover {
  box-shadow: 0 18px 44px #2a8f2a21;
  transform: translateY(-2px) scale(1.015);
}
.card-image {
  width: 120px;
  height: 88px;
  object-fit: cover;
  border-radius: 11px;
  background: #f7fbff;
  border: 1.2px solid #eaf9ec;
  box-shadow: 0 2px 10px #38c36712;
}
.card-content {
  flex: 1;
  min-width: 0;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
}
.card-content h3 {
  font-size: 18px;
  margin: 0 0 7px 0;
  font-weight: 700;
  color: #228c26;
}
.gonggao-title-link {
  color: #2a8f2a;
  text-decoration: none;
  transition: color 0.18s;
}
.gonggao-title-link:hover {
  color: #158c45;
  text-decoration: underline;
}
.card-content .content {
  font-size: 15px;
  color: #21792c;
  margin-bottom: 7px;
  word-break: break-all;
}
.card-content .date {
  font-size: 14px;
  color: #4bc46d;
  margin-top: 6px;
  letter-spacing: 1px;
  font-weight: 600;
}

/* 分页按钮按你的实际分页组件风格即可 */

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
  .announcement-list {
    width: 100%;
    min-width: 0;
    max-width: 100vw;
  }
}
@media (max-width: 700px) {
  .announcement-card {
    flex-direction: column;
    align-items: stretch;
    padding: 12px 8px;
    gap: 12px;
  }
  .card-image {
    width: 100%;
    max-width: 340px;
    height: 150px;
    margin: 0 auto 8px;
    display: block;
  }
}
</style>
