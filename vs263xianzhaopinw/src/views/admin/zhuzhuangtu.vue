<template>
  <section style="margin: 10px">
    <div>
      <el-form :inline="true" :model="filters">
        <el-form-item>
          <el-input v-model="filters.sjshaochuMark" size="small" placeholder="学校" style="width: 100%"></el-input>
        </el-form-item>
        <el-form-item>
          <el-input v-model="filters.sjshaochuMark1" size="small" placeholder="学历" style="width: 100%"></el-input>
        </el-form-item>
        <el-form-item>
          <el-input v-model="filters.sjshaochuMark2" size="small" placeholder="专业" style="width: 100%"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="info" size="small" @click="query">查询</el-button>
        </el-form-item>
      </el-form>
    </div>
    <div>
      <el-card>
        <div id="main" style="width: 800px;height:600px;"></div>
      </el-card>
    </div>
  </section>
</template>
<script>
import request, { base } from "../../../utils/http";
// 导入echarts
import * as echarts from 'echarts';
export default {
  data(){
      return {
        filters: {
          //列表查询参数       
          shujuName: "",
          sjleixingId: "",    
          sdate: "",
          edate: "",
        },
        jcpeizhi:{}, //基础配置数据
        sjleixingList: "",
        tongjiData:{},//获取的统计数据
        tongjiNames: [], //统计名称
        tongjiZongshus: [], //统计数据
        tongjiShuju: "", //统计总数
      }
    },
    created(){
      this.getSjleixingList();
      this.jcpeizhi = sessionStorage.getItem("jcpeizhi");
      this.jcpeizhi = JSON.parse(this.jcpeizhi);
      this.getDatas();
    },
    methods: {
      //获取sjleixing
      getSjleixingList() {
        let para = {};
        let url = base + "/sjleixingComboList";
        request.post(url, para).then((res) => {
          this.sjleixingList = res.resdata;
        });
      },
      //查询
      query() {
        this.getDatas();
      },
      //搜索条件数据
      getDatas() {
        console.log("getDatas步骤");

        let para = {
          sjshaochuMark: this.filters.sjshaochuMark,
          sjshaochuMark1: this.filters.sjshaochuMark1,
          sjshaochuMark2: this.filters.sjshaochuMark2,
          sjleixingId: this.filters.sjleixingId,
        };
        if(this.role=='user'){
        var user = sessionStorage.getItem("currentUser");
        user = JSON.parse(user);
          para = {
            sjshaochuMark: this.filters.sjshaochuMark,
            sjshaochuMark1: this.filters.sjshaochuMark1,
            sjshaochuMark2: this.filters.sjshaochuMark2,
            sjleixingId: this.filters.sjleixingId,
            userId:user.userId
          };     
        }
        let url = base + "/sjshaochuTongji";
        request.post(url, para).then((res) => {
          this.tongjiData = res;
          this.tongjiShuju = res.tongjiMap;
          //console.log("getDatas步骤后的提交步骤" + this.tongjiShuju);
          this.getLoadEcharts();
        });
      },
      getLoadEcharts() {
        var myChart = echarts.init(document.getElementById('main'));
        // 指定图表的配置项和数据
        var option;
        option = {
          title: {
            text: '柱状统计图'
          },
          tooltip: {
            trigger: 'axis'
          },
          grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true
          },
          toolbox: {
            feature: {
              saveAsImage: {}
            }
          },
          xAxis: {
            type: 'category',
            data: this.tongjiShuju.name
          },
          yAxis: {
            type: 'value'
          },
          series: [
            {
              data: this.tongjiShuju.value,
              type: 'bar',
              showBackground: true,
              backgroundStyle: {
                color: 'rgba(180, 180, 180, 0.2)'
              }
            }
          ]
        };
        
        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option,true);
      },
    },
    // 此时页面上的元素已被渲染完毕
    mounted(){

    },
}
</script>