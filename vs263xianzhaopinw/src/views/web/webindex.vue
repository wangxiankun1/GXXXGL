<template>
  <main class="home-main">
    <!-- 热门商品模块 -->
    <div class="bg-product">
      <section class="section-inner product-section">
        <h2>{{ webJcpeizhi.shujuBieming }}信息</h2>
        <div class="product-grid">
          <div v-for="shuju in shujuList" :key="shuju.shujuId" class="product-card">
            <a :href="'/shujuShow?shujuId=' + shuju.shujuId" :title="shuju.shujuName">
              <img :src="shuju.shujuImg" :alt="shuju.shujuName" />
              <h3>{{ shuju.shujuName }}</h3>
            </a>
          </div>
        </div>
      </section>
    </div>

    <!-- 关于我们模块 -->
    <div class="bg-intro">
      <section class="section-inner intro-section">
        <div class="intro-row">
          <!-- 左侧：标题+文字 -->
          <div class="intro-text-block">
            <h2>{{ sjxiaoxiShow.sjxiaoxiName }}</h2>
            <div class="intro-text" v-html="jiequString(sjxiaoxiShow.sjxiaoxiMark, 240)"></div>
          </div>
          <!-- 右侧：图片 -->
          <div class="intro-image-block">
            <img :src="aboutImg" :alt="sjxiaoxiShow.sjxiaoxiName" />
          </div>
        </div>
      </section>
    </div>

    <!-- 公告 + 联系方式模块 -->
    <div class="bg-bottom">
      <section class="section-inner bottom-section">
        <!-- 最新公告 -->
        <div class="announcement-section">
          <h2>{{ webJcpeizhi.gonggaoBieming }}信息</h2>
          <ul class="info-list announcement">
            <li v-for="gonggao in gonggaoList" :key="gonggao.gonggaoId" class="gonggao-item">
              <a :href="'/gonggaoShow?gonggaoId=' + gonggao.gonggaoId">
                <img v-if="gonggao.gonggaoImg" :src="gonggao.gonggaoImg" :alt="gonggao.gonggaoName" />
                <span class="title">{{ gonggao.gonggaoName }}</span>
                <span class="type">{{ gonggao.ggtypeName }}</span>
              </a>
            </li>
          </ul>
        </div>

        <!-- 联系方式 -->
        <div class="contact-section">
          <div class="contact-row">
            <!-- 左侧：文字列表 -->
            <div class="contact-info">
              <h2>联系方式</h2>
              <ul class="info-list contact">
                <li>
                  <img src="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/icons/person-fill.svg" alt="联系人" />
                  <span>联系人：张三</span>
                </li>
                <li>
                  <img src="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/icons/phone-fill.svg" alt="手机" />
                  <span>手机：139-0000-0000</span>
                </li>
                <li>
                  <img src="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/icons/telephone-fill.svg" alt="电话" />
                  <span>电话：188-8888-8888</span>
                </li>
                <li>
                  <img src="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/icons/envelope-fill.svg" alt="邮箱" />
                  <span>邮箱：info@example.com</span>
                </li>
                <li>
                  <img src="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/icons/geo-alt-fill.svg" alt="地址" />
                  <span>地址：西安市碑林区某街道100号</span>
                </li>
              </ul>
            </div>
            <!-- 右侧：图片 -->
            <div class="contact-img">
              <img :src="contactImg" alt="联系我们" />
            </div>
          </div>
        </div>
      </section>
    </div>
  </main>
</template>

<script>
import request, { base } from "../../../utils/http";

export default {
  data() {
    return {
      webJcpeizhi: [],
      shujuList: [],
      sjleixingList: [],
      gonggaoList: [],
      ggtypeList: [],
      sjxiaoxiList: [],
      sjxiaoxiShow: {},
      formData: {},
      aboutImg: require('@/assets/shouye/image/shouye.jpg'),
      contactImg: require('@/assets/shouye/image/shouye1.jpg'),
    };
  },
  created() {
    this.getJcpeizhi();
    this.getShujuList();
    this.getSjleixingList();
    this.getGonggaoList();
    this.getGgtypeList();
    this.getSjxiaoxiList();
  },
  methods: {
    getShujuList() {
      const url = base + "/getShujus?rows=12&page=1";
      request.post(url, {}).then(res => {
        if (res.code === 200) this.shujuList = res.resdata;
      });
    },
    getSjleixingList() {
      const url = base + "/getSjleixings";
      request.post(url, {}).then(res => {
        if (res.code === 200) this.sjleixingList = res.resdata;
      });
    },
    getGonggaoList() {
      const url = base + "/getGonggaos?rows=3&page=1";
      request.post(url, {}).then(res => {
        if (res.code === 200) {
          this.gonggaoList = res.resdata;
        }
      });
    },
    getGgtypeList() {
      const url = base + "/getGgtypes";
      request.post(url, {}).then(res => {
        if (res.code === 200) {
          this.ggtypeList = res.resdata;
        }
      });
    },
    getSjxiaoxiList() {
      const url = base + "/getSjxiaoxis";
      request.post(url, { sjxiaoxiId: 1 }).then(res => {
        this.sjxiaoxiList = res.resdata;
        this.sjxiaoxiShow = this.sjxiaoxiList[0] || {};
      });
    },
    getJcpeizhi() {
      const url = base + "/jcpeizhiComboList";
      request.post(url, {}).then(res => {
        this.webJcpeizhi = res.resdata;
      });
    },
    jiequString(str, len) {
      return str ? str.substring(0, len) : '';
    },
    query() {
      const shujuName = this.formData.shujuName || '';
      this.$router.push("/shujuList?shujuName=" + shujuName);
    }
  }
};
</script>

<style scoped>
.home-main {
  font-family: 'Segoe UI', 'Arial', sans-serif;
  background: #f4fcf6;
}

/* ========== 主体宽度最大 1400 ========== */
.section-inner {
  max-width: 1400px;
  margin: 0 auto;
  padding: 48px 16px;
  box-sizing: border-box;
}

/* ========== 关于我们模块 ========== */
.bg-intro {
  background: linear-gradient(120deg, #e6fbe8 0%, #f8fdf6 100%);
}
.intro-section {
  padding: 0;
  margin-bottom: 0;
  display: flex;
  justify-content: center;
}
.intro-row {
  display: flex;
  align-items: center;
  justify-content: space-between;
  gap: 44px;
  min-height: 280px;
  width: 100%;
  margin: 0 auto;
  background: #fff;
  border-radius: 20px;
  box-shadow: 0 8px 40px 0 #2a8f2a0e;
  padding: 44px 36px;
}


/* 左侧：标题+文字 */
.intro-text-block {
  flex: 1.3;
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  justify-content: center;
  min-width: 0;
}
.intro-text-block h2 {
  font-size: 28px;
  color: #228c26;
  font-weight: 700;
  margin-bottom: 24px;
  text-align: left;
  letter-spacing: 1.5px;
}
.intro-text {
  font-size: 18px;
  color: #237a30;
  line-height: 2;
  text-align: left;
  margin: 0;
  padding: 0;
}

/* 右侧图片 */
.intro-image-block {
  flex: 1;
  display: flex;
  align-items: center;
  justify-content: center;
  min-width: 220px;
  max-width: 340px;
}
.intro-image-block img {
  width: 95%;
  max-width: 320px;
  min-width: 140px;
  border-radius: 18px;
  box-shadow: 0 8px 32px #2a8f2a13;
  border: 2px solid #e6fbe8;
  background: #fff;
}

/* ========== 商品卡片区 ========== */
.bg-product {
  background: linear-gradient(120deg, #e6fbe8 0%, #c2efd2 100%);
}
.product-section h2 {
  text-align: center;
  font-size: 27px;
  color: #2a8f2a;
  letter-spacing: 2px;
  margin-bottom: 30px;
  font-weight: 700;
}
.product-grid {
  display: grid;
  grid-template-columns: repeat(6, 1fr);
  gap: 28px 16px;
}
.product-card {
  background: #fff;
  border-radius: 16px;
  border: 1.8px solid #d4efde;
  box-shadow: 0 4px 18px 0 #38c36726;
  padding: 28px 14px 20px;
  text-align: center;
  transition: transform 0.22s, box-shadow 0.22s;
  position: relative;
  z-index: 1;
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

/* ========== 公告+联系方式底栏 ========== */
.bg-bottom {
  background: linear-gradient(120deg, #f4fcf6 60%, #e6fbe8 100%);
}
.bottom-section {
  display: flex;
  flex-wrap: wrap;
  gap: 48px;
  justify-content: space-between;
}
.announcement-section,
.contact-section {
  flex: 1 1 400px;
  background: #fff;
  border-radius: 16px;
  box-shadow: 0 4px 18px #2a8f2a1a;
  padding: 36px 32px 22px;
  min-width: 300px;
  border: 1.2px solid #e1efe4;
}

/* 公告列表样式 */
.announcement-section h2 {
  font-size: 22px;
  margin-bottom: 18px;
  color: #2a8f2a;
  letter-spacing: 1.2px;
  font-weight: 700;
}
.info-list.announcement li {
  display: flex;
  align-items: center;
  gap: 18px;
  padding: 10px 0;
  border-bottom: 1px dashed #bfe6cc;
  font-size: 16px;
  line-height: 1.7;
  transition: background 0.2s;
}
.info-list.announcement li:last-child {
  border-bottom: none;
}
.gonggao-item a {
  display: flex;
  align-items: center;
  gap: 18px;
  width: 100%;
  color: #225e2a;
  font-weight: 500;
  transition: color .2s;
}
.gonggao-item img {
  width: 54px !important;
  height: 54px !important;
  object-fit: cover;
  border-radius: 9px;
  background: #e6fbe8;
  box-shadow: 0 2px 10px #2a8f2a0c;
  border: 1.5px solid #d6fbe3;
}
.gonggao-item .title {
  flex: 1;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
  color: #257f39;
  font-size: 16px;
  font-weight: 600;
  transition: color .2s;
}
.gonggao-item .type {
  color: #249a42;
  font-size: 15px;
  font-weight: 500;
  background: linear-gradient(90deg,#d4fbe3 10%, #b9f0cd 100%);
  padding: 2px 11px;
  border-radius: 8px;
  border: 1px solid #e6fbe8;
}
.gonggao-item a:hover .title {
  color: #14802b;
}

/* ========== 联系方式左右结构 ========== */
.contact-section h2 {
  font-size: 22px;
  margin-bottom: 18px;
  color: #2a8f2a;
  letter-spacing: 1.2px;
  font-weight: 700;
}
.contact-row {
  display: flex;
  align-items: center;
  gap: 44px;
}
.contact-info {
  flex: 1.2;
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  justify-content: center;
  min-width: 0;
}
.info-list.contact {
  width: 100%;
}
.info-list.contact li {
  display: flex;
  align-items: center;
  gap: 15px;
  padding: 8px 0;
  font-size: 15.5px;
  color: #21792c;
}
.info-list.contact li img {
  width: 26px;
  height: 26px;
  object-fit: contain;
  filter: grayscale(0%) brightness(1) sepia(90%) hue-rotate(80deg) saturate(1500%);
  opacity: 0.83;
}
.contact-img {
  flex: 1.2;
  display: flex;
  align-items: center;
  justify-content: center;
  min-width: 220px;
  max-width: 100%;
}
.contact-img img {
  width: 100%;
  max-width: 380px;
  min-width: 120px;
  border-radius: 18px;
  box-shadow: 0 8px 32px #2a8f2a13;
  border: 2px solid #e6fbe8;
  background: #fff;
  object-fit: cover;
}

/* 通用a链接样式 */
a {
  color: #278b2a;
  text-decoration: none;
  transition: color 0.2s;
}
a:hover {
  color: #1d7f36;
}

/* ========== 响应式 ========== */
@media (max-width: 1400px) {
  .section-inner {
    max-width: 98vw;
  }
}
@media (max-width: 1400px) {
  .product-grid {
    grid-template-columns: repeat(4, 1fr);
  }
  .intro-row {
    padding: 28px 6px;
    max-width: 98vw;
  }
}
@media (max-width: 1100px) {
  .intro-row,
  .contact-row {
    flex-direction: column;
    gap: 20px;
    min-height: 0;
    padding: 22px 4px;
    max-width: 100vw;
  }
  .intro-text-block, .intro-image-block,
  .contact-info, .contact-img {
    align-items: center;
    justify-content: center;
    text-align: center;
    width: 100%;
    min-width: 0;
    max-width: 100%;
  }
  .intro-text-block h2,
  .contact-info h2 {
    text-align: center;
  }
  .product-grid {
    grid-template-columns: repeat(2, 1fr);
  }
}
@media (max-width: 900px) {
  .section-inner {
    padding: 18px 2px;
  }
}
@media (max-width: 700px) {
  .product-grid {
    grid-template-columns: 1fr;
  }
  .intro-row {
    padding: 10px 2px;
    gap: 14px;
    min-width: 0;
    max-width: 100vw;
  }
  .intro-image-block img,
  .contact-img img {
    max-width: 100%;
    min-width: 60px;
  }
  .announcement-section,
  .contact-section {
    padding: 10px 2px 5px;
    border-radius: 8px;
  }
}
</style>
