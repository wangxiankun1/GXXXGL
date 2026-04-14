package com.dao;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Repository;

import com.mapper.*;
import com.model.*;
@Repository
public class AdminDao{
	@Autowired
	private AdminMapper adminMapper;

	public List getAdminList(Admin record,String adminname,int page,int rows,String sdate, String edate,String sdate1, String edate1) {
		List<Admin> list = adminMapper.selectAll(record,adminname,page,rows,sdate,edate,sdate1,edate1);
		return list;
	}
	
	public Admin getAdminById(int id){
		Admin admin = adminMapper.selectByPrimaryKey(id);
		
		return admin;
	}

	public void update(Admin admin) {
		adminMapper.updateByPrimaryKey(admin);

	}

	public void delete(Integer id) {
		adminMapper.deleteByPrimaryKey(id);
	}

	public void add(Admin admin) {
		adminMapper.insert(admin);
		
	}
}
