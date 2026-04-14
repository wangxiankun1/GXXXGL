<template>
  <section style="margin: 10px">
    <!--工具条-->
    <el-col :span="24" class="toolbar" style="padding-bottom: 0px; margin-left: 10px" >
      <el-form :inline="true" :model="filters">
        <el-form-item>
          <el-input v-model="filters.uyijianName" size="small" placeholder="名称" ></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="info" size="small" @click="query">查询</el-button>
        </el-form-item>
        <el-form-item v-if="role == 'user'">
          <el-button type="primary" size="small" @click="handleAdd">新增</el-button>
        </el-form-item>
        <el-form-item>
          <el-button type="danger" size="small" @click="deleteUyijians" :disabled="selectedIds.length === 0">批量删除</el-button>
        </el-form-item>

      </el-form>
    </el-col>
    <!--列表-->
    <el-table ref="exportTableRef" @selection-change="handleSelectionChange" id="myTable" :data="datalist" border stripe v-loading="listLoading" highlight-current-row max-height="800" size="small" style="width: 100%">
      <el-table-column  type="selection"  width="40" />
      <el-table-column sortable type="index" align="center" width="50" label="编号"> </el-table-column>
      <el-table-column sortable prop="uyijianId" align="center" label="Id" min-width="20%" v-if="false"></el-table-column>
      <el-table-column sortable prop="uyijianName" align="center" label="名称" min-width="40%"></el-table-column>
      <el-table-column prop="uyijianMark" align="center" label="详情" :show-overflow-tooltip="true" min-width="80%"></el-table-column>
      <el-table-column prop="uyijianMark1" align="center" label="回复" :show-overflow-tooltip="true" min-width="80%"></el-table-column>
      <el-table-column prop="userId" align="center" :label=jcpeizhi.userBieming min-width="20%" v-if="false"></el-table-column>
      <el-table-column prop="userName" align="center" :label=jcpeizhi.userBieming min-width="20%" v-if="role == 'admin'"></el-table-column>
      <el-table-column sortable prop="uyijianDate" align="center" label="时间" width="130" :formatter="formatDate" v-if="false"></el-table-column>
      <!--操作按钮开始-->
      <el-table-column label="操作" align="center" width="150">
        <template #default="scope">
          <el-button type="primary" size="mini" @click="handleEdit(scope.$index, scope.row)" v-if="role == 'user'">编辑</el-button>
          <el-button type="warning" size="mini" @click="danduChuli(scope.$index, scope.row)" v-if="role == 'admin'">回复</el-button>
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
    <el-dialog :title=jcpeizhi.uyijianBieming v-model="formVisible" width="40%" :close-on-click-modal="false">
      <el-form :model="formData" ref="formDataRef" :rules="rules" label-width="100px">
        <el-form-item label="名称" prop="uyijianName">
          <el-input v-model="formData.uyijianName" style="width: 50%"></el-input>
        </el-form-item>
        <el-form-item label="详情" prop="uyijianMark">
          <el-input type="textarea" :rows="4" v-model="formData.uyijianMark" style="width: 80%"></el-input>
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
        <el-form-item label="附件" prop="uyijianImg">
          <el-input v-model="formData.uyijianImg" readonly="true" style="width: 50%"></el-input>
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
    <!--处理信息-->
    <el-dialog title="处理信息" v-model="formChuli" width="40%" :close-on-click-modal="false">
      <el-form :model="formData" ref="formDataRef" label-width="100px">      
        <el-form-item label="回复" prop="uyijianMark1">
          <el-input type="textarea" :rows="4" v-model="formData.uyijianMark1" style="width: 80%"></el-input>
        </el-form-item>
      </el-form>
      <template #footer>
        <span class="dialog-footer">
          <el-button @click="formChuli = false">取 消</el-button>
          <el-button type="primary" :loading="btnLoading" @click="chuliSave">确 定</el-button>
        </span>
      </template>
    </el-dialog>
    <!--处理信息结束-->
    <!--审核信息-->
    <el-dialog title="审核信息" v-model="formShenhe" width="40%" :close-on-click-modal="false">
      <el-form :model="formData" ref="formDataRef" label-width="100px">      
        <el-form-item label="状态" prop="uyijianType">
          <el-select v-model="formData.uyijianType" style="width: 50%" placeholder="请选择">
            <el-option label="通过" value="1"></el-option>
            <el-option label="拒绝" value="2"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="审核" prop="uyijianMark3">
          <el-input type="textarea" :rows="4" v-model="formData.uyijianMark3" style="width: 80%"></el-input>
        </el-form-item>
      </el-form>
      <template #footer>
        <span class="dialog-footer">
          <el-button @click="formShenhe = false">取 消</el-button>
          <el-button type="primary" :loading="btnLoading" @click="shenheSave">确 定</el-button>
        </span>
      </template>
    </el-dialog>
    <!--审核信息结束-->
    <!--导入上传-->
    <el-dialog title="导入信息" v-model="formDaoru" width="40%" :close-on-click-modal="false">
      <el-form :model="formData" ref="formDataRef" label-width="100px">      
        <el-form-item label="excel表" prop="uyijianImg">
          <el-input v-model="formData.uyijianImg" readonly="true" style="width: 50%"></el-input>
          <el-button type="primary" size="small" style="margin-left: 15px" @click="showUpload">上传</el-button>
        </el-form-item>
      </el-form>
      <template #footer>
        <span class="dialog-footer">
          <el-button @click="formDaoru = false">取 消</el-button>
          <el-button type="primary" :loading="btnLoading" @click="daoruSave">确 定</el-button>
        </span>
      </template>
    </el-dialog>
    <!--导入上传结束-->
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
import FileSaver from "file-saver"; //导出
import XLSX from "xlsx";  //导出excel
export default {
  name: "uyijian",
  components: {
    WangEditor,
  },
  data() {
    /*****表单验证开始 */
		var isDoubleNum = (rule, value, callback) => {
      const decimalReg = /^(([0-9]|([1-9][0-9]{0,9}))((\.[0-9]{1,2})?))$/;
      if (!value) {
        return callback(new Error("不能为空"));
      }
      setTimeout(() => {
        if (decimalReg.test(value)) {
          callback();
        } else {
          callback(new Error("请输入正确的Double格式"));
        }
      }, 100);
    };
		var isIntNum = (rule, value, callback) => {
      const decimalReg = /(^-?\d+$)|(^$)/;
      if (!value) {
        return callback(new Error("不能为空"));
      }
      setTimeout(() => {
        if (decimalReg.test(value)) {
          callback();
        } else {
          callback(new Error("请输入正确的Int格式"));
        }
      }, 100);
    };
		var isPhone = (rule, value, callback) => {
      const decimalReg = /^1[0-9]{10}$/;
      if (!value) {
        return callback(new Error("不能为空"));
      }
      setTimeout(() => {
        if (decimalReg.test(value)) {
          callback();
        } else {
          callback(new Error("请输入正确手机号码"));
        }
      }, 100);
    };
		var isIdCard = (rule, value, callback) => {
      const decimalReg = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;
      if (!value) {
        return callback(new Error("不能为空"));
      }
      setTimeout(() => {
        if (decimalReg.test(value)) {
          callback();
        } else {
          callback(new Error("请输入正确身份证号"));
        }
      }, 100);
    };
		var isEmail = (rule, value, callback) => {
      const decimalReg = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((.[a-zA-Z0-9_-]{2,3}){1,2})$/;
      if (!value) {
        return callback(new Error("不能为空"));
      }
      setTimeout(() => {
        if (decimalReg.test(value)) {
          callback();
        } else {
          callback(new Error("请输入正确邮箱地址"));
        }
      }, 100);
    };
		var isAge = (rule, value, callback) => {
      const decimalReg = /^([1-9][0-9]{0,1}|100)$/;
      if (!value) {
        return callback(new Error("不能为空"));
      }
      setTimeout(() => {
        if (decimalReg.test(value)) {
          callback();
        } else {
          callback(new Error("请输入正确年龄"));
        }
      }, 100);
    };
    /*****表单验证结束 */
    return {
      filters: {
        //列表预加载参数
        uyijianName: "",
        uyijianMark: "",
        uxtypeId: "",
        userId: "",
        buzhiId: "",
        bumenId: "",
        buyuanId: "",
        sdate: "",
        edate: "",
      },
      page: {
        currentPage: 1, // 当前页
        pageSize: 10, // 每页显示条目个数
        totalCount: 0, // 总条目数
      },
      jcpeizhi:{}, //基础配置数据
      isClear: false,//富文本触发清除文本域内容
      uploadVisible: false,//上传表单不可见
      uxtypeList: "",//下拉列表
      buzhiList: "",//下拉列表
      bumenList: "",//下拉列表
      buyuanList: "",//下拉列表
      userList: "",//下拉列表
      role:'',//权限
      listLoading: false, //列表加载状态
      btnLoading: false, //保存按钮加载状态
      datalist: [], //表格数据
      formVisible: false, //表单是否可见，默认不可见
      formShangchuan: false, //单独上传是否可见，默认不可见
      formChuli: false, //处理信息是否可见，默认不可见
      formShenhe: false, //审核信息是否可见，默认不可见
      formDaoru: false, //导入上传是否可见，默认不可见
      formData: {}, //表单数据
      selectedIds: [],//多选数据
      rules: {     
        uyijianName: [{ required: true, message: "不能为空", trigger: "blur" }],
        uyijianMark: [{ required: true, message: "不能为空", trigger: "blur" }],
        uyijianMark1: [{ required: true, message: "不能为空", trigger: "blur" }],
        uyijianMark2: [{ required: true, message: "不能为空", trigger: "blur" }],
        uyijianDouble: [{ required: true, validator: isDoubleNum, trigger: "blur" }],
        uyijianDouble1: [{ required: true, validator: isDoubleNum, trigger: "blur" }],
        uyijianDouble2: [{ required: true, validator: isDoubleNum, trigger: "blur" }],
        uyijianZong: [{ required: true, validator: isIntNum, trigger: "blur" }],
        uyijianZong1: [{ required: true, validator: isIntNum, trigger: "blur" }],
        uyijianZong2: [{ required: true, validator: isIntNum, trigger: "blur" }],
      },
    };
  },
  created() {
    let role = sessionStorage.getItem('role');
    this.role = role;
    this.jcpeizhi = sessionStorage.getItem("jcpeizhi");
    this.jcpeizhi = JSON.parse(this.jcpeizhi);
    this.getDatas();
    this.getUxtypeList();
    this.getBuzhiList();
    this.getBumenList();
    this.getBuyuanList();
    this.getUserList();
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
      this.formData.uyijianMark = info;
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
            delIds: row.uyijianId,
          };
          let url = base + "/deleteUyijian";
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
    //删除上传文件
    handleRemove(file, fileList) {
      this.fileList = fileList;
    },
    //打印文件名
    handlePreview(file) {
      console.log(file);
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
        this.formData.uyijianImg = furl;
        this.hideUpload();
        console.log(res);
      });
    },
    //打开导入界面
    daoruShangchuan() {
      this.formDaoru = true;
      this.formData = {};
    },
    //确认导入
    daoruSave() {
      this.$refs["formDataRef"].validate((valid) => {
        if (valid) {
          let url = base + "/daoruUyijian";
          this.btnLoading = true;
          request.post(url, this.formData).then((res) => {
            if (res.code == 200) {
              this.btnLoading = false;
              this.formDaoru = false;
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
      this.selectedIds = selection.map((item) => item.uyijianId);
      this.delIds = JSON.stringify(this.selectedIds).replace(/[\[\]]/g,"");
      console.log(selection);
      console.log(this.delIds);
    },
    //多选批量删除
    deleteUyijians() {
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
          let url = base + "/deleteUyijian";
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
        uyijianName: this.filters.uyijianName,
        uxtypeId: this.filters.uxtypeId,
        buzhiId: this.filters.buzhiId,
        buyuanId: this.filters.buyuanId,
        bumenId: this.filters.bumenId,
        sdate: this.filters.sdate,
        edate: this.filters.edate,
      };
      if(this.role=='user'){
        var user = sessionStorage.getItem("currentUser");
        user = JSON.parse(user);
        para = {
          uyijianName: this.filters.uyijianName,
          uxtypeId: this.filters.uxtypeId,
          buzhiId: this.filters.buzhiId,
          buyuanId: this.filters.buyuanId,
          bumenId: this.filters.bumenId,
          sdate: this.filters.sdate,
          edate: this.filters.edate,
          userId:user.userId
        };     
      }
      //console.log(para)
      this.listLoading = true;
      let url = base + "/getUyijians?page=" + this.page.currentPage;
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
    //获取uxtype
    getUxtypeList() {
      let para = {};
      this.listLoading = true;
      let url = base + "/uxtypeComboList";
      request.post(url, para).then((res) => {
        if (res.code == 200) {
          this.uxtypeList = res.resdata;
        }
      });
    },
    //获取buzhi
    getBuzhiList() {
      let para = {};
      this.listLoading = true;
      let url = base + "/buzhiComboList";
      request.post(url, para).then((res) => {
        if (res.code == 200) {
          this.buzhiList = res.resdata;
        }
      });
    },
    //获取buyuan
    getBuyuanList() {
      let para = {};
      this.listLoading = true;
      let url = base + "/buyuanComboList";
      request.post(url, para).then((res) => {
        if (res.code == 200) {
          this.buyuanList = res.resdata;
        }
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
    //获取user
    getUserList() {
      let para = {};
      this.listLoading = true;
      let url = base + "/userComboList";
      request.post(url, para).then((res) => {
        if (res.code == 200) {
          this.userList = res.resdata;
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
          let url = base + "/addUyijian?uyijianId=" + this.formData.uyijianId;
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
      if (this.formData.uyijianId != null) {
        //如果有ID就修改数据
        this.$refs["formDataRef"].validate((valid) => {
          if (valid) {
            let url = base + "/addUyijian?uyijianId=" + this.formData.uyijianId;
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
            let url = base + "/addUyijian";
            if(this.role=='user'){
              var user = sessionStorage.getItem("currentUser");
              user = JSON.parse(user);
              this.formData.userId = user.userId;
            }
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
    //打开处理界面
    danduChuli(index, row) {
      this.formData = JSON.parse(JSON.stringify(row));
      this.formChuli = true;
    },
    //确认处理
    chuliSave() {
      this.$refs["formDataRef"].validate((valid) => {
        if (valid) {
          let url = base + "/addUyijian?uyijianId=" + this.formData.uyijianId;
          this.btnLoading = true;
          request.post(url, this.formData).then((res) => {
            if (res.code == 200) {
              this.btnLoading = false;
              this.formChuli = false;
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
    //打开审核界面
    danduShenhe(index, row) {
      this.formData = JSON.parse(JSON.stringify(row));
      if(this.formData.uyijianType==0){
        this.formShenhe = true;
      }else{
        this.$message({
          message: "不允许修改",
          offset: 320,
        });
      }
    },
    //确认审核
    shenheSave() {
      this.$refs["formDataRef"].validate((valid) => {
        if (valid) {
          let url = base + "/addUyijian?uyijianId=" + this.formData.uyijianId;
          this.btnLoading = true;
          request.post(url, this.formData).then((res) => {
            if (res.code == 200) {
              this.btnLoading = false;
              this.formShenhe = false;
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
    //根据数据状态显示文本
    zhuangtaiType(row){
        if(row.uyijianType==0){
             return "启用";
        }
        if(row.uyijianType==1){
             return "禁用";
        }
    },
    //单项更改状态
    shezhiZhuangtai(index, row,zhuangtai) {
      this.listLoading = true;
      let para = {
        uyijianType: zhuangtai,
        uyijianId: row.uyijianId,
      };

      let url = base + "/addUyijian";
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
