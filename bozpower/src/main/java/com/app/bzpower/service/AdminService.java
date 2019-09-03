package com.app.bzpower.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.app.bzpower.entity.PageData;
import com.app.bzpower.entity.Admin;

public interface AdminService {
	
	/**
	 * 查询管理员，该方法用于管理员的注册权限设置
	 * @param admin
	 * @return
	 */
	Admin selectAdmin(Admin admin);
	
	
	/**
	 * 管理员新增
	 */
	int insertAdmin(Admin admin);
	
	/**
	 * 分页查询
	 * @param page
	 * @param maxResult
	 * @return
	 */
	List<Admin> selectAdminList(PageData pageData, Admin admin);

	/**
	 * 根据ID查询
	 * @param id
	 * @return
	 */
	Admin selectAdminById(int id);
	
	/**
	 * 根据管理员名称查询
	 * @param AdminName
	 * @return
	 */
	List<Admin> selectAdminByName(String adminName);
	
	
	Admin selectAdminByUsername(@Param("username")String username);
	
	/**
	 * 根据电话号码查询管理员
	 * @param phone
	 * @return
	 */
	Admin selectAdminByPhone(String phone);
	
	
	/**
	 * 根据电压等级查询管理员
	 * @param voltage
	 * @return
	 */
	List<Admin> selectAdminByVoltage(String voltage);
	
	/**
	 * 得到总页数
	 * @return
	 */
	int selectPages();
	
	/**
	 * 查询总数
	 * @return
	 */
	int selectCount(Admin admin);
	
	/**
	 * 根据ID删除
	 * @param id
	 */
	int deleteAdmin(int id);
	
	/**
	 * 根据ID更新用户信息
	 * @param Admin
	 */
	int updateAdmin(Admin admin);
	
	
	/**
	 * 更新密码
	 * @param admin
	 * @return
	 */
	int updatePassword(Admin admin);
	
	/**
	 * 管理员登录
	 */
	Admin adminLogin(Admin admin);

}
