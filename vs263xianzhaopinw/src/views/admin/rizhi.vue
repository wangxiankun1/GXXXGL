<template>
  <section style="margin: 10px">
    <!--工具条-->
    <el-col :span="24" class="toolbar" style="padding-bottom: 0px; margin-left: 10px" >
      <el-form :inline="true" :model="filters">
        <el-form-item>
          <el-button type="danger" size="small" @click="deleteRizhis" :disabled="selectedIds.length === 0">批量删除</el-button>
          <!-- 可选：添加导出按钮，修复后可用 -->
          <el-button type="primary" size="mini" @click="exportTable">导出Excel</el-button>
        </el-form-item>
      </el-form>
    </el-col>
    <!--列表-->
    <el-table
        ref="exportTableRef"
        @selection-change="handleSelectionChange"
        id="myTable"
        :data="datalist"
        border
        stripe
        v-loading="listLoading"
        highlight-current-row
        max-height="800"
        size="small"
        style="width: 100%"
    >
      <el-table-column type="selection" width="40" />
      <el-table-column sortable type="index" align="center" width="50" label="编号" />
      <el-table-column sortable prop="rizhiId" align="center" label="Id" min-width="20%" v-if="false" />
      <el-table-column sortable prop="rizhiName" align="center" label="用户名" min-width="40%" />
      <el-table-column sortable prop="dengluIp" align="center" label="IP" min-width="40%" />
      <el-table-column sortable prop="date" align="center" label="时间" min-width="40%" :formatter="formatDate" />
      <el-table-column prop="rizhiType" label="操作类型" />
      <el-table-column prop="rizhiResult" label="操作结果">
        <!-- 修复1：简化作用域插槽判断，Element Plus scope 必定存在 row -->
        <template #default="scope">
          <el-tag :type="scope.row.rizhiResult === '成功' ? 'success' : 'danger'">
            {{ scope.row.rizhiResult }}
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column prop="rizhiRemark" label="备注" />
      <!--操作按钮-->
      <el-table-column label="操作" align="center" width="300">
        <template #default="scope">
          <el-button type="danger" size="mini" @click="handleDelete(scope.$index, scope.row)">删除</el-button>
        </template>
      </el-table-column>
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
    />
  </section>
</template>

<script>
// 修复2：导入导出依赖（项目需安装 xlsx file-saver）
import XLSX from 'xlsx'
import FileSaver from 'file-saver'
import request, { base } from "../../../utils/http";
export default {
  name: "rizhi",
  data() {
    return {
      filters: {
        rizhiName: "",
        sdate: "",
        edate: "",
      },
      page: {
        currentPage: 1,
        pageSize: 10,
        totalCount: 0,
      },
      jcpeizhi:{},
      selectedIds: [],
      // 修复3：添加缺失的响应式变量
      delIds: '',
      isPage: false,
      listLoading: false,
      datalist: [],
    };
  },
  created() {
    this.getDatas();
  },
  methods: {
    handleAdd() {
      this.formVisible = true;
      this.ureadonly = false;
      this.files = [];
      this.fileList = [];
      this.formData = {};
    },
    // 删除单条
    handleDelete(index, row) {
      this.$confirm("确认删除该记录吗?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      }).then(() => {
        this.listLoading = true;
        let para = { delIds: row.rizhiId };
        request.post(base + "/deleteRizhi", para).then((res) => {
          this.listLoading = false;
          this.$message.success("删除成功");
          this.getDatas();
        }).catch(() => {
          this.listLoading = false;
        });
      }).catch(() => {});
    },
    // 分页
    handleCurrentChange(val) {
      this.page.currentPage = val;
      this.getDatas();
    },
    // 修复4：日期格式化，返回空字符串而非null，避免渲染异常
    formatDate(row, column) {
      let data = row[column.property]
      if(!data) return ''
      let dt = new Date(data)
      return dt.getFullYear() + '-' +
          (dt.getMonth() + 1).toString().padStart(2, '0') + '-' +
          dt.getDate().toString().padStart(2, '0') + ' ' +
          dt.getHours().toString().padStart(2, '0') + ':' +
          dt.getMinutes().toString().padStart(2, '0') + ':' +
          dt.getSeconds().toString().padStart(2, '0')
    },
    handlePrint() {
      window.print()
    },
    // 修复5：核心！修复导出方法的DOM获取错误（原代码直接用myTable导致崩溃）
    exportTable() {
      const tableDom = document.getElementById('myTable');
      if (!tableDom) return this.$message.warning('表格不存在');

      const filename = "登录日志.xlsx";
      const wb = XLSX.utils.table_to_book(tableDom);
      const wbout = XLSX.write(wb, { bookType: "xlsx", bookSST: true, type: "array" });

      FileSaver.saveAs(new Blob([wbout], { type: "application/octet-stream" }), filename);
      this.$message.success('导出成功');
    },
    // 多选事件
    handleSelectionChange(selection) {
      this.selectedIds = selection.map(item => item.rizhiId);
      this.delIds = this.selectedIds.join(','); // 简化字符串处理
    },
    // 批量删除
    deleteRizhis() {
      this.$confirm("确认删除所选记录吗?", "提示", { type: "warning" })
          .then(() => {
            this.listLoading = true;
            request.post(base + "/deleteRizhi", { delIds: this.delIds }).then((res) => {
              this.listLoading = false;
              this.$message.success("批量删除成功");
              this.getDatas();
            }).catch(() => {
              this.listLoading = false;
            });
          }).catch(() => {});
    },
    query() {
      this.page.currentPage = 1; // 查询重置页码
      this.getDatas();
    },
    // 修复6：添加catch捕获异常，永远关闭loading（解决页面一直转圈）
    getDatas() {
      let para = {
        rizhiName: this.filters.rizhiName,
        sdate: this.filters.sdate,
        edate: this.filters.edate,
      };
      this.listLoading = true;
      let url = `${base}/getRizhis?page=${this.page.currentPage}`;

      request.post(url, para).then((res) => {
        this.isPage = res.resdata.length > 0;
        this.page.totalCount = res.count || 0;
        this.datalist = res.resdata || [];
      }).catch((err) => {
        console.error('获取日志失败：', err);
        this.$message.error('数据加载失败');
        this.datalist = [];
      }).finally(() => {
        // 无论成功失败，都关闭loading
        this.listLoading = false;
      });
    },
  },
};
</script>