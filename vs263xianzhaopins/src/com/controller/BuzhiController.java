package com.controller;


import java.util.Date;
import java.util.HashMap;
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

@Controller
public class BuzhiController {
	@Autowired
	private BuzhiService buzhiService;
	@Autowired
	private BuyuanService buyuanService;
	@Autowired
	private BumenService bumenService;
	@Autowired
	private UserService userService;
	@Autowired
	private UxinxiService uxinxiService;

	//添加修改同样一个方法，如果id为空就是新增，如果id不为空就会加载原有数据，然后更新数据
	@ResponseBody
	@RequestMapping("/addBuzhi")
	public Response addBuzhi(@RequestBody Object req,HttpServletRequest request, HttpServletResponse response)
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
			String buzhiName = map.get("buzhiName");
			String buzhiMark = map.get("buzhiMark");
			String buzhiMark1 = map.get("buzhiMark1");
			String buzhiMark2 = map.get("buzhiMark2");
			String buzhiType = map.get("buzhiType");
			String buzhiType1 = map.get("buzhiType1");
			String buzhiType2 = map.get("buzhiType2");
			String buzhiDouble = map.get("buzhiDouble");
			String buzhiDouble1 = map.get("buzhiDouble1");
			String buzhiDouble2 = map.get("buzhiDouble2");
			String buyuanId = map.get("buyuanId");
			String bumenId = map.get("bumenId");
			String buzhiId = map.get("buzhiId");
			//model初始化
			Buzhi buzhi = new Buzhi();
			//判断id是否为空，如果不为空则加载原有的model
			if (StringUtil.isNotEmpty(buzhiId)) {
				buzhi = buzhiService.getBuzhi(Integer.parseInt(buzhiId));
			}
			if (StringUtil.isNotEmpty(buzhiName)) {
				buzhi.setBuzhiName(buzhiName);
			}
			if (StringUtil.isNotEmpty(buzhiMark)) {
				buzhi.setBuzhiMark(buzhiMark);
			}
			if (StringUtil.isNotEmpty(buzhiMark1)) {
				buzhi.setBuzhiMark1(buzhiMark1);
			}
			if (StringUtil.isNotEmpty(buzhiMark2)) {
				buzhi.setBuzhiMark2(buzhiMark2);
			}
			if (StringUtil.isNotEmpty(buzhiType)) {
				buzhi.setBuzhiType(Integer.parseInt(buzhiType));
			}
			if (StringUtil.isNotEmpty(buzhiType1)) {
				buzhi.setBuzhiType1(Integer.parseInt(buzhiType1));
			}
			if (StringUtil.isNotEmpty(buzhiType2)) {
				buzhi.setBuzhiType2(Integer.parseInt(buzhiType2));
			}
			if (StringUtil.isNotEmpty(buzhiDouble)) {
				buzhi.setBuzhiDouble(Double.parseDouble(buzhiDouble));
			}
			if (StringUtil.isNotEmpty(buzhiDouble1)) {
				buzhi.setBuzhiDouble1(Double.parseDouble(buzhiDouble1));
			}
			if (StringUtil.isNotEmpty(buzhiDouble2)) {
				buzhi.setBuzhiDouble2(Double.parseDouble(buzhiDouble2));
			}
			if (StringUtil.isNotEmpty(bumenId)) {
				buzhi.setBumenId(Integer.parseInt(bumenId));
				Uxinxi uxinxi = uxinxiService.getUxinxi(Integer.parseInt(bumenId));
				buzhi.setBumenName(uxinxi.getUxinxiName());
			}
			if (StringUtil.isNotEmpty(buyuanId)) {
				buzhi.setBuyuanId(Integer.parseInt(buyuanId));
				User user = userService.getUser(Integer.parseInt(buyuanId));
				buzhi.setBuyuanName(user.getUserXingming());
			}
			/****初始化参数赋值结束****/
			//id不为空，修改（更改状态等）；Id为空就是增加；然后返回结果
			if (StringUtil.isNotEmpty(buzhiId)) {
				buzhiService.modifyBuzhi(buzhi);
				return Response.success();
			} else {
				buzhiService.save(buzhi);
				return Response.success();
			}
		} catch (Exception e) {
			//e.printStackTrace();
			return Response.error(204,"服务器错误");
		}
	}

	//获取列表，搜索条件，返回数据
	@ResponseBody
	@RequestMapping("/getBuzhis")
	public Response getBuzhis(@RequestBody Object req,HttpServletRequest request,
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
		String buzhiName = map.get("buzhiName");
		String buzhiType = map.get("buzhiType");
		String buzhiType1 = map.get("buzhiType1");
		String buzhiType2 = map.get("buzhiType2");
		String buyuanId = map.get("buyuanId");
		String bumenId = map.get("bumenId");
		//初始化
		Buzhi buzhi = new Buzhi();
		if (StringUtil.isNotEmpty(buzhiName)) {
			buzhi.setBuzhiName(buzhiName);
		}
		/****初始化赋值，搜索参数****/
		if (StringUtil.isNotEmpty(buzhiType)) {
			buzhi.setBuzhiType(Integer.parseInt(buzhiType));
		}
		if (StringUtil.isNotEmpty(buzhiType1)) {
			buzhi.setBuzhiType1(Integer.parseInt(buzhiType1));
		}
		if (StringUtil.isNotEmpty(buzhiType2)) {
			buzhi.setBuzhiType2(Integer.parseInt(buzhiType2));
		}
		if (StringUtil.isNotEmpty(buyuanId)) {
			buzhi.setBuyuanId(Integer.parseInt(buyuanId));
		}
		if (StringUtil.isNotEmpty(bumenId)) {
			buzhi.setBumenId(Integer.parseInt(bumenId));
		}
		/****初始化复制，搜索参数结束****/
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
			//总数
			int total = buzhiService.queryBuzhis(buzhi, 0,0).size();
			//返回页面（列表、总数、行数）
			return Response.success(buzhiService.queryBuzhis(buzhi, pageBean.getStart(), pageBean.getRows()),total,Integer.parseInt(rows));
		} catch (Exception e) {
			//e.printStackTrace();
		}
		return null;
	}

	//删除数据
	@ResponseBody
	@RequestMapping("/deleteBuzhi")
	public Response deleteBuzhi(@RequestBody Object req,HttpServletRequest request, HttpServletResponse response)
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
			String delIds = map.get("delIds");
			String str[] = delIds.split(",");
			for (int i = 0; i < str.length; i++) {
				buzhiService.deleteBuzhi(Integer.parseInt(str[i]));
			}
			return Response.success();
		} catch (Exception e) {
			//e.printStackTrace();
			return Response.error(204,"服务器错误");
		}
	}

	//下拉列表参数，跟搜索基本上一样，但是返回的是所有值，不分页
	@ResponseBody
	@RequestMapping("/buzhiComboList")
	public Response buzhiComboList(@RequestBody Object req,HttpServletRequest request, HttpServletResponse response)
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
		String buzhiName = map.get("buzhiName");
		String buzhiType = map.get("buzhiType");
		String buyuanId = map.get("buyuanId");
		String bumenId = map.get("bumenId");
		Buzhi buzhi = new Buzhi();
		if (StringUtil.isNotEmpty(buzhiName)) {
			buzhi.setBuzhiName(buzhiName);
		}
		if (StringUtil.isNotEmpty(buzhiType)) {
			buzhi.setBuzhiType(Integer.parseInt(buzhiType));
		}
		if (StringUtil.isNotEmpty(buyuanId)) {
			buzhi.setBuyuanId(Integer.parseInt(buyuanId));
		}
		if (StringUtil.isNotEmpty(bumenId)) {
			buzhi.setBumenId(Integer.parseInt(bumenId));
		}
		try {
			return Response.success(buzhiService.queryBuzhis(buzhi,0,0));
		} catch (Exception e) {
			//e.printStackTrace();
			return Response.error(204,"服务器错误");
		}
	}
}
