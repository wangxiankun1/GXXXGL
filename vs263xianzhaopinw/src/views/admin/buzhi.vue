<template>
  <section style="margin: 10px">
    <!--工具条-->
    <el-col :span="24" class="toolbar" style="padding-bottom: 0px; margin-left: 10px" >
      <el-form :inline="true" :model="filters">
        <el-form-item>
          <el-input v-model="filters.buzhiName" size="small" placeholder="名称" ></el-input>
        </el-form-item>
        <el-form-item :label=jcpeizhi.bumenBieming prop="bumenId">
          <el-select size="small" v-model="filters.bumenId" placeholder="请选择" style="width: 50%">
            <el-option label="请选择" value=""> </el-option>
            <el-option v-for="item in bumenList" :key="item.bumenId" :label="item.bumenName" :value="item.bumenId">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item>
          <el-button type="info" size="small" @click="query">查询</el-button>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" size="small" @click="handleAdd">新增</el-button>
        </el-form-item>
        <el-form-item>
          <el-button type="danger" size="small" @click="deleteBuzhis" :disabled="selectedIds.length === 0">批量删除</el-button>
        </el-form-item>

      </el-form>
    </el-col>
    <!--列表-->
    <el-table ref="exportTableRef" @selection-change="handleSelectionChange" id="myTable" :data="datalist" border stripe v-loading="listLoading" highlight-current-row max-height="800" size="small" style="width: 100%">
      <el-table-column  type="selection"  width="40" />
      <el-table-column sortable type="index" align="center" width="50" label="编号"> </el-table-column>
      <el-table-column sortable prop="buzhiId" align="center" label="Id" min-width="20%" v-if="false"></el-table-column>
      <el-table-column sortable prop="buzhiName" align="center" label="名称" min-width="40%"></el-table-column>
      <el-table-column prop="buzhiMark" align="center" label="备注" :show-overflow-tooltip="true" min-width="80%"></el-table-column>
      <!--操作按钮开始-->
      <el-table-column label="操作" align="center" width="170">
        <template #default="scope">
          <el-button type="success" size="mini" @click="handleEdit(scope.$index, scope.row)" >编辑</el-button>
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
    <!--表单-->
    <el-dialog :title=jcpeizhi.buzhiBieming v-model="formVisible" width="40%" :close-on-click-modal="false">
      <el-form :model="formData" ref="formDataRef" :rules="rules" label-width="100px">
        <el-form-item label="名称" prop="buzhiName">
          <el-input v-model="formData.buzhiName" style="width: 50%"></el-input>
        </el-form-item>
        <el-form-item label="备注" prop="buzhiMark">
          <el-input type="textarea" :rows="4" v-model="formData.buzhiMark" style="width: 80%"></el-input>
        </el-form-item>
      </el-form>
      <template #footer>
        <span class="dialog-footer">
          <el-button @click="formVisible = false">取 消</el-button>
          <el-button type="primary" :loading="btnLoading" @click="save">确 定</el-button>
        </span>
      </template>
    </el-dialog>
    <!--表单结束-->
  </section>
</template>
<script>
import request, { base } from "../../../utils/http";  //请求地址
import WangEditor from "../../components/WangEditor"; //富文本
export default {
  name: "buzhi",
  components: {
    WangEditor,
  },
  data() {
    return {
      filters: {
        //列表预加载参数
        buzhiName: "",
        buzhiMark: "",
        bumenId: "",
      },
      page: {
        currentPage: 1, // 当前页
        pageSize: 10, // 每页显示条目个数
        totalCount: 0, // 总条目数
      },
      jcpeizhi:{}, //基础配置数据
      isClear: false,//富文本触发清除文本域内容
      uploadVisible: false,//上传表单不可见
      bumenList: "",//下拉列表
      role:'',//权限
      listLoading: false, //列表加载状态
      btnLoading: false, //保存按钮加载状态
      datalist: [], //表格数据
      formVisible: false, //表单是否可见，默认不可见
      formData: {}, //表单数据
      selectedIds: [],//多选数据
      rules: {     
        buzhiName: [{ required: true, message: "不能为空", trigger: "blur" }],
        buzhiMark: [{ required: true, message: "不能为空", trigger: "blur" }],
      },
    };
  },
  created() {
    let role = sessionStorage.getItem('role');
    this.role = role;
    this.jcpeizhi = sessionStorage.getItem("jcpeizhi");
    this.jcpeizhi = JSON.parse(this.jcpeizhi);
    this.getDatas();
    this.getBumenList();
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
    //富文本
    editorChange(info) {
      this.formData.buzhiMark = info;
    },
    //打开修改界面
    handleEdit(index, row) {
      this.formVisible = true;
      this.ureadonly = true;
      this.formData = JSON.parse(JSON.stringify(row));
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
            delIds: row.buzhiId,
          };
          let url = base + "/deleteBuzhi";
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
    //多选项
    handleSelectionChange(selection) {
      this.selectedIds = selection.map((item) => item.buzhiId);
      this.delIds = JSON.stringify(this.selectedIds).replace(/[\[\]]/g,"");
      console.log(selection);
      console.log(this.delIds);
    },
    //多选批量删除
    deleteBuzhis() {
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
          let url = base + "/deleteBuzhi";
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
        buzhiName: this.filters.buzhiName,
        bumenId: this.filters.bumenId,
      };
      if(this.role=='user'){
        var user = sessionStorage.getItem("currentUser");
        user = JSON.parse(user);
        para = {
          buzhiName: this.filters.buzhiName,
          bumenId: this.filters.bumenId,
          userId:user.userId
        };     
      }
      if(this.role=='yonghu'){
        var user = sessionStorage.getItem("currentUser");
        user = JSON.parse(user);
        para = {
          buzhiName: this.filters.buzhiName,
          bumenId: this.filters.bumenId,
          userId:user.yonghuId
        };     
      }
      //console.log(para)
      this.listLoading = true;
      let url = base + "/getBuzhis?page=" + this.page.currentPage;
      //console.log(url);
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
    //获取bumen
    getBumenList() {
      let para = {};
      this.listLoading = true;
      let url = base + "/bumenComboList";
      request.post(url, para).then((res) => {
        if (res.code == 200) {
          this.bumenList = res.resdata;
        }
      });
    },
    //添加修改
    save() {
      if (this.formData.buzhiId != null) {
        //如果有ID就修改数据
        this.$refs["formDataRef"].validate((valid) => {
          if (valid) {
            let url = base + "/addBuzhi?buzhiId=" + this.formData.buzhiId;
            this.btnLoading = true;
            request.post(url, this.formData).then((res) => {
              if (res.code == 200) {
                this.btnLoading = false;
                this.formVisible = false;
                this.$message({
                  message: "操作成功",
                  type: "success",
                  offset: 320,
                });
                this.getDatas();
              } else {
                this.$message({
                  message: res.msg,
                  type: "error",
                  offset: 320,
                });
              }
            });
          }
        });
      } else {
        //如果没有ID就新增数据
        this.$refs["formDataRef"].validate((valid) => {
          if (valid) {
            let url = base + "/addBuzhi";
            var user = sessionStorage.getItem("currentUser");
            user = JSON.parse(user);
            this.formData.userId = user.userId;
            this.btnLoading = true;
            request.post(url, this.formData).then((res) => {
              console.log(res);
              if (res.code == 200) {
                this.$message({
                  message: "操作成功",
                  type: "success",
                  offset: 320,
                });
                this.btnLoading = false;
                this.formVisible = false;
                this.getDatas();
              } else {
                this.$message({
                  message: res.msg,
                  type: "error",
                  offset: 320,
                });
              }
            });
          }
        });
      }
    },
    //根据数据状态显示文本
    zhuangtaiType(row){
        if(row.buzhiType==0){
             return "启用";
        }
        if(row.buzhiType==1){
             return "禁用";
        }
    },
    //单项更改状态
    shezhiZhuangtai(index, row,zhuangtai) {
      this.listLoading = true;
      let para = {
        buzhiType: zhuangtai,
        buzhiId: row.buzhiId,
      };

      let url = base + "/addBuzhi";
      request.post(url,para).then((res) => {
        this.listLoading = false;
        if (res.code == 200) {
          this.$message({
            message: "操作成功",
            type: "success",
            offset: 320,
          });
          this.getDatas();
        }else{
          this.$message({
            message: res.msg,
            offset: 320,
          });
        }
      });
    },
    
  },
};
</script>
