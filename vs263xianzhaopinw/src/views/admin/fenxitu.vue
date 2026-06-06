<template>
  <section class="analysis-page">
    <!-- 顶部筛选栏 -->
    <div class="filter-bar">
      <div class="filter-left">
        <el-form :inline="true" :model="filterForm" class="filter-form">
          <el-form-item label="年级">
            <el-select v-model="filterForm.grade" placeholder="请选择年级">
              <el-option label="全部" value=""></el-option>
              <el-option label="2021级" value="2021"></el-option>
              <el-option label="2022级" value="2022"></el-option>
              <el-option label="2023级" value="2023"></el-option>
              <el-option label="2024级" value="2024"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="学院">
            <el-select v-model="filterForm.college" placeholder="请选择学院">
              <el-option label="全部" value=""></el-option>
              <el-option label="计算机学院" value="计算机学院"></el-option>
              <el-option label="商学院" value="商学院"></el-option>
              <el-option label="文学院" value="文学院"></el-option>
              <el-option label="理学院" value="理学院"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="专业">
            <el-select v-model="filterForm.major" placeholder="请选择专业">
              <el-option label="全部" value=""></el-option>
              <el-option label="计算机科学与技术" value="计算机科学与技术"></el-option>
              <el-option label="软件工程" value="软件工程"></el-option>
              <el-option label="市场营销" value="市场营销"></el-option>
              <el-option label="会计学" value="会计学"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="性别">
            <el-select v-model="filterForm.gender" placeholder="请选择性别">
              <el-option label="全部" value=""></el-option>
              <el-option label="男" value="1"></el-option>
              <el-option label="女" value="2"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="loadAllCharts">查询</el-button>
            <el-button @click="resetFilter">重置</el-button>
          </el-form-item>
        </el-form>
      </div>
      <div class="filter-right">
        <el-dropdown trigger="click">
          <el-button type="success">
            导出报表 <i class="el-icon-arrow-down"></i>
          </el-button>
          <template #dropdown>
            <el-dropdown-menu>
              <el-dropdown-item @click.native="exportExcel">Excel格式</el-dropdown-item>
              <el-dropdown-item @click.native="exportCSV">CSV格式</el-dropdown-item>
              <el-dropdown-item @click.native="exportPDF">PDF格式</el-dropdown-item>
            </el-dropdown-menu>
          </template>
        </el-dropdown>
      </div>
    </div>

    <!-- 数据概览头部 -->
    <div class="analysis-header">
      <div class="header-left">
        <div class="analysis-title">📊 就业数据分析中心</div>
        <div class="analysis-desc">深度分析学生就业数据，洞察就业趋势，助力就业决策</div>
      </div>
      <div class="header-right">
        <el-button type="primary" @click="loadAllCharts">
          <i class="el-icon-refresh"></i> 刷新数据
        </el-button>
        <el-button @click="toggleFullscreen">
          <i class="el-icon-full-screen"></i> 全屏查看
        </el-button>
      </div>
    </div>

    <!-- 关键指标卡片 -->
    <el-row :gutter="20" class="analysis-row">
      <el-col :xs="24" :sm="12" :md="6" :lg="6" :xl="6">
        <div class="stat-card">
          <div class="stat-icon employment">👥</div>
          <div class="stat-content">
            <div class="stat-label">就业总人数</div>
            <div class="stat-value">{{ summaryStats.totalEmployed }}</div>
            <div class="stat-change positive">↑ {{ summaryStats.employmentGrowth }}%</div>
          </div>
        </div>
      </el-col>
      <el-col :xs="24" :sm="12" :md="6" :lg="6" :xl="6">
        <div class="stat-card">
          <div class="stat-icon rate">📈</div>
          <div class="stat-content">
            <div class="stat-label">总体就业率</div>
            <div class="stat-value">{{ summaryStats.overallRate }}%</div>
            <div class="stat-change positive">↑ {{ summaryStats.rateGrowth }}%</div>
          </div>
        </div>
      </el-col>
      <el-col :xs="24" :sm="12" :md="6" :lg="6" :xl="6">
        <div class="stat-card">
          <div class="stat-icon salary">🤝</div>
          <div class="stat-content">
            <div class="stat-label">校企匹配度</div>
            <div class="stat-value">{{ summaryStats.supplyDemandRatio }}%</div>
            <div class="stat-change positive">↑ {{ summaryStats.supplyDemandGrowth }}%</div>
          </div>
        </div>
      </el-col>
      <el-col :xs="24" :sm="12" :md="6" :lg="6" :xl="6">
        <div class="stat-card">
          <div class="stat-icon major">🎓</div>
          <div class="stat-content">
            <div class="stat-label">专业覆盖率</div>
            <div class="stat-value">40%</div>
            <div class="stat-change positive">↑ 8.3%</div>
          </div>
        </div>
      </el-col>
    </el-row>

    <!-- 就业率统计 -->
    <el-row :gutter="20" class="analysis-row">
      <el-col :xs="24">
        <el-card shadow="hover" class="chart-card">
          <template #header>
            <div class="card-header">
              <span>就业率统计</span>
            </div>
          </template>
          <div class="rate-content">
            <div class="rate-grid">
              <div class="rate-item">
                <div class="rate-label">总体就业率</div>
                <div class="rate-value">{{ employmentRate.totalRate }}%</div>
              </div>
              <div class="rate-item male">
                <div class="rate-label">男生就业率</div>
                <div class="rate-value">{{ employmentRate.maleRate }}%</div>
              </div>
              <div class="rate-item female">
                <div class="rate-label">女生就业率</div>
                <div class="rate-value">{{ employmentRate.femaleRate }}%</div>
              </div>
            </div>
          </div>
        </el-card>
      </el-col>
    </el-row>

    <!-- 就业去向和行业占比 -->
    <el-row :gutter="20" class="analysis-row">
      <el-col :xs="24" :sm="24" :md="12" :lg="12" :xl="12">
        <el-card shadow="hover" class="chart-card">
          <template #header>
            <div class="card-header">
              <span>就业去向占比</span>
            </div>
          </template>
          <div id="directionChart" class="chart-box"></div>
        </el-card>
      </el-col>

      <el-col :xs="24" :sm="24" :md="12" :lg="12" :xl="12">
        <el-card shadow="hover" class="chart-card">
          <template #header>
            <div class="card-header">
              <span>行业占比</span>
            </div>
          </template>
          <div id="industryChart" class="chart-box"></div>
        </el-card>
      </el-col>
    </el-row>

    <!-- 分专业就业率排行 -->
    <el-row :gutter="20" class="analysis-row">
      <el-col :xs="24">
        <el-card shadow="hover" class="chart-card">
          <template #header>
            <div class="card-header">
              <span>分专业就业率排行</span>
            </div>
          </template>
          <div id="majorRateChart" class="chart-box-large"></div>
        </el-card>
      </el-col>
    </el-row>

    <!-- 历年就业率变化 -->
    <el-row :gutter="20" class="analysis-row">
      <el-col :xs="24">
        <el-card shadow="hover" class="chart-card">
          <template #header>
            <div class="card-header">
              <span>历年就业率变化</span>
            </div>
          </template>
          <div id="yearlyRateChart" class="chart-box-large"></div>
        </el-card>
      </el-col>
    </el-row>

    <!-- 就业地域分布和薪资统计 -->
    <el-row :gutter="20" class="analysis-row">
      <el-col :xs="24" :sm="24" :md="12" :lg="12" :xl="12">
        <el-card shadow="hover" class="chart-card">
          <template #header>
            <div class="card-header">
              <span>就业地域分布</span>
            </div>
          </template>
          <div id="areaChart" class="chart-box"></div>
        </el-card>
      </el-col>

      <el-col :xs="24" :sm="24" :md="12" :lg="12" :xl="12">
        <el-card shadow="hover" class="chart-card">
          <template #header>
            <div class="card-header">
              <span>薪资分布统计</span>
            </div>
          </template>
          <div id="salaryChart" class="chart-box"></div>
        </el-card>
      </el-col>
    </el-row>

    <!-- 专业-薪资分布箱线图 -->
    <el-row :gutter="20" class="analysis-row">
      <el-col :xs="24">
        <el-card shadow="hover" class="chart-card">
          <template #header>
            <div class="card-header">
              <span>专业-薪资分布</span>
            </div>
          </template>
          <div id="salaryBoxChart" class="chart-box-large"></div>
        </el-card>
      </el-col>
    </el-row>

    <!-- 就业趋势分析 -->
    <el-row :gutter="20" class="analysis-row">
      <el-col :xs="24">
        <el-card shadow="hover" class="chart-card">
          <template #header>
            <div class="card-header">
              <span>就业趋势分析</span>
            </div>
          </template>
          <div id="trendChart" class="chart-box-large"></div>
        </el-card>
      </el-col>
    </el-row>

    <!-- 毕业生七大去向 -->
    <el-row :gutter="20" class="analysis-row">
      <el-col :xs="24">
        <el-card shadow="hover" class="chart-card">
          <template #header>
            <div class="card-header">
              <span>毕业生七大去向</span>
            </div>
          </template>
          <div class="direction-container">
            <div id="graduateDirectionChart" class="chart-box-medium"></div>
            <div class="direction-cards">
              <div class="dir-card">
                <div class="dir-label">升学率</div>
                <div class="dir-value">{{ graduateDirection.entranceRate }}%</div>
              </div>
              <div class="dir-card">
                <div class="dir-label">考编率</div>
                <div class="dir-value">{{ graduateDirection.civilServantRate }}%</div>
              </div>
              <div class="dir-card">
                <div class="dir-label">未就业率</div>
                <div class="dir-value">{{ graduateDirection.unemployRate }}%</div>
              </div>
            </div>
          </div>
        </el-card>
      </el-col>
    </el-row>

    <!-- 薪资分析 -->
    <el-row :gutter="20" class="analysis-row">
      <el-col :xs="24">
        <el-card shadow="hover" class="chart-card">
          <template #header>
            <div class="card-header">
              <span>薪资分析</span>
              <span class="card-subtitle">平均薪资：¥{{ salaryAnalysis.avgSalary.toLocaleString() }}/月</span>
            </div>
          </template>
          <div class="salary-container">
            <div id="salaryPieChart" class="chart-box-medium"></div>
            <div id="majorSalaryChart" class="chart-box-medium"></div>
          </div>
        </el-card>
      </el-col>
    </el-row>

    <!-- 就业地域分析 -->
    <el-row :gutter="20" class="analysis-row">
      <el-col :xs="24" :sm="24" :md="12" :lg="12" :xl="12">
        <el-card shadow="hover" class="chart-card">
          <template #header>
            <div class="card-header">
              <span>省内省外占比</span>
            </div>
          </template>
          <div id="areaRatioChart" class="chart-box"></div>
        </el-card>
      </el-col>
      <el-col :xs="24" :sm="24" :md="12" :lg="12" :xl="12">
        <el-card shadow="hover" class="chart-card">
          <template #header>
            <div class="card-header">
              <span>城市等级分布</span>
            </div>
          </template>
          <div id="cityLevelChart" class="chart-box"></div>
        </el-card>
      </el-col>
    </el-row>

    <!-- 未就业分析 -->
    <el-row :gutter="20" class="analysis-row">
      <el-col :xs="24" :sm="24" :md="12" :lg="12" :xl="12">
        <el-card shadow="hover" class="chart-card">
          <template #header>
            <div class="card-header">
              <span>未就业原因占比</span>
            </div>
          </template>
          <div id="unemployedReasonChart" class="chart-box"></div>
        </el-card>
      </el-col>
      <el-col :xs="24" :sm="24" :md="12" :lg="12" :xl="12">
        <el-card shadow="hover" class="chart-card">
          <template #header>
            <div class="card-header">
              <span>未就业专业分布</span>
            </div>
          </template>
          <div id="unemployedMajorChart" class="chart-box"></div>
        </el-card>
      </el-col>
    </el-row>

    <!-- 校企供需对比 -->
    <el-row :gutter="20" class="analysis-row">
      <el-col :xs="24">
        <el-card shadow="hover" class="chart-card">
          <template #header>
            <div class="card-header">
              <span>校企供需对比</span>
              <span class="card-subtitle">岗位数:求职人数 = {{ supplyDemand.ratio }}:1</span>
            </div>
          </template>
          <div id="supplyDemandChart" class="chart-box-large"></div>
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
      currentDate: new Date().toLocaleDateString(),
      filterForm: {
        grade: "",
        college: "",
        major: "",
        gender: ""
      },
      chartMap: {},
      employmentRate: {
        totalRate: 0, maleRate: 0, femaleRate: 0,
        totalStudents: 0, totalEmployed: 0,
        totalMale: 0, employedMale: 0,
        totalFemale: 0, employedFemale: 0
      },
      graduateDirection: { entranceRate: 0, civilServantRate: 0, unemployRate: 0 },
      salaryAnalysis: { avgSalary: 0 },
      supplyDemand: { ratio: 0 },
      majorRateDetail: [],
      summaryStats: {
        totalEmployed: 0,
        overallRate: 0,
        supplyDemandRatio: 0,
        supplyDemandGrowth: 6.5,
        majorCount: 0,
        studentCount: 0,
        employmentGrowth: 5.2,
        rateGrowth: 2.1
      }
    };
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
  methods: {
    loadAllCharts() {
      this.getEmploymentRate();
      this.getEmploymentDirection();
      this.getIndustryDistribution();
      this.getMajorEmploymentRate();
      this.getYearlyEmploymentRate();
      this.getUxinxiArea();
      this.getUxinxiSalary();
      this.getMajorSalaryBox();
      this.getUxinxiTrend();
      this.getGraduateDirection();
      this.getSalaryAnalysis();
      this.getAreaAnalysis();
      this.getUnemployedAnalysis();
      this.getSupplyDemand();
    },
    initAllCharts() {
      let charts = [
        'directionChart', 'industryChart', 'majorRateChart', 'yearlyRateChart',
        'areaChart', 'salaryChart', 'salaryBoxChart', 'trendChart',
        'graduateDirectionChart', 'salaryPieChart', 'majorSalaryChart',
        'areaRatioChart', 'cityLevelChart',
        'unemployedReasonChart', 'unemployedMajorChart', 'supplyDemandChart'
      ];
      charts.forEach(id => this.initChart(id));
    },
    initChart(id) {
      const dom = document.getElementById(id);
      if (!dom) return;
      if (this.chartMap[id]) this.chartMap[id].dispose();
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
        if (this.chartMap[key]) this.chartMap[key].resize();
      }
    },
    resetFilter() {
      this.filterForm = { grade: "", college: "", major: "", gender: "" };
    },
    exportExcel() {
      alert("Excel报表导出功能开发中...");
    },
    exportCSV() {
      alert("CSV报表导出功能开发中...");
    },
    exportPDF() {
      alert("PDF报表导出功能开发中...");
    },
    toggleFullscreen() {
      if (!document.fullscreenElement) {
        document.documentElement.requestFullscreen();
      } else {
        document.exitFullscreen();
      }
    },
    normalizeTongjiData(res) {
      let source = {};
      if (res && res.tongjiMap) source = res.tongjiMap;
      else if (res && res.resdata) source = res.resdata;
      else if (res && res.data) source = res.data;
      else if (res) source = res;

      let names = [], values = [];
      if (source && Array.isArray(source.name) && Array.isArray(source.value)) {
        names = source.name; values = source.value;
      } else if (Array.isArray(source)) {
        source.forEach(item => {
          names.push(item.name || item.label || "");
          values.push(Number(item.value || item.count || 0));
        });
      }
      return { name: names, value: values };
    },
    toPieData(dataObj) {
      let arr = [];
      if (!dataObj || !dataObj.name || !dataObj.value) return arr;
      for (let i = 0; i < dataObj.name.length; i++) {
        arr.push({ name: dataObj.name[i], value: Number(dataObj.value[i] || 0) });
      }
      return arr;
    },
    getEmploymentRate() {
      request.post(base + "/getEmploymentRate", {}).then(res => {
        if (res && res.tongjiMap) {
          const data = res.tongjiMap;
          this.employmentRate = {
            totalRate: (data.value[0] || 0).toFixed(2),
            maleRate: (data.value[1] || 0).toFixed(2),
            femaleRate: (data.value[2] || 0).toFixed(2),
            totalStudents: data.totalStudents || 0,
            totalEmployed: data.totalEmployed || 0,
            totalMale: data.totalMale || 0,
            employedMale: data.employedMale || 0,
            totalFemale: data.totalFemale || 0,
            employedFemale: data.employedFemale || 0
          };
          // 更新概览统计数据
          this.summaryStats.totalEmployed = data.totalEmployed || 0;
          this.summaryStats.overallRate = (data.value[0] || 0).toFixed(1);
          this.summaryStats.studentCount = data.totalStudents || 0;
        }
      });
    },
    getEmploymentDirection() {
      request.post(base + "/getEmploymentDirection", {}).then(res => {
        const data = this.normalizeTongjiData(res);
        const chart = this.chartMap['directionChart'];
        if (!chart) return;
        chart.setOption({
          tooltip: { trigger: 'item', formatter: '{b}: {c}人 ({d}%)' },
          legend: { bottom: 10 },
          series: [{
            type: 'pie', radius: ['40%', '70%'], center: ['50%', '50%'],
            itemStyle: { borderRadius: 8 }, label: { formatter: '{b}\n{d}%' },
            data: this.toPieData(data)
          }]
        });
      });
    },
    getIndustryDistribution() {
      request.post(base + "/getIndustryDistribution", {}).then(res => {
        const data = this.normalizeTongjiData(res);
        const chart = this.chartMap['industryChart'];
        if (!chart) return;
        chart.setOption({
          tooltip: { trigger: 'item', formatter: '{b}: {c}人 ({d}%)' },
          legend: { bottom: 10 },
          series: [{
            type: 'pie', radius: ['40%', '70%'], center: ['50%', '50%'],
            roseType: 'area', itemStyle: { borderRadius: 8 },
            data: this.toPieData(data)
          }]
        });
      });
    },
    getMajorEmploymentRate() {
      request.post(base + "/getMajorEmploymentRate", {}).then(res => {
        const data = this.normalizeTongjiData(res);
        const chart = this.chartMap['majorRateChart'];
        if (!chart) return;
        const detail = res && res.tongjiMap && res.tongjiMap.detail || [];
        chart.setOption({
          tooltip: { trigger: 'axis', formatter: (params) => {
            const idx = params[0].dataIndex;
            return `${params[0].name}<br/>就业率: ${params[0].value}%<br/>(就业人数/总人数: ${detail[idx] || '-'})`;
          }},
          grid: { left: '6%', right: '4%', bottom: '8%', top: '12%', containLabel: true },
          xAxis: { type: 'category', name: '专业', data: data.name, axisLabel: { rotate: data.name.length > 6 ? 25 : 0 } },
          yAxis: { type: 'value', name: '就业率(%)', min: 0, max: 100 },
          series: [{
            type: 'bar', barWidth: '45%', data: data.value.map(v => v.toFixed(2)),
            label: { show: true, position: 'top', formatter: (params) => {
              const idx = params.dataIndex;
              return `${params.value}%(${detail[idx] || '-'})`;
            }},
            itemStyle: { borderRadius: [8, 8, 0, 0], color: new echarts.graphic.LinearGradient(0,0,0,1,[
              {offset:0,color:'#83bff6'},{offset:0.5,color:'#188df0'},{offset:1,color:'#188df0'}])
            }
          }]
        });
      });
    },
    getYearlyEmploymentRate() {
      request.post(base + "/getYearlyEmploymentRate", {}).then(res => {
        const data = this.normalizeTongjiData(res);
        const chart = this.chartMap['yearlyRateChart'];
        if (!chart) return;
        chart.setOption({
          tooltip: { trigger: 'axis', formatter: '{b}: {c}%' },
          grid: { left: '6%', right: '4%', bottom: '8%', top: '12%', containLabel: true },
          xAxis: { type: 'category', name: '年份', data: data.name },
          yAxis: { type: 'value', name: '就业率(%)', min: 0, max: 100 },
          series: [{
            type: 'line', smooth: true, data: data.value.map(v => v.toFixed(2)),
            areaStyle: { color: new echarts.graphic.LinearGradient(0,0,0,1,[
              {offset:0,color:'rgba(103, 194, 58, 0.3)'},{offset:1,color:'rgba(103, 194, 58, 0.05)'}])
            },
            itemStyle: { color: '#67C23A' },
            label: { show: true, position: 'top', formatter: '{c}%' }
          }]
        });
      });
    },
    getUxinxiArea() {
      request.post(base + "/getUxinxiArea", {}).then(res => {
        const data = this.normalizeTongjiData(res);
        const chart = this.chartMap['areaChart'];
        if (!chart) return;
        chart.setOption({
          tooltip: { trigger: 'item', formatter: '{b}: {c}人 ({d}%)' },
          legend: { bottom: 10 },
          series: [{
            type: 'pie', radius: ['35%', '70%'], center: ['50%', '50%'],
            roseType: 'radius', label: { formatter: '{b}: {c}人' },
            data: this.toPieData(data)
          }]
        });
      });
    },
    getUxinxiSalary() {
      request.post(base + "/getUxinxiSalary", {}).then(res => {
        const data = this.normalizeTongjiData(res);
        const chart = this.chartMap['salaryChart'];
        if (!chart) return;
        chart.setOption({
          tooltip: { trigger: 'axis' },
          grid: { left: '6%', right: '4%', bottom: '8%', top: '12%', containLabel: true },
          xAxis: { type: 'category', name: '薪资区间', data: data.name, axisLabel: { rotate: 25 } },
          yAxis: { type: 'value', name: '人数' },
          series: [{ type: 'bar', barWidth: '45%', data: data.value, label: { show: true, position: 'top' } }]
        });
      });
    },
    getMajorSalaryBox() {
      request.post(base + "/getMajorSalaryBox", {}).then(res => {
        if (!res || !res.tongjiMap) return;
        const data = res.tongjiMap;
        const chart = this.chartMap['salaryBoxChart'];
        if (!chart || !data.name || !data.name.length) return;
        
        const boxData = (data.salaryData || []).map(d => {
          if (!d || d.length === 0) return [0, 0, 0, 0, 0];
          d.sort((a, b) => a - b);
          const len = d.length;
          return [d[0], d[Math.floor(len * 0.25)], d[Math.floor(len * 0.5)], d[Math.floor(len * 0.75)], d[len - 1]];
        });
        
        chart.setOption({
          tooltip: { trigger: 'item', formatter: (params) => {
            const v = params.value;
            return `${params.name}<br/>最小值: ${v[0]}<br/>Q1: ${v[1]}<br/>中位数: ${v[2]}<br/>Q3: ${v[3]}<br/>最大值: ${v[4]}`;
          }},
          grid: { left: '6%', right: '4%', bottom: '8%', top: '12%', containLabel: true },
          xAxis: { type: 'category', name: '专业', data: data.name, axisLabel: { rotate: data.name.length > 6 ? 25 : 0 } },
          yAxis: { type: 'value', name: '薪资(元)' },
          series: [{ type: 'boxplot', data: boxData }]
        });
      });
    },
    getUxinxiTrend() {
      request.post(base + "/getUxinxiTrend", {}).then(res => {
        const data = this.normalizeTongjiData(res);
        const chart = this.chartMap['trendChart'];
        if (!chart) return;
        chart.setOption({
          tooltip: { trigger: 'axis' },
          grid: { left: '6%', right: '4%', bottom: '8%', top: '12%', containLabel: true },
          xAxis: { type: 'category', name: '月份', data: data.name },
          yAxis: { type: 'value', name: '就业人数' },
          series: [{
            type: 'line', smooth: true, data: data.value,
            areaStyle: { color: new echarts.graphic.LinearGradient(0,0,0,1,[
              {offset:0,color:'rgba(64, 158, 255, 0.3)'},{offset:1,color:'rgba(64, 158, 255, 0.05)'}])
            },
            itemStyle: { color: '#409EFF' },
            label: { show: true, position: 'top' }
          }]
        });
      });
    },
    getGraduateDirection() {
      request.post(base + "/getGraduateDirection", {}).then(res => {
        if (!res || !res.tongjiMap) return;
        const data = res.tongjiMap;
        this.graduateDirection = {
          entranceRate: data.entranceRate || 0,
          civilServantRate: data.civilServantRate || 0,
          unemployRate: data.unemployRate || 0
        };
        
        const chart = this.chartMap['graduateDirectionChart'];
        if (!chart) return;
        
        const pieData = [];
        (data.name || []).forEach((name, idx) => {
          pieData.push({ name, value: Number(data.value[idx] || 0) });
        });
        
        chart.setOption({
          tooltip: { trigger: 'item', formatter: '{b}: {c}人 ({d}%)' },
          legend: { bottom: 10 },
          series: [{
            type: 'pie', radius: ['40%', '70%'], center: ['50%', '50%'],
            itemStyle: { borderRadius: 8 }, label: { formatter: '{b}: {c}人' },
            data: pieData
          }]
        });
      });
    },
    getSalaryAnalysis() {
      request.post(base + "/getSalaryAnalysis", {}).then(res => {
        if (!res || !res.tongjiMap) return;
        const data = res.tongjiMap;
        this.salaryAnalysis = { avgSalary: data.avgSalary || 0 };
        
        const pieChart = this.chartMap['salaryPieChart'];
        if (pieChart) {
          const pieData = [];
          (data.salaryRangeNames || []).forEach((name, idx) => {
            pieData.push({ name, value: Number(data.salaryRangeValues[idx] || 0) });
          });
          pieChart.setOption({
            title: { text: '薪资分段分布', left: 'center', top: 10 },
            tooltip: { trigger: 'item', formatter: '{b}: {c}人 ({d}%)' },
            legend: { bottom: 10, orient: 'horizontal' },
            series: [{ type: 'pie', radius: ['40%', '70%'], center: ['50%', '52%'], data: pieData }]
          });
        }
        
        const barChart = this.chartMap['majorSalaryChart'];
        if (barChart) {
          barChart.setOption({
            title: { text: '各专业平均薪资', left: 'center', top: 10 },
            tooltip: { trigger: 'axis' },
            grid: { left: '10%', right: '4%', bottom: '15%', top: '18%', containLabel: true },
            xAxis: { type: 'category', data: data.majorNames || [], axisLabel: { rotate: 25 } },
            yAxis: { type: 'value', name: '薪资(元)' },
            series: [{ type: 'bar', barWidth: '40%', data: data.majorSalaries || [], label: { show: true, position: 'top' } }]
          });
        }
      });
    },
    getAreaAnalysis() {
      request.post(base + "/getAreaAnalysis", {}).then(res => {
        if (!res || !res.tongjiMap) return;
        const data = res.tongjiMap;
        
        const ratioChart = this.chartMap['areaRatioChart'];
        if (ratioChart) {
          ratioChart.setOption({
            tooltip: { trigger: 'item', formatter: '{b}: {c}人 ({d}%)' },
            series: [{
              type: 'pie', radius: ['40%', '70%'], center: ['50%', '50%'],
              label: { formatter: '{b}\n{d}%' },
              data: [
                { name: '省内就业', value: data.inProvince || 0 },
                { name: '省外就业', value: data.outProvince || 0 }
              ]
            }]
          });
        }
        
        const levelChart = this.chartMap['cityLevelChart'];
        if (levelChart) {
          levelChart.setOption({
            tooltip: { trigger: 'axis' },
            grid: { left: '10%', right: '4%', bottom: '10%', top: '15%', containLabel: true },
            xAxis: { type: 'category', data: data.cityLevels || [] },
            yAxis: { type: 'value', name: '人数' },
            series: [{ type: 'bar', barWidth: '40%', data: data.cityValues || [], label: { show: true, position: 'top' } }]
          });
        }
      });
    },
    getUnemployedAnalysis() {
      request.post(base + "/getUnemployedAnalysis", {}).then(res => {
        if (!res || !res.tongjiMap) return;
        const data = res.tongjiMap;
        
        const reasonChart = this.chartMap['unemployedReasonChart'];
        if (reasonChart) {
          const reasonData = [];
          (data.reasonNames || []).forEach((name, idx) => {
            reasonData.push({ name, value: Number(data.reasonValues[idx] || 0) });
          });
          reasonChart.setOption({
            tooltip: { trigger: 'item', formatter: '{b}: {c}人 ({d}%)' },
            legend: { bottom: 10 },
            series: [{
              type: 'pie', radius: ['40%', '70%'], center: ['50%', '50%'],
              label: { formatter: '{b}\n{d}%' },
              data: reasonData
            }]
          });
        }
        
        const majorChart = this.chartMap['unemployedMajorChart'];
        if (majorChart) {
          majorChart.setOption({
            tooltip: { trigger: 'axis' },
            grid: { left: '10%', right: '4%', bottom: '15%', top: '12%', containLabel: true },
            xAxis: { type: 'category', data: data.majorNames || [], axisLabel: { rotate: 25 } },
            yAxis: { type: 'value', name: '未就业人数' },
            series: [{ type: 'bar', barWidth: '40%', data: data.majorValues || [], label: { show: true, position: 'top' } }]
          });
        }
      });
    },
    getSupplyDemand() {
      request.post(base + "/getSupplyDemand", {}).then(res => {
        if (!res || !res.tongjiMap) return;
        const data = res.tongjiMap;
        this.supplyDemand = { ratio: data.ratio || 0 };
        // 更新概览统计数据
        this.summaryStats.supplyDemandRatio = (data.ratio || 0).toFixed(1);
        
        const chart = this.chartMap['supplyDemandChart'];
        if (!chart) return;
        chart.setOption({
          tooltip: { trigger: 'axis' },
          grid: { left: '15%', right: '15%', bottom: '10%', top: '15%', containLabel: true },
          xAxis: { type: 'category', data: ['招聘岗位数', '求职人数'] },
          yAxis: { type: 'value' },
          series: [{
            type: 'bar', barWidth: '40%',
            data: [
              { value: data.totalJobs || 0, itemStyle: { color: '#67C23A' } },
              { value: data.jobSeekers || 0, itemStyle: { color: '#409EFF' } }
            ],
            label: { show: true, position: 'top', formatter: '{c}' }
          }]
        });
      });
    }
  }
};
</script>

<style scoped>
.analysis-page { margin: 10px; min-height: calc(100vh - 20px); background: #f5f7fa; border-radius: 12px; padding: 16px; box-sizing: border-box; }

.filter-bar { display: flex; justify-content: space-between; align-items: center; padding: 12px 16px; background: #fff; border-radius: 8px; margin-bottom: 16px; }
.filter-left { flex: 1; }
.filter-right { margin-left: 20px; }

.analysis-header { display: flex; justify-content: space-between; align-items: center; margin-bottom: 18px; padding-bottom: 12px; border-bottom: 1px solid #ebeef5; }
.header-left { display: flex; flex-direction: column; }
.analysis-title { font-size: 24px; font-weight: 700; color: #303133; }
.analysis-desc { font-size: 13px; color: #909399; margin-top: 6px; }

/* 统计卡片样式 */
.stat-card { display: flex; align-items: center; background: #fff; border-radius: 12px; padding: 20px; box-shadow: 0 2px 12px rgba(0,0,0,0.08); transition: all 0.3s ease; }
.stat-card:hover { transform: translateY(-4px); box-shadow: 0 8px 24px rgba(0,0,0,0.12); }
.stat-icon { width: 56px; height: 56px; border-radius: 14px; display: flex; align-items: center; justify-content: center; font-size: 28px; margin-right: 16px; }
.stat-icon.employment { background: linear-gradient(135deg, #667eea 0%, #764ba2 100%); }
.stat-icon.rate { background: linear-gradient(135deg, #4facfe 0%, #00f2fe 100%); }
.stat-icon.salary { background: linear-gradient(135deg, #43e97b 0%, #38f9d7 100%); }
.stat-icon.major { background: linear-gradient(135deg, #fa709a 0%, #fee140 100%); }
.stat-content { flex: 1; }
.stat-label { font-size: 13px; color: #909399; margin-bottom: 6px; }
.stat-value { font-size: 28px; font-weight: 700; color: #303133; }
.stat-change { font-size: 12px; margin-top: 4px; }
.stat-change.positive { color: #67c23a; }
.stat-change.negative { color: #f56c6c; }
.stat-change.neutral { color: #909399; }

.analysis-row { margin-bottom: 20px; }

.chart-card { border-radius: 14px; overflow: hidden; }
.card-header { display: flex; align-items: center; justify-content: space-between; font-size: 16px; font-weight: 600; color: #303133; }
.card-subtitle { font-size: 12px; font-weight: 400; color: #909399; }

.chart-box { width: 100%; height: 360px; }
.chart-box-medium { width: 100%; height: 320px; }
.chart-box-large { width: 100%; height: 420px; }

.rate-content { padding: 16px; }
.rate-grid { display: flex; justify-content: space-around; flex-wrap: wrap; }
.rate-item { text-align: center; padding: 20px 30px; background: linear-gradient(135deg, #667eea 0%, #764ba2 100%); border-radius: 12px; min-width: 200px; margin: 8px; }
.rate-item.male { background: linear-gradient(135deg, #4facfe 0%, #00f2fe 100%); }
.rate-item.female { background: linear-gradient(135deg, #fa709a 0%, #fee140 100%); }
.rate-label { font-size: 14px; color: rgba(255,255,255,0.9); margin-bottom: 8px; }
.rate-value { font-size: 36px; font-weight: 700; color: #fff; margin-bottom: 4px; }
.rate-detail { font-size: 12px; color: rgba(255,255,255,0.8); }

.direction-container { display: flex; gap: 20px; }
.direction-container > div:first-child { flex: 1; }
.direction-cards { display: flex; flex-direction: column; justify-content: center; gap: 12px; min-width: 150px; }
.dir-card { background: #f8f9fa; border-radius: 8px; padding: 12px; text-align: center; }
.dir-label { font-size: 13px; color: #606266; margin-bottom: 4px; }
.dir-value { font-size: 24px; font-weight: 700; color: #409EFF; }

.salary-container { display: flex; gap: 20px; }
.salary-container > div { flex: 1; }

@media screen and (max-width: 768px) {
  .filter-bar { flex-direction: column; gap: 12px; }
  .filter-form { width: 100%; }
  .direction-container, .salary-container { flex-direction: column; }
  .chart-box { height: 320px; }
  .chart-box-large { height: 360px; }
  .rate-item { min-width: 140px; padding: 16px 20px; }
  .rate-value { font-size: 28px; }
  .stat-card { flex-direction: column; text-align: center; }
  .stat-icon { margin-right: 0; margin-bottom: 12px; }
}
</style>