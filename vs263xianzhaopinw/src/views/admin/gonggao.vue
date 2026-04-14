<template>
  <section style="margin: 10px">
    <!--工具条-->
    <el-col :span="24" class="toolbar" style="padding-bottom: 0px; margin-left: 10px" >
      <el-form :inline="true" :model="filters">
        <el-form-item>
          <el-input v-model="filters.gonggaoName" size="small" placeholder="名称" ></el-input>
        </el-form-item>
        <el-form-item :label=jcpeizhi.ggtypeBieming prop="ggtypeId">
          <el-select size="small" v-model="filters.ggtypeId" placeholder="请选择" style="width: 50%">
            <el-option label="请选择" value=""> </el-option>
            <el-option v-for="item in ggtypeList" :key="item.ggtypeId" :label="item.ggtypeName" :value="item.ggtypeId">
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
          <el-button type="danger" size="small" @click="deleteGonggaos" :disabled="selectedIds.length === 0">批量删除</el-button>
        </el-form-item>

      </el-form>
    </el-col>
    <!--列表-->
    <el-table ref="exportTableRef" @selection-change="handleSelectionChange" id="myTable" :data="datalist" border stripe v-loading="listLoading" highlight-current-row max-height="800" size="small" style="width: 100%">
      <el-table-column  type="selection"  width="40" />
      <el-table-column sortable type="index" align="center" width="50" label="编号"> </el-table-column>
      <el-table-column sortable prop="gonggaoId" align="center" label="Id" min-width="20%" v-if="false"></el-table-column>
      <el-table-column sortable prop="gonggaoName" align="center" label="名称" min-width="40%"></el-table-column>
      <!--显示图片开始-->
      <el-table-column prop="gonggaoImg" align="center" label="图片" width="130">
        <template #default="scope">
          <div style="width: 110px;height: 110px;display: flex;align-items: center;">
            <el-image fit="fill" :src="scope.row.gonggaoImg" :preview-src-list="[scope.row.gonggaoImg]"></el-image>
          </div>
        </template>
      </el-table-column>
      <!--显示图片结束-->
      <el-table-column prop="ggtypeId" align="center" :label=jcpeizhi.ggtypeBieming min-width="20%" v-if="false"></el-table-column>
      <el-table-column prop="ggtypeName" align="center" :label=jcpeizhi.ggtypeBieming min-width="40%"></el-table-column>
      <el-table-column prop="gonggaoDate" align="center" label="时间" width="130" :formatter="formatDate" v-if="false"></el-table-column>
      <!--操作按钮开始-->
      <el-table-column label="操作" align="center" width="220">
        <template #default="scope">
          <el-button type="warning" size="mini" @click="danduShangchuan(scope.$index, scope.row)" >上传</el-button>
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
    <el-dialog :title=jcpeizhi.gonggaoBieming v-model="formVisible" width="40%" :close-on-click-modal="false">
      <el-form :model="formData" ref="formDataRef" :rules="rules" label-width="100px">
        <el-form-item label="名称" prop="gonggaoName">
          <el-input v-model="formData.gonggaoName" style="width: 50%"></el-input>
        </el-form-item>
        <el-form-item :label=jcpeizhi.ggtypeBieming prop="ggtypeId">
          <el-select v-model="formData.ggtypeId" placeholder="请选择" style="width: 50%">
            <el-option label="请选择" value="0"> </el-option>
            <el-option v-for="item in ggtypeList" :key="item.ggtypeId" :label="item.ggtypeName" :value="item.ggtypeId"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="缩略图" prop="gonggaoImg">
          <el-input v-model="formData.gonggaoImg" readonly="true" style="width: 50%"></el-input>
          <el-button type="primary" size="small" style="margin-left: 15px" @click="showUpload">上传</el-button>
        </el-form-item>
        <el-form-item>
          <WangEditor v-model="formData.gonggaoMark" :isClear="isClear" @change="editorChange"></WangEditor>
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
    <!--单独上传-->
    <el-dialog title="上传信息" v-model="formShangchuan" width="40%" :close-on-click-modal="false">
      <el-form :model="formData" ref="formDataRef" label-width="100px">      
        <el-form-item label="附件" prop="gonggaoImg">
          <el-input v-model="formData.gonggaoImg" readonly="true" style="width: 50%"></el-input>
          <el-button type="primary" size="small" style="margin-left: 15px" @click="showUpload">上传</el-button>
        </el-form-item>
      </el-form>
      <template #footer>
        <span class="dialog-footer">
          <el-button @click="formShangchuan = false">取 消</el-button>
          <el-button type="primary" :loading="btnLoading" @click="shangchuanSave">确 定</el-button>
        </span>
      </template>
    </el-dialog>
    <!--单独上传结束-->
    <!--上传表单-->
    <el-dialog v-model="uploadVisible" title="附件上传" custom-class="el-dialog-widthSmall">
      <div>
        <b>请选择文件进行上传！&emsp;</b>
      </div>
      <el-upload
        style="  margin: auto; margin-top: 10px; border: 1px solid #dcdfe6; border-radius: 4px; "
        drag
        :limit="1"
        :on-preview="handlePreview"
        :on-remove="handleRemove"
        :file-list="fileList"
        :on-exceed="handleExceed"
        :auto-upload="false"
        name="file"
        :on-change="fileListChange"
      >
        <i class="el-icon-upload"></i>
        <div class="el-upload__text">
          将数据文件拖到此处，或<em>点击上传</em>
        </div>
        <div class="el-upload__tip">
          <div
            style="display: inline; color: #d70000; font-size: 14px"
            class="uploadFileWarning"
            id="uploadFileWarning"
          ></div>
        </div>
      </el-upload>
      <span class="dialog-footer">
        <el-button @click="hideUpload">取 消</el-button>
        <el-button type="primary" @click="handleConfirm">提 交</el-button>
      </span>
    </el-dialog>
    <!--上传表单结束-->
  </section>
</template>
<script>
import request, { base } from "../../../utils/http";  //请求地址
import WangEditor from "../../components/WangEditor"; //富文本
export default {
  name: "gonggao",
  components: {
    WangEditor,
  },
  data() {
    return {
      filters: {
        //列表预加载参数
        gonggaoName: "",
        gonggaoMark: "",
        ggtypeId: "",
      },
      page: {
        currentPage: 1, // 当前页
        pageSize: 10, // 每页显示条目个数
        totalCount: 0, // 总条目数
      },
      jcpeizhi:{}, //基础配置数据
      isClear: false,//富文本触发清除文本域内容
      uploadVisible: false,//上传表单不可见
      ggtypeList: "",//下拉列表
      role:'',//权限
      listLoading: false, //列表加载状态
      btnLoading: false, //保存按钮加载状态
      datalist: [], //表格数据
      formVisible: false, //表单是否可见，默认不可见
      formShangchuan: false, //单独上传是否可见，默认不可见
      formData: {}, //表单数据
      selectedIds: [],//多选数据
      rules: {     
        gonggaoName: [{ required: true, message: "不能为空", trigger: "blur" }],
        gonggaoMark: [{ required: true, message: "不能为空", trigger: "blur" }],
      },
    };
  },
  created() {
    let role = sessionStorage.getItem('role');
    this.role = role;
    this.jcpeizhi = sessionStorage.getItem("jcpeizhi");
    this.jcpeizhi = JSON.parse(this.jcpeizhi);
    this.getDatas();
    this.getGgtypeList();
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
      this.formData.gonggaoMark = info;
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
            delIds: row.gonggaoId,
          };
          let url = base + "/deleteGonggao";
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
    //表单总体上传
    showUpload() {
      this.uploadVisible = true;
    },
    //表单总体上传取消
    hideUpload() {
      this.uploadVisible = false;
    },
    //提示
    handleExceed(files, fileList) {
      this.$message({
        duration: 1000,
        message: "只能上传一个文件",
        type: "error",
        offset: 320,
      });
    },
    //上传文件
    fileListChange(file, fileList) {
      let fileNames = [];
      let files = [];
      let that = this;
      fileList.forEach(function (key, val) {
        if (fileNames.indexOf(key["name"]) != -1) {
          that.$message({
            duration: 1000,
            message: "上传的文件重复！",
            type: "error",
            offset: 320,
          });
          return false;
        }
        files.push(key);
        fileNames.push(key["name"]);
      });
      this.files = fileNames;
      this.fileList = files;
    },
    /**
     * 表单内上传确认按钮
     */
    handleConfirm() {
      let filePath = this.fileList;
      if (filePath.length === 0) {
        this.$message({
          duration: 1000,
          message: "请选择文件！",
          type: "error",
          offset: 320,
        });
        return false;
      }
      let formData = new FormData();
      this.fileList.forEach((file) => {
        formData.append("file", file.raw, file.raw.name);
      });
      let url = base + "/upload/fileUpload";
      console.log("url=" + url);
      request.post(url, formData).then((res) => {
        console.log(res);
        let furl = res.filePath;
        console.log(furl + "  ------------");
        this.formData.gonggaoImg = furl;
        this.hideUpload();
        console.log(res);
      });
    },
    //多选项
    handleSelectionChange(selection) {
      this.selectedIds = selection.map((item) => item.gonggaoId);
      this.delIds = JSON.stringify(this.selectedIds).replace(/[\[\]]/g,"");
      console.log(selection);
      console.log(this.delIds);
    },
    //多选批量删除
    deleteGonggaos() {
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
          let url = base + "/deleteGonggao";
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
        gonggaoName: this.filters.gonggaoName,
        ggtypeId: this.filters.ggtypeId,
      };
      if(this.role=='user'){
        var user = sessionStorage.getItem("currentUser");
        user = JSON.parse(user);
        para = {
          gonggaoName: this.filters.gonggaoName,
          ggtypeId: this.filters.ggtypeId,
          userId:user.userId
        };     
      }
      if(this.role=='yonghu'){
        var user = sessionStorage.getItem("currentUser");
        user = JSON.parse(user);
        para = {
          gonggaoName: this.filters.gonggaoName,
          ggtypeId: this.filters.ggtypeId,
          userId:user.yonghuId
        };     
      }
      //console.log(para)
      this.listLoading = true;
      let url = base + "/getGonggaos?page=" + this.page.currentPage;
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
    //获取ggtype
    getGgtypeList() {
      let para = {};
      this.listLoading = true;
      let url = base + "/ggtypeComboList";
      request.post(url, para).then((res) => {
        if (res.code == 200) {
          this.ggtypeList = res.resdata;
        }
      });
    },
    //打开上传界面
    danduShangchuan(index, row) {
      this.formShangchuan = true;
      this.formData = JSON.parse(JSON.stringify(row));
    },
    //确认上传
    shangchuanSave() {
      this.$refs["formDataRef"].validate((valid) => {
        if (valid) {
          let url = base + "/addGonggao?GonggaoId=" + this.formData.GonggaoId;
          this.btnLoading = true;
          request.post(url, this.formData).then((res) => {
            if (res.code == 200) {
              this.btnLoading = false;
              this.formShangchuan = false;
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
    },
    //添加修改
    save() {
      if (this.formData.gonggaoId != null) {
        //如果有ID就修改数据
        this.$refs["formDataRef"].validate((valid) => {
          if (valid) {
            let url = base + "/addGonggao?gonggaoId=" + this.formData.gonggaoId;
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
            let url = base + "/addGonggao";
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
    //根据数据状态显示文本
    zhuangtaiType(row){
        if(row.gonggaoType==0){
             return "启用";
        }
        if(row.gonggaoType==1){
             return "禁用";
        }
    },
    //单项更改状态
    shezhiZhuangtai(index, row,zhuangtai) {
      this.listLoading = true;
      let para = {
        gonggaoType: zhuangtai,
        gonggaoId: row.gonggaoId,
      };

      let url = base + "/addGonggao";
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
