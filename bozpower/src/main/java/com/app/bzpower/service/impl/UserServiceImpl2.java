package com.app.bzpower.service.impl;

import java.util.List;


import com.app.bzpower.entity.Admin;
import com.app.bzpower.entity.PageData;
import com.app.bzpower.entity.User;
import com.app.bzpower.util.DefaultUtils;
import com.mysql.jdbc.StringUtils;

/**
 * 鐢ㄦ埛Service
 * 
 * @author Admin
 *
 */
//@Service
public class UserServiceImpl2 {

//	@Autowired
//	private UserDao userDao;

//	@Autowired
//	private UserMapper userMapper;

	private int maxResult = DefaultUtils.maxResult;

	/**
	 * 
	 */
	public User selectUser(User user) {
//		User example = new User();
		if (!StringUtils.isNullOrEmpty(user.getUsername()) 
				&& !StringUtils.isNullOrEmpty(user.getPassword()) ) {
//			Criteria criteria = example.createCriteria();
//			criteria.andUsernameEqualTo(user.getUsername());
//			criteria.andPasswordEqualTo(user.getPassword());
//			criteria.andRealnameEqualTo(user.getRealname());
			return null;
		}else 
			return null;
	}

	public User selectUserByUsername(String username) {
		
//		User example = new User();
//		example.createCriteria().andUsernameEqualTo(username);
//		List<User> userList = userMapper.selectByExample(example);
//		if(userList.size() > 0) {
//			return userList.get(0);
//		}else
		return null;
	}

	public void insertUser(User user) {
		try {
//			userMapper.insert(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
//
	public List<User> selectUserList(PageData pageData, String voltage) {
		
		List<User> userList = null;
		try {
//			User example = new User();
//			example.createCriteria().andVoltageEqualTo(voltage);
//			userList = userMapper.selectByExample(example);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userList;
	}
//
	public User selectUserById(int id) {
//		User user = userMapper.selectByPrimaryKey(id);
		return null;
	}

//
	public void deleteUser(int id) {
		try {
//			userMapper.deleteByPrimaryKey(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
//
	public void updateUser(User user) {
		try {
//			userMapper.updateByPrimaryKey(user);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}

	
	public int selectPages(String voltage) {
		int pages = this.selectCount(voltage);
		if(maxResult == 0) {//防止java.lang.ArithmeticException: / by zero
			maxResult = 10;
		}
		if (pages % maxResult != 0) {
			pages = pages / maxResult + 1;
		} else {
			pages = pages / maxResult;
		}
		return pages;
	}


	public User selectUserByName(String username) {
//		User example = new User();
//		example.createCriteria().andUsernameEqualTo(username);
//		List<User> userList = userMapper.selectByExample(example);
//		if(userList.size() > 0)
//			return userList.get(0);
//		else
			return null;
	}

	public List<User> selectUserList(String voltage) {
//		User example = new User();
//		example.createCriteria().andVoltageEqualTo(voltage);
//		List<User> userList = userMapper.selectByExample(example);
		
		return null;
	}

	public int selectCount(String voltage) {
//		User example = new User();
//		example.createCriteria().andVoltageEqualTo(voltage);
//		return (int) userMapper.countByExample(example);
		return 4;
	}

	public User selectUserByPhone(String phone) {
//		User example = new User();
//		example.createCriteria().andPhoneEqualTo(phone);
//		List<User> userList = userMapper.selectByExample(example);
//		if(userList.size() > 0) {
//			return userList.get(0);
//		}else 
			return null;
	}

	public List<User> selectUserList(PageData pageData, Admin admin) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
