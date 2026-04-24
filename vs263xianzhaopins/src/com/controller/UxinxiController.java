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

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

@Controller
public class UxinxiController {
	@Autowired
	private UxinxiService uxinxiService;
	@Autowired
	private UxtypeService uxtypeService;
	@Autowired
	private UserService userService;

	/***上传导入开始***/
	private InputStream excelFile;

	public InputStream getExcelFile() {
		return excelFile;
	}
	/***上传导入结束***/

	@ResponseBody
	@RequestMapping("/getUxinxis")
	public Response getUxinxis(@RequestBody Object req,HttpServletRequest request,
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
		String uxinxiName = map.get("uxinxiName");
		String uxinxiId = map.get("uxinxiId");
		String uxtypeId = map.get("uxtypeId");
		String uxinxiType = map.get("uxinxiType");
		String uxinxiType1 = map.get("uxinxiType1");
		String userId = map.get("userId");
		String bumenId = map.get("bumenId");
		String buyuanId = map.get("buyuanId");
		String buzhiId = map.get("buzhiId");
		String sdate = map.get("sdate");
		String edate = map.get("edate");
		//初始化
		Uxinxi uxinxi = new Uxinxi();
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
			if (StringUtil.isNotEmpty(uxinxiName)) {
				uxinxi.setUxinxiName(uxinxiName);
			}
			if (StringUtil.isNotEmpty(uxinxiId)) {
				uxinxi.setUxinxiId(Integer.parseInt(uxinxiId));
			}
			if (StringUtil.isNotEmpty(uxtypeId)) {
				uxinxi.setUxtypeId(Integer.parseInt(uxtypeId));
			}
			if (StringUtil.isNotEmpty(uxinxiType)) {
				uxinxi.setUxinxiType(Integer.parseInt(uxinxiType));
			}
			if (StringUtil.isNotEmpty(uxinxiType1)) {
				uxinxi.setUxinxiType1(Integer.parseInt(uxinxiType1));
			}
			if (StringUtil.isNotEmpty(userId)) {
				uxinxi.setUserId(Integer.parseInt(userId));
			}
			if (StringUtil.isNotEmpty(bumenId)) {
				uxinxi.setBumenId(Integer.parseInt(bumenId));
			}
			if (StringUtil.isNotEmpty(buyuanId)) {
				uxinxi.setBuyuanId(Integer.parseInt(buyuanId));
			}
			if (StringUtil.isNotEmpty(buzhiId)) {
				uxinxi.setBuzhiId(Integer.parseInt(buzhiId));
			}
			/****初始化复制，搜索参数结束****/
			//总数
			int total = uxinxiService.queryUxinxis(uxinxi, 0,0, sdate, edate).size();
			//返回页面（列表、总数、行数）
			return Response.success(uxinxiService.queryUxinxis(uxinxi, pageBean.getStart(), pageBean.getRows(), sdate, edate),total,Integer.parseInt(rows));
		} catch (Exception e) {
			//e.printStackTrace();
			return Response.error(204,"服务器错误");
		}
	}

	//添加修改同样一个方法，如果id为空就是新增，如果id不为空就会加载原有数据，然后更新数据
	@ResponseBody
	@RequestMapping("/addUxinxi")
	public Response addUxinxi(@RequestBody Object req,HttpServletRequest request, HttpServletResponse response)
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
			String uxinxiName = map.get("uxinxiName");
			String uxinxiMark = map.get("uxinxiMark");
			String uxinxiMark1 = map.get("uxinxiMark1");
			String uxinxiMark2 = map.get("uxinxiMark2");
			String uxinxiDate = map.get("uxinxiDate");
			String uxinxiType = map.get("uxinxiType");
			String uxinxiType1 = map.get("uxinxiType1");
			String uxinxiType2 = map.get("uxinxiType2");
			String uxinxiZong = map.get("uxinxiZong");
			String uxinxiZong1 = map.get("uxinxiZong1");
			String uxinxiZong2 = map.get("uxinxiZong2");
			String uxinxiDouble = map.get("uxinxiDouble");
			String uxinxiDouble1 = map.get("uxinxiDouble1");
			String uxinxiDouble2 = map.get("uxinxiDouble2");
			String uxtypeId = map.get("uxtypeId");
			String userId = map.get("userId");
			String uxinxiImg = map.get("uxinxiImg");
			String uxinxiId = map.get("uxinxiId");
			//model初始化
			Uxinxi uxinxi = new Uxinxi();
			//判断id是否为空，如果不为空则加载原有的model
			if (StringUtil.isNotEmpty(uxinxiId)) {
				uxinxi = uxinxiService.getUxinxi(Integer.parseInt(uxinxiId));
			}
			/****初始化参数赋值****/
			if (StringUtil.isNotEmpty(uxinxiImg)) {
				String str[] = uxinxiImg.split("/");
				uxinxiImg = str[str.length-1];
				uxinxi.setUxinxiImg(request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/file/" + uxinxiImg);
				uxinxi.setUxinxiImgName(request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/downFile?filename=" + uxinxiImg);
			}
			if (StringUtil.isNotEmpty(uxinxiName)) {
				uxinxi.setUxinxiName(uxinxiName);
			}
			if (StringUtil.isNotEmpty(uxinxiMark)) {
				uxinxi.setUxinxiMark(uxinxiMark);
			}
			if (StringUtil.isNotEmpty(uxinxiMark1)) {
				uxinxi.setUxinxiMark1(uxinxiMark1);
			}
			if (StringUtil.isNotEmpty(uxinxiMark2)) {
				uxinxi.setUxinxiMark2(uxinxiMark2);
			}
			if (StringUtil.isNotEmpty(uxinxiDate)) {
				uxinxi.setUxinxiDate(DateUtil.formatString(uxinxiDate,
						"yyyy-MM-dd HH:mm:ss"));
			}
			if (StringUtil.isNotEmpty(uxinxiType)) {
				uxinxi.setUxinxiType(Integer.parseInt(uxinxiType));
			}
			if (StringUtil.isNotEmpty(uxinxiType1)) {
				uxinxi.setUxinxiType1(Integer.parseInt(uxinxiType1));
			}
			if (StringUtil.isNotEmpty(uxinxiType2)) {
				uxinxi.setUxinxiType2(Integer.parseInt(uxinxiType2));
			}
			if (StringUtil.isNotEmpty(uxinxiZong)) {
				uxinxi.setUxinxiZong(Integer.parseInt(uxinxiZong));
			}
			if (StringUtil.isNotEmpty(uxinxiZong1)) {
				uxinxi.setUxinxiZong1(Integer.parseInt(uxinxiZong1));
			}
			if (StringUtil.isNotEmpty(uxinxiZong2)) {
				uxinxi.setUxinxiZong2(Integer.parseInt(uxinxiZong2));
			}
			if (StringUtil.isNotEmpty(uxinxiDouble)) {
				uxinxi.setUxinxiDouble(Double.parseDouble(uxinxiDouble));
			}
			if (StringUtil.isNotEmpty(uxinxiDouble1)) {
				uxinxi.setUxinxiDouble1(Double.parseDouble(uxinxiDouble1));
			}
			if (StringUtil.isNotEmpty(uxinxiDouble2)) {
				uxinxi.setUxinxiDouble2(Double.parseDouble(uxinxiDouble2));
			}
			if (StringUtil.isNotEmpty(uxtypeId)) {
				uxinxi.setUxtypeId(Integer.parseInt(uxtypeId));
				Uxtype uxtype = new Uxtype();
				uxtype = uxtypeService.getUxtype(Integer.parseInt(uxtypeId));
				uxinxi.setUxtypeName(uxtype.getUxtypeName());
			}
			if (StringUtil.isNotEmpty(userId)) {
				uxinxi.setUserId(Integer.parseInt(userId));
				User user = new User();
				user = userService.getUser(Integer.parseInt(userId));
				uxinxi.setUserName(user.getUserXingming());
				uxinxi.setBumenId(user.getBumenId());
				uxinxi.setBumenName(user.getBumenName());
				uxinxi.setBuyuanId(user.getBuyuanId());
				uxinxi.setBuyuanName(user.getBuyuanName());
				uxinxi.setBuzhiId(user.getBuzhiId());
				uxinxi.setBuzhiName(user.getBuzhiName());
			}
			/****初始化参数赋值结束****/
			//id不为空，修改（更改状态等）；Id为空就是增加；然后返回结果
			if (StringUtil.isNotEmpty(uxinxiId)) {
				uxinxiService.modifyUxinxi(uxinxi);
				return Response.success();
			} else {
				Date date = new Date();
				uxinxi.setUxinxiDate(date);
				uxinxi.setUxinxiType(0);
				uxinxiService.save(uxinxi);
				return Response.success();
			}
		} catch (Exception e) {
			//e.printStackTrace();
			return Response.error(204,"服务器错误");
		}
	}

	//删除数据
	@ResponseBody
	@RequestMapping("/deleteUxinxi")
	public Response deleteUxinxi(@RequestBody Object req,HttpServletRequest request, HttpServletResponse response)
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
				uxinxiService.deleteUxinxi(Integer.parseInt(str[i]));
			}
			return Response.success();
		} catch (Exception e) {
			//e.printStackTrace();
			return Response.error(204,"服务器错误");
		}
	}

	//下拉列表参数，跟搜索基本上一样，但是返回的是所有值，不分页
	@ResponseBody
	@RequestMapping("/uxinxiComboList")
	public Response uxinxiComboList(@RequestBody Object req,HttpServletRequest request, HttpServletResponse response)
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
		String uxinxiName = map.get("uxinxiName");
		String uxinxiId = map.get("uxinxiId");
		String uxtypeId = map.get("uxtypeId");
		String uxinxiType = map.get("uxinxiType");
		String uxinxiType1 = map.get("uxinxiType1");
		String userId = map.get("userId");
		String bumenId = map.get("bumenId");
		String buyuanId = map.get("buyuanId");
		String buzhiId = map.get("buzhiId");
		Uxinxi uxinxi = new Uxinxi();
		try {
			if (StringUtil.isNotEmpty(uxinxiName)) {
				uxinxi.setUxinxiName(uxinxiName);
			}
			if (StringUtil.isNotEmpty(uxinxiId)) {
				uxinxi.setUxinxiId(Integer.parseInt(uxinxiId));
			}
			if (StringUtil.isNotEmpty(uxtypeId)) {
				uxinxi.setUxtypeId(Integer.parseInt(uxtypeId));
			}
			if (StringUtil.isNotEmpty(uxinxiType)) {
				uxinxi.setUxinxiType(Integer.parseInt(uxinxiType));
			}
			if (StringUtil.isNotEmpty(uxinxiType1)) {
				uxinxi.setUxinxiType1(Integer.parseInt(uxinxiType1));
			}
			if (StringUtil.isNotEmpty(userId)) {
				uxinxi.setUserId(Integer.parseInt(userId));
			}
			if (StringUtil.isNotEmpty(bumenId)) {
				uxinxi.setBumenId(Integer.parseInt(bumenId));
			}
			if (StringUtil.isNotEmpty(buyuanId)) {
				uxinxi.setBuyuanId(Integer.parseInt(buyuanId));
			}
			if (StringUtil.isNotEmpty(buzhiId)) {
				uxinxi.setBuzhiId(Integer.parseInt(buzhiId));
			}
			return Response.success(uxinxiService.queryUxinxis(uxinxi,0,0, null, null));
		} catch (Exception e) {
			//e.printStackTrace();
			return Response.error(204,"服务器错误");
		}
	}


	//统计方法
	@ResponseBody
	@RequestMapping("/uxinxiTongji")
	public Response uxinxiTongji(@RequestBody Object req,HttpServletRequest request, HttpServletResponse response)
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
		String uxinxiName = map.get("uxinxiName");
		String userId=map.get("userId");
		List<Integer> uxtypeIds = new ArrayList<Integer>();
		List<Uxtype> uxtypes = new ArrayList<Uxtype>();
		List<Uxinxi> uxinxis = new ArrayList<Uxinxi>();
		Uxinxi uxinxi = new Uxinxi();
		if (StringUtil.isNotEmpty(userId)) {
			uxinxi.setUserId(Integer.parseInt(userId));
		}
		try {
			//统计图横（X）轴名称
			List<String> nameList = new ArrayList<String>();
			//统计图竖（Y）轴数值
	        List<Double> zongshuList = new ArrayList<Double>();
	        String tongjiUxinxi = "[";
			uxtypes = uxtypeService.queryUxtypes(null, 0,0);
			for(int i=0;i<uxtypes.size();i++){
				uxtypeIds.add(uxtypes.get(i).getUxtypeId());
				nameList.add(uxtypes.get(i).getUxtypeName());
				Double uxinxiZongshu = 0.0;
				if (StringUtil.isNotEmpty(uxinxiName)) {
					uxinxi.setUxinxiName(uxinxiName);
				}
				uxinxi.setUxinxiType(1);
				uxinxi.setUxtypeId(uxtypes.get(i).getUxtypeId());
				uxinxis = uxinxiService.queryUxinxis(uxinxi, 0,0,sdate,edate);
				uxinxiZongshu = uxinxiZongshu + uxinxis.size();
				//统计数值
				zongshuList.add(uxinxiZongshu);
				
				tongjiUxinxi = tongjiUxinxi + "{\"value\":" + uxinxiZongshu + ",\"name\": '" + uxtypes.get(i).getUxtypeName() + "' }";
				if(i<(uxtypes.size()-1)){
					tongjiUxinxi = tongjiUxinxi + ",";
				}
			}
			tongjiUxinxi = tongjiUxinxi + "]";
			//转为map型
			Map<String,Object> tongjiMap=new HashMap<String,Object>();
			tongjiMap.put("value",zongshuList);
			tongjiMap.put("name",nameList);
			//转为json型
			JSONArray jsonArray = JSONArray.fromObject(tongjiUxinxi);
			return Response.tongjiSuccess(tongjiMap,jsonArray);
		} catch (Exception e) {
			//e.printStackTrace();
			return Response.error(204,"服务器错误");
		}
	}

	//导入方法
	@ResponseBody
	@RequestMapping("/daoruUxinxi")
	public Response daoruUxinxi(@RequestBody Object req,HttpServletRequest request, HttpServletResponse response)
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
	        String fileName = map.get("uxinxiImg");
			File dir = new File(targetDirectory,fileName);
			excelFile = new FileInputStream(dir);
			List<List<String>> list = new ArrayList<List<String>>();
			list = ExcelUtil.jiexiExcel(excelFile);
			for (int i = 1; i < list.size(); i++) {
				List<String> row = list.get(i);
				String uxinxiName = row.get(1);
				String uxinxiDouble = row.get(2);
				String uxinxiDouble1 = row.get(3);
				String uxinxiZong = row.get(4);
				String uxinxiMark = row.get(5);
				String uxtypeId = row.get(6);
				Uxinxi uxinxi = new Uxinxi();
				if (StringUtil.isNotEmpty(uxinxiName)) {
					uxinxi.setUxinxiName(uxinxiName);
				}
				if (StringUtil.isNotEmpty(uxinxiMark)) {
					uxinxi.setUxinxiMark(uxinxiMark);
				}
				if (StringUtil.isNotEmpty(uxinxiZong)) {
					uxinxi.setUxinxiZong(Integer.parseInt(uxinxiZong));
				}
				if (StringUtil.isNotEmpty(uxinxiDouble)) {
					uxinxi.setUxinxiDouble(Double.parseDouble(uxinxiDouble));
				}
				if (StringUtil.isNotEmpty(uxinxiDouble1)) {
					uxinxi.setUxinxiDouble1(Double.parseDouble(uxinxiDouble1));
				}
				if (StringUtil.isNotEmpty(uxtypeId)) {
					uxinxi.setUxtypeId(Integer.parseInt(uxtypeId));
					Uxtype uxtype = new Uxtype();
					uxtype = uxtypeService.getUxtype(Integer.parseInt(uxtypeId));
					uxinxi.setUxtypeName(uxtype.getUxtypeName());
				}
				Date date = new Date();
				uxinxi.setUxinxiDate(date);
				uxinxi.setUxinxiType(0);
				uxinxiService.save(uxinxi);
			}
			return Response.success();
		} catch (Exception e) {
			//e.printStackTrace();
			return Response.error(204,"服务器错误");
		}
	}
}
