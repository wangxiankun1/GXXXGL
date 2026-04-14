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
public class SjjianchuController {
	@Autowired
	private SjjianchuService sjjianchuService;
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
	@RequestMapping("/getSjjianchus")
	public Response getSjjianchus(@RequestBody Object req,HttpServletRequest request,
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
		String sjjianchuName = map.get("sjjianchuName");
		String sjjianchuMark = map.get("sjjianchuMark");
		String sjjianchuId = map.get("sjjianchuId");
		String sjjianchuType = map.get("sjjianchuType");
		String sjjianchuType1 = map.get("sjjianchuType1");
		String sjjianchuType2 = map.get("sjjianchuType2");
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
		Sjjianchu sjjianchu = new Sjjianchu();
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
			if (StringUtil.isNotEmpty(sjjianchuName)) {
				sjjianchu.setSjjianchuName(sjjianchuName);
			}
			if (StringUtil.isNotEmpty(sjjianchuMark)) {
				sjjianchu.setSjjianchuMark(sjjianchuMark);
			}
			if (StringUtil.isNotEmpty(sjjianchuId)) {
				sjjianchu.setSjjianchuId(Integer.parseInt(sjjianchuId));
			}
			if (StringUtil.isNotEmpty(sjjianchuType)) {
				sjjianchu.setSjjianchuType(Integer.parseInt(sjjianchuType));
			}
			if (StringUtil.isNotEmpty(sjjianchuType1)) {
				sjjianchu.setSjjianchuType1(Integer.parseInt(sjjianchuType1));
			}
			if (StringUtil.isNotEmpty(sjjianchuType2)) {
				sjjianchu.setSjjianchuType2(Integer.parseInt(sjjianchuType2));
			}
			if (StringUtil.isNotEmpty(sjlaiyuanId)) {
				sjjianchu.setSjlaiyuanId(Integer.parseInt(sjlaiyuanId));
			}
			if (StringUtil.isNotEmpty(sjqitaId)) {
				sjjianchu.setSjqitaId(Integer.parseInt(sjqitaId));
			}
			if (StringUtil.isNotEmpty(shujuId)) {
				sjjianchu.setShujuId(Integer.parseInt(shujuId));
			}
			if (StringUtil.isNotEmpty(sjleixingId)) {
				sjjianchu.setSjleixingId(Integer.parseInt(sjleixingId));
			}
			if (StringUtil.isNotEmpty(sjxingtaiId)) {
				sjjianchu.setSjxingtaiId(Integer.parseInt(sjxingtaiId));
			}
			if (StringUtil.isNotEmpty(userId)) {
				sjjianchu.setUserId(Integer.parseInt(userId));
			}
			if (StringUtil.isNotEmpty(bumenId)) {
				sjjianchu.setBumenId(Integer.parseInt(bumenId));
			}
			if (StringUtil.isNotEmpty(buyuanId)) {
				sjjianchu.setBuyuanId(Integer.parseInt(buyuanId));
			}
			if (StringUtil.isNotEmpty(buzhiId)) {
				sjjianchu.setBuzhiId(Integer.parseInt(buzhiId));
			}
			if (StringUtil.isNotEmpty(yonghuId)) {
				sjjianchu.setYonghuId(Integer.parseInt(yonghuId));
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
			int total = sjjianchuService.querySjjianchus(sjjianchu, 0,0, sdate, edate, sdate1, edate1).size();
			return Response.success(sjjianchuService.querySjjianchus(sjjianchu, pageBean.getStart(), pageBean.getRows(), sdate, edate, sdate1, edate1),total,Integer.parseInt(rows));
		} catch (Exception e) {
			//e.printStackTrace();
			return Response.error(204,"服务器错误");
		}
	}

	//添加修改同样一个方法，如果id为空就是新增，如果id不为空就会加载原有数据，然后更新数据
	@ResponseBody
	@RequestMapping("/addSjjianchu")
	public Response addSjjianchu(@RequestBody Object req,HttpServletRequest request, HttpServletResponse response)
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
			String sjjianchuName = map.get("sjjianchuName");
			String sjjianchuMark = map.get("sjjianchuMark");
			String sjjianchuMark1 = map.get("sjjianchuMark1");
			String sjjianchuMark2 = map.get("sjjianchuMark2");
			String sjjianchuMark3 = map.get("sjjianchuMark3");
			String sjjianchuDate = map.get("sjjianchuDate");
			String sjjianchuDate1 = map.get("sjjianchuDate1");
			String sjjianchuZong = map.get("sjjianchuZong");
			String sjjianchuZong1 = map.get("sjjianchuZong1");
			String sjjianchuZong2 = map.get("sjjianchuZong2");
			String sjjianchuDouble = map.get("sjjianchuDouble");
			String sjjianchuDouble1 = map.get("sjjianchuDouble1");
			String sjjianchuDouble2 = map.get("sjjianchuDouble2");
			String sjjianchuType = map.get("sjjianchuType");
			String sjjianchuType1 = map.get("sjjianchuType1");
			String sjjianchuType2 = map.get("sjjianchuType2");
			String shujuId = map.get("shujuId");
			String sjqitaId = map.get("sjqitaId");
			String sjlaiyuanId = map.get("sjlaiyuanId");
			String yonghuId = map.get("yonghuId");
			String userId = map.get("userId");
			String sjjianchuImg = map.get("sjjianchuImg");
			String sjleixingId = map.get("sjleixingId");
			String sjxingtaiId = map.get("sjxingtaiId");
			String buzhiId = map.get("buzhiId");
			String sjjianchuId = map.get("sjjianchuId");
			//model初始化
			Sjjianchu sjjianchu = new Sjjianchu();
			//判断id是否为空，如果不为空则加载原有的model
			if (StringUtil.isNotEmpty(sjjianchuId)) {
				sjjianchu = sjjianchuService.getSjjianchu(Integer.parseInt(sjjianchuId));
			}
			/****初始化参数赋值****/
			if (StringUtil.isNotEmpty(sjjianchuImg)) {
				String str[] = sjjianchuImg.split("/");
				sjjianchuImg = str[str.length-1];
				sjjianchu.setSjjianchuImg(request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/file/" + sjjianchuImg);
				sjjianchu.setSjjianchuImgName(request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/downFile?filename=" + sjjianchuImg);
			}
			if (StringUtil.isNotEmpty(sjjianchuName)) {
				sjjianchu.setSjjianchuName(sjjianchuName);
			}
			if (StringUtil.isNotEmpty(sjjianchuMark)) {
				sjjianchu.setSjjianchuMark(sjjianchuMark);
			}
			if (StringUtil.isNotEmpty(sjjianchuMark1)) {
				sjjianchu.setSjjianchuMark1(sjjianchuMark1);
			}
			if (StringUtil.isNotEmpty(sjjianchuMark2)) {
				sjjianchu.setSjjianchuMark2(sjjianchuMark2);
			}
			if (StringUtil.isNotEmpty(sjjianchuMark3)) {
				sjjianchu.setSjjianchuMark3(sjjianchuMark3);
			}
			if (StringUtil.isNotEmpty(sjjianchuDate)) {
				sjjianchu.setSjjianchuDate(DateUtil.formatString(sjjianchuDate,
						"yyyy-MM-dd HH:mm:ss"));
			}else{
				sjjianchu.setSjjianchuDate(new Date());
			}
			if (StringUtil.isNotEmpty(sjjianchuDate1)) {
				sjjianchu.setSjjianchuDate1(DateUtil.formatString(sjjianchuDate1,
						"yyyy-MM-dd HH:mm:ss"));
			}
			if (StringUtil.isNotEmpty(sjjianchuZong)) {
				sjjianchu.setSjjianchuZong(Integer.parseInt(sjjianchuZong));
			}
			if (StringUtil.isNotEmpty(sjjianchuZong1)) {
				sjjianchu.setSjjianchuZong1(Integer.parseInt(sjjianchuZong1));
			}
			if (StringUtil.isNotEmpty(sjjianchuZong2)) {
				sjjianchu.setSjjianchuZong2(Integer.parseInt(sjjianchuZong2));
			}
			if (StringUtil.isNotEmpty(sjjianchuDouble)) {
				sjjianchu.setSjjianchuDouble(Double.parseDouble(sjjianchuDouble));
			}
			if (StringUtil.isNotEmpty(sjjianchuDouble1)) {
				sjjianchu.setSjjianchuDouble1(Double.parseDouble(sjjianchuDouble1));
			}
			if (StringUtil.isNotEmpty(sjjianchuDouble2)) {
				sjjianchu.setSjjianchuDouble2(Double.parseDouble(sjjianchuDouble2));
			}
			if (StringUtil.isNotEmpty(sjjianchuType)) {
				sjjianchu.setSjjianchuType(Integer.parseInt(sjjianchuType));
			}
			if (StringUtil.isNotEmpty(sjjianchuType1)) {
				sjjianchu.setSjjianchuType1(Integer.parseInt(sjjianchuType1));
			}
			if (StringUtil.isNotEmpty(sjjianchuType2)) {
				sjjianchu.setSjjianchuType2(Integer.parseInt(sjjianchuType2));
			}
			if (StringUtil.isNotEmpty(shujuId)) {
				sjjianchu.setShujuId(Integer.parseInt(shujuId));
				Shuju shuju = new Shuju();
				shuju = shujuService.getShuju(Integer.parseInt(shujuId));
				sjjianchu.setShujuName(shuju.getShujuName());
				sjjianchu.setSjleixingId(shuju.getSjleixingId());
				sjjianchu.setSjleixingName(shuju.getSjleixingName());
				sjjianchu.setSjxingtaiId(shuju.getSjxingtaiId());
				sjjianchu.setSjxingtaiName(shuju.getSjxingtaiName());
				sjjianchu.setYonghuId(shuju.getYonghuId());
				sjjianchu.setYonghuName(shuju.getYonghuName());
				sjjianchu.setUserId(shuju.getUserId());
				sjjianchu.setUserName(shuju.getUserName());
				sjjianchu.setBumenId(shuju.getBumenId());
				sjjianchu.setBumenName(shuju.getBumenName());
				sjjianchu.setBuyuanId(shuju.getBuyuanId());
				sjjianchu.setBuyuanName(shuju.getBuyuanName());
				sjjianchu.setBuzhiId(shuju.getBuzhiId());
				sjjianchu.setBuzhiName(shuju.getBuzhiName());
			}
			if (StringUtil.isNotEmpty(sjqitaId)) {
				sjjianchu.setSjqitaId(Integer.parseInt(sjqitaId));
				Sjqita sjqita = new Sjqita();
				sjqita = sjqitaService.getSjqita(Integer.parseInt(sjqitaId));
				sjjianchu.setSjqitaName(sjqita.getSjqitaName());
			}
			if (StringUtil.isNotEmpty(sjlaiyuanId)) {
				sjjianchu.setSjlaiyuanId(Integer.parseInt(sjlaiyuanId));
				Sjlaiyuan sjlaiyuan = new Sjlaiyuan();
				sjlaiyuan = sjlaiyuanService.getSjlaiyuan(Integer.parseInt(sjlaiyuanId));
				sjjianchu.setSjlaiyuanName(sjlaiyuan.getSjlaiyuanName());
			}
			if (StringUtil.isNotEmpty(yonghuId)) {
				sjjianchu.setYonghuId(Integer.parseInt(yonghuId));
				Yonghu yonghu = new Yonghu();
				yonghu = yonghuService.getYonghu(Integer.parseInt(yonghuId));
				sjjianchu.setYonghuName(yonghu.getYonghuXingming());
				sjjianchu.setBumenId(yonghu.getBumenId());
				sjjianchu.setBumenName(yonghu.getBumenName());
				sjjianchu.setBuyuanId(yonghu.getBuyuanId());
				sjjianchu.setBuyuanName(yonghu.getBuyuanName());
				sjjianchu.setBuzhiId(yonghu.getBuzhiId());
				sjjianchu.setBuzhiName(yonghu.getBuzhiName());
			}
			if (StringUtil.isNotEmpty(userId)) {
				sjjianchu.setUserId(Integer.parseInt(userId));
				User user = new User();
				user = userService.getUser(Integer.parseInt(userId));
				sjjianchu.setUserName(user.getUserXingming());
				sjjianchu.setBumenId(user.getBumenId());
				sjjianchu.setBumenName(user.getBumenName());
				sjjianchu.setBuyuanId(user.getBuyuanId());
				sjjianchu.setBuyuanName(user.getBuyuanName());
				sjjianchu.setBuzhiId(user.getBuzhiId());
				sjjianchu.setBuzhiName(user.getBuzhiName());
			}
			if (StringUtil.isNotEmpty(sjleixingId)) {
				sjjianchu.setSjleixingId(Integer.parseInt(sjleixingId));
				Sjleixing sjleixing = new Sjleixing();
				sjleixing = sjleixingService.getSjleixing(Integer.parseInt(sjleixingId));
				sjjianchu.setSjleixingName(sjleixing.getSjleixingName());
			}
			if (StringUtil.isNotEmpty(sjxingtaiId)) {
				sjjianchu.setSjxingtaiId(Integer.parseInt(sjxingtaiId));
				Sjxingtai sjxingtai = new Sjxingtai();
				sjxingtai = sjxingtaiService.getSjxingtai(Integer.parseInt(sjxingtaiId));
				sjjianchu.setSjxingtaiName(sjxingtai.getSjxingtaiName());
			}
			if (StringUtil.isNotEmpty(buzhiId)) {
				sjjianchu.setBuzhiId(Integer.parseInt(buzhiId));
				Buzhi buzhi = new Buzhi();
				buzhi = buzhiService.getBuzhi(Integer.parseInt(buzhiId));
				sjjianchu.setBuzhiName(buzhi.getBuzhiName());
				sjjianchu.setBumenId(buzhi.getBumenId());
				sjjianchu.setBumenName(buzhi.getBumenName());
				sjjianchu.setBuyuanId(buzhi.getBuyuanId());
				sjjianchu.setBuyuanName(buzhi.getBuyuanName());
			}
			/****初始化参数赋值结束****/
			//id不为空，修改（更改状态等）；Id为空就是增加；然后返回结果
			if (StringUtil.isNotEmpty(sjjianchuId)) {
				sjjianchuService.modifySjjianchu(sjjianchu);
				return Response.success();
			} else {
				sjjianchu.setSjjianchuType(0);
				sjjianchuService.save(sjjianchu);
				return Response.success();
			}
		} catch (Exception e) {
			//e.printStackTrace();
			return Response.error(204,"服务器错误");
		}
	}

	//删除数据
	@ResponseBody
	@RequestMapping("/deleteSjjianchu")
	public Response deleteSjjianchu(@RequestBody Object req,HttpServletRequest request, HttpServletResponse response)
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
				sjjianchuService.deleteSjjianchu(Integer.parseInt(str[i]));
			}
			return Response.success();
		} catch (Exception e) {
			//e.printStackTrace();
			return Response.error(204,"服务器错误");
		}
	}

	//下拉列表参数，跟搜索基本上一样，但是返回的是所有值，不分页
	@ResponseBody
	@RequestMapping("/sjjianchuComboList")
	public Response sjjianchuComboList(@RequestBody Object req,HttpServletRequest request, HttpServletResponse response)
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
		String sjjianchuName = map.get("sjjianchuName");
		String sjjianchuId = map.get("sjjianchuId");
		String sjjianchuType = map.get("sjjianchuType");
		String sjjianchuType1 = map.get("sjjianchuType1");
		String sjjianchuType2 = map.get("sjjianchuType2");
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
		Sjjianchu sjjianchu = new Sjjianchu();
		try {
			if (StringUtil.isNotEmpty(sjjianchuName)) {
				sjjianchu.setSjjianchuName(sjjianchuName);
			}
			if (StringUtil.isNotEmpty(sjjianchuId)) {
				sjjianchu.setSjjianchuId(Integer.parseInt(sjjianchuId));
			}
			if (StringUtil.isNotEmpty(sjjianchuType)) {
				sjjianchu.setSjjianchuType(Integer.parseInt(sjjianchuType));
			}
			if (StringUtil.isNotEmpty(sjjianchuType1)) {
				sjjianchu.setSjjianchuType1(Integer.parseInt(sjjianchuType1));
			}
			if (StringUtil.isNotEmpty(sjjianchuType2)) {
				sjjianchu.setSjjianchuType2(Integer.parseInt(sjjianchuType2));
			}
			if (StringUtil.isNotEmpty(sjlaiyuanId)) {
				sjjianchu.setSjlaiyuanId(Integer.parseInt(sjlaiyuanId));
			}
			if (StringUtil.isNotEmpty(sjqitaId)) {
				sjjianchu.setSjqitaId(Integer.parseInt(sjqitaId));
			}
			if (StringUtil.isNotEmpty(shujuId)) {
				sjjianchu.setShujuId(Integer.parseInt(shujuId));
			}
			if (StringUtil.isNotEmpty(sjleixingId)) {
				sjjianchu.setSjleixingId(Integer.parseInt(sjleixingId));
			}
			if (StringUtil.isNotEmpty(sjxingtaiId)) {
				sjjianchu.setSjxingtaiId(Integer.parseInt(sjxingtaiId));
			}
			if (StringUtil.isNotEmpty(userId)) {
				sjjianchu.setUserId(Integer.parseInt(userId));
			}
			if (StringUtil.isNotEmpty(bumenId)) {
				sjjianchu.setBumenId(Integer.parseInt(bumenId));
			}
			if (StringUtil.isNotEmpty(buyuanId)) {
				sjjianchu.setBuyuanId(Integer.parseInt(buyuanId));
			}
			if (StringUtil.isNotEmpty(buzhiId)) {
				sjjianchu.setBuzhiId(Integer.parseInt(buzhiId));
			}
			if (StringUtil.isNotEmpty(yonghuId)) {
				sjjianchu.setYonghuId(Integer.parseInt(yonghuId));
			}
			return Response.success(sjjianchuService.querySjjianchus(sjjianchu,0,0, null, null, null, null));
		} catch (Exception e) {
			//e.printStackTrace();
			return Response.error(204,"服务器错误");
		}
	}

	//统计方法
	@ResponseBody
	@RequestMapping("/sjjianchuTongji")
	public Response sjjianchuTongji(@RequestBody Object req,HttpServletRequest request, HttpServletResponse response)
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
		String sjjianchuName = map.get("sjjianchuName");
		String userId=map.get("userId");
		List<Integer> shujuIds = new ArrayList<Integer>();
		List<Shuju> shujus = new ArrayList<Shuju>();
		List<Sjjianchu> sjjianchus = new ArrayList<Sjjianchu>();
		Sjjianchu sjjianchu = new Sjjianchu();
		if (StringUtil.isNotEmpty(userId)) {
			sjjianchu.setUserId(Integer.parseInt(userId));
		}
		try {
			//统计图横（X）轴名称
			List<String> nameList = new ArrayList<String>();
			//统计图竖（Y）轴数值
	        List<Double> zongshuList = new ArrayList<Double>();
	        String tongjiSjjianchu = "[";
			shujus = shujuService.queryShujus(null, 0,0,null,null,null,null);
			for(int i=0;i<shujus.size();i++){
				shujuIds.add(shujus.get(i).getShujuId());
				nameList.add(shujus.get(i).getShujuName());
				Double sjjianchuZongshu = 0.0;
				if (StringUtil.isNotEmpty(sjjianchuName)) {
					sjjianchu.setSjjianchuName(sjjianchuName);
				}
				sjjianchu.setShujuId(shujus.get(i).getShujuId());
				sjjianchus = sjjianchuService.querySjjianchus(sjjianchu, 0,0,sdate,edate, null, null);
				for(int j=0;j<sjjianchus.size();j++){
					sjjianchuZongshu = sjjianchuZongshu + sjjianchus.get(j).getSjjianchuZong();
				}
				//统计数值
				zongshuList.add(sjjianchuZongshu);
				
				tongjiSjjianchu = tongjiSjjianchu + "{\"value\":" + sjjianchuZongshu + ",\"name\": '" + shujus.get(i).getShujuName() + "' }";
				if(i==0){
					tongjiSjjianchu = tongjiSjjianchu + ",";
				}
			}
			tongjiSjjianchu = tongjiSjjianchu + "]";
			//转为map型
			Map<String,Object> tongjiMap=new HashMap<String,Object>();
			tongjiMap.put("value",zongshuList);
			tongjiMap.put("name",nameList);
			//转为json型
			JSONArray jsonArray = JSONArray.fromObject(tongjiSjjianchu);
			return Response.tongjiSuccess(tongjiMap,jsonArray);
		} catch (Exception e) {
			//e.printStackTrace();
			return Response.error(204,"服务器错误");
		}
	}

	//导入方法
	@ResponseBody
	@RequestMapping("/daoruSjjianchu")
	public Response daoruSjjianchu(@RequestBody Object req,HttpServletRequest request, HttpServletResponse response)
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
	        String fileName = map.get("sjjianchuImg");
			File dir = new File(targetDirectory,fileName);
			excelFile = new FileInputStream(dir);
			List<List<String>> list = new ArrayList<List<String>>();
			list = ExcelUtil.jiexiExcel(excelFile);
			for (int i = 1; i < list.size(); i++) {
				List<String> row = list.get(i);
				String sjjianchuName = row.get(1);
				String sjjianchuDouble = row.get(2);
				String sjjianchuDouble1 = row.get(3);
				String sjjianchuZong = row.get(4);
				String sjjianchuMark = row.get(5);
				String shujuId = row.get(6);
				Sjjianchu sjjianchu = new Sjjianchu();
				if (StringUtil.isNotEmpty(sjjianchuName)) {
					sjjianchu.setSjjianchuName(sjjianchuName);
				}
				if (StringUtil.isNotEmpty(sjjianchuMark)) {
					sjjianchu.setSjjianchuMark(sjjianchuMark);
				}
				if (StringUtil.isNotEmpty(sjjianchuZong)) {
					sjjianchu.setSjjianchuZong(Integer.parseInt(sjjianchuZong));
				}
				if (StringUtil.isNotEmpty(sjjianchuDouble)) {
					sjjianchu.setSjjianchuDouble(Double.parseDouble(sjjianchuDouble));
				}
				if (StringUtil.isNotEmpty(sjjianchuDouble1)) {
					sjjianchu.setSjjianchuDouble1(Double.parseDouble(sjjianchuDouble1));
				}
				if (StringUtil.isNotEmpty(shujuId)) {
					sjjianchu.setShujuId(Integer.parseInt(shujuId));
					Shuju shuju = new Shuju();
					shuju = shujuService.getShuju(Integer.parseInt(shujuId));
					sjjianchu.setShujuName(shuju.getShujuName());
				}
				Date date = new Date();
				sjjianchu.setSjjianchuDate(date);
				sjjianchu.setSjjianchuType(0);
				sjjianchuService.save(sjjianchu);
			}
			return Response.success();
		} catch (Exception e) {
			//e.printStackTrace();
			return Response.error(204,"服务器错误");
		}
	}
}
