package com.app.bzpower.service;

import java.util.List;

import com.app.bzpower.entity.Lockinfo;
import com.app.bzpower.entity.PageData;

public interface LockService {
	

	/**
	 * 查询所有锁信息
	 * @param i 
	 * @return
	 */
	List<Lockinfo> selectLockList(PageData pageData, String voltage, String companyName);
	
	
	long countByExample(String companyname, String  voltage);
	/**
	 * 根据锁名称查询锁信息
	 * @param lockName
	 * @return
	 */
	Lockinfo selectLockByName(String lockName);
	
	/**
	 * 根据ID查询锁信息
	 * @param id
	 * @return
	 */
	Lockinfo selectLockById(Integer id);
	
	/**
	 * 根据所did查询
	 * @param did
	 * @return
	 */
	Lockinfo selectLockByDid(String did);
	
	/**
	 * 根据所mac查询
	 * @param mac
	 * @return
	 */
	Lockinfo selectLockByMac(String mac);
	
	/**
	 * 得到锁总数
	 * @return
	 */
	int selectPages(String voltage);
	
	
	/**
	 * 查询锁的总数
	 * @return
	 */
	int selectCount(String voltage);
	
	
	/**
	 * 新增锁信息
	 * @param lock
	 */
	int insertLock(Lockinfo Lockinfo);
	
	/**
	 * 删除锁根据锁名称
	 */
	int deleteLockByName(String lockName);
	
	/**
	 * 根据ID删除锁信息
	 * @param id
	 */
	int deleteLockById(Integer id);
	
	/**
	 * 根据ID修改锁信息
	 * @param Lockinfo
	 */
	int updateLock(Lockinfo Lockinfo);
	
}
