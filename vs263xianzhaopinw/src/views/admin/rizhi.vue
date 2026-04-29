<template>
  <section style="margin: 10px">

      <el-form :inline="true" :model="filters" @submit.prevent>
        <el-form-item>
          <el-input v-model="filters.rizhiName" placeholder="请输入用户名" size="small" style="width: 200px"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" size="small" icon="el-icon-search" @click="query">查询</el-button>
        </el-form-item>
        <el-form-item>
          <el-button type="danger" size="small" icon="el-icon-delete" @click="deleteRizhis" :disabled="selectedIds.length === 0">批量删除</el-button>
        </el-form-item>
        <el-form-item>
          <el-button type="warning" size="small" icon="el-icon-download" @click="exportTable" :disabled="selectedIds.length === 0">导出Excel</el-button>
        </el-form-item>
      </el-form>


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
        <template #default="scope">
          <el-tag :type="scope.row.rizhiResult === '成功' ? 'success' : 'danger'">
            {{ scope.row.rizhiResult }}
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column prop="rizhiRemark" label="备注" />
      <el-table-column label="操作" align="center" width="150">
        <template #default="scope">
          <el-button type="danger" size="mini" @click="handleDelete(scope.$index, scope.row)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

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
      selectedRows: [],
      delIds: '',
      isPage: false,
      listLoading: false,
      datalist: [],
      currentAdminName: ''
    };
  },
  created() {
    const adminStr = localStorage.getItem('admin');
    if (adminStr) {
      try {
        const adminObj = JSON.parse(adminStr);
        this.currentAdminName = adminObj.adminName || '管理员';
      } catch (e) {
        this.currentAdminName = '管理员';
      }
    }
    this.getDatas();
  },
  methods: {
    handleDelete(index, row) {
      this.$confirm("确认删除该记录吗?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      }).then(() => {
        this.listLoading = true;
        let para = {
          delIds: row.rizhiId,
          currentUserName: this.currentAdminName
        };
        request.post(base + "/deleteRizhi", para).then((res) => {
          this.listLoading = false;
          this.$message.success("删除成功");
          this.getDatas();
        }).catch(() => {
          this.listLoading = false;
        });
      }).catch(() => {});
    },
    handleCurrentChange(val) {
      this.page.currentPage = val;
      this.getDatas();
    },
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
    exportTable() {
      if (!this.selectedRows || this.selectedRows.length === 0) {
        return this.$message.warning('请至少勾选一条需要导出的数据');
      }
      const exportData = this.selectedRows.map((item, index) => {
        return {
          '编号': index + 1,
          '用户名': item.rizhiName,
          'IP': item.dengluIp,
          '时间': this.formatDate(item, { property: 'date' }),
          '操作类型': item.rizhiType,
          '操作结果': item.rizhiResult,
          '备注': item.rizhiRemark
        };
      });
      const filename = "登录日志导出.xlsx";
      const ws = XLSX.utils.json_to_sheet(exportData);
      const wb = XLSX.utils.book_new();
      XLSX.utils.book_append_sheet(wb, ws, "Sheet1");
      const wbout = XLSX.write(wb, { bookType: "xlsx", bookSST: true, type: "array" });
      FileSaver.saveAs(new Blob([wbout], { type: "application/octet-stream" }), filename);
      this.$message.success('导出成功');
    },
    handleSelectionChange(selection) {
      this.selectedRows = selection;
      this.selectedIds = selection.map(item => item.rizhiId);
      this.delIds = this.selectedIds.join(',');
    },
    deleteRizhis() {
      this.$confirm("确认删除所选记录吗?", "提示", { type: "warning" })
          .then(() => {
            this.listLoading = true;
            request.post(base + "/deleteRizhi", {
              delIds: this.delIds,
              currentUserName: this.currentAdminName
            }).then((res) => {
              this.listLoading = false;
              this.$message.success("批量删除成功");
              this.getDatas();
            }).catch(() => {
              this.listLoading = false;
            });
          }).catch(() => {});
    },
    query() {
      this.page.currentPage = 1;
      this.getDatas();
    },
    getDatas() {
      let para = {
        rizhiName: this.filters.rizhiName,
        sdate: this.filters.sdate,
        edate: this.filters.edate,
      };
      this.listLoading = true;
      let url = `${base}/getRizhis?page=${this.page.currentPage}`;

      request.post(url, para).then((res) => {
        this.page.totalCount = res.count || 0;
        this.datalist = res.resdata || [];
      }).catch((err) => {
        this.$message.error('数据加载失败');
        this.datalist = [];
      }).finally(() => {
        this.listLoading = false;
      });
    },
  },
};
</script>

<style scoped>
.toolbar {
  background: #f2f2f2;
  padding: 10px;
  border: 1px solid #dfe6ec;
  margin: 10px 0px;
}
</style>