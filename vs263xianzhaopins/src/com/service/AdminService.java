package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.*;
import com.model.*;

@Service
public class AdminService {
	@Autowired
	private AdminDao adminDao;

	public List queryAdmins(Admin record,String adminName,int page,int rows, String sdate, String edate, String sdate1, String edate1) {
		// TODO Auto-generated method stub
		return adminDao.getAdminList(record,adminName,page,rows,sdate,edate,sdate1,edate1);
	}

	public Admin getAdmin(int parseInt) {
		// TODO Auto-generated method stub
		return adminDao.getAdminById(parseInt);
	}

	public void modifyAdmin(Admin admin) {
		// TODO Auto-generated method stub
		adminDao.update(admin);
	}

	public void deleteAdmin(Integer id) {
		// TODO Auto-generated method stub
		adminDao.delete(id);

	}

	public void save(Admin admin) {
		// TODO Auto-generated method stub
		adminDao.add(admin);

	}

}
