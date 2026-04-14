package com.controller;

import java.util.ArrayList;
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
public class BuyuanController {
	@Autowired
	private BuyuanService buyuanService;
	@Autowired
	private SjduochuService sjduochuService;
	@Autowired
	private UserService userService;

	//获取列表，搜索条件，返回数据
	@ResponseBody
	@RequestMapping("/getBuyuans")
	public Response getBuyuans(@RequestBody Object req,HttpServletRequest request,
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
		String buyuanName = map.get("buyuanName");
		String buyuanType2 = map.get("buyuanType2");
		String buyuanId = map.get("buyuanId");
		String buyuanType1 = map.get("buyuanType1");
		String buyuanType = map.get("buyuanType");
		//初始化
		Buyuan buyuan = new Buyuan();
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
			if (StringUtil.isNotEmpty(buyuanName)) {
				buyuan.setBuyuanName(buyuanName);
			}
			if (StringUtil.isNotEmpty(buyuanType2)) {
				buyuan.setBuyuanType2(Integer.parseInt(buyuanType2));
			}
			if (StringUtil.isNotEmpty(buyuanId)) {
				buyuan.setBuyuanId(Integer.parseInt(buyuanId));
			}
			if (StringUtil.isNotEmpty(buyuanType)) {
				buyuan.setBuyuanType(Integer.parseInt(buyuanType));
			}
			if (StringUtil.isNotEmpty(buyuanType1)) {
				buyuan.setBuyuanType1(Integer.parseInt(buyuanType1));
			}
			/****初始化复制，搜索参数结束****/
			//总数
			int total = buyuanService.queryBuyuans(buyuan, 0,0).size();
			//获取返回列表
			List<Buyuan> buyuans = new ArrayList<Buyuan>();
			buyuans = buyuanService.queryBuyuans(buyuan, pageBean.getStart(), pageBean.getRows());
			//返回页面（列表、总数、行数）
			return Response.success(buyuans,total,Integer.parseInt(rows));
		} catch (Exception e) {
			//e.printStackTrace();
			return Response.error(204,"服务器错误");
		}
	}

	//添加修改同样一个方法，如果id为空就是新增，如果id不为空就会加载原有数据，然后更新数据
	@ResponseBody
	@RequestMapping("/addBuyuan")
	public Response addBuyuan(@RequestBody Object req,HttpServletRequest request, HttpServletResponse response)
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
			String buyuanName = map.get("buyuanName");
			String buyuanType2 = map.get("buyuanType2");
			String buyuanMark = map.get("buyuanMark");
			String buyuanMark1 = map.get("buyuanMark1");
			String buyuanMark2 = map.get("buyuanMark2");
			String buyuanDouble2 = map.get("buyuanDouble2");
			String buyuanType = map.get("buyuanType");
			String buyuanType1 = map.get("buyuanType1");
			String buyuanDouble = map.get("buyuanDouble");
			String buyuanDouble1 = map.get("buyuanDouble1");
			String buyuanId = map.get("buyuanId");
			//model初始化
			Buyuan buyuan = new Buyuan();
			//判断id是否为空，如果不为空则加载原有的model
			if (StringUtil.isNotEmpty(buyuanId)) {
				buyuan = buyuanService.getBuyuan(Integer.parseInt(buyuanId));
			}
			/****初始化参数赋值****/
			if (StringUtil.isNotEmpty(buyuanName)) {
				buyuan.setBuyuanName(buyuanName);
			}
			if (StringUtil.isNotEmpty(buyuanType2)) {
				buyuan.setBuyuanType2(Integer.parseInt(buyuanType2));
			}
			if (StringUtil.isNotEmpty(buyuanMark)) {
				buyuan.setBuyuanMark(buyuanMark);
			}
			if (StringUtil.isNotEmpty(buyuanMark1)) {
				buyuan.setBuyuanMark1(buyuanMark1);
			}
			if (StringUtil.isNotEmpty(buyuanMark2)) {
				buyuan.setBuyuanMark2(buyuanMark2);
			}
			if (StringUtil.isNotEmpty(buyuanDouble2)) {
				buyuan.setBuyuanDouble2(Double.parseDouble(buyuanDouble2));
			}
			if (StringUtil.isNotEmpty(buyuanType)) {
				buyuan.setBuyuanType(Integer.parseInt(buyuanType));
			}
			if (StringUtil.isNotEmpty(buyuanType1)) {
				buyuan.setBuyuanType1(Integer.parseInt(buyuanType1));
			}
			if (StringUtil.isNotEmpty(buyuanDouble)) {
				buyuan.setBuyuanDouble(Double.parseDouble(buyuanDouble));
			}
			if (StringUtil.isNotEmpty(buyuanDouble1)) {
				buyuan.setBuyuanDouble1(Double.parseDouble(buyuanDouble1));
			}
			/****初始化参数赋值结束****/
			//id不为空，修改（更改状态等）；Id为空就是增加；然后返回结果
			if (StringUtil.isNotEmpty(buyuanId)) {
				buyuanService.modifyBuyuan(buyuan);
				return Response.success();
			} else {
				List<Buyuan> buyuans = new ArrayList<Buyuan>();
				Buyuan newBuyuan = new Buyuan();
				newBuyuan.setBuyuanType(buyuan.getBuyuanType());
				newBuyuan.setBuyuanType1(buyuan.getBuyuanType1());
				buyuans = buyuanService.queryBuyuans(newBuyuan, 0, 0);
				if(buyuans.size()==0){
					User user = userService.getUser(buyuan.getBuyuanType());
					buyuan.setBuyuanMark(user.getUserXingming());
					Sjduochu sjduochu = sjduochuService.getSjduochu(buyuan.getBuyuanType1());
					buyuan.setBuyuanMark1(sjduochu.getSjduochuName());
					sjduochu.setSjduochuZong(sjduochu.getSjduochuZong()+1);
					sjduochuService.modifySjduochu(sjduochu);
					buyuanService.save(buyuan);
					return Response.success();
				}else{
					return Response.error(204,"重复操作");
				}
			}
		} catch (Exception e) {
			//e.printStackTrace();
			return Response.error(204,"服务器错误");
		}
	}

	//删除数据
	@ResponseBody
	@RequestMapping("/deleteBuyuan")
	public Response deleteBuyuan(@RequestBody Object req,HttpServletRequest request, HttpServletResponse response)
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
				buyuanService.deleteBuyuan(Integer.parseInt(str[i]));
			}
			return Response.success();
		} catch (Exception e) {
			//e.printStackTrace();
			return Response.error(204,"服务器错误");
		}
	}

	//下拉列表参数，跟搜索基本上一样，但是返回的是所有值，不分页
	@ResponseBody
	@RequestMapping("/buyuanComboList")
	public Response buyuanComboList(@RequestBody Object req,HttpServletRequest request, HttpServletResponse response)
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
		String buyuanType1 = map.get("buyuanType1");
		String buyuanType = map.get("buyuanType");
		Buyuan buyuan = new Buyuan();
		try {
			if (StringUtil.isNotEmpty(buyuanType)) {
				buyuan.setBuyuanType(Integer.parseInt(buyuanType));
			}
			if (StringUtil.isNotEmpty(buyuanType1)) {
				buyuan.setBuyuanType1(Integer.parseInt(buyuanType1));
			}
			List<Buyuan> buyuans = new ArrayList<Buyuan>();
			buyuans = buyuanService.queryBuyuans(buyuan, 0, 0);
			return Response.success(buyuans);
		} catch (Exception e) {
			//e.printStackTrace();
			return Response.error(204,"服务器错误");
		}
	}
}
