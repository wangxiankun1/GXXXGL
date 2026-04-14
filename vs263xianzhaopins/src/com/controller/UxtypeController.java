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
public class UxtypeController {
	@Autowired
	private UxtypeService uxtypeService;

	//获取列表，搜索条件，返回数据
	@ResponseBody
	@RequestMapping("/getUxtypes")
	public Response getUxtypes(@RequestBody Object req,HttpServletRequest request,
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
		String uxtypeName = map.get("uxtypeName");
		String uxtypeId = map.get("uxtypeId");
		//初始化
		Uxtype uxtype = new Uxtype();
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
			if (StringUtil.isNotEmpty(uxtypeName)) {
				uxtype.setUxtypeName(uxtypeName);
			}
			if (StringUtil.isNotEmpty(uxtypeId)) {
				uxtype.setUxtypeId(Integer.parseInt(uxtypeId));
			}
			/****初始化复制，搜索参数结束****/
			//总数
			int total = uxtypeService.queryUxtypes(uxtype, 0,0).size();
			//获取返回列表
			List<Uxtype> uxtypes = new ArrayList<Uxtype>();
			uxtypes = uxtypeService.queryUxtypes(uxtype, pageBean.getStart(), pageBean.getRows());
			//返回页面（列表、总数、行数）
			return Response.success(uxtypes,total,Integer.parseInt(rows));
		} catch (Exception e) {
			//e.printStackTrace();
			return Response.error(204,"服务器错误");
		}
	}

	//添加修改同样一个方法，如果id为空就是新增，如果id不为空就会加载原有数据，然后更新数据
	@ResponseBody
	@RequestMapping("/addUxtype")
	public Response addUxtype(@RequestBody Object req,HttpServletRequest request, HttpServletResponse response)
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
			String uxtypeName = map.get("uxtypeName");
			String uxtypeMark = map.get("uxtypeMark");
			String uxtypeId = map.get("uxtypeId");
			//model初始化
			Uxtype uxtype = new Uxtype();
			//判断id是否为空，如果不为空则加载原有的model
			if (StringUtil.isNotEmpty(uxtypeId)) {
				uxtype = uxtypeService.getUxtype(Integer.parseInt(uxtypeId));
			}
			/****初始化参数赋值****/
			if (StringUtil.isNotEmpty(uxtypeName)) {
				uxtype.setUxtypeName(uxtypeName);
			}
			if (StringUtil.isNotEmpty(uxtypeMark)) {
				uxtype.setUxtypeMark(uxtypeMark);
			}
			/****初始化参数赋值结束****/
			//id不为空，修改（更改状态等）；Id为空就是增加；然后返回结果
			if (StringUtil.isNotEmpty(uxtypeId)) {
				uxtypeService.modifyUxtype(uxtype);
				return Response.success();
			} else {
				uxtypeService.save(uxtype);
				return Response.success();
			}
		} catch (Exception e) {
			//e.printStackTrace();
			return Response.error(204,"服务器错误");
		}
	}

	//删除数据
	@ResponseBody
	@RequestMapping("/deleteUxtype")
	public Response deleteUxtype(@RequestBody Object req,HttpServletRequest request, HttpServletResponse response)
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
				uxtypeService.deleteUxtype(Integer.parseInt(str[i]));
			}
			return Response.success();
		} catch (Exception e) {
			//e.printStackTrace();
			return Response.error(204,"服务器错误");
		}
	}

	//下拉列表参数，跟搜索基本上一样，但是返回的是所有值，不分页
	@ResponseBody
	@RequestMapping("/uxtypeComboList")
	public Response uxtypeComboList(@RequestBody Object req,HttpServletRequest request, HttpServletResponse response)
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
		String uxtypeType1 = map.get("uxtypeType1");
		String uxtypeType = map.get("uxtypeType");
		Uxtype uxtype = new Uxtype();
		try {
			List<Uxtype> uxtypes = new ArrayList<Uxtype>();
			uxtypes = uxtypeService.queryUxtypes(uxtype, 0, 0);
			return Response.success(uxtypes);
		} catch (Exception e) {
			//e.printStackTrace();
			return Response.error(204,"服务器错误");
		}
	}
}
