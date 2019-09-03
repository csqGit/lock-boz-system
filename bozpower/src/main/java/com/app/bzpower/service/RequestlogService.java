package com.app.bzpower.service;

import java.util.List;

import com.app.bzpower.entity.Admin;
import com.app.bzpower.entity.PageData;
import com.app.bzpower.entity.Requestlog;

public interface RequestlogService {
	
	

	/**
	 * app 查询请求日志
	 * @param requesttime
	 * @param opentime
	 * @param status
	 * @return
	 */
	public List<Requestlog> adminSelectlogList(String requesttime, String opentime, String phone);
	
	/**
	 * pc分页查询请求日志
	 * @param pageData
	 * @return
	 */
	public List<Requestlog> selectRequestLogList(PageData pageData,String companyname, String voltage);
	/**
	 * 用户查询当前审核的信息结果，如果requesttime 为空，则表示查询全部
	 * @param requesttime
	 * @param realname
	 * @return
	 */
	public List<Requestlog> selectRequestLogList(String requesttime,String realname, String examinetype);
	
	/**
	 * 根据id查询请求日志
	 * @param id
	 * @return
	 */
	public Requestlog selectRequestLogById(Integer id);
	
	
	/**
	 * 管理员查询所有日志条数
	 * @return
	 */
	public int selectRequestLogPages(String voltage);
	
	
	/**
	 * 用户查询所有日志条数
	 * @return
	 */
	public int selectRequestLogByUserNamePages(String phone);
	
	
	/**
	 * 
	 * @param phone 根据用户的电话号码查询
	 * @param voltage 根据用户的 电压等级查询
	 * @return
	 */
	public List<Requestlog> selectLogHositoryList(String phone, String voltage);
	
	public List<Requestlog> selectLogHositoryList(String phone);
	
	/**
	 * 管理员查询日志总数
	 * @param adminname
	 * @return
	 */
	public long selectCountByExample(Admin admin);
	
	/**
	 * 用户查询日志总数
	 * @return
	 */
	public int selectCountByUser(String phone);
	
	/**
	 * 新增请求日志
	 * @param requestlog
	 */
	public void insertRequestlog(Requestlog requestlog);
	
	/**
	 * 根据id删除请求日志
	 * @param id
	 */
	public void deleteRequestlog(int id);
	
	/**
	 * 更新请求日志
	 * @param requestlog
	 */
	public void updateRequestlog(Requestlog requestlog);
	
}
