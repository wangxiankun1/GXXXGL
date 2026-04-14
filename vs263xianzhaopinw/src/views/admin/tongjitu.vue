<template>
  <section class="dashboard-page">
    <div class="dashboard-header">
      <div class="header-left">
        <div class="dashboard-title">数据看板</div>
        <div class="dashboard-desc">用户、职位、热度与交流情况综合统计</div>
      </div>
      <div class="header-right">
        <el-button type="primary" @click="loadAllCharts">刷新数据</el-button>
      </div>
    </div>

    <el-row :gutter="20" class="dashboard-row">
      <el-col :xs="24" :sm="24" :md="12" :lg="12" :xl="12">
        <el-card shadow="hover" class="chart-card">
          <template #header>
            <div class="card-header">
              <span>用户比例统计</span>
            </div>
          </template>
          <div id="userChart" class="chart-box"></div>
        </el-card>
      </el-col>

      <el-col :xs="24" :sm="24" :md="12" :lg="12" :xl="12">
        <el-card shadow="hover" class="chart-card">
          <template #header>
            <div class="card-header">
              <span>职位数量统计</span>
            </div>
          </template>
          <div id="shujuChart" class="chart-box"></div>
        </el-card>
      </el-col>
    </el-row>

    <el-row :gutter="20" class="dashboard-row">
      <el-col :xs="24" :sm="24" :md="12" :lg="12" :xl="12">
        <el-card shadow="hover" class="chart-card">
          <template #header>
            <div class="card-header">
              <span>职位热度统计</span>
            </div>
          </template>
          <div id="dianzanChart" class="chart-box"></div>
        </el-card>
      </el-col>

      <el-col :xs="24" :sm="24" :md="12" :lg="12" :xl="12">
        <el-card shadow="hover" class="chart-card">
          <template #header>
            <div class="card-header">
              <span>就业信息统计</span>
            </div>
          </template>
          <div id="uxinxiChart" class="chart-box"></div>
        </el-card>
      </el-col>
    </el-row>

    <el-row :gutter="20" class="dashboard-row">
      <el-col :span="24">
        <el-card shadow="hover" class="chart-card">
          <template #header>
            <div class="card-header">
              <span>讨论交流统计</span>
            </div>
          </template>
          <div id="sjduochuChart" class="chart-box chart-box-large"></div>
        </el-card>
      </el-col>
    </el-row>
  </section>
</template>

<script>
import request, { base } from "../../../utils/http";
import * as echarts from "echarts";

export default {
  data() {
    return {
      jcpeizhi: {},
      chartMap: {},

      userTongjiData: { name: [], value: [] },
      shujuTongjiData: { name: [], value: [] },
      dianzanTongjiData: { name: [], value: [] },
      uxinxiTongjiData: { name: [], value: [] },
      sjduochuTongjiData: { name: [], value: [] },
    };
  },
  created() {
    let jcpeizhi = sessionStorage.getItem("jcpeizhi");
    this.jcpeizhi = jcpeizhi ? JSON.parse(jcpeizhi) : {};
  },
  mounted() {
    this.$nextTick(() => {
      this.initAllCharts();
      this.loadAllCharts();
      window.addEventListener("resize", this.handleResize);
    });
  },
  beforeUnmount() {
    window.removeEventListener("resize", this.handleResize);
    this.disposeAllCharts();
  },
  beforeDestroy() {
    window.removeEventListener("resize", this.handleResize);
    this.disposeAllCharts();
  },
  methods: {
    loadAllCharts() {
      this.getUserTongji();
      this.getShujuTongji();
      this.getDianzanTongji();
      this.getUxinxiTongji();
      this.getSjduochuTongji();
    },

    initAllCharts() {
      this.initChart("userChart");
      this.initChart("shujuChart");
      this.initChart("dianzanChart");
      this.initChart("uxinxiChart");
      this.initChart("sjduochuChart");
    },

    initChart(id) {
      const dom = document.getElementById(id);
      if (!dom) return;
      if (this.chartMap[id]) {
        this.chartMap[id].dispose();
      }
      this.chartMap[id] = echarts.init(dom);
    },

    disposeAllCharts() {
      for (let key in this.chartMap) {
        if (this.chartMap[key]) {
          this.chartMap[key].dispose();
          this.chartMap[key] = null;
        }
      }
    },

    handleResize() {
      for (let key in this.chartMap) {
        if (this.chartMap[key]) {
          this.chartMap[key].resize();
        }
      }
    },

    normalizeTongjiData(res) {
      let source = {};

      if (res && res.tongjiMap) {
        source = res.tongjiMap;
      } else if (res && res.resdata) {
        source = res.resdata;
      } else if (res && res.data) {
        source = res.data;
      } else if (res) {
        source = res;
      }

      let names = [];
      let values = [];

      if (source && Array.isArray(source.name) && Array.isArray(source.value)) {
        names = source.name;
        values = source.value;
      } else if (Array.isArray(source)) {
        source.forEach((item) => {
          names.push(item.name || item.label || item.title || "");
          values.push(Number(item.value || item.count || item.num || 0));
        });
      } else if (typeof source === "object") {
        for (let key in source) {
          if (Array.isArray(source[key])) continue;
          names.push(key);
          values.push(Number(source[key] || 0));
        }
      }

      return {
        name: names,
        value: values,
      };
    },

    toPieData(dataObj) {
      let arr = [];
      if (!dataObj || !dataObj.name || !dataObj.value) return arr;
      for (let i = 0; i < dataObj.name.length; i++) {
        arr.push({
          name: dataObj.name[i],
          value: Number(dataObj.value[i] || 0),
        });
      }
      return arr;
    },

    getUserTongji() {
      let url = base + "/userTongji";
      request.post(url, {}).then((res) => {
        this.userTongjiData = this.normalizeTongjiData(res);
        this.renderUserChart();
      });
    },

    getShujuTongji() {
      let url = base + "/shujuTongji";
      request.post(url, {}).then((res) => {
        this.shujuTongjiData = this.normalizeTongjiData(res);
        this.renderShujuChart();
      });
    },

    getDianzanTongji() {
      let url = base + "/dianzanTongji";
      request.post(url, {}).then((res) => {
        this.dianzanTongjiData = this.normalizeTongjiData(res);
        this.renderDianzanChart();
      });
    },

    getUxinxiTongji() {
      let url = base + "/uxinxiTongji";
      request.post(url, {}).then((res) => {
        this.uxinxiTongjiData = this.normalizeTongjiData(res);
        this.renderUxinxiChart();
      });
    },

    getSjduochuTongji() {
      let url = base + "/sjduochuTongji";
      request.post(url, {}).then((res) => {
        this.sjduochuTongjiData = this.normalizeTongjiData(res);
        this.renderSjduochuChart();
      });
    },

    renderUserChart() {
      let chart = this.chartMap["userChart"];
      if (!chart) return;

      const option = {
        title: {
          text: "用户比例",
          left: "center",
          top: 10,
          textStyle: {
            fontSize: 16,
            fontWeight: "bold",
          },
        },
        tooltip: {
          trigger: "item",
        },
        legend: {
          bottom: 10,
          left: "center",
        },
        series: [
          {
            name: "用户比例",
            type: "pie",
            radius: ["45%", "70%"],
            center: ["50%", "52%"],
            avoidLabelOverlap: false,
            itemStyle: {
              borderRadius: 8,
              borderColor: "#fff",
              borderWidth: 2,
            },
            label: {
              show: true,
              formatter: "{b}\n{d}%",
            },
            emphasis: {
              label: {
                show: true,
                fontSize: 16,
                fontWeight: "bold",
              },
            },
            labelLine: {
              show: true,
            },
            data: this.toPieData(this.userTongjiData),
          },
        ],
      };

      chart.setOption(option, true);
    },

    renderShujuChart() {
      let chart = this.chartMap["shujuChart"];
      if (!chart) return;

      const option = {
        title: {
          text: "职位数量",
          left: "center",
          top: 10,
          textStyle: {
            fontSize: 16,
            fontWeight: "bold",
          },
        },
        tooltip: {
          trigger: "axis",
        },
        grid: {
          left: "6%",
          right: "4%",
          bottom: "8%",
          top: "18%",
          containLabel: true,
        },
        xAxis: {
          type: "category",
          data: this.shujuTongjiData.name,
          axisLabel: {
            interval: 0,
            rotate: this.shujuTongjiData.name.length > 6 ? 25 : 0,
          },
        },
        yAxis: {
          type: "value",
          name: "数量",
        },
        series: [
          {
            name: "职位数量",
            type: "bar",
            barWidth: "45%",
            data: this.shujuTongjiData.value,
            showBackground: true,
            backgroundStyle: {
              color: "rgba(180, 180, 180, 0.15)",
            },
            itemStyle: {
              borderRadius: [8, 8, 0, 0],
            },
            label: {
              show: true,
              position: "top",
            },
          },
        ],
      };

      chart.setOption(option, true);
    },

    renderDianzanChart() {
      let chart = this.chartMap["dianzanChart"];
      if (!chart) return;

      const option = {
        title: {
          text: "职位热度",
          left: "center",
          top: 10,
          textStyle: {
            fontSize: 16,
            fontWeight: "bold",
          },
        },
        tooltip: {
          trigger: "axis",
        },
        grid: {
          left: "6%",
          right: "4%",
          bottom: "8%",
          top: "18%",
          containLabel: true,
        },
        xAxis: {
          type: "category",
          boundaryGap: false,
          data: this.dianzanTongjiData.name,
          axisLabel: {
            interval: 0,
            rotate: this.dianzanTongjiData.name.length > 6 ? 25 : 0,
          },
        },
        yAxis: {
          type: "value",
          name: "点赞数",
        },
        series: [
          {
            name: "点赞数",
            type: "line",
            smooth: true,
            areaStyle: {},
            symbolSize: 8,
            data: this.dianzanTongjiData.value,
            label: {
              show: true,
              position: "top",
            },
          },
        ],
      };

      chart.setOption(option, true);
    },

    renderUxinxiChart() {
      let chart = this.chartMap["uxinxiChart"];
      if (!chart) return;

      const option = {
        title: {
          text: "就业信息",
          left: "center",
          top: 10,
          textStyle: {
            fontSize: 16,
            fontWeight: "bold",
          },
        },
        tooltip: {
          trigger: "axis",
        },
        grid: {
          left: "10%",
          right: "6%",
          bottom: "6%",
          top: "18%",
          containLabel: true,
        },
        xAxis: {
          type: "value",
          name: "收藏数",
        },
        yAxis: {
          type: "category",
          data: this.uxinxiTongjiData.name,
        },
        series: [
          {
            name: "收藏数",
            type: "bar",
            data: this.uxinxiTongjiData.value,
            barWidth: 18,
            label: {
              show: true,
              position: "right",
            },
            itemStyle: {
              borderRadius: [0, 8, 8, 0],
            },
          },
        ],
      };

      chart.setOption(option, true);
    },

    renderSjduochuChart() {
      let chart = this.chartMap["sjduochuChart"];
      if (!chart) return;

      const option = {
        title: {
          text: "讨论交流",
          left: "center",
          top: 10,
          textStyle: {
            fontSize: 16,
            fontWeight: "bold",
          },
        },
        tooltip: {
          trigger: "item",
        },
        legend: {
          bottom: 10,
          left: "center",
        },
        series: [
          {
            name: "讨论交流",
            type: "pie",
            radius: ["20%", "72%"],
            center: ["50%", "48%"],
            roseType: "area",
            itemStyle: {
              borderRadius: 8,
            },
            label: {
              formatter: "{b}: {c}",
            },
            data: this.toPieData(this.sjduochuTongjiData),
          },
        ],
      };

      chart.setOption(option, true);
    },
  },
};
</script>

<style scoped>
.dashboard-page {
  margin: 10px;
  min-height: calc(100vh - 20px);
  background: #f5f7fa;
  border-radius: 12px;
  padding: 16px;
  box-sizing: border-box;
}

.dashboard-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 18px;
  padding: 4px 4px 12px 4px;
  border-bottom: 1px solid #ebeef5;
}

.header-left {
  display: flex;
  flex-direction: column;
}

.dashboard-title {
  font-size: 24px;
  font-weight: 700;
  color: #303133;
  line-height: 1.2;
}

.dashboard-desc {
  font-size: 13px;
  color: #909399;
  margin-top: 6px;
}

.dashboard-row {
  margin-bottom: 20px;
}

.chart-card {
  border-radius: 14px;
  overflow: hidden;
}

.card-header {
  display: flex;
  align-items: center;
  justify-content: space-between;
  font-size: 16px;
  font-weight: 600;
  color: #303133;
}

.chart-box {
  width: 100%;
  height: 360px;
}

.chart-box-large {
  height: 420px;
}

@media screen and (max-width: 768px) {
  .dashboard-page {
    padding: 10px;
  }

  .dashboard-header {
    flex-direction: column;
    align-items: flex-start;
    gap: 10px;
  }

  .dashboard-title {
    font-size: 20px;
  }

  .chart-box {
    height: 320px;
  }

  .chart-box-large {
    height: 360px;
  }
}
</style>