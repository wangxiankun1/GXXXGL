<template>
  <section style="margin: 10px">
    <!--工具条-->
    <el-col :span="24" class="toolbar" style="padding-bottom: 0px; margin-left: 10px" >
      <el-form :inline="true" :model="filters">
        <el-form-item>
          <el-button type="danger" size="small" @click="deleteRizhis" :disabled="selectedIds.length === 0">批量删除</el-button>
        </el-form-item>

      </el-form>
    </el-col>
    <!--列表-->
    <el-table ref="exportTableRef" @selection-change="handleSelectionChange" id="myTable" :data="datalist" border stripe v-loading="listLoading" highlight-current-row max-height="800" size="small" style="width: 100%">
      <el-table-column  type="selection"  width="40" />
      <el-table-column sortable type="index" align="center" width="50" label="编号"> </el-table-column>
      <el-table-column sortable prop="rizhiId" align="center" label="Id" min-width="20%" v-if="false"></el-table-column>
      <el-table-column sortable prop="rizhiName" align="center" label="用户名" min-width="40%"></el-table-column>
      <el-table-column sortable prop="dengluIp" align="center" label="IP" min-width="40%"></el-table-column>
      <el-table-column sortable prop="date" align="center" label="时间" min-width="40%" :formatter="formatDate"></el-table-column>
      <!--操作按钮开始-->
      <el-table-column label="操作" align="center" width="300">
        <template #default="scope">
          <el-button type="danger" size="mini" @click="handleDelete(scope.$index, scope.row)" >删除</el-button>
        </template>
      </el-table-column>
      <!--操作按钮结束-->
    </el-table>
    <!--分页-->
    <el-pagination
      @current-change="handleCurrentChange"
      :current-page="page.currentPage"
      :page-size="page.pageSize"
      background
      layout="total, prev, pager, next, jumper"
      :total="page.totalCount"
      style="float: right; margin: 10px 20px 0 0"
    >
    </el-pagination>
  </section>
</template>
<script>
import request, { base } from "../../../utils/http";  //请求地址
export default {
  name: "rizhi",
  data() {
    return {
      filters: {
        //列表查询参数
        rizhiName: "",
        sdate: "",
        edate: "",
      },
      page: {
        currentPage: 1, // 当前页
        pageSize: 10, // 每页显示条目个数
        totalCount: 0, // 总条目数
      },
      jcpeizhi:{}, //基础配置数据
      selectedIds: [],//多选数据
      listLoading: false, //列表加载状态
      datalist: [], //表格数据
    };
  },
  created() {
    this.getDatas();
  },
  methods: {
    handleAdd() {
      //弹出新增窗口
      this.formVisible = true;
      this.ureadonly = false;

      this.files = [];
      this.fileList = [];

      this.formData = {};
    },
    //删除
    handleDelete(index, row) {
      this.$confirm("确认删除该记录吗?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          this.listLoading = true;
          let para = {
            delIds: row.rizhiId,
          };
          let url = base + "/deleteRizhi";
          request.post(url,para).then((res) => {
            this.listLoading = false;
            //NProgress.done();
            this.$message({
              message: "删除成功",
              type: "success",
              offset: 320,
            });
            this.getDatas();
          });
        })
        .catch(() => {});
    },
    
    //分页
    handleCurrentChange(val) {
      this.page.currentPage = val;
      this.getDatas();
    },
    //打印文件名
    handlePreview(file) {
      console.log(file);
    },
    //显示日期
    formatDate(row, column) {
      // 获取单元格数据
      let data = row[column.property]
      if(data == null) {
          return null
      }
      let dt = new Date(data)
      return dt.getFullYear() + '-' + (dt.getMonth() + 1) + '-' + dt.getDate() + ' ' + dt.getHours() + ':' + dt.getMinutes() + ':' + dt.getSeconds()
    },
    /**
     * 调用浏览器的打印方法
    */
    handlePrint() {
      window.print()
    },
    /**
     * 调用导出方法
    */
    exportTable() {
      // 获取表格元素
      const els = this.$refs.exportTableRef;
      // 文件名
      const filename = "导出数据.xlsx";
      /* generate workbook object from table */
      const wb = XLSX.utils.table_to_book(myTable);
      const wbout = XLSX.write(wb, {
        bookType: "xlsx",
        bookSST: true,
        type: "array",
      });
      try {
        FileSaver.saveAs(
          new Blob([wbout], { type: "application/octet-stream" }),
          filename
        );
      } catch (e) {
        console.log(e);
      }
      return wbout;
    },
    //多选项
    handleSelectionChange(selection) {
      this.selectedIds = selection.map((item) => item.rizhiId);
      this.delIds = JSON.stringify(this.selectedIds).replace(/[\[\]]/g,"");
      console.log(selection);
      console.log(this.delIds);
    },
    //多选批量删除
    deleteRizhis() {
      this.$confirm("确认删除所选的记录吗?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          this.listLoading = true;
          let para = {
            delIds: this.delIds,
          };
          let url = base + "/deleteRizhi";
          request.post(url,para).then((res) => {
            this.listLoading = false;
            //NProgress.done();
            this.$message({
              message: "删除成功",
              type: "success",
              offset: 320,
            });
            this.getDatas();
          });
        })
        .catch(() => {});
    },
    //查询
    query() {
      this.getDatas();
    },
    //搜索条件数据
    getDatas() {

      let para = {
        rizhiName: this.filters.rizhiName,
        sdate: this.filters.sdate,
        edate: this.filters.edate,
      };
      this.listLoading = true;
      let url = base + "/getRizhis?page=" + this.page.currentPage;
      request.post(url, para).then((res) => {
        console.log(res);
        if (res.resdata.length > 0) {
          this.isPage = true;
        } else {
          this.isPage = false;
        }
        this.page.totalCount = res.count;
        this.datalist = res.resdata;
        this.listLoading = false;
      });
    },
    
  },
};
</script>
