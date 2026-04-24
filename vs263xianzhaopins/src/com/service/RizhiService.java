package com.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.*;
import com.model.*;

import javax.servlet.http.HttpServletRequest;

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
		rizhi.setRizhiName(userName);
		rizhi.setDengluIp(request.getRemoteAddr()); // 获取客户端IP
		rizhi.setDate(new Date());
		rizhi.setRizhiType(type);
		rizhi.setRizhiRemark(remark);
		rizhi.setRizhiResult(result);
		rizhiDao.add(rizhi);
	}
}
