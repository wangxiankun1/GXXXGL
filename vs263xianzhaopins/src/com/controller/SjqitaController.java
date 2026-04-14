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
public class SjqitaController {
	@Autowired
	private SjqitaService sjqitaService;
	@Autowired
	private SjduochuService sjduochuService;
	@Autowired
	private UserService userService;

	//获取列表，搜索条件，返回数据
	@ResponseBody
	@RequestMapping("/getSjqitas")
	public Response getSjqitas(@RequestBody Object req,HttpServletRequest request,
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
		String sjqitaName = map.get("sjqitaName");
		String sjqitaPhone = map.get("sjqitaPhone");
		String sjqitaId = map.get("sjqitaId");
		String sjqitaType1 = map.get("sjqitaType1");
		String sjqitaType = map.get("sjqitaType");
		//初始化
		Sjqita sjqita = new Sjqita();
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
			if (StringUtil.isNotEmpty(sjqitaName)) {
				sjqita.setSjqitaName(sjqitaName);
			}
			if (StringUtil.isNotEmpty(sjqitaPhone)) {
				sjqita.setSjqitaPhone(sjqitaPhone);
			}
			if (StringUtil.isNotEmpty(sjqitaId)) {
				sjqita.setSjqitaId(Integer.parseInt(sjqitaId));
			}
			if (StringUtil.isNotEmpty(sjqitaType)) {
				sjqita.setSjqitaType(Integer.parseInt(sjqitaType));
			}
			if (StringUtil.isNotEmpty(sjqitaType1)) {
				sjqita.setSjqitaType1(Integer.parseInt(sjqitaType1));
			}
			/****初始化复制，搜索参数结束****/
			//总数
			int total = sjqitaService.querySjqitas(sjqita, 0,0).size();
			//获取返回列表
			List<Sjqita> sjqitas = new ArrayList<Sjqita>();
			sjqitas = sjqitaService.querySjqitas(sjqita, pageBean.getStart(), pageBean.getRows());
			//返回页面（列表、总数、行数）
			return Response.success(sjqitas,total,Integer.parseInt(rows));
		} catch (Exception e) {
			//e.printStackTrace();
			return Response.error(204,"服务器错误");
		}
	}

	//添加修改同样一个方法，如果id为空就是新增，如果id不为空就会加载原有数据，然后更新数据
	@ResponseBody
	@RequestMapping("/addSjqita")
	public Response addSjqita(@RequestBody Object req,HttpServletRequest request, HttpServletResponse response)
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
			String sjqitaName = map.get("sjqitaName");
			String sjqitaPhone = map.get("sjqitaPhone");
			String sjqitaMark = map.get("sjqitaMark");
			String sjqitaMark1 = map.get("sjqitaMark1");
			String sjqitaMark2 = map.get("sjqitaMark2");
			String sjqitaDizhi = map.get("sjqitaDizhi");
			String sjqitaDate = map.get("sjqitaDate");
			String sjqitaDate1 = map.get("sjqitaDate1");
			String sjqitaType = map.get("sjqitaType");
			String sjqitaType1 = map.get("sjqitaType1");
			String sjqitaDouble = map.get("sjqitaDouble");
			String sjqitaDouble1 = map.get("sjqitaDouble1");
			String sjqitaId = map.get("sjqitaId");
			//model初始化
			Sjqita sjqita = new Sjqita();
			//判断id是否为空，如果不为空则加载原有的model
			if (StringUtil.isNotEmpty(sjqitaId)) {
				sjqita = sjqitaService.getSjqita(Integer.parseInt(sjqitaId));
			}
			/****初始化参数赋值****/
			if (StringUtil.isNotEmpty(sjqitaName)) {
				sjqita.setSjqitaName(sjqitaName);
			}
			if (StringUtil.isNotEmpty(sjqitaPhone)) {
				sjqita.setSjqitaPhone(sjqitaPhone);
			}
			if (StringUtil.isNotEmpty(sjqitaMark)) {
				sjqita.setSjqitaMark(sjqitaMark);
			}
			if (StringUtil.isNotEmpty(sjqitaMark1)) {
				sjqita.setSjqitaMark1(sjqitaMark1);
			}
			if (StringUtil.isNotEmpty(sjqitaMark2)) {
				sjqita.setSjqitaMark2(sjqitaMark2);
			}
			if (StringUtil.isNotEmpty(sjqitaDizhi)) {
				sjqita.setSjqitaDizhi(sjqitaDizhi);
			}
			if (StringUtil.isNotEmpty(sjqitaDate)) {
				sjqita.setSjqitaDate(DateUtil.formatString(sjqitaDate,
						"yyyy-MM-dd HH:mm:ss"));
			}
			if (StringUtil.isNotEmpty(sjqitaDate1)) {
				sjqita.setSjqitaDate1(DateUtil.formatString(sjqitaDate1,
						"yyyy-MM-dd HH:mm:ss"));
			}
			if (StringUtil.isNotEmpty(sjqitaType)) {
				sjqita.setSjqitaType(Integer.parseInt(sjqitaType));
			}
			if (StringUtil.isNotEmpty(sjqitaType1)) {
				sjqita.setSjqitaType1(Integer.parseInt(sjqitaType1));
			}
			if (StringUtil.isNotEmpty(sjqitaDouble)) {
				sjqita.setSjqitaDouble(Double.parseDouble(sjqitaDouble));
			}
			if (StringUtil.isNotEmpty(sjqitaDouble1)) {
				sjqita.setSjqitaDouble1(Double.parseDouble(sjqitaDouble1));
			}
			/****初始化参数赋值结束****/
			//id不为空，修改（更改状态等）；Id为空就是增加；然后返回结果
			if (StringUtil.isNotEmpty(sjqitaId)) {
				Date date = new Date();
				sjqita.setSjqitaDate(date);
				sjqitaService.modifySjqita(sjqita);
				return Response.success();
			} else {
				User user = userService.getUser(sjqita.getSjqitaType());
				sjqita.setSjqitaPhone(user.getUserXingming());
				Sjduochu sjduochu = sjduochuService.getSjduochu(sjqita.getSjqitaType1());
				sjqita.setSjqitaDizhi(sjduochu.getSjduochuName());
				Date date = new Date();
				sjqita.setSjqitaDate(date);
				sjqitaService.save(sjqita);
				return Response.success();
			}
		} catch (Exception e) {
			//e.printStackTrace();
			return Response.error(204,"服务器错误");
		}
	}

	//删除数据
	@ResponseBody
	@RequestMapping("/deleteSjqita")
	public Response deleteSjqita(@RequestBody Object req,HttpServletRequest request, HttpServletResponse response)
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
				sjqitaService.deleteSjqita(Integer.parseInt(str[i]));
			}
			return Response.success();
		} catch (Exception e) {
			//e.printStackTrace();
			return Response.error(204,"服务器错误");
		}
	}

	//下拉列表参数，跟搜索基本上一样，但是返回的是所有值，不分页
	@ResponseBody
	@RequestMapping("/sjqitaComboList")
	public Response sjqitaComboList(@RequestBody Object req,HttpServletRequest request, HttpServletResponse response)
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
		String sjqitaType1 = map.get("sjqitaType1");
		String sjqitaType = map.get("sjqitaType");
		Sjqita sjqita = new Sjqita();
		try {
			if (StringUtil.isNotEmpty(sjqitaType)) {
				sjqita.setSjqitaType(Integer.parseInt(sjqitaType));
			}
			if (StringUtil.isNotEmpty(sjqitaType1)) {
				sjqita.setSjqitaType1(Integer.parseInt(sjqitaType1));
			}
			List<Sjqita> sjqitas = new ArrayList<Sjqita>();
			sjqitas = sjqitaService.querySjqitas(sjqita, 0, 0);
			return Response.success(sjqitas);
		} catch (Exception e) {
			//e.printStackTrace();
			return Response.error(204,"服务器错误");
		}
	}
}
