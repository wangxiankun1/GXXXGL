package com.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.model.*;
import com.service.*;
import com.util.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

@Controller
public class ShujuController {
	@Autowired
	private ShujuService shujuService;
	@Autowired
	private SjleixingService sjleixingService;
	@Autowired
	private SjxingtaiService sjxingtaiService;
	@Autowired
	private UserService userService;
	@Autowired
	private YonghuService yonghuService;
	@Autowired
	private BuzhiService buzhiService;

	/***上传导入开始***/
	private InputStream excelFile;

	public InputStream getExcelFile() {
		return excelFile;
	}
	/***上传导入结束***/

	//推荐shuju，用户未登陆或者协同过滤推荐没有结果的时候使用随机推荐6个
	/*****
	 * 协同过滤推荐说明
	 * 推荐方法是使用协同过滤算法和随机推荐组合推荐的
	 * 全部shuju排序是根据点赞或收藏量、发布时间倒叙排列
	 * 协同过滤是根据用户收藏或者点赞，然后算法会根据用户的相似度推荐的，如果想了解算法具体是怎么用的可以看scr/util/CFUtils.java，可以从网上找找资料介绍
	 * 为什么我点了收藏或者点赞，推荐不变
	 * 协同过滤是算法推荐，并不是某一个用户点一下立马就变的，这个是需要很多用户和很多数据才能推荐变化的
	 * 比如您注册10个用户，每个用户都有10条收藏或点赞，第11个用户在收藏或点赞，会推荐1、2、3，第12个用户的收藏或点赞后，可能就回推荐3、4、5
	 * 这个推荐是针对某一个用户的，而不是这个用户点一下就会变
	 * 为什么要补充随机推荐，因为协同过滤需要很多条数据，演示的时候不可能加那么多条数据，并且算法我们也没法控制，为了演示的时候推荐不为空，所以用随机推荐补充
	 */
	@ResponseBody
	@RequestMapping("/tuijianShujus")
	public Response tuijianShujus(@RequestBody Object req,HttpServletRequest request,
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
		String userId = map.get("userId");
		//用户登陆后推荐
		if(StringUtil.isNotEmpty(userId)){
			List<String> shujuIds = new ArrayList<String>();
			User user = userService.getUser(Integer.parseInt(userId));
			if(user.getUserType2()!=0){//协同过滤
				List<User> users = new ArrayList<User>();
				user = new User();
				user.setUserType2(1);
				users = userService.queryUsers(user, null, 0, 0, null, null, null, null);
				String[][] userLikeLists = new String[users.size()][];
				for (int i = 0; i < users.size(); i++) {
					userLikeLists[i] = users.get(i).getUserMark4().split(",");
				}
				shujuIds = CFUtils.CF_shujus(users.size(), userLikeLists, userId);
			}
			List<Shuju> shujus = new ArrayList<Shuju>();
			List<Shuju> tuijianShujus = new ArrayList<Shuju>();
			shujus = shujuService.queryShujus(null, 0, 0, null, null, null, null);
			List<Integer> quanbuIds = new ArrayList<Integer>();
			for (int i = 0; i < shujus.size(); i++) {
				quanbuIds.add(shujus.get(i).getShujuId());
			}
			List<Integer> tuijianIds = new ArrayList<Integer>();
			for (int i = 0; i < shujuIds.size(); i++) {
				if(quanbuIds.contains(Integer.parseInt(shujuIds.get(i)))){
					tuijianIds.add(Integer.parseInt(shujuIds.get(i)));
				}
			}
			for (int i = 0; i < tuijianIds.size(); i++) {
				tuijianShujus.add(shujuService.getShuju(tuijianIds.get(i)));
			}
			//协同过滤是否有数据，如果有数据返回数据
			if(tuijianShujus.size()>0){
				return Response.success(tuijianShujus);
			}
			//协同过滤为空，随机推荐
			else{
				tuijianShujus = ListSuiji.createRandomList(shujus, 6);
				return Response.success(tuijianShujus);
			}
		}
		//没有登陆随机推荐
		else{
			List<Shuju> shujus = new ArrayList<Shuju>();
			shujus = shujuService.queryShujus(null, 0, 0, null, null, null, null);
			shujus = ListSuiji.createRandomList(shujus, 6);
			return Response.success(shujus);
		}
	}

	//获取列表，搜索条件，返回数据
	@ResponseBody
	@RequestMapping("/getShujus")
	public Response getShujus(@RequestBody Object req,HttpServletRequest request,
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
		String shujuName = map.get("shujuName");
		String shujuMark = map.get("shujuMark");
		String shujuId = map.get("shujuId");
		String shujuType = map.get("shujuType");
		String shujuType1 = map.get("shujuType1");
		String sjleixingId = map.get("sjleixingId");
		String sjxingtaiId = map.get("sjxingtaiId");
		String shujuZong2 = map.get("shujuZong2");
		String yonghuId = map.get("yonghuId");
		String userId = map.get("userId");
		String bumenId = map.get("bumenId");
		String buyuanId = map.get("buyuanId");
		String buzhiId = map.get("buzhiId");
		String sdate = map.get("sdate");
		String edate = map.get("edate");
		String sdate1 = map.get("sdate1");
		String edate1 = map.get("edate1");
		String shujuMark1 = map.get("shujuMark1");
		String shujuMark2 = map.get("shujuMark2");
		String shujuMark3 = map.get("shujuMark3");
		String shujuDouble3 = map.get("shujuDouble3");
		String shujuDouble4 = map.get("shujuDouble4");
		//初始化
		Shuju shuju = new Shuju();
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
			if (StringUtil.isNotEmpty(shujuName)) {
				shuju.setShujuName(shujuName);
			}
			if (StringUtil.isNotEmpty(shujuMark)) {
				shuju.setShujuMark(shujuMark);
			}
			if (StringUtil.isNotEmpty(shujuMark1)) {
				shuju.setShujuMark1(shujuMark1);
			}
			if (StringUtil.isNotEmpty(shujuMark2)) {
				shuju.setShujuMark2(shujuMark2);
			}
			if (StringUtil.isNotEmpty(shujuMark3)) {
				shuju.setShujuMark3(shujuMark3);
			}
			if (StringUtil.isNotEmpty(shujuDouble3)) {
				shuju.setShujuDouble3(Double.parseDouble(shujuDouble3));
			}
			if (StringUtil.isNotEmpty(shujuDouble4)) {
				shuju.setShujuDouble4(Double.parseDouble(shujuDouble4));
			}
			if (StringUtil.isNotEmpty(shujuId)) {
				shuju.setShujuId(Integer.parseInt(shujuId));
			}
			if (StringUtil.isNotEmpty(shujuType)) {
				shuju.setShujuType(Integer.parseInt(shujuType));
			}
			if (StringUtil.isNotEmpty(shujuType1)) {
				shuju.setShujuType1(Integer.parseInt(shujuType1));
			}
			if (StringUtil.isNotEmpty(sjleixingId)) {
				shuju.setSjleixingId(Integer.parseInt(sjleixingId));
			}
			if (StringUtil.isNotEmpty(sjxingtaiId)) {
				shuju.setSjxingtaiId(Integer.parseInt(sjxingtaiId));
			}
			if (StringUtil.isNotEmpty(shujuZong2)) {
				shuju.setShujuZong2(Integer.parseInt(shujuZong2));
			}
			if (StringUtil.isNotEmpty(userId)) {
				shuju.setUserId(Integer.parseInt(userId));
			}
			if (StringUtil.isNotEmpty(bumenId)) {
				shuju.setBumenId(Integer.parseInt(bumenId));
			}
			if (StringUtil.isNotEmpty(buyuanId)) {
				shuju.setBuyuanId(Integer.parseInt(buyuanId));
			}
			if (StringUtil.isNotEmpty(buzhiId)) {
				shuju.setBuzhiId(Integer.parseInt(buzhiId));
			}
			if (StringUtil.isNotEmpty(yonghuId)) {
				shuju.setYonghuId(Integer.parseInt(yonghuId));
			}
			if (StringUtil.isNotEmpty(sdate1)) {
				Date date = new Date();
				sdate1 = DateUtil.formatDate(date, "yyyy-MM-dd HH:mm:ss");
			}
			if (StringUtil.isNotEmpty(edate1)) {
				Date date = new Date();
				edate1 = DateUtil.formatDate(date, "yyyy-MM-dd HH:mm:ss");
			}
			/****初始化复制，搜索参数结束****/
			//总数
			int total = shujuService.queryShujus(shuju, 0,0, sdate, edate, sdate1, edate1).size();
			//获取返回列表
			List<Shuju> shujus = new ArrayList<Shuju>();
			shujus = shujuService.queryShujus(shuju, pageBean.getStart(), pageBean.getRows(), sdate, edate, sdate1, edate1);
			//返回页面（列表、总数、行数）
			return Response.success(shujus,total,Integer.parseInt(rows));
		} catch (Exception e) {
			//e.printStackTrace();
			return Response.error(204,"服务器错误");
		}
	}

	//添加修改同样一个方法，如果id为空就是新增，如果id不为空就会加载原有数据，然后更新数据
	@ResponseBody
	@RequestMapping("/addShuju")
	public Response addShuju(@RequestBody Object req,HttpServletRequest request, HttpServletResponse response)
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
			String shujuName = map.get("shujuName");
			String shujuMark = map.get("shujuMark");
			String shujuMark1 = map.get("shujuMark1");
			String shujuMark2 = map.get("shujuMark2");
			String shujuMark3 = map.get("shujuMark3");
			String shujuDate = map.get("shujuDate");
			String shujuDate1 = map.get("shujuDate1");
			String shujuZong = map.get("shujuZong");
			String shujuZong1 = map.get("shujuZong1");
			String shujuZong2 = map.get("shujuZong2");
			String shujuType = map.get("shujuType");
			String shujuType1 = map.get("shujuType1");
			String shujuType2 = map.get("shujuType2");
			String sjleixingId = map.get("sjleixingId");
			String sjxingtaiId = map.get("sjxingtaiId");
			String shujuDouble = map.get("shujuDouble");
			String shujuDouble1 = map.get("shujuDouble1");
			String shujuDouble2 = map.get("shujuDouble2");
			String shujuDouble3 = map.get("shujuDouble3");
			String shujuDouble4 = map.get("shujuDouble4");
			String yonghuId = map.get("yonghuId");
			String userId = map.get("userId");
			String buzhiId = map.get("buzhiId");
			String shujuImg = map.get("shujuImg");
			String shujuId = map.get("shujuId");
			//model初始化
			Shuju shuju = new Shuju();
			//判断id是否为空，如果不为空则加载原有的model
			if (StringUtil.isNotEmpty(shujuId)) {
				shuju = shujuService.getShuju(Integer.parseInt(shujuId));
			}
			/****初始化参数赋值****/
			if (StringUtil.isNotEmpty(shujuImg)) {
				String str[] = shujuImg.split("/");
				shujuImg = str[str.length-1];
				shuju.setShujuImg(request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/file/" + shujuImg);
				shuju.setShujuImgName(request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/downFile?filename=" + shujuImg);
			}
			if (StringUtil.isNotEmpty(shujuName)) {
				shuju.setShujuName(shujuName);
			}
			if (StringUtil.isNotEmpty(shujuMark)) {
				shuju.setShujuMark(shujuMark);
			}
			if (StringUtil.isNotEmpty(shujuMark1)) {
				shuju.setShujuMark1(shujuMark1);
			}
			if (StringUtil.isNotEmpty(shujuMark2)) {
				shuju.setShujuMark2(shujuMark2);
			}
			if (StringUtil.isNotEmpty(shujuMark3)) {
				shuju.setShujuMark3(shujuMark3);
			}
			if (StringUtil.isNotEmpty(shujuDate)) {
				shuju.setShujuDate(DateUtil.formatString(shujuDate,
						"yyyy-MM-dd HH:mm:ss"));
			}else{
				shuju.setShujuDate(new Date());
			}
			if (StringUtil.isNotEmpty(shujuDate1)) {
				shuju.setShujuDate1(DateUtil.formatString(shujuDate1,
						"yyyy-MM-dd HH:mm:ss"));
			}
			if (StringUtil.isNotEmpty(shujuZong)) {
				shuju.setShujuZong(Integer.parseInt(shujuZong));
			}
			if (StringUtil.isNotEmpty(shujuZong1)) {
				shuju.setShujuZong1(Integer.parseInt(shujuZong1));
			}
			if (StringUtil.isNotEmpty(shujuZong2)) {
				shuju.setShujuZong2(Integer.parseInt(shujuZong2));
			}
			if (StringUtil.isNotEmpty(shujuType)) {
				shuju.setShujuType(Integer.parseInt(shujuType));
			}
			if (StringUtil.isNotEmpty(shujuType1)) {
				shuju.setShujuType1(Integer.parseInt(shujuType1));
			}
			if (StringUtil.isNotEmpty(shujuType2)) {
				shuju.setShujuType2(Integer.parseInt(shujuType2));
			}
			if (StringUtil.isNotEmpty(shujuDouble)) {
				shuju.setShujuDouble(Double.parseDouble(shujuDouble));
			}
			if (StringUtil.isNotEmpty(shujuDouble1)) {
				shuju.setShujuDouble1(Double.parseDouble(shujuDouble1));
			}
			if (StringUtil.isNotEmpty(shujuDouble2)) {
				shuju.setShujuDouble2(Double.parseDouble(shujuDouble2));
			}
			if (StringUtil.isNotEmpty(shujuDouble3)) {
				shuju.setShujuDouble3(Double.parseDouble(shujuDouble3));
			}
			if (StringUtil.isNotEmpty(shujuDouble4)) {
				shuju.setShujuDouble4(Double.parseDouble(shujuDouble4));
			}
			if (StringUtil.isNotEmpty(sjleixingId)) {
				shuju.setSjleixingId(Integer.parseInt(sjleixingId));
				Sjleixing sjleixing = new Sjleixing();
				sjleixing = sjleixingService.getSjleixing(Integer.parseInt(sjleixingId));
				shuju.setSjleixingName(sjleixing.getSjleixingName());
			}
			if (StringUtil.isNotEmpty(sjxingtaiId)) {
				shuju.setSjxingtaiId(Integer.parseInt(sjxingtaiId));
				Sjxingtai sjxingtai = new Sjxingtai();
				sjxingtai = sjxingtaiService.getSjxingtai(Integer.parseInt(sjxingtaiId));
				shuju.setSjxingtaiName(sjxingtai.getSjxingtaiName());
			}
			if (StringUtil.isNotEmpty(yonghuId)) {
				shuju.setYonghuId(Integer.parseInt(yonghuId));
				Yonghu yonghu = new Yonghu();
				yonghu = yonghuService.getYonghu(Integer.parseInt(yonghuId));
				shuju.setYonghuName(yonghu.getYonghuXingming());
				shuju.setBumenId(yonghu.getBumenId());
				shuju.setBumenName(yonghu.getBumenName());
				shuju.setBuyuanId(yonghu.getBuyuanId());
				shuju.setBuyuanName(yonghu.getBuyuanName());
				shuju.setBuzhiId(yonghu.getBuzhiId());
				shuju.setBuzhiName(yonghu.getBuzhiName());
			}
			if (StringUtil.isNotEmpty(userId)) {
				shuju.setUserId(Integer.parseInt(userId));
				User user = new User();
				user = userService.getUser(Integer.parseInt(userId));
				shuju.setUserName(user.getUserXingming());
				shuju.setBumenId(user.getBumenId());
				shuju.setBumenName(user.getBumenName());
				shuju.setBuyuanId(user.getBuyuanId());
				shuju.setBuyuanName(user.getBuyuanName());
				shuju.setBuzhiId(user.getBuzhiId());
				shuju.setBuzhiName(user.getBuzhiName());
			}
			if (StringUtil.isNotEmpty(buzhiId)) {
				shuju.setBuzhiId(Integer.parseInt(buzhiId));
				Buzhi buzhi = new Buzhi();
				buzhi = buzhiService.getBuzhi(Integer.parseInt(buzhiId));
				shuju.setBuzhiName(buzhi.getBuzhiName());
				shuju.setBumenId(buzhi.getBumenId());
				shuju.setBumenName(buzhi.getBumenName());
				shuju.setBuyuanId(buzhi.getBuyuanId());
				shuju.setBuyuanName(buzhi.getBuyuanName());
			}
			/****初始化参数赋值结束****/
			//id不为空，修改（更改状态等）；Id为空就是增加；然后返回结果
			if (StringUtil.isNotEmpty(shujuId)) {
				shujuService.modifyShuju(shuju);
				return Response.success();
			} else {
				Yonghu yonghu = yonghuService.getYonghu(shuju.getYonghuId());
				if(yonghu.getYonghuType()==1){
					if (StringUtil.isEmpty(shujuDate)) {
						shuju.setShujuDate(new Date());
					}
					shuju.setShujuZong2(0);
					shuju.setShujuType(0);
					shujuService.save(shuju);
					return Response.success();
				}else{
					return Response.error(204,"审核未通过，请联系管理员");
				}
			}
		} catch (Exception e) {
			//e.printStackTrace();
			return Response.error(204,"服务器错误");
		}
	}

	//删除数据
	@ResponseBody
	@RequestMapping("/deleteShuju")
	public Response deleteShuju(@RequestBody Object req,HttpServletRequest request, HttpServletResponse response)
			throws Exception {
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
		String delIds = map.get("delIds");
		try {
			String str[] = delIds.split(",");
			for (int i = 0; i < str.length; i++) {
				shujuService.deleteShuju(Integer.parseInt(str[i]));
			}
			return Response.success();
		} catch (Exception e) {
			//e.printStackTrace();
			return Response.error(204,"服务器错误");
		}
	}

	//下拉列表参数，跟搜索基本上一样，但是返回的是所有值，不分页
	@ResponseBody
	@RequestMapping("/shujuComboList")
	public Response shujuComboList(@RequestBody Object req,HttpServletRequest request, HttpServletResponse response)
			throws Exception {
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
		String shujuName = map.get("shujuName");
		String shujuId = map.get("shujuId");
		String shujuType = map.get("shujuType");
		String shujuType1 = map.get("shujuType1");
		String sjleixingId = map.get("sjleixingId");
		String sjxingtaiId = map.get("sjxingtaiId");
		String shujuZong2 = map.get("shujuZong2");
		String yonghuId = map.get("yonghuId");
		String userId = map.get("userId");
		String bumenId = map.get("bumenId");
		String buyuanId = map.get("buyuanId");
		String buzhiId = map.get("buzhiId");
		Shuju shuju = new Shuju();
		try {
			if (StringUtil.isNotEmpty(shujuName)) {
				shuju.setShujuName(shujuName);
			}
			if (StringUtil.isNotEmpty(shujuId)) {
				shuju.setShujuId(Integer.parseInt(shujuId));
			}
			if (StringUtil.isNotEmpty(shujuType)) {
				shuju.setShujuType(Integer.parseInt(shujuType));
			}
			if (StringUtil.isNotEmpty(shujuType1)) {
				shuju.setShujuType1(Integer.parseInt(shujuType1));
			}
			if (StringUtil.isNotEmpty(sjleixingId)) {
				shuju.setSjleixingId(Integer.parseInt(sjleixingId));
			}
			if (StringUtil.isNotEmpty(sjxingtaiId)) {
				shuju.setSjxingtaiId(Integer.parseInt(sjxingtaiId));
			}
			if (StringUtil.isNotEmpty(shujuZong2)) {
				shuju.setShujuZong2(Integer.parseInt(shujuZong2));
			}
			if (StringUtil.isNotEmpty(userId)) {
				shuju.setUserId(Integer.parseInt(userId));
			}
			if (StringUtil.isNotEmpty(bumenId)) {
				shuju.setBumenId(Integer.parseInt(bumenId));
			}
			if (StringUtil.isNotEmpty(buyuanId)) {
				shuju.setBuyuanId(Integer.parseInt(buyuanId));
			}
			if (StringUtil.isNotEmpty(buzhiId)) {
				shuju.setBuzhiId(Integer.parseInt(buzhiId));
			}
			if (StringUtil.isNotEmpty(yonghuId)) {
				shuju.setYonghuId(Integer.parseInt(yonghuId));
			}
			List<Shuju> shujus = new ArrayList<Shuju>();
			shujus = shujuService.queryShujus(shuju,0,0, null, null, null, null);
			return Response.success(shujus);
		} catch (Exception e) {
			//e.printStackTrace();
			return Response.error(204,"服务器错误");
		}
	}

	//统计方法
	@ResponseBody
	@RequestMapping("/shujuTongji")
	public Response shujuTongji(@RequestBody Object req,HttpServletRequest request, HttpServletResponse response)
			throws Exception {
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
		//统计条件参数
		String sdate=map.get("sdate");
		String edate=map.get("edate");
		String shujuName = map.get("shujuName");
		String shujuMark1 = map.get("shujuMark1");
		String shujuMark2 = map.get("shujuMark2");
		String shujuMark3 = map.get("shujuMark3");
		String userId=map.get("userId");
		List<Integer> sjleixingIds = new ArrayList<Integer>();
		List<Sjleixing> sjleixings = new ArrayList<Sjleixing>();
		List<Shuju> shujus = new ArrayList<Shuju>();
		Shuju shuju = new Shuju();
		if (StringUtil.isNotEmpty(userId)) {
			shuju.setUserId(Integer.parseInt(userId));
		}
		try {
			//统计图横（X）轴名称
			List<String> nameList = new ArrayList<String>();
			//统计图竖（Y）轴数值
	        List<Double> zongshuList = new ArrayList<Double>();
	        String tongjiShuju = "[";
			sjleixings = sjleixingService.querySjleixings(null, 0,0);
			for(int i=0;i<sjleixings.size();i++){
				sjleixingIds.add(sjleixings.get(i).getSjleixingId());
				nameList.add(sjleixings.get(i).getSjleixingName());
				Double shujuZongshu = 0.0;
				if (StringUtil.isNotEmpty(shujuName)) {
					shuju.setShujuName(shujuName);
				}
				if (StringUtil.isNotEmpty(shujuMark1)) {
					shuju.setShujuMark1(shujuMark1);
				}
				if (StringUtil.isNotEmpty(shujuMark2)) {
					shuju.setShujuMark2(shujuMark2);
				}
				if (StringUtil.isNotEmpty(shujuMark3)) {
					shuju.setShujuMark3(shujuMark3);
				}
				shuju.setSjleixingId(sjleixings.get(i).getSjleixingId());
				shujus = shujuService.queryShujus(shuju, 0,0,sdate,edate, null, null);
				shujuZongshu = shujuZongshu + shujus.size();
				//统计数值
				zongshuList.add(shujuZongshu);
				
				tongjiShuju = tongjiShuju + "{\"value\":" + shujuZongshu + ",\"name\": '" + sjleixings.get(i).getSjleixingName() + "' }";
				if(i<(sjleixings.size()-1)){
					tongjiShuju = tongjiShuju + ",";
				}
			}
			tongjiShuju = tongjiShuju + "]";
			//转为map型
			Map<String,Object> tongjiMap=new HashMap<String,Object>();
			tongjiMap.put("value",zongshuList);
			tongjiMap.put("name",nameList);
			//转为json型
			JSONArray jsonArray = JSONArray.fromObject(tongjiShuju);
			return Response.tongjiSuccess(tongjiMap,jsonArray);
		} catch (Exception e) {
			//e.printStackTrace();
			return Response.error(204,"服务器错误");
		}
	}

	//统计方法
	@ResponseBody
	@RequestMapping("/dianzanTongji")
	public Response dianzanTongji(@RequestBody Object req,HttpServletRequest request, HttpServletResponse response)
			throws Exception {
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
		//统计条件参数
		String sdate=map.get("sdate");
		String edate=map.get("edate");
		String shujuName = map.get("shujuName");
		String shujuMark1 = map.get("shujuMark1");
		String shujuMark2 = map.get("shujuMark2");
		String shujuMark3 = map.get("shujuMark3");
		String userId=map.get("userId");
		String sjleixingId=map.get("sjleixingId");
		List<Shuju> shujus = new ArrayList<Shuju>();
		Shuju shuju = new Shuju();
		if (StringUtil.isNotEmpty(userId)) {
			shuju.setUserId(Integer.parseInt(userId));
		}
		if (StringUtil.isNotEmpty(sjleixingId)) {
			shuju.setSjleixingId(Integer.parseInt(sjleixingId));
		}
		if (StringUtil.isNotEmpty(shujuName)) {
			shuju.setShujuName(shujuName);
		}
		if (StringUtil.isNotEmpty(shujuMark1)) {
			shuju.setShujuMark1(shujuMark1);
		}
		if (StringUtil.isNotEmpty(shujuMark2)) {
			shuju.setShujuMark2(shujuMark2);
		}
		if (StringUtil.isNotEmpty(shujuMark3)) {
			shuju.setShujuMark3(shujuMark3);
		}
		try {
			//统计图横（X）轴名称
			List<String> nameList = new ArrayList<String>();
			//统计图竖（Y）轴数值
	        List<Double> zongshuList = new ArrayList<Double>();
	        String tongjiShuju = "[";
			shujus = shujuService.queryShujus(shuju, 0, 0,sdate,edate, null, null);
			for(int i=0;i<shujus.size();i++){
				nameList.add(shujus.get(i).getShujuName());
				zongshuList.add(shujus.get(i).getShujuZong2()*1.0);
				tongjiShuju = tongjiShuju + "{\"value\":" + shujus.get(i).getShujuZong2()*1.0 + ",\"name\": '" + shujus.get(i).getShujuName() + "' }";
				if(i<(shujus.size()-1)){
					tongjiShuju = tongjiShuju + ",";
				}
			}
			tongjiShuju = tongjiShuju + "]";
			//转为map型
			Map<String,Object> tongjiMap=new HashMap<String,Object>();
			tongjiMap.put("value",zongshuList);
			tongjiMap.put("name",nameList);
			//转为json型
			JSONArray jsonArray = JSONArray.fromObject(tongjiShuju);
			return Response.tongjiSuccess(tongjiMap,jsonArray);
		} catch (Exception e) {
			//e.printStackTrace();
			return Response.error(204,"服务器错误");
		}
	}

	//导入方法
	@ResponseBody
	@RequestMapping("/daoruShuju")
	public Response daoruShuju(@RequestBody Object req,HttpServletRequest request, HttpServletResponse response)
			throws Exception {
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
			String directory = "/file";
			String targetDirectory = request.getSession().getServletContext().getRealPath(directory);
	        String fileName = map.get("shujuImg");
			File dir = new File(targetDirectory,fileName);
			excelFile = new FileInputStream(dir);
			List<List<String>> list = new ArrayList<List<String>>();
			list = ExcelUtil.jiexiExcel(excelFile);
			for (int i = 1; i < list.size(); i++) {
				List<String> row = list.get(i);
				String shujuName = row.get(1);
				String shujuDouble = row.get(2);
				String shujuDouble1 = row.get(3);
				String shujuZong = row.get(4);
				String shujuMark = row.get(5);
				String sjleixingId = row.get(6);
				Shuju shuju = new Shuju();
				if (StringUtil.isNotEmpty(shujuName)) {
					shuju.setShujuName(shujuName);
				}
				if (StringUtil.isNotEmpty(shujuMark)) {
					shuju.setShujuMark(shujuMark);
				}
				if (StringUtil.isNotEmpty(shujuZong)) {
					shuju.setShujuZong(Integer.parseInt(shujuZong));
				}
				if (StringUtil.isNotEmpty(shujuDouble)) {
					shuju.setShujuDouble(Double.parseDouble(shujuDouble));
				}
				if (StringUtil.isNotEmpty(shujuDouble1)) {
					shuju.setShujuDouble1(Double.parseDouble(shujuDouble1));
				}
				if (StringUtil.isNotEmpty(sjleixingId)) {
					shuju.setSjleixingId(Integer.parseInt(sjleixingId));
					Sjleixing sjleixing = new Sjleixing();
					sjleixing = sjleixingService.getSjleixing(Integer.parseInt(sjleixingId));
					shuju.setSjleixingName(sjleixing.getSjleixingName());
				}
				Date date = new Date();
				shuju.setShujuDate(date);
				shuju.setShujuType(0);
				shujuService.save(shuju);
			}
			return Response.success();
		} catch (Exception e) {
			//e.printStackTrace();
			return Response.error(204,"服务器错误");
		}
	}
}
