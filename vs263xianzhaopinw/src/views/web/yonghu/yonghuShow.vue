<template>
  <div class="product-detail-page">
    <div class="container">

      <!-- 右侧详情 -->
      <section class="detail-content">
        <!-- 上层：图 + 属性 -->
        <div class="top-info">
          <div class="image-box">
            <img :src="yonghuShow.yonghuImg" />
          </div>
          <div class="info-box">
            <h2>{{ yonghuShow.yonghuMark1 }}</h2>
            <p>{{webJcpeizhi.buzhiBieming}}：{{ yonghuShow.buzhiName }}</p>
            <p>公司：{{ yonghuShow.yonghuMark1 }}</p>
            <p>地址：{{ yonghuShow.yonghuMark2 }}</p>
            <p>联系人：{{ yonghuShow.yonghuXingming }}</p>
            <p>电话：{{ yonghuShow.yonghuPhone }}</p>

            <!-- 点赞 + 数量 + 下单 -->
            <div class="buy-area">
            <a :href="'/shujuList?yonghuId=' + yonghuShow.yonghuId">
                <button class="buy-btn">
                {{ webJcpeizhi.yonghuBieming }}{{ webJcpeizhi.shujuBieming }}
                </button>
            </a>
            </div>

          </div>
        </div>

        <!-- 中层：详情介绍 -->
        <div class="middle-info">
          <h3>详情信息</h3>
          <p><div v-html="yonghuShow.yonghuMark"></div></p>
        </div>
      </section>
      <!-- 左侧 -->
      <aside class="sidebar">
        <h3>{{ webJcpeizhi.yonghuBieming }}{{ webJcpeizhi.buzhiBieming }}</h3>
        <ul class="category-list">
          <li
            v-for="buzhi in buzhiList"
            :key="buzhi.buzhiId"
            @click="goToLeixing(buzhi.buzhiId)"
            :class="{ active: currentLeixingId == buzhi.buzhiId }"
          >
            {{ buzhi.buzhiName }}
          </li>
        </ul>
        <div class="search-box">
          <input type="text" :placeholder="webJcpeizhi.yonghuBieming + '名称'" v-model="formData.yonghuMark1" />
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
      webJcpeizhi:[], //基础配置数据
      yonghuList: "",//yonghus数据集
      yonghuShow:[],//单独展示的数据
      buzhiList: "",//buzhi数据集
      formData:{},//搜索条件
      yonghuId: "",//获取url传来的id
      num: 0,//总数量
      limit: 10,//页面显示数量
      currentPage: 1,
      currentLeixingId: '',
    };
  },
  created() {
    this.yonghuId = this.$route.query.yonghuId;
    this.currentLeixingId = this.$route.query.buzhiId || '';
    this.getJcpeizhi();
    this.getYonghuList();
    this.getBuzhiList();
  },
  methods: { 
    goToLeixing(buzhiId) {
      this.currentLeixingId = buzhiId;
      this.$router.push(`/yonghuList?buzhiId=${buzhiId}`);
    },
    //获取yonghu
    getYonghuList() {
      let para = {
        yonghuId: this.yonghuId,
      };
      let url = base + "/getYonghus";
      request.post(url, para).then((res) => {
        if (res.code == 200) {
          this.yonghuList = res.resdata;
          //console.log("yonghuList:" + this.yonghuList);
          this.yonghuShow = this.yonghuList[0];
          //console.log("yonghuShow:" + this.yonghuShow);
        }
      });
    },
    //获取buzhi
    getBuzhiList() {
      let para = {};
      
      let url = base + "/getBuzhis";
      request.post(url, para).then((res) => {
        if (res.code == 200) {
          this.buzhiList = res.resdata;
        }
      });
    },
    //获取jcpeizhi
    getJcpeizhi() {
      let para = {};
      
      let url = base + "/jcpeizhiComboList";
      request.post(url, para).then((res) => {
        this.webJcpeizhi = res.resdata;
        //console.log(this.webJcpeizhi);
      });
    },
    //显示日期
    formatDate(itmeDate) {
      // 获取单元格数据
      let data = itmeDate
      if(data == null) {
          return null
      }
      let dt = new Date(data)
      return dt.getFullYear() + '-' + (dt.getMonth() + 1) + '-' + dt.getDate() + ' ' + dt.getHours() + ':' + dt.getMinutes() + ':' + dt.getSeconds()
    },
    //搜索
    query(){
      let yonghuMark1 = this.formData.yonghuMark1;
      if(yonghuMark1!=null && yonghuMark1!=''){
        this.$router.push("/yonghuList?yonghuMark1=" + yonghuMark1);
      }else{
        this.$router.push("/yonghuList");
      }
    },
  },
};
</script>

<style scoped>
.product-detail-page {
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
.category-list li.active,
.category-list li:hover {
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

/* 右侧详情区 */
.detail-content {
  flex: 1;
  min-width: 0;
  display: flex;
  flex-direction: column;
  gap: 28px;
}

/* 上层：图片+参数 */
.top-info {
  display: flex;
  flex-wrap: wrap;
  gap: 44px;
  background: #fff;
  padding: 44px 40px 34px 40px;
  border-radius: 18px;
  border: 1.5px solid #d4efde;
  box-shadow: 0 8px 40px 0 #2a8f2a0e;
  align-items: flex-start;
}
.image-box img {
  width: 320px;
  height: 320px;
  object-fit: cover;
  border-radius: 14px;
  border: 2px solid #e6fbe8;
  box-shadow: 0 8px 32px #2a8f2a13;
  background: #fff;
}
.info-box {
  flex: 1 1 320px;
  min-width: 200px;
  display: flex;
  flex-direction: column;
  justify-content: flex-start;
}
.info-box h2 {
  font-size: 28px;
  color: #2a8f2a;
  font-weight: 700;
  margin-bottom: 14px;
}
.info-box p {
  margin: 9px 0;
  font-size: 17px;
  color: #237a30;
  line-height: 1.9;
}
.buy-area {
  margin-top: 26px;
  display: flex;
  align-items: center;
  gap: 22px;
  flex-wrap: wrap;
}
.like-btn {
  background: #ffcb05;
  border: none;
  padding: 9px 24px;
  border-radius: 9px;
  cursor: pointer;
  font-weight: bold;
  color: #333;
  font-size: 15.5px;
  transition: background 0.22s;
  box-shadow: 0 2px 10px #ffe16e38;
}
.like-btn:hover {
  background: #ffe16e;
}
.buy-btn {
  background: #2a8f2a;
  color: white;
  padding: 9px 24px;
  border: none;
  border-radius: 9px;
  cursor: pointer;
  font-weight: 600;
  font-size: 15.5px;
  margin-left: 6px;
  transition: background 0.18s;
  box-shadow: 0 2px 10px #b9efb688;
}
.buy-btn:hover {
  background: #158c45;
}

/* 中层：详情介绍 */
.middle-info {
  background: #fff;
  padding: 32px 34px 30px 34px;
  border-radius: 18px;
  border: 1.5px solid #d4efde;
  box-shadow: 0 8px 40px 0 #2a8f2a0e;
  margin-top: 10px;
}
.middle-info h3 {
  margin-bottom: 14px;
  color: #2a8f2a;
  font-weight: 700;
  font-size: 21px;
}
.middle-info p,
.middle-info .content {
  color: #237a30;
  font-size: 17px;
  line-height: 2;
  margin: 0;
}

/* 下层：评论区 */
.bottom-info {
  background: #fff;
  padding: 28px 34px;
  border-radius: 18px;
  border: 1.5px solid #d4efde;
  box-shadow: 0 8px 40px 0 #2a8f2a0e;
  margin-top: 10px;
}
.bottom-info h3 {
  margin-bottom: 13px;
  color: #2a8f2a;
  font-weight: 700;
}
.comment-list {
  list-style: none;
  padding: 0;
  margin-bottom: 24px;
}
.comment-list li {
  padding: 10px 0;
  border-bottom: 1px dashed #bfe6cc;
  font-size: 15.5px;
  color: #237a30;
}
.comment-list .user {
  font-weight: bold;
  margin-right: 8px;
  color: #228c26;
}
.pagebox {
  margin-bottom: 18px;
}
.comment-form textarea {
  width: 100%;
  height: 80px;
  padding: 9px;
  font-size: 15.5px;
  resize: vertical;
  border: 1.5px solid #d4efde;
  border-radius: 7px;
  margin-bottom: 10px;
  background: #e6fbe8;
}
.comment-form button {
  background: #2a8f2a;
  color: white;
  padding: 9px 24px;
  border: none;
  border-radius: 8px;
  cursor: pointer;
  font-weight: 600;
  font-size: 15.5px;
  transition: background 0.18s;
  box-shadow: 0 2px 10px #b9efb688;
}
.comment-form button:hover {
  background: #158c45;
}

/* 链接颜色统一为绿色 */
a {
  color: #24913e;
  text-decoration: none;
  transition: color 0.2s;
}
a:hover {
  color: #127427;
}

/* 响应式适配 */
@media (max-width: 1400px) {
  .container {
    max-width: 98vw;
    gap: 18px;
  }
}
@media (max-width: 1100px) {
  .container {
    flex-direction: column;
    gap: 18px;
  }
  .sidebar {
    margin-bottom: 18px;
    width: 100%;
    min-width: 0;
    max-width: 100vw;
  }
  .detail-content {
    width: 100%;
    min-width: 0;
    max-width: 100vw;
  }
  .top-info {
    padding: 28px 12px 18px 12px;
    gap: 18px;
    border-radius: 14px;
  }
  .middle-info, .bottom-info {
    padding: 18px 8px;
    border-radius: 14px;
  }
  .image-box img {
    width: 180px;
    height: 180px;
  }
}
@media (max-width: 700px) {
  .top-info {
    flex-direction: column;
    align-items: flex-start;
    gap: 12px;
    padding: 16px 6px 10px 6px;
    border-radius: 8px;
  }
  .sidebar {
    padding: 10px 6px;
    border-radius: 8px;
  }
  .image-box img {
    width: 98vw;
    max-width: 100%;
    height: auto;
  }
  .middle-info, .bottom-info {
    padding: 12px 6px;
    border-radius: 8px;
  }
}
</style>
