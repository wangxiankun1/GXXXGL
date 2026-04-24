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
public class SjxiaoxiController {
	@Autowired
	private SjxiaoxiService sjxiaoxiService;

	//获取列表，搜索条件，返回数据
	@ResponseBody
	@RequestMapping("/getSjxiaoxis")
	public Response getSjxiaoxis(@RequestBody Object req,HttpServletRequest request,
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
		String sjxiaoxiName = map.get("sjxiaoxiName");
		String sjxiaoxiPhone = map.get("sjxiaoxiPhone");
		String sjxiaoxiId = map.get("sjxiaoxiId");
		String sjxiaoxiType1 = map.get("sjxiaoxiType1");
		String sjxiaoxiType = map.get("sjxiaoxiType");
		//初始化
		Sjxiaoxi sjxiaoxi = new Sjxiaoxi();
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
			if (StringUtil.isNotEmpty(sjxiaoxiName)) {
				sjxiaoxi.setSjxiaoxiName(sjxiaoxiName);
			}
			if (StringUtil.isNotEmpty(sjxiaoxiPhone)) {
				sjxiaoxi.setSjxiaoxiPhone(sjxiaoxiPhone);
			}
			if (StringUtil.isNotEmpty(sjxiaoxiId)) {
				sjxiaoxi.setSjxiaoxiId(Integer.parseInt(sjxiaoxiId));
			}
			if (StringUtil.isNotEmpty(sjxiaoxiType)) {
				sjxiaoxi.setSjxiaoxiType(Integer.parseInt(sjxiaoxiType));
			}
			if (StringUtil.isNotEmpty(sjxiaoxiType1)) {
				sjxiaoxi.setSjxiaoxiType1(Integer.parseInt(sjxiaoxiType1));
			}
			/****初始化复制，搜索参数结束****/
			//总数
			int total = sjxiaoxiService.querySjxiaoxis(sjxiaoxi, 0,0).size();
			//获取返回列表
			List<Sjxiaoxi> sjxiaoxis = new ArrayList<Sjxiaoxi>();
			sjxiaoxis = sjxiaoxiService.querySjxiaoxis(sjxiaoxi, pageBean.getStart(), pageBean.getRows());
			//返回页面（列表、总数、行数）
			return Response.success(sjxiaoxis,total,Integer.parseInt(rows));
		} catch (Exception e) {
			//e.printStackTrace();
			return Response.error(204,"服务器错误");
		}
	}

	//添加修改同样一个方法，如果id为空就是新增，如果id不为空就会加载原有数据，然后更新数据
	@ResponseBody
	@RequestMapping("/addSjxiaoxi")
	public Response addSjxiaoxi(@RequestBody Object req,HttpServletRequest request, HttpServletResponse response)
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
			String sjxiaoxiName = map.get("sjxiaoxiName");
			String sjxiaoxiPhone = map.get("sjxiaoxiPhone");
			String sjxiaoxiMark = map.get("sjxiaoxiMark");
			String sjxiaoxiMark1 = map.get("sjxiaoxiMark1");
			String sjxiaoxiMark2 = map.get("sjxiaoxiMark2");
			String sjxiaoxiDizhi = map.get("sjxiaoxiDizhi");
			String sjxiaoxiDate = map.get("sjxiaoxiDate");
			String sjxiaoxiDate1 = map.get("sjxiaoxiDate1");
			String sjxiaoxiType = map.get("sjxiaoxiType");
			String sjxiaoxiType1 = map.get("sjxiaoxiType1");
			String sjxiaoxiDouble = map.get("sjxiaoxiDouble");
			String sjxiaoxiDouble1 = map.get("sjxiaoxiDouble1");
			String sjxiaoxiId = map.get("sjxiaoxiId");
			//model初始化
			Sjxiaoxi sjxiaoxi = new Sjxiaoxi();
			//判断id是否为空，如果不为空则加载原有的model
			if (StringUtil.isNotEmpty(sjxiaoxiId)) {
				sjxiaoxi = sjxiaoxiService.getSjxiaoxi(Integer.parseInt(sjxiaoxiId));
			}
			/****初始化参数赋值****/
			if (StringUtil.isNotEmpty(sjxiaoxiName)) {
				sjxiaoxi.setSjxiaoxiName(sjxiaoxiName);
			}
			if (StringUtil.isNotEmpty(sjxiaoxiPhone)) {
				sjxiaoxi.setSjxiaoxiPhone(sjxiaoxiPhone);
			}
			if (StringUtil.isNotEmpty(sjxiaoxiMark)) {
				sjxiaoxi.setSjxiaoxiMark(sjxiaoxiMark);
			}
			if (StringUtil.isNotEmpty(sjxiaoxiMark1)) {
				sjxiaoxi.setSjxiaoxiMark1(sjxiaoxiMark1);
			}
			if (StringUtil.isNotEmpty(sjxiaoxiMark2)) {
				sjxiaoxi.setSjxiaoxiMark2(sjxiaoxiMark2);
			}
			if (StringUtil.isNotEmpty(sjxiaoxiDizhi)) {
				sjxiaoxi.setSjxiaoxiDizhi(sjxiaoxiDizhi);
			}
			if (StringUtil.isNotEmpty(sjxiaoxiDate)) {
				sjxiaoxi.setSjxiaoxiDate(DateUtil.formatString(sjxiaoxiDate,
						"yyyy-MM-dd HH:mm:ss"));
			}
			if (StringUtil.isNotEmpty(sjxiaoxiDate1)) {
				sjxiaoxi.setSjxiaoxiDate1(DateUtil.formatString(sjxiaoxiDate1,
						"yyyy-MM-dd HH:mm:ss"));
			}
			if (StringUtil.isNotEmpty(sjxiaoxiType)) {
				sjxiaoxi.setSjxiaoxiType(Integer.parseInt(sjxiaoxiType));
			}
			if (StringUtil.isNotEmpty(sjxiaoxiType1)) {
				sjxiaoxi.setSjxiaoxiType1(Integer.parseInt(sjxiaoxiType1));
			}
			if (StringUtil.isNotEmpty(sjxiaoxiDouble)) {
				sjxiaoxi.setSjxiaoxiDouble(Double.parseDouble(sjxiaoxiDouble));
			}
			if (StringUtil.isNotEmpty(sjxiaoxiDouble1)) {
				sjxiaoxi.setSjxiaoxiDouble1(Double.parseDouble(sjxiaoxiDouble1));
			}
			/****初始化参数赋值结束****/
			//id不为空，修改（更改状态等）；Id为空就是增加；然后返回结果
			if (StringUtil.isNotEmpty(sjxiaoxiId)) {
				Date date = new Date();
				sjxiaoxi.setSjxiaoxiDate(date);
				sjxiaoxiService.modifySjxiaoxi(sjxiaoxi);
				return Response.success();
			} else {
				Date date = new Date();
				sjxiaoxi.setSjxiaoxiDate(date);
				sjxiaoxiService.save(sjxiaoxi);
				return Response.success();
			}
		} catch (Exception e) {
			//e.printStackTrace();
			return Response.error(204,"服务器错误");
		}
	}

	//删除数据
	@ResponseBody
	@RequestMapping("/deleteSjxiaoxi")
	public Response deleteSjxiaoxi(@RequestBody Object req,HttpServletRequest request, HttpServletResponse response)
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
				sjxiaoxiService.deleteSjxiaoxi(Integer.parseInt(str[i]));
			}
			return Response.success();
		} catch (Exception e) {
			//e.printStackTrace();
			return Response.error(204,"服务器错误");
		}
	}

	//下拉列表参数，跟搜索基本上一样，但是返回的是所有值，不分页
	@ResponseBody
	@RequestMapping("/sjxiaoxiComboList")
	public Response sjxiaoxiComboList(@RequestBody Object req,HttpServletRequest request, HttpServletResponse response)
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
		String sjxiaoxiId = (String) request.getParameter("sjxiaoxiId");
		String sjxiaoxiType1 = map.get("sjxiaoxiType1");
		String sjxiaoxiType = map.get("sjxiaoxiType");
		Sjxiaoxi sjxiaoxi = new Sjxiaoxi();
		try {
			if (StringUtil.isNotEmpty(sjxiaoxiType)) {
				sjxiaoxi.setSjxiaoxiType(Integer.parseInt(sjxiaoxiType));
			}
			if (StringUtil.isNotEmpty(sjxiaoxiType1)) {
				sjxiaoxi.setSjxiaoxiType1(Integer.parseInt(sjxiaoxiType1));
			}
			if (StringUtil.isNotEmpty(sjxiaoxiId)) {
				sjxiaoxi.setSjxiaoxiId(Integer.parseInt(sjxiaoxiId));
			}
			List<Sjxiaoxi> sjxiaoxis = new ArrayList<Sjxiaoxi>();
			sjxiaoxis = sjxiaoxiService.querySjxiaoxis(sjxiaoxi, 0, 0);
			return Response.success(sjxiaoxis.get(0));
		} catch (Exception e) {
			//e.printStackTrace();
			return Response.error(204,"服务器错误");
		}
	}
}
