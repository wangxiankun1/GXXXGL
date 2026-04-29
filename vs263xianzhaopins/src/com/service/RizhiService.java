package com.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.*;
import com.model.*;
import com.util.StringUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Service
public class RizhiService {
	@Autowired
	private RizhiDao rizhiDao;

	public List queryRizhis(Rizhi record,int page,int rows) {
		// TODO Auto-generated method stub
		return rizhiDao.getRizhiList(record,page,rows);
	}

	public Rizhi getRizhi(int parseInt) {
		// TODO Auto-generated method stub
		return rizhiDao.getRizhiById(parseInt);
	}

	public void modifyRizhi(Rizhi rizhi) {
		// TODO Auto-generated method stub
		rizhiDao.update(rizhi);
	}

	public void deleteRizhi(Integer id) {
		// TODO Auto-generated method stub
		rizhiDao.delete(id);

	}

	public void save(Rizhi rizhi) {
		// TODO Auto-generated method stub
		rizhiDao.add(rizhi);

	}

	public void addLog(HttpServletRequest request, String userName, String type, String remark, String result) {
		Rizhi rizhi = new Rizhi();

		// 修复点：优先使用传入的有效用户名
		if (userName != null && !userName.trim().isEmpty()) {
			rizhi.setRizhiName(userName);
		} else {
			// 如果没传，再尝试从Session拿（兼容性处理）
			Admin admin = (Admin) request.getSession().getAttribute("admin");
			if (admin != null) {
				rizhi.setRizhiName(admin.getAdminName());
			} else {
				rizhi.setRizhiName("系统管理员"); // 最后的兜底
			}
		}

		rizhi.setDengluIp(request.getRemoteAddr());
		rizhi.setDate(new Date());
		rizhi.setRizhiType(type);
		rizhi.setRizhiRemark(remark);
		rizhi.setRizhiResult(result);
		rizhiDao.add(rizhi);
	}
}
