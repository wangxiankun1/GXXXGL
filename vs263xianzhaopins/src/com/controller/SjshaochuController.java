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
public class SjshaochuController {
	@Autowired
	private SjshaochuService sjshaochuService;
	@Autowired
	private SjqitaService sjqitaService;
	@Autowired
	private SjlaiyuanService sjlaiyuanService;
	@Autowired
	private SjleixingService sjleixingService;
	@Autowired
	private ShujuService shujuService;
	@Autowired
	private YonghuService yonghuService;
	@Autowired
	private UserService userService;
	@Autowired
	private SjxingtaiService sjxingtaiService;
	@Autowired
	private BuzhiService buzhiService;

	/***上传导入开始***/
	private InputStream excelFile;

	public InputStream getExcelFile() {
		return excelFile;
	}
	/***上传导入结束***/

	//获取列表，搜索条件，返回数据
	@ResponseBody
	@RequestMapping("/getSjshaochus")
	public Response getSjshaochus(@RequestBody Object req,HttpServletRequest request,
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
		String sjshaochuName = map.get("sjshaochuName");
		String sjshaochuMark = map.get("sjshaochuMark");
		String sjshaochuMark1 = map.get("sjshaochuMark1");
		String sjshaochuMark2 = map.get("sjshaochuMark2");
		String sjshaochuId = map.get("sjshaochuId");
		String sjshaochuType = map.get("sjshaochuType");
		String sjshaochuType1 = map.get("sjshaochuType1");
		String sjshaochuType2 = map.get("sjshaochuType2");
		String sjlaiyuanId = map.get("sjlaiyuanId");
		String sjqitaId = map.get("sjqitaId");
		String shujuId = map.get("shujuId");
		String sjleixingId = map.get("sjleixingId");
		String sjxingtaiId = map.get("sjxingtaiId");
		String yonghuId = map.get("yonghuId");
		String userId = map.get("userId");
		String bumenId = map.get("bumenId");
		String buyuanId = map.get("buyuanId");
		String buzhiId = map.get("buzhiId");
		String sdate = map.get("sdate");
		String edate = map.get("edate");
		String sdate1 = map.get("sdate1");
		String edate1 = map.get("edate1");
		//初始化
		Sjshaochu sjshaochu = new Sjshaochu();
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
				sjshaochu.setShujuName(shujuName);
			}
			if (StringUtil.isNotEmpty(sjshaochuName)) {
				sjshaochu.setSjshaochuName(sjshaochuName);
			}
			if (StringUtil.isNotEmpty(sjshaochuMark)) {
				sjshaochu.setSjshaochuMark(sjshaochuMark);
			}
			if (StringUtil.isNotEmpty(sjshaochuMark1)) {
				sjshaochu.setSjshaochuMark1(sjshaochuMark1);
			}
			if (StringUtil.isNotEmpty(sjshaochuMark2)) {
				sjshaochu.setSjshaochuMark2(sjshaochuMark2);
			}
			if (StringUtil.isNotEmpty(sjshaochuId)) {
				sjshaochu.setSjshaochuId(Integer.parseInt(sjshaochuId));
			}
			if (StringUtil.isNotEmpty(sjshaochuType)) {
				sjshaochu.setSjshaochuType(Integer.parseInt(sjshaochuType));
			}
			if (StringUtil.isNotEmpty(sjshaochuType1)) {
				sjshaochu.setSjshaochuType1(Integer.parseInt(sjshaochuType1));
			}
			if (StringUtil.isNotEmpty(sjshaochuType2)) {
				sjshaochu.setSjshaochuType2(Integer.parseInt(sjshaochuType2));
			}
			if (StringUtil.isNotEmpty(sjlaiyuanId)) {
				sjshaochu.setSjlaiyuanId(Integer.parseInt(sjlaiyuanId));
			}
			if (StringUtil.isNotEmpty(sjqitaId)) {
				sjshaochu.setSjqitaId(Integer.parseInt(sjqitaId));
			}
			if (StringUtil.isNotEmpty(shujuId)) {
				sjshaochu.setShujuId(Integer.parseInt(shujuId));
			}
			if (StringUtil.isNotEmpty(sjleixingId)) {
				sjshaochu.setSjleixingId(Integer.parseInt(sjleixingId));
			}
			if (StringUtil.isNotEmpty(sjxingtaiId)) {
				sjshaochu.setSjxingtaiId(Integer.parseInt(sjxingtaiId));
			}
			if (StringUtil.isNotEmpty(userId)) {
				sjshaochu.setUserId(Integer.parseInt(userId));
			}
			if (StringUtil.isNotEmpty(bumenId)) {
				sjshaochu.setBumenId(Integer.parseInt(bumenId));
			}
			if (StringUtil.isNotEmpty(buyuanId)) {
				sjshaochu.setBuyuanId(Integer.parseInt(buyuanId));
			}
			if (StringUtil.isNotEmpty(buzhiId)) {
				sjshaochu.setBuzhiId(Integer.parseInt(buzhiId));
			}
			if (StringUtil.isNotEmpty(yonghuId)) {
				sjshaochu.setYonghuId(Integer.parseInt(yonghuId));
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
			int total = sjshaochuService.querySjshaochus(sjshaochu, 0,0, sdate, edate, sdate1, edate1).size();
			return Response.success(sjshaochuService.querySjshaochus(sjshaochu, pageBean.getStart(), pageBean.getRows(), sdate, edate, sdate1, edate1),total,Integer.parseInt(rows));
		} catch (Exception e) {
			//e.printStackTrace();
			return Response.error(204,"服务器错误");
		}
	}

	//添加修改同样一个方法，如果id为空就是新增，如果id不为空就会加载原有数据，然后更新数据
	@ResponseBody
	@RequestMapping("/addSjshaochu")
	public Response addSjshaochu(@RequestBody Object req,HttpServletRequest request, HttpServletResponse response)
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
			String sjshaochuName = map.get("sjshaochuName");
			String sjshaochuMark = map.get("sjshaochuMark");
			String sjshaochuMark1 = map.get("sjshaochuMark1");
			String sjshaochuMark2 = map.get("sjshaochuMark2");
			String sjshaochuMark3 = map.get("sjshaochuMark3");
			String sjshaochuDate = map.get("sjshaochuDate");
			String sjshaochuDate1 = map.get("sjshaochuDate1");
			String sjshaochuZong = map.get("sjshaochuZong");
			String sjshaochuZong1 = map.get("sjshaochuZong1");
			String sjshaochuZong2 = map.get("sjshaochuZong2");
			String sjshaochuDouble = map.get("sjshaochuDouble");
			String sjshaochuDouble1 = map.get("sjshaochuDouble1");
			String sjshaochuDouble2 = map.get("sjshaochuDouble2");
			String sjshaochuType = map.get("sjshaochuType");
			String sjshaochuType1 = map.get("sjshaochuType1");
			String sjshaochuType2 = map.get("sjshaochuType2");
			String shujuId = map.get("shujuId");
			String sjqitaId = map.get("sjqitaId");
			String sjlaiyuanId = map.get("sjlaiyuanId");
			String yonghuId = map.get("yonghuId");
			String userId = map.get("userId");
			String sjshaochuImg = map.get("sjshaochuImg");
			String sjleixingId = map.get("sjleixingId");
			String sjxingtaiId = map.get("sjxingtaiId");
			String buzhiId = map.get("buzhiId");
			String sjshaochuId = map.get("sjshaochuId");
			//model初始化
			Sjshaochu sjshaochu = new Sjshaochu();
			//判断id是否为空，如果不为空则加载原有的model
			if (StringUtil.isNotEmpty(sjshaochuId)) {
				sjshaochu = sjshaochuService.getSjshaochu(Integer.parseInt(sjshaochuId));
			}
			/****初始化参数赋值****/
			if (StringUtil.isNotEmpty(sjshaochuImg)) {
				String str[] = sjshaochuImg.split("/");
				sjshaochuImg = str[str.length-1];
				sjshaochu.setSjshaochuImg(request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/file/" + sjshaochuImg);
				sjshaochu.setSjshaochuImgName(request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/downFile?filename=" + sjshaochuImg);
			}
			if (StringUtil.isNotEmpty(sjshaochuName)) {
				sjshaochu.setSjshaochuName(sjshaochuName);
			}
			if (StringUtil.isNotEmpty(sjshaochuMark)) {
				sjshaochu.setSjshaochuMark(sjshaochuMark);
			}
			if (StringUtil.isNotEmpty(sjshaochuMark1)) {
				sjshaochu.setSjshaochuMark1(sjshaochuMark1);
			}
			if (StringUtil.isNotEmpty(sjshaochuMark2)) {
				sjshaochu.setSjshaochuMark2(sjshaochuMark2);
			}
			if (StringUtil.isNotEmpty(sjshaochuMark3)) {
				sjshaochu.setSjshaochuMark3(sjshaochuMark3);
			}
			if (StringUtil.isNotEmpty(sjshaochuDate)) {
				sjshaochu.setSjshaochuDate(DateUtil.formatString(sjshaochuDate,
						"yyyy-MM-dd HH:mm:ss"));
			}else{
				sjshaochu.setSjshaochuDate(new Date());
			}
			if (StringUtil.isNotEmpty(sjshaochuDate1)) {
				sjshaochu.setSjshaochuDate1(DateUtil.formatString(sjshaochuDate1,
						"yyyy-MM-dd HH:mm:ss"));
			}
			if (StringUtil.isNotEmpty(sjshaochuZong)) {
				sjshaochu.setSjshaochuZong(Integer.parseInt(sjshaochuZong));
			}
			if (StringUtil.isNotEmpty(sjshaochuZong1)) {
				sjshaochu.setSjshaochuZong1(Integer.parseInt(sjshaochuZong1));
			}
			if (StringUtil.isNotEmpty(sjshaochuZong2)) {
				sjshaochu.setSjshaochuZong2(Integer.parseInt(sjshaochuZong2));
			}
			if (StringUtil.isNotEmpty(sjshaochuDouble)) {
				sjshaochu.setSjshaochuDouble(Double.parseDouble(sjshaochuDouble));
			}
			if (StringUtil.isNotEmpty(sjshaochuDouble1)) {
				sjshaochu.setSjshaochuDouble1(Double.parseDouble(sjshaochuDouble1));
			}
			if (StringUtil.isNotEmpty(sjshaochuDouble2)) {
				sjshaochu.setSjshaochuDouble2(Double.parseDouble(sjshaochuDouble2));
			}
			if (StringUtil.isNotEmpty(sjshaochuType)) {
				sjshaochu.setSjshaochuType(Integer.parseInt(sjshaochuType));
			}
			if (StringUtil.isNotEmpty(sjshaochuType1)) {
				sjshaochu.setSjshaochuType1(Integer.parseInt(sjshaochuType1));
			}
			if (StringUtil.isNotEmpty(sjshaochuType2)) {
				sjshaochu.setSjshaochuType2(Integer.parseInt(sjshaochuType2));
			}
			if (StringUtil.isNotEmpty(shujuId)) {
				sjshaochu.setShujuId(Integer.parseInt(shujuId));
				Shuju shuju = new Shuju();
				shuju = shujuService.getShuju(Integer.parseInt(shujuId));
				sjshaochu.setShujuName(shuju.getShujuName());
				sjshaochu.setSjleixingId(shuju.getSjleixingId());
				sjshaochu.setSjleixingName(shuju.getSjleixingName());
				sjshaochu.setSjxingtaiId(shuju.getSjxingtaiId());
				sjshaochu.setSjxingtaiName(shuju.getSjxingtaiName());
				sjshaochu.setYonghuId(shuju.getYonghuId());
				sjshaochu.setYonghuName(shuju.getYonghuName());
				sjshaochu.setBumenId(shuju.getBumenId());
				sjshaochu.setBumenName(shuju.getBumenName());
				sjshaochu.setBuyuanId(shuju.getBuyuanId());
				sjshaochu.setBuyuanName(shuju.getBuyuanName());
				sjshaochu.setBuzhiId(shuju.getBuzhiId());
				sjshaochu.setBuzhiName(shuju.getBuzhiName());
			}
			if (StringUtil.isNotEmpty(sjqitaId)) {
				sjshaochu.setSjqitaId(Integer.parseInt(sjqitaId));
				Sjqita sjqita = new Sjqita();
				sjqita = sjqitaService.getSjqita(Integer.parseInt(sjqitaId));
				sjshaochu.setSjqitaName(sjqita.getSjqitaName());
			}
			if (StringUtil.isNotEmpty(sjlaiyuanId)) {
				sjshaochu.setSjlaiyuanId(Integer.parseInt(sjlaiyuanId));
				Sjlaiyuan sjlaiyuan = new Sjlaiyuan();
				sjlaiyuan = sjlaiyuanService.getSjlaiyuan(Integer.parseInt(sjlaiyuanId));
				sjshaochu.setSjlaiyuanName(sjlaiyuan.getSjlaiyuanName());
			}
			if (StringUtil.isNotEmpty(yonghuId)) {
				sjshaochu.setYonghuId(Integer.parseInt(yonghuId));
				Yonghu yonghu = new Yonghu();
				yonghu = yonghuService.getYonghu(Integer.parseInt(yonghuId));
				sjshaochu.setYonghuName(yonghu.getYonghuXingming());
				sjshaochu.setBumenId(yonghu.getBumenId());
				sjshaochu.setBumenName(yonghu.getBumenName());
				sjshaochu.setBuyuanId(yonghu.getBuyuanId());
				sjshaochu.setBuyuanName(yonghu.getBuyuanName());
				sjshaochu.setBuzhiId(yonghu.getBuzhiId());
				sjshaochu.setBuzhiName(yonghu.getBuzhiName());
			}
			if (StringUtil.isNotEmpty(userId)) {
				sjshaochu.setUserId(Integer.parseInt(userId));
				User user = new User();
				user = userService.getUser(Integer.parseInt(userId));
				sjshaochu.setUserName(user.getUserXingming());
				sjshaochu.setBumenId(user.getBumenId());
				sjshaochu.setBumenName(user.getBumenName());
				sjshaochu.setBuyuanId(user.getBuyuanId());
				sjshaochu.setBuyuanName(user.getBuyuanName());
				sjshaochu.setBuzhiId(user.getBuzhiId());
				sjshaochu.setBuzhiName(user.getBuzhiName());
				sjshaochu.setSjshaochuImg(user.getUserImg());
				sjshaochu.setSjshaochuImgName(user.getUserImgName());
			}
			if (StringUtil.isNotEmpty(sjleixingId)) {
				sjshaochu.setSjleixingId(Integer.parseInt(sjleixingId));
				Sjleixing sjleixing = new Sjleixing();
				sjleixing = sjleixingService.getSjleixing(Integer.parseInt(sjleixingId));
				sjshaochu.setSjleixingName(sjleixing.getSjleixingName());
			}
			if (StringUtil.isNotEmpty(sjxingtaiId)) {
				sjshaochu.setSjxingtaiId(Integer.parseInt(sjxingtaiId));
				Sjxingtai sjxingtai = new Sjxingtai();
				sjxingtai = sjxingtaiService.getSjxingtai(Integer.parseInt(sjxingtaiId));
				sjshaochu.setSjxingtaiName(sjxingtai.getSjxingtaiName());
			}
			if (StringUtil.isNotEmpty(buzhiId)) {
				sjshaochu.setBuzhiId(Integer.parseInt(buzhiId));
				Buzhi buzhi = new Buzhi();
				buzhi = buzhiService.getBuzhi(Integer.parseInt(buzhiId));
				sjshaochu.setBuzhiName(buzhi.getBuzhiName());
				sjshaochu.setBumenId(buzhi.getBumenId());
				sjshaochu.setBumenName(buzhi.getBumenName());
				sjshaochu.setBuyuanId(buzhi.getBuyuanId());
				sjshaochu.setBuyuanName(buzhi.getBuyuanName());
			}
			/****初始化参数赋值结束****/
			//id不为空，修改（更改状态等）；Id为空就是增加；然后返回结果
			if (StringUtil.isNotEmpty(sjshaochuId)) {
				Shuju shuju = shujuService.getShuju(sjshaochu.getShujuId());
				if(sjshaochu.getSjshaochuType()==1){
					if(shuju.getShujuZong()>0){
						shuju.setShujuZong(shuju.getShujuZong()-1);
						shujuService.modifyShuju(shuju);
						sjshaochuService.modifySjshaochu(sjshaochu);
						return Response.success();
					}else{
						return Response.error(204,"人数已满");
					}
				}else{
					sjshaochuService.modifySjshaochu(sjshaochu);
					return Response.success();
				}
			} else {
				User user = userService.getUser(sjshaochu.getUserId());
				if(StringUtil.isNotEmpty(user.getUserImgName())){
					Shuju shuju = shujuService.getShuju(sjshaochu.getShujuId());
					if(shuju.getShujuZong()>0){
						sjshaochu.setSjshaochuDouble(shuju.getShujuDouble());
						sjshaochu.setSjshaochuName(user.getUserPhone());
						sjshaochu.setSjshaochuMark(user.getUserMark());
						sjshaochu.setSjshaochuMark1(user.getUserMark1());
						sjshaochu.setSjshaochuMark2(user.getUserMark2());
						sjshaochu.setSjshaochuType(0);
						sjshaochuService.save(sjshaochu);
						return Response.success();
					}else{
						return Response.error(204,"人数已满");
					}
				}else{
					return Response.error(204,"请先上传简历");
				}
			}
		} catch (Exception e) {
			//e.printStackTrace();
			return Response.error(204,"服务器错误");
		}
	}

	//删除数据
	@ResponseBody
	@RequestMapping("/deleteSjshaochu")
	public Response deleteSjshaochu(@RequestBody Object req,HttpServletRequest request, HttpServletResponse response)
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
		String delIds = map.get("delIds");
		try {
			String str[] = delIds.split(",");
			for (int i = 0; i < str.length; i++) {
				sjshaochuService.deleteSjshaochu(Integer.parseInt(str[i]));
			}
			return Response.success();
		} catch (Exception e) {
			//e.printStackTrace();
			return Response.error(204,"服务器错误");
		}
	}

	//下拉列表参数，跟搜索基本上一样，但是返回的是所有值，不分页
	@ResponseBody
	@RequestMapping("/sjshaochuComboList")
	public Response sjshaochuComboList(@RequestBody Object req,HttpServletRequest request, HttpServletResponse response)
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
		String sjshaochuName = map.get("sjshaochuName");
		String sjshaochuId = map.get("sjshaochuId");
		String sjshaochuType = map.get("sjshaochuType");
		String sjshaochuType1 = map.get("sjshaochuType1");
		String sjshaochuType2 = map.get("sjshaochuType2");
		String sjlaiyuanId = map.get("sjlaiyuanId");
		String sjqitaId = map.get("sjqitaId");
		String shujuId = map.get("shujuId");
		String sjleixingId = map.get("sjleixingId");
		String sjxingtaiId = map.get("sjxingtaiId");
		String yonghuId = map.get("yonghuId");
		String userId = map.get("userId");
		String bumenId = map.get("bumenId");
		String buyuanId = map.get("buyuanId");
		String buzhiId = map.get("buzhiId");
		Sjshaochu sjshaochu = new Sjshaochu();
		try {
			if (StringUtil.isNotEmpty(sjshaochuName)) {
				sjshaochu.setSjshaochuName(sjshaochuName);
			}
			if (StringUtil.isNotEmpty(sjshaochuId)) {
				sjshaochu.setSjshaochuId(Integer.parseInt(sjshaochuId));
			}
			if (StringUtil.isNotEmpty(sjshaochuType)) {
				sjshaochu.setSjshaochuType(Integer.parseInt(sjshaochuType));
			}
			if (StringUtil.isNotEmpty(sjshaochuType1)) {
				sjshaochu.setSjshaochuType1(Integer.parseInt(sjshaochuType1));
			}
			if (StringUtil.isNotEmpty(sjshaochuType2)) {
				sjshaochu.setSjshaochuType2(Integer.parseInt(sjshaochuType2));
			}
			if (StringUtil.isNotEmpty(sjlaiyuanId)) {
				sjshaochu.setSjlaiyuanId(Integer.parseInt(sjlaiyuanId));
			}
			if (StringUtil.isNotEmpty(sjqitaId)) {
				sjshaochu.setSjqitaId(Integer.parseInt(sjqitaId));
			}
			if (StringUtil.isNotEmpty(shujuId)) {
				sjshaochu.setShujuId(Integer.parseInt(shujuId));
			}
			if (StringUtil.isNotEmpty(sjleixingId)) {
				sjshaochu.setSjleixingId(Integer.parseInt(sjleixingId));
			}
			if (StringUtil.isNotEmpty(sjxingtaiId)) {
				sjshaochu.setSjxingtaiId(Integer.parseInt(sjxingtaiId));
			}
			if (StringUtil.isNotEmpty(userId)) {
				sjshaochu.setUserId(Integer.parseInt(userId));
			}
			if (StringUtil.isNotEmpty(bumenId)) {
				sjshaochu.setBumenId(Integer.parseInt(bumenId));
			}
			if (StringUtil.isNotEmpty(buyuanId)) {
				sjshaochu.setBuyuanId(Integer.parseInt(buyuanId));
			}
			if (StringUtil.isNotEmpty(buzhiId)) {
				sjshaochu.setBuzhiId(Integer.parseInt(buzhiId));
			}
			if (StringUtil.isNotEmpty(yonghuId)) {
				sjshaochu.setYonghuId(Integer.parseInt(yonghuId));
			}
			return Response.success(sjshaochuService.querySjshaochus(sjshaochu,0,0, null, null, null, null));
		} catch (Exception e) {
			//e.printStackTrace();
			return Response.error(204,"服务器错误");
		}
	}

	//统计方法
	@ResponseBody
	@RequestMapping("/sjshaochuTongji")
	public Response sjshaochuTongji(@RequestBody Object req,HttpServletRequest request, HttpServletResponse response)
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
		String sjshaochuName = map.get("sjshaochuName");
		String sjshaochuMark = map.get("sjshaochuMark");
		String sjshaochuMark1 = map.get("sjshaochuMark1");
		String sjshaochuMark2 = map.get("sjshaochuMark2");
		String userId=map.get("userId");
		List<Integer> sjleixingIds = new ArrayList<Integer>();
		List<Sjleixing> sjleixings = new ArrayList<Sjleixing>();
		List<Sjshaochu> sjshaochus = new ArrayList<Sjshaochu>();
		Sjshaochu sjshaochu = new Sjshaochu();
		if (StringUtil.isNotEmpty(userId)) {
			sjshaochu.setUserId(Integer.parseInt(userId));
		}
		try {
			//统计图横（X）轴名称
			List<String> nameList = new ArrayList<String>();
			//统计图竖（Y）轴数值
	        List<Double> zongshuList = new ArrayList<Double>();
	        String tongjiSjshaochu = "[";
			sjleixings = sjleixingService.querySjleixings(null, 0,0);
			for(int i=0;i<sjleixings.size();i++){
				sjleixingIds.add(sjleixings.get(i).getSjleixingId());
				nameList.add(sjleixings.get(i).getSjleixingName());
				Double sjshaochuZongshu = 0.0;
				if (StringUtil.isNotEmpty(sjshaochuName)) {
					sjshaochu.setSjshaochuName(sjshaochuName);
				}
				if (StringUtil.isNotEmpty(sjshaochuMark)) {
					sjshaochu.setSjshaochuMark(sjshaochuMark);
				}
				if (StringUtil.isNotEmpty(sjshaochuMark1)) {
					sjshaochu.setSjshaochuMark1(sjshaochuMark1);
				}
				if (StringUtil.isNotEmpty(sjshaochuMark2)) {
					sjshaochu.setSjshaochuMark2(sjshaochuMark2);
				}
				sjshaochu.setSjleixingId(sjleixings.get(i).getSjleixingId());
				sjshaochu.setSjshaochuType(1);
				sjshaochus = sjshaochuService.querySjshaochus(sjshaochu, 0,0,sdate,edate, null, null);
				sjshaochuZongshu = sjshaochuZongshu + sjshaochus.size();
				//统计数值
				zongshuList.add(sjshaochuZongshu);
				
				tongjiSjshaochu = tongjiSjshaochu + "{\"value\":" + sjshaochuZongshu + ",\"name\": '" + sjleixings.get(i).getSjleixingName() + "' }";
				if(i<(sjleixings.size()-1)){
					tongjiSjshaochu = tongjiSjshaochu + ",";
				}
			}
			tongjiSjshaochu = tongjiSjshaochu + "]";
			//转为map型
			Map<String,Object> tongjiMap=new HashMap<String,Object>();
			tongjiMap.put("value",zongshuList);
			tongjiMap.put("name",nameList);
			//转为json型
			JSONArray jsonArray = JSONArray.fromObject(tongjiSjshaochu);
			return Response.tongjiSuccess(tongjiMap,jsonArray);
		} catch (Exception e) {
			//e.printStackTrace();
			return Response.error(204,"服务器错误");
		}
	}

	//导入方法
	@ResponseBody
	@RequestMapping("/daoruSjshaochu")
	public Response daoruSjshaochu(@RequestBody Object req,HttpServletRequest request, HttpServletResponse response)
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
	        String fileName = map.get("sjshaochuImg");
			File dir = new File(targetDirectory,fileName);
			excelFile = new FileInputStream(dir);
			List<List<String>> list = new ArrayList<List<String>>();
			list = ExcelUtil.jiexiExcel(excelFile);
			for (int i = 1; i < list.size(); i++) {
				List<String> row = list.get(i);
				String sjshaochuName = row.get(1);
				String sjshaochuDouble = row.get(2);
				String sjshaochuDouble1 = row.get(3);
				String sjshaochuZong = row.get(4);
				String sjshaochuMark = row.get(5);
				String shujuId = row.get(6);
				Sjshaochu sjshaochu = new Sjshaochu();
				if (StringUtil.isNotEmpty(sjshaochuName)) {
					sjshaochu.setSjshaochuName(sjshaochuName);
				}
				if (StringUtil.isNotEmpty(sjshaochuMark)) {
					sjshaochu.setSjshaochuMark(sjshaochuMark);
				}
				if (StringUtil.isNotEmpty(sjshaochuZong)) {
					sjshaochu.setSjshaochuZong(Integer.parseInt(sjshaochuZong));
				}
				if (StringUtil.isNotEmpty(sjshaochuDouble)) {
					sjshaochu.setSjshaochuDouble(Double.parseDouble(sjshaochuDouble));
				}
				if (StringUtil.isNotEmpty(sjshaochuDouble1)) {
					sjshaochu.setSjshaochuDouble1(Double.parseDouble(sjshaochuDouble1));
				}
				if (StringUtil.isNotEmpty(shujuId)) {
					sjshaochu.setShujuId(Integer.parseInt(shujuId));
					Shuju shuju = new Shuju();
					shuju = shujuService.getShuju(Integer.parseInt(shujuId));
					sjshaochu.setShujuName(shuju.getShujuName());
				}
				Date date = new Date();
				sjshaochu.setSjshaochuDate(date);
				sjshaochu.setSjshaochuType(0);
				sjshaochuService.save(sjshaochu);
			}
			return Response.success();
		} catch (Exception e) {
			//e.printStackTrace();
			return Response.error(204,"服务器错误");
		}
	}
}
