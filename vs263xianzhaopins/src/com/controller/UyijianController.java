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
public class UyijianController {
	@Autowired
	private UyijianService uyijianService;
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
	@RequestMapping("/getUyijians")
	public Response getUyijians(@RequestBody Object req,HttpServletRequest request,
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
		String uyijianName = map.get("uyijianName");
		String uyijianId = map.get("uyijianId");
		String uxtypeId = map.get("uxtypeId");
		String uyijianType = map.get("uyijianType");
		String uyijianType1 = map.get("uyijianType1");
		String userId = map.get("userId");
		String bumenId = map.get("bumenId");
		String buyuanId = map.get("buyuanId");
		String buzhiId = map.get("buzhiId");
		String sdate = map.get("sdate");
		String edate = map.get("edate");
		//初始化
		Uyijian uyijian = new Uyijian();
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
			if (StringUtil.isNotEmpty(uyijianName)) {
				uyijian.setUyijianName(uyijianName);
			}
			if (StringUtil.isNotEmpty(uyijianId)) {
				uyijian.setUyijianId(Integer.parseInt(uyijianId));
			}
			if (StringUtil.isNotEmpty(uxtypeId)) {
				uyijian.setUxtypeId(Integer.parseInt(uxtypeId));
			}
			if (StringUtil.isNotEmpty(uyijianType)) {
				uyijian.setUyijianType(Integer.parseInt(uyijianType));
			}
			if (StringUtil.isNotEmpty(uyijianType1)) {
				uyijian.setUyijianType1(Integer.parseInt(uyijianType1));
			}
			if (StringUtil.isNotEmpty(userId)) {
				uyijian.setUserId(Integer.parseInt(userId));
			}
			if (StringUtil.isNotEmpty(bumenId)) {
				uyijian.setBumenId(Integer.parseInt(bumenId));
			}
			if (StringUtil.isNotEmpty(buyuanId)) {
				uyijian.setBuyuanId(Integer.parseInt(buyuanId));
			}
			if (StringUtil.isNotEmpty(buzhiId)) {
				uyijian.setBuzhiId(Integer.parseInt(buzhiId));
			}
			/****初始化复制，搜索参数结束****/
			//总数
			int total = uyijianService.queryUyijians(uyijian, 0,0, sdate, edate).size();
			//返回页面（列表、总数、行数）
			return Response.success(uyijianService.queryUyijians(uyijian, pageBean.getStart(), pageBean.getRows(), sdate, edate),total,Integer.parseInt(rows));
		} catch (Exception e) {
			//e.printStackTrace();
			return Response.error(204,"服务器错误");
		}
	}

	//添加修改同样一个方法，如果id为空就是新增，如果id不为空就会加载原有数据，然后更新数据
	@ResponseBody
	@RequestMapping("/addUyijian")
	public Response addUyijian(@RequestBody Object req,HttpServletRequest request, HttpServletResponse response)
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
			String uyijianName = map.get("uyijianName");
			String uyijianMark = map.get("uyijianMark");
			String uyijianMark1 = map.get("uyijianMark1");
			String uyijianMark2 = map.get("uyijianMark2");
			String uyijianDate = map.get("uyijianDate");
			String uyijianType = map.get("uyijianType");
			String uyijianType1 = map.get("uyijianType1");
			String uyijianType2 = map.get("uyijianType2");
			String uyijianZong = map.get("uyijianZong");
			String uyijianZong1 = map.get("uyijianZong1");
			String uyijianZong2 = map.get("uyijianZong2");
			String uyijianDouble = map.get("uyijianDouble");
			String uyijianDouble1 = map.get("uyijianDouble1");
			String uyijianDouble2 = map.get("uyijianDouble2");
			String uxtypeId = map.get("uxtypeId");
			String userId = map.get("userId");
			String uyijianImg = map.get("uyijianImg");
			String uyijianId = map.get("uyijianId");
			//model初始化
			Uyijian uyijian = new Uyijian();
			//判断id是否为空，如果不为空则加载原有的model
			if (StringUtil.isNotEmpty(uyijianId)) {
				uyijian = uyijianService.getUyijian(Integer.parseInt(uyijianId));
			}
			/****初始化参数赋值****/
			if (StringUtil.isNotEmpty(uyijianImg)) {
				String str[] = uyijianImg.split("/");
				uyijianImg = str[str.length-1];
				uyijian.setUyijianImg(request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/file/" + uyijianImg);
				uyijian.setUyijianImgName(request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/downFile?filename=" + uyijianImg);
			}
			if (StringUtil.isNotEmpty(uyijianName)) {
				uyijian.setUyijianName(uyijianName);
			}
			if (StringUtil.isNotEmpty(uyijianMark)) {
				uyijian.setUyijianMark(uyijianMark);
			}
			if (StringUtil.isNotEmpty(uyijianMark1)) {
				uyijian.setUyijianMark1(uyijianMark1);
			}
			if (StringUtil.isNotEmpty(uyijianMark2)) {
				uyijian.setUyijianMark2(uyijianMark2);
			}
			if (StringUtil.isNotEmpty(uyijianDate)) {
				uyijian.setUyijianDate(DateUtil.formatString(uyijianDate,
						"yyyy-MM-dd HH:mm:ss"));
			}
			if (StringUtil.isNotEmpty(uyijianType)) {
				uyijian.setUyijianType(Integer.parseInt(uyijianType));
			}
			if (StringUtil.isNotEmpty(uyijianType1)) {
				uyijian.setUyijianType1(Integer.parseInt(uyijianType1));
			}
			if (StringUtil.isNotEmpty(uyijianType2)) {
				uyijian.setUyijianType2(Integer.parseInt(uyijianType2));
			}
			if (StringUtil.isNotEmpty(uyijianZong)) {
				uyijian.setUyijianZong(Integer.parseInt(uyijianZong));
			}
			if (StringUtil.isNotEmpty(uyijianZong1)) {
				uyijian.setUyijianZong1(Integer.parseInt(uyijianZong1));
			}
			if (StringUtil.isNotEmpty(uyijianZong2)) {
				uyijian.setUyijianZong2(Integer.parseInt(uyijianZong2));
			}
			if (StringUtil.isNotEmpty(uyijianDouble)) {
				uyijian.setUyijianDouble(Double.parseDouble(uyijianDouble));
			}
			if (StringUtil.isNotEmpty(uyijianDouble1)) {
				uyijian.setUyijianDouble1(Double.parseDouble(uyijianDouble1));
			}
			if (StringUtil.isNotEmpty(uyijianDouble2)) {
				uyijian.setUyijianDouble2(Double.parseDouble(uyijianDouble2));
			}
			if (StringUtil.isNotEmpty(uxtypeId)) {
				uyijian.setUxtypeId(Integer.parseInt(uxtypeId));
				Uxtype uxtype = new Uxtype();
				uxtype = uxtypeService.getUxtype(Integer.parseInt(uxtypeId));
				uyijian.setUxtypeName(uxtype.getUxtypeName());
			}
			if (StringUtil.isNotEmpty(userId)) {
				uyijian.setUserId(Integer.parseInt(userId));
				User user = new User();
				user = userService.getUser(Integer.parseInt(userId));
				uyijian.setUserName(user.getUserXingming());
				uyijian.setBumenId(user.getBumenId());
				uyijian.setBumenName(user.getBumenName());
				uyijian.setBuyuanId(user.getBuyuanId());
				uyijian.setBuyuanName(user.getBuyuanName());
				uyijian.setBuzhiId(user.getBuzhiId());
				uyijian.setBuzhiName(user.getBuzhiName());
			}
			/****初始化参数赋值结束****/
			//id不为空，修改（更改状态等）；Id为空就是增加；然后返回结果
			if (StringUtil.isNotEmpty(uyijianId)) {
				uyijianService.modifyUyijian(uyijian);
				return Response.success();
			} else {
				Date date = new Date();
				uyijian.setUyijianDate(date);
				uyijianService.save(uyijian);
				return Response.success();
			}
		} catch (Exception e) {
			//e.printStackTrace();
			return Response.error(204,"服务器错误");
		}
	}

	//删除数据
	@ResponseBody
	@RequestMapping("/deleteUyijian")
	public Response deleteUyijian(@RequestBody Object req,HttpServletRequest request, HttpServletResponse response)
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
				uyijianService.deleteUyijian(Integer.parseInt(str[i]));
			}
			return Response.success();
		} catch (Exception e) {
			//e.printStackTrace();
			return Response.error(204,"服务器错误");
		}
	}

	//下拉列表参数，跟搜索基本上一样，但是返回的是所有值，不分页
	@ResponseBody
	@RequestMapping("/uyijianComboList")
	public Response uyijianComboList(@RequestBody Object req,HttpServletRequest request, HttpServletResponse response)
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
		String uyijianName = map.get("uyijianName");
		String uyijianId = map.get("uyijianId");
		String uxtypeId = map.get("uxtypeId");
		String uyijianType = map.get("uyijianType");
		String uyijianType1 = map.get("uyijianType1");
		String userId = map.get("userId");
		String bumenId = map.get("bumenId");
		String buyuanId = map.get("buyuanId");
		String buzhiId = map.get("buzhiId");
		Uyijian uyijian = new Uyijian();
		try {
			if (StringUtil.isNotEmpty(uyijianName)) {
				uyijian.setUyijianName(uyijianName);
			}
			if (StringUtil.isNotEmpty(uyijianId)) {
				uyijian.setUyijianId(Integer.parseInt(uyijianId));
			}
			if (StringUtil.isNotEmpty(uxtypeId)) {
				uyijian.setUxtypeId(Integer.parseInt(uxtypeId));
			}
			if (StringUtil.isNotEmpty(uyijianType)) {
				uyijian.setUyijianType(Integer.parseInt(uyijianType));
			}
			if (StringUtil.isNotEmpty(uyijianType1)) {
				uyijian.setUyijianType1(Integer.parseInt(uyijianType1));
			}
			if (StringUtil.isNotEmpty(userId)) {
				uyijian.setUserId(Integer.parseInt(userId));
			}
			if (StringUtil.isNotEmpty(bumenId)) {
				uyijian.setBumenId(Integer.parseInt(bumenId));
			}
			if (StringUtil.isNotEmpty(buyuanId)) {
				uyijian.setBuyuanId(Integer.parseInt(buyuanId));
			}
			if (StringUtil.isNotEmpty(buzhiId)) {
				uyijian.setBuzhiId(Integer.parseInt(buzhiId));
			}
			return Response.success(uyijianService.queryUyijians(uyijian,0,0, null, null));
		} catch (Exception e) {
			//e.printStackTrace();
			return Response.error(204,"服务器错误");
		}
	}


	//统计方法
	@ResponseBody
	@RequestMapping("/uyijianTongji")
	public Response uyijianTongji(@RequestBody Object req,HttpServletRequest request, HttpServletResponse response)
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
		String uyijianName = map.get("uyijianName");
		String userId=map.get("userId");
		List<Integer> uxtypeIds = new ArrayList<Integer>();
		List<Uxtype> uxtypes = new ArrayList<Uxtype>();
		List<Uyijian> uyijians = new ArrayList<Uyijian>();
		Uyijian uyijian = new Uyijian();
		if (StringUtil.isNotEmpty(userId)) {
			uyijian.setUserId(Integer.parseInt(userId));
		}
		try {
			//统计图横（X）轴名称
			List<String> nameList = new ArrayList<String>();
			//统计图竖（Y）轴数值
	        List<Double> zongshuList = new ArrayList<Double>();
	        String tongjiUyijian = "[";
			uxtypes = uxtypeService.queryUxtypes(null, 0,0);
			for(int i=0;i<uxtypes.size();i++){
				uxtypeIds.add(uxtypes.get(i).getUxtypeId());
				nameList.add(uxtypes.get(i).getUxtypeName());
				Double uyijianZongshu = 0.0;
				if (StringUtil.isNotEmpty(uyijianName)) {
					uyijian.setUyijianName(uyijianName);
				}
				uyijian.setUxtypeId(uxtypes.get(i).getUxtypeId());
				uyijians = uyijianService.queryUyijians(uyijian, 0,0,sdate,edate);
				for(int j=0;j<uyijians.size();j++){
					uyijianZongshu = uyijianZongshu + uyijians.get(j).getUyijianZong();
				}
				//统计数值
				zongshuList.add(uyijianZongshu);
				
				tongjiUyijian = tongjiUyijian + "{\"value\":" + uyijianZongshu + ",\"name\": '" + uxtypes.get(i).getUxtypeName() + "' }";
				if(i==0){
					tongjiUyijian = tongjiUyijian + ",";
				}
			}
			tongjiUyijian = tongjiUyijian + "]";
			//转为map型
			Map<String,Object> tongjiMap=new HashMap<String,Object>();
			tongjiMap.put("value",zongshuList);
			tongjiMap.put("name",nameList);
			//转为json型
			JSONArray jsonArray = JSONArray.fromObject(tongjiUyijian);
			return Response.tongjiSuccess(tongjiMap,jsonArray);
		} catch (Exception e) {
			//e.printStackTrace();
			return Response.error(204,"服务器错误");
		}
	}

	//导入方法
	@ResponseBody
	@RequestMapping("/daoruUyijian")
	public Response daoruUyijian(@RequestBody Object req,HttpServletRequest request, HttpServletResponse response)
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
	        String fileName = map.get("uyijianImg");
			File dir = new File(targetDirectory,fileName);
			excelFile = new FileInputStream(dir);
			List<List<String>> list = new ArrayList<List<String>>();
			list = ExcelUtil.jiexiExcel(excelFile);
			for (int i = 1; i < list.size(); i++) {
				List<String> row = list.get(i);
				String uyijianName = row.get(1);
				String uyijianDouble = row.get(2);
				String uyijianDouble1 = row.get(3);
				String uyijianZong = row.get(4);
				String uyijianMark = row.get(5);
				String uxtypeId = row.get(6);
				Uyijian uyijian = new Uyijian();
				if (StringUtil.isNotEmpty(uyijianName)) {
					uyijian.setUyijianName(uyijianName);
				}
				if (StringUtil.isNotEmpty(uyijianMark)) {
					uyijian.setUyijianMark(uyijianMark);
				}
				if (StringUtil.isNotEmpty(uyijianZong)) {
					uyijian.setUyijianZong(Integer.parseInt(uyijianZong));
				}
				if (StringUtil.isNotEmpty(uyijianDouble)) {
					uyijian.setUyijianDouble(Double.parseDouble(uyijianDouble));
				}
				if (StringUtil.isNotEmpty(uyijianDouble1)) {
					uyijian.setUyijianDouble1(Double.parseDouble(uyijianDouble1));
				}
				if (StringUtil.isNotEmpty(uxtypeId)) {
					uyijian.setUxtypeId(Integer.parseInt(uxtypeId));
					Uxtype uxtype = new Uxtype();
					uxtype = uxtypeService.getUxtype(Integer.parseInt(uxtypeId));
					uyijian.setUxtypeName(uxtype.getUxtypeName());
				}
				Date date = new Date();
				uyijian.setUyijianDate(date);
				uyijian.setUyijianType(0);
				uyijianService.save(uyijian);
			}
			return Response.success();
		} catch (Exception e) {
			//e.printStackTrace();
			return Response.error(204,"服务器错误");
		}
	}
}
