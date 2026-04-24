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
public class YxtypeController {
	@Autowired
	private YxtypeService yxtypeService;

	//获取列表，搜索条件，返回数据
	@ResponseBody
	@RequestMapping("/getYxtypes")
	public Response getYxtypes(@RequestBody Object req,HttpServletRequest request,
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
		String yxtypeName = map.get("yxtypeName");
		String yxtypeId = map.get("yxtypeId");
		//初始化
		Yxtype yxtype = new Yxtype();
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
			if (StringUtil.isNotEmpty(yxtypeName)) {
				yxtype.setYxtypeName(yxtypeName);
			}
			if (StringUtil.isNotEmpty(yxtypeId)) {
				yxtype.setYxtypeId(Integer.parseInt(yxtypeId));
			}
			/****初始化复制，搜索参数结束****/
			//总数
			int total = yxtypeService.queryYxtypes(yxtype, 0,0).size();
			//获取返回列表
			List<Yxtype> yxtypes = new ArrayList<Yxtype>();
			yxtypes = yxtypeService.queryYxtypes(yxtype, pageBean.getStart(), pageBean.getRows());
			//返回页面（列表、总数、行数）
			return Response.success(yxtypes,total,Integer.parseInt(rows));
		} catch (Exception e) {
			//e.printStackTrace();
			return Response.error(204,"服务器错误");
		}
	}

	//添加修改同样一个方法，如果id为空就是新增，如果id不为空就会加载原有数据，然后更新数据
	@ResponseBody
	@RequestMapping("/addYxtype")
	public Response addYxtype(@RequestBody Object req,HttpServletRequest request, HttpServletResponse response)
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
			String yxtypeName = map.get("yxtypeName");
			String yxtypeMark = map.get("yxtypeMark");
			String yxtypeId = map.get("yxtypeId");
			//model初始化
			Yxtype yxtype = new Yxtype();
			//判断id是否为空，如果不为空则加载原有的model
			if (StringUtil.isNotEmpty(yxtypeId)) {
				yxtype = yxtypeService.getYxtype(Integer.parseInt(yxtypeId));
			}
			/****初始化参数赋值****/
			if (StringUtil.isNotEmpty(yxtypeName)) {
				yxtype.setYxtypeName(yxtypeName);
			}
			if (StringUtil.isNotEmpty(yxtypeMark)) {
				yxtype.setYxtypeMark(yxtypeMark);
			}
			/****初始化参数赋值结束****/
			//id不为空，修改（更改状态等）；Id为空就是增加；然后返回结果
			if (StringUtil.isNotEmpty(yxtypeId)) {
				yxtypeService.modifyYxtype(yxtype);
				return Response.success();
			} else {
				yxtypeService.save(yxtype);
				return Response.success();
			}
		} catch (Exception e) {
			//e.printStackTrace();
			return Response.error(204,"服务器错误");
		}
	}

	//删除数据
	@ResponseBody
	@RequestMapping("/deleteYxtype")
	public Response deleteYxtype(@RequestBody Object req,HttpServletRequest request, HttpServletResponse response)
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
				yxtypeService.deleteYxtype(Integer.parseInt(str[i]));
			}
			return Response.success();
		} catch (Exception e) {
			//e.printStackTrace();
			return Response.error(204,"服务器错误");
		}
	}

	//下拉列表参数，跟搜索基本上一样，但是返回的是所有值，不分页
	@ResponseBody
	@RequestMapping("/yxtypeComboList")
	public Response yxtypeComboList(@RequestBody Object req,HttpServletRequest request, HttpServletResponse response)
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
		String yxtypeType1 = map.get("yxtypeType1");
		String yxtypeType = map.get("yxtypeType");
		Yxtype yxtype = new Yxtype();
		try {
			List<Yxtype> yxtypes = new ArrayList<Yxtype>();
			yxtypes = yxtypeService.queryYxtypes(yxtype, 0, 0);
			return Response.success(yxtypes);
		} catch (Exception e) {
			//e.printStackTrace();
			return Response.error(204,"服务器错误");
		}
	}
}
