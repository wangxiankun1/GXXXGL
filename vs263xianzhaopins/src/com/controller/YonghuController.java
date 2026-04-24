package com.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.model.*;
import com.service.*;
import com.util.*;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
//导入导出

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

@Controller
public class YonghuController {
	@Autowired
	private YonghuService yonghuService;
	@Autowired
	private UserService userService;
	@Autowired
	private BumenService bumenService;
	@Autowired
	private BuyuanService buyuanService;
	@Autowired
	private BuzhiService buzhiService;

	/***上传导入开始***/
	private InputStream excelFile;

	public InputStream getExcelFile() {
		return excelFile;
	}
	/***上传导入结束***/

	//获取列表，搜索条件，返回数据
	@ResponseBody
	@RequestMapping("/getYonghus")
	public Response getYonghus(@RequestBody Object req,HttpServletRequest request,
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
		String yonghuName = map.get("yonghuName");
		String yonghuXingming = map.get("yonghuXingming");
		String yonghuPhone = map.get("yonghuPhone");
		String yonghuId = map.get("yonghuId");
		String yonghuType = map.get("yonghuType");
		String yonghuType1 = map.get("yonghuType1");
		String yonghuType2 = map.get("yonghuType2");
		String yonghuMinzu = map.get("yonghuMinzu");
		String yonghuSex = map.get("yonghuSex");
		String userId = map.get("userId");
		String bumenId = map.get("bumenId");
		String buyuanId = map.get("buyuanId");
		String buzhiId = map.get("buzhiId");
		String sdate = map.get("sdate");
		String edate = map.get("edate");
		String sdate1 = map.get("sdate1");
		String edate1 = map.get("edate1");
		//初始化
		Yonghu yonghu = new Yonghu();
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
			if (StringUtil.isNotEmpty(yonghuPhone)) {
				yonghu.setYonghuPhone(yonghuPhone);
			}
			if (StringUtil.isNotEmpty(yonghuXingming)) {
				yonghu.setYonghuXingming(yonghuXingming);
			}
			if (StringUtil.isNotEmpty(yonghuName)) {
				yonghu.setYonghuName(yonghuName);
			}
			if (StringUtil.isNotEmpty(yonghuMinzu)) {
				yonghu.setYonghuMinzu(yonghuMinzu);
			}
			if (StringUtil.isNotEmpty(yonghuId)) {
				yonghu.setYonghuId(Integer.parseInt(yonghuId));
			}
			if (StringUtil.isNotEmpty(yonghuType)) {
				yonghu.setYonghuType(Integer.parseInt(yonghuType));
			}
			if (StringUtil.isNotEmpty(yonghuType1)) {
				yonghu.setYonghuType1(Integer.parseInt(yonghuType1));
			}
			if (StringUtil.isNotEmpty(yonghuType2)) {
				yonghu.setYonghuType2(Integer.parseInt(yonghuType2));
			}
			if (StringUtil.isNotEmpty(yonghuSex)) {
				yonghu.setYonghuSex(Integer.parseInt(yonghuSex));
			}
			if (StringUtil.isNotEmpty(userId)) {
				yonghu.setUserId(Integer.parseInt(userId));
			}
			if (StringUtil.isNotEmpty(buyuanId)) {
				yonghu.setBuyuanId(Integer.parseInt(buyuanId));
			}
			if (StringUtil.isNotEmpty(buzhiId)) {
				yonghu.setBuzhiId(Integer.parseInt(buzhiId));
			}
			if (StringUtil.isNotEmpty(bumenId)) {
				yonghu.setBumenId(Integer.parseInt(bumenId));
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
			int total = yonghuService.queryYonghus(yonghu,null, 0,0, sdate, edate, sdate1, edate1).size();
			//返回页面（列表、总数、行数）
			return Response.success(yonghuService.queryYonghus(yonghu,null, pageBean.getStart(), pageBean.getRows(), sdate, edate, sdate1, edate1),total,Integer.parseInt(rows));
		} catch (Exception e) {
			//e.printStackTrace();
			return Response.error(204,"服务器错误");
		}
	}

	//添加修改同样一个方法，如果id为空就是新增，如果id不为空就会加载原有数据，然后更新数据
	@ResponseBody
	@RequestMapping("/addYonghu")
	public Response addYonghu(@RequestBody Object req,HttpServletRequest request, HttpServletResponse response)
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
			String yonghuName = map.get("yonghuName");
			String yonghuPassword = map.get("yonghuPassword");
			String yonghuAge = map.get("yonghuAge");
			String yonghuXingming = map.get("yonghuXingming");
			String yonghuSex = map.get("yonghuSex");
			String yonghuPhone = map.get("yonghuPhone");
			String yonghuMark = map.get("yonghuMark");
			String yonghuMark1 = map.get("yonghuMark1");
			String yonghuMark2 = map.get("yonghuMark2");
			String yonghuMark3 = map.get("yonghuMark3");
			String yonghuMark4 = map.get("yonghuMark4");
			String yonghuDate = map.get("yonghuDate");
			String yonghuDate1 = map.get("yonghuDate1");
			String yonghuDate2 = map.get("yonghuDate2");
			String yonghuType = map.get("yonghuType");
			String yonghuType1 = map.get("yonghuType1");
			String yonghuType2 = map.get("yonghuType2");
			String yonghuZong = map.get("yonghuZong");
			String yonghuZong1 = map.get("yonghuZong1");
			String yonghuZong2 = map.get("yonghuZong2");
			String yonghuDouble = map.get("yonghuDouble");
			String yonghuDouble1 = map.get("yonghuDouble1");
			String yonghuDouble2 = map.get("yonghuDouble2");
			String userId = map.get("userId");
			String bumenId = map.get("bumenId");
			String buyuanId = map.get("buyuanId");
			String buzhiId = map.get("buzhiId");
			String yonghuImg = map.get("yonghuImg");
			String yonghuId = map.get("yonghuId");
			//model初始化
			Yonghu yonghu = new Yonghu();
			//判断id是否为空，如果不为空则加载原有的model
			if (StringUtil.isNotEmpty(yonghuId)) {
				yonghu = yonghuService.getYonghu(Integer.parseInt(yonghuId));
			}
			/****初始化参数赋值****/
			if (StringUtil.isNotEmpty(yonghuImg)) {
				String str[] = yonghuImg.split("/");
				yonghuImg = str[str.length-1];
				yonghu.setYonghuImg(request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/file/" + yonghuImg);
				yonghu.setYonghuImgName(request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/downFile?filename=" + yonghuImg);
			}
			if (StringUtil.isNotEmpty(yonghuName)) {
				yonghu.setYonghuName(yonghuName);
			}
			if (StringUtil.isNotEmpty(yonghuPassword)) {
				yonghu.setYonghuPassword(yonghuPassword);
			}
			if (StringUtil.isNotEmpty(yonghuAge)) {
				yonghu.setYonghuAge(Integer.parseInt(yonghuAge));
			}
			if (StringUtil.isNotEmpty(yonghuXingming)) {
				yonghu.setYonghuXingming(yonghuXingming);
			}
			if (StringUtil.isNotEmpty(yonghuSex)) {
				yonghu.setYonghuSex(Integer.parseInt(yonghuSex));
			}
			if (StringUtil.isNotEmpty(yonghuPhone)) {
				yonghu.setYonghuPhone(yonghuPhone);
			}
			if (StringUtil.isNotEmpty(yonghuMark)) {
				yonghu.setYonghuMark(yonghuMark);
			}
			if (StringUtil.isNotEmpty(yonghuMark1)) {
				yonghu.setYonghuMark1(yonghuMark1);
			}
			if (StringUtil.isNotEmpty(yonghuMark2)) {
				yonghu.setYonghuMark2(yonghuMark2);
			}
			if (StringUtil.isNotEmpty(yonghuMark3)) {
				yonghu.setYonghuMark3(yonghuMark3);
			}
			if (StringUtil.isNotEmpty(yonghuMark4)) {
				yonghu.setYonghuMark4(yonghuMark4);
			}
			if (StringUtil.isNotEmpty(yonghuDate)) {
				yonghu.setYonghuDate(DateUtil.formatString(yonghuDate,
						"yyyy-MM-dd HH:mm:ss"));
			}
			if (StringUtil.isNotEmpty(yonghuDate1)) {
				yonghu.setYonghuDate1(DateUtil.formatString(yonghuDate1,
						"yyyy-MM-dd HH:mm:ss"));
			}
			if (StringUtil.isNotEmpty(yonghuDate2)) {
				yonghu.setYonghuDate2(DateUtil.formatString(yonghuDate2,
						"yyyy-MM-dd HH:mm:ss"));
			}
			if (StringUtil.isNotEmpty(yonghuType)) {
				yonghu.setYonghuType(Integer.parseInt(yonghuType));
			}
			if (StringUtil.isNotEmpty(yonghuType1)) {
				yonghu.setYonghuType1(Integer.parseInt(yonghuType1));
			}
			if (StringUtil.isNotEmpty(yonghuType2)) {
				yonghu.setYonghuType2(Integer.parseInt(yonghuType2));
			}
			if (StringUtil.isNotEmpty(yonghuZong)) {
				yonghu.setYonghuZong(Integer.parseInt(yonghuZong));
			}
			if (StringUtil.isNotEmpty(yonghuZong1)) {
				yonghu.setYonghuZong1(Integer.parseInt(yonghuZong1));
			}
			if (StringUtil.isNotEmpty(yonghuZong2)) {
				yonghu.setYonghuZong2(Integer.parseInt(yonghuZong2));
			}
			if (StringUtil.isNotEmpty(yonghuDouble)) {
				yonghu.setYonghuDouble(Double.parseDouble(yonghuDouble));
			}
			if (StringUtil.isNotEmpty(yonghuDouble1)) {
				yonghu.setYonghuDouble1(Double.parseDouble(yonghuDouble1));
			}
			if (StringUtil.isNotEmpty(yonghuDouble2)) {
				yonghu.setYonghuDouble2(Double.parseDouble(yonghuDouble2));
			}
			if (StringUtil.isNotEmpty(userId)) {
				yonghu.setUserId(Integer.parseInt(userId));
				User user = new User();
				user = userService.getUser(Integer.parseInt(userId));
				yonghu.setUserName(user.getUserXingming());
				yonghu.setBumenId(user.getBumenId());
				yonghu.setBumenName(user.getBumenName());
				yonghu.setBuyuanId(user.getBuyuanId());
				yonghu.setBuyuanName(user.getBuyuanName());
				yonghu.setBuzhiId(user.getBuzhiId());
				yonghu.setBuzhiName(user.getBuzhiName());
			}
			if (StringUtil.isNotEmpty(buzhiId)) {
				yonghu.setBuzhiId(Integer.parseInt(buzhiId));
				Buzhi buzhi = new Buzhi();
				buzhi = buzhiService.getBuzhi(Integer.parseInt(buzhiId));
				yonghu.setBuzhiName(buzhi.getBuzhiName());
				yonghu.setBumenId(buzhi.getBumenId());
				yonghu.setBumenName(buzhi.getBumenName());
				yonghu.setBuyuanId(buzhi.getBuyuanId());
				yonghu.setBuyuanName(buzhi.getBuyuanName());
			}
			if (StringUtil.isNotEmpty(bumenId)) {
				yonghu.setBumenId(Integer.parseInt(bumenId));
				Bumen bumen = new Bumen();
				bumen = bumenService.getBumen(Integer.parseInt(bumenId));
				yonghu.setBumenName(bumen.getBumenName());
				yonghu.setBuyuanId(bumen.getBuyuanId());
				yonghu.setBuyuanName(bumen.getBuyuanName());
			}
			if (StringUtil.isNotEmpty(buyuanId)) {
				yonghu.setBuyuanId(Integer.parseInt(buyuanId));
				Buyuan buyuan = new Buyuan();
				buyuan = buyuanService.getBuyuan(Integer.parseInt(buyuanId));
				yonghu.setBuyuanName(buyuan.getBuyuanName());
			}
			/****初始化参数赋值结束****/
			//id不为空，修改（更改状态等）；Id为空就是增加；然后返回结果
			if (StringUtil.isNotEmpty(yonghuId)) {
				yonghuService.modifyYonghu(yonghu);
				return Response.success();
			} else {
				int total = yonghuService.queryYonghus(null, yonghuName, 0, 0, null, null, null, null).size();
				if (total==0) {
					Date date = new Date();
					yonghu.setYonghuDate(date);
					yonghu.setYonghuType(0);
					yonghuService.save(yonghu);
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
	@RequestMapping("/mimaYonghu")
	public Response mimaYonghu(@RequestBody Object req,HttpServletRequest request, HttpServletResponse response)
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
		String yonghuId = (String) request.getParameter("yonghuId");
		String yonghuPassword = map.get("yonghuPassword");
		String yonghuPassword1 = map.get("yonghuPassword1");
		Yonghu yonghu = new Yonghu();
		try {
			yonghu = yonghuService.getYonghu(Integer.parseInt(yonghuId));
			if (!yonghu.getYonghuPassword().equals(yonghuPassword)) {
				return Response.error(202,"原密码错误，请重新输入");
			}else{
				yonghu.setYonghuPassword(yonghuPassword1);
				yonghuService.modifyYonghu(yonghu);
				return Response.success();
			}
		} catch (Exception e) {
			//e.printStackTrace();
			return Response.error(202,"系统错误");
		}
	}

	//找回密码
	@ResponseBody
	@RequestMapping("/zhaohuiYonghu")
	public Response zhaohuiYonghu(@RequestBody Object req,HttpServletRequest request, HttpServletResponse response)
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
			String yonghuName = map.get("yonghuName");
			String yonghuPhone = map.get("yonghuPhone");
			Yonghu yonghu = new Yonghu();

			if (StringUtil.isNotEmpty(yonghuName)) {
				yonghu.setYonghuName(yonghuName);
			}
			List<Yonghu> yonghus = yonghuService.queryYonghus(yonghu, null, 0,0, null, null, null, null);
			if(yonghus.size()==0){
				return Response.error(202,"无此用户名请重新找回");
			}else{
				yonghu = yonghus.get(0);
				if(yonghu.getYonghuPhone().equals(yonghuPhone)){
					String yonghuPassword = yonghu.getYonghuPassword();
					return Response.error(202,"密码："+yonghuPassword);
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
	@RequestMapping("/zhuceYonghu")
	public Response zhuceYonghu(@RequestBody Object req,HttpServletRequest request, HttpServletResponse response)
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
			String yonghuName = map.get("yonghuName");
			String yonghuPassword = map.get("yonghuPassword");
			String yonghuAge = map.get("yonghuAge");
			String yonghuXingming = map.get("yonghuXingming");
			String yonghuSex = map.get("yonghuSex");
			String yonghuPhone = map.get("yonghuPhone");
			String yonghuMark = map.get("yonghuMark");
			String yonghuMark1 = map.get("yonghuMark1");
			String yonghuMark2 = map.get("yonghuMark2");
			String yonghuMark3 = map.get("yonghuMark3");
			String yonghuMark4 = map.get("yonghuMark4");
			String yonghuDate = map.get("yonghuDate");
			String yonghuDate1 = map.get("yonghuDate1");
			String yonghuDate2 = map.get("yonghuDate2");
			String yonghuType = map.get("yonghuType");
			String yonghuType1 = map.get("yonghuType1");
			String yonghuType2 = map.get("yonghuType2");
			String yonghuZong = map.get("yonghuZong");
			String yonghuZong1 = map.get("yonghuZong1");
			String yonghuZong2 = map.get("yonghuZong2");
			String yonghuDouble = map.get("yonghuDouble");
			String yonghuDouble1 = map.get("yonghuDouble1");
			String yonghuDouble2 = map.get("yonghuDouble2");
			String userId = map.get("userId");
			String bumenId = map.get("bumenId");
			String buyuanId = map.get("buyuanId");
			String buzhiId = map.get("buzhiId");
			String yonghuId = map.get("yonghuId");
			Yonghu yonghu = new Yonghu();

			if (StringUtil.isNotEmpty(yonghuId)) {
				yonghu = yonghuService.getYonghu(Integer.parseInt(yonghuId));
			}
			if (StringUtil.isNotEmpty(yonghuName)) {
				yonghu.setYonghuName(yonghuName);
			}
			if (StringUtil.isNotEmpty(yonghuPassword)) {
				yonghu.setYonghuPassword(yonghuPassword);
			}
			if (StringUtil.isNotEmpty(yonghuAge)) {
				yonghu.setYonghuAge(Integer.parseInt(yonghuAge));
			}
			if (StringUtil.isNotEmpty(yonghuXingming)) {
				yonghu.setYonghuXingming(yonghuXingming);
			}
			if (StringUtil.isNotEmpty(yonghuSex)) {
				yonghu.setYonghuSex(Integer.parseInt(yonghuSex));
			}
			if (StringUtil.isNotEmpty(yonghuPhone)) {
				yonghu.setYonghuPhone(yonghuPhone);
			}
			if (StringUtil.isNotEmpty(yonghuMark)) {
				yonghu.setYonghuMark(yonghuMark);
			}
			if (StringUtil.isNotEmpty(yonghuMark1)) {
				yonghu.setYonghuMark1(yonghuMark1);
			}
			if (StringUtil.isNotEmpty(yonghuMark2)) {
				yonghu.setYonghuMark2(yonghuMark2);
			}
			if (StringUtil.isNotEmpty(yonghuMark3)) {
				yonghu.setYonghuMark3(yonghuMark3);
			}
			if (StringUtil.isNotEmpty(yonghuMark4)) {
				yonghu.setYonghuMark4(yonghuMark4);
			}
			if (StringUtil.isNotEmpty(yonghuDate)) {
				yonghu.setYonghuDate(DateUtil.formatString(yonghuDate,
						"yyyy-MM-dd HH:mm:ss"));
			}
			if (StringUtil.isNotEmpty(yonghuDate1)) {
				yonghu.setYonghuDate1(DateUtil.formatString(yonghuDate1,
						"yyyy-MM-dd HH:mm:ss"));
			}
			if (StringUtil.isNotEmpty(yonghuDate2)) {
				yonghu.setYonghuDate2(DateUtil.formatString(yonghuDate2,
						"yyyy-MM-dd HH:mm:ss"));
			}
			if (StringUtil.isNotEmpty(yonghuType)) {
				yonghu.setYonghuType(Integer.parseInt(yonghuType));
			}
			if (StringUtil.isNotEmpty(yonghuType1)) {
				yonghu.setYonghuType1(Integer.parseInt(yonghuType1));
			}
			if (StringUtil.isNotEmpty(yonghuType2)) {
				yonghu.setYonghuType2(Integer.parseInt(yonghuType2));
			}
			if (StringUtil.isNotEmpty(yonghuZong)) {
				yonghu.setYonghuZong(Integer.parseInt(yonghuZong));
			}
			if (StringUtil.isNotEmpty(yonghuZong1)) {
				yonghu.setYonghuZong1(Integer.parseInt(yonghuZong1));
			}
			if (StringUtil.isNotEmpty(yonghuZong2)) {
				yonghu.setYonghuZong2(Integer.parseInt(yonghuZong2));
			}
			if (StringUtil.isNotEmpty(yonghuDouble)) {
				yonghu.setYonghuDouble(Double.parseDouble(yonghuDouble));
			}
			if (StringUtil.isNotEmpty(yonghuDouble1)) {
				yonghu.setYonghuDouble1(Double.parseDouble(yonghuDouble1));
			}
			if (StringUtil.isNotEmpty(yonghuDouble2)) {
				yonghu.setYonghuDouble2(Double.parseDouble(yonghuDouble2));
			}
			if (StringUtil.isNotEmpty(userId)) {
				yonghu.setUserId(Integer.parseInt(userId));
				User user = new User();
				user = userService.getUser(Integer.parseInt(userId));
				yonghu.setUserName(user.getUserXingming());
				yonghu.setBumenId(user.getBumenId());
				yonghu.setBumenName(user.getBumenName());
				yonghu.setBuyuanId(user.getBuyuanId());
				yonghu.setBuyuanName(user.getBuyuanName());
				yonghu.setBuzhiId(user.getBuzhiId());
				yonghu.setBuzhiName(user.getBuzhiName());
			}
			if (StringUtil.isNotEmpty(buzhiId)) {
				yonghu.setBuzhiId(Integer.parseInt(buzhiId));
				Buzhi buzhi = new Buzhi();
				buzhi = buzhiService.getBuzhi(Integer.parseInt(buzhiId));
				yonghu.setBuzhiName(buzhi.getBuzhiName());
				yonghu.setBumenId(buzhi.getBumenId());
				yonghu.setBumenName(buzhi.getBumenName());
				yonghu.setBuyuanId(buzhi.getBuyuanId());
				yonghu.setBuyuanName(buzhi.getBuyuanName());
			}
			if (StringUtil.isNotEmpty(bumenId)) {
				yonghu.setBumenId(Integer.parseInt(bumenId));
				Bumen bumen = new Bumen();
				bumen = bumenService.getBumen(Integer.parseInt(bumenId));
				yonghu.setBumenName(bumen.getBumenName());
				yonghu.setBuyuanId(bumen.getBuyuanId());
				yonghu.setBuyuanName(bumen.getBuyuanName());
			}
			if (StringUtil.isNotEmpty(buyuanId)) {
				yonghu.setBuyuanId(Integer.parseInt(buyuanId));
				Buyuan buyuan = new Buyuan();
				buyuan = buyuanService.getBuyuan(Integer.parseInt(buyuanId));
				yonghu.setBuyuanName(buyuan.getBuyuanName());
			}
			int total = yonghuService.queryYonghus(null, yonghuName, 0, 0, null, null, null, null).size();
			if (total==0) {
				Date date = new Date();
				yonghu.setYonghuDate(date);
				yonghu.setYonghuZong(0);
				yonghu.setYonghuType(0);
				yonghuService.save(yonghu);
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
	@RequestMapping("/deleteYonghu")
	public Response deleteYonghu(@RequestBody Object req,HttpServletRequest request, HttpServletResponse response)
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
				yonghuService.deleteYonghu(Integer.parseInt(str[i]));
			}
			return Response.success();
		} catch (Exception e) {
			//e.printStackTrace();
			return Response.error(204,"服务器错误");
		}
	}

	//下拉列表参数，跟搜索基本上一样，但是返回的是所有值，不分页
	@ResponseBody
	@RequestMapping("/yonghuComboList")
	public Response yonghuComboList(@RequestBody Object req,HttpServletRequest request, HttpServletResponse response)
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
		String yonghuName = map.get("yonghuName");
		String yonghuXingming = map.get("yonghuXingming");
		String yonghuId = map.get("yonghuId");
		String yonghuType = map.get("yonghuType");
		String yonghuType1 = map.get("yonghuType1");
		String yonghuType2 = map.get("yonghuType2");
		String yonghuMinzu = map.get("yonghuMinzu");
		String yonghuSex = map.get("yonghuSex");
		String userId = map.get("userId");
		String bumenId = map.get("bumenId");
		String buyuanId = map.get("buyuanId");
		String buzhiId = map.get("buzhiId");
		Yonghu yonghu = new Yonghu();
		try {
			if (StringUtil.isNotEmpty(yonghuXingming)) {
				yonghu.setYonghuXingming(yonghuXingming);
			}
			if (StringUtil.isNotEmpty(yonghuName)) {
				yonghu.setYonghuName(yonghuName);
			}
			if (StringUtil.isNotEmpty(yonghuMinzu)) {
				yonghu.setYonghuMinzu(yonghuMinzu);
			}
			if (StringUtil.isNotEmpty(yonghuId)) {
				yonghu.setYonghuId(Integer.parseInt(yonghuId));
			}
			if (StringUtil.isNotEmpty(yonghuType)) {
				yonghu.setYonghuType(Integer.parseInt(yonghuType));
			}
			if (StringUtil.isNotEmpty(yonghuType1)) {
				yonghu.setYonghuType1(Integer.parseInt(yonghuType1));
			}
			if (StringUtil.isNotEmpty(yonghuType2)) {
				yonghu.setYonghuType2(Integer.parseInt(yonghuType2));
			}
			if (StringUtil.isNotEmpty(yonghuSex)) {
				yonghu.setYonghuSex(Integer.parseInt(yonghuSex));
			}
			if (StringUtil.isNotEmpty(userId)) {
				yonghu.setUserId(Integer.parseInt(userId));
			}
			if (StringUtil.isNotEmpty(buyuanId)) {
				yonghu.setBuyuanId(Integer.parseInt(buyuanId));
			}
			if (StringUtil.isNotEmpty(buzhiId)) {
				yonghu.setBuzhiId(Integer.parseInt(buzhiId));
			}
			if (StringUtil.isNotEmpty(bumenId)) {
				yonghu.setBumenId(Integer.parseInt(bumenId));
			}
			return Response.success(yonghuService.queryYonghus(yonghu,null, 0,0, null, null, null, null));
		} catch (Exception e) {
			//e.printStackTrace();
			return Response.error(204,"服务器错误");
		}
	}

	//统计方法
	@ResponseBody
	@RequestMapping("/yonghuTongji")
	public Response yonghuTongji(@RequestBody Object req,HttpServletRequest request, HttpServletResponse response)
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
		String yonghuName = map.get("yonghuName");
		String yonghuId=map.get("yonghuId");
		List<Integer> buzhiIds = new ArrayList<Integer>();
		List<Buzhi> buzhis = new ArrayList<Buzhi>();
		List<Yonghu> yonghus = new ArrayList<Yonghu>();
		Yonghu yonghu = new Yonghu();
		if (StringUtil.isNotEmpty(yonghuId)) {
			yonghu.setYonghuId(Integer.parseInt(yonghuId));
		}
		try {
			//统计图横（X）轴名称
			List<String> nameList = new ArrayList<String>();
			//统计图竖（Y）轴数值
	        List<Double> zongshuList = new ArrayList<Double>();
	        String tongjiYonghu = "[";
			buzhis = buzhiService.queryBuzhis(null, 0,0);
			for(int i=0;i<buzhis.size();i++){
				buzhiIds.add(buzhis.get(i).getBuzhiId());
				nameList.add(buzhis.get(i).getBuzhiName());
				Double yonghuZongshu = 0.0;
				if (StringUtil.isNotEmpty(yonghuName)) {
					yonghu.setYonghuName(yonghuName);
				}
				yonghu.setBuzhiId(buzhis.get(i).getBuzhiId());
				yonghus = yonghuService.queryYonghus(yonghu, null, 0,0,sdate,edate, null, null);
				yonghuZongshu = yonghuZongshu + yonghus.size();
				//统计数值
				zongshuList.add(yonghuZongshu);
				
				tongjiYonghu = tongjiYonghu + "{\"value\":" + yonghuZongshu + ",\"name\": '" + buzhis.get(i).getBuzhiName() + "' }";
				if(i==0){
					tongjiYonghu = tongjiYonghu + ",";
				}
			}
			tongjiYonghu = tongjiYonghu + "]";
			//转为map型
			Map<String,Object> tongjiMap=new HashMap<String,Object>();
			tongjiMap.put("value",zongshuList);
			tongjiMap.put("name",nameList);
			//转为json型
			JSONArray jsonArray = JSONArray.fromObject(tongjiYonghu);
			return Response.tongjiSuccess(tongjiMap,jsonArray);
		} catch (Exception e) {
			//e.printStackTrace();
			return Response.error(204,"服务器错误");
		}
	}

	//导入方法
	@ResponseBody
	@RequestMapping("/daoruYonghu")
	public Response daoruYonghu(@RequestBody Object req,HttpServletRequest request, HttpServletResponse response)
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
			String directory = "/file";
			String targetDirectory = request.getSession().getServletContext().getRealPath(directory);
	        String fileName = map.get("yonghuImg");
			File dir = new File(targetDirectory,fileName);
			excelFile = new FileInputStream(dir);
			List<List<String>> list = new ArrayList<List<String>>();
			list = ExcelUtil.jiexiExcel(excelFile);
			for (int i = 1; i < list.size(); i++) {
				List<String> row = list.get(i);
				String yonghuName = row.get(1);
				String yonghuDouble = row.get(2);
				String yonghuDouble1 = row.get(3);
				String yonghuZong = row.get(4);
				String yonghuMark = row.get(5);
				String buzhiId = row.get(6);
				Yonghu yonghu = new Yonghu();
				if (StringUtil.isNotEmpty(yonghuName)) {
					yonghu.setYonghuName(yonghuName);
				}
				if (StringUtil.isNotEmpty(yonghuMark)) {
					yonghu.setYonghuMark(yonghuMark);
				}
				if (StringUtil.isNotEmpty(yonghuZong)) {
					yonghu.setYonghuZong(Integer.parseInt(yonghuZong));
				}
				if (StringUtil.isNotEmpty(yonghuDouble)) {
					yonghu.setYonghuDouble(Double.parseDouble(yonghuDouble));
				}
				if (StringUtil.isNotEmpty(yonghuDouble1)) {
					yonghu.setYonghuDouble1(Double.parseDouble(yonghuDouble1));
				}
				if (StringUtil.isNotEmpty(buzhiId)) {
					yonghu.setBuzhiId(Integer.parseInt(buzhiId));
					Buzhi buzhi = new Buzhi();
					buzhi = buzhiService.getBuzhi(Integer.parseInt(buzhiId));
					yonghu.setBuzhiName(buzhi.getBuzhiName());
				}
				Date date = new Date();
				yonghu.setYonghuDate(date);
				yonghu.setYonghuType(0);
				yonghuService.save(yonghu);
			}
			return Response.success();
		} catch (Exception e) {
			//e.printStackTrace();
			return Response.error(204,"服务器错误");
		}
	}
}
