<template>
  <section style="margin: 10px">
    <!--工具条-->
    <el-col :span="24" class="toolbar" style="padding-bottom: 0px; margin-left: 10px">
      <el-form :inline="true" :model="filters" v-if="role == 'admin'">
        <el-form-item>
          <el-input v-model="filters.adminName" size="small" placeholder="用户名" ></el-input>
        </el-form-item>
        <el-form-item>
          <el-input v-model="filters.adminXingming" size="small" placeholder="姓名" ></el-input>
        </el-form-item>
        <el-form-item>
          <el-input v-model="filters.adminPhone" size="small" placeholder="电话" ></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="info" size="small" @click="query">查询</el-button>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" size="small" @click="handleAdd">新增</el-button>
        </el-form-item>
        <el-form-item>
          <el-button type="danger" size="small" @click="deleteAdmins" :disabled="selectedIds.length === 0">批量删除</el-button>
        </el-form-item>

      </el-form>
    </el-col>
    <!--列表-->
    <el-table ref="exportTableRef" @selection-change="handleSelectionChange" id="myTable" :data="datalist" border stripe v-loading="listLoading" highlight-current-row max-height="800" size="small" style="width: 100%">
      <el-table-column  type="selection"  width="40" />
      <el-table-column sortable type="index" align="center" width="50" label="编号"> </el-table-column>
      <el-table-column sortable prop="adminId" align="center" label="Id" min-width="20%" v-if="false"></el-table-column>
      <el-table-column sortable prop="adminName" align="center" label="用户名" min-width="20%"></el-table-column>
      <el-table-column prop="adminPassword" align="center" label="密码" min-width="20%" v-if="false"></el-table-column>
      <el-table-column sortable prop="adminXingming" align="center" label="姓名" min-width="20%"></el-table-column>
      <!--显示图片开始-->
      <el-table-column prop="adminImg" align="center" label="头像" width="130">
        <template #default="scope">
          <div style="width: 110px;height: 110px;display: flex;align-items: center;">
            <el-image fit="fill" :src="scope.row.adminImg" :preview-src-list="[scope.row.adminImg]"></el-image>
          </div>
        </template>
      </el-table-column>
      <!--显示图片结束-->
      <el-table-column prop="adminSex" align="center" label="性别" width="50" :formatter="sexType"></el-table-column>
      <el-table-column prop="adminAge" align="center" label="年龄" width="50"></el-table-column>
      <el-table-column prop="adminPhone" align="center" label="电话" width="100"></el-table-column>
      <el-table-column prop="adminMark" align="center" label="备注" :show-overflow-tooltip="true" min-width="60%"></el-table-column>
      <el-table-column prop="adminType" align="center" label="状态" min-width="20%" :formatter="zhuangtaiType" v-if="role == 'admin'"></el-table-column>
      <el-table-column prop="adminDate" align="center" label="时间" width="120" :formatter="formatDate"></el-table-column>
      <!--操作按钮开始-->
      <el-table-column label="操作" align="center" width="250">
        <template #default="scope">
          <el-button v-if="scope.row.adminType1 == 1 && role == 'admin'" type="success" size="mini" @click="shezhiZhuangtai(scope.$index, scope.row,0)" >启用</el-button>
          <el-button v-if="scope.row.adminType1 == 0 && role == 'admin'" type="success" size="mini" @click="shezhiZhuangtai(scope.$index, scope.row,1)" >禁用</el-button>
          <el-button type="success" size="mini" @click="danduChuli(scope.$index, scope.row)" >编辑</el-button>
          <el-button type="warning" size="mini" @click="danduShangchuan(scope.$index, scope.row)" >上传头像</el-button>
        </template>
      </el-table-column>
      <!--操作按钮结束-->
    </el-table>
    <!--列表结束-->
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
    <el-dialog :title=jcpeizhi.adminBieming v-model="formVisible" width="40%" :close-on-click-modal="false">
      <el-form :model="formData" ref="formDataRef" :rules="rules" label-width="100px">
        <el-form-item label="用户名" prop="adminName">
          <el-input v-model="formData.adminName" style="width: 50%"></el-input>
        </el-form-item>
        <el-form-item label="密码" prop="adminPassword">
          <el-input type="password" v-model="formData.adminPassword" style="width: 50%"></el-input>
        </el-form-item>
        <el-form-item label="姓名" prop="adminXingming">
          <el-input v-model="formData.adminXingming" style="width: 50%"></el-input>
        </el-form-item>
        <el-form-item label="性别" prop="adminSex">
          <el-select v-model="formData.adminSex" style="width: 50%" placeholder="请选择">
            <el-option label="男" value="0"></el-option>
            <el-option label="女" value="1"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="年龄" prop="adminAge">
          <el-input v-model="formData.adminAge" style="width: 50%"></el-input>
        </el-form-item>
        <el-form-item label="电话" prop="adminPhone">
          <el-input v-model="formData.adminPhone" style="width: 50%"></el-input>
        </el-form-item>
        <el-form-item label="头像" prop="adminImg">
          <el-input v-model="formData.adminImg" readonly="true" style="width: 50%"></el-input>
          <el-button type="primary" size="small" style="margin-left: 15px" @click="showUpload">上传</el-button>
        </el-form-item>
        <el-form-item label="备注" prop="adminMark">
          <el-input type="textarea" :rows="4" v-model="formData.adminMark" style="width: 80%"></el-input>
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
        <el-form-item label="附件" prop="adminImg">
          <el-input v-model="formData.adminImg" readonly="true" style="width: 50%"></el-input>
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
      <el-form :model="formData" ref="formDataRef" :rules="rules" label-width="100px">      
        <el-form-item label="姓名" prop="adminXingming">
          <el-input v-model="formData.adminXingming" style="width: 50%"></el-input>
        </el-form-item>
        <el-form-item label="性别" prop="adminSex">
          <el-select v-model="formData.adminSex" style="width: 50%" placeholder="请选择">
            <el-option label="男" value="0"></el-option>
            <el-option label="女" value="1"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="年龄" prop="adminAge">
          <el-input v-model="formData.adminAge" style="width: 50%"></el-input>
        </el-form-item>
        <el-form-item label="电话" prop="adminPhone">
          <el-input v-model="formData.adminPhone" style="width: 50%"></el-input>
        </el-form-item>
        <el-form-item label="头像" prop="adminImg">
          <el-input v-model="formData.adminImg" readonly="true" style="width: 50%"></el-input>
          <el-button type="primary" size="small" style="margin-left: 15px" @click="showUpload">上传</el-button>
        </el-form-item>
        <el-form-item label="备注" prop="adminMark">
          <el-input type="textarea" :rows="4" v-model="formData.adminMark" style="width: 80%"></el-input>
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
export default {
  name: "admin",
  data() {
    /*****表单验证开始 */
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
        adminName: "",
        adminXingming: "",
        adminPhone: "",
      },
      page: {
        currentPage: 1, // 当前页
        pageSize: 10, // 每页显示条目个数
        totalCount: 0, // 总条目数
      },
      jcpeizhi:{}, //基础配置数据
      isClear: false,//富文本触发清除文本域内容
      uploadVisible: false,//上传表单不可见
      role:'',//权限
      listLoading: false, //列表加载状态
      btnLoading: false, //保存按钮加载状态
      datalist: [], //表格数据
      formVisible: false, //表单是否可见，默认不可见
      formShangchuan: false, //单独上传是否可见，默认不可见
      formChuli: false, //处理信息是否可见，默认不可见
      formData: {}, //表单数据
      selectedIds: [],//多选数据
      rules: {     
        adminName: [{ required: true, message: "不能为空", trigger: "blur" }],
        adminPassword: [{ required: true, message: "不能为空", trigger: "blur" }],
        adminXingming: [{ required: true, message: "不能为空", trigger: "blur" }],
        adminMark: [{ required: true, message: "不能为空", trigger: "blur" }],
        adminAge: [{ required: true, validator: isAge, trigger: "blur" }],
        adminPhone: [{ required: true, validator: isPhone, trigger: "blur" }],
      },
    };
  },
  created() {
    let role = sessionStorage.getItem('role');
    this.role = role;
    this.jcpeizhi = sessionStorage.getItem("jcpeizhi");
    this.jcpeizhi = JSON.parse(this.jcpeizhi);
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
            delIds: row.adminId,
          };
          let url = base + "/deleteAdmin";
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
        //console.log(furl + "  ------------");
        this.formData.adminImg = furl;
        this.hideUpload();
        console.log(res);
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
    //多选项
    handleSelectionChange(selection) {
      this.selectedIds = selection.map((item) => item.adminId);
      this.delIds = JSON.stringify(this.selectedIds).replace(/[\[\]]/g,"");
      console.log(selection);
      console.log(this.delIds);
    },
    //多选批量删除
    deleteAdmins() {
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
          let url = base + "/deleteAdmin";
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
        adminName: this.filters.adminName,
        adminXingming: this.filters.adminXingming,
        adminPhone: this.filters.adminPhone,
      };
      if(this.role=='admin1'){
        var user = sessionStorage.getItem("currentUser");
        user = JSON.parse(user);
        para = {
          adminName: this.filters.adminName,
          adminXingming: this.filters.adminXingming,
          adminPhone: this.filters.adminPhone,
          adminId:user.adminId
        };     
      }
      //console.log(para)
      this.listLoading = true;
      let url = base + "/getAdmins?page=" + this.page.currentPage;
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
    //打开上传界面
    danduShangchuan(index, row) {
      this.formShangchuan = true;
      this.formData = JSON.parse(JSON.stringify(row));
    },
    //确认上传
    shangchuanSave() {
      this.$refs["formDataRef"].validate((valid) => {
        if (valid) {
          let url = base + "/addAdmin?adminId=" + this.formData.adminId;
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
      if (this.formData.adminId != null) {
        //如果有ID就修改数据
        this.$refs["formDataRef"].validate((valid) => {
          if (valid) {
            let url = base + "/addAdmin?adminId=" + this.formData.adminId;
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
            let url = base + "/addAdmin";
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
      if(this.formData.adminType==1){
        this.formChuli = true;
      }else{
        this.$message({
          message: "不允许修改",
          offset: 320,
        });
      }
    },
    //确认处理
    chuliSave() {
      this.$refs["formDataRef"].validate((valid) => {
        if (valid) {
          let url = base + "/addAdmin?adminId=" + this.formData.adminId;
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
    //性别状态
    sexType(row){
        if(row.adminSex==0){
             return "男";
        }
        if(row.adminSex==1){
             return "女";
        }
    },
    //根据数据状态显示文本
    zhuangtaiType(row){
        if(row.adminType1==0){
             return "启用";
        }
        if(row.adminType1==1){
             return "禁用";
        }
    },
    //单项更改状态
    shezhiZhuangtai(index, row,zhuangtai) {
      this.listLoading = true;
      let para = {
        adminType1: zhuangtai,
        adminId: row.adminId,
      };

      let url = base + "/addAdmin";
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
