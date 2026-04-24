package com.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.model.*;
import com.service.*;
import com.util.*;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
//导入导出

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

@Controller
public class GonggaoController {
	@Autowired
	private GonggaoService gonggaoService;
	@Autowired
	private GgtypeService ggtypeService;

	/***上传导入开始***/
	private InputStream excelFile;

	public InputStream getExcelFile() {
		return excelFile;
	}
	/***上传导入结束***/

	//获取列表，搜索条件，返回数据
	@ResponseBody
	@RequestMapping("/getGonggaos")
	public Response getGonggaos(@RequestBody Object req,HttpServletRequest request,
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
		String gonggaoName = map.get("gonggaoName");
		String gonggaoId = map.get("gonggaoId");
		String ggtypeId = map.get("ggtypeId");
		String sdate = map.get("sdate");
		String edate = map.get("edate");
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
		//初始化
		Gonggao gonggao = new Gonggao();
		try {
			/****初始化赋值，搜索参数****/
			if (StringUtil.isNotEmpty(gonggaoName)) {
				gonggao.setGonggaoName(gonggaoName);
			}
			if (StringUtil.isNotEmpty(gonggaoId)) {
				gonggao.setGonggaoId(Integer.parseInt(gonggaoId));
			}
			if (StringUtil.isNotEmpty(ggtypeId)) {
				gonggao.setGgtypeId(Integer.parseInt(ggtypeId));
			}
			//总数
			int total = gonggaoService.queryGonggaos(gonggao, 0, 0, sdate, edate).size();
			//返回页面（列表、总数、行数）
			return Response.success(gonggaoService.queryGonggaos(gonggao, pageBean.getStart(), pageBean.getRows(), sdate, edate),total,Integer.parseInt(rows));
		} catch (Exception e) {
			//e.printStackTrace();
			return Response.error(204,"服务器错误");
		}
	}

	//添加修改同样一个方法，如果id为空就是新增，如果id不为空就会加载原有数据，然后更新数据
	@ResponseBody
	@RequestMapping("/addGonggao")
	public Response addGonggao(@RequestBody Object req,HttpServletRequest request, HttpServletResponse response)
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
	    //model参数
		String gonggaoName = map.get("gonggaoName");
		String gonggaoMark = map.get("gonggaoMark");
		String gonggaoDate = map.get("gonggaoDate");
		String ggtypeId = map.get("ggtypeId");
		String gonggaoImg = map.get("gonggaoImg");
		String gonggaoId = map.get("gonggaoId");
		//model初始化
		Gonggao gonggao = new Gonggao();
		//判断id是否为空，如果不为空则加载原有的model
		if (StringUtil.isNotEmpty(gonggaoId)) {
			gonggao = gonggaoService.getGonggao(Integer.parseInt(gonggaoId));
		}
		/****初始化参数赋值****/
		if (StringUtil.isNotEmpty(gonggaoName)) {
			gonggao.setGonggaoName(gonggaoName);
		}
		if (StringUtil.isNotEmpty(gonggaoMark)) {
			gonggao.setGonggaoMark(gonggaoMark);
		}
		if (StringUtil.isNotEmpty(gonggaoDate)) {
			gonggao.setGonggaoDate(DateUtil.formatString(gonggaoDate,
					"yyyy-MM-dd HH:mm:ss"));
		}
		if (StringUtil.isNotEmpty(ggtypeId)) {
			gonggao.setGgtypeId(Integer.parseInt(ggtypeId));
			Ggtype ggtype = new Ggtype();
			ggtype = ggtypeService.getGgtype(Integer.parseInt(ggtypeId));
			gonggao.setGgtypeName(ggtype.getGgtypeName());
		}
		/****初始化参数赋值结束****/
		//id不为空，修改（更改状态等）；Id为空就是增加；然后返回结果
		try {
			if (StringUtil.isNotEmpty(gonggaoImg)) {
				String str[] = gonggaoImg.split("/");
				gonggaoImg = str[str.length-1];
				gonggao.setGonggaoImg(request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/file/" + gonggaoImg);
				gonggao.setGonggaoImgName(request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/downFile?filename=" + gonggaoImg);
			}
			if (StringUtil.isNotEmpty(gonggaoId)) {
				Date date = new Date();
				gonggao.setGonggaoDate(date);
				gonggaoService.modifyGonggao(gonggao);
				return Response.success();
			} else {
				Date date = new Date();
				gonggao.setGonggaoDate(date);
				gonggaoService.save(gonggao);
				return Response.success();
			}
		} catch (Exception e) {
			//e.printStackTrace();
			return Response.error(204,"服务器错误");
		}
	}

	//删除数据
	@ResponseBody
	@RequestMapping("/deleteGonggao")
	public Response deleteGonggao(@RequestBody Object req,HttpServletRequest request, HttpServletResponse response)
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
				gonggaoService.deleteGonggao(Integer.parseInt(str[i]));
			}
			return Response.success();
		} catch (Exception e) {
			//e.printStackTrace();
			return Response.error(204,"服务器错误");
		}
	}

	//下拉列表参数，跟搜索基本上一样，但是返回的是所有值，不分页
	@ResponseBody
	@RequestMapping("/gonggaoComboList")
	public Response gonggaoComboList(@RequestBody Object req,HttpServletRequest request, HttpServletResponse response)
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
		String ggtypeId = map.get("ggtypeId");
		Gonggao gonggao = new Gonggao();
		if (StringUtil.isNotEmpty(ggtypeId)) {
			gonggao.setGgtypeId(Integer.parseInt(ggtypeId));
		}
		try {
			return Response.success(gonggaoService.queryGonggaos(gonggao, 0, 0, null, null));
		} catch (Exception e) {
			//e.printStackTrace();
			return Response.error(204,"服务器错误");
		}
	}

	//统计方法
	@ResponseBody
	@RequestMapping("/gonggaoTongji")
	public Response gonggaoTongji(@RequestBody Object req,HttpServletRequest request, HttpServletResponse response)
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
		String gonggaoName = map.get("gonggaoName");
		List<Integer> ggtypeIds = new ArrayList<Integer>();
		List<Ggtype> ggtypes = new ArrayList<Ggtype>();
		List<Gonggao> gonggaos = new ArrayList<Gonggao>();
		Gonggao gonggao = new Gonggao();
		try {
			//统计图横（X）轴名称
			List<String> nameList = new ArrayList<String>();
			//统计图竖（Y）轴数值
	        List<Double> zongshuList = new ArrayList<Double>();
	        String tongjiGonggao = "[";
			ggtypes = ggtypeService.queryGgtypes(null, 0,0);
			for(int i=0;i<ggtypes.size();i++){
				ggtypeIds.add(ggtypes.get(i).getGgtypeId());
				nameList.add(ggtypes.get(i).getGgtypeName());
				Double gonggaoZongshu = 0.0;
				if (StringUtil.isNotEmpty(gonggaoName)) {
					gonggao.setGonggaoName(gonggaoName);
				}
				gonggao.setGgtypeId(ggtypes.get(i).getGgtypeId());
				gonggaos = gonggaoService.queryGonggaos(gonggao, 0,0,sdate,edate);
				gonggaoZongshu = gonggaoZongshu + gonggaos.size();
				//统计数值
				zongshuList.add(gonggaoZongshu);
				
				tongjiGonggao = tongjiGonggao + "{\"value\":" + gonggaoZongshu + ",\"name\": '" + ggtypes.get(i).getGgtypeName() + "' }";
				if(i==0){
					tongjiGonggao = tongjiGonggao + ",";
				}
			}
			tongjiGonggao = tongjiGonggao + "]";
			//转为map型
			Map<String,Object> tongjiMap=new HashMap<String,Object>();
			tongjiMap.put("value",zongshuList);
			tongjiMap.put("name",nameList);
			//转为json型
			JSONArray jsonArray = JSONArray.fromObject(tongjiGonggao);
			return Response.tongjiSuccess(tongjiMap,jsonArray);
		} catch (Exception e) {
			//e.printStackTrace();
			return Response.error(204,"服务器错误");
		}
	}
}
