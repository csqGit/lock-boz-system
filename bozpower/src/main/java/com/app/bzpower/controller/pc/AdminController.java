package com.app.bzpower.controller.pc;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.app.bzpower.entity.Admin;
import com.app.bzpower.entity.PageData;
import com.app.bzpower.entity.Requestlog;
import com.app.bzpower.entity.TransformerSub;
import com.app.bzpower.service.AdminService;
import com.app.bzpower.service.CompanyService;
import com.app.bzpower.service.RequestlogService;
import com.app.bzpower.service.TransformerSubService;
import com.app.bzpower.util.Md5Utils;
import com.app.bzpower.util.PagesUtils;

/**
 * <p>
 * 该类中的所有方法
 * </p>
 * <p>
 * 1、分页查询所有的管理员列表 public ModelAndView selectAdminList(PageData pageData,
 * HttpServletRequest request)
 * </p>
 * <p>
 * 2、根据id删除管理员 public ModelAndView deleteAdmin(int id)
 * </p>
 * <p>
 * 3、新增管理员 public ModelAndView insertAdmin(Admin admin)
 * </p>
 * <p>
 * 4、更新管理员信息 public ModelAndView updateAdmin(Admin admin)
 * </p>
 * <p>
 * 5、根据请求参数分配不同的业务需求
 * </p>
 * <p>
 * param 为：exit ,则退出系统；add:新增 ； update:更新信息
 * </p>
 * <p>
 * public String requestType(HttpServletRequest request, Model model, String
 * param,Integer id)
 * </p>
 * <p>
 * 6、管理员分页查询所有用户列表 public ModelAndView selectUserList(PageData pageData,
 * HttpServletRequest request)
 * </p>
 * <p>
 * 7、根据电压等级查询该电压所对应的所有变电站信息 public List<TransformerSub>
 * selectTransformerSubList(String voltage)
 * </p>
 * <p>
 * 8、分页查询所有日志信息 public ModelAndView selectLogList(HttpServletRequest request,
 * PageData pageData)
 * </p>
 * <p>
 * 9、删除用户请求信息 public String deleteRequestlog(HttpServletRequest request,Integer
 * id, Integer page)
 * </p>
 *
 */
@Controller("pcAdminController")
@RequestMapping("/pcAdmin")
public class AdminController {

	@Autowired
	private AdminService adminService;

	@Autowired
	private CompanyService companyService;


	@Autowired
	private TransformerSubService transformerSubService;

	@Autowired
	private RequestlogService requestlogService;

	/**
	 * 分页查询所有的管理员列表
	 * 
	 * @param pageData
	 * @param request
	 * @return
	 */
	@RequestMapping("/selectAdminList")
	public ModelAndView selectAdminList(PageData pageData, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		pageData = PagesUtils.getPageData(pageData);
		try {
			Admin admin = (Admin) request.getSession().getAttribute("admin");

			List<Admin> adminList = adminService.selectAdminList(pageData, admin);
			int count = adminService.selectCount(admin);
			pageData.setCount(count);
			pageData.setPages(PagesUtils.getPages(pageData.getLimit(), count));
			mav.addObject("adminList", adminList);
			mav.addObject("pageData", pageData);
			mav.setViewName("/admin/admin_query_success.jsp");
			return mav;
		} catch (Exception e) {
			e.printStackTrace();
			mav.setViewName("/error.jsp");
			return mav;
		}

	}

	/**
	 * 删除管理员
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/deleteAdmin")
	public String deleteAdmin(HttpServletRequest request, int id) {
		try {
			int result = adminService.deleteAdmin(id);
			if (result == 1) {
				request.setAttribute("result", "删除成功！");
				return "redirect:selectAdminList";
			} else {
				request.setAttribute("result", "删除失败！");
				return "/admin/result.jsp";
			}
		} catch (Exception e) {
			request.setAttribute("result", "删除失败！");
			return "/admin/result.jsp";
		}
	}

//	
//	
	/**
	 * 更新管理员信息
	 * 
	 * @param admin
	 * @return
	 */
	@RequestMapping("/updateAdmin")
	public String updateAdmin(HttpServletRequest request, Admin admin) {
		try {
			System.out.println(1 / 0);
			String newPass = admin.getPassword();
			newPass = Md5Utils.encodingMd5(admin.getUsername() + newPass);// 密码加密
			admin.setPassword(newPass);
			int result = adminService.updateAdmin(admin);
			if (result == 1) {
				request.setAttribute("result", "修改成功！");
				return "redirect:selectAdminList";
			} else {
				request.setAttribute("result", "修改失败！");
				return "/admin/result.jsp";
			}
		} catch (Exception e) {
			request.setAttribute("result", "修改失败！");
			return "/admin/result.jsp";
		}
	}

//	
//	
	/**
	 * 新增管理员
	 * 
	 * @param admin
	 * @return
	 */
	@RequestMapping("/insertAdmin")
	public String insertAdmin(HttpServletRequest request, Admin admin) {
		try {
			String newPass = admin.getPassword();
			newPass = Md5Utils.encodingMd5(admin.getUsername() + newPass);// 密码加密
			admin.setPassword(newPass);
			int result = adminService.insertAdmin(admin);
			if (result == 1) {
				request.setAttribute("result", "新增成功！");
				return "redirect:selectAdminList";
			} else {
				request.setAttribute("result", "新增失败！");
				return "/admin/result.jsp";
			}
		} catch (Exception e) {
			request.setAttribute("result", "新增失败！");
			return "/admin/result.jsp";
		}
	}
//	

	/**
	 * 根据请求参数分配不同的业务需求 param 为：exit ,则退出系统；add:新增 ； update:更新信息 public String
	 * requestType(HttpServletRequest request, Model model, String param,Integer id)
	 * 
	 * @param request
	 * @param model
	 * @param param
	 * @param id
	 * @return
	 */
	@RequestMapping("/requestType")
	public String requestType(HttpServletRequest request, Model model, String param, Integer id) {
		PageData pageData = new PageData();

		String url = "";
		if ("exit".equals(param)) {
			HttpSession session = request.getSession();
			session.invalidate();
			url = "/login.jsp";
			return url;
		}

		Admin adminSession = (Admin) request.getSession().getAttribute("admin");
		if (adminSession != null) {
			model.addAttribute("companyList", companyService.selectCompany(pageData, adminSession));
//			model.addAttribute("voltageList", voltageService.selectVoltageList());
		}

		if ("add".equals(param)) {
			url = "/admin/admin_add.jsp";
			return url;
		}
		if ("update".equals(param)) {
			Admin admin = adminService.selectAdminById(id);
			model.addAttribute("admin", admin);
			url = "/admin/admin_edit.jsp";
			return url;
		}
		return url;
	}
//	

	/**
	 * 管理员查询所有用户列表
	 * 
	 * @param pageData
	 * @return
	 */
//	@RequestMapping(value = "/selectUserList")
//	public ModelAndView selectUserList(PageData pageData, HttpServletRequest request) {
//		ModelAndView mav = new ModelAndView();
//		mav.setViewName("/user/user_query_success.jsp");
//		Admin admin = (Admin) request.getSession().getAttribute("admin");
//		pageData = PagesUtils.getPageData(pageData);
//		if (admin != null) {
//			String voltage = admin.getVoltage();
//			String companyname = admin.getCompanyId().getCompanyname();
//			try {
//				List<User> userList = userService.selectUserList(pageData, admin);
//				mav.addObject("user", userList);
//				long count = userService.countByExample(companyname, voltage);
//				pageData.setCount(count);
//				pageData.setPages(PagesUtils.getPages(pageData.getLimit(), count));
//				return mav;
//			} catch (Exception e) {
//				e.printStackTrace();
//				return null;
//			}
//		} else {
//			return mav;
//		}
//
//	}

	/**
	 * 查询寻电压所对应的变电站
	 * 
	 * @param voltage
	 * @return
	 */
	@RequestMapping(value = "/selectTransformerSubList")
	@ResponseBody
	public List<TransformerSub> selectTransformerSubList(HttpServletRequest request) {
		try {
			HttpSession session = request.getSession();
			Admin admin = (Admin) session.getAttribute("admin");
			String voltage = admin.getVoltage();
			return transformerSubService.selectTransformerSubList(new PageData(), voltage,
					admin.getCompanyId().getCompanyname());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 查询所有请求开锁日志
	 * 
	 * @param request
	 * @param pageData
	 * @return
	 */
	@RequestMapping("/selectLogList")
	public ModelAndView selectLogList(HttpServletRequest request, PageData pageData) {
		pageData = PagesUtils.getPageData(pageData);
		ModelAndView mav = new ModelAndView();
		Admin admin = (Admin) request.getSession().getAttribute("admin");

		if (admin != null) {
			List<Requestlog> logList = requestlogService.selectRequestLogList(pageData,
					admin.getCompanyId().getCompanyname(), admin.getVoltage());
			long count = requestlogService.selectCountByExample(admin);
			pageData.setCount((int) count);
			pageData.setPages(PagesUtils.getPages(pageData.getLimit(), (int) count));
			mav.addObject("logList", logList);
		}

		mav.addObject("pageData", pageData);
		mav.setViewName("/log/log_query_success.jsp");
		return mav;
	}

	/**
	 * 删除请求信息
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/deleteRequestlog")
	public String deleteRequestlog(HttpServletRequest request, Integer id, Integer page) {
		String url = "redirect:selectLogList?page" + page;
		try {
			requestlogService.deleteRequestlog(id);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("result", "删除失败！");
			return "/log/log_delete_result.jsp";
		}
		return url;
	}

}
