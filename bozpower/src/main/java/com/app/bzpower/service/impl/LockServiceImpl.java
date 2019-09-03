package com.app.bzpower.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.bzpower.dao.CompanyMapper;
import com.app.bzpower.dao.LockinfoMapper;
import com.app.bzpower.entity.Company;
import com.app.bzpower.entity.Lockinfo;
import com.app.bzpower.entity.PageData;
import com.app.bzpower.service.LockService;

@Service
public class LockServiceImpl implements LockService {

	@Autowired
	private LockinfoMapper lockinfoMapper;
	
	@Autowired
	private CompanyMapper companyMapper;
	
	public List<Lockinfo> selectLockList(PageData pageData, String voltage ,String companyName) {
		List<Lockinfo> lockList = null;
		try {
			lockList = lockinfoMapper.selectLockList(pageData, voltage, 
					companyMapper.selectCompanyByPrimaryCompanyname(companyName).getId());
			return lockList;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
		
	}

	

	public long countByExample(String companyname, String  voltage) {
		try {
			Lockinfo example = new Lockinfo();
			Company company = companyMapper.selectCompanyByPrimaryCompanyname(companyname);
			example.setVoltage(voltage);
			example.setCompanyId(company);
			return lockinfoMapper.countByExample(example);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		
	}



	public Lockinfo selectLockByName(String lockName) {
		// TODO Auto-generated method stub
		return null;
	}



	public Lockinfo selectLockById(Integer id) {
		try {
			return lockinfoMapper.selectByPrimaryKey(id);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}



	public Lockinfo selectLockByDid(String did) {
		// TODO Auto-generated method stub
		return null;
	}



	public Lockinfo selectLockByMac(String mac) {
		// TODO Auto-generated method stub
		return null;
	}



	public int selectPages(String voltage) {
		// TODO Auto-generated method stub
		return 0;
	}



	public int selectCount(String voltage) {
		// TODO Auto-generated method stub
		return 0;
	}



	public int insertLock(Lockinfo record) {
		try {
			return lockinfoMapper.insertSelective(record);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		
	}



	public int deleteLockByName(String lockName) {
		// TODO Auto-generated method stub
		return 0;
	}



	public int deleteLockById(Integer id) {
		try {
			return lockinfoMapper.deleteByPrimaryKey(id);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		
	}



	public int updateLock(Lockinfo Lockinfo) {
		try {
			return lockinfoMapper.updateByPrimaryKeySelective(Lockinfo);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		
	}

	



}
