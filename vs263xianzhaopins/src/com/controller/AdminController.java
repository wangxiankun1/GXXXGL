package com.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.model.*;
import com.service.*;
import com.util.*;

import java.io.InputStream;

@Controller
public class AdminController {
	@Autowired
	private AdminService adminService;

	/***上传导入开始***/
	private InputStream excelFile;

	public InputStream getExcelFile() {
		return excelFile;
	}
	/***上传导入结束***/

	//获取列表，搜索条件，返回数据
	@ResponseBody
	@RequestMapping("/getAdmins")
	public Response getAdmins(@RequestBody Object req,HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		/****获取页面参数，转为String型****/
		Map<String,String> newMap = (Map<String, String>) req;
		Map<String,String> map = new HashMap<String, String>();
		for(Map.Entry entry : newMap.entrySet()) {
	        String key = String.valueOf(entry.getKey());
	        String value = "";
	        if(entry.getValue()!=null){
	        	value = String.valueOf(entry.getValue());
	        }
	        map.put(key, value);
	    }
		//获取页码数和行数
	    String page = (String) request.getParameter("page");
	    String rows = (String) request.getParameter("rows");
		if (StringUtil.isEmpty(rows)) {
			rows = "10";
		}
	    //model参数
		String adminName = map.get("adminName");
		String adminXingming = map.get("adminXingming");
		String adminPhone = map.get("adminPhone");
		String adminId = map.get("adminId");
		String adminType1 = map.get("adminType1");
		String adminType2 = map.get("adminType2");
		String adminSex = map.get("adminSex");
		String sdate = map.get("sdate");
		String edate = map.get("edate");
		String sdate1 = map.get("sdate1");
		String edate1 = map.get("edate1");
		Admin admin = new Admin();
		//初始化
		PageBean pageBean = null;
		//分页初始化
		if ((StringUtil.isNotEmpty(page))&&(!page.equals("null"))) {
			if (StringUtil.isEmpty(rows)) {
				rows = "10";
			}
			pageBean = new PageBean(Integer.parseInt(page),Integer.parseInt(rows));
		}else{
			pageBean = new PageBean(0,0);
		}
		try {
			/****初始化赋值，搜索参数****/
			if (StringUtil.isNotEmpty(adminXingming)) {
				admin.setAdminXingming(adminXingming);
			}
			if (StringUtil.isNotEmpty(adminPhone)) {
				admin.setAdminPhone(adminPhone);
			}
			if (StringUtil.isNotEmpty(adminName)) {
				admin.setAdminName(adminName);
			}
			if (StringUtil.isNotEmpty(adminId)) {
				admin.setAdminId(Integer.parseInt(adminId));
			}
			admin.setAdminType(1);
			if (StringUtil.isNotEmpty(adminType1)) {
				admin.setAdminType1(Integer.parseInt(adminType1));
			}
			if (StringUtil.isNotEmpty(adminType2)) {
				admin.setAdminType2(Integer.parseInt(adminType2));
			}
			if (StringUtil.isNotEmpty(adminSex)) {
				admin.setAdminSex(Integer.parseInt(adminSex));
			}
			if (StringUtil.isNotEmpty(sdate1)) {
				Date date = new Date();
				sdate1 = DateUtil.formatDate(date, "yyyy-MM-dd HH:mm:ss");
			}
			if (StringUtil.isNotEmpty(edate1)) {
				Date date = new Date();
				edate1 = DateUtil.formatDate(date, "yyyy-MM-dd HH:mm:ss");
			}
			/****初始化复制，搜索参数结束****/
			//总数
			int total = adminService.queryAdmins(admin,null, 0,0, sdate, edate, sdate1, edate1).size();
			//返回页面（列表、总数、行数）
			return Response.success(adminService.queryAdmins(admin,null, pageBean.getStart(), pageBean.getRows(), sdate, edate, sdate1, edate1),total,Integer.parseInt(rows));
		} catch (Exception e) {
			//e.printStackTrace();
			return Response.error(204,"服务器错误");
		}
	}

	//添加修改同样一个方法，如果id为空就是新增，如果id不为空就会加载原有数据，然后更新数据
	@ResponseBody
	@RequestMapping("/addAdmin")
	public Response addAdmin(@RequestBody Object req,HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Map<String,String> newMap = (Map<String, String>) req;
		Map<String,String> map = new HashMap<String, String>();
		for(Map.Entry entry : newMap.entrySet()) {
	        String key = String.valueOf(entry.getKey());
	        String value = "";
	        if(entry.getValue()!=null){
	        	value = String.valueOf(entry.getValue());
	        }
	        map.put(key, value);
	    }
		try {
		    //model参数
			String adminName = map.get("adminName");
			String adminPassword = map.get("adminPassword");
			String adminAge = map.get("adminAge");
			String adminXingming = map.get("adminXingming");
			String adminSex = map.get("adminSex");
			String adminPhone = map.get("adminPhone");
			String adminMark = map.get("adminMark");
			String adminMark1 = map.get("adminMark1");
			String adminMark2 = map.get("adminMark2");
			String adminMark3 = map.get("adminMark3");
			String adminMark4 = map.get("adminMark4");
			String adminDate = map.get("adminDate");
			String adminDate1 = map.get("adminDate1");
			String adminDate2 = map.get("adminDate2");
			String adminType = map.get("adminType");
			String adminType1 = map.get("adminType1");
			String adminType2 = map.get("adminType2");
			String adminZong = map.get("adminZong");
			String adminZong1 = map.get("adminZong1");
			String adminZong2 = map.get("adminZong2");
			String adminDouble = map.get("adminDouble");
			String adminDouble1 = map.get("adminDouble1");
			String adminDouble2 = map.get("adminDouble2");
			String adminImg = map.get("adminImg");
			String adminId = map.get("adminId");
			//model初始化
			Admin admin = new Admin();
			//判断id是否为空，如果不为空则加载原有的model
			if (StringUtil.isNotEmpty(adminId)) {
				admin = adminService.getAdmin(Integer.parseInt(adminId));
			}
			/****初始化参数赋值****/
			if (StringUtil.isNotEmpty(adminImg)) {
				String str[] = adminImg.split("/");
				adminImg = str[str.length-1];
				admin.setAdminImg(request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/file/" + adminImg);
				admin.setAdminImgName(request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/downFile?filename=" + adminImg);
			}
			if (StringUtil.isNotEmpty(adminName)) {
				admin.setAdminName(adminName);
			}
			if (StringUtil.isNotEmpty(adminPassword)) {
				admin.setAdminPassword(adminPassword);
			}
			if (StringUtil.isNotEmpty(adminAge)) {
				admin.setAdminAge(Integer.parseInt(adminAge));
			}
			if (StringUtil.isNotEmpty(adminXingming)) {
				admin.setAdminXingming(adminXingming);
			}
			if (StringUtil.isNotEmpty(adminSex)) {
				admin.setAdminSex(Integer.parseInt(adminSex));
			}
			if (StringUtil.isNotEmpty(adminPhone)) {
				admin.setAdminPhone(adminPhone);
			}
			if (StringUtil.isNotEmpty(adminMark)) {
				admin.setAdminMark(adminMark);
			}
			if (StringUtil.isNotEmpty(adminMark1)) {
				admin.setAdminMark1(adminMark1);
			}
			if (StringUtil.isNotEmpty(adminMark2)) {
				admin.setAdminMark2(adminMark2);
			}
			if (StringUtil.isNotEmpty(adminMark3)) {
				admin.setAdminMark3(adminMark3);
			}
			if (StringUtil.isNotEmpty(adminMark4)) {
				admin.setAdminMark4(adminMark4);
			}
			if (StringUtil.isNotEmpty(adminDate)) {
				admin.setAdminDate(DateUtil.formatString(adminDate,
						"yyyy-MM-dd HH:mm:ss"));
			}
			if (StringUtil.isNotEmpty(adminDate1)) {
				admin.setAdminDate1(DateUtil.formatString(adminDate1,
						"yyyy-MM-dd HH:mm:ss"));
			}
			if (StringUtil.isNotEmpty(adminDate2)) {
				admin.setAdminDate2(DateUtil.formatString(adminDate2,
						"yyyy-MM-dd HH:mm:ss"));
			}
			if (StringUtil.isNotEmpty(adminType)) {
				admin.setAdminType(Integer.parseInt(adminType));
			}
			if (StringUtil.isNotEmpty(adminType1)) {
				admin.setAdminType1(Integer.parseInt(adminType1));
			}
			if (StringUtil.isNotEmpty(adminType2)) {
				admin.setAdminType2(Integer.parseInt(adminType2));
			}
			if (StringUtil.isNotEmpty(adminZong)) {
				admin.setAdminZong(Integer.parseInt(adminZong));
			}
			if (StringUtil.isNotEmpty(adminZong1)) {
				admin.setAdminZong1(Integer.parseInt(adminZong1));
			}
			if (StringUtil.isNotEmpty(adminZong2)) {
				admin.setAdminZong2(Integer.parseInt(adminZong2));
			}
			if (StringUtil.isNotEmpty(adminDouble)) {
				admin.setAdminDouble(Double.parseDouble(adminDouble));
			}
			if (StringUtil.isNotEmpty(adminDouble1)) {
				admin.setAdminDouble1(Double.parseDouble(adminDouble1));
			}
			if (StringUtil.isNotEmpty(adminDouble2)) {
				admin.setAdminDouble2(Double.parseDouble(adminDouble2));
			}
			/****初始化参数赋值结束****/
			//id不为空，修改（更改状态等）；Id为空就是增加；然后返回结果
			if (StringUtil.isNotEmpty(adminId)) {
				adminService.modifyAdmin(admin);
				return Response.success();
			} else {
				int total = adminService.queryAdmins(null, adminName, 0, 0, null, null, null, null).size();
				if (total==0) {
					Date date = new Date();
					admin.setAdminDate(date);
					admin.setAdminType(1);
					adminService.save(admin);
					return Response.success();
				} else {
					return Response.error(201,"用户名重复");
				}
			}
		} catch (Exception e) {
			//e.printStackTrace();
			return Response.error(204,"服务器错误");
		}
	}

	//修改密码
	@ResponseBody
	@RequestMapping("/mimaAdmin")
	public Response mimaAdmin(@RequestBody Object req,HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Map<String,String> newMap = (Map<String, String>) req;
		Map<String,String> map = new HashMap<String, String>();
		for(Map.Entry entry : newMap.entrySet()) {
	        String key = String.valueOf(entry.getKey());
	        String value = "";
	        if(entry.getValue()!=null){
	        	value = String.valueOf(entry.getValue());
	        }
	        map.put(key, value);
	    }		
		String adminId = (String) request.getParameter("adminId");
		String adminPassword = map.get("adminPassword");
		String adminPassword1 = map.get("adminPassword1");
		Admin admin = new Admin();
		try {
			admin = adminService.getAdmin(Integer.parseInt(adminId));
			if (!admin.getAdminPassword().equals(adminPassword)) {
				return Response.error(202,"原密码错误，请重新输入");
			}else{
				admin.setAdminPassword(adminPassword1);
				adminService.modifyAdmin(admin);
				return Response.success();
			}
		} catch (Exception e) {
			//e.printStackTrace();
			return Response.error(202,"系统错误");
		}
	}

	//找回密码
	@ResponseBody
	@RequestMapping("/zhaohuiAdmin")
	public Response zhaohuiAdmin(@RequestBody Object req,HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Map<String,String> newMap = (Map<String, String>) req;
		Map<String,String> map = new HashMap<String, String>();
		for(Map.Entry entry : newMap.entrySet()) {
	        String key = String.valueOf(entry.getKey());
	        String value = "";
	        if(entry.getValue()!=null){
	        	value = String.valueOf(entry.getValue());
	        }
	        map.put(key, value);
	    }
		try {
			String adminName = map.get("adminName");
			if(adminName.equals("admin")){
				return Response.error(202,"不支持admin用户找回密码");
			}else{
				String adminPhone = map.get("adminPhone");
				Admin admin = new Admin();
				if (StringUtil.isNotEmpty(adminName)) {
					admin.setAdminName(adminName);
				}
				List<Admin> admins = adminService.queryAdmins(admin, null, 0,0, null, null, null, null);
				if(admins.size()==0){
					return Response.error(202,"无此用户名请重新找回");
				}else{
					admin = admins.get(0);
					if(admin.getAdminPhone().equals(adminPhone)){
						String adminPassword = admin.getAdminPassword();
						return Response.error(202,"密码："+adminPassword);
					}else{
						return Response.error(202,"用户名和电话不匹配");
					}
				}
			}
		} catch (Exception e) {
			//e.printStackTrace();
			return Response.error(202,"系统错误，联系管理员");
		}
	}

	//注册
	@ResponseBody
	@RequestMapping("/zhuceAdmin")
	public Response zhuceAdmin(@RequestBody Object req,HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Map<String,String> newMap = (Map<String, String>) req;
		Map<String,String> map = new HashMap<String, String>();
		for(Map.Entry entry : newMap.entrySet()) {
	        String key = String.valueOf(entry.getKey());
	        String value = "";
	        if(entry.getValue()!=null){
	        	value = String.valueOf(entry.getValue());
	        }
	        map.put(key, value);
	    }
		try {
			String adminName = map.get("adminName");
			String adminPassword = map.get("adminPassword");
			String adminAge = map.get("adminAge");
			String adminXingming = map.get("adminXingming");
			String adminSex = map.get("adminSex");
			String adminPhone = map.get("adminPhone");
			String adminMark = map.get("adminMark");
			String adminMark1 = map.get("adminMark1");
			String adminMark2 = map.get("adminMark2");
			String adminMark3 = map.get("adminMark3");
			String adminMark4 = map.get("adminMark4");
			String adminDate = map.get("adminDate");
			String adminDate1 = map.get("adminDate1");
			String adminDate2 = map.get("adminDate2");
			String adminType = map.get("adminType");
			String adminType1 = map.get("adminType1");
			String adminType2 = map.get("adminType2");
			String adminZong = map.get("adminZong");
			String adminZong1 = map.get("adminZong1");
			String adminZong2 = map.get("adminZong2");
			String adminDouble = map.get("adminDouble");
			String adminDouble1 = map.get("adminDouble1");
			String adminDouble2 = map.get("adminDouble2");
			String adminId = map.get("adminId");
			Admin admin = new Admin();

			if (StringUtil.isNotEmpty(adminId)) {
				admin = adminService.getAdmin(Integer.parseInt(adminId));
			}
			if (StringUtil.isNotEmpty(adminName)) {
				admin.setAdminName(adminName);
			}
			if (StringUtil.isNotEmpty(adminPassword)) {
				admin.setAdminPassword(adminPassword);
			}
			if (StringUtil.isNotEmpty(adminAge)) {
				admin.setAdminAge(Integer.parseInt(adminAge));
			}
			if (StringUtil.isNotEmpty(adminXingming)) {
				admin.setAdminXingming(adminXingming);
			}
			if (StringUtil.isNotEmpty(adminSex)) {
				admin.setAdminSex(Integer.parseInt(adminSex));
			}
			if (StringUtil.isNotEmpty(adminPhone)) {
				admin.setAdminPhone(adminPhone);
			}
			if (StringUtil.isNotEmpty(adminMark)) {
				admin.setAdminMark(adminMark);
			}
			if (StringUtil.isNotEmpty(adminMark1)) {
				admin.setAdminMark1(adminMark1);
			}
			if (StringUtil.isNotEmpty(adminMark2)) {
				admin.setAdminMark2(adminMark2);
			}
			if (StringUtil.isNotEmpty(adminMark3)) {
				admin.setAdminMark3(adminMark3);
			}
			if (StringUtil.isNotEmpty(adminMark4)) {
				admin.setAdminMark4(adminMark4);
			}
			if (StringUtil.isNotEmpty(adminDate)) {
				admin.setAdminDate(DateUtil.formatString(adminDate,
						"yyyy-MM-dd HH:mm:ss"));
			}
			if (StringUtil.isNotEmpty(adminDate1)) {
				admin.setAdminDate1(DateUtil.formatString(adminDate1,
						"yyyy-MM-dd HH:mm:ss"));
			}
			if (StringUtil.isNotEmpty(adminDate2)) {
				admin.setAdminDate2(DateUtil.formatString(adminDate2,
						"yyyy-MM-dd HH:mm:ss"));
			}
			if (StringUtil.isNotEmpty(adminType)) {
				admin.setAdminType(Integer.parseInt(adminType));
			}
			if (StringUtil.isNotEmpty(adminType1)) {
				admin.setAdminType1(Integer.parseInt(adminType1));
			}
			if (StringUtil.isNotEmpty(adminType2)) {
				admin.setAdminType2(Integer.parseInt(adminType2));
			}
			if (StringUtil.isNotEmpty(adminZong)) {
				admin.setAdminZong(Integer.parseInt(adminZong));
			}
			if (StringUtil.isNotEmpty(adminZong1)) {
				admin.setAdminZong1(Integer.parseInt(adminZong1));
			}
			if (StringUtil.isNotEmpty(adminZong2)) {
				admin.setAdminZong2(Integer.parseInt(adminZong2));
			}
			if (StringUtil.isNotEmpty(adminDouble)) {
				admin.setAdminDouble(Double.parseDouble(adminDouble));
			}
			if (StringUtil.isNotEmpty(adminDouble1)) {
				admin.setAdminDouble1(Double.parseDouble(adminDouble1));
			}
			if (StringUtil.isNotEmpty(adminDouble2)) {
				admin.setAdminDouble2(Double.parseDouble(adminDouble2));
			}
			int total = adminService.queryAdmins(null, adminName, 0, 0, null, null, null, null).size();
			if (total==0) {
				Date date = new Date();
				admin.setAdminDate(date);
				admin.setAdminType(1);
				adminService.save(admin);
				return Response.success();
			} else {
				return Response.error(204,"用户名重复");
			}
		} catch (Exception e) {
			//e.printStackTrace();
			return Response.error(204,"服务器错误");
		}
	}

	//删除数据
	@ResponseBody
	@RequestMapping("/deleteAdmin")
	public Response deleteAdmin(@RequestBody Object req,HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Map<String,String> newMap = (Map<String, String>) req;
		Map<String,String> map = new HashMap<String, String>();
		for(Map.Entry entry : newMap.entrySet()) {
	        String key = String.valueOf(entry.getKey());
	        String value = "";
	        if(entry.getValue()!=null){
	        	value = String.valueOf(entry.getValue());
	        }
	        map.put(key, value);
	    }
		String delIds = map.get("delIds");
		try {
			String str[] = delIds.split(",");
			for (int i = 0; i < str.length; i++) {
				adminService.deleteAdmin(Integer.parseInt(str[i]));
			}
			return Response.success();
		} catch (Exception e) {
			//e.printStackTrace();
			return Response.error(204,"服务器错误");
		}
	}
}
