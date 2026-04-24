package com.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.model.*;
import com.service.*;
import com.util.*;

@Controller
public class SjleixingController {
	@Autowired
	private SjleixingService sjleixingService;

	//获取列表，搜索条件，返回数据
	@ResponseBody
	@RequestMapping("/getSjleixings")
	public Response getSjleixings(@RequestBody Object req,HttpServletRequest request,
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
		String sjleixingName = map.get("sjleixingName");
		String sjleixingPhone = map.get("sjleixingPhone");
		String sjleixingId = map.get("sjleixingId");
		String sjleixingType1 = map.get("sjleixingType1");
		String sjleixingType = map.get("sjleixingType");
		//初始化
		Sjleixing sjleixing = new Sjleixing();
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
			if (StringUtil.isNotEmpty(sjleixingName)) {
				sjleixing.setSjleixingName(sjleixingName);
			}
			if (StringUtil.isNotEmpty(sjleixingPhone)) {
				sjleixing.setSjleixingPhone(sjleixingPhone);
			}
			if (StringUtil.isNotEmpty(sjleixingId)) {
				sjleixing.setSjleixingId(Integer.parseInt(sjleixingId));
			}
			if (StringUtil.isNotEmpty(sjleixingType)) {
				sjleixing.setSjleixingType(Integer.parseInt(sjleixingType));
			}
			if (StringUtil.isNotEmpty(sjleixingType1)) {
				sjleixing.setSjleixingType1(Integer.parseInt(sjleixingType1));
			}
			/****初始化复制，搜索参数结束****/
			//总数
			int total = sjleixingService.querySjleixings(sjleixing, 0,0).size();
			//获取返回列表
			List<Sjleixing> sjleixings = new ArrayList<Sjleixing>();
			sjleixings = sjleixingService.querySjleixings(sjleixing, pageBean.getStart(), pageBean.getRows());
			//返回页面（列表、总数、行数）
			return Response.success(sjleixings,total,Integer.parseInt(rows));
		} catch (Exception e) {
			//e.printStackTrace();
			return Response.error(204,"服务器错误");
		}
	}

	//添加修改同样一个方法，如果id为空就是新增，如果id不为空就会加载原有数据，然后更新数据
	@ResponseBody
	@RequestMapping("/addSjleixing")
	public Response addSjleixing(@RequestBody Object req,HttpServletRequest request, HttpServletResponse response)
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
			String sjleixingName = map.get("sjleixingName");
			String sjleixingPhone = map.get("sjleixingPhone");
			String sjleixingMark = map.get("sjleixingMark");
			String sjleixingMark1 = map.get("sjleixingMark1");
			String sjleixingMark2 = map.get("sjleixingMark2");
			String sjleixingDizhi = map.get("sjleixingDizhi");
			String sjleixingDate = map.get("sjleixingDate");
			String sjleixingDate1 = map.get("sjleixingDate1");
			String sjleixingType = map.get("sjleixingType");
			String sjleixingType1 = map.get("sjleixingType1");
			String sjleixingDouble = map.get("sjleixingDouble");
			String sjleixingDouble1 = map.get("sjleixingDouble1");
			String sjleixingId = map.get("sjleixingId");
			//model初始化
			Sjleixing sjleixing = new Sjleixing();
			//判断id是否为空，如果不为空则加载原有的model
			if (StringUtil.isNotEmpty(sjleixingId)) {
				sjleixing = sjleixingService.getSjleixing(Integer.parseInt(sjleixingId));
			}
			/****初始化参数赋值****/
			if (StringUtil.isNotEmpty(sjleixingName)) {
				sjleixing.setSjleixingName(sjleixingName);
			}
			if (StringUtil.isNotEmpty(sjleixingPhone)) {
				sjleixing.setSjleixingPhone(sjleixingPhone);
			}
			if (StringUtil.isNotEmpty(sjleixingMark)) {
				sjleixing.setSjleixingMark(sjleixingMark);
			}
			if (StringUtil.isNotEmpty(sjleixingMark1)) {
				sjleixing.setSjleixingMark1(sjleixingMark1);
			}
			if (StringUtil.isNotEmpty(sjleixingMark2)) {
				sjleixing.setSjleixingMark2(sjleixingMark2);
			}
			if (StringUtil.isNotEmpty(sjleixingDizhi)) {
				sjleixing.setSjleixingDizhi(sjleixingDizhi);
			}
			if (StringUtil.isNotEmpty(sjleixingDate)) {
				sjleixing.setSjleixingDate(DateUtil.formatString(sjleixingDate,
						"yyyy-MM-dd HH:mm:ss"));
			}
			if (StringUtil.isNotEmpty(sjleixingDate1)) {
				sjleixing.setSjleixingDate1(DateUtil.formatString(sjleixingDate1,
						"yyyy-MM-dd HH:mm:ss"));
			}
			if (StringUtil.isNotEmpty(sjleixingType)) {
				sjleixing.setSjleixingType(Integer.parseInt(sjleixingType));
			}
			if (StringUtil.isNotEmpty(sjleixingType1)) {
				sjleixing.setSjleixingType1(Integer.parseInt(sjleixingType1));
			}
			if (StringUtil.isNotEmpty(sjleixingDouble)) {
				sjleixing.setSjleixingDouble(Double.parseDouble(sjleixingDouble));
			}
			if (StringUtil.isNotEmpty(sjleixingDouble1)) {
				sjleixing.setSjleixingDouble1(Double.parseDouble(sjleixingDouble1));
			}
			/****初始化参数赋值结束****/
			//id不为空，修改（更改状态等）；Id为空就是增加；然后返回结果
			if (StringUtil.isNotEmpty(sjleixingId)) {
				Date date = new Date();
				sjleixing.setSjleixingDate(date);
				sjleixingService.modifySjleixing(sjleixing);
				return Response.success();
			} else {
				Date date = new Date();
				sjleixing.setSjleixingDate(date);
				sjleixingService.save(sjleixing);
				return Response.success();
			}
		} catch (Exception e) {
			//e.printStackTrace();
			return Response.error(204,"服务器错误");
		}
	}

	//删除数据
	@ResponseBody
	@RequestMapping("/deleteSjleixing")
	public Response deleteSjleixing(@RequestBody Object req,HttpServletRequest request, HttpServletResponse response)
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
				sjleixingService.deleteSjleixing(Integer.parseInt(str[i]));
			}
			return Response.success();
		} catch (Exception e) {
			//e.printStackTrace();
			return Response.error(204,"服务器错误");
		}
	}

	//下拉列表参数，跟搜索基本上一样，但是返回的是所有值，不分页
	@ResponseBody
	@RequestMapping("/sjleixingComboList")
	public Response sjleixingComboList(@RequestBody Object req,HttpServletRequest request, HttpServletResponse response)
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
		String sjleixingType1 = map.get("sjleixingType1");
		String sjleixingType = map.get("sjleixingType");
		Sjleixing sjleixing = new Sjleixing();
		try {
			if (StringUtil.isNotEmpty(sjleixingType)) {
				sjleixing.setSjleixingType(Integer.parseInt(sjleixingType));
			}
			if (StringUtil.isNotEmpty(sjleixingType1)) {
				sjleixing.setSjleixingType1(Integer.parseInt(sjleixingType1));
			}
			List<Sjleixing> sjleixings = new ArrayList<Sjleixing>();
			sjleixings = sjleixingService.querySjleixings(sjleixing, 0, 0);
			return Response.success(sjleixings);
		} catch (Exception e) {
			//e.printStackTrace();
			return Response.error(204,"服务器错误");
		}
	}
}
