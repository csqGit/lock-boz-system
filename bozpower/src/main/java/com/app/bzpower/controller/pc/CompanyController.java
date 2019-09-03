package com.app.bzpower.controller.pc;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.app.bzpower.entity.Admin;
import com.app.bzpower.entity.Company;
import com.app.bzpower.entity.PageData;
import com.app.bzpower.service.CompanyService;

@Controller("pcCompanyController")
@RequestMapping("/pcCompany")
public class CompanyController {
	
	@Autowired
	private CompanyService companyService;
	
	@RequestMapping("/selectCompanyList")
	public ModelAndView selectCompanyList(HttpServletRequest request, PageData pageData) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/company/company_query_success.jsp");
		Admin admin = (Admin) request.getSession().getAttribute("admin");
		if(admin != null) {
			String voltage = admin.getVoltage();
			try {
				mav.addObject("companyList", companyService.selectCompany(pageData, admin));
				
				pageData.setPages(companyService.selectPages(voltage));
				pageData.setCount(companyService.selectCount(voltage));
				mav.addObject("pageData", pageData);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		return mav;
		
	}
	
	
	@RequestMapping("/selectCompanyById")
	public ModelAndView selectCompanyById(HttpServletRequest request,String param, int id) {
		ModelAndView mav = new ModelAndView();
		if(!"".equals(param) || !(param == null)) {//表示修改查询
			mav.addObject("company", companyService.selectCompanyById(id));
			mav.setViewName("/company/company_edit.jsp");
		}else {//表示查询
			Admin admin = (Admin) request.getAttribute("admin");
			if(admin != null) {
				
//				mav.addObject("companyList", companyService.selectCompany(pageData, admin.getVoltage()));
				mav.setViewName("/company/company_query_success.jsp");
			}
			
		}
		return mav;
	}
	
	
	
	/**
	 * 新增单位名称
	 * @param company
	 * @return
	 */
	@RequestMapping("/insertCompany")
	public ModelAndView insertCompany(Company company) {
		ModelAndView mav = new ModelAndView();
		try {
			companyService.insertCompany(company);
			mav.addObject("result", "添加成功！");
		} catch (Exception e) {
			e.printStackTrace();
			mav.addObject("result", "添加失败！");
		}
		mav.setViewName("/company/company_addOrEdit_result.jsp");
		return mav;
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/deleteCompany")
	public ModelAndView deleteCompany(int id) {
		ModelAndView mav = new ModelAndView();
		try {
			companyService.deleteCompanyById(id);
			mav.addObject("result", "删除成功！");
		} catch (Exception e) {
			e.printStackTrace();
			mav.addObject("result", "删除失败！");
		}
		mav.setViewName("/company/company_addOrEdit_result.jsp");
		return mav;
	}
	
	/**
	 * 
	 * @param company
	 * @return
	 */
	@RequestMapping("/updateCompany")
	public ModelAndView updateCompany(Company company) {
		ModelAndView mav = new ModelAndView();
		try {
			companyService.updateCompany(company);
			mav.addObject("result", "修改成功！");
		} catch (Exception e) {
			e.printStackTrace();
			mav.addObject("result", "修改失败！");
		}
		mav.setViewName("/company/company_addOrEdit_result.jsp");
		return mav;
	}

}
