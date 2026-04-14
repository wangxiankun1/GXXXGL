<template>
  <section style="margin: 10px">
    <!--工具条-->
    <el-col :span="24" class="toolbar" style="padding-bottom: 0px; margin-left: 10px" >
      <el-form :inline="true" :model="filters" v-if="role == 'admin'">
        <el-form-item>
          <el-input v-model="filters.userName" size="small" placeholder="用户名" ></el-input>
        </el-form-item>
        <el-form-item>
          <el-input v-model="filters.userXingming" size="small" placeholder="姓名" ></el-input>
        </el-form-item>
        <el-form-item>
          <el-input v-model="filters.userPhone" size="small" placeholder="电话" ></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="info" size="small" @click="query">查询</el-button>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" size="small" @click="daoruShangchuan">导入</el-button>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" size="small" @click="exportTable">导出</el-button>
        </el-form-item>
        <el-form-item>
          <el-button type="danger" size="small" @click="deleteUsers" :disabled="selectedIds.length === 0">批量删除</el-button>
        </el-form-item>

      </el-form>
    </el-col>
    <!--列表-->
    <el-table ref="exportTableRef" @selection-change="handleSelectionChange" id="myTable" :data="datalist" border stripe v-loading="listLoading" highlight-current-row max-height="800" size="small" style="width: 100%">
      <el-table-column  type="selection"  width="40" />
      <el-table-column sortable type="index" align="center" width="50" label="编号"> </el-table-column>
      <el-table-column sortable prop="userId" align="center" label="Id" min-width="20%" v-if="false"></el-table-column>
      <el-table-column prop="userName" align="center" label="用户名" min-width="20%"></el-table-column>
      <el-table-column prop="userPassword" align="center" label="密码" min-width="20%" v-if="false"></el-table-column>
      <el-table-column prop="userXingming" align="center" label="姓名" min-width="20%"></el-table-column>
      <el-table-column prop="userSex" align="center" label="性别" width="50" :formatter="sexType"></el-table-column>
      <el-table-column prop="userAge" align="center" label="年龄" width="50"></el-table-column>
      <el-table-column prop="userPhone" align="center" label="电话" width="100"></el-table-column>
      <el-table-column prop="userMark" align="center" label="学校" min-width="60%"></el-table-column>
      <el-table-column prop="userMark1" align="center" label="学历" min-width="30%"></el-table-column>
      <el-table-column prop="userMark2" align="center" label="专业" min-width="30%"></el-table-column>
      <!--附件下载开始-->
      <el-table-column prop="userImgName" align="center" label="简历" width="70">
        <template #default="scope">
          <a target="_blank" style="color:#007bff;" v-if="scope.row.userImgName!=null"
           v-bind:href="scope.row.userImgName">
            下载
          </a>
          <span v-else>未上传</span>
        </template>
      </el-table-column>
      <el-table-column sortable prop="userDate" align="center" label="时间" width="150" :formatter="formatDate" v-if="false"></el-table-column>
      <!--操作按钮开始-->
      <el-table-column label="操作" align="center" width="150">
        <template #default="scope">
          <el-button type="warning" size="mini" @click="danduShangchuan(scope.$index, scope.row)" >简历</el-button>
          <el-button type="success" size="mini" @click="danduChuli(scope.$index, scope.row)" >编辑</el-button>
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
    <el-dialog :title=jcpeizhi.userBieming v-model="formVisible" width="40%" :close-on-click-modal="false">
      <el-form :model="formData" ref="formDataRef" :rules="rules" label-width="100px">
        <el-form-item label="用户名" prop="userName">
          <el-input v-model="formData.userName" style="width: 50%"></el-input>
        </el-form-item>
        <el-form-item label="密码" prop="userPassword">
          <el-input type="password" v-model="formData.userPassword" style="width: 50%"></el-input>
        </el-form-item>
        <el-form-item label="姓名" prop="userXingming">
          <el-input v-model="formData.userXingming" style="width: 50%"></el-input>
        </el-form-item>
        <el-form-item label="性别" prop="userSex">
          <el-select v-model="formData.userSex" style="width: 50%" placeholder="请选择">
            <el-option label="男" value="0"></el-option>
            <el-option label="女" value="1"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="年龄" prop="userAge">
          <el-input v-model="formData.userAge" style="width: 50%"></el-input>
        </el-form-item>
        <el-form-item label="电话" prop="userPhone">
          <el-input v-model="formData.userPhone" style="width: 50%"></el-input>
        </el-form-item>
        <el-form-item label="头像" prop="userImg">
          <el-input v-model="formData.userImg" readonly="true" style="width: 50%"></el-input>
          <el-button type="primary" size="small" style="margin-left: 15px" @click="showUpload">上传</el-button>
        </el-form-item>
        <el-form-item :label=jcpeizhi.buzhiBieming prop="buzhiId">
          <el-select v-model="formData.buzhiId" placeholder="请选择" style="width: 50%">
            <el-option label="请选择" value="0"> </el-option>
            <el-option v-for="item in buzhiList" :key="item.buzhiId" :label="item.buzhiName" :value="item.buzhiId"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item :label=jcpeizhi.bumenBieming prop="bumenId">
          <el-select v-model="formData.bumenId" placeholder="请选择" style="width: 50%">
            <el-option label="请选择" value="0"> </el-option>
            <el-option v-for="item in bumenList" :key="item.bumenId" :label="item.bumenName" :value="item.bumenId"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item :label=jcpeizhi.buyuanBieming prop="buyuanId">
          <el-select v-model="formData.buyuanId" placeholder="请选择" style="width: 50%">
            <el-option label="请选择" value="0"> </el-option>
            <el-option v-for="item in buyuanList" :key="item.buyuanId" :label="item.buyuanName" :value="item.buyuanId"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item :label=jcpeizhi.roleBieming prop="roleId">
          <el-select v-model="formData.roleId" placeholder="请选择" style="width: 50%">
            <el-option label="请选择" value="0"> </el-option>
            <el-option v-for="item in roleList" :key="item.roleId" :label="item.roleName" :value="item.roleId"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="备注" prop="userMark">
          <el-input type="textarea" :rows="4" v-model="formData.userMark" style="width: 80%"></el-input>
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
        <el-form-item label="附件" prop="userImg">
          <el-input v-model="formData.userImg" readonly="true" style="width: 50%"></el-input>
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
    <!--修改信息-->
    <el-dialog title="修改信息" v-model="formChuli" width="40%" :close-on-click-modal="false">
      <el-form :model="formData" ref="formDataRef" :rules="rules" label-width="100px">      
        <el-form-item label="姓名" prop="userXingming">
          <el-input v-model="formData.userXingming" style="width: 50%"></el-input>
        </el-form-item>
        <el-form-item label="性别" prop="userSex">
          <el-select v-model="formData.userSex" style="width: 50%" placeholder="请选择">
            <el-option label="男" value="0"></el-option>
            <el-option label="女" value="1"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="年龄" prop="userAge">
          <el-input v-model="formData.userAge" style="width: 50%"></el-input>
        </el-form-item>
        <el-form-item label="电话" prop="userPhone">
          <el-input v-model="formData.userPhone" style="width: 50%"></el-input>
        </el-form-item>
        <el-form-item label="学校" prop="userMark">
          <el-input v-model="formData.userMark" style="width: 50%"></el-input>
        </el-form-item>
        <el-form-item label="学历" prop="userMark1">
          <el-input v-model="formData.userMark1" style="width: 50%"></el-input>
        </el-form-item>
        <el-form-item label="专业" prop="userMark2">
          <el-input v-model="formData.userMark2" style="width: 50%"></el-input>
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
        <el-form-item label="状态" prop="userType">
          <el-select v-model="formData.userType" style="width: 50%" placeholder="请选择">
            <el-option label="通过" value="1"></el-option>
            <el-option label="拒绝" value="2"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="审核" prop="userMark3">
          <el-input type="textarea" :rows="4" v-model="formData.userMark3" style="width: 80%"></el-input>
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
        <el-form-item label="excel表" prop="userImg">
          <el-input v-model="formData.userImg" readonly="true" style="width: 50%"></el-input>
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
  name: "user",
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
        userName: "",
        userXingming: "",
        userPhone: "",
        buzhiId: "",
        roleId: "",
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
      buzhiList: "",//下拉列表
      bumenList: "",//下拉列表
      buyuanList: "",//下拉列表
      roleList: "",//下拉列表
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
        userName: [{ required: true, message: "不能为空", trigger: "blur" }],
        userXingming: [{ required: true, message: "不能为空", trigger: "blur" }],
        userPassword: [{ required: true, message: "不能为空", trigger: "blur" }],
        userMark: [{ required: true, message: "不能为空", trigger: "blur" }],
        userMark1: [{ required: true, message: "不能为空", trigger: "blur" }],
        userMark2: [{ required: true, message: "不能为空", trigger: "blur" }],
        userMark3: [{ required: true, message: "不能为空", trigger: "blur" }],
        userDouble: [{ required: true, validator: isDoubleNum, trigger: "blur" }],
        userDouble1: [{ required: true, validator: isDoubleNum, trigger: "blur" }],
        userDouble2: [{ required: true, validator: isDoubleNum, trigger: "blur" }],
        userZong: [{ required: true, validator: isIntNum, trigger: "blur" }],
        userZong1: [{ required: true, validator: isIntNum, trigger: "blur" }],
        userZong2: [{ required: true, validator: isIntNum, trigger: "blur" }],
        userAge: [{ required: true, validator: isAge, trigger: "blur" }],
        userPhone: [{ required: true, validator: isPhone, trigger: "blur" }],
      },
    };
  },
  created() {
    let role = sessionStorage.getItem('role');
    this.role = role;
    this.jcpeizhi = sessionStorage.getItem("jcpeizhi");
    this.jcpeizhi = JSON.parse(this.jcpeizhi);
    this.getDatas();
    this.getBuzhiList();
    this.getBumenList();
    this.getBuyuanList();
    this.getRoleList();
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
      this.formData.userMark = info;
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
            delIds: row.userId,
          };
          let url = base + "/deleteUser";
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
        this.formData.userImg = furl;
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
          let url = base + "/daoruUser";
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
      // 1. 获取原始表格的 DOM 节点
      const table = document.getElementById("myTable");

      // 2. 克隆一个表格副本，防止删除操作影响页面正常显示
      const newTable = table.cloneNode(true);

      // 3. 遍历克隆出来的表格行，删除每一行的最后一个单元格（即操作列）
      const rows = newTable.querySelectorAll("tr");
      rows.forEach(row => {
        if (row.cells.length > 0) {
          // deleteCell(-1) 会自动删除该行的最后一个 td 或 th
          row.deleteCell(-1);
        }
      });

      // 4. 文件名
      const filename = "导出数据.xlsx";

      /* 使用处理过的 newTable 生成 workbook 对象 */
      const wb = XLSX.utils.table_to_book(newTable);

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
      this.selectedIds = selection.map((item) => item.userId);
      this.delIds = JSON.stringify(this.selectedIds).replace(/[\[\]]/g,"");
      console.log(selection);
      console.log(this.delIds);
    },
    //多选批量删除
    deleteUsers() {
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
          let url = base + "/deleteUser";
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
        userName: this.filters.userName,
        userXingming: this.filters.userXingming,
        userPhone: this.filters.userPhone,
        buzhiId: this.filters.buzhiId,
        roleId: this.filters.roleId,
        bumenId: this.filters.bumenId,
        buyuanId: this.filters.buyuanId,
        sdate: this.filters.sdate,
        edate: this.filters.edate,
      };
      if(this.role=='user'){
        var user = sessionStorage.getItem("currentUser");
        user = JSON.parse(user);
        para = {
          userName: this.filters.userName,
          userXingming: this.filters.userXingming,
          userPhone: this.filters.userPhone,
          buzhiId: this.filters.buzhiId,
          roleId: this.filters.roleId,
          bumenId: this.filters.bumenId,
          buyuanId: this.filters.buyuanId,
          sdate: this.filters.sdate,
          edate: this.filters.edate,
          userId:user.userId
        };     
      }
      //console.log(para)
      this.listLoading = true;
      let url = base + "/getUsers?page=" + this.page.currentPage;
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
    //获取role
    getRoleList() {
      let para = {};
      this.listLoading = true;
      let url = base + "/roleComboList";
      request.post(url, para).then((res) => {
        if (res.code == 200) {
          this.roleList = res.resdata;
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
    //打开上传界面
    danduShangchuan(index, row) {
      this.formShangchuan = true;
      this.formData = JSON.parse(JSON.stringify(row));
    },
    //确认上传
    shangchuanSave() {
      this.$refs["formDataRef"].validate((valid) => {
        if (valid) {
          let url = base + "/addUser?userId=" + this.formData.userId;
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
      if (this.formData.userId != null) {
        //如果有ID就修改数据
        this.$refs["formDataRef"].validate((valid) => {
          if (valid) {
            let url = base + "/addUser?userId=" + this.formData.userId;
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
            let url = base + "/addUser";
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
          let url = base + "/addUser?userId=" + this.formData.userId;
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
      this.formShenhe = true;
    },
    //确认审核
    shenheSave() {
      this.$refs["formDataRef"].validate((valid) => {
        if (valid) {
          let url = base + "/addUser?userId=" + this.formData.userId;
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
    //性别状态
    sexType(row){
        if(row.userSex==0){
             return "男";
        }
        if(row.userSex==1){
             return "女";
        }
    },
    //根据数据状态显示文本
    zhuangtaiType(row){
        if(row.userType==0){
             return "启用";
        }
        if(row.userType==1){
             return "禁用";
        }
    },
    //根据数据状态显示文本
    shenheType(row){
        if(row.userType==0){
             return "提交";
        }
        if(row.userType==1){
             return "通过";
        }
        if(row.userType==2){
             return "拒绝";
        }
    },
    //单项更改状态
    shezhiZhuangtai(index, row,zhuangtai) {
      this.listLoading = true;
      let para = {
        userType: zhuangtai,
        userId: row.userId,
      };

      let url = base + "/addUser";
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
