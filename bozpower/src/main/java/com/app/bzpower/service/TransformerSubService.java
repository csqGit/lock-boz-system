package com.app.bzpower.service;

import java.util.List;

import com.app.bzpower.entity.PageData;
import com.app.bzpower.entity.TransformerSub;

public interface TransformerSubService {

	/**
	 * 查询所有变电站信息
	 * @return
	 */
	public List<TransformerSub> selectTransformerSubList(PageData pageData, String voltage, String companyname);
	
	long selectTransformerSubCount(String voltage, String companyname);
	/**
	 * 根据ID查询变电站信息
	 * @param id
	 * @return
	 */
	public TransformerSub selectTransfromerSubById(Integer id);
	
	
	/**
	 * 查询变电站
	 * @param voltage
	 * @param transformerSubName
	 * @param companyname
	 * @return
	 */
	public TransformerSub selectTransformerSubList(String voltage,String transformerSubName, String companyname);
	
	
	
	
	/**
	 * 新增变电站
	 * @param transformerSub
	 */
	public int insertTransformerSub(TransformerSub transformerSub);
	
	
	/**
	 * 更新变电站信息
	 * @param transformerSub
	 */
	public int updateTransformerSub(TransformerSub transformerSub);
	
	/**
	 * 删除变电站
	 * @param id
	 */
	public int deleteTransformerSubById(Integer id);
}
