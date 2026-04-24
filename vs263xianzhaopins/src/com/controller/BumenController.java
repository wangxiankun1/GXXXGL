package com.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.model.*;
import com.service.*;
import com.util.*;

@Controller
public class BumenController {
	@Autowired
	private BumenService bumenService;
	@Autowired
	private BuyuanService buyuanService;
	@Autowired
	private ShujuService shujuService;
	@Autowired
	private UserService userService;

	//添加修改同样一个方法，如果id为空就是新增，如果id不为空就会加载原有数据，然后更新数据
	@ResponseBody
	@RequestMapping("/addBumen")
	public Response addBumen(@RequestBody Object req,HttpServletRequest request, HttpServletResponse response)
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
			String bumenName = map.get("bumenName");
			String bumenMark = map.get("bumenMark");
			String bumenMark1 = map.get("bumenMark1");
			String bumenMark2 = map.get("bumenMark2");
			String bumenType = map.get("bumenType");
			String bumenType1 = map.get("bumenType1");
			String bumenType2 = map.get("bumenType2");
			String bumenDouble = map.get("bumenDouble");
			String bumenDouble1 = map.get("bumenDouble1");
			String bumenDouble2 = map.get("bumenDouble2");
			String buyuanId = map.get("buyuanId");
			String bumenId = map.get("bumenId");
			//model初始化
			Bumen bumen = new Bumen();
			//判断id是否为空，如果不为空则加载原有的model
			if (StringUtil.isNotEmpty(bumenId)) {
				bumen = bumenService.getBumen(Integer.parseInt(bumenId));
			}
			if (StringUtil.isNotEmpty(bumenName)) {
				bumen.setBumenName(bumenName);
			}
			if (StringUtil.isNotEmpty(bumenMark)) {
				bumen.setBumenMark(bumenMark);
			}
			if (StringUtil.isNotEmpty(bumenMark1)) {
				bumen.setBumenMark1(bumenMark1);
			}
			if (StringUtil.isNotEmpty(bumenMark2)) {
				bumen.setBumenMark2(bumenMark2);
			}
			if (StringUtil.isNotEmpty(bumenType)) {
				bumen.setBumenType(Integer.parseInt(bumenType));
			}
			if (StringUtil.isNotEmpty(bumenType1)) {
				bumen.setBumenType1(Integer.parseInt(bumenType1));
			}
			if (StringUtil.isNotEmpty(bumenType2)) {
				bumen.setBumenType2(Integer.parseInt(bumenType2));
			}
			if (StringUtil.isNotEmpty(bumenDouble)) {
				bumen.setBumenDouble(Double.parseDouble(bumenDouble));
			}
			if (StringUtil.isNotEmpty(bumenDouble1)) {
				bumen.setBumenDouble1(Double.parseDouble(bumenDouble1));
			}
			if (StringUtil.isNotEmpty(bumenDouble2)) {
				bumen.setBumenDouble2(Double.parseDouble(bumenDouble2));
			}
			if (StringUtil.isNotEmpty(buyuanId)) {
				bumen.setBuyuanId(Integer.parseInt(buyuanId));
				Buyuan buyuan = new Buyuan();
				buyuan = buyuanService.getBuyuan(Integer.parseInt(buyuanId));
				bumen.setBuyuanName(buyuan.getBuyuanName());
			}
			/****初始化参数赋值结束****/
			//id不为空，修改（更改状态等）；Id为空就是增加；然后返回结果
			if (StringUtil.isNotEmpty(bumenId)) {
				bumenService.modifyBumen(bumen);
				return Response.success();
			} else {
				User user = userService.getUser(bumen.getBumenType1());
				List<Bumen> bumens = new ArrayList<Bumen>();
				Bumen newBumen = new Bumen();
				newBumen.setBumenType1(bumen.getBumenType1());
				bumens = bumenService.queryBumens(newBumen, 0, 0);
				if(bumens.size()==0){
					user.setUserMark4(user.getUserId().toString() + ",");
					user.setUserType2(1);
				}else{
					user.setUserMark4(user.getUserMark4()+",");
				}
				newBumen.setBumenType2(bumen.getBumenType2());
				bumens = bumenService.queryBumens(newBumen, 0, 0);
				if(bumens.size()==0){
					Shuju shuju = shujuService.getShuju(bumen.getBumenType2());
					bumen.setBumenDouble(shuju.getShujuDouble());
					bumen.setBumenName(shuju.getShujuName());
					bumen.setBumenMark(shuju.getShujuMark1());
					bumen.setBumenMark1(shuju.getShujuMark2());
					bumen.setBumenMark2(shuju.getShujuMark3());
					bumenService.save(bumen);
					user.setUserMark4(user.getUserMark4()+bumen.getBumenType2());
					userService.modifyUser(user);
					shuju.setShujuZong2(shuju.getShujuZong2()+1);
					shujuService.modifyShuju(shuju);
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

	//获取列表，搜索条件，返回数据
	@ResponseBody
	@RequestMapping("/getBumens")
	public Response getBumens(@RequestBody Object req,HttpServletRequest request,
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
		String bumenName = map.get("bumenName");
		String bumenType = map.get("bumenType");
		String bumenType1 = map.get("bumenType1");
		String bumenType2 = map.get("bumenType2");
		String buyuanId = map.get("buyuanId");
		//初始化
		Bumen bumen = new Bumen();
		if (StringUtil.isNotEmpty(bumenName)) {
			bumen.setBumenName(bumenName);
		}
		/****初始化赋值，搜索参数****/
		if (StringUtil.isNotEmpty(bumenType)) {
			bumen.setBumenType(Integer.parseInt(bumenType));
		}
		if (StringUtil.isNotEmpty(bumenType1)) {
			bumen.setBumenType1(Integer.parseInt(bumenType1));
		}
		if (StringUtil.isNotEmpty(bumenType2)) {
			bumen.setBumenType2(Integer.parseInt(bumenType2));
		}
		if (StringUtil.isNotEmpty(buyuanId)) {
			bumen.setBuyuanId(Integer.parseInt(buyuanId));
		}
		/****初始化复制，搜索参数结束****/
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
			//总数
			int total = bumenService.queryBumens(bumen, 0,0).size();
			return Response.success(bumenService.queryBumens(bumen, pageBean.getStart(), pageBean.getRows()),total,Integer.parseInt(rows));
		} catch (Exception e) {
			//e.printStackTrace();
		}
		return null;
	}

	//删除数据
	@ResponseBody
	@RequestMapping("/deleteBumen")
	public Response deleteBumen(@RequestBody Object req,HttpServletRequest request, HttpServletResponse response)
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
				bumenService.deleteBumen(Integer.parseInt(str[i]));
			}
			return Response.success();
		} catch (Exception e) {
			//e.printStackTrace();
			return Response.error(204,"服务器错误");
		}
	}

	//下拉列表参数，跟搜索基本上一样，但是返回的是所有值，不分页
	@ResponseBody
	@RequestMapping("/bumenComboList")
	public Response bumenComboList(@RequestBody Object req,HttpServletRequest request, HttpServletResponse response)
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
		String bumenType = map.get("bumenType");
		String buyuanId = map.get("buyuanId");
		Bumen bumen = new Bumen();
		if (StringUtil.isNotEmpty(bumenType)) {
			bumen.setBumenType(Integer.parseInt(bumenType));
		}
		if (StringUtil.isNotEmpty(buyuanId)) {
			bumen.setBuyuanId(Integer.parseInt(buyuanId));
		}
		try {
			return Response.success(bumenService.queryBumens(bumen,0,0));
		} catch (Exception e) {
			//e.printStackTrace();
			return Response.error(204,"服务器错误");
		}
	}
}
