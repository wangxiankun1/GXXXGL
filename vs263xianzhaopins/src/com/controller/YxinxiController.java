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
public class YxinxiController {
	@Autowired
	private YxinxiService yxinxiService;
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
	@RequestMapping("/getYxinxis")
	public Response getYxinxis(@RequestBody Object req,HttpServletRequest request,
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
		String yxinxiName = map.get("yxinxiName");
		String yxinxiId = map.get("yxinxiId");
		String yxtypeId = map.get("yxtypeId");
		String yxinxiType = map.get("yxinxiType");
		String yxinxiType1 = map.get("yxinxiType1");
		String yonghuId = map.get("yonghuId");
		String userId = map.get("userId");
		String bumenId = map.get("bumenId");
		String buyuanId = map.get("buyuanId");
		String buzhiId = map.get("buzhiId");
		String sdate = map.get("sdate");
		String edate = map.get("edate");
		//初始化
		Yxinxi yxinxi = new Yxinxi();
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
			if (StringUtil.isNotEmpty(yxinxiName)) {
				yxinxi.setYxinxiName(yxinxiName);
			}
			if (StringUtil.isNotEmpty(yxinxiId)) {
				yxinxi.setYxinxiId(Integer.parseInt(yxinxiId));
			}
			if (StringUtil.isNotEmpty(yxtypeId)) {
				yxinxi.setYxtypeId(Integer.parseInt(yxtypeId));
			}
			if (StringUtil.isNotEmpty(yxinxiType)) {
				yxinxi.setYxinxiType(Integer.parseInt(yxinxiType));
			}
			if (StringUtil.isNotEmpty(yxinxiType1)) {
				yxinxi.setYxinxiType1(Integer.parseInt(yxinxiType1));
			}
			if (StringUtil.isNotEmpty(yonghuId)) {
				yxinxi.setYonghuId(Integer.parseInt(yonghuId));
			}
			if (StringUtil.isNotEmpty(userId)) {
				yxinxi.setUserId(Integer.parseInt(userId));
			}
			if (StringUtil.isNotEmpty(bumenId)) {
				yxinxi.setBumenId(Integer.parseInt(bumenId));
			}
			if (StringUtil.isNotEmpty(buyuanId)) {
				yxinxi.setBuyuanId(Integer.parseInt(buyuanId));
			}
			if (StringUtil.isNotEmpty(buzhiId)) {
				yxinxi.setBuzhiId(Integer.parseInt(buzhiId));
			}
			/****初始化复制，搜索参数结束****/
			//总数
			int total = yxinxiService.queryYxinxis(yxinxi, 0,0, sdate, edate).size();
			//返回页面（列表、总数、行数）
			return Response.success(yxinxiService.queryYxinxis(yxinxi, pageBean.getStart(), pageBean.getRows(), sdate, edate),total,Integer.parseInt(rows));
		} catch (Exception e) {
			//e.printStackTrace();
			return Response.error(204,"服务器错误");
		}
	}

	//添加修改同样一个方法，如果id为空就是新增，如果id不为空就会加载原有数据，然后更新数据
	@ResponseBody
	@RequestMapping("/addYxinxi")
	public Response addYxinxi(@RequestBody Object req,HttpServletRequest request, HttpServletResponse response)
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
			String yxinxiName = map.get("yxinxiName");
			String yxinxiMark = map.get("yxinxiMark");
			String yxinxiMark1 = map.get("yxinxiMark1");
			String yxinxiMark2 = map.get("yxinxiMark2");
			String yxinxiDate = map.get("yxinxiDate");
			String yxinxiType = map.get("yxinxiType");
			String yxinxiType1 = map.get("yxinxiType1");
			String yxinxiType2 = map.get("yxinxiType2");
			String yxinxiZong = map.get("yxinxiZong");
			String yxinxiZong1 = map.get("yxinxiZong1");
			String yxinxiZong2 = map.get("yxinxiZong2");
			String yxinxiDouble = map.get("yxinxiDouble");
			String yxinxiDouble1 = map.get("yxinxiDouble1");
			String yxinxiDouble2 = map.get("yxinxiDouble2");
			String yxtypeId = map.get("yxtypeId");
			String yonghuId = map.get("yonghuId");
			String userId = map.get("userId");
			String yxinxiImg = map.get("yxinxiImg");
			String yxinxiId = map.get("yxinxiId");
			//model初始化
			Yxinxi yxinxi = new Yxinxi();
			//判断id是否为空，如果不为空则加载原有的model
			if (StringUtil.isNotEmpty(yxinxiId)) {
				yxinxi = yxinxiService.getYxinxi(Integer.parseInt(yxinxiId));
			}
			/****初始化参数赋值****/
			if (StringUtil.isNotEmpty(yxinxiImg)) {
				String str[] = yxinxiImg.split("/");
				yxinxiImg = str[str.length-1];
				yxinxi.setYxinxiImg(request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/file/" + yxinxiImg);
				yxinxi.setYxinxiImgName(request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/downFile?filename=" + yxinxiImg);
			}
			if (StringUtil.isNotEmpty(yxinxiName)) {
				yxinxi.setYxinxiName(yxinxiName);
			}
			if (StringUtil.isNotEmpty(yxinxiMark)) {
				yxinxi.setYxinxiMark(yxinxiMark);
			}
			if (StringUtil.isNotEmpty(yxinxiMark1)) {
				yxinxi.setYxinxiMark1(yxinxiMark1);
			}
			if (StringUtil.isNotEmpty(yxinxiMark2)) {
				yxinxi.setYxinxiMark2(yxinxiMark2);
			}
			if (StringUtil.isNotEmpty(yxinxiDate)) {
				yxinxi.setYxinxiDate(DateUtil.formatString(yxinxiDate,
						"yyyy-MM-dd hh:mm:ss"));
			}
			if (StringUtil.isNotEmpty(yxinxiType)) {
				yxinxi.setYxinxiType(Integer.parseInt(yxinxiType));
			}
			if (StringUtil.isNotEmpty(yxinxiType1)) {
				yxinxi.setYxinxiType1(Integer.parseInt(yxinxiType1));
			}
			if (StringUtil.isNotEmpty(yxinxiType2)) {
				yxinxi.setYxinxiType2(Integer.parseInt(yxinxiType2));
			}
			if (StringUtil.isNotEmpty(yxinxiZong)) {
				yxinxi.setYxinxiZong(Integer.parseInt(yxinxiZong));
			}
			if (StringUtil.isNotEmpty(yxinxiZong1)) {
				yxinxi.setYxinxiZong1(Integer.parseInt(yxinxiZong1));
			}
			if (StringUtil.isNotEmpty(yxinxiZong2)) {
				yxinxi.setYxinxiZong2(Integer.parseInt(yxinxiZong2));
			}
			if (StringUtil.isNotEmpty(yxinxiDouble)) {
				yxinxi.setYxinxiDouble(Double.parseDouble(yxinxiDouble));
			}
			if (StringUtil.isNotEmpty(yxinxiDouble1)) {
				yxinxi.setYxinxiDouble1(Double.parseDouble(yxinxiDouble1));
			}
			if (StringUtil.isNotEmpty(yxinxiDouble2)) {
				yxinxi.setYxinxiDouble2(Double.parseDouble(yxinxiDouble2));
			}
			if (StringUtil.isNotEmpty(yxtypeId)) {
				yxinxi.setYxtypeId(Integer.parseInt(yxtypeId));
				Yxtype yxtype = new Yxtype();
				yxtype = yxtypeService.getYxtype(Integer.parseInt(yxtypeId));
				yxinxi.setYxtypeName(yxtype.getYxtypeName());
			}
			if (StringUtil.isNotEmpty(yonghuId)) {
				yxinxi.setYonghuId(Integer.parseInt(yonghuId));
				Yonghu yonghu = new Yonghu();
				yonghu = yonghuService.getYonghu(Integer.parseInt(yonghuId));
				yxinxi.setYonghuName(yonghu.getYonghuXingming());
			}
			if (StringUtil.isNotEmpty(userId)) {
				yxinxi.setUserId(Integer.parseInt(userId));
				User user = new User();
				user = userService.getUser(Integer.parseInt(userId));
				yxinxi.setUserName(user.getUserXingming());
				yxinxi.setBumenId(user.getBumenId());
				yxinxi.setBumenName(user.getBumenName());
				yxinxi.setBuyuanId(user.getBuyuanId());
				yxinxi.setBuyuanName(user.getBuyuanName());
				yxinxi.setBuzhiId(user.getBuzhiId());
				yxinxi.setBuzhiName(user.getBuzhiName());
			}
			/****初始化参数赋值结束****/
			//id不为空，修改（更改状态等）；Id为空就是增加；然后返回结果
			if (StringUtil.isNotEmpty(yxinxiId)) {
				yxinxiService.modifyYxinxi(yxinxi);
				return Response.success();
			} else {
				Date date = new Date();
				yxinxi.setYxinxiDate(date);
				yxinxi.setYxinxiType(0);
				yxinxiService.save(yxinxi);
				return Response.success();
			}
		} catch (Exception e) {
			//e.printStackTrace();
			return Response.error(204,"服务器错误");
		}
	}

	//删除数据
	@ResponseBody
	@RequestMapping("/deleteYxinxi")
	public Response deleteYxinxi(@RequestBody Object req,HttpServletRequest request, HttpServletResponse response)
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
				yxinxiService.deleteYxinxi(Integer.parseInt(str[i]));
			}
			return Response.success();
		} catch (Exception e) {
			//e.printStackTrace();
			return Response.error(204,"服务器错误");
		}
	}

	//下拉列表参数，跟搜索基本上一样，但是返回的是所有值，不分页
	@ResponseBody
	@RequestMapping("/yxinxiComboList")
	public Response yxinxiComboList(@RequestBody Object req,HttpServletRequest request, HttpServletResponse response)
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
		String yxinxiName = map.get("yxinxiName");
		String yxinxiId = map.get("yxinxiId");
		String yxtypeId = map.get("yxtypeId");
		String yxinxiType = map.get("yxinxiType");
		String yxinxiType1 = map.get("yxinxiType1");
		String yonghuId = map.get("yonghuId");
		String userId = map.get("userId");
		String bumenId = map.get("bumenId");
		String buyuanId = map.get("buyuanId");
		String buzhiId = map.get("buzhiId");
		Yxinxi yxinxi = new Yxinxi();
		try {
			if (StringUtil.isNotEmpty(yxinxiName)) {
				yxinxi.setYxinxiName(yxinxiName);
			}
			if (StringUtil.isNotEmpty(yxinxiId)) {
				yxinxi.setYxinxiId(Integer.parseInt(yxinxiId));
			}
			if (StringUtil.isNotEmpty(yxtypeId)) {
				yxinxi.setYxtypeId(Integer.parseInt(yxtypeId));
			}
			if (StringUtil.isNotEmpty(yxinxiType)) {
				yxinxi.setYxinxiType(Integer.parseInt(yxinxiType));
			}
			if (StringUtil.isNotEmpty(yxinxiType1)) {
				yxinxi.setYxinxiType1(Integer.parseInt(yxinxiType1));
			}
			if (StringUtil.isNotEmpty(yonghuId)) {
				yxinxi.setYonghuId(Integer.parseInt(yonghuId));
			}
			if (StringUtil.isNotEmpty(userId)) {
				yxinxi.setUserId(Integer.parseInt(userId));
			}
			if (StringUtil.isNotEmpty(bumenId)) {
				yxinxi.setBumenId(Integer.parseInt(bumenId));
			}
			if (StringUtil.isNotEmpty(buyuanId)) {
				yxinxi.setBuyuanId(Integer.parseInt(buyuanId));
			}
			if (StringUtil.isNotEmpty(buzhiId)) {
				yxinxi.setBuzhiId(Integer.parseInt(buzhiId));
			}
			return Response.success(yxinxiService.queryYxinxis(yxinxi,0,0, null, null));
		} catch (Exception e) {
			//e.printStackTrace();
			return Response.error(204,"服务器错误");
		}
	}


	//统计方法
	@ResponseBody
	@RequestMapping("/yxinxiTongji")
	public Response yxinxiTongji(@RequestBody Object req,HttpServletRequest request, HttpServletResponse response)
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
		String yxinxiName = map.get("yxinxiName");
		String userId=map.get("userId");
		List<Integer> yxtypeIds = new ArrayList<Integer>();
		List<Yxtype> yxtypes = new ArrayList<Yxtype>();
		List<Yxinxi> yxinxis = new ArrayList<Yxinxi>();
		Yxinxi yxinxi = new Yxinxi();
		if (StringUtil.isNotEmpty(userId)) {
			yxinxi.setUserId(Integer.parseInt(userId));
		}
		try {
			//统计图横（X）轴名称
			List<String> nameList = new ArrayList<String>();
			//统计图竖（Y）轴数值
	        List<Double> zongshuList = new ArrayList<Double>();
	        String tongjiYxinxi = "[";
			yxtypes = yxtypeService.queryYxtypes(null, 0,0);
			for(int i=0;i<yxtypes.size();i++){
				yxtypeIds.add(yxtypes.get(i).getYxtypeId());
				nameList.add(yxtypes.get(i).getYxtypeName());
				Double yxinxiZongshu = 0.0;
				if (StringUtil.isNotEmpty(yxinxiName)) {
					yxinxi.setYxinxiName(yxinxiName);
				}
				yxinxi.setYxtypeId(yxtypes.get(i).getYxtypeId());
				yxinxis = yxinxiService.queryYxinxis(yxinxi, 0,0,sdate,edate);
				for(int j=0;j<yxinxis.size();j++){
					yxinxiZongshu = yxinxiZongshu + yxinxis.get(j).getYxinxiZong();
				}
				//统计数值
				zongshuList.add(yxinxiZongshu);
				
				tongjiYxinxi = tongjiYxinxi + "{\"value\":" + yxinxiZongshu + ",\"name\": '" + yxtypes.get(i).getYxtypeName() + "' }";
				if(i==0){
					tongjiYxinxi = tongjiYxinxi + ",";
				}
			}
			tongjiYxinxi = tongjiYxinxi + "]";
			//转为map型
			Map<String,Object> tongjiMap=new HashMap<String,Object>();
			tongjiMap.put("value",zongshuList);
			tongjiMap.put("name",nameList);
			//转为json型
			JSONArray jsonArray = JSONArray.fromObject(tongjiYxinxi);
			return Response.tongjiSuccess(tongjiMap,jsonArray);
		} catch (Exception e) {
			//e.printStackTrace();
			return Response.error(204,"服务器错误");
		}
	}

	//导入方法
	@ResponseBody
	@RequestMapping("/daoruYxinxi")
	public Response daoruYxinxi(@RequestBody Object req,HttpServletRequest request, HttpServletResponse response)
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
	        String fileName = map.get("yxinxiImg");
			File dir = new File(targetDirectory,fileName);
			excelFile = new FileInputStream(dir);
			List<List<String>> list = new ArrayList<List<String>>();
			list = ExcelUtil.jiexiExcel(excelFile);
			for (int i = 1; i < list.size(); i++) {
				List<String> row = list.get(i);
				String yxinxiName = row.get(1);
				String yxinxiDouble = row.get(2);
				String yxinxiDouble1 = row.get(3);
				String yxinxiZong = row.get(4);
				String yxinxiMark = row.get(5);
				String yxtypeId = row.get(6);
				Yxinxi yxinxi = new Yxinxi();
				if (StringUtil.isNotEmpty(yxinxiName)) {
					yxinxi.setYxinxiName(yxinxiName);
				}
				if (StringUtil.isNotEmpty(yxinxiMark)) {
					yxinxi.setYxinxiMark(yxinxiMark);
				}
				if (StringUtil.isNotEmpty(yxinxiZong)) {
					yxinxi.setYxinxiZong(Integer.parseInt(yxinxiZong));
				}
				if (StringUtil.isNotEmpty(yxinxiDouble)) {
					yxinxi.setYxinxiDouble(Double.parseDouble(yxinxiDouble));
				}
				if (StringUtil.isNotEmpty(yxinxiDouble1)) {
					yxinxi.setYxinxiDouble1(Double.parseDouble(yxinxiDouble1));
				}
				if (StringUtil.isNotEmpty(yxtypeId)) {
					yxinxi.setYxtypeId(Integer.parseInt(yxtypeId));
					Yxtype yxtype = new Yxtype();
					yxtype = yxtypeService.getYxtype(Integer.parseInt(yxtypeId));
					yxinxi.setYxtypeName(yxtype.getYxtypeName());
				}
				Date date = new Date();
				yxinxi.setYxinxiDate(date);
				yxinxi.setYxinxiType(0);
				yxinxiService.save(yxinxi);
			}
			return Response.success();
		} catch (Exception e) {
			//e.printStackTrace();
			return Response.error(204,"服务器错误");
		}
	}
}
