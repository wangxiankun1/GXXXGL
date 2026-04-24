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
public class SjpinglunController {
	@Autowired
	private SjpinglunService sjpinglunService;
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
	@RequestMapping("/getSjpingluns")
	public Response getSjpingluns(@RequestBody Object req,HttpServletRequest request,
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
		String sjpinglunName = map.get("sjpinglunName");
		String sjpinglunMark = map.get("sjpinglunMark");
		String sjpinglunId = map.get("sjpinglunId");
		String sjpinglunType = map.get("sjpinglunType");
		String sjpinglunType1 = map.get("sjpinglunType1");
		String sjpinglunType2 = map.get("sjpinglunType2");
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
		Sjpinglun sjpinglun = new Sjpinglun();
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
			if (StringUtil.isNotEmpty(sjpinglunName)) {
				sjpinglun.setSjpinglunName(sjpinglunName);
			}
			if (StringUtil.isNotEmpty(sjpinglunMark)) {
				sjpinglun.setSjpinglunMark(sjpinglunMark);
			}
			if (StringUtil.isNotEmpty(sjpinglunId)) {
				sjpinglun.setSjpinglunId(Integer.parseInt(sjpinglunId));
			}
			if (StringUtil.isNotEmpty(sjpinglunType)) {
				sjpinglun.setSjpinglunType(Integer.parseInt(sjpinglunType));
			}
			if (StringUtil.isNotEmpty(sjpinglunType1)) {
				sjpinglun.setSjpinglunType1(Integer.parseInt(sjpinglunType1));
			}
			if (StringUtil.isNotEmpty(sjpinglunType2)) {
				sjpinglun.setSjpinglunType2(Integer.parseInt(sjpinglunType2));
			}
			if (StringUtil.isNotEmpty(sjlaiyuanId)) {
				sjpinglun.setSjlaiyuanId(Integer.parseInt(sjlaiyuanId));
			}
			if (StringUtil.isNotEmpty(sjqitaId)) {
				sjpinglun.setSjqitaId(Integer.parseInt(sjqitaId));
			}
			if (StringUtil.isNotEmpty(shujuId)) {
				sjpinglun.setShujuId(Integer.parseInt(shujuId));
			}
			if (StringUtil.isNotEmpty(sjleixingId)) {
				sjpinglun.setSjleixingId(Integer.parseInt(sjleixingId));
			}
			if (StringUtil.isNotEmpty(sjxingtaiId)) {
				sjpinglun.setSjxingtaiId(Integer.parseInt(sjxingtaiId));
			}
			if (StringUtil.isNotEmpty(userId)) {
				sjpinglun.setUserId(Integer.parseInt(userId));
			}
			if (StringUtil.isNotEmpty(bumenId)) {
				sjpinglun.setBumenId(Integer.parseInt(bumenId));
			}
			if (StringUtil.isNotEmpty(buyuanId)) {
				sjpinglun.setBuyuanId(Integer.parseInt(buyuanId));
			}
			if (StringUtil.isNotEmpty(buzhiId)) {
				sjpinglun.setBuzhiId(Integer.parseInt(buzhiId));
			}
			if (StringUtil.isNotEmpty(yonghuId)) {
				sjpinglun.setYonghuId(Integer.parseInt(yonghuId));
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
			int total = sjpinglunService.querySjpingluns(sjpinglun, 0,0, sdate, edate, sdate1, edate1).size();
			return Response.success(sjpinglunService.querySjpingluns(sjpinglun, pageBean.getStart(), pageBean.getRows(), sdate, edate, sdate1, edate1),total,Integer.parseInt(rows));
		} catch (Exception e) {
			//e.printStackTrace();
			return Response.error(204,"服务器错误");
		}
	}

	//添加修改同样一个方法，如果id为空就是新增，如果id不为空就会加载原有数据，然后更新数据
	@ResponseBody
	@RequestMapping("/addSjpinglun")
	public Response addSjpinglun(@RequestBody Object req,HttpServletRequest request, HttpServletResponse response)
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
			String sjpinglunName = map.get("sjpinglunName");
			String sjpinglunMark = map.get("sjpinglunMark");
			String sjpinglunMark1 = map.get("sjpinglunMark1");
			String sjpinglunMark2 = map.get("sjpinglunMark2");
			String sjpinglunMark3 = map.get("sjpinglunMark3");
			String sjpinglunDate = map.get("sjpinglunDate");
			String sjpinglunDate1 = map.get("sjpinglunDate1");
			String sjpinglunZong = map.get("sjpinglunZong");
			String sjpinglunZong1 = map.get("sjpinglunZong1");
			String sjpinglunZong2 = map.get("sjpinglunZong2");
			String sjpinglunDouble = map.get("sjpinglunDouble");
			String sjpinglunDouble1 = map.get("sjpinglunDouble1");
			String sjpinglunDouble2 = map.get("sjpinglunDouble2");
			String sjpinglunType = map.get("sjpinglunType");
			String sjpinglunType1 = map.get("sjpinglunType1");
			String sjpinglunType2 = map.get("sjpinglunType2");
			String shujuId = map.get("shujuId");
			String sjqitaId = map.get("sjqitaId");
			String sjlaiyuanId = map.get("sjlaiyuanId");
			String yonghuId = map.get("yonghuId");
			String userId = map.get("userId");
			String sjpinglunImg = map.get("sjpinglunImg");
			String sjleixingId = map.get("sjleixingId");
			String sjxingtaiId = map.get("sjxingtaiId");
			String buzhiId = map.get("buzhiId");
			String sjpinglunId = map.get("sjpinglunId");
			//model初始化
			Sjpinglun sjpinglun = new Sjpinglun();
			//判断id是否为空，如果不为空则加载原有的model
			if (StringUtil.isNotEmpty(sjpinglunId)) {
				sjpinglun = sjpinglunService.getSjpinglun(Integer.parseInt(sjpinglunId));
			}
			/****初始化参数赋值****/
			if (StringUtil.isNotEmpty(sjpinglunImg)) {
				String str[] = sjpinglunImg.split("/");
				sjpinglunImg = str[str.length-1];
				sjpinglun.setSjpinglunImg(request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/file/" + sjpinglunImg);
				sjpinglun.setSjpinglunImgName(request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/downFile?filename=" + sjpinglunImg);
			}
			if (StringUtil.isNotEmpty(sjpinglunName)) {
				sjpinglun.setSjpinglunName(sjpinglunName);
			}
			if (StringUtil.isNotEmpty(sjpinglunMark)) {
				sjpinglun.setSjpinglunMark(sjpinglunMark);
			}
			if (StringUtil.isNotEmpty(sjpinglunMark1)) {
				sjpinglun.setSjpinglunMark1(sjpinglunMark1);
			}
			if (StringUtil.isNotEmpty(sjpinglunMark2)) {
				sjpinglun.setSjpinglunMark2(sjpinglunMark2);
			}
			if (StringUtil.isNotEmpty(sjpinglunMark3)) {
				sjpinglun.setSjpinglunMark3(sjpinglunMark3);
			}
			if (StringUtil.isNotEmpty(sjpinglunDate)) {
				sjpinglun.setSjpinglunDate(DateUtil.formatString(sjpinglunDate,
						"yyyy-MM-dd HH:mm:ss"));
			}else{
				sjpinglun.setSjpinglunDate(new Date());
			}
			if (StringUtil.isNotEmpty(sjpinglunDate1)) {
				sjpinglun.setSjpinglunDate1(DateUtil.formatString(sjpinglunDate1,
						"yyyy-MM-dd HH:mm:ss"));
			}
			if (StringUtil.isNotEmpty(sjpinglunZong)) {
				sjpinglun.setSjpinglunZong(Integer.parseInt(sjpinglunZong));
			}
			if (StringUtil.isNotEmpty(sjpinglunZong1)) {
				sjpinglun.setSjpinglunZong1(Integer.parseInt(sjpinglunZong1));
			}
			if (StringUtil.isNotEmpty(sjpinglunZong2)) {
				sjpinglun.setSjpinglunZong2(Integer.parseInt(sjpinglunZong2));
			}
			if (StringUtil.isNotEmpty(sjpinglunDouble)) {
				sjpinglun.setSjpinglunDouble(Double.parseDouble(sjpinglunDouble));
			}
			if (StringUtil.isNotEmpty(sjpinglunDouble1)) {
				sjpinglun.setSjpinglunDouble1(Double.parseDouble(sjpinglunDouble1));
			}
			if (StringUtil.isNotEmpty(sjpinglunDouble2)) {
				sjpinglun.setSjpinglunDouble2(Double.parseDouble(sjpinglunDouble2));
			}
			if (StringUtil.isNotEmpty(sjpinglunType)) {
				sjpinglun.setSjpinglunType(Integer.parseInt(sjpinglunType));
			}
			if (StringUtil.isNotEmpty(sjpinglunType1)) {
				sjpinglun.setSjpinglunType1(Integer.parseInt(sjpinglunType1));
			}
			if (StringUtil.isNotEmpty(sjpinglunType2)) {
				sjpinglun.setSjpinglunType2(Integer.parseInt(sjpinglunType2));
			}
			if (StringUtil.isNotEmpty(shujuId)) {
				sjpinglun.setShujuId(Integer.parseInt(shujuId));
				Shuju shuju = new Shuju();
				shuju = shujuService.getShuju(Integer.parseInt(shujuId));
				sjpinglun.setShujuName(shuju.getShujuName());
				sjpinglun.setSjleixingId(shuju.getSjleixingId());
				sjpinglun.setSjleixingName(shuju.getSjleixingName());
				sjpinglun.setSjxingtaiId(shuju.getSjxingtaiId());
				sjpinglun.setSjxingtaiName(shuju.getSjxingtaiName());
				sjpinglun.setYonghuId(shuju.getYonghuId());
				sjpinglun.setYonghuName(shuju.getYonghuName());
				sjpinglun.setUserId(shuju.getUserId());
				sjpinglun.setUserName(shuju.getUserName());
				sjpinglun.setBumenId(shuju.getBumenId());
				sjpinglun.setBumenName(shuju.getBumenName());
				sjpinglun.setBuyuanId(shuju.getBuyuanId());
				sjpinglun.setBuyuanName(shuju.getBuyuanName());
				sjpinglun.setBuzhiId(shuju.getBuzhiId());
				sjpinglun.setBuzhiName(shuju.getBuzhiName());
			}
			if (StringUtil.isNotEmpty(sjqitaId)) {
				sjpinglun.setSjqitaId(Integer.parseInt(sjqitaId));
				Sjqita sjqita = new Sjqita();
				sjqita = sjqitaService.getSjqita(Integer.parseInt(sjqitaId));
				sjpinglun.setSjqitaName(sjqita.getSjqitaName());
			}
			if (StringUtil.isNotEmpty(sjlaiyuanId)) {
				sjpinglun.setSjlaiyuanId(Integer.parseInt(sjlaiyuanId));
				Sjlaiyuan sjlaiyuan = new Sjlaiyuan();
				sjlaiyuan = sjlaiyuanService.getSjlaiyuan(Integer.parseInt(sjlaiyuanId));
				sjpinglun.setSjlaiyuanName(sjlaiyuan.getSjlaiyuanName());
			}
			if (StringUtil.isNotEmpty(yonghuId)) {
				sjpinglun.setYonghuId(Integer.parseInt(yonghuId));
				Yonghu yonghu = new Yonghu();
				yonghu = yonghuService.getYonghu(Integer.parseInt(yonghuId));
				sjpinglun.setYonghuName(yonghu.getYonghuXingming());
				sjpinglun.setBumenId(yonghu.getBumenId());
				sjpinglun.setBumenName(yonghu.getBumenName());
				sjpinglun.setBuyuanId(yonghu.getBuyuanId());
				sjpinglun.setBuyuanName(yonghu.getBuyuanName());
				sjpinglun.setBuzhiId(yonghu.getBuzhiId());
				sjpinglun.setBuzhiName(yonghu.getBuzhiName());
			}
			if (StringUtil.isNotEmpty(userId)) {
				sjpinglun.setUserId(Integer.parseInt(userId));
				User user = new User();
				user = userService.getUser(Integer.parseInt(userId));
				sjpinglun.setUserName(user.getUserXingming());
				sjpinglun.setBumenId(user.getBumenId());
				sjpinglun.setBumenName(user.getBumenName());
				sjpinglun.setBuyuanId(user.getBuyuanId());
				sjpinglun.setBuyuanName(user.getBuyuanName());
				sjpinglun.setBuzhiId(user.getBuzhiId());
				sjpinglun.setBuzhiName(user.getBuzhiName());
			}
			if (StringUtil.isNotEmpty(sjleixingId)) {
				sjpinglun.setSjleixingId(Integer.parseInt(sjleixingId));
				Sjleixing sjleixing = new Sjleixing();
				sjleixing = sjleixingService.getSjleixing(Integer.parseInt(sjleixingId));
				sjpinglun.setSjleixingName(sjleixing.getSjleixingName());
			}
			if (StringUtil.isNotEmpty(sjxingtaiId)) {
				sjpinglun.setSjxingtaiId(Integer.parseInt(sjxingtaiId));
				Sjxingtai sjxingtai = new Sjxingtai();
				sjxingtai = sjxingtaiService.getSjxingtai(Integer.parseInt(sjxingtaiId));
				sjpinglun.setSjxingtaiName(sjxingtai.getSjxingtaiName());
			}
			if (StringUtil.isNotEmpty(buzhiId)) {
				sjpinglun.setBuzhiId(Integer.parseInt(buzhiId));
				Buzhi buzhi = new Buzhi();
				buzhi = buzhiService.getBuzhi(Integer.parseInt(buzhiId));
				sjpinglun.setBuzhiName(buzhi.getBuzhiName());
				sjpinglun.setBumenId(buzhi.getBumenId());
				sjpinglun.setBumenName(buzhi.getBumenName());
				sjpinglun.setBuyuanId(buzhi.getBuyuanId());
				sjpinglun.setBuyuanName(buzhi.getBuyuanName());
			}
			/****初始化参数赋值结束****/
			//id不为空，修改（更改状态等）；Id为空就是增加；然后返回结果
			if (StringUtil.isNotEmpty(sjpinglunId)) {
				sjpinglunService.modifySjpinglun(sjpinglun);
				return Response.success();
			} else {
				sjpinglun.setSjpinglunType(0);
				sjpinglunService.save(sjpinglun);
				return Response.success();
			}
		} catch (Exception e) {
			//e.printStackTrace();
			return Response.error(204,"服务器错误");
		}
	}

	//删除数据
	@ResponseBody
	@RequestMapping("/deleteSjpinglun")
	public Response deleteSjpinglun(@RequestBody Object req,HttpServletRequest request, HttpServletResponse response)
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
				sjpinglunService.deleteSjpinglun(Integer.parseInt(str[i]));
			}
			return Response.success();
		} catch (Exception e) {
			//e.printStackTrace();
			return Response.error(204,"服务器错误");
		}
	}

	//下拉列表参数，跟搜索基本上一样，但是返回的是所有值，不分页
	@ResponseBody
	@RequestMapping("/sjpinglunComboList")
	public Response sjpinglunComboList(@RequestBody Object req,HttpServletRequest request, HttpServletResponse response)
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
		String sjpinglunName = map.get("sjpinglunName");
		String sjpinglunId = map.get("sjpinglunId");
		String sjpinglunType = map.get("sjpinglunType");
		String sjpinglunType1 = map.get("sjpinglunType1");
		String sjpinglunType2 = map.get("sjpinglunType2");
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
		Sjpinglun sjpinglun = new Sjpinglun();
		try {
			if (StringUtil.isNotEmpty(sjpinglunName)) {
				sjpinglun.setSjpinglunName(sjpinglunName);
			}
			if (StringUtil.isNotEmpty(sjpinglunId)) {
				sjpinglun.setSjpinglunId(Integer.parseInt(sjpinglunId));
			}
			if (StringUtil.isNotEmpty(sjpinglunType)) {
				sjpinglun.setSjpinglunType(Integer.parseInt(sjpinglunType));
			}
			if (StringUtil.isNotEmpty(sjpinglunType1)) {
				sjpinglun.setSjpinglunType1(Integer.parseInt(sjpinglunType1));
			}
			if (StringUtil.isNotEmpty(sjpinglunType2)) {
				sjpinglun.setSjpinglunType2(Integer.parseInt(sjpinglunType2));
			}
			if (StringUtil.isNotEmpty(sjlaiyuanId)) {
				sjpinglun.setSjlaiyuanId(Integer.parseInt(sjlaiyuanId));
			}
			if (StringUtil.isNotEmpty(sjqitaId)) {
				sjpinglun.setSjqitaId(Integer.parseInt(sjqitaId));
			}
			if (StringUtil.isNotEmpty(shujuId)) {
				sjpinglun.setShujuId(Integer.parseInt(shujuId));
			}
			if (StringUtil.isNotEmpty(sjleixingId)) {
				sjpinglun.setSjleixingId(Integer.parseInt(sjleixingId));
			}
			if (StringUtil.isNotEmpty(sjxingtaiId)) {
				sjpinglun.setSjxingtaiId(Integer.parseInt(sjxingtaiId));
			}
			if (StringUtil.isNotEmpty(userId)) {
				sjpinglun.setUserId(Integer.parseInt(userId));
			}
			if (StringUtil.isNotEmpty(bumenId)) {
				sjpinglun.setBumenId(Integer.parseInt(bumenId));
			}
			if (StringUtil.isNotEmpty(buyuanId)) {
				sjpinglun.setBuyuanId(Integer.parseInt(buyuanId));
			}
			if (StringUtil.isNotEmpty(buzhiId)) {
				sjpinglun.setBuzhiId(Integer.parseInt(buzhiId));
			}
			if (StringUtil.isNotEmpty(yonghuId)) {
				sjpinglun.setYonghuId(Integer.parseInt(yonghuId));
			}
			return Response.success(sjpinglunService.querySjpingluns(sjpinglun,0,0, null, null, null, null));
		} catch (Exception e) {
			//e.printStackTrace();
			return Response.error(204,"服务器错误");
		}
	}

	//统计方法
	@ResponseBody
	@RequestMapping("/sjpinglunTongji")
	public Response sjpinglunTongji(@RequestBody Object req,HttpServletRequest request, HttpServletResponse response)
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
		String sjpinglunName = map.get("sjpinglunName");
		String userId=map.get("userId");
		List<Integer> shujuIds = new ArrayList<Integer>();
		List<Shuju> shujus = new ArrayList<Shuju>();
		List<Sjpinglun> sjpingluns = new ArrayList<Sjpinglun>();
		Sjpinglun sjpinglun = new Sjpinglun();
		if (StringUtil.isNotEmpty(userId)) {
			sjpinglun.setUserId(Integer.parseInt(userId));
		}
		try {
			//统计图横（X）轴名称
			List<String> nameList = new ArrayList<String>();
			//统计图竖（Y）轴数值
	        List<Double> zongshuList = new ArrayList<Double>();
	        String tongjiSjpinglun = "[";
			shujus = shujuService.queryShujus(null, 0,0,null,null,null,null);
			for(int i=0;i<shujus.size();i++){
				shujuIds.add(shujus.get(i).getShujuId());
				nameList.add(shujus.get(i).getShujuName());
				Double sjpinglunZongshu = 0.0;
				if (StringUtil.isNotEmpty(sjpinglunName)) {
					sjpinglun.setSjpinglunName(sjpinglunName);
				}
				sjpinglun.setShujuId(shujus.get(i).getShujuId());
				sjpingluns = sjpinglunService.querySjpingluns(sjpinglun, 0,0,sdate,edate, null, null);
				for(int j=0;j<sjpingluns.size();j++){
					sjpinglunZongshu = sjpinglunZongshu + sjpingluns.get(j).getSjpinglunZong();
				}
				//统计数值
				zongshuList.add(sjpinglunZongshu);
				
				tongjiSjpinglun = tongjiSjpinglun + "{\"value\":" + sjpinglunZongshu + ",\"name\": '" + shujus.get(i).getShujuName() + "' }";
				if(i==0){
					tongjiSjpinglun = tongjiSjpinglun + ",";
				}
			}
			tongjiSjpinglun = tongjiSjpinglun + "]";
			//转为map型
			Map<String,Object> tongjiMap=new HashMap<String,Object>();
			tongjiMap.put("value",zongshuList);
			tongjiMap.put("name",nameList);
			//转为json型
			JSONArray jsonArray = JSONArray.fromObject(tongjiSjpinglun);
			return Response.tongjiSuccess(tongjiMap,jsonArray);
		} catch (Exception e) {
			//e.printStackTrace();
			return Response.error(204,"服务器错误");
		}
	}

	//导入方法
	@ResponseBody
	@RequestMapping("/daoruSjpinglun")
	public Response daoruSjpinglun(@RequestBody Object req,HttpServletRequest request, HttpServletResponse response)
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
	        String fileName = map.get("sjpinglunImg");
			File dir = new File(targetDirectory,fileName);
			excelFile = new FileInputStream(dir);
			List<List<String>> list = new ArrayList<List<String>>();
			list = ExcelUtil.jiexiExcel(excelFile);
			for (int i = 1; i < list.size(); i++) {
				List<String> row = list.get(i);
				String sjpinglunName = row.get(1);
				String sjpinglunDouble = row.get(2);
				String sjpinglunDouble1 = row.get(3);
				String sjpinglunZong = row.get(4);
				String sjpinglunMark = row.get(5);
				String shujuId = row.get(6);
				Sjpinglun sjpinglun = new Sjpinglun();
				if (StringUtil.isNotEmpty(sjpinglunName)) {
					sjpinglun.setSjpinglunName(sjpinglunName);
				}
				if (StringUtil.isNotEmpty(sjpinglunMark)) {
					sjpinglun.setSjpinglunMark(sjpinglunMark);
				}
				if (StringUtil.isNotEmpty(sjpinglunZong)) {
					sjpinglun.setSjpinglunZong(Integer.parseInt(sjpinglunZong));
				}
				if (StringUtil.isNotEmpty(sjpinglunDouble)) {
					sjpinglun.setSjpinglunDouble(Double.parseDouble(sjpinglunDouble));
				}
				if (StringUtil.isNotEmpty(sjpinglunDouble1)) {
					sjpinglun.setSjpinglunDouble1(Double.parseDouble(sjpinglunDouble1));
				}
				if (StringUtil.isNotEmpty(shujuId)) {
					sjpinglun.setShujuId(Integer.parseInt(shujuId));
					Shuju shuju = new Shuju();
					shuju = shujuService.getShuju(Integer.parseInt(shujuId));
					sjpinglun.setShujuName(shuju.getShujuName());
				}
				Date date = new Date();
				sjpinglun.setSjpinglunDate(date);
				sjpinglun.setSjpinglunType(0);
				sjpinglunService.save(sjpinglun);
			}
			return Response.success();
		} catch (Exception e) {
			//e.printStackTrace();
			return Response.error(204,"服务器错误");
		}
	}
}
