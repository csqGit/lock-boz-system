package com.app.bzpower.controller.pc;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.app.bzpower.entity.Admin;
import com.app.bzpower.service.AdminService;
import com.app.bzpower.util.Md5Utils;

@Controller("loginController")
public class LoginController {

	@Autowired
	private AdminService adminService;

	@RequestMapping(value = "/login")
	public ModelAndView adminLogin(HttpServletRequest request, String username, String password, String voltage) {
		ModelAndView mav = new ModelAndView();

		try {

			Admin newAdmin = null;
			if (StringUtils.isEmpty(username)) {
				request.setAttribute("err", "用户名输入为空");
				mav.setViewName("/login.jsp");
				return mav;
			}
			if (StringUtils.isEmpty(password)) {
				request.setAttribute("err", "密码输入为空");
				mav.setViewName("/login.jsp");
				return mav;
			}
			request.setAttribute("voltage", voltage);
			List<Admin> existUser = adminService.selectAdminByName(username);
			if (existUser.size() == 0) {
				request.setAttribute("err", "用户名输入错误");
				mav.setViewName("/login.jsp");
				return mav;
			} else {
				String newPass = Md5Utils.encodingMd5(username + password);// 密码加密
				Admin admin = new Admin();
				admin.setUsername(username);
				admin.setPassword(newPass);
				admin.setVoltage(voltage);
				newAdmin = adminService.adminLogin(admin);
				request.setAttribute("username", username);
				if (newAdmin != null) {// 表示登录成功
					mav.setViewName("/admin/admin_login_success.jsp");
					mav.addObject("obj", newAdmin);
					request.getSession().setAttribute("admin", newAdmin);
					return mav;
				} else {
					mav.setViewName("/login.jsp");
					request.setAttribute("err", "密码输入错误");
					return mav;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			mav.setViewName("/login.jsp");
			return mav;
		}
	}
	
	
	/**
	 * 更新密码
	 * @param request
	 * @param admin
	 * @return
	 */
	@RequestMapping(value = "update", method = RequestMethod.POST)
	public String update(HttpServletRequest request, Admin admin) {
		
		try {
			
			String username = admin.getUsername();
			Admin newAdmin = adminService.selectAdminByUsername(username);
			if(newAdmin != null) {
				String newPass = Md5Utils.encodingMd5(admin.getUsername() + admin.getPassword());
				admin.setPassword(newPass);
				int result = adminService.updatePassword(admin);
				if(result == 1) {
					request.setAttribute("msg", "修改成功");
					return "forgetpass.jsp";
				}else {
					request.setAttribute("msg", "修改错误");
					return "forgetpass.jsp";
				}
			}else {
				request.setAttribute("msg", "用户名不存在");
				return "forgetpass.jsp";
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "修改错误");
			return "forgetpass.jsp";
		}
	}
	
	
	/**
	 * 退出登录
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "logout")
	public String logout(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.invalidate();
		
		return "/login.jsp";
	}


}
