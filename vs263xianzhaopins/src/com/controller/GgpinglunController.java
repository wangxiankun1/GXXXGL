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

import java.io.InputStream;

@Controller
public class GgpinglunController {
	@Autowired
	private GonggaoService gonggaoService;
	@Autowired
	private GgpinglunService ggpinglunService;
	@Autowired
	private YonghuService yonghuService;
	@Autowired
	private UserService userService;
	@Autowired
	private GgtypeService ggtypeService;

	/***上传导入开始***/
	private InputStream excelFile;

	public InputStream getExcelFile() {
		return excelFile;
	}
	/***上传导入结束***/

	//添加修改同样一个方法，如果id为空就是新增，如果id不为空就会加载原有数据，然后更新数据
	@ResponseBody
	@RequestMapping("/addGgpinglun")
	public Response addGgpinglun(@RequestBody Object req,HttpServletRequest request, HttpServletResponse response)
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
			String ggpinglunName = map.get("ggpinglunName");
			String ggpinglunMark = map.get("ggpinglunMark");
			String ggpinglunDate = map.get("ggpinglunDate");
			String ggpinglunType = map.get("ggpinglunType");
			String ggpinglunType1 = map.get("ggpinglunType1");
			String gonggaoId = map.get("gonggaoId");
			String yonghuId = map.get("yonghuId");
			String userId = map.get("userId");
			String ggpinglunImg = map.get("ggpinglunImg");
			String ggpinglunId = map.get("ggpinglunId");
			//model初始化
			Ggpinglun ggpinglun = new Ggpinglun();
			//判断id是否为空，如果不为空则加载原有的model
			if (StringUtil.isNotEmpty(ggpinglunId)) {
				ggpinglun = ggpinglunService.getGgpinglun(Integer.parseInt(ggpinglunId));
			}
			/****初始化参数赋值****/
			if (StringUtil.isNotEmpty(ggpinglunImg)) {
				String str[] = ggpinglunImg.split("/");
				ggpinglunImg = str[str.length-1];
				ggpinglun.setGgpinglunImg(request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/file/" + ggpinglunImg);
				ggpinglun.setGgpinglunImgName(request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/downFile?filename=" + ggpinglunImg);
			}
			if (StringUtil.isNotEmpty(ggpinglunName)) {
				ggpinglun.setGgpinglunName(ggpinglunName);
			}
			if (StringUtil.isNotEmpty(ggpinglunMark)) {
				ggpinglun.setGgpinglunMark(ggpinglunMark);
			}
			if (StringUtil.isNotEmpty(ggpinglunDate)) {
				ggpinglun.setGgpinglunDate(DateUtil.formatString(ggpinglunDate,
						"yyyy-MM-dd HH:mm:ss"));
			}
			if (StringUtil.isNotEmpty(ggpinglunType)) {
				ggpinglun.setGgpinglunType(Integer.parseInt(ggpinglunType));
			}
			if (StringUtil.isNotEmpty(ggpinglunType1)) {
				ggpinglun.setGgpinglunType1(Integer.parseInt(ggpinglunType1));
			}
			if (StringUtil.isNotEmpty(gonggaoId)) {
				ggpinglun.setGonggaoId(Integer.parseInt(gonggaoId));
				Gonggao gonggao = new Gonggao();
				gonggao = gonggaoService.getGonggao(Integer.parseInt(gonggaoId));
				ggpinglun.setGonggaoName(gonggao.getGonggaoName());
				ggpinglun.setGgtypeId(gonggao.getGgtypeId());
				ggpinglun.setGgtypeName(gonggao.getGgtypeName());
			}
			if (StringUtil.isNotEmpty(yonghuId)) {
				ggpinglun.setYonghuId(Integer.parseInt(yonghuId));
				Yonghu yonghu = new Yonghu();
				yonghu = yonghuService.getYonghu(Integer.parseInt(yonghuId));
				ggpinglun.setYonghuName(yonghu.getYonghuXingming());
				ggpinglun.setUserId(yonghu.getUserId());
				ggpinglun.setUserName(yonghu.getUserName());
				ggpinglun.setBumenId(yonghu.getBumenId());
				ggpinglun.setBumenName(yonghu.getBumenName());
				ggpinglun.setBuyuanId(yonghu.getBuyuanId());
				ggpinglun.setBuyuanName(yonghu.getBuyuanName());
				ggpinglun.setBuzhiId(yonghu.getBuzhiId());
				ggpinglun.setBuzhiName(yonghu.getBuzhiName());
			}
			if (StringUtil.isNotEmpty(userId)) {
				ggpinglun.setUserId(Integer.parseInt(userId));
				User user = new User();
				user = userService.getUser(Integer.parseInt(userId));
				ggpinglun.setUserName(user.getUserXingming());
				ggpinglun.setBumenId(user.getBumenId());
				ggpinglun.setBumenName(user.getBumenName());
				ggpinglun.setBuyuanId(user.getBuyuanId());
				ggpinglun.setBuyuanName(user.getBuyuanName());
				ggpinglun.setBuzhiId(user.getBuzhiId());
				ggpinglun.setBuzhiName(user.getBuzhiName());
			}
			/****初始化参数赋值结束****/
			//id不为空，修改（更改状态等）；Id为空就是增加；然后返回结果
			if (StringUtil.isNotEmpty(ggpinglunId)) {
				ggpinglunService.modifyGgpinglun(ggpinglun);
				return Response.success();
			} else {
				Date date = new Date();
				ggpinglun.setGgpinglunDate(date);
				ggpinglun.setGgpinglunType(0);
				ggpinglunService.save(ggpinglun);
				return Response.success();
			}
		} catch (Exception e) {
			//e.printStackTrace();
			return Response.error(204,"服务器错误");
		}
	}

	//获取列表，搜索条件，返回数据
	@ResponseBody
	@RequestMapping("/getGgpingluns")
	public Response getGgpingluns(@RequestBody Object req,HttpServletRequest request,
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
		String ggpinglunName = map.get("ggpinglunName");
		String ggpinglunId = map.get("ggpinglunId");
		String ggpinglunType = map.get("ggpinglunType");
		String ggpinglunType1 = map.get("ggpinglunType1");
		String gonggaoId = map.get("gonggaoId");
		String ggtypeId = map.get("ggtypeId");
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
		Ggpinglun ggpinglun = new Ggpinglun();
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
			if (StringUtil.isNotEmpty(ggpinglunName)) {
				ggpinglun.setGgpinglunName(ggpinglunName);
			}
			if (StringUtil.isNotEmpty(ggpinglunId)) {
				ggpinglun.setGgpinglunId(Integer.parseInt(ggpinglunId));
			}
			if (StringUtil.isNotEmpty(ggpinglunType)) {
				ggpinglun.setGgpinglunType(Integer.parseInt(ggpinglunType));
			}
			if (StringUtil.isNotEmpty(ggpinglunType1)) {
				ggpinglun.setGgpinglunType1(Integer.parseInt(ggpinglunType1));
			}
			if (StringUtil.isNotEmpty(gonggaoId)) {
				ggpinglun.setGonggaoId(Integer.parseInt(gonggaoId));
			}
			if (StringUtil.isNotEmpty(ggtypeId)) {
				ggpinglun.setGgtypeId(Integer.parseInt(ggtypeId));
			}
			if (StringUtil.isNotEmpty(userId)) {
				ggpinglun.setUserId(Integer.parseInt(userId));
			}
			if (StringUtil.isNotEmpty(bumenId)) {
				ggpinglun.setBumenId(Integer.parseInt(bumenId));
			}
			if (StringUtil.isNotEmpty(buyuanId)) {
				ggpinglun.setBuyuanId(Integer.parseInt(buyuanId));
			}
			if (StringUtil.isNotEmpty(buzhiId)) {
				ggpinglun.setBuzhiId(Integer.parseInt(buzhiId));
			}
			if (StringUtil.isNotEmpty(yonghuId)) {
				ggpinglun.setYonghuId(Integer.parseInt(yonghuId));
			}
			if (StringUtil.isNotEmpty(sdate1)) {
				Date date = new Date();
				sdate1 = DateUtil.formatDate(date, "yyyy-MM-dd HH:mm:ss");
			}
			if (StringUtil.isNotEmpty(edate1)) {
				Date date = new Date();
				edate1 = DateUtil.formatDate(date, "yyyy-MM-dd HH:mm:ss");
			}
			//总数
			int total = ggpinglunService.queryGgpingluns(ggpinglun, 0,0, sdate, edate, sdate1, edate1).size();
			//返回页面（列表、总数、行数）
			return Response.success(ggpinglunService.queryGgpingluns(ggpinglun, pageBean.getStart(), pageBean.getRows(), sdate, edate, sdate1, edate1),total,Integer.parseInt(rows));
		} catch (Exception e) {
			//e.printStackTrace();
			return Response.error(204,"服务器错误");
		}
	}

	//删除数据
	@ResponseBody
	@RequestMapping("/deleteGgpinglun")
	public Response deleteGgpinglun(@RequestBody Object req,HttpServletRequest request, HttpServletResponse response)
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
				ggpinglunService.deleteGgpinglun(Integer.parseInt(str[i]));
			}
			return Response.success();
		} catch (Exception e) {
			//e.printStackTrace();
			return Response.error(204,"服务器错误");
		}
	}

	//下拉列表参数，跟搜索基本上一样，但是返回的是所有值，不分页
	@ResponseBody
	@RequestMapping("/ggpinglunComboList")
	public Response ggpinglunComboList(@RequestBody Object req,HttpServletRequest request, HttpServletResponse response)
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
		String ggpinglunName = map.get("ggpinglunName");
		String ggpinglunId = map.get("ggpinglunId");
		String ggpinglunType = map.get("ggpinglunType");
		String ggpinglunType1 = map.get("ggpinglunType1");
		String gonggaoId = map.get("gonggaoId");
		String ggtypeId = map.get("ggtypeId");
		String yonghuId = map.get("yonghuId");
		String userId = map.get("userId");
		String bumenId = map.get("bumenId");
		String buyuanId = map.get("buyuanId");
		String buzhiId = map.get("buzhiId");
		Ggpinglun ggpinglun = new Ggpinglun();
		try {
			if (StringUtil.isNotEmpty(ggpinglunName)) {
				ggpinglun.setGgpinglunName(ggpinglunName);
			}
			if (StringUtil.isNotEmpty(ggpinglunId)) {
				ggpinglun.setGgpinglunId(Integer.parseInt(ggpinglunId));
			}
			if (StringUtil.isNotEmpty(ggpinglunType)) {
				ggpinglun.setGgpinglunType(Integer.parseInt(ggpinglunType));
			}
			if (StringUtil.isNotEmpty(ggpinglunType1)) {
				ggpinglun.setGgpinglunType1(Integer.parseInt(ggpinglunType1));
			}
			if (StringUtil.isNotEmpty(gonggaoId)) {
				ggpinglun.setGonggaoId(Integer.parseInt(gonggaoId));
			}
			if (StringUtil.isNotEmpty(ggtypeId)) {
				ggpinglun.setGgtypeId(Integer.parseInt(ggtypeId));
			}
			if (StringUtil.isNotEmpty(userId)) {
				ggpinglun.setUserId(Integer.parseInt(userId));
			}
			if (StringUtil.isNotEmpty(bumenId)) {
				ggpinglun.setBumenId(Integer.parseInt(bumenId));
			}
			if (StringUtil.isNotEmpty(buyuanId)) {
				ggpinglun.setBuyuanId(Integer.parseInt(buyuanId));
			}
			if (StringUtil.isNotEmpty(buzhiId)) {
				ggpinglun.setBuzhiId(Integer.parseInt(buzhiId));
			}
			if (StringUtil.isNotEmpty(yonghuId)) {
				ggpinglun.setYonghuId(Integer.parseInt(yonghuId));
			}
			return Response.success(ggpinglunService.queryGgpingluns(ggpinglun,0,0, null, null, null, null));
		} catch (Exception e) {
			//e.printStackTrace();
			return Response.error(204,"服务器错误");
		}
	}

	@ResponseBody
	@RequestMapping("/ggpinglunTongji")
	public Response ggpinglunTongji(@RequestBody Object req,HttpServletRequest request, HttpServletResponse response)
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
		String ggpinglunName = map.get("ggpinglunName");
		String userId=map.get("userId");
		List<Integer> ggtypeIds = new ArrayList<Integer>();
		List<Ggtype> ggtypes = new ArrayList<Ggtype>();
		List<Ggpinglun> ggpingluns = new ArrayList<Ggpinglun>();
		Ggpinglun ggpinglun = new Ggpinglun();
		if (StringUtil.isNotEmpty(userId)) {
			ggpinglun.setUserId(Integer.parseInt(userId));
		}
		try {
			//统计图横（X）轴名称
			List<String> nameList = new ArrayList<String>();
			//统计图竖（Y）轴数值
	        List<Double> zongshuList = new ArrayList<Double>();
	        String tongjiGgpinglun = "[";
			ggtypes = ggtypeService.queryGgtypes(null, 0,0);
			for(int i=0;i<ggtypes.size();i++){
				ggtypeIds.add(ggtypes.get(i).getGgtypeId());
				nameList.add(ggtypes.get(i).getGgtypeName());
				Double ggpinglunZongshu = 0.0;
				if (StringUtil.isNotEmpty(ggpinglunName)) {
					ggpinglun.setGgpinglunName(ggpinglunName);
				}
				ggpinglun.setGgtypeId(ggtypes.get(i).getGgtypeId());
				ggpingluns = ggpinglunService.queryGgpingluns(ggpinglun, 0,0,sdate,edate,null,null);
				ggpinglunZongshu = ggpinglunZongshu + ggpingluns.size();
				//统计数值
				zongshuList.add(ggpinglunZongshu);
				
				tongjiGgpinglun = tongjiGgpinglun + "{\"value\":" + ggpinglunZongshu + ",\"name\": '" + ggtypes.get(i).getGgtypeName() + "' }";
				if(i==0){
					tongjiGgpinglun = tongjiGgpinglun + ",";
				}
			}
			tongjiGgpinglun = tongjiGgpinglun + "]";
			//转为map型
			Map<String,Object> tongjiMap=new HashMap<String,Object>();
			tongjiMap.put("value",zongshuList);
			tongjiMap.put("name",nameList);
			//转为json型
			JSONArray jsonArray = JSONArray.fromObject(tongjiGgpinglun);
			return Response.tongjiSuccess(tongjiMap,jsonArray);
		} catch (Exception e) {
			//e.printStackTrace();
			return Response.error(204,"服务器错误");
		}
	}
}
