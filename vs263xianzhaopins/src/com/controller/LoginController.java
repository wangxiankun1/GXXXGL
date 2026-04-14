package com.controller;

import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
public class LoginController {
	@Autowired
	private RizhiService rizhiService;
	@Autowired
	private UserService userService;
	@Autowired
	private YonghuService yonghuService;
	@Autowired
	private AdminService adminService;
	@Autowired
	private JcpeizhiService jcpeizhiService;
	@Autowired
	private JcdaohangService jcdaohangService;
	@Autowired
	private JcbiaotiService jcbiaotiService;
	
	public Jcpeizhi jiazaiPeizhi() {
		List<Jcpeizhi> jcpeizhis = new ArrayList<Jcpeizhi>();
		jcpeizhis = jcpeizhiService.queryJcpeizhis(null, 0, 0);
		if(jcpeizhis.size()>0){
			return jcpeizhis.get(0);
		}else{
			return null;
		}
	}
	
	public List<Jcbiaoti> jiazaiBiaoti(int jcbiaotiType) {
		Jcbiaoti jcbiaoti = new Jcbiaoti();
		jcbiaoti.setJcbiaotiType(jcbiaotiType);
		jcbiaoti.setJcbiaotiType1(0);
		List<Jcbiaoti> jcbiaotis = new ArrayList<Jcbiaoti>();
		jcbiaotis = jcbiaotiService.queryJcbiaotis(jcbiaoti, 0, 0);
		return jcbiaotis;
	}
	
	public List<List<Jcdaohang>> jiazaiDaohang(List<Jcbiaoti> jcbiaotis) {
		List<List<Jcdaohang>> jcdaohangslist = new ArrayList<List<Jcdaohang>>();
		for (int i = 0; i < jcbiaotis.size(); i++) {
			List<Jcdaohang> jcdaohangs = new ArrayList<Jcdaohang>();
			Jcdaohang jcdaohang = new Jcdaohang();
			jcdaohang.setJcdaohangType1(0);
			jcdaohang.setJcbiaotiId(jcbiaotis.get(i).getJcbiaotiId());
			jcdaohangs = jcdaohangService.queryJcdaohangs(jcdaohang,0 ,0);
			jcdaohangslist.add(jcdaohangs);
		}
		return jcdaohangslist;
	}
	@ResponseBody
	@RequestMapping("/login")
	public Response loginUser(@RequestBody Object req,HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Map<String,String> map = (Map<String, String>) req;
		String userName = map.get("userName");
		String password = map.get("password");
		String loginType = map.get("loginType");		
		String ip = request.getRemoteAddr();
		Date date = new Date();
		Rizhi rizhi = new Rizhi();						
		rizhi.setRizhiName(userName);
		rizhi.setDate(date);
		rizhi.setDengluIp(ip);
		rizhiService.save(rizhi);
		if (StringUtil.isEmpty(userName) || StringUtil.isEmpty(password)) {
			return Response.error(201,"用户名或密码错误");
		} else {
			Jcpeizhi jcpeizhi = jiazaiPeizhi();
			if(jcpeizhi == null){
				return Response.error(202,"系统还未配置参数，联系管理员");
			}else{
				// 获取Session
				if (loginType.equals("admin")) {
					Admin admin = new Admin();
					admin.setAdminName(userName);
					admin.setAdminPassword(password);
					try {
						if (adminService.queryAdmins(admin, userName, 0, 0, null, null, null, null).size()==1) {
							admin = (Admin) adminService.queryAdmins(admin, userName, 0, 0, null, null, null, null).get(0);
							if(admin.getAdminType()==null){
								List<Jcbiaoti> jcbiaotis = jiazaiBiaoti(1);
								if(jcbiaotis.size()==0){
									return Response.error(203,"系统还未配置导航，联系管理员");
								}else{
									List<List<Jcdaohang>> jcdaohangslist = jiazaiDaohang(jcbiaotis);
									Response newResponse = Response.success(admin,"admin",jcpeizhi,jcbiaotis,jcdaohangslist);
									return newResponse;
									//return Response.success(admin,"admin",jcpeizhi,jcbiaotis,jcdaohangslist);
								}
							}else{
								return Response.error(201,"用户名或密码错误");
							}
						}else{
							return Response.error(201,"用户名或密码错误");
						}
					} catch (Exception e) {
						//e.printStackTrace();
						return Response.error(204,"服务器错误");
					}
				} else if (loginType.equals("admin1")){
					Admin admin = new Admin();
					admin.setAdminName(userName);
					admin.setAdminPassword(password);
					try {
						if (adminService.queryAdmins(admin, userName, 0, 0, null, null, null, null).size()==1) {
							admin = (Admin) adminService.queryAdmins(admin, userName, 0, 0, null, null, null, null).get(0);
							if(admin.getAdminType()==1){
								List<Jcbiaoti> jcbiaotis = jiazaiBiaoti(1);
								if(jcbiaotis.size()==0){
									return Response.error(203,"系统还未配置导航，联系管理员");
								}else{
									List<List<Jcdaohang>> jcdaohangslist = jiazaiDaohang(jcbiaotis);
									Response newResponse = Response.success(admin,"admin1",jcpeizhi,jcbiaotis,jcdaohangslist);
									return newResponse;
								}
							}else{
								return Response.error(201,"用户名或密码错误");
							}
						}else{
							return Response.error(201,"用户名或密码错误");
						}
					} catch (Exception e) {
						//e.printStackTrace();
						return Response.error(204,"服务器错误");
					}
				} else if (loginType.equals("yonghu")){
					Yonghu yonghu = new Yonghu();
					yonghu.setYonghuName(userName);
					yonghu.setYonghuPassword(password);
					try {
						if (yonghuService.queryYonghus(yonghu, userName, 0, 0, null, null, null, null).size() == 1) {
							Yonghu yonghuLogin = (Yonghu)(yonghuService.queryYonghus(yonghu, userName, 0, 0, null, null, null, null)).get(0);
							List<Jcbiaoti> jcbiaotis = jiazaiBiaoti(3);
							if(jcbiaotis.size()==0){
								return Response.error(203,"系统还未配置导航，联系管理员");
							}else{
								List<List<Jcdaohang>> jcdaohangslist = jiazaiDaohang(jcbiaotis);
								return Response.success(yonghuLogin,"yonghu",jcpeizhi,jcbiaotis,jcdaohangslist);
							}
						}else{
							return Response.error(201,"用户名或密码错误");
						}
					} catch (Exception e) {
						//e.printStackTrace();
						return Response.error(204,"服务器错误");
					}
				}else if (loginType.equals("user")){
					User user = new User();
					user.setUserName(userName);
					user.setUserPassword(password);
					try {
						if (userService.queryUsers(user, userName, 0, 0, null, null, null, null).size() == 1) {
							User userLogin = (User)(userService.queryUsers(user, userName, 0, 0, null, null, null, null)).get(0);
							List<Jcbiaoti> jcbiaotis = jiazaiBiaoti(2);
							if(jcbiaotis.size()==0){
								return Response.error(203,"系统还未配置导航，联系管理员");
							}else{
								List<List<Jcdaohang>> jcdaohangslist = jiazaiDaohang(jcbiaotis);
								return Response.success(userLogin,"user",jcpeizhi,jcbiaotis,jcdaohangslist);
							}
						}else{
							return Response.error(201,"用户名或密码错误");
						}
					} catch (Exception e) {
						//e.printStackTrace();
						return Response.error(204,"服务器错误");
					}
				}else{
					return Response.error(205,"登录权限错误");
				}
			}
		}
	}
}
