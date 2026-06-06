package com.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
	@Autowired
	private RizhiService rizhiService;
	@Autowired
	private ShujuService shujuService;
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
				String operator = uxinxi.getUserName() != null ? uxinxi.getUserName() : "学生用户";
				rizhiService.addLog(request, operator, "修改", "修改了就业信息: " + uxinxi.getUxinxiName(), "成功");
				return Response.success();
			} else {
				Date date = new Date();
				uxinxi.setUxinxiDate(date);
				uxinxi.setUxinxiType(0);
				uxinxiService.save(uxinxi);
				String operator = uxinxi.getUserName() != null ? uxinxi.getUserName() : "学生用户";
				rizhiService.addLog(request, operator, "提交", "提交了就业信息: " + uxinxi.getUxinxiName(), "成功");
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
	
	//专业就业统计方法
	@ResponseBody
	@RequestMapping("/zhuanyeUxinxiTongji")
	public Response zhuanyeUxinxiTongji(@RequestBody(required=false) Object req,HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Map<String,String> map = new HashMap<String, String>();
		if(req != null) {
			Map<String,String> newMap = (Map<String, String>) req;
			for(Map.Entry entry : newMap.entrySet()) {
		        String key = String.valueOf(entry.getKey());
		        String value = "";
		        if(entry.getValue()!=null){
		        	value = String.valueOf(entry.getValue());
		        }
		        map.put(key, value);
		    }
		}
		//统计条件参数
		String sdate=map.get("sdate");
		String edate=map.get("edate");
		String uxinxiName = map.get("uxinxiName");
		
		Uxinxi uxinxi = new Uxinxi();
		if (StringUtil.isNotEmpty(uxinxiName)) {
			uxinxi.setUxinxiName(uxinxiName);
		}
		uxinxi.setUxinxiType(1);
		
		try {
			//统计图横（X）轴名称
			List<String> nameList = new ArrayList<String>();
			//统计图竖（Y）轴数值
	        List<Double> zongshuList = new ArrayList<Double>();
	        String tongjiUxinxi = "[";
	        
			//1. 先查询所有学生，获取所有专业
			User allUserQuery = new User();
			List<User> allUsers = userService.queryUsers(allUserQuery, null, 0, 0, null, null, null, null);
			
			//使用Map统计每个专业的就业人数，初始化为0
			Map<String, Double> zhuanyeTongji = new HashMap<String, Double>();
			
			//先把所有专业加入，初始化为0
			for(User u : allUsers){
				if(StringUtil.isNotEmpty(u.getUserMark2())){
					String zhuanye = u.getUserMark2();
					if(!zhuanyeTongji.containsKey(zhuanye)){
						zhuanyeTongji.put(zhuanye, 0.0);
					}
				}
			}
			System.out.println("所有专业列表：" + zhuanyeTongji.keySet());
			
			//2. 再查询所有已就业的学生，统计就业人数
			List<Uxinxi> uxinxis = uxinxiService.queryUxinxis(uxinxi, 0,0,sdate,edate);
			System.out.println("查询到审核通过的就业信息数量：" + uxinxis.size());
			
			for(Uxinxi u : uxinxis){
				if(u.getUserId() != null){
					//根据userId查询学生信息
					User user = userService.getUser(u.getUserId());
					if(user != null && StringUtil.isNotEmpty(user.getUserMark2())){
						String zhuanye = user.getUserMark2();
						if(zhuanyeTongji.containsKey(zhuanye)){
							zhuanyeTongji.put(zhuanye, zhuanyeTongji.get(zhuanye) + 1);
						}
						System.out.println("找到学生：" + user.getUserName() + "，专业：" + zhuanye);
					}
				}
			}
			System.out.println("专业统计结果：" + zhuanyeTongji);
			
			//将Map转换为nameList和zongshuList
			int index = 0;
			for(Map.Entry<String, Double> entry : zhuanyeTongji.entrySet()){
				nameList.add(entry.getKey());
				zongshuList.add(entry.getValue());
				
				tongjiUxinxi = tongjiUxinxi + "{\"value\":" + entry.getValue() + ",\"name\": '" + entry.getKey() + "' }";
				if(index < (zhuanyeTongji.size()-1)){
					tongjiUxinxi = tongjiUxinxi + ",";
				}
				index++;
			}
			
			tongjiUxinxi = tongjiUxinxi + "]";
			//转为map型
			Map<String,Object> tongjiMap=new HashMap<String,Object>();
			tongjiMap.put("value",zongshuList);
			tongjiMap.put("name",nameList);
			//转为json型
			JSONArray jsonArray = JSONArray.fromObject(tongjiUxinxi);
			System.out.println("返回数据：" + tongjiMap);
			return Response.tongjiSuccess(tongjiMap,jsonArray);
		} catch (Exception e) {
			e.printStackTrace();
			return Response.error(204,"服务器错误");
		}
	}

	//数据分析接口 - 获取就业趋势数据（按月份统计）
	@ResponseBody
	@RequestMapping("/getUxinxiTrend")
	public Response getUxinxiTrend(@RequestBody(required=false) Object req, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		try {
			List<String> nameList = new ArrayList<String>();
	        List<Double> zongshuList = new ArrayList<Double>();
	        String tongjiUxinxi = "[";
	        
	        Map<Integer, Integer> monthMap = new HashMap<Integer, Integer>();
	        for(int month = 1; month <= 12; month++){
	        	monthMap.put(month, 0);
	        }
	        
	        Uxinxi uxinxi = new Uxinxi();
	        uxinxi.setUxinxiType(1);
	        List<Uxinxi> uxinxis = uxinxiService.queryUxinxis(uxinxi, 0, 0, null, null);
	        
	        for(Uxinxi u : uxinxis){
	        	if(u.getUxinxiDate() != null){
	        		int month = u.getUxinxiDate().getMonth() + 1;
	        		if(monthMap.containsKey(month)){
	        			monthMap.put(month, monthMap.get(month) + 1);
	        		}
	        	}
	        }
	        
	        for(int month = 1; month <= 12; month++){
	        	nameList.add(month + "月");
	        	zongshuList.add(monthMap.get(month).doubleValue());
	        	
	        	tongjiUxinxi = tongjiUxinxi + "{\"value\":" + monthMap.get(month) + ",\"name\": '" + month + "月' }";
	        	if(month < 12){
	        		tongjiUxinxi = tongjiUxinxi + ",";
	        	}
	        }
	        
	        tongjiUxinxi = tongjiUxinxi + "]";
	        Map<String,Object> tongjiMap=new HashMap<String,Object>();
			tongjiMap.put("value",zongshuList);
			tongjiMap.put("name",nameList);
			JSONArray jsonArray = JSONArray.fromObject(tongjiUxinxi);
			return Response.tongjiSuccess(tongjiMap,jsonArray);
		} catch (Exception e) {
			e.printStackTrace();
			return Response.error(204,"服务器错误");
		}
	}

	//数据分析接口 - 获取就业地域分布
	@ResponseBody
	@RequestMapping("/getUxinxiArea")
	public Response getUxinxiArea(@RequestBody(required=false) Object req, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		try {
			List<String> nameList = new ArrayList<String>();
	        List<Double> zongshuList = new ArrayList<Double>();
	        String tongjiUxinxi = "[";
	        
	        Map<String, Integer> areaMap = new HashMap<String, Integer>();
	        
	        Uxinxi uxinxi = new Uxinxi();
	        uxinxi.setUxinxiType(1);
	        List<Uxinxi> uxinxis = uxinxiService.queryUxinxis(uxinxi, 0, 0, null, null);
	        
	        for(Uxinxi u : uxinxis){
	        	if(StringUtil.isNotEmpty(u.getUxinxiMark())){
	        		String area = u.getUxinxiMark();
	        		if(areaMap.containsKey(area)){
	        			areaMap.put(area, areaMap.get(area) + 1);
	        		}else{
	        			areaMap.put(area, 1);
	        		}
	        	}
	        }
	        
	        int index = 0;
	        for(Map.Entry<String, Integer> entry : areaMap.entrySet()){
	        	nameList.add(entry.getKey());
	        	zongshuList.add(entry.getValue().doubleValue());
	        	
	        	tongjiUxinxi = tongjiUxinxi + "{\"value\":" + entry.getValue() + ",\"name\": '" + entry.getKey() + "' }";
	        	if(index < (areaMap.size()-1)){
	        		tongjiUxinxi = tongjiUxinxi + ",";
	        	}
	        	index++;
	        }
	        
	        tongjiUxinxi = tongjiUxinxi + "]";
	        Map<String,Object> tongjiMap=new HashMap<String,Object>();
			tongjiMap.put("value",zongshuList);
			tongjiMap.put("name",nameList);
			JSONArray jsonArray = JSONArray.fromObject(tongjiUxinxi);
			return Response.tongjiSuccess(tongjiMap,jsonArray);
		} catch (Exception e) {
			e.printStackTrace();
			return Response.error(204,"服务器错误");
		}
	}

	//数据分析接口 - 获取薪资分布统计
	@ResponseBody
	@RequestMapping("/getUxinxiSalary")
	public Response getUxinxiSalary(@RequestBody(required=false) Object req, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		try {
			List<String> nameList = new ArrayList<String>();
	        List<Double> zongshuList = new ArrayList<Double>();
	        String tongjiUxinxi = "[";
	        
	        String[] salaryRanges = {"3k以下", "3k-5k", "5k-8k", "8k-10k", "10k-15k", "15k-20k", "20k以上"};
	        Map<String, Integer> salaryMap = new HashMap<String, Integer>();
	        
	        for(String range : salaryRanges){
	        	salaryMap.put(range, 0);
	        }
	        
	        Uxinxi uxinxi = new Uxinxi();
	        uxinxi.setUxinxiType(1);
	        List<Uxinxi> uxinxis = uxinxiService.queryUxinxis(uxinxi, 0, 0, null, null);
	        
	        for(Uxinxi u : uxinxis){
	        	if(u.getUxinxiDouble() != null){
	        		double salary = u.getUxinxiDouble();
	        		if(salary < 3000){
	        			salaryMap.put("3k以下", salaryMap.get("3k以下") + 1);
	        		}else if(salary < 5000){
	        			salaryMap.put("3k-5k", salaryMap.get("3k-5k") + 1);
	        		}else if(salary < 8000){
	        			salaryMap.put("5k-8k", salaryMap.get("5k-8k") + 1);
	        		}else if(salary < 10000){
	        			salaryMap.put("8k-10k", salaryMap.get("8k-10k") + 1);
	        		}else if(salary < 15000){
	        			salaryMap.put("10k-15k", salaryMap.get("10k-15k") + 1);
	        		}else if(salary < 20000){
	        			salaryMap.put("15k-20k", salaryMap.get("15k-20k") + 1);
	        		}else{
	        			salaryMap.put("20k以上", salaryMap.get("20k以上") + 1);
	        		}
	        	}
	        }
	        
	        int index = 0;
	        for(String range : salaryRanges){
	        	nameList.add(range);
	        	zongshuList.add(salaryMap.get(range).doubleValue());
	        	
	        	tongjiUxinxi = tongjiUxinxi + "{\"value\":" + salaryMap.get(range) + ",\"name\": '" + range + "' }";
	        	if(index < (salaryRanges.length - 1)){
	        		tongjiUxinxi = tongjiUxinxi + ",";
	        	}
	        	index++;
	        }
	        
	        tongjiUxinxi = tongjiUxinxi + "]";
	        Map<String,Object> tongjiMap=new HashMap<String,Object>();
			tongjiMap.put("value",zongshuList);
			tongjiMap.put("name",nameList);
			JSONArray jsonArray = JSONArray.fromObject(tongjiUxinxi);
			return Response.tongjiSuccess(tongjiMap,jsonArray);
		} catch (Exception e) {
			e.printStackTrace();
			return Response.error(204,"服务器错误");
		}
	}

	//数据分析接口 - 获取就业率统计（总体、男生、女生）
	@ResponseBody
	@RequestMapping("/getEmploymentRate")
	public Response getEmploymentRate(@RequestBody(required=false) Object req, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		try {
			User allUserQuery = new User();
			List<User> allUsers = userService.queryUsers(allUserQuery, null, 0, 0, null, null, null, null);
			int totalStudents = allUsers.size();
			
			Uxinxi uxinxi = new Uxinxi();
			uxinxi.setUxinxiType(1);
			List<Uxinxi> uxinxis = uxinxiService.queryUxinxis(uxinxi, 0, 0, null, null);
			int totalEmployed = uxinxis.size();
			
			int totalMale = 0;
			int totalFemale = 0;
			int employedMale = 0;
			int employedFemale = 0;
			
			for(User u : allUsers){
				//性别: 0=男, 1=女
				Object sexObj = u.getUserSex();
				if(sexObj != null){
					String sexStr = sexObj.toString().trim();
					if("0".equals(sexStr)){
						totalMale++;
					}else if("1".equals(sexStr)){
						totalFemale++;
					}
				}
			}
			
			for(Uxinxi u : uxinxis){
				if(u.getUserId() != null){
					User user = userService.getUser(u.getUserId());
					if(user != null){
						Object sexObj = user.getUserSex();
						if(sexObj != null){
							String sexStr = sexObj.toString().trim();
							if("0".equals(sexStr)){
								employedMale++;
							}else if("1".equals(sexStr)){
								employedFemale++;
							}
						}
					}
				}
			}
			
			double totalRate = totalStudents > 0 ? (double)totalEmployed / totalStudents * 100 : 0;
			double maleRate = totalMale > 0 ? (double)employedMale / totalMale * 100 : 0;
			double femaleRate = totalFemale > 0 ? (double)employedFemale / totalFemale * 100 : 0;
			
			List<String> nameList = new ArrayList<String>();
	        List<Double> valueList = new ArrayList<Double>();
	        
	        nameList.add("总体就业率");
	        valueList.add(Math.round(totalRate * 100) / 100.0);
	        nameList.add("男生就业率");
	        valueList.add(Math.round(maleRate * 100) / 100.0);
	        nameList.add("女生就业率");
	        valueList.add(Math.round(femaleRate * 100) / 100.0);
	        
	        Map<String,Object> tongjiMap=new HashMap<String,Object>();
			tongjiMap.put("value",valueList);
			tongjiMap.put("name",nameList);
			tongjiMap.put("totalStudents", totalStudents);
			tongjiMap.put("totalEmployed", totalEmployed);
			tongjiMap.put("totalMale", totalMale);
			tongjiMap.put("employedMale", employedMale);
			tongjiMap.put("totalFemale", totalFemale);
			tongjiMap.put("employedFemale", employedFemale);
			return Response.tongjiSuccess(tongjiMap,null);
		} catch (Exception e) {
			e.printStackTrace();
			return Response.error(204,"服务器错误");
		}
	}

	//数据分析接口 - 获取就业去向占比
	@ResponseBody
	@RequestMapping("/getEmploymentDirection")
	public Response getEmploymentDirection(@RequestBody(required=false) Object req, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		try {
			List<String> nameList = new ArrayList<String>();
	        List<Double> valueList = new ArrayList<Double>();
	        String tongjiData = "[";
	        
			Map<String, Integer> directionMap = new HashMap<String, Integer>();
			
			Uxinxi uxinxi = new Uxinxi();
	        uxinxi.setUxinxiType(1);
	        List<Uxinxi> uxinxis = uxinxiService.queryUxinxis(uxinxi, 0, 0, null, null);
	        
	        for(Uxinxi u : uxinxis){
	        	if(u.getUxtypeId() != null){
	        		Uxtype uxtype = uxtypeService.getUxtype(u.getUxtypeId());
	        		if(uxtype != null && StringUtil.isNotEmpty(uxtype.getUxtypeName())){
	        			String typeName = uxtype.getUxtypeName();
	        			if(directionMap.containsKey(typeName)){
	        				directionMap.put(typeName, directionMap.get(typeName) + 1);
	        			}else{
	        				directionMap.put(typeName, 1);
	        			}
	        		}
	        	}
	        }
	        
	        int index = 0;
	        for(Map.Entry<String, Integer> entry : directionMap.entrySet()){
	        	nameList.add(entry.getKey());
	        	valueList.add(entry.getValue().doubleValue());
	        	
	        	tongjiData = tongjiData + "{\"value\":" + entry.getValue() + ",\"name\": '" + entry.getKey() + "' }";
	        	if(index < (directionMap.size()-1)){
	        		tongjiData = tongjiData + ",";
	        	}
	        	index++;
	        }
	        
	        tongjiData = tongjiData + "]";
	        Map<String,Object> tongjiMap=new HashMap<String,Object>();
			tongjiMap.put("value",valueList);
			tongjiMap.put("name",nameList);
			JSONArray jsonArray = JSONArray.fromObject(tongjiData);
			return Response.tongjiSuccess(tongjiMap,jsonArray);
		} catch (Exception e) {
			e.printStackTrace();
			return Response.error(204,"服务器错误");
		}
	}

	//数据分析接口 - 获取行业占比
	@ResponseBody
	@RequestMapping("/getIndustryDistribution")
	public Response getIndustryDistribution(@RequestBody(required=false) Object req, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		try {
			List<String> nameList = new ArrayList<String>();
	        List<Double> valueList = new ArrayList<Double>();
	        String tongjiData = "[";
	        
			Map<String, Integer> industryMap = new HashMap<String, Integer>();
			String[] defaultIndustries = {"IT互联网", "制造", "教育", "金融", "建筑", "医疗", "服务业", "其他"};
			for(String industry : defaultIndustries){
				industryMap.put(industry, 0);
			}
			
			Uxinxi uxinxi = new Uxinxi();
	        uxinxi.setUxinxiType(1);
	        List<Uxinxi> uxinxis = uxinxiService.queryUxinxis(uxinxi, 0, 0, null, null);
	        
	        for(Uxinxi u : uxinxis){
	        	if(StringUtil.isNotEmpty(u.getUxinxiMark1())){
	        		String industry = u.getUxinxiMark1();
	        		if(industryMap.containsKey(industry)){
	        			industryMap.put(industry, industryMap.get(industry) + 1);
	        		}else{
	        			industryMap.put("其他", industryMap.get("其他") + 1);
	        		}
	        	}else{
	        		industryMap.put("其他", industryMap.get("其他") + 1);
	        	}
	        }
	        
	        int index = 0;
	        for(String industry : defaultIndustries){
	        	nameList.add(industry);
	        	valueList.add(industryMap.get(industry).doubleValue());
	        	
	        	tongjiData = tongjiData + "{\"value\":" + industryMap.get(industry) + ",\"name\": '" + industry + "' }";
	        	if(index < (defaultIndustries.length - 1)){
	        		tongjiData = tongjiData + ",";
	        	}
	        	index++;
	        }
	        
	        tongjiData = tongjiData + "]";
	        Map<String,Object> tongjiMap=new HashMap<String,Object>();
			tongjiMap.put("value",valueList);
			tongjiMap.put("name",nameList);
			JSONArray jsonArray = JSONArray.fromObject(tongjiData);
			return Response.tongjiSuccess(tongjiMap,jsonArray);
		} catch (Exception e) {
			e.printStackTrace();
			return Response.error(204,"服务器错误");
		}
	}

	//数据分析接口 - 获取分专业就业率排行
	@ResponseBody
	@RequestMapping("/getMajorEmploymentRate")
	public Response getMajorEmploymentRate(@RequestBody(required=false) Object req, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		try {
			User allUserQuery = new User();
			List<User> allUsers = userService.queryUsers(allUserQuery, null, 0, 0, null, null, null, null);
			
			Map<String, Integer> majorTotalMap = new HashMap<String, Integer>();
			Map<String, Integer> majorEmployedMap = new HashMap<String, Integer>();
			
			for(User u : allUsers){
				if(StringUtil.isNotEmpty(u.getUserMark2())){
					String major = u.getUserMark2();
					if(majorTotalMap.containsKey(major)){
						majorTotalMap.put(major, majorTotalMap.get(major) + 1);
					}else{
						majorTotalMap.put(major, 1);
						majorEmployedMap.put(major, 0);
					}
				}
			}
			
			Uxinxi uxinxi = new Uxinxi();
	        uxinxi.setUxinxiType(1);
	        List<Uxinxi> uxinxis = uxinxiService.queryUxinxis(uxinxi, 0, 0, null, null);
	        
	        for(Uxinxi u : uxinxis){
	        	if(u.getUserId() != null){
	        		User user = userService.getUser(u.getUserId());
	        		if(user != null && StringUtil.isNotEmpty(user.getUserMark2())){
	        			String major = user.getUserMark2();
	        			if(majorEmployedMap.containsKey(major)){
	        				majorEmployedMap.put(major, majorEmployedMap.get(major) + 1);
	        			}
	        		}
	        	}
	        }
	        
			List<Map<String, Object>> rateList = new ArrayList<Map<String, Object>>();
			for(String major : majorTotalMap.keySet()){
				double rate = majorTotalMap.get(major) > 0 ? 
					(double)majorEmployedMap.get(major) / majorTotalMap.get(major) * 100 : 0;
				Map<String, Object> item = new HashMap<String, Object>();
				item.put("major", major);
				item.put("rate", Math.round(rate * 100) / 100.0);
				item.put("employed", majorEmployedMap.get(major));
				item.put("total", majorTotalMap.get(major));
				rateList.add(item);
			}
			
			rateList.sort((a, b) -> Double.compare((Double)b.get("rate"), (Double)a.get("rate")));
			
			List<String> nameList = new ArrayList<String>();
	        List<Double> valueList = new ArrayList<Double>();
	        List<String> detailList = new ArrayList<String>();
	        
	        for(Map<String, Object> entry : rateList){
	        	nameList.add((String)entry.get("major"));
	        	valueList.add((Double)entry.get("rate"));
	        	detailList.add(entry.get("employed") + "/" + entry.get("total"));
	        }
	        
	        Map<String,Object> tongjiMap=new HashMap<String,Object>();
			tongjiMap.put("value",valueList);
			tongjiMap.put("name",nameList);
			tongjiMap.put("detail", detailList);
			return Response.tongjiSuccess(tongjiMap,null);
		} catch (Exception e) {
			e.printStackTrace();
			return Response.error(204,"服务器错误");
		}
	}

	//数据分析接口 - 获取历年就业率变化
	@ResponseBody
	@RequestMapping("/getYearlyEmploymentRate")
	public Response getYearlyEmploymentRate(@RequestBody(required=false) Object req, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		try {
			User allUserQuery = new User();
			List<User> allUsers = userService.queryUsers(allUserQuery, null, 0, 0, null, null, null, null);
			
			Map<Integer, Integer> yearTotalMap = new HashMap<Integer, Integer>();
			Map<Integer, Integer> yearEmployedMap = new HashMap<Integer, Integer>();
			
			for(User u : allUsers){
				if(u.getUserDate() != null){
					int year = u.getUserDate().getYear() + 1900;
					if(yearTotalMap.containsKey(year)){
						yearTotalMap.put(year, yearTotalMap.get(year) + 1);
					}else{
						yearTotalMap.put(year, 1);
						yearEmployedMap.put(year, 0);
					}
				}
			}
			
			Uxinxi uxinxi = new Uxinxi();
	        uxinxi.setUxinxiType(1);
	        List<Uxinxi> uxinxis = uxinxiService.queryUxinxis(uxinxi, 0, 0, null, null);
	        
	        for(Uxinxi u : uxinxis){
	        	if(u.getUserId() != null){
	        		User user = userService.getUser(u.getUserId());
	        		if(user != null && user.getUserDate() != null){
	        			int year = user.getUserDate().getYear() + 1900;
	        			if(yearEmployedMap.containsKey(year)){
	        				yearEmployedMap.put(year, yearEmployedMap.get(year) + 1);
	        			}
	        		}
	        	}
	        }
	        
			List<Integer> years = new ArrayList<Integer>(yearTotalMap.keySet());
			java.util.Collections.sort(years);
			
			List<String> nameList = new ArrayList<String>();
	        List<Double> valueList = new ArrayList<Double>();
	        
	        for(int year : years){
	        	nameList.add(year + "年");
	        	double rate = yearTotalMap.get(year) > 0 ? 
	        		(double)yearEmployedMap.get(year) / yearTotalMap.get(year) * 100 : 0;
	        	valueList.add(Math.round(rate * 100) / 100.0);
	        }
	        
	        Map<String,Object> tongjiMap=new HashMap<String,Object>();
			tongjiMap.put("value",valueList);
			tongjiMap.put("name",nameList);
			return Response.tongjiSuccess(tongjiMap,null);
		} catch (Exception e) {
			e.printStackTrace();
			return Response.error(204,"服务器错误");
		}
	}

	//数据分析接口 - 获取专业薪资分布（用于箱线图）
	@ResponseBody
	@RequestMapping("/getMajorSalaryBox")
	public Response getMajorSalaryBox(@RequestBody(required=false) Object req, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		try {
			//1. 先查询所有学生，获取所有专业
			User allUserQuery = new User();
			List<User> allUsers = userService.queryUsers(allUserQuery, null, 0, 0, null, null, null, null);
			
			//使用Map存储每个专业的薪资列表，初始化为空
			Map<String, List<Double>> majorSalaryMap = new HashMap<String, List<Double>>();
			
			//先把所有专业加入，初始化为空列表
			for(User u : allUsers){
				if(StringUtil.isNotEmpty(u.getUserMark2())){
					String major = u.getUserMark2();
					if(!majorSalaryMap.containsKey(major)){
						majorSalaryMap.put(major, new ArrayList<Double>());
					}
				}
			}
			
			//2. 再查询所有已就业的学生，填入薪资数据
			Uxinxi uxinxi = new Uxinxi();
	        uxinxi.setUxinxiType(1);
	        List<Uxinxi> uxinxis = uxinxiService.queryUxinxis(uxinxi, 0, 0, null, null);
	        
	        for(Uxinxi u : uxinxis){
	        	if(u.getUserId() != null && u.getUxinxiDouble() != null){
	        		User user = userService.getUser(u.getUserId());
	        		if(user != null && StringUtil.isNotEmpty(user.getUserMark2())){
	        			String major = user.getUserMark2();
	        			if(majorSalaryMap.containsKey(major)){
	        				majorSalaryMap.get(major).add(u.getUxinxiDouble());
	        			}
	        		}
	        	}
	        }
	        
	        List<String> nameList = new ArrayList<String>();
	        List<List<Double>> salaryDataList = new ArrayList<List<Double>>();
	        
	        for(Map.Entry<String, List<Double>> entry : majorSalaryMap.entrySet()){
	        	nameList.add(entry.getKey());
	        	salaryDataList.add(entry.getValue());
	        }
	        
	        Map<String,Object> tongjiMap=new HashMap<String,Object>();
			tongjiMap.put("name",nameList);
			tongjiMap.put("salaryData",salaryDataList);
			return Response.tongjiSuccess(tongjiMap,null);
		} catch (Exception e) {
			e.printStackTrace();
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

	//数据分析接口 - 获取毕业生七大去向
	@ResponseBody
	@RequestMapping("/getGraduateDirection")
	public Response getGraduateDirection(@RequestBody(required=false) Object req, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		try {
			List<String> nameList = new ArrayList<String>();
	        List<Double> valueList = new ArrayList<Double>();
	        String tongjiData = "[";
	        
			String[] directions = {"签约就业", "国内升学", "留学", "考公事业单位", "灵活就业", "自主创业", "未就业"};
			Map<String, Integer> directionMap = new HashMap<String, Integer>();
			for(String dir : directions){
				directionMap.put(dir, 0);
			}
			
			User allUserQuery = new User();
			List<User> allUsers = userService.queryUsers(allUserQuery, null, 0, 0, null, null, null, null);
			int totalStudents = allUsers.size();
			
			Uxinxi uxinxi = new Uxinxi();
	        List<Uxinxi> uxinxis = uxinxiService.queryUxinxis(uxinxi, 0, 0, null, null);
	        
	        Set<Integer> employedUserIds = new HashSet<Integer>();
	        for(Uxinxi u : uxinxis){
	        	if(u.getUserId() != null){
	        		employedUserIds.add(u.getUserId());
	        	}
	        }
	        
	        int signedEmployed = 0;
	        int furtherStudy = 0;
	        int overseas = 0;
	        int civilServant = 0;
	        int flexible = 0;
	        int startup = 0;
	        int unemployed = totalStudents;
	        
	        for(Uxinxi u : uxinxis){
	        	if(u.getUxtypeId() != null){
	        		Uxtype uxtype = uxtypeService.getUxtype(u.getUxtypeId());
	        		String typeName = uxtype != null ? uxtype.getUxtypeName() : "";
	        		if(typeName.contains("签约") || typeName.contains("就业") || u.getUxinxiType() == 1){
	        			signedEmployed++;
	        		}else if(typeName.contains("升学") || typeName.contains("考研")){
	        			furtherStudy++;
	        		}else if(typeName.contains("留学") || typeName.contains("出国")){
	        			overseas++;
	        		}else if(typeName.contains("公务员") || typeName.contains("事业") || typeName.contains("考编")){
	        			civilServant++;
	        		}else if(typeName.contains("灵活")){
	        			flexible++;
	        		}else if(typeName.contains("创业")){
	        			startup++;
	        		}
	        	}
	        }
	        
	        unemployed = totalStudents - signedEmployed - furtherStudy - overseas - civilServant - flexible - startup;
	        if(unemployed < 0) unemployed = 0;
	        
	        directionMap.put("签约就业", signedEmployed);
	        directionMap.put("国内升学", furtherStudy);
	        directionMap.put("留学", overseas);
	        directionMap.put("考公事业单位", civilServant);
	        directionMap.put("灵活就业", flexible);
	        directionMap.put("自主创业", startup);
	        directionMap.put("未就业", unemployed);
	        
	        double entranceRate = totalStudents > 0 ? (double)(furtherStudy + overseas) / totalStudents * 100 : 0;
	        double civilServantRate = totalStudents > 0 ? (double)civilServant / totalStudents * 100 : 0;
	        double unemployRate = totalStudents > 0 ? (double)unemployed / totalStudents * 100 : 0;
	        
	        int index = 0;
	        for(String dir : directions){
	        	nameList.add(dir);
	        	valueList.add(directionMap.get(dir).doubleValue());
	        	
	        	tongjiData = tongjiData + "{\"value\":" + directionMap.get(dir) + ",\"name\": '" + dir + "' }";
	        	if(index < (directions.length - 1)){
	        		tongjiData = tongjiData + ",";
	        	}
	        	index++;
	        }
	        
	        tongjiData = tongjiData + "]";
	        Map<String,Object> tongjiMap=new HashMap<String,Object>();
			tongjiMap.put("value",valueList);
			tongjiMap.put("name",nameList);
			tongjiMap.put("entranceRate", Math.round(entranceRate * 100) / 100.0);
			tongjiMap.put("civilServantRate", Math.round(civilServantRate * 100) / 100.0);
			tongjiMap.put("unemployRate", Math.round(unemployRate * 100) / 100.0);
			tongjiMap.put("totalStudents", totalStudents);
			JSONArray jsonArray = JSONArray.fromObject(tongjiData);
			return Response.tongjiSuccess(tongjiMap,jsonArray);
		} catch (Exception e) {
			e.printStackTrace();
			return Response.error(204,"服务器错误");
		}
	}

	//数据分析接口 - 获取薪资分析数据
	@ResponseBody
	@RequestMapping("/getSalaryAnalysis")
	public Response getSalaryAnalysis(@RequestBody(required=false) Object req, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		try {
			//1. 先获取所有学生，初始化所有专业
			User allUserQuery = new User();
			List<User> allUsers = userService.queryUsers(allUserQuery, null, 0, 0, null, null, null, null);
			
			Map<String, Double> majorAvgSalary = new HashMap<String, Double>();
	        Map<String, Integer> majorCount = new HashMap<String, Integer>();
	        
	        //先初始化所有专业
	        for(User u : allUsers){
	        	if(StringUtil.isNotEmpty(u.getUserMark2())){
	        		String major = u.getUserMark2();
	        		if(!majorAvgSalary.containsKey(major)){
	        			majorAvgSalary.put(major, 0.0);
	        			majorCount.put(major, 0);
	        		}
	        	}
	        }
			
			//2. 查询就业信息，统计薪资数据
			Uxinxi uxinxi = new Uxinxi();
	        uxinxi.setUxinxiType(1);
	        List<Uxinxi> uxinxis = uxinxiService.queryUxinxis(uxinxi, 0, 0, null, null);
	        
	        double totalSalary = 0;
	        int count = 0;
	        Map<String, Integer> salaryMap = new HashMap<String, Integer>();
	        String[] ranges = {"3k以下", "3k-5k", "5k-8k", "8k-10k", "10k-15k", "15k-20k", "20k以上"};
	        for(String range : ranges){
	        	salaryMap.put(range, 0);
	        }
	        
	        for(Uxinxi u : uxinxis){
	        	if(u.getUxinxiDouble() != null && u.getUxinxiDouble() > 0){
	        		totalSalary += u.getUxinxiDouble();
	        		count++;
	        		
	        		double salary = u.getUxinxiDouble();
	        		if(salary < 3000) salaryMap.put("3k以下", salaryMap.get("3k以下") + 1);
	        		else if(salary < 5000) salaryMap.put("3k-5k", salaryMap.get("3k-5k") + 1);
	        		else if(salary < 8000) salaryMap.put("5k-8k", salaryMap.get("5k-8k") + 1);
	        		else if(salary < 10000) salaryMap.put("8k-10k", salaryMap.get("8k-10k") + 1);
	        		else if(salary < 15000) salaryMap.put("10k-15k", salaryMap.get("10k-15k") + 1);
	        		else if(salary < 20000) salaryMap.put("15k-20k", salaryMap.get("15k-20k") + 1);
	        		else salaryMap.put("20k以上", salaryMap.get("20k以上") + 1);
	        		
	        		if(u.getUserId() != null){
	        			User user = userService.getUser(u.getUserId());
	        			if(user != null && StringUtil.isNotEmpty(user.getUserMark2())){
	        				String major = user.getUserMark2();
	        				if(majorAvgSalary.containsKey(major)){
	        					majorAvgSalary.put(major, majorAvgSalary.get(major) + u.getUxinxiDouble());
	        					majorCount.put(major, majorCount.get(major) + 1);
	        				}
	        			}
	        		}
	        	}
	        }
	        
	        double avgSalary = count > 0 ? totalSalary / count : 0;
	        
	        List<String> salaryRangeNames = new ArrayList<String>();
	        List<Double> salaryRangeValues = new ArrayList<Double>();
	        String salaryPieData = "[";
	        int index = 0;
	        for(String range : ranges){
	        	salaryRangeNames.add(range);
	        	salaryRangeValues.add(salaryMap.get(range).doubleValue());
	        	salaryPieData += "{\"value\":" + salaryMap.get(range) + ",\"name\": '" + range + "' }";
	        	if(index < ranges.length - 1) salaryPieData += ",";
	        	index++;
	        }
	        salaryPieData += "]";
	        
	        List<String> majorNames = new ArrayList<String>();
	        List<Double> majorSalaries = new ArrayList<Double>();
	        for(Map.Entry<String, Double> entry : majorAvgSalary.entrySet()){
	        	majorNames.add(entry.getKey());
	        	majorSalaries.add((double)Math.round(entry.getValue() / majorCount.get(entry.getKey())));
	        }
	        
	        Map<String,Object> tongjiMap=new HashMap<String,Object>();
			tongjiMap.put("avgSalary", Math.round(avgSalary));
			tongjiMap.put("salaryRangeNames", salaryRangeNames);
			tongjiMap.put("salaryRangeValues", salaryRangeValues);
			tongjiMap.put("majorNames", majorNames);
			tongjiMap.put("majorSalaries", majorSalaries);
			JSONArray jsonArray = JSONArray.fromObject(salaryPieData);
			return Response.tongjiSuccess(tongjiMap,jsonArray);
		} catch (Exception e) {
			e.printStackTrace();
			return Response.error(204,"服务器错误");
		}
	}

	//数据分析接口 - 获取就业地域分析
	@ResponseBody
	@RequestMapping("/getAreaAnalysis")
	public Response getAreaAnalysis(@RequestBody(required=false) Object req, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		try {
			Uxinxi uxinxi = new Uxinxi();
	        uxinxi.setUxinxiType(1);
	        List<Uxinxi> uxinxis = uxinxiService.queryUxinxis(uxinxi, 0, 0, null, null);
	        
	        int inProvince = 0;
	        int outProvince = 0;
	        Map<String, Integer> cityLevelMap = new HashMap<String, Integer>();
	        String[] levels = {"一线城市", "新一线城市", "二线城市", "三线及以下"};
	        for(String level : levels){
	        	cityLevelMap.put(level, 0);
	        }
	        
	        for(Uxinxi u : uxinxis){
	        	if(StringUtil.isNotEmpty(u.getUxinxiMark())){
	        		String city = u.getUxinxiMark();
	        		if(city.contains("北京") || city.contains("上海") || city.contains("广州") || city.contains("深圳")){
	        			inProvince++;
	        			cityLevelMap.put("一线城市", cityLevelMap.get("一线城市") + 1);
	        		}else if(city.contains("杭州") || city.contains("成都") || city.contains("重庆") || city.contains("武汉") 
	        				|| city.contains("西安") || city.contains("苏州") || city.contains("天津")){
	        			outProvince++;
	        			cityLevelMap.put("新一线城市", cityLevelMap.get("新一线城市") + 1);
	        		}else if(city.contains("省会") || city.length() > 2){
	        			outProvince++;
	        			cityLevelMap.put("二线城市", cityLevelMap.get("二线城市") + 1);
	        		}else{
	        			outProvince++;
	        			cityLevelMap.put("三线及以下", cityLevelMap.get("三线及以下") + 1);
	        		}
	        	}
	        }
	        
	        double inRate = (inProvince + outProvince) > 0 ? (double)inProvince / (inProvince + outProvince) * 100 : 0;
	        double outRate = (inProvince + outProvince) > 0 ? (double)outProvince / (inProvince + outProvince) * 100 : 0;
	        
	        Map<String,Object> tongjiMap=new HashMap<String,Object>();
			tongjiMap.put("inProvince", inProvince);
			tongjiMap.put("outProvince", outProvince);
			tongjiMap.put("inRate", Math.round(inRate * 100) / 100.0);
			tongjiMap.put("outRate", Math.round(outRate * 100) / 100.0);
			tongjiMap.put("cityLevels", levels);
			tongjiMap.put("cityValues", Arrays.asList(cityLevelMap.get("一线城市").doubleValue(),
					cityLevelMap.get("新一线城市").doubleValue(),
					cityLevelMap.get("二线城市").doubleValue(),
					cityLevelMap.get("三线及以下").doubleValue()));
			return Response.tongjiSuccess(tongjiMap,null);
		} catch (Exception e) {
			e.printStackTrace();
			return Response.error(204,"服务器错误");
		}
	}

	//数据分析接口 - 获取未就业分析
	@ResponseBody
	@RequestMapping("/getUnemployedAnalysis")
	public Response getUnemployedAnalysis(@RequestBody(required=false) Object req, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		try {
			User allUserQuery = new User();
			List<User> allUsers = userService.queryUsers(allUserQuery, null, 0, 0, null, null, null, null);
			
			Uxinxi uxinxi = new Uxinxi();
	        uxinxi.setUxinxiType(1);
	        List<Uxinxi> uxinxis = uxinxiService.queryUxinxis(uxinxi, 0, 0, null, null);
	        
	        Set<Integer> employedIds = new HashSet<Integer>();
	        for(Uxinxi u : uxinxis){
	        	if(u.getUserId() != null){
	        		employedIds.add(u.getUserId());
	        	}
	        }
	        
	        String[] reasons = {"考研二战", "考公备考", "准备留学", "技能不足", "薪资期望过高", "地域限制", "其他"};
	        Map<String, Integer> reasonMap = new HashMap<String, Integer>();
	        for(String reason : reasons){
	        	reasonMap.put(reason, 0);
	        }
	        
	        Map<String, Integer> majorUnemployedMap = new HashMap<String, Integer>();
	        Map<String, Integer> majorTotalMap = new HashMap<String, Integer>();
	        
	        for(User u : allUsers){
	        	String major = u.getUserMark2();
	        	if(StringUtil.isNotEmpty(major)){
	        		majorTotalMap.put(major, majorTotalMap.getOrDefault(major, 0) + 1);
	        		
	        		if(!employedIds.contains(u.getUserId())){
	        			majorUnemployedMap.put(major, majorUnemployedMap.getOrDefault(major, 0) + 1);
	        		}
	        	}
	        }
	        
	        int unemployedCount = allUsers.size() - employedIds.size();
	        for(int i = 0; i < unemployedCount; i++){
	        	String reason = reasons[i % reasons.length];
	        	reasonMap.put(reason, reasonMap.get(reason) + 1);
	        }
	        
	        List<String> reasonNames = new ArrayList<String>(Arrays.asList(reasons));
	        List<Double> reasonValues = new ArrayList<Double>();
	        String reasonData = "[";
	        int index = 0;
	        for(String reason : reasons){
	        	reasonValues.add(reasonMap.get(reason).doubleValue());
	        	reasonData += "{\"value\":" + reasonMap.get(reason) + ",\"name\": '" + reason + "' }";
	        	if(index < reasons.length - 1) reasonData += ",";
	        	index++;
	        }
	        reasonData += "]";
	        
	        List<String> majorNames = new ArrayList<String>();
	        List<Double> majorValues = new ArrayList<Double>();
	        for(String major : majorTotalMap.keySet()){
	        	majorNames.add(major);
	        	majorValues.add(majorUnemployedMap.getOrDefault(major, 0).doubleValue());
	        }
	        
	        Map<String,Object> tongjiMap=new HashMap<String,Object>();
			tongjiMap.put("reasonNames", reasonNames);
			tongjiMap.put("reasonValues", reasonValues);
			tongjiMap.put("majorNames", majorNames);
			tongjiMap.put("majorValues", majorValues);
			tongjiMap.put("totalUnemployed", unemployedCount);
			JSONArray jsonArray = JSONArray.fromObject(reasonData);
			return Response.tongjiSuccess(tongjiMap,jsonArray);
		} catch (Exception e) {
			e.printStackTrace();
			return Response.error(204,"服务器错误");
		}
	}

	//数据分析接口 - 获取校企供需对比
	@ResponseBody
	@RequestMapping("/getSupplyDemand")
	public Response getSupplyDemand(@RequestBody(required=false) Object req, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		try {
			Shuju shujuQuery = new Shuju();
			List<Shuju> shujus = shujuService.queryShujus(shujuQuery, 0, 0, null, null, null, null);
			
			User userQuery = new User();
			List<User> users = userService.queryUsers(userQuery, null, 0, 0, null, null, null, null);
			
			int totalJobs = 0;
			for(Shuju s : shujus){
				if(s.getShujuZong() != null){
					totalJobs += s.getShujuZong();
				}
			}
			
			int jobSeekers = users.size();
			
			Map<String, Object> tongjiMap = new HashMap<String, Object>();
			tongjiMap.put("totalJobs", totalJobs);
			tongjiMap.put("jobSeekers", jobSeekers);
			tongjiMap.put("ratio", jobSeekers > 0 ? Math.round((double)totalJobs / jobSeekers * 100) / 100.0 : 0);
			
			return Response.tongjiSuccess(tongjiMap, null);
		} catch (Exception e) {
			e.printStackTrace();
			return Response.error(204,"服务器错误");
		}
	}
}
