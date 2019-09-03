package com.app.bzpower.controller.pc;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.app.bzpower.entity.Admin;
import com.app.bzpower.entity.Company;
import com.app.bzpower.entity.PageData;
import com.app.bzpower.entity.Requestlog;
import com.app.bzpower.entity.User;
import com.app.bzpower.service.CompanyService;
import com.app.bzpower.service.RequestlogService;
import com.app.bzpower.service.TransformerSubService;
import com.app.bzpower.service.UserService;
import com.app.bzpower.util.Md5Utils;
import com.app.bzpower.util.PagesUtils;

@Controller("pcUserController")
@RequestMapping("/pcUser")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private CompanyService companyService;

	@Autowired
	private RequestlogService requestlogService;

	@Autowired
	private TransformerSubService transformerSubService;

	/**
	 * 用户列表
	 * 
	 * @param request
	 * @param user
	 * @param voltage 电压等级，管理员根据电压等级来查询自己所管理的用户人员
	 * @return
	 */
	@RequestMapping("/selectUserList")
	public ModelAndView selectUserList(HttpServletRequest request, PageData pageData) {
		ModelAndView mav = new ModelAndView();
		try {
			mav.setViewName("/user/user_query_success.jsp");
			Admin admin = (Admin) request.getSession().getAttribute("admin");
			pageData = PagesUtils.getPageData(pageData);
			
			String voltage = admin.getVoltage();
			String companyname = admin.getCompanyId().getCompanyname();
			
			List<User> userList = userService.selectUserList(pageData, admin);
			mav.addObject("user", userList);
			long count = userService.countByExample(companyname, voltage);
			pageData.setCount(count);
			pageData.setPages(PagesUtils.getPages(pageData.getLimit(), count));
			mav.addObject("pageData", pageData);
			return mav;
		} catch (Exception e) {
			e.printStackTrace();
			return mav;
		}

		
	}

	@RequestMapping("/insertUser")
	public String insertUser(HttpServletRequest request, User user) {
		Admin admin = (Admin) request.getSession().getAttribute("admin");
		try {
			Company company = new Company();
			company.setId(admin.getCompanyId().getId());
			user.setCompanyId(company);
			int result = userService.insertUser(user);
			if(result == 1) {
				request.setAttribute("result", "新增成功！");
				return "redirect:selectUserList";
			}else {
				request.setAttribute("result", "新增失败！");
				return "/user/result.jsp";
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("result", "新增失败！");
			return "/user/result.jsp";
		}
	}

//	
	@RequestMapping("/selectUserById")
	public ModelAndView selectUserById(String param, int id) {
		ModelAndView mav = new ModelAndView();
		if (param != null && !"".equals(param)) {

			mav.addObject("user", userService.selectUserById(id));
			mav.setViewName("/user/user_edit.jsp");
		}
		return mav;

	}

//	
	@RequestMapping("/deleteUser")
	public String deleteUser(HttpServletRequest request, int id) {
		try {
			int result = userService.deleteUser(id);
			if(result == 1) {
				request.setAttribute("result", "删除成功！");
				return "redirect:selectUserList";
			}
			else {
				request.setAttribute("result", "删除失败！");
				return "/user/result.jsp";
			}
				
		} catch (Exception e) {
			request.setAttribute("result", "删除失败！");
			return "/user/result.jsp";
		}
	}

	/**
	 * 用户注册
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping("/registerUser")
	public ModelAndView registerUser(User user) {
		ModelAndView mav = new ModelAndView();
		try {
			// 判断用户名是否已经存在，保证每一个用户注册用户名都是唯一的
			String username = user.getUsername();
			User newUser = userService.selectUserByUsername(username);
			if (newUser != null) {
				mav.addObject("result", "用户名已经存在！请更换用户名");
				mav.setViewName("/user/user_add.jsp");
				return mav;
			} else {
				String newPass = user.getPassword();
				newPass = Md5Utils.encodingMd5(newPass);// 密码加密
				user.setPassword(newPass);
				user.setUsername(user.getPhone());
				userService.insertUser(user);
				mav.addObject("result", "添加成功！");
				mav.setViewName("/user/result.jsp");
			}

		} catch (Exception e) {
			mav.addObject("result", "添加失败！");
		}
		return mav;
	}

	/**
	 * 用户查询自己的请求信息
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/selectlogByUsername")
	public ModelAndView selectlogByUsername(HttpServletRequest request, PageData pageData) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/log/log_query_success.jsp");
		try {
			User user = (User) request.getSession().getAttribute("user");
			String username = user.getUsername();
			if (user != null) {
				List<Requestlog> logList = null;
				int pages = requestlogService.selectRequestLogByUserNamePages(username);
				pageData.setPages(pages);
				mav.addObject("logList", logList);
				mav.addObject("pageData", pageData);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return mav;
		}
		return mav;
	}

	/**
	 * 分配请求页面
	 * 
	 * @param request
	 * @param model
	 * @param param
	 * @param id
	 * @return
	 */
	@RequestMapping("/requestType")
	public String requestType(HttpServletRequest request, Model model, String param, Integer id) {
		String url = "";
		Admin admin = (Admin) request.getSession().getAttribute("admin");
		if (admin != null) {
			if ("logOut".equals(param)) {
				HttpSession session = request.getSession();
//				session.removeAttribute("admin");
				session.invalidate();
				url = "/login.jsp";
				return url;
			}
			model.addAttribute("companyList", companyService.selectCompany(new PageData(), admin));
			if ("add".equals(param)) {
//				User user = userService.selectUserById(id);
				url = "/user/user_add.jsp";
				model.addAttribute("transformerSubList", transformerSubService
						.selectTransformerSubList(new PageData(),admin.getVoltage(), admin.getCompanyId().getCompanyname()));
				return url;
			}
			if ("update".equals(param)) {
				User user = userService.selectUserById(id);
				model.addAttribute("user", user);
				model.addAttribute("transformerSubList", transformerSubService
						.selectTransformerSubList(new PageData(),user.getVoltage(), admin.getCompanyId().getCompanyname()));
				url = "/user/user_edit.jsp";
				return url;
			}
		}

		return url;
	}
//	

	@RequestMapping("/updateUser")
	public String updateUser(HttpServletRequest request, User user) {

		try {
			user.setPassword(Md5Utils.encodingMd5(user.getUsername() + user.getPassword()));
			int result = userService.updateUser(user);
			if (result == 1) {
				request.setAttribute("result", "更新成功");
				return "redirect:selectUserList";
			}
			else
				request.setAttribute("result", "更新失败");
			return "/user/result.jsp";
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "更新失败");
			return "/user/result.jsp";
		}
	}

}
