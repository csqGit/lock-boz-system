package com.app.bzpower.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.bzpower.dao.RequestlogMapper;
import com.app.bzpower.entity.Admin;
import com.app.bzpower.entity.PageData;
import com.app.bzpower.entity.Requestlog;
import com.app.bzpower.service.RequestlogService;
import com.app.bzpower.util.DefaultUtils;

@Service
public class RequestLogServiceImpl implements RequestlogService{

	@Autowired
	private RequestlogMapper requestlogMapper;
	
	
	private int maxResult = DefaultUtils.maxResult;
	
	/**
	 * 管理员查询申请信息app
	 */
	public List<Requestlog> adminSelectlogList(String requesttime,String param, String phone) {
//		Criteria create = example.createCriteria();
//		Admin.createCriteria().andTelephoneEqualTo(phone);
//		List<Admin> adminList = adminMapper.selectByExample(Admin);
//		if(adminList.size() > 0) {
//			voltage = adminList.get(0).getVoltage();
//			if("audited".equals(param)) {//已经审核的日志
////				create.andOpentimeIsNotNull();
//			}else {//待审核日志
////				create.andOpentimeIsNull();
//			}
////			create.andRequesttimeLike(DateFormatUtils.dateFormat(requesttime) + "%");
////			create.andVoltageEqualTo(voltage);
////			example.setOrderByClause("requesttime desc");//desc  降序排序
////			List<Requestlog> requestlogList = requestlogMapper.selectByExample(example);
//			return null;
//		}else {
//			return null;
//		}
		return null;
	}
	
	/**
	 * 用户查询审核信息app
	 */
	public List<Requestlog> selectRequestLogList(String requesttime,String phone, String examinetype) {
//		Criteria create = example.createCriteria();
//		create.andPhoneEqualTo(phone);
		if("selectWaitExamine".equals(examinetype)) {//查询当天未审核的请求信息
//			create.andStatusEqualTo(0);
//			create.andRequesttimeLike( DateFormatUtils.dateFormat(requesttime)  + "%" );
		}else if("selectAlreadyExamine".equals(examinetype)) {//查询当天已经审核  模糊查询
//			create.andStatusNotEqualTo(0);
//			create.andOpentimeIsNotNull();
//			create.andStatusBetween(1, 3);
//			create.andRequesttimeLike( DateFormatUtils.dateFormat(requesttime)  + "%" );			
		} else if("selectThisExamine".equals(examinetype)) {//当天的全部待审核信息和已审核信息 模糊查询
//			create.andRequesttimeLike( DateFormatUtils.dateFormat(requesttime)  + "%" );
		}else if("selectRequesttime".equals(examinetype)) {//刚提交的待审核信息
//			create.andStatusEqualTo(0);
//			create.andRequesttimeEqualTo(requesttime);//准确查询
		}else if("selectExamineAll".equals(examinetype)) {//查询所有时间的请求信息和审核信息
			
		}
//		example.setOrderByClause("requesttime desc");//desc  降序排序
		List<Requestlog> requestlogList = null;
//		requestlogList = requestlogMapper.selectByExample(example);
//		System.out.println(requestlogList);
		return requestlogList;
	}
	

	/**
	 * 保存用户请求信息app
	 */
	public void insertRequestlog(Requestlog record) {
		int result = 0;
		if(result > 0) {
			System.err.println("请求成功");
		} 
		else {
			System.err.println("请求失败");
		}
	}

	/**
	 * 删除用户请求日志信息
	 */
	public void deleteRequestlog(int id) {
//		requestlogMapper.deleteByPrimaryKey(id);
	}

	/**
	 * 更新用户请求日志信息app
	 */
	public void updateRequestlog(Requestlog requestlog) {
//		example.createCriteria().andTelephoneEqualTo(requestlog.getExamineperson());
////		List<Admin> adminList = adminMapper.selectByExample(example);
//		if(adminList.size() > 0) {
//			String realname = adminList.get(0).getRealname();
//			requestlog.setExamineperson(realname);
//		}
//		if(requestlog.getStatus() == 1) {//表示审核结果为同意
//			Requestlog log = requestlogMapper.selectByPrimaryKey(requestlog.getId());
//			String dtm = log.getDtm();
////			requestlog.setOpennum(this.computerDtm(dtm));
//		}else {//不同意
//			
//		}
//		example.setOrderByClause("requesttime desc");//desc  降序排序
//		requestlogMapper.updateByPrimaryKeySelective(requestlog);
	}

	/**
	 * 根据id查询请求日志信息app
	 */
	public Requestlog selectRequestLogById(Integer id) {
		
//		return requestlogMapper.selectByPrimaryKey(id);
		return null;
	}
	
	

	/**
	 * 查询请求日志pc admin查询
	 */
	public List<Requestlog> selectRequestLogList(PageData pageData,
			String companyname, 
			String voltage) {
		List<Requestlog> list = null;
		try {
			list = requestlogMapper.selectlogList(pageData, companyname, voltage);
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * pc admin查询总页数
	 */
	public int selectRequestLogPages(String voltage) {
//		example.createCriteria().andVoltageEqualTo(voltage);
//		int pages = (int) requestlogMapper.countByExample(example);
		int pages = 0;
		if(maxResult == 0)
			maxResult = 10;
		int result = pages / maxResult;
		if(pages % maxResult != 0)
			result ++;
		return result;
	}

	/**
	 * pc user根据用户名查询总页数
	 */
	public int selectRequestLogByUserNamePages(String phone) {
//		Requestlog example = new Requestlog();
//		example.createCriteria().andPhoneEqualTo(phone);
//		List<Requestlog> list = requestlogMapper.selectByExample(example);
		int count =12;
		int pages = count / maxResult;
		if(count % maxResult != 0)
			pages ++;
		return pages;
	}

	
	/**
	 * 管理员查询所管理的请求日志信息， 根据电压等级来区分
	 */
	public long selectCountByExample(Admin admin) {
		
		return requestlogMapper.countByExample(admin);
	}

	
	/**
	 * 用户查询自己的请求日志信息
	 */
	public int selectCountByUser(String phone) {
//		Requestlog example = new Requestlog();
//		example.createCriteria().andPhoneEqualTo(phone);
//		return (int) requestlogMapper.countByExample(example);
		return 0;
	}

	public List<Requestlog> selectLogHositoryList(String phone, String voltage) {
//		Requestlog example = new Requestlog();
//		com.app.bzpower.entity.Requestlog.Criteria criteria = example.createCriteria();
//		criteria.andPhoneEqualTo(phone);
//		criteria.andVoltageEqualTo(voltage);
//		example.setOrderByClause("desc");
//		List<Requestlog> logList = requestlogMapper.selectByExample(example);
		
		return null; 
	}
	
	
	public List<Requestlog> selectLogHositoryList(String voltage) {
//		Requestlog example = new Requestlog();
//		com.app.bzpower.entity.Requestlog.Criteria criteria = example.createCriteria();
//		criteria.andVoltageEqualTo(voltage);
//		example.setOrderByClause("requesttime desc");
//		List<Requestlog> logList = requestlogMapper.selectByExample(example);
		return null;
		
	}
	

	
}
