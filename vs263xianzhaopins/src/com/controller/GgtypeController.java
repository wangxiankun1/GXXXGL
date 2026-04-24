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
public class GgtypeController {
	@Autowired
	private GgtypeService ggtypeService;

	//获取列表，搜索条件，返回数据
	@ResponseBody
	@RequestMapping("/getGgtypes")
	public Response getGgtypes(@RequestBody Object req,HttpServletRequest request,
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
		String ggtypeName = map.get("ggtypeName");
		String ggtypeId = map.get("ggtypeId");
		//初始化
		Ggtype ggtype = new Ggtype();
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
			if (StringUtil.isNotEmpty(ggtypeName)) {
				ggtype.setGgtypeName(ggtypeName);
			}
			if (StringUtil.isNotEmpty(ggtypeId)) {
				ggtype.setGgtypeId(Integer.parseInt(ggtypeId));
			}
			/****初始化复制，搜索参数结束****/
			//总数
			int total = ggtypeService.queryGgtypes(ggtype, 0,0).size();
			//获取返回列表
			List<Ggtype> ggtypes = new ArrayList<Ggtype>();
			ggtypes = ggtypeService.queryGgtypes(ggtype, pageBean.getStart(), pageBean.getRows());
			//返回页面（列表、总数、行数）
			return Response.success(ggtypes,total,Integer.parseInt(rows));
		} catch (Exception e) {
			//e.printStackTrace();
			return Response.error(204,"服务器错误");
		}
	}

	//添加修改同样一个方法，如果id为空就是新增，如果id不为空就会加载原有数据，然后更新数据
	@ResponseBody
	@RequestMapping("/addGgtype")
	public Response addGgtype(@RequestBody Object req,HttpServletRequest request, HttpServletResponse response)
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
			String ggtypeName = map.get("ggtypeName");
			String ggtypeMark = map.get("ggtypeMark");
			String ggtypeId = map.get("ggtypeId");
			//model初始化
			Ggtype ggtype = new Ggtype();
			//判断id是否为空，如果不为空则加载原有的model
			if (StringUtil.isNotEmpty(ggtypeId)) {
				ggtype = ggtypeService.getGgtype(Integer.parseInt(ggtypeId));
			}
			/****初始化参数赋值****/
			if (StringUtil.isNotEmpty(ggtypeName)) {
				ggtype.setGgtypeName(ggtypeName);
			}
			if (StringUtil.isNotEmpty(ggtypeMark)) {
				ggtype.setGgtypeMark(ggtypeMark);
			}
			/****初始化参数赋值结束****/
			//id不为空，修改（更改状态等）；Id为空就是增加；然后返回结果
			if (StringUtil.isNotEmpty(ggtypeId)) {
				ggtypeService.modifyGgtype(ggtype);
				return Response.success();
			} else {
				ggtypeService.save(ggtype);
				return Response.success();
			}
		} catch (Exception e) {
			//e.printStackTrace();
			return Response.error(204,"服务器错误");
		}
	}

	//删除数据
	@ResponseBody
	@RequestMapping("/deleteGgtype")
	public Response deleteGgtype(@RequestBody Object req,HttpServletRequest request, HttpServletResponse response)
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
				ggtypeService.deleteGgtype(Integer.parseInt(str[i]));
			}
			return Response.success();
		} catch (Exception e) {
			//e.printStackTrace();
			return Response.error(204,"服务器错误");
		}
	}

	//下拉列表参数，跟搜索基本上一样，但是返回的是所有值，不分页
	@ResponseBody
	@RequestMapping("/ggtypeComboList")
	public Response ggtypeComboList(@RequestBody Object req,HttpServletRequest request, HttpServletResponse response)
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
		String ggtypeType1 = map.get("ggtypeType1");
		String ggtypeType = map.get("ggtypeType");
		Ggtype ggtype = new Ggtype();
		try {
			List<Ggtype> ggtypes = new ArrayList<Ggtype>();
			ggtypes = ggtypeService.queryGgtypes(ggtype, 0, 0);
			return Response.success(ggtypes);
		} catch (Exception e) {
			//e.printStackTrace();
			return Response.error(204,"服务器错误");
		}
	}
}
