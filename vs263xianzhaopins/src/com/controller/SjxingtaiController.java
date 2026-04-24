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
public class SjxingtaiController {
	@Autowired
	private SjxingtaiService sjxingtaiService;

	//获取列表，搜索条件，返回数据
	@ResponseBody
	@RequestMapping("/getSjxingtais")
	public Response getSjxingtais(@RequestBody Object req,HttpServletRequest request,
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
		String sjxingtaiName = map.get("sjxingtaiName");
		String sjxingtaiPhone = map.get("sjxingtaiPhone");
		String sjxingtaiId = map.get("sjxingtaiId");
		String sjxingtaiType1 = map.get("sjxingtaiType1");
		String sjxingtaiType = map.get("sjxingtaiType");
		//初始化
		Sjxingtai sjxingtai = new Sjxingtai();
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
			if (StringUtil.isNotEmpty(sjxingtaiName)) {
				sjxingtai.setSjxingtaiName(sjxingtaiName);
			}
			if (StringUtil.isNotEmpty(sjxingtaiPhone)) {
				sjxingtai.setSjxingtaiPhone(sjxingtaiPhone);
			}
			if (StringUtil.isNotEmpty(sjxingtaiId)) {
				sjxingtai.setSjxingtaiId(Integer.parseInt(sjxingtaiId));
			}
			if (StringUtil.isNotEmpty(sjxingtaiType)) {
				sjxingtai.setSjxingtaiType(Integer.parseInt(sjxingtaiType));
			}
			if (StringUtil.isNotEmpty(sjxingtaiType1)) {
				sjxingtai.setSjxingtaiType1(Integer.parseInt(sjxingtaiType1));
			}
			/****初始化复制，搜索参数结束****/
			//总数
			int total = sjxingtaiService.querySjxingtais(sjxingtai, 0,0).size();
			//获取返回列表
			List<Sjxingtai> sjxingtais = new ArrayList<Sjxingtai>();
			sjxingtais = sjxingtaiService.querySjxingtais(sjxingtai, pageBean.getStart(), pageBean.getRows());
			//返回页面（列表、总数、行数）
			return Response.success(sjxingtais,total,Integer.parseInt(rows));
		} catch (Exception e) {
			//e.printStackTrace();
			return Response.error(204,"服务器错误");
		}
	}

	//添加修改同样一个方法，如果id为空就是新增，如果id不为空就会加载原有数据，然后更新数据
	@ResponseBody
	@RequestMapping("/addSjxingtai")
	public Response addSjxingtai(@RequestBody Object req,HttpServletRequest request, HttpServletResponse response)
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
			String sjxingtaiName = map.get("sjxingtaiName");
			String sjxingtaiPhone = map.get("sjxingtaiPhone");
			String sjxingtaiMark = map.get("sjxingtaiMark");
			String sjxingtaiMark1 = map.get("sjxingtaiMark1");
			String sjxingtaiMark2 = map.get("sjxingtaiMark2");
			String sjxingtaiDizhi = map.get("sjxingtaiDizhi");
			String sjxingtaiDate = map.get("sjxingtaiDate");
			String sjxingtaiDate1 = map.get("sjxingtaiDate1");
			String sjxingtaiType = map.get("sjxingtaiType");
			String sjxingtaiType1 = map.get("sjxingtaiType1");
			String sjxingtaiDouble = map.get("sjxingtaiDouble");
			String sjxingtaiDouble1 = map.get("sjxingtaiDouble1");
			String sjxingtaiId = map.get("sjxingtaiId");
			//model初始化
			Sjxingtai sjxingtai = new Sjxingtai();
			//判断id是否为空，如果不为空则加载原有的model
			if (StringUtil.isNotEmpty(sjxingtaiId)) {
				sjxingtai = sjxingtaiService.getSjxingtai(Integer.parseInt(sjxingtaiId));
			}
			/****初始化参数赋值****/
			if (StringUtil.isNotEmpty(sjxingtaiName)) {
				sjxingtai.setSjxingtaiName(sjxingtaiName);
			}
			if (StringUtil.isNotEmpty(sjxingtaiPhone)) {
				sjxingtai.setSjxingtaiPhone(sjxingtaiPhone);
			}
			if (StringUtil.isNotEmpty(sjxingtaiMark)) {
				sjxingtai.setSjxingtaiMark(sjxingtaiMark);
			}
			if (StringUtil.isNotEmpty(sjxingtaiMark1)) {
				sjxingtai.setSjxingtaiMark1(sjxingtaiMark1);
			}
			if (StringUtil.isNotEmpty(sjxingtaiMark2)) {
				sjxingtai.setSjxingtaiMark2(sjxingtaiMark2);
			}
			if (StringUtil.isNotEmpty(sjxingtaiDizhi)) {
				sjxingtai.setSjxingtaiDizhi(sjxingtaiDizhi);
			}
			if (StringUtil.isNotEmpty(sjxingtaiDate)) {
				sjxingtai.setSjxingtaiDate(DateUtil.formatString(sjxingtaiDate,
						"yyyy-MM-dd HH:mm:ss"));
			}
			if (StringUtil.isNotEmpty(sjxingtaiDate1)) {
				sjxingtai.setSjxingtaiDate1(DateUtil.formatString(sjxingtaiDate1,
						"yyyy-MM-dd HH:mm:ss"));
			}
			if (StringUtil.isNotEmpty(sjxingtaiType)) {
				sjxingtai.setSjxingtaiType(Integer.parseInt(sjxingtaiType));
			}
			if (StringUtil.isNotEmpty(sjxingtaiType1)) {
				sjxingtai.setSjxingtaiType1(Integer.parseInt(sjxingtaiType1));
			}
			if (StringUtil.isNotEmpty(sjxingtaiDouble)) {
				sjxingtai.setSjxingtaiDouble(Double.parseDouble(sjxingtaiDouble));
			}
			if (StringUtil.isNotEmpty(sjxingtaiDouble1)) {
				sjxingtai.setSjxingtaiDouble1(Double.parseDouble(sjxingtaiDouble1));
			}
			/****初始化参数赋值结束****/
			//id不为空，修改（更改状态等）；Id为空就是增加；然后返回结果
			if (StringUtil.isNotEmpty(sjxingtaiId)) {
				Date date = new Date();
				sjxingtai.setSjxingtaiDate(date);
				sjxingtaiService.modifySjxingtai(sjxingtai);
				return Response.success();
			} else {
				Date date = new Date();
				sjxingtai.setSjxingtaiDate(date);
				sjxingtaiService.save(sjxingtai);
				return Response.success();
			}
		} catch (Exception e) {
			//e.printStackTrace();
			return Response.error(204,"服务器错误");
		}
	}

	//删除数据
	@ResponseBody
	@RequestMapping("/deleteSjxingtai")
	public Response deleteSjxingtai(@RequestBody Object req,HttpServletRequest request, HttpServletResponse response)
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
				sjxingtaiService.deleteSjxingtai(Integer.parseInt(str[i]));
			}
			return Response.success();
		} catch (Exception e) {
			//e.printStackTrace();
			return Response.error(204,"服务器错误");
		}
	}

	//下拉列表参数，跟搜索基本上一样，但是返回的是所有值，不分页
	@ResponseBody
	@RequestMapping("/sjxingtaiComboList")
	public Response sjxingtaiComboList(@RequestBody Object req,HttpServletRequest request, HttpServletResponse response)
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
		String sjxingtaiType1 = map.get("sjxingtaiType1");
		String sjxingtaiType = map.get("sjxingtaiType");
		Sjxingtai sjxingtai = new Sjxingtai();
		try {
			if (StringUtil.isNotEmpty(sjxingtaiType)) {
				sjxingtai.setSjxingtaiType(Integer.parseInt(sjxingtaiType));
			}
			if (StringUtil.isNotEmpty(sjxingtaiType1)) {
				sjxingtai.setSjxingtaiType1(Integer.parseInt(sjxingtaiType1));
			}
			List<Sjxingtai> sjxingtais = new ArrayList<Sjxingtai>();
			sjxingtais = sjxingtaiService.querySjxingtais(sjxingtai, 0, 0);
			return Response.success(sjxingtais);
		} catch (Exception e) {
			//e.printStackTrace();
			return Response.error(204,"服务器错误");
		}
	}
}
