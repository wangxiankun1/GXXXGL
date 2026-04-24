<template>
  <div class="product-page">
    <div class="container">

      <!-- 右侧：商品列表 -->
      <section class="product-list">
        <h2>{{ webJcpeizhi.shujuBieming }}信息</h2>
        <div class="product-grid">
          <div
            class="product-card"
            v-for="shuju in shujuList" :key="shuju.shujuId"
          >
            <a :href="'/shujuShow?shujuId=' + shuju.shujuId">
              <img :src="shuju.shujuImg" />
              <h3>{{ shuju.shujuName }}</h3>
            </a>
          </div>
        </div>
        <div class="pagebox">
          <pagination :num="num" :limit="limit" @getList="getShujuList" />
        </div>
      </section>
      <!-- 左侧：分类和搜索 -->
      <aside class="sidebar">
        <h3>{{ webJcpeizhi.shujuBieming }}{{ webJcpeizhi.sjleixingBieming }}</h3>
        <ul class="category-list">
          <li 
            v-for="sjleixing in sjleixingList" 
            :key="sjleixing.sjleixingId"
            @click="goToLeixing(sjleixing.sjleixingId)">
            {{ sjleixing.sjleixingName }}
          </li>
        </ul>

        <div class="search-box">
          <input 
            type="text" 
            :placeholder="webJcpeizhi.shujuBieming + '名称'" 
            v-model="formData.shujuName" />
          <button @click="query">
            <img
              src="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/icons/search.svg"
              alt="搜索"
            />
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
      shujuList: [],
      sjleixingList: [],
      formData: { shujuName: "" },
      shujuName: "",
      sjleixingId: "",
      yonghuId: "",
      num: 0,
      limit: 12,
      currentPage: 1,
    };
  },
  created() {
    this.getJcpeizhi();
    this.getSjleixingList();
  },
  watch: {
    '$route.query': {
      immediate: true,
      handler(newQuery) {
        this.shujuName = newQuery.shujuName || '';
        this.formData.shujuName = this.shujuName;
        this.sjleixingId = newQuery.sjleixingId || '';
        this.yonghuId = newQuery.yonghuId || '';
        this.getShujuList(0);
      }
    }
  },
  methods: {
    getShujuList(value) {
      this.currentPage = value ? (value + this.limit) / this.limit : 1;
      const para = {
        shujuName: this.shujuName,
        sjleixingId: this.sjleixingId,
        yonghuId: this.yonghuId,
      };
      const url = `${base}/getShujus?page=${this.currentPage}&rows=${this.limit}`;
      request.post(url, para).then((res) => {
        if (res.code === 200) {
          this.shujuList = res.resdata;
          this.num = res.count;
        }
      });
    },
    getSjleixingList() {
      request.post(`${base}/getSjleixings`, {}).then((res) => {
        if (res.code === 200) {
          this.sjleixingList = res.resdata;
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
    query() {
      const name = this.formData.shujuName;
      if (name) {
        this.$router.push({ path: "/shujuList", query: { shujuName: name } });
      } else {
        this.$router.push("/shujuList");
      }
    },
    goToLeixing(sjleixingId) {
      this.$router.push({ path: "/shujuList", query: { sjleixingId } });
    },
  },
};
</script>

<style scoped>
.product-page {
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

/* 左侧栏 */
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
  margin: 0 0 28px 0;
}
.category-list li {
  background: linear-gradient(90deg,#f7fbff 80%, #eaf9ec 100%);
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
  border-radius: 7px;
  background: #f8fbff;
  margin-bottom: 6px;
  box-sizing: border-box;
}
.search-box input {
  width: 0;
  flex: 1;
  min-width: 0;
  padding: 8px 10px;
  border: none;
  font-size: 15px;
  background: transparent;
  outline: none;
  color: #237a30;
}
.search-box button {
  width: 38px;
  background: #2a8f2a;
  display: flex;
  align-items: center;
  justify-content: center;
  border: none;
  cursor: pointer;
  border-radius: 0 7px 7px 0;
}
.search-box button img {
  width: 18px;
  height: 18px;
  filter: brightness(0) invert(0.9) sepia(1) saturate(3) hue-rotate(80deg);
}

/* 右侧商品区 */
.product-list {
  flex: 1;
}
.product-list h2 {
  font-size: 24px;
  margin-bottom: 24px;
  color: #2a8f2a;
  font-weight: 700;
  letter-spacing: 2px;
}
.product-grid {
  display: grid;
  grid-template-columns: repeat(4, 1fr);
  gap: 28px 18px;
}
.product-card {
  background: #fff;
  border: 1.5px solid #d4efde;
  border-radius: 16px;
  box-shadow: 0 4px 18px #38c36716;
  padding: 28px 12px 20px 12px;
  text-align: center;
  transition: transform 0.22s, box-shadow 0.22s;
  position: relative;
  z-index: 1;
  display: flex;
  flex-direction: column;
  align-items: center;
}
.product-card:hover {
  transform: translateY(-5px) scale(1.04);
  box-shadow: 0 10px 34px 0 #2a8f2a32;
  border-color: #2a8f2a50;
}
.product-card img {
  width: 98%;
  max-width: 170px;
  height: 110px;
  border-radius: 11px;
  object-fit: cover;
  margin-bottom: 16px;
  box-shadow: 0 2px 13px #2a8f2a13;
  border: 2px solid #eaf9ec;
  background: #e6fbe8;
}
.product-card h3 {
  margin-top: 8px;
  font-size: 16px;
  color: #228c26;
  font-weight: 600;
  letter-spacing: 1.1px;
}
.product-card a {
  color: #24913e;
  text-decoration: none;
  transition: color 0.2s;
  display: block;
  width: 100%;
}
.product-card a:visited {
  color: #24913e;
}
.product-card a:hover h3 {
  color: #127427;
}
/* 分页区 */
.pagebox {
  margin-top: 22px;
}

/* 响应式适配 */
@media (max-width: 1400px) {
  .container {
    max-width: 98vw;
    gap: 16px;
  }
  .product-grid {
    grid-template-columns: repeat(4, 1fr);
    gap: 18px;
  }
}
@media (max-width: 1100px) {
  .container {
    flex-direction: column;
    gap: 18px;
  }
  .sidebar {
    margin-bottom: 16px;
    width: 100%;
    min-width: 0;
    max-width: 100vw;
  }
  .product-list {
    width: 100%;
    min-width: 0;
    max-width: 100vw;
  }
  .product-grid {
    grid-template-columns: repeat(2, 1fr);
    gap: 14px;
  }
}
@media (max-width: 800px) {
  .product-list h2 {
    font-size: 19px;
  }
  .sidebar {
    padding: 10px 6px;
    border-radius: 8px;
  }
  .product-card {
    border-radius: 8px;
    padding: 12px 6px;
  }
  .product-card img {
    max-width: 90px;
    height: 60px;
  }
}
@media (max-width: 600px) {
  .container {
    gap: 8px;
    padding: 0 2px;
  }
  .product-grid {
    grid-template-columns: 1fr;
    gap: 8px;
  }
  .sidebar {
    padding: 6px 2px;
    border-radius: 6px;
  }
}
</style>
