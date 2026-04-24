package com.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.model.*;
import com.service.*;
import com.util.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
@Controller
public class UserController {
	@Autowired
	private UserService userService;
	@Autowired
	private BumenService bumenService;
	@Autowired
	private BuyuanService buyuanService;
	@Autowired
	private BuzhiService buzhiService;
	@Autowired
	private RoleService roleService;

	/***上传导入开始***/
	private InputStream excelFile;

	public InputStream getExcelFile() {
		return excelFile;
	}
	/***上传导入结束***/

	//获取列表，搜索条件，返回数据
	@ResponseBody
	@RequestMapping("/getUsers")
	public Response getUsers(@RequestBody Object req,HttpServletRequest request,
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
		String userName = map.get("userName");
		String userXingming = map.get("userXingming");
		String userPhone = map.get("userPhone");
		String userId = map.get("userId");
		String userMinzu = map.get("userMinzu");
		String userType = map.get("userType");
		String userType1 = map.get("userType1");
		String userType2 = map.get("userType2");
		String roleId = map.get("roleId");
		String bumenId = map.get("bumenId");
		String buyuanId = map.get("buyuanId");
		String buzhiId = map.get("buzhiId");
		String userSex = map.get("userSex");
		String sdate = map.get("sdate");
		String edate = map.get("edate");
		String sdate1 = map.get("sdate1");
		String edate1 = map.get("edate1");
		//初始化
		User user = new User();
		//分页初始化
		PageBean pageBean = null;
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
			if (StringUtil.isNotEmpty(userPhone)) {
				user.setUserPhone(userPhone);
			}
			if (StringUtil.isNotEmpty(userXingming)) {
				user.setUserXingming(userXingming);
			}
			if (StringUtil.isNotEmpty(userName)) {
				user.setUserName(userName);
			}
			if (StringUtil.isNotEmpty(userId)) {
				user.setUserId(Integer.parseInt(userId));
			}
			if (StringUtil.isNotEmpty(buyuanId)) {
				user.setBuyuanId(Integer.parseInt(buyuanId));
			}
			if (StringUtil.isNotEmpty(buzhiId)) {
				user.setBuzhiId(Integer.parseInt(buzhiId));
			}
			if (StringUtil.isNotEmpty(userMinzu)) {
				user.setUserMinzu(userMinzu);
			}
			if (StringUtil.isNotEmpty(userType)) {
				user.setUserType(Integer.parseInt(userType));
			}
			if (StringUtil.isNotEmpty(userType1)) {
				user.setUserType1(Integer.parseInt(userType1));
			}
			if (StringUtil.isNotEmpty(userType2)) {
				user.setUserType2(Integer.parseInt(userType2));
			}
			if (StringUtil.isNotEmpty(roleId)) {
				user.setRoleId(Integer.parseInt(roleId));
			}
			if (StringUtil.isNotEmpty(bumenId)) {
				user.setBumenId(Integer.parseInt(bumenId));
			}
			if (StringUtil.isNotEmpty(userSex)) {
				user.setUserSex(Integer.parseInt(userSex));
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
			int total = userService.queryUsers(user,null, 0,0, sdate, edate, sdate1, edate1).size();
			//返回页面（列表、总数、行数）
			return Response.success(userService.queryUsers(user,null, pageBean.getStart(), pageBean.getRows(), sdate, edate, sdate1, edate1),total,Integer.parseInt(rows));
		} catch (Exception e) {
			//e.printStackTrace();
			return Response.error(204,"服务器错误");
		}
	}

	//添加修改同样一个方法，如果id为空就是新增，如果id不为空就会加载原有数据，然后更新数据
	@ResponseBody
	@RequestMapping("/addUser")
	public Response addUser(@RequestBody Object req,HttpServletRequest request, HttpServletResponse response)
			throws Exception {
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
		try {
		    //model参数
			String userName = map.get("userName");
			String userPassword = map.get("userPassword");
			String userAge = map.get("userAge");
			String userXingming = map.get("userXingming");
			String userSex = map.get("userSex");
			String userMinzu = map.get("userMinzu");
			String userPhone = map.get("userPhone");
			String userMark = map.get("userMark");
			String userMark1 = map.get("userMark1");
			String userMark2 = map.get("userMark2");
			String userMark3 = map.get("userMark3");
			String userMark4 = map.get("userMark4");
			String userDate = map.get("userDate");
			String userDate1 = map.get("userDate1");
			String userDate2 = map.get("userDate2");
			String userType = map.get("userType");
			String userType1 = map.get("userType1");
			String userType2 = map.get("userType2");
			String userZong = map.get("userZong");
			String userZong1 = map.get("userZong1");
			String userZong2 = map.get("userZong2");
			String userDouble = map.get("userDouble");
			String userDouble1 = map.get("userDouble1");
			String userDouble2 = map.get("userDouble2");
			String roleId = map.get("roleId");
			String bumenId = map.get("bumenId");
			String buyuanId = map.get("buyuanId");
			String buzhiId = map.get("buzhiId");
			String userImg = map.get("userImg");
			String userId = map.get("userId");
			//model初始化
			User user = new User();
			//判断id是否为空，如果不为空则加载原有的model
			if (StringUtil.isNotEmpty(userId)) {
				user = userService.getUser(Integer.parseInt(userId));
			}
			/****初始化参数赋值****/
			if (StringUtil.isNotEmpty(userImg)) {
				String str[] = userImg.split("/");
				userImg = str[str.length-1];
				user.setUserImg(request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/file/" + userImg);
				user.setUserImgName(request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/downFile?filename=" + userImg);
			}
			if (StringUtil.isNotEmpty(userName)) {
				user.setUserName(userName);
			}
			if (StringUtil.isNotEmpty(userPassword)) {
				user.setUserPassword(userPassword);
			}
			if (StringUtil.isNotEmpty(userAge)) {
				user.setUserAge(Integer.parseInt(userAge));
			}
			if (StringUtil.isNotEmpty(userXingming)) {
				user.setUserXingming(userXingming);
			}
			if (StringUtil.isNotEmpty(userSex)) {
				user.setUserSex(Integer.parseInt(userSex));
			}
			if (StringUtil.isNotEmpty(userMinzu)) {
				user.setUserMinzu(userMinzu);
			}
			if (StringUtil.isNotEmpty(userPhone)) {
				user.setUserPhone(userPhone);
			}
			if (StringUtil.isNotEmpty(userMark)) {
				user.setUserMark(userMark);
			}
			if (StringUtil.isNotEmpty(userMark1)) {
				user.setUserMark1(userMark1);
			}
			if (StringUtil.isNotEmpty(userMark2)) {
				user.setUserMark2(userMark2);
			}
			if (StringUtil.isNotEmpty(userMark3)) {
				user.setUserMark3(userMark3);
			}
			if (StringUtil.isNotEmpty(userMark4)) {
				user.setUserMark4(userMark4);
			}
			if (StringUtil.isNotEmpty(userDate)) {
				user.setUserDate(DateUtil.formatString(userDate,
						"yyyy-MM-dd HH:mm:ss"));
			}
			if (StringUtil.isNotEmpty(userDate1)) {
				user.setUserDate1(DateUtil.formatString(userDate1,
						"yyyy-MM-dd HH:mm:ss"));
			}
			if (StringUtil.isNotEmpty(userDate2)) {
				user.setUserDate2(DateUtil.formatString(userDate2,
						"yyyy-MM-dd HH:mm:ss"));
			}
			if (StringUtil.isNotEmpty(userType)) {
				user.setUserType(Integer.parseInt(userType));
			}
			if (StringUtil.isNotEmpty(userType1)) {
				user.setUserType1(Integer.parseInt(userType1));
			}
			if (StringUtil.isNotEmpty(userType2)) {
				user.setUserType2(Integer.parseInt(userType2));
			}
			if (StringUtil.isNotEmpty(userZong)) {
				user.setUserZong(Integer.parseInt(userZong));
			}
			if (StringUtil.isNotEmpty(userZong1)) {
				user.setUserZong1(Integer.parseInt(userZong1));
			}
			if (StringUtil.isNotEmpty(userZong2)) {
				user.setUserZong2(Integer.parseInt(userZong2));
			}
			if (StringUtil.isNotEmpty(userDouble)) {
				user.setUserDouble(Double.parseDouble(userDouble));
			}
			if (StringUtil.isNotEmpty(userDouble1)) {
				user.setUserDouble1(Double.parseDouble(userDouble1));
			}
			if (StringUtil.isNotEmpty(userDouble2)) {
				user.setUserDouble2(Double.parseDouble(userDouble2));
			}
			if (StringUtil.isNotEmpty(roleId)) {
				user.setRoleId(Integer.parseInt(roleId));
				Role role = new Role();
				role = roleService.getRole(Integer.parseInt(roleId));
				user.setRoleName(role.getRoleName());
			}
			if (StringUtil.isNotEmpty(buzhiId)) {
				user.setBuzhiId(Integer.parseInt(buzhiId));
				Buzhi buzhi = new Buzhi();
				buzhi = buzhiService.getBuzhi(Integer.parseInt(buzhiId));
				user.setBuzhiName(buzhi.getBuzhiName());
				user.setBumenId(buzhi.getBumenId());
				user.setBumenName(buzhi.getBumenName());
				user.setBuyuanId(buzhi.getBuyuanId());
				user.setBuyuanName(buzhi.getBuyuanName());
			}
			if (StringUtil.isNotEmpty(bumenId)) {
				user.setBumenId(Integer.parseInt(bumenId));
				Bumen bumen = new Bumen();
				bumen = bumenService.getBumen(Integer.parseInt(bumenId));
				user.setBumenName(bumen.getBumenName());
				user.setBuyuanId(bumen.getBuyuanId());
				user.setBuyuanName(bumen.getBuyuanName());
			}
			if (StringUtil.isNotEmpty(buyuanId)) {
				user.setBuyuanId(Integer.parseInt(buyuanId));
				Buyuan buyuan = new Buyuan();
				buyuan = buyuanService.getBuyuan(Integer.parseInt(buyuanId));
				user.setBuyuanName(buyuan.getBuyuanName());
			}
			/****初始化参数赋值结束****/
			//id不为空，修改（更改状态等）；Id为空就是增加；然后返回结果
			if (StringUtil.isNotEmpty(userId)) {
				userService.modifyUser(user);
				return Response.success();
			} else {
				int total = userService.queryUsers(null, userName, 0, 0, null, null, null, null).size();
				if (total==0) {
					Date date = new Date();
					user.setUserDate(date);
					user.setUserType(0);
					user.setUserType2(0);
					userService.save(user);
					return Response.success();
				} else {
					return Response.error(204,"用户名重复");
				}
			}
		} catch (Exception e) {
			//e.printStackTrace();
			return Response.error(204,"服务器错误");
		}
	}

	//修改密码
	@ResponseBody
	@RequestMapping("/mimaUser")
	public Response mimaUser(@RequestBody Object req,HttpServletRequest request, HttpServletResponse response)
			throws Exception {
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
		String userId = (String) request.getParameter("userId");
		String userPassword = map.get("userPassword");
		String userPassword1 = map.get("userPassword1");
		User user = new User();
		try {
			user = userService.getUser(Integer.parseInt(userId));
			if (!user.getUserPassword().equals(userPassword)) {
				return Response.error(202,"原密码错误，请重新输入");
			}else{
				user.setUserPassword(userPassword1);
				userService.modifyUser(user);
				return Response.success();
			}
		} catch (Exception e) {
			//e.printStackTrace();
			return Response.error(202,"系统错误");
		}
	}

	//找回密码
	@ResponseBody
	@RequestMapping("/zhaohuiUser")
	public Response zhaohuiUser(@RequestBody Object req,HttpServletRequest request, HttpServletResponse response)
			throws Exception {
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
		try {
			String userName = map.get("userName");
			String userPhone = map.get("userPhone");
			User user = new User();

			if (StringUtil.isNotEmpty(userName)) {
				user.setUserName(userName);
			}
			List<User> users = userService.queryUsers(user, null, 0,0, null, null, null, null);
			if(users.size()==0){
				return Response.error(202,"无此用户名请重新找回");
			}else{
				user = users.get(0);
				if(user.getUserPhone().equals(userPhone)){
					String userPassword = user.getUserPassword();
					return Response.error(202,"密码："+userPassword);
				}else{
					return Response.error(202,"用户名和电话不匹配");
				}
			}
		} catch (Exception e) {
			//e.printStackTrace();
			return Response.error(202,"系统错误");
		}
	}

	//注册
	@ResponseBody
	@RequestMapping("/zhuceUser")
	public Response zhuceUser(@RequestBody Object req,HttpServletRequest request, HttpServletResponse response)
			throws Exception {
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
		try {
			String userName = map.get("userName");
			String userPassword = map.get("userPassword");
			String userAge = map.get("userAge");
			String userXingming = map.get("userXingming");
			String userSex = map.get("userSex");
			String userMinzu = map.get("userMinzu");
			String userPhone = map.get("userPhone");
			String userMark = map.get("userMark");
			String userMark1 = map.get("userMark1");
			String userMark2 = map.get("userMark2");
			String userMark3 = map.get("userMark3");
			String userMark4 = map.get("userMark4");
			String userDate = map.get("userDate");
			String userDate1 = map.get("userDate1");
			String userDate2 = map.get("userDate2");
			String userType = map.get("userType");
			String userType1 = map.get("userType1");
			String userType2 = map.get("userType2");
			String userZong = map.get("userZong");
			String userZong1 = map.get("userZong1");
			String userZong2 = map.get("userZong2");
			String userDouble = map.get("userDouble");
			String userDouble1 = map.get("userDouble1");
			String userDouble2 = map.get("userDouble2");
			String roleId = map.get("roleId");
			String bumenId = map.get("bumenId");
			String buyuanId = map.get("buyuanId");
			String buzhiId = map.get("buzhiId");
			User user = new User();
			if (StringUtil.isNotEmpty(userName)) {
				user.setUserName(userName);
			}
			if (StringUtil.isNotEmpty(userPassword)) {
				user.setUserPassword(userPassword);
			}
			if (StringUtil.isNotEmpty(userAge)) {
				user.setUserAge(Integer.parseInt(userAge));
			}
			if (StringUtil.isNotEmpty(userMinzu)) {
				user.setUserMinzu(userMinzu);
			}
			if (StringUtil.isNotEmpty(userXingming)) {
				user.setUserXingming(userXingming);
			}
			if (StringUtil.isNotEmpty(userSex)) {
				user.setUserSex(Integer.parseInt(userSex));
			}
			if (StringUtil.isNotEmpty(userPhone)) {
				user.setUserPhone(userPhone);
			}
			if (StringUtil.isNotEmpty(userMark)) {
				user.setUserMark(userMark);
			}
			if (StringUtil.isNotEmpty(userMark1)) {
				user.setUserMark1(userMark1);
			}
			if (StringUtil.isNotEmpty(userMark2)) {
				user.setUserMark2(userMark2);
			}
			if (StringUtil.isNotEmpty(userMark3)) {
				user.setUserMark3(userMark3);
			}
			if (StringUtil.isNotEmpty(userMark4)) {
				user.setUserMark4(userMark4);
			}
			if (StringUtil.isNotEmpty(userDate)) {
				user.setUserDate(DateUtil.formatString(userDate,
						"yyyy-MM-dd HH:mm:ss"));
			}
			if (StringUtil.isNotEmpty(userDate1)) {
				user.setUserDate1(DateUtil.formatString(userDate1,
						"yyyy-MM-dd HH:mm:ss"));
			}
			if (StringUtil.isNotEmpty(userDate2)) {
				user.setUserDate2(DateUtil.formatString(userDate2,
						"yyyy-MM-dd HH:mm:ss"));
			}
			if (StringUtil.isNotEmpty(userType)) {
				user.setUserType(Integer.parseInt(userType));
			}
			if (StringUtil.isNotEmpty(userType1)) {
				user.setUserType1(Integer.parseInt(userType1));
			}
			if (StringUtil.isNotEmpty(userType2)) {
				user.setUserType2(Integer.parseInt(userType2));
			}
			if (StringUtil.isNotEmpty(userZong)) {
				user.setUserZong(Integer.parseInt(userZong));
			}
			if (StringUtil.isNotEmpty(userZong1)) {
				user.setUserZong1(Integer.parseInt(userZong1));
			}
			if (StringUtil.isNotEmpty(userZong2)) {
				user.setUserZong2(Integer.parseInt(userZong2));
			}
			if (StringUtil.isNotEmpty(userDouble)) {
				user.setUserDouble(Double.parseDouble(userDouble));
			}
			if (StringUtil.isNotEmpty(userDouble1)) {
				user.setUserDouble1(Double.parseDouble(userDouble1));
			}
			if (StringUtil.isNotEmpty(userDouble2)) {
				user.setUserDouble2(Double.parseDouble(userDouble2));
			}
			if (StringUtil.isNotEmpty(roleId)) {
				user.setRoleId(Integer.parseInt(roleId));
				Role role = new Role();
				role = roleService.getRole(Integer.parseInt(roleId));
				user.setRoleName(role.getRoleName());
			}
			if (StringUtil.isNotEmpty(buzhiId)) {
				user.setBuzhiId(Integer.parseInt(buzhiId));
				Buzhi buzhi = new Buzhi();
				buzhi = buzhiService.getBuzhi(Integer.parseInt(buzhiId));
				user.setBuzhiName(buzhi.getBuzhiName());
				user.setBumenId(buzhi.getBumenId());
				user.setBumenName(buzhi.getBumenName());
				user.setBuyuanId(buzhi.getBuyuanId());
				user.setBuyuanName(buzhi.getBuyuanName());
			}
			if (StringUtil.isNotEmpty(bumenId)) {
				user.setBumenId(Integer.parseInt(bumenId));
				Bumen bumen = new Bumen();
				bumen = bumenService.getBumen(Integer.parseInt(bumenId));
				user.setBumenName(bumen.getBumenName());
				user.setBuyuanId(bumen.getBuyuanId());
				user.setBuyuanName(bumen.getBuyuanName());
			}
			if (StringUtil.isNotEmpty(buyuanId)) {
				user.setBuyuanId(Integer.parseInt(buyuanId));
				Buyuan buyuan = new Buyuan();
				buyuan = buyuanService.getBuyuan(Integer.parseInt(buyuanId));
				user.setBuyuanName(buyuan.getBuyuanName());
			}
			int total = userService.queryUsers(null, userName, 0, 0, null, null, null, null).size();
			if (total==0) {
				Date date = new Date();
				user.setUserDate(date);
				user.setUserType(0);
				user.setUserType2(0);
				userService.save(user);
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
	@RequestMapping("/deleteUser")
	public Response deleteUser(@RequestBody Object req,HttpServletRequest request, HttpServletResponse response)
			throws Exception {
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
		String delIds = map.get("delIds");
		try {
			String str[] = delIds.split(",");
			for (int i = 0; i < str.length; i++) {
				userService.deleteUser(Integer.parseInt(str[i]));
			}
			return Response.success();
		} catch (Exception e) {
			//e.printStackTrace();
			return Response.error(204,"服务器错误");
		}
	}

	//下拉列表参数，跟搜索基本上一样，但是返回的是所有值，不分页
	@ResponseBody
	@RequestMapping("/userComboList")
	public Response userComboList(@RequestBody Object req,HttpServletRequest request, HttpServletResponse response)
			throws Exception {
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
		String userName = map.get("userName");
		String userXingming = map.get("userXingming");
		String userId = map.get("userId");
		String userMinzu = map.get("userMinzu");
		String userType = map.get("userType");
		String userType1 = map.get("userType1");
		String userType2 = map.get("userType2");
		String roleId = map.get("roleId");
		String bumenId = map.get("bumenId");
		String buyuanId = map.get("buyuanId");
		String buzhiId = map.get("buzhiId");
		String userSex = map.get("userSex");
		User user = new User();
		try {
			if (StringUtil.isNotEmpty(userXingming)) {
				user.setUserXingming(userXingming);
			}
			if (StringUtil.isNotEmpty(userName)) {
				user.setUserName(userName);
			}
			if (StringUtil.isNotEmpty(userId)) {
				user.setUserId(Integer.parseInt(userId));
			}
			if (StringUtil.isNotEmpty(buyuanId)) {
				user.setBuyuanId(Integer.parseInt(buyuanId));
			}
			if (StringUtil.isNotEmpty(buzhiId)) {
				user.setBuzhiId(Integer.parseInt(buzhiId));
			}
			if (StringUtil.isNotEmpty(userMinzu)) {
				user.setUserMinzu(userMinzu);
			}
			if (StringUtil.isNotEmpty(userType)) {
				user.setUserType(Integer.parseInt(userType));
			}
			if (StringUtil.isNotEmpty(userType1)) {
				user.setUserType1(Integer.parseInt(userType1));
			}
			if (StringUtil.isNotEmpty(userType2)) {
				user.setUserType2(Integer.parseInt(userType2));
			}
			if (StringUtil.isNotEmpty(roleId)) {
				user.setRoleId(Integer.parseInt(roleId));
			}
			if (StringUtil.isNotEmpty(bumenId)) {
				user.setBumenId(Integer.parseInt(bumenId));
			}
			if (StringUtil.isNotEmpty(userSex)) {
				user.setUserSex(Integer.parseInt(userSex));
			}
			return Response.success(userService.queryUsers(user,null, 0,0, null, null, null, null));
		} catch (Exception e) {
			//e.printStackTrace();
			return Response.error(204,"服务器错误");
		}
	}

	//统计方法
	@ResponseBody
	@RequestMapping("/userTongji")
	public Response userTongji(@RequestBody Object req,HttpServletRequest request, HttpServletResponse response)
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
		//统计条件参数
		String sdate=map.get("sdate");
		String edate=map.get("edate");
		String userName = map.get("userName");
		String userId=map.get("userId");
		List<Integer> userSexs = new ArrayList<Integer>();
		userSexs.add(0);
		userSexs.add(1);
		//统计图横（X）轴名称
		List<String> nameList = new ArrayList<String>();
		nameList.add("男");
		nameList.add("女");
		List<User> users = new ArrayList<User>();
		User user = new User();
		if (StringUtil.isNotEmpty(userId)) {
			user.setUserId(Integer.parseInt(userId));
		}
		try {
			//统计图竖（Y）轴数值
	        List<Double> zongshuList = new ArrayList<Double>();
	        String tongjiUser = "[";
			for(int i=0;i<userSexs.size();i++){
				Double userZongshu = 0.0;
				if (StringUtil.isNotEmpty(userName)) {
					user.setUserName(userName);
				}
				user.setUserSex(userSexs.get(i));
				users = userService.queryUsers(user, null, 0,0,sdate,edate, null, null);
				userZongshu = userZongshu + users.size();
				//统计数值
				zongshuList.add(userZongshu);
				
				tongjiUser = tongjiUser + "{\"value\":" + userZongshu + ",\"name\": '" + nameList.get(i) + "' }";
				if(i<(userSexs.size()-1)){
					tongjiUser = tongjiUser + ",";
				}
			}
			tongjiUser = tongjiUser + "]";
			//转为map型
			Map<String,Object> tongjiMap=new HashMap<String,Object>();
			tongjiMap.put("value",zongshuList);
			tongjiMap.put("name",nameList);
			//转为json型
			JSONArray jsonArray = JSONArray.fromObject(tongjiUser);
			return Response.tongjiSuccess(tongjiMap,jsonArray);
		} catch (Exception e) {
			//e.printStackTrace();
			return Response.error(204,"服务器错误");
		}
	}
	
	//导入方法
	@ResponseBody
	@RequestMapping("/daoruUser")
	public Response daoruUser(@RequestBody Object req,HttpServletRequest request, HttpServletResponse response)
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
	        // 和上传时保持完全一致的目录写法
	        String directory = "//WebRoot//WEB-INF//classes//static//file";
	        // 文件保存目录路径（项目运行根路径 + directory）
	        String savePath = System.getProperty("user.dir") + directory;
	        if (!savePath.endsWith(File.separator)) {
	            savePath = savePath + File.separator;
	        }

	        String fileName = map.get("userImg");   // excel 文件名，例如 20251206144230_599.xls

	        // 用 savePath + fileName 来定位文件，不再用 getRealPath
	        File dir = new File(savePath, fileName);

	        // 如果目录或者文件不存在，先给出明确提示，方便你排查
	        if (!dir.exists()) {
	            System.out.println("导入文件不存在，实际路径：" + dir.getAbsolutePath());
	            return Response.error(204, "导入文件不存在：" + dir.getAbsolutePath());
	        }

	        excelFile = new FileInputStream(dir);

	        List<List<String>> list = ExcelUtil.jiexiExcel(excelFile);
	        for (int i = 1; i < list.size(); i++) {
	            List<String> row = list.get(i);
	            String userName = row.get(1);
	            String userPassword = row.get(2);
	            String userXingming = row.get(3);
	            String userSex = row.get(4);
	            String userAge = row.get(5);
	            String userPhone = row.get(6);
	            String userMark = row.get(7);
	            String userMark1 = row.get(8);
	            String userMark2 = row.get(9);
	            User user = new User();

	            if (StringUtil.isNotEmpty(userName)) {
	                user.setUserName(userName);
	            }
	            if (StringUtil.isNotEmpty(userPassword)) {
	                user.setUserPassword(userPassword);
	            }
	            if (StringUtil.isNotEmpty(userAge)) {
	                user.setUserAge(Integer.parseInt(userAge));
	            }
	            if (StringUtil.isNotEmpty(userXingming)) {
	                user.setUserXingming(userXingming);
	            }
	            if (StringUtil.isNotEmpty(userSex)) {
	                if(userSex.equals("男")){
	                    user.setUserSex(0);
	                }else if(userSex.equals("女")){
	                    user.setUserSex(1);
	                }else{
	                    user.setUserSex(0);
	                }
	            }
	            if (StringUtil.isNotEmpty(userPhone)) {
	                user.setUserPhone(userPhone);
	            }
	            if (StringUtil.isNotEmpty(userMark)) {
	                user.setUserMark(userMark);
	            }
	            if (StringUtil.isNotEmpty(userMark1)) {
	                user.setUserMark1(userMark1);
	            }
	            if (StringUtil.isNotEmpty(userMark2)) {
	                user.setUserMark2(userMark2);
	            }
	            int total = userService.queryUsers(null, userName, 0, 0, null, null, null, null).size();
	            if (total == 0) {
					Date date = new Date();
					user.setUserDate(date);
					user.setUserType(0);
					user.setUserType2(0);
					userService.save(user);
	            }
	        }
	        return Response.success();
	    } catch (Exception e) {
	        e.printStackTrace();
	        return Response.error(204,"服务器错误");
	    }
	}
}
