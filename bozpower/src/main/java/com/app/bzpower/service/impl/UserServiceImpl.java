package com.app.bzpower.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.bzpower.dao.CompanyMapper;
import com.app.bzpower.dao.UserMapper;
import com.app.bzpower.entity.Admin;
import com.app.bzpower.entity.PageData;
import com.app.bzpower.entity.User;
import com.app.bzpower.service.UserService;


@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private CompanyMapper companyMapper;

	public User selectUser(User user) {
		// TODO Auto-generated method stub
		return null ;
	}

	public User selectUserByPhone(String phone) {
		// TODO Auto-generated method stub
		return null;
	}

	public int insertUser(User record) {
		try {
			return userMapper.insertSelective(record);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		
	}

	public List<User> selectUserList(PageData pageData, Admin admin) {
		
		return userMapper.selectUserList(pageData, admin);
	}

	public List<User> selectUserList(String voltage) {
		// TODO Auto-generated method stub
		return null;
	}

	public User selectUserById(int id) {
		// TODO Auto-generated method stub
		return userMapper.selectByPrimaryKey(id);
	}

	public User selectUserByUsername(String username) {
		// TODO Auto-generated method stub
		return null;
	}


	public long  countByExample(String companyname, String voltage) {
		try {
			User example = new User();
			example.setVoltage(voltage);
			example.setCompanyId(companyMapper.selectCompanyByPrimaryCompanyname(companyname));
			
			return userMapper.countByExample(example);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	public int deleteUser(int id) {
		try {
			
			return userMapper.deleteByPrimaryKey(id);
		} catch (Exception e) {
			return 0;
		}
		
	}

	public int updateUser(User user) {
		try {
			
			return userMapper.updateByPrimaryKeySelective(user);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		
	}

}
