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
public class SjlianjieController {
	@Autowired
	private SjlianjieService sjlianjieService;

	//获取列表，搜索条件，返回数据
	@ResponseBody
	@RequestMapping("/getSjlianjies")
	public Response getSjlianjies(@RequestBody Object req,HttpServletRequest request,
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
		String sjlianjieName = map.get("sjlianjieName");
		String sjlianjiePhone = map.get("sjlianjiePhone");
		String sjlianjieId = map.get("sjlianjieId");
		String sjlianjieType1 = map.get("sjlianjieType1");
		String sjlianjieType = map.get("sjlianjieType");
		//初始化
		Sjlianjie sjlianjie = new Sjlianjie();
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
			if (StringUtil.isNotEmpty(sjlianjieName)) {
				sjlianjie.setSjlianjieName(sjlianjieName);
			}
			if (StringUtil.isNotEmpty(sjlianjiePhone)) {
				sjlianjie.setSjlianjiePhone(sjlianjiePhone);
			}
			if (StringUtil.isNotEmpty(sjlianjieId)) {
				sjlianjie.setSjlianjieId(Integer.parseInt(sjlianjieId));
			}
			if (StringUtil.isNotEmpty(sjlianjieType)) {
				sjlianjie.setSjlianjieType(Integer.parseInt(sjlianjieType));
			}
			if (StringUtil.isNotEmpty(sjlianjieType1)) {
				sjlianjie.setSjlianjieType1(Integer.parseInt(sjlianjieType1));
			}
			/****初始化复制，搜索参数结束****/
			//总数
			int total = sjlianjieService.querySjlianjies(sjlianjie, 0,0).size();
			//获取返回列表
			List<Sjlianjie> sjlianjies = new ArrayList<Sjlianjie>();
			sjlianjies = sjlianjieService.querySjlianjies(sjlianjie, pageBean.getStart(), pageBean.getRows());
			//返回页面（列表、总数、行数）
			return Response.success(sjlianjies,total,Integer.parseInt(rows));
		} catch (Exception e) {
			//e.printStackTrace();
			return Response.error(204,"服务器错误");
		}
	}

	//添加修改同样一个方法，如果id为空就是新增，如果id不为空就会加载原有数据，然后更新数据
	@ResponseBody
	@RequestMapping("/addSjlianjie")
	public Response addSjlianjie(@RequestBody Object req,HttpServletRequest request, HttpServletResponse response)
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
			String sjlianjieName = map.get("sjlianjieName");
			String sjlianjiePhone = map.get("sjlianjiePhone");
			String sjlianjieMark = map.get("sjlianjieMark");
			String sjlianjieMark1 = map.get("sjlianjieMark1");
			String sjlianjieMark2 = map.get("sjlianjieMark2");
			String sjlianjieDizhi = map.get("sjlianjieDizhi");
			String sjlianjieDate = map.get("sjlianjieDate");
			String sjlianjieDate1 = map.get("sjlianjieDate1");
			String sjlianjieType = map.get("sjlianjieType");
			String sjlianjieType1 = map.get("sjlianjieType1");
			String sjlianjieDouble = map.get("sjlianjieDouble");
			String sjlianjieDouble1 = map.get("sjlianjieDouble1");
			String sjlianjieId = map.get("sjlianjieId");
			//model初始化
			Sjlianjie sjlianjie = new Sjlianjie();
			//判断id是否为空，如果不为空则加载原有的model
			if (StringUtil.isNotEmpty(sjlianjieId)) {
				sjlianjie = sjlianjieService.getSjlianjie(Integer.parseInt(sjlianjieId));
			}
			/****初始化参数赋值****/
			if (StringUtil.isNotEmpty(sjlianjieName)) {
				sjlianjie.setSjlianjieName(sjlianjieName);
			}
			if (StringUtil.isNotEmpty(sjlianjiePhone)) {
				sjlianjie.setSjlianjiePhone(sjlianjiePhone);
			}
			if (StringUtil.isNotEmpty(sjlianjieMark)) {
				sjlianjie.setSjlianjieMark(sjlianjieMark);
			}
			if (StringUtil.isNotEmpty(sjlianjieMark1)) {
				sjlianjie.setSjlianjieMark1(sjlianjieMark1);
			}
			if (StringUtil.isNotEmpty(sjlianjieMark2)) {
				sjlianjie.setSjlianjieMark2(sjlianjieMark2);
			}
			if (StringUtil.isNotEmpty(sjlianjieDizhi)) {
				sjlianjie.setSjlianjieDizhi(sjlianjieDizhi);
			}
			if (StringUtil.isNotEmpty(sjlianjieDate)) {
				sjlianjie.setSjlianjieDate(DateUtil.formatString(sjlianjieDate,
						"yyyy-MM-dd HH:mm:ss"));
			}
			if (StringUtil.isNotEmpty(sjlianjieDate1)) {
				sjlianjie.setSjlianjieDate1(DateUtil.formatString(sjlianjieDate1,
						"yyyy-MM-dd HH:mm:ss"));
			}
			if (StringUtil.isNotEmpty(sjlianjieType)) {
				sjlianjie.setSjlianjieType(Integer.parseInt(sjlianjieType));
			}
			if (StringUtil.isNotEmpty(sjlianjieType1)) {
				sjlianjie.setSjlianjieType1(Integer.parseInt(sjlianjieType1));
			}
			if (StringUtil.isNotEmpty(sjlianjieDouble)) {
				sjlianjie.setSjlianjieDouble(Double.parseDouble(sjlianjieDouble));
			}
			if (StringUtil.isNotEmpty(sjlianjieDouble1)) {
				sjlianjie.setSjlianjieDouble1(Double.parseDouble(sjlianjieDouble1));
			}
			/****初始化参数赋值结束****/
			//id不为空，修改（更改状态等）；Id为空就是增加；然后返回结果
			if (StringUtil.isNotEmpty(sjlianjieId)) {
				Date date = new Date();
				sjlianjie.setSjlianjieDate(date);
				sjlianjieService.modifySjlianjie(sjlianjie);
				return Response.success();
			} else {
				Date date = new Date();
				sjlianjie.setSjlianjieDate(date);
				sjlianjieService.save(sjlianjie);
				return Response.success();
			}
		} catch (Exception e) {
			//e.printStackTrace();
			return Response.error(204,"服务器错误");
		}
	}

	//删除数据
	@ResponseBody
	@RequestMapping("/deleteSjlianjie")
	public Response deleteSjlianjie(@RequestBody Object req,HttpServletRequest request, HttpServletResponse response)
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
				sjlianjieService.deleteSjlianjie(Integer.parseInt(str[i]));
			}
			return Response.success();
		} catch (Exception e) {
			//e.printStackTrace();
			return Response.error(204,"服务器错误");
		}
	}

	//下拉列表参数，跟搜索基本上一样，但是返回的是所有值，不分页
	@ResponseBody
	@RequestMapping("/sjlianjieComboList")
	public Response sjlianjieComboList(@RequestBody Object req,HttpServletRequest request, HttpServletResponse response)
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
		String sjlianjieType1 = map.get("sjlianjieType1");
		String sjlianjieType = map.get("sjlianjieType");
		Sjlianjie sjlianjie = new Sjlianjie();
		try {
			if (StringUtil.isNotEmpty(sjlianjieType)) {
				sjlianjie.setSjlianjieType(Integer.parseInt(sjlianjieType));
			}
			if (StringUtil.isNotEmpty(sjlianjieType1)) {
				sjlianjie.setSjlianjieType1(Integer.parseInt(sjlianjieType1));
			}
			List<Sjlianjie> sjlianjies = new ArrayList<Sjlianjie>();
			sjlianjies = sjlianjieService.querySjlianjies(sjlianjie, 0, 0);
			return Response.success(sjlianjies);
		} catch (Exception e) {
			//e.printStackTrace();
			return Response.error(204,"服务器错误");
		}
	}
}
