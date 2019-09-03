package com.app.bzpower.controller.pc;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.app.bzpower.entity.Admin;
import com.app.bzpower.entity.Lockinfo;
import com.app.bzpower.entity.PageData;
import com.app.bzpower.entity.TransformerSub;
//import com.app.bzpower.service.CompanyService;
import com.app.bzpower.service.LockService;
import com.app.bzpower.service.TransformerSubService;
import com.app.bzpower.util.PagesUtils;


/**
 * 锁
 */
@Controller(value = "/pcLockController")
@RequestMapping(value = "/pcLock")
public class LockController {

	@Autowired
	private LockService lockService;
//	@Autowired
//	private CompanyService companyService;
	@Autowired
	private TransformerSubService transformerSubService;

	
	
	@RequestMapping("/selectLockList")
	public ModelAndView selectLockList(HttpServletRequest request, PageData pageData) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/jsp/lock_query_success.jsp");
		Admin admin = (Admin) request.getSession().getAttribute("admin");
		pageData = PagesUtils.getPageData(pageData);
		if(admin != null) {
			String voltage = admin.getVoltage();
			try {
				
				mav.addObject("lockList", lockService.selectLockList(pageData, voltage, admin.getCompanyId().getCompanyname()));
//				List<Company> company = companyService.selectCompany(pageData, admin);
//				request.getSession().setAttribute("companyList", company);
				List<TransformerSub> trans = transformerSubService.selectTransformerSubList(pageData,voltage, "");
				request.getSession().setAttribute("transformerSubList", trans);
				
				long count = lockService.countByExample(admin.getCompanyId().getCompanyname(), voltage);
				pageData.setPages(PagesUtils.getPages(pageData.getLimit(), count));
				pageData.setCount(count);
				mav.addObject("pageData", pageData);
				
				return mav;
			} catch (Exception e) {
				e.printStackTrace();
				mav.addObject("pageData", pageData);
				return mav;
			}
		}else {
			pageData.setPages(0);
			mav.addObject("pageData", pageData);
			return mav;
		}
		
	}
	
	@RequestMapping("/selectLockById")
	public String selectLockById(HttpServletRequest request, Integer id) {
		try {
			Admin admin = (Admin) request.getSession().getAttribute("admin");
			Lockinfo lock = lockService.selectLockById(id);
			request.setAttribute("lock", lock);
			request.setAttribute("transformerSubList", transformerSubService
					.selectTransformerSubList(new PageData(),admin.getVoltage(), admin.getCompanyId().getCompanyname()));
			return "/jsp/lock_edit.jsp";
		} catch (Exception e) {
			e.printStackTrace();
			return "/jsp/result.jsp";
		}
		
	}


	
	/**
	 * 根据锁名称查询锁信息
	 * 
	 * @param lockName
	 * @return
	 */
	@RequestMapping("/selectLockByDid")
	public ModelAndView selectLockByDid(String did) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/jsp/lock_edit");
		Lockinfo lock = null;
		try {
			if (did != null || !"".equals(did)) {
			 lock = lockService.selectLockByDid(did);
			 mav.addObject("lock", lock);
			}
		} catch (Exception e) {
			e.printStackTrace();
			mav.addObject("lock", lock);
		}
		return mav;
	}
	
	/**
	 * 根据锁名称查询锁信息
	 * 
	 * @param lockName
	 * @return
	 */
	@RequestMapping("/selectLockByName")
	public ModelAndView selectLockByName(HttpServletRequest request, String lockName) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/jsp/lock_query_success.jsp");
		Lockinfo lock = null;
		Admin admin = (Admin) request.getSession().getAttribute("admin");
		if(admin != null) {
			try {
				if (lockName != null && !"".equals(lockName)) {
				 lock = lockService.selectLockByName(lockName);
				 List<Lockinfo> lockList = new ArrayList<Lockinfo>();
				 lockList.add(lock);
				 mav.addObject("lockList", lockList);
				}else {
					PageData pageData = new PageData();
					mav.addObject("lockList", lockService.selectLockList(pageData, admin.getVoltage(), ""));
				}
			} catch (Exception e) {
				e.printStackTrace();
				mav.addObject("lock", lock);
				return mav;
			}
		}else {
			return mav;
		}
		return mav;
	}
	
	@RequestMapping("/updateLock")
	public String updateLock(HttpServletRequest request, Lockinfo Lockinfo) {
		try {
			int result = lockService.updateLock(Lockinfo);
			if(result == 1) {
				request.setAttribute("result", "更新成功！");
				return "redirect:selectLockList";
			}
			else {
				request.setAttribute("result", "更新失败！");
				return "/jsp/result.jsp";
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("result", "更新失败！");
			return "/jsp/result.jsp";
		}
	}
	
	/**
	 * 新增锁信息
	 * @param Lockinfo
	 * @return
	 */
	@RequestMapping("/insertLock")
	public String insertLock(HttpServletRequest request, Lockinfo Lockinfo) {
		try {
			int result = lockService.insertLock(Lockinfo);
			if(result == 1) {
				request.setAttribute("result", "新增成功！");
				return "redirect:selectLockList";
			}
			else {
				request.setAttribute("result", "新增失败！");
				return "/jsp/result.jsp";
			}
		} catch (Exception e) {
			request.setAttribute("result", "新增失败！");
			return "/jsp/result.jsp";
		}
	}
	
	@RequestMapping("/deleteLockById")
	public String deleteLockById(HttpServletRequest request, Integer id) {
		try {
			int result = lockService.deleteLockById(id);
			if(result == 1) {
				request.setAttribute("result", "删除成功！");
				return "redirect:selectLockList?page=1";
			}else {
				request.setAttribute("result", "删除失败！");
				return "/jsp/result.jsp";
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("result", "删除失败！");
			return "/jsp/result.jsp";
		}
	}
	
	
	/**
	 * 分配请求页面
	 * @param request
	 * @param model
	 * @param param
	 * @param id
	 * @return
	 */
	@RequestMapping("/requestType")
	public String requestType(HttpServletRequest request, Model model, String param,Integer id) {
		String url = "";
		if("logOut".equals(param)) {
			HttpSession session = request.getSession();
//			session.removeAttribute("admin");
			session.invalidate();
			url = "/login.jsp";
			return url;
		}
		Admin admin = (Admin) request.getSession().getAttribute("admin");
		if(admin != null) {
			model.addAttribute("transformerSubList", 
					transformerSubService.selectTransformerSubList(new PageData(), admin.getVoltage(), 
							admin.getCompanyId().getCompanyname()));
			if("add".equals(param)) {
				
				url = "/jsp/lock_add.jsp";
				return url;
			}
			if("update".equals(param)) {
				model.addAttribute("lock", lockService.selectLockById(id));
				url = "/user/user_edit.jsp";
				return url;
			}
		}
		
		return url;
	}
	

	
}
