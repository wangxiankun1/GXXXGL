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
public class YyijianController {
	@Autowired
	private YyijianService yyijianService;
	@Autowired
	private YxtypeService yxtypeService;
	@Autowired
	private YonghuService yonghuService;
	@Autowired
	private UserService userService;

	/***上传导入开始***/
	private InputStream excelFile;

	public InputStream getExcelFile() {
		return excelFile;
	}
	/***上传导入结束***/

	@ResponseBody
	@RequestMapping("/getYyijians")
	public Response getYyijians(@RequestBody Object req,HttpServletRequest request,
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
		String yyijianName = map.get("yyijianName");
		String yyijianId = map.get("yyijianId");
		String yxtypeId = map.get("yxtypeId");
		String yyijianType = map.get("yyijianType");
		String yyijianType1 = map.get("yyijianType1");
		String yonghuId = map.get("yonghuId");
		String userId = map.get("userId");
		String bumenId = map.get("bumenId");
		String buyuanId = map.get("buyuanId");
		String buzhiId = map.get("buzhiId");
		String sdate = map.get("sdate");
		String edate = map.get("edate");
		//初始化
		Yyijian yyijian = new Yyijian();
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
			if (StringUtil.isNotEmpty(yyijianName)) {
				yyijian.setYyijianName(yyijianName);
			}
			if (StringUtil.isNotEmpty(yyijianId)) {
				yyijian.setYyijianId(Integer.parseInt(yyijianId));
			}
			if (StringUtil.isNotEmpty(yxtypeId)) {
				yyijian.setYxtypeId(Integer.parseInt(yxtypeId));
			}
			if (StringUtil.isNotEmpty(yyijianType)) {
				yyijian.setYyijianType(Integer.parseInt(yyijianType));
			}
			if (StringUtil.isNotEmpty(yyijianType1)) {
				yyijian.setYyijianType1(Integer.parseInt(yyijianType1));
			}
			if (StringUtil.isNotEmpty(yonghuId)) {
				yyijian.setYonghuId(Integer.parseInt(yonghuId));
			}
			if (StringUtil.isNotEmpty(userId)) {
				yyijian.setUserId(Integer.parseInt(userId));
			}
			if (StringUtil.isNotEmpty(bumenId)) {
				yyijian.setBumenId(Integer.parseInt(bumenId));
			}
			if (StringUtil.isNotEmpty(buyuanId)) {
				yyijian.setBuyuanId(Integer.parseInt(buyuanId));
			}
			if (StringUtil.isNotEmpty(buzhiId)) {
				yyijian.setBuzhiId(Integer.parseInt(buzhiId));
			}
			/****初始化复制，搜索参数结束****/
			//总数
			int total = yyijianService.queryYyijians(yyijian, 0,0, sdate, edate).size();
			//返回页面（列表、总数、行数）
			return Response.success(yyijianService.queryYyijians(yyijian, pageBean.getStart(), pageBean.getRows(), sdate, edate),total,Integer.parseInt(rows));
		} catch (Exception e) {
			//e.printStackTrace();
			return Response.error(204,"服务器错误");
		}
	}

	//添加修改同样一个方法，如果id为空就是新增，如果id不为空就会加载原有数据，然后更新数据
	@ResponseBody
	@RequestMapping("/addYyijian")
	public Response addYyijian(@RequestBody Object req,HttpServletRequest request, HttpServletResponse response)
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
			String yyijianName = map.get("yyijianName");
			String yyijianMark = map.get("yyijianMark");
			String yyijianMark1 = map.get("yyijianMark1");
			String yyijianMark2 = map.get("yyijianMark2");
			String yyijianDate = map.get("yyijianDate");
			String yyijianType = map.get("yyijianType");
			String yyijianType1 = map.get("yyijianType1");
			String yyijianType2 = map.get("yyijianType2");
			String yyijianZong = map.get("yyijianZong");
			String yyijianZong1 = map.get("yyijianZong1");
			String yyijianZong2 = map.get("yyijianZong2");
			String yyijianDouble = map.get("yyijianDouble");
			String yyijianDouble1 = map.get("yyijianDouble1");
			String yyijianDouble2 = map.get("yyijianDouble2");
			String yxtypeId = map.get("yxtypeId");
			String yonghuId = map.get("yonghuId");
			String userId = map.get("userId");
			String yyijianImg = map.get("yyijianImg");
			String yyijianId = map.get("yyijianId");
			//model初始化
			Yyijian yyijian = new Yyijian();
			//判断id是否为空，如果不为空则加载原有的model
			if (StringUtil.isNotEmpty(yyijianId)) {
				yyijian = yyijianService.getYyijian(Integer.parseInt(yyijianId));
			}
			/****初始化参数赋值****/
			if (StringUtil.isNotEmpty(yyijianImg)) {
				String str[] = yyijianImg.split("/");
				yyijianImg = str[str.length-1];
				yyijian.setYyijianImg(request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/file/" + yyijianImg);
				yyijian.setYyijianImgName(request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/downFile?filename=" + yyijianImg);
			}
			if (StringUtil.isNotEmpty(yyijianName)) {
				yyijian.setYyijianName(yyijianName);
			}
			if (StringUtil.isNotEmpty(yyijianMark)) {
				yyijian.setYyijianMark(yyijianMark);
			}
			if (StringUtil.isNotEmpty(yyijianMark1)) {
				yyijian.setYyijianMark1(yyijianMark1);
			}
			if (StringUtil.isNotEmpty(yyijianMark2)) {
				yyijian.setYyijianMark2(yyijianMark2);
			}
			if (StringUtil.isNotEmpty(yyijianDate)) {
				yyijian.setYyijianDate(DateUtil.formatString(yyijianDate,
						"yyyy-MM-dd hh:mm:ss"));
			}
			if (StringUtil.isNotEmpty(yyijianType)) {
				yyijian.setYyijianType(Integer.parseInt(yyijianType));
			}
			if (StringUtil.isNotEmpty(yyijianType1)) {
				yyijian.setYyijianType1(Integer.parseInt(yyijianType1));
			}
			if (StringUtil.isNotEmpty(yyijianType2)) {
				yyijian.setYyijianType2(Integer.parseInt(yyijianType2));
			}
			if (StringUtil.isNotEmpty(yyijianZong)) {
				yyijian.setYyijianZong(Integer.parseInt(yyijianZong));
			}
			if (StringUtil.isNotEmpty(yyijianZong1)) {
				yyijian.setYyijianZong1(Integer.parseInt(yyijianZong1));
			}
			if (StringUtil.isNotEmpty(yyijianZong2)) {
				yyijian.setYyijianZong2(Integer.parseInt(yyijianZong2));
			}
			if (StringUtil.isNotEmpty(yyijianDouble)) {
				yyijian.setYyijianDouble(Double.parseDouble(yyijianDouble));
			}
			if (StringUtil.isNotEmpty(yyijianDouble1)) {
				yyijian.setYyijianDouble1(Double.parseDouble(yyijianDouble1));
			}
			if (StringUtil.isNotEmpty(yyijianDouble2)) {
				yyijian.setYyijianDouble2(Double.parseDouble(yyijianDouble2));
			}
			if (StringUtil.isNotEmpty(yxtypeId)) {
				yyijian.setYxtypeId(Integer.parseInt(yxtypeId));
				Yxtype yxtype = new Yxtype();
				yxtype = yxtypeService.getYxtype(Integer.parseInt(yxtypeId));
				yyijian.setYxtypeName(yxtype.getYxtypeName());
			}
			if (StringUtil.isNotEmpty(yonghuId)) {
				yyijian.setYonghuId(Integer.parseInt(yonghuId));
				Yonghu yonghu = new Yonghu();
				yonghu = yonghuService.getYonghu(Integer.parseInt(yonghuId));
				yyijian.setYonghuName(yonghu.getYonghuXingming());
			}
			if (StringUtil.isNotEmpty(userId)) {
				yyijian.setUserId(Integer.parseInt(userId));
				User user = new User();
				user = userService.getUser(Integer.parseInt(userId));
				yyijian.setUserName(user.getUserXingming());
				yyijian.setBumenId(user.getBumenId());
				yyijian.setBumenName(user.getBumenName());
				yyijian.setBuyuanId(user.getBuyuanId());
				yyijian.setBuyuanName(user.getBuyuanName());
				yyijian.setBuzhiId(user.getBuzhiId());
				yyijian.setBuzhiName(user.getBuzhiName());
			}
			/****初始化参数赋值结束****/
			//id不为空，修改（更改状态等）；Id为空就是增加；然后返回结果
			if (StringUtil.isNotEmpty(yyijianId)) {
				yyijianService.modifyYyijian(yyijian);
				return Response.success();
			} else {
				Date date = new Date();
				yyijian.setYyijianDate(date);
				yyijian.setYyijianType(0);
				yyijianService.save(yyijian);
				return Response.success();
			}
		} catch (Exception e) {
			//e.printStackTrace();
			return Response.error(204,"服务器错误");
		}
	}

	//删除数据
	@ResponseBody
	@RequestMapping("/deleteYyijian")
	public Response deleteYyijian(@RequestBody Object req,HttpServletRequest request, HttpServletResponse response)
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
				yyijianService.deleteYyijian(Integer.parseInt(str[i]));
			}
			return Response.success();
		} catch (Exception e) {
			//e.printStackTrace();
			return Response.error(204,"服务器错误");
		}
	}

	//下拉列表参数，跟搜索基本上一样，但是返回的是所有值，不分页
	@ResponseBody
	@RequestMapping("/yyijianComboList")
	public Response yyijianComboList(@RequestBody Object req,HttpServletRequest request, HttpServletResponse response)
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
		String yyijianName = map.get("yyijianName");
		String yyijianId = map.get("yyijianId");
		String yxtypeId = map.get("yxtypeId");
		String yyijianType = map.get("yyijianType");
		String yyijianType1 = map.get("yyijianType1");
		String yonghuId = map.get("yonghuId");
		String userId = map.get("userId");
		String bumenId = map.get("bumenId");
		String buyuanId = map.get("buyuanId");
		String buzhiId = map.get("buzhiId");
		Yyijian yyijian = new Yyijian();
		try {
			if (StringUtil.isNotEmpty(yyijianName)) {
				yyijian.setYyijianName(yyijianName);
			}
			if (StringUtil.isNotEmpty(yyijianId)) {
				yyijian.setYyijianId(Integer.parseInt(yyijianId));
			}
			if (StringUtil.isNotEmpty(yxtypeId)) {
				yyijian.setYxtypeId(Integer.parseInt(yxtypeId));
			}
			if (StringUtil.isNotEmpty(yyijianType)) {
				yyijian.setYyijianType(Integer.parseInt(yyijianType));
			}
			if (StringUtil.isNotEmpty(yyijianType1)) {
				yyijian.setYyijianType1(Integer.parseInt(yyijianType1));
			}
			if (StringUtil.isNotEmpty(yonghuId)) {
				yyijian.setYonghuId(Integer.parseInt(yonghuId));
			}
			if (StringUtil.isNotEmpty(userId)) {
				yyijian.setUserId(Integer.parseInt(userId));
			}
			if (StringUtil.isNotEmpty(bumenId)) {
				yyijian.setBumenId(Integer.parseInt(bumenId));
			}
			if (StringUtil.isNotEmpty(buyuanId)) {
				yyijian.setBuyuanId(Integer.parseInt(buyuanId));
			}
			if (StringUtil.isNotEmpty(buzhiId)) {
				yyijian.setBuzhiId(Integer.parseInt(buzhiId));
			}
			return Response.success(yyijianService.queryYyijians(yyijian,0,0, null, null));
		} catch (Exception e) {
			//e.printStackTrace();
			return Response.error(204,"服务器错误");
		}
	}


	//统计方法
	@ResponseBody
	@RequestMapping("/yyijianTongji")
	public Response yyijianTongji(@RequestBody Object req,HttpServletRequest request, HttpServletResponse response)
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
		String yyijianName = map.get("yyijianName");
		String userId=map.get("userId");
		List<Integer> yxtypeIds = new ArrayList<Integer>();
		List<Yxtype> yxtypes = new ArrayList<Yxtype>();
		List<Yyijian> yyijians = new ArrayList<Yyijian>();
		Yyijian yyijian = new Yyijian();
		if (StringUtil.isNotEmpty(userId)) {
			yyijian.setUserId(Integer.parseInt(userId));
		}
		try {
			//统计图横（X）轴名称
			List<String> nameList = new ArrayList<String>();
			//统计图竖（Y）轴数值
	        List<Double> zongshuList = new ArrayList<Double>();
	        String tongjiYyijian = "[";
			yxtypes = yxtypeService.queryYxtypes(null, 0,0);
			for(int i=0;i<yxtypes.size();i++){
				yxtypeIds.add(yxtypes.get(i).getYxtypeId());
				nameList.add(yxtypes.get(i).getYxtypeName());
				Double yyijianZongshu = 0.0;
				if (StringUtil.isNotEmpty(yyijianName)) {
					yyijian.setYyijianName(yyijianName);
				}
				yyijian.setYxtypeId(yxtypes.get(i).getYxtypeId());
				yyijians = yyijianService.queryYyijians(yyijian, 0,0,sdate,edate);
				for(int j=0;j<yyijians.size();j++){
					yyijianZongshu = yyijianZongshu + yyijians.get(j).getYyijianZong();
				}
				//统计数值
				zongshuList.add(yyijianZongshu);
				
				tongjiYyijian = tongjiYyijian + "{\"value\":" + yyijianZongshu + ",\"name\": '" + yxtypes.get(i).getYxtypeName() + "' }";
				if(i==0){
					tongjiYyijian = tongjiYyijian + ",";
				}
			}
			tongjiYyijian = tongjiYyijian + "]";
			//转为map型
			Map<String,Object> tongjiMap=new HashMap<String,Object>();
			tongjiMap.put("value",zongshuList);
			tongjiMap.put("name",nameList);
			//转为json型
			JSONArray jsonArray = JSONArray.fromObject(tongjiYyijian);
			return Response.tongjiSuccess(tongjiMap,jsonArray);
		} catch (Exception e) {
			//e.printStackTrace();
			return Response.error(204,"服务器错误");
		}
	}

	//导入方法
	@ResponseBody
	@RequestMapping("/daoruYyijian")
	public Response daoruYyijian(@RequestBody Object req,HttpServletRequest request, HttpServletResponse response)
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
	        String fileName = map.get("yyijianImg");
			File dir = new File(targetDirectory,fileName);
			excelFile = new FileInputStream(dir);
			List<List<String>> list = new ArrayList<List<String>>();
			list = ExcelUtil.jiexiExcel(excelFile);
			for (int i = 1; i < list.size(); i++) {
				List<String> row = list.get(i);
				String yyijianName = row.get(1);
				String yyijianDouble = row.get(2);
				String yyijianDouble1 = row.get(3);
				String yyijianZong = row.get(4);
				String yyijianMark = row.get(5);
				String yxtypeId = row.get(6);
				Yyijian yyijian = new Yyijian();
				if (StringUtil.isNotEmpty(yyijianName)) {
					yyijian.setYyijianName(yyijianName);
				}
				if (StringUtil.isNotEmpty(yyijianMark)) {
					yyijian.setYyijianMark(yyijianMark);
				}
				if (StringUtil.isNotEmpty(yyijianZong)) {
					yyijian.setYyijianZong(Integer.parseInt(yyijianZong));
				}
				if (StringUtil.isNotEmpty(yyijianDouble)) {
					yyijian.setYyijianDouble(Double.parseDouble(yyijianDouble));
				}
				if (StringUtil.isNotEmpty(yyijianDouble1)) {
					yyijian.setYyijianDouble1(Double.parseDouble(yyijianDouble1));
				}
				if (StringUtil.isNotEmpty(yxtypeId)) {
					yyijian.setYxtypeId(Integer.parseInt(yxtypeId));
					Yxtype yxtype = new Yxtype();
					yxtype = yxtypeService.getYxtype(Integer.parseInt(yxtypeId));
					yyijian.setYxtypeName(yxtype.getYxtypeName());
				}
				Date date = new Date();
				yyijian.setYyijianDate(date);
				yyijian.setYyijianType(0);
				yyijianService.save(yyijian);
			}
			return Response.success();
		} catch (Exception e) {
			//e.printStackTrace();
			return Response.error(204,"服务器错误");
		}
	}
}
