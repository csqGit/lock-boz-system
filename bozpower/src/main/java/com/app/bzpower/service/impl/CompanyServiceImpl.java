package com.app.bzpower.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.app.bzpower.entity.Admin;
import com.app.bzpower.entity.Company;
import com.app.bzpower.entity.PageData;
import com.app.bzpower.service.CompanyService;
import com.app.bzpower.util.DefaultUtils;

@Service
public class CompanyServiceImpl implements CompanyService {
	
	private int maxResult = DefaultUtils.maxResult;
	
	public List<Company> selectCompany(PageData pageData, Admin admin) {
		
		return null;
	}

	public Company selectCompanyByName(String companyName) {
		return null; 
	}

	public Company selectCompanyById(Integer id) {
		
		return null;
	}

	public int selectPages(String voltage) {
		int count = this.selectCount(voltage);
		if(maxResult == 0)
			maxResult = 10;
		int pages = count / maxResult;
		if(count % maxResult != 0) {
			pages ++;
		}
		return pages;
	}

	public void insertCompany(Company company) {
		
	}

	public void deleteCompanyByName(String companyName) {
		
	}

	public void deleteCompanyById(int id) {
//		companyMapper.deleteByPrimaryKey(id);
		
	}

	public void updateCompany(Company company) {
//		companyMapper.updateByPrimaryKeySelective(company);
	}

	public int selectCount(String voltage) {
		return 0;
	}


	
	

}
