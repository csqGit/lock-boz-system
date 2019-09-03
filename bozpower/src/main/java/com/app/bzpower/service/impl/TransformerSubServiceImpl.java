package com.app.bzpower.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.bzpower.dao.CompanyMapper;
import com.app.bzpower.dao.TransformersubMapper;
import com.app.bzpower.entity.Company;
import com.app.bzpower.entity.PageData;
import com.app.bzpower.entity.TransformerSub;
import com.app.bzpower.service.TransformerSubService;

@Service
public class TransformerSubServiceImpl implements TransformerSubService{

	@Autowired
	private TransformersubMapper transformerSubMapper;
	
	@Autowired
	private CompanyMapper companyMapper;

	public List<TransformerSub> selectTransformerSubList(PageData pageData,String voltage, String companyname) {
		List<TransformerSub> list = null;
		list = transformerSubMapper.selectTransformerSubList(pageData, voltage, companyname);
		
		return list;
	}

	
	public long selectTransformerSubCount(String voltage, String companyname) {
		
		try {
			Company company = companyMapper.selectCompanyByPrimaryCompanyname(companyname);
			TransformerSub example = new TransformerSub();
			example.setVoltage(voltage);
			example.setCompanyId(company);
			
			return transformerSubMapper.countByExample(example);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	
	public TransformerSub selectTransfromerSubById(Integer id) {
		try {
			return transformerSubMapper.selectByPrimaryKey(id);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}

	public TransformerSub selectTransformerSubList(String voltage, String transformerSubName, String companyname) {
//		TransformerSub example = new TransformerSub();
//		com.app.bzpower.entity.TransformerSub.Criteria creataria = example.createCriteria();
//		creataria.andCompanynameEqualTo(companyname);
//		creataria.andVoltageEqualTo(voltage);
//		creataria.andTransformersubnameEqualTo(transformerSubName);
//		List list = transformerSubMapper.selectByExample(example);
//		if(list.size() > 0)
//			return (TransformerSub) list.get(0);
//		else
		return null;
	}

	public int insertTransformerSub(TransformerSub record) {
		try {
			return transformerSubMapper.insertSelective(record);
			
		} catch (Exception e) {
			return 0;
		}
	}

	public int updateTransformerSub(TransformerSub transformerSub) {
		try {
			return transformerSubMapper.updateByPrimaryKeySelective(transformerSub);
			
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		
	}

	public int deleteTransformerSubById(Integer id) {
		try {
			return transformerSubMapper.deleteByPrimaryKey(id);
			
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		
	}

	
	

}
