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
public class SjduochuController {
	@Autowired
	private SjduochuService sjduochuService;
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
	@RequestMapping("/getSjduochus")
	public Response getSjduochus(@RequestBody Object req,HttpServletRequest request,
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
		String sjduochuName = map.get("sjduochuName");
		String sjduochuMark = map.get("sjduochuMark");
		String sjduochuId = map.get("sjduochuId");
		String sjduochuType = map.get("sjduochuType");
		String sjduochuType1 = map.get("sjduochuType1");
		String sjduochuType2 = map.get("sjduochuType2");
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
		Sjduochu sjduochu = new Sjduochu();
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
			if (StringUtil.isNotEmpty(sjduochuName)) {
				sjduochu.setSjduochuName(sjduochuName);
			}
			if (StringUtil.isNotEmpty(sjduochuMark)) {
				sjduochu.setSjduochuMark(sjduochuMark);
			}
			if (StringUtil.isNotEmpty(sjduochuId)) {
				sjduochu.setSjduochuId(Integer.parseInt(sjduochuId));
			}
			if (StringUtil.isNotEmpty(sjduochuType)) {
				sjduochu.setSjduochuType(Integer.parseInt(sjduochuType));
			}
			if (StringUtil.isNotEmpty(sjduochuType1)) {
				sjduochu.setSjduochuType1(Integer.parseInt(sjduochuType1));
			}
			if (StringUtil.isNotEmpty(sjduochuType2)) {
				sjduochu.setSjduochuType2(Integer.parseInt(sjduochuType2));
			}
			if (StringUtil.isNotEmpty(sjlaiyuanId)) {
				sjduochu.setSjlaiyuanId(Integer.parseInt(sjlaiyuanId));
			}
			if (StringUtil.isNotEmpty(sjqitaId)) {
				sjduochu.setSjqitaId(Integer.parseInt(sjqitaId));
			}
			if (StringUtil.isNotEmpty(shujuId)) {
				sjduochu.setShujuId(Integer.parseInt(shujuId));
			}
			if (StringUtil.isNotEmpty(sjleixingId)) {
				sjduochu.setSjleixingId(Integer.parseInt(sjleixingId));
			}
			if (StringUtil.isNotEmpty(sjxingtaiId)) {
				sjduochu.setSjxingtaiId(Integer.parseInt(sjxingtaiId));
			}
			if (StringUtil.isNotEmpty(userId)) {
				sjduochu.setUserId(Integer.parseInt(userId));
			}
			if (StringUtil.isNotEmpty(bumenId)) {
				sjduochu.setBumenId(Integer.parseInt(bumenId));
			}
			if (StringUtil.isNotEmpty(buyuanId)) {
				sjduochu.setBuyuanId(Integer.parseInt(buyuanId));
			}
			if (StringUtil.isNotEmpty(buzhiId)) {
				sjduochu.setBuzhiId(Integer.parseInt(buzhiId));
			}
			if (StringUtil.isNotEmpty(yonghuId)) {
				sjduochu.setYonghuId(Integer.parseInt(yonghuId));
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
			int total = sjduochuService.querySjduochus(sjduochu, 0,0, sdate, edate, sdate1, edate1).size();
			return Response.success(sjduochuService.querySjduochus(sjduochu, pageBean.getStart(), pageBean.getRows(), sdate, edate, sdate1, edate1),total,Integer.parseInt(rows));
		} catch (Exception e) {
			//e.printStackTrace();
			return Response.error(204,"服务器错误");
		}
	}

	//添加修改同样一个方法，如果id为空就是新增，如果id不为空就会加载原有数据，然后更新数据
	@ResponseBody
	@RequestMapping("/addSjduochu")
	public Response addSjduochu(@RequestBody Object req,HttpServletRequest request, HttpServletResponse response)
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
			String sjduochuName = map.get("sjduochuName");
			String sjduochuMark = map.get("sjduochuMark");
			String sjduochuMark1 = map.get("sjduochuMark1");
			String sjduochuMark2 = map.get("sjduochuMark2");
			String sjduochuMark3 = map.get("sjduochuMark3");
			String sjduochuDate = map.get("sjduochuDate");
			String sjduochuDate1 = map.get("sjduochuDate1");
			String sjduochuZong = map.get("sjduochuZong");
			String sjduochuZong1 = map.get("sjduochuZong1");
			String sjduochuZong2 = map.get("sjduochuZong2");
			String sjduochuDouble = map.get("sjduochuDouble");
			String sjduochuDouble1 = map.get("sjduochuDouble1");
			String sjduochuDouble2 = map.get("sjduochuDouble2");
			String sjduochuType = map.get("sjduochuType");
			String sjduochuType1 = map.get("sjduochuType1");
			String sjduochuType2 = map.get("sjduochuType2");
			String shujuId = map.get("shujuId");
			String sjqitaId = map.get("sjqitaId");
			String sjlaiyuanId = map.get("sjlaiyuanId");
			String yonghuId = map.get("yonghuId");
			String userId = map.get("userId");
			String sjduochuImg = map.get("sjduochuImg");
			String sjleixingId = map.get("sjleixingId");
			String sjxingtaiId = map.get("sjxingtaiId");
			String buzhiId = map.get("buzhiId");
			String sjduochuId = map.get("sjduochuId");
			//model初始化
			Sjduochu sjduochu = new Sjduochu();
			//判断id是否为空，如果不为空则加载原有的model
			if (StringUtil.isNotEmpty(sjduochuId)) {
				sjduochu = sjduochuService.getSjduochu(Integer.parseInt(sjduochuId));
			}
			/****初始化参数赋值****/
			if (StringUtil.isNotEmpty(sjduochuImg)) {
				String str[] = sjduochuImg.split("/");
				sjduochuImg = str[str.length-1];
				sjduochu.setSjduochuImg(request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/file/" + sjduochuImg);
				sjduochu.setSjduochuImgName(request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/downFile?filename=" + sjduochuImg);
			}
			if (StringUtil.isNotEmpty(sjduochuName)) {
				sjduochu.setSjduochuName(sjduochuName);
			}
			if (StringUtil.isNotEmpty(sjduochuMark)) {
				sjduochu.setSjduochuMark(sjduochuMark);
			}
			if (StringUtil.isNotEmpty(sjduochuMark1)) {
				sjduochu.setSjduochuMark1(sjduochuMark1);
			}
			if (StringUtil.isNotEmpty(sjduochuMark2)) {
				sjduochu.setSjduochuMark2(sjduochuMark2);
			}
			if (StringUtil.isNotEmpty(sjduochuMark3)) {
				sjduochu.setSjduochuMark3(sjduochuMark3);
			}
			if (StringUtil.isNotEmpty(sjduochuDate)) {
				sjduochu.setSjduochuDate(DateUtil.formatString(sjduochuDate,
						"yyyy-MM-dd HH:mm:ss"));
			}else{
				sjduochu.setSjduochuDate(new Date());
			}
			if (StringUtil.isNotEmpty(sjduochuDate1)) {
				sjduochu.setSjduochuDate1(DateUtil.formatString(sjduochuDate1,
						"yyyy-MM-dd HH:mm:ss"));
			}
			if (StringUtil.isNotEmpty(sjduochuZong)) {
				sjduochu.setSjduochuZong(Integer.parseInt(sjduochuZong));
			}
			if (StringUtil.isNotEmpty(sjduochuZong1)) {
				sjduochu.setSjduochuZong1(Integer.parseInt(sjduochuZong1));
			}
			if (StringUtil.isNotEmpty(sjduochuZong2)) {
				sjduochu.setSjduochuZong2(Integer.parseInt(sjduochuZong2));
			}
			if (StringUtil.isNotEmpty(sjduochuDouble)) {
				sjduochu.setSjduochuDouble(Double.parseDouble(sjduochuDouble));
			}
			if (StringUtil.isNotEmpty(sjduochuDouble1)) {
				sjduochu.setSjduochuDouble1(Double.parseDouble(sjduochuDouble1));
			}
			if (StringUtil.isNotEmpty(sjduochuDouble2)) {
				sjduochu.setSjduochuDouble2(Double.parseDouble(sjduochuDouble2));
			}
			if (StringUtil.isNotEmpty(sjduochuType)) {
				sjduochu.setSjduochuType(Integer.parseInt(sjduochuType));
			}
			if (StringUtil.isNotEmpty(sjduochuType1)) {
				sjduochu.setSjduochuType1(Integer.parseInt(sjduochuType1));
			}
			if (StringUtil.isNotEmpty(sjduochuType2)) {
				sjduochu.setSjduochuType2(Integer.parseInt(sjduochuType2));
			}
			if (StringUtil.isNotEmpty(shujuId)) {
				sjduochu.setShujuId(Integer.parseInt(shujuId));
				Shuju shuju = new Shuju();
				shuju = shujuService.getShuju(Integer.parseInt(shujuId));
				sjduochu.setShujuName(shuju.getShujuName());
				sjduochu.setSjleixingId(shuju.getSjleixingId());
				sjduochu.setSjleixingName(shuju.getSjleixingName());
				sjduochu.setSjxingtaiId(shuju.getSjxingtaiId());
				sjduochu.setSjxingtaiName(shuju.getSjxingtaiName());
				sjduochu.setYonghuId(shuju.getYonghuId());
				sjduochu.setYonghuName(shuju.getYonghuName());
				sjduochu.setUserId(shuju.getUserId());
				sjduochu.setUserName(shuju.getUserName());
				sjduochu.setBumenId(shuju.getBumenId());
				sjduochu.setBumenName(shuju.getBumenName());
				sjduochu.setBuyuanId(shuju.getBuyuanId());
				sjduochu.setBuyuanName(shuju.getBuyuanName());
				sjduochu.setBuzhiId(shuju.getBuzhiId());
				sjduochu.setBuzhiName(shuju.getBuzhiName());
			}
			if (StringUtil.isNotEmpty(sjqitaId)) {
				sjduochu.setSjqitaId(Integer.parseInt(sjqitaId));
				Sjqita sjqita = new Sjqita();
				sjqita = sjqitaService.getSjqita(Integer.parseInt(sjqitaId));
				sjduochu.setSjqitaName(sjqita.getSjqitaName());
			}
			if (StringUtil.isNotEmpty(sjlaiyuanId)) {
				sjduochu.setSjlaiyuanId(Integer.parseInt(sjlaiyuanId));
				Sjlaiyuan sjlaiyuan = new Sjlaiyuan();
				sjlaiyuan = sjlaiyuanService.getSjlaiyuan(Integer.parseInt(sjlaiyuanId));
				sjduochu.setSjlaiyuanName(sjlaiyuan.getSjlaiyuanName());
			}
			if (StringUtil.isNotEmpty(yonghuId)) {
				sjduochu.setYonghuId(Integer.parseInt(yonghuId));
				Yonghu yonghu = new Yonghu();
				yonghu = yonghuService.getYonghu(Integer.parseInt(yonghuId));
				sjduochu.setYonghuName(yonghu.getYonghuXingming());
				sjduochu.setBumenId(yonghu.getBumenId());
				sjduochu.setBumenName(yonghu.getBumenName());
				sjduochu.setBuyuanId(yonghu.getBuyuanId());
				sjduochu.setBuyuanName(yonghu.getBuyuanName());
				sjduochu.setBuzhiId(yonghu.getBuzhiId());
				sjduochu.setBuzhiName(yonghu.getBuzhiName());
			}
			if (StringUtil.isNotEmpty(userId)) {
				sjduochu.setUserId(Integer.parseInt(userId));
				User user = new User();
				user = userService.getUser(Integer.parseInt(userId));
				sjduochu.setUserName(user.getUserXingming());
				sjduochu.setBumenId(user.getBumenId());
				sjduochu.setBumenName(user.getBumenName());
				sjduochu.setBuyuanId(user.getBuyuanId());
				sjduochu.setBuyuanName(user.getBuyuanName());
				sjduochu.setBuzhiId(user.getBuzhiId());
				sjduochu.setBuzhiName(user.getBuzhiName());
			}
			if (StringUtil.isNotEmpty(sjleixingId)) {
				sjduochu.setSjleixingId(Integer.parseInt(sjleixingId));
				Sjleixing sjleixing = new Sjleixing();
				sjleixing = sjleixingService.getSjleixing(Integer.parseInt(sjleixingId));
				sjduochu.setSjleixingName(sjleixing.getSjleixingName());
			}
			if (StringUtil.isNotEmpty(sjxingtaiId)) {
				sjduochu.setSjxingtaiId(Integer.parseInt(sjxingtaiId));
				Sjxingtai sjxingtai = new Sjxingtai();
				sjxingtai = sjxingtaiService.getSjxingtai(Integer.parseInt(sjxingtaiId));
				sjduochu.setSjxingtaiName(sjxingtai.getSjxingtaiName());
			}
			if (StringUtil.isNotEmpty(buzhiId)) {
				sjduochu.setBuzhiId(Integer.parseInt(buzhiId));
				Buzhi buzhi = new Buzhi();
				buzhi = buzhiService.getBuzhi(Integer.parseInt(buzhiId));
				sjduochu.setBuzhiName(buzhi.getBuzhiName());
				sjduochu.setBumenId(buzhi.getBumenId());
				sjduochu.setBumenName(buzhi.getBumenName());
				sjduochu.setBuyuanId(buzhi.getBuyuanId());
				sjduochu.setBuyuanName(buzhi.getBuyuanName());
			}
			/****初始化参数赋值结束****/
			//id不为空，修改（更改状态等）；Id为空就是增加；然后返回结果
			if (StringUtil.isNotEmpty(sjduochuId)) {
				sjduochuService.modifySjduochu(sjduochu);
				return Response.success();
			} else {
				sjduochu.setSjduochuType(0);
				sjduochu.setSjduochuZong(0);
				sjduochuService.save(sjduochu);
				return Response.success();
			}
		} catch (Exception e) {
			//e.printStackTrace();
			return Response.error(204,"服务器错误");
		}
	}

	//删除数据
	@ResponseBody
	@RequestMapping("/deleteSjduochu")
	public Response deleteSjduochu(@RequestBody Object req,HttpServletRequest request, HttpServletResponse response)
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
				sjduochuService.deleteSjduochu(Integer.parseInt(str[i]));
			}
			return Response.success();
		} catch (Exception e) {
			//e.printStackTrace();
			return Response.error(204,"服务器错误");
		}
	}

	//下拉列表参数，跟搜索基本上一样，但是返回的是所有值，不分页
	@ResponseBody
	@RequestMapping("/sjduochuComboList")
	public Response sjduochuComboList(@RequestBody Object req,HttpServletRequest request, HttpServletResponse response)
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
		String sjduochuName = map.get("sjduochuName");
		String sjduochuId = map.get("sjduochuId");
		String sjduochuType = map.get("sjduochuType");
		String sjduochuType1 = map.get("sjduochuType1");
		String sjduochuType2 = map.get("sjduochuType2");
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
		Sjduochu sjduochu = new Sjduochu();
		try {
			if (StringUtil.isNotEmpty(sjduochuName)) {
				sjduochu.setSjduochuName(sjduochuName);
			}
			if (StringUtil.isNotEmpty(sjduochuId)) {
				sjduochu.setSjduochuId(Integer.parseInt(sjduochuId));
			}
			if (StringUtil.isNotEmpty(sjduochuType)) {
				sjduochu.setSjduochuType(Integer.parseInt(sjduochuType));
			}
			if (StringUtil.isNotEmpty(sjduochuType1)) {
				sjduochu.setSjduochuType1(Integer.parseInt(sjduochuType1));
			}
			if (StringUtil.isNotEmpty(sjduochuType2)) {
				sjduochu.setSjduochuType2(Integer.parseInt(sjduochuType2));
			}
			if (StringUtil.isNotEmpty(sjlaiyuanId)) {
				sjduochu.setSjlaiyuanId(Integer.parseInt(sjlaiyuanId));
			}
			if (StringUtil.isNotEmpty(sjqitaId)) {
				sjduochu.setSjqitaId(Integer.parseInt(sjqitaId));
			}
			if (StringUtil.isNotEmpty(shujuId)) {
				sjduochu.setShujuId(Integer.parseInt(shujuId));
			}
			if (StringUtil.isNotEmpty(sjleixingId)) {
				sjduochu.setSjleixingId(Integer.parseInt(sjleixingId));
			}
			if (StringUtil.isNotEmpty(sjxingtaiId)) {
				sjduochu.setSjxingtaiId(Integer.parseInt(sjxingtaiId));
			}
			if (StringUtil.isNotEmpty(userId)) {
				sjduochu.setUserId(Integer.parseInt(userId));
			}
			if (StringUtil.isNotEmpty(bumenId)) {
				sjduochu.setBumenId(Integer.parseInt(bumenId));
			}
			if (StringUtil.isNotEmpty(buyuanId)) {
				sjduochu.setBuyuanId(Integer.parseInt(buyuanId));
			}
			if (StringUtil.isNotEmpty(buzhiId)) {
				sjduochu.setBuzhiId(Integer.parseInt(buzhiId));
			}
			if (StringUtil.isNotEmpty(yonghuId)) {
				sjduochu.setYonghuId(Integer.parseInt(yonghuId));
			}
			return Response.success(sjduochuService.querySjduochus(sjduochu,0,0, null, null, null, null));
		} catch (Exception e) {
			//e.printStackTrace();
			return Response.error(204,"服务器错误");
		}
	}

	//统计方法
	@ResponseBody
	@RequestMapping("/sjduochuTongji")
	public Response sjduochuTongji(@RequestBody Object req,HttpServletRequest request, HttpServletResponse response)
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
		String sjduochuName = map.get("sjduochuName");
		String userId=map.get("userId");
		List<Integer> sjxingtaiIds = new ArrayList<Integer>();
		List<Sjxingtai> sjxingtais = new ArrayList<Sjxingtai>();
		List<Sjduochu> sjduochus = new ArrayList<Sjduochu>();
		Sjduochu sjduochu = new Sjduochu();
		if (StringUtil.isNotEmpty(userId)) {
			sjduochu.setUserId(Integer.parseInt(userId));
		}
		try {
			//统计图横（X）轴名称
			List<String> nameList = new ArrayList<String>();
			//统计图竖（Y）轴数值
	        List<Double> zongshuList = new ArrayList<Double>();
	        String tongjiSjduochu = "[";
			sjxingtais = sjxingtaiService.querySjxingtais(null, 0,0);
			for(int i=0;i<sjxingtais.size();i++){
				sjxingtaiIds.add(sjxingtais.get(i).getSjxingtaiId());
				nameList.add(sjxingtais.get(i).getSjxingtaiName());
				Double sjduochuZongshu = 0.0;
				if (StringUtil.isNotEmpty(sjduochuName)) {
					sjduochu.setSjduochuName(sjduochuName);
				}
				sjduochu.setSjxingtaiId(sjxingtais.get(i).getSjxingtaiId());
				sjduochus = sjduochuService.querySjduochus(sjduochu, 0,0,sdate,edate, null, null);
				sjduochuZongshu = sjduochuZongshu + sjduochus.size();
				//统计数值
				zongshuList.add(sjduochuZongshu);
				
				tongjiSjduochu = tongjiSjduochu + "{\"value\":" + sjduochuZongshu + ",\"name\": '" + sjxingtais.get(i).getSjxingtaiName() + "' }";
				if(i<(sjxingtais.size()-1)){
					tongjiSjduochu = tongjiSjduochu + ",";
				}
			}
			tongjiSjduochu = tongjiSjduochu + "]";
			//转为map型
			Map<String,Object> tongjiMap=new HashMap<String,Object>();
			tongjiMap.put("value",zongshuList);
			tongjiMap.put("name",nameList);
			//转为json型
			JSONArray jsonArray = JSONArray.fromObject(tongjiSjduochu);
			return Response.tongjiSuccess(tongjiMap,jsonArray);
		} catch (Exception e) {
			//e.printStackTrace();
			return Response.error(204,"服务器错误");
		}
	}

	//导入方法
	@ResponseBody
	@RequestMapping("/daoruSjduochu")
	public Response daoruSjduochu(@RequestBody Object req,HttpServletRequest request, HttpServletResponse response)
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
	        String fileName = map.get("sjduochuImg");
			File dir = new File(targetDirectory,fileName);
			excelFile = new FileInputStream(dir);
			List<List<String>> list = new ArrayList<List<String>>();
			list = ExcelUtil.jiexiExcel(excelFile);
			for (int i = 1; i < list.size(); i++) {
				List<String> row = list.get(i);
				String sjduochuName = row.get(1);
				String sjduochuDouble = row.get(2);
				String sjduochuDouble1 = row.get(3);
				String sjduochuZong = row.get(4);
				String sjduochuMark = row.get(5);
				String shujuId = row.get(6);
				Sjduochu sjduochu = new Sjduochu();
				if (StringUtil.isNotEmpty(sjduochuName)) {
					sjduochu.setSjduochuName(sjduochuName);
				}
				if (StringUtil.isNotEmpty(sjduochuMark)) {
					sjduochu.setSjduochuMark(sjduochuMark);
				}
				if (StringUtil.isNotEmpty(sjduochuZong)) {
					sjduochu.setSjduochuZong(Integer.parseInt(sjduochuZong));
				}
				if (StringUtil.isNotEmpty(sjduochuDouble)) {
					sjduochu.setSjduochuDouble(Double.parseDouble(sjduochuDouble));
				}
				if (StringUtil.isNotEmpty(sjduochuDouble1)) {
					sjduochu.setSjduochuDouble1(Double.parseDouble(sjduochuDouble1));
				}
				if (StringUtil.isNotEmpty(shujuId)) {
					sjduochu.setShujuId(Integer.parseInt(shujuId));
					Shuju shuju = new Shuju();
					shuju = shujuService.getShuju(Integer.parseInt(shujuId));
					sjduochu.setShujuName(shuju.getShujuName());
				}
				Date date = new Date();
				sjduochu.setSjduochuDate(date);
				sjduochu.setSjduochuType(0);
				sjduochuService.save(sjduochu);
			}
			return Response.success();
		} catch (Exception e) {
			//e.printStackTrace();
			return Response.error(204,"服务器错误");
		}
	}
}
