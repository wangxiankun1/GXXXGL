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
public class SjlaiyuanController {
	@Autowired
	private SjlaiyuanService sjlaiyuanService;

	//获取列表，搜索条件，返回数据
	@ResponseBody
	@RequestMapping("/getSjlaiyuans")
	public Response getSjlaiyuans(@RequestBody Object req,HttpServletRequest request,
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
		String sjlaiyuanName = map.get("sjlaiyuanName");
		String sjlaiyuanPhone = map.get("sjlaiyuanPhone");
		String sjlaiyuanId = map.get("sjlaiyuanId");
		String sjlaiyuanType1 = map.get("sjlaiyuanType1");
		String sjlaiyuanType = map.get("sjlaiyuanType");
		//初始化
		Sjlaiyuan sjlaiyuan = new Sjlaiyuan();
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
			if (StringUtil.isNotEmpty(sjlaiyuanName)) {
				sjlaiyuan.setSjlaiyuanName(sjlaiyuanName);
			}
			if (StringUtil.isNotEmpty(sjlaiyuanPhone)) {
				sjlaiyuan.setSjlaiyuanPhone(sjlaiyuanPhone);
			}
			if (StringUtil.isNotEmpty(sjlaiyuanId)) {
				sjlaiyuan.setSjlaiyuanId(Integer.parseInt(sjlaiyuanId));
			}
			if (StringUtil.isNotEmpty(sjlaiyuanType)) {
				sjlaiyuan.setSjlaiyuanType(Integer.parseInt(sjlaiyuanType));
			}
			if (StringUtil.isNotEmpty(sjlaiyuanType1)) {
				sjlaiyuan.setSjlaiyuanType1(Integer.parseInt(sjlaiyuanType1));
			}
			/****初始化复制，搜索参数结束****/
			//总数
			int total = sjlaiyuanService.querySjlaiyuans(sjlaiyuan, 0,0).size();
			//获取返回列表
			List<Sjlaiyuan> sjlaiyuans = new ArrayList<Sjlaiyuan>();
			sjlaiyuans = sjlaiyuanService.querySjlaiyuans(sjlaiyuan, pageBean.getStart(), pageBean.getRows());
			//返回页面（列表、总数、行数）
			return Response.success(sjlaiyuans,total,Integer.parseInt(rows));
		} catch (Exception e) {
			//e.printStackTrace();
			return Response.error(204,"服务器错误");
		}
	}

	//添加修改同样一个方法，如果id为空就是新增，如果id不为空就会加载原有数据，然后更新数据
	@ResponseBody
	@RequestMapping("/addSjlaiyuan")
	public Response addSjlaiyuan(@RequestBody Object req,HttpServletRequest request, HttpServletResponse response)
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
			String sjlaiyuanName = map.get("sjlaiyuanName");
			String sjlaiyuanPhone = map.get("sjlaiyuanPhone");
			String sjlaiyuanMark = map.get("sjlaiyuanMark");
			String sjlaiyuanMark1 = map.get("sjlaiyuanMark1");
			String sjlaiyuanMark2 = map.get("sjlaiyuanMark2");
			String sjlaiyuanDizhi = map.get("sjlaiyuanDizhi");
			String sjlaiyuanDate = map.get("sjlaiyuanDate");
			String sjlaiyuanDate1 = map.get("sjlaiyuanDate1");
			String sjlaiyuanType = map.get("sjlaiyuanType");
			String sjlaiyuanType1 = map.get("sjlaiyuanType1");
			String sjlaiyuanDouble = map.get("sjlaiyuanDouble");
			String sjlaiyuanDouble1 = map.get("sjlaiyuanDouble1");
			String sjlaiyuanId = map.get("sjlaiyuanId");
			//model初始化
			Sjlaiyuan sjlaiyuan = new Sjlaiyuan();
			//判断id是否为空，如果不为空则加载原有的model
			if (StringUtil.isNotEmpty(sjlaiyuanId)) {
				sjlaiyuan = sjlaiyuanService.getSjlaiyuan(Integer.parseInt(sjlaiyuanId));
			}
			/****初始化参数赋值****/
			if (StringUtil.isNotEmpty(sjlaiyuanName)) {
				sjlaiyuan.setSjlaiyuanName(sjlaiyuanName);
			}
			if (StringUtil.isNotEmpty(sjlaiyuanPhone)) {
				sjlaiyuan.setSjlaiyuanPhone(sjlaiyuanPhone);
			}
			if (StringUtil.isNotEmpty(sjlaiyuanMark)) {
				sjlaiyuan.setSjlaiyuanMark(sjlaiyuanMark);
			}
			if (StringUtil.isNotEmpty(sjlaiyuanMark1)) {
				sjlaiyuan.setSjlaiyuanMark1(sjlaiyuanMark1);
			}
			if (StringUtil.isNotEmpty(sjlaiyuanMark2)) {
				sjlaiyuan.setSjlaiyuanMark2(sjlaiyuanMark2);
			}
			/****初始化参数赋值****/
			if (StringUtil.isNotEmpty(sjlaiyuanDizhi)) {
				String str[] = sjlaiyuanDizhi.split("/");
				sjlaiyuanDizhi = str[str.length-1];
				sjlaiyuan.setSjlaiyuanDizhi(request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/file/" + sjlaiyuanDizhi);
			}
			if (StringUtil.isNotEmpty(sjlaiyuanDate)) {
				sjlaiyuan.setSjlaiyuanDate(DateUtil.formatString(sjlaiyuanDate,
						"yyyy-MM-dd HH:mm:ss"));
			}
			if (StringUtil.isNotEmpty(sjlaiyuanDate1)) {
				sjlaiyuan.setSjlaiyuanDate1(DateUtil.formatString(sjlaiyuanDate1,
						"yyyy-MM-dd HH:mm:ss"));
			}
			if (StringUtil.isNotEmpty(sjlaiyuanType)) {
				sjlaiyuan.setSjlaiyuanType(Integer.parseInt(sjlaiyuanType));
			}
			if (StringUtil.isNotEmpty(sjlaiyuanType1)) {
				sjlaiyuan.setSjlaiyuanType1(Integer.parseInt(sjlaiyuanType1));
			}
			if (StringUtil.isNotEmpty(sjlaiyuanDouble)) {
				sjlaiyuan.setSjlaiyuanDouble(Double.parseDouble(sjlaiyuanDouble));
			}
			if (StringUtil.isNotEmpty(sjlaiyuanDouble1)) {
				sjlaiyuan.setSjlaiyuanDouble1(Double.parseDouble(sjlaiyuanDouble1));
			}
			/****初始化参数赋值结束****/
			//id不为空，修改（更改状态等）；Id为空就是增加；然后返回结果
			if (StringUtil.isNotEmpty(sjlaiyuanId)) {
				Date date = new Date();
				sjlaiyuan.setSjlaiyuanDate(date);
				sjlaiyuanService.modifySjlaiyuan(sjlaiyuan);
				return Response.success();
			} else {
				Date date = new Date();
				sjlaiyuan.setSjlaiyuanDate(date);
				sjlaiyuanService.save(sjlaiyuan);
				return Response.success();
			}
		} catch (Exception e) {
			//e.printStackTrace();
			return Response.error(204,"服务器错误");
		}
	}

	//删除数据
	@ResponseBody
	@RequestMapping("/deleteSjlaiyuan")
	public Response deleteSjlaiyuan(@RequestBody Object req,HttpServletRequest request, HttpServletResponse response)
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
				sjlaiyuanService.deleteSjlaiyuan(Integer.parseInt(str[i]));
			}
			return Response.success();
		} catch (Exception e) {
			//e.printStackTrace();
			return Response.error(204,"服务器错误");
		}
	}

	//下拉列表参数，跟搜索基本上一样，但是返回的是所有值，不分页
	@ResponseBody
	@RequestMapping("/sjlaiyuanComboList")
	public Response sjlaiyuanComboList(@RequestBody Object req,HttpServletRequest request, HttpServletResponse response)
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
		String sjlaiyuanType1 = map.get("sjlaiyuanType1");
		String sjlaiyuanType = map.get("sjlaiyuanType");
		Sjlaiyuan sjlaiyuan = new Sjlaiyuan();
		try {
			if (StringUtil.isNotEmpty(sjlaiyuanType)) {
				sjlaiyuan.setSjlaiyuanType(Integer.parseInt(sjlaiyuanType));
			}
			if (StringUtil.isNotEmpty(sjlaiyuanType1)) {
				sjlaiyuan.setSjlaiyuanType1(Integer.parseInt(sjlaiyuanType1));
			}
			List<Sjlaiyuan> sjlaiyuans = new ArrayList<Sjlaiyuan>();
			sjlaiyuans = sjlaiyuanService.querySjlaiyuans(sjlaiyuan, 0, 0);
			return Response.success(sjlaiyuans);
		} catch (Exception e) {
			//e.printStackTrace();
			return Response.error(204,"服务器错误");
		}
	}
}
