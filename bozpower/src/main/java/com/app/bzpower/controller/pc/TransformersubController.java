package com.app.bzpower.controller.pc;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.app.bzpower.entity.Admin;
import com.app.bzpower.entity.PageData;
import com.app.bzpower.entity.TransformerSub;
import com.app.bzpower.service.TransformerSubService;
import com.app.bzpower.util.PagesUtils;

@Controller("pcTransformersubController")
@RequestMapping("pcTransformersub")
public class TransformersubController {
	
	@Autowired
	private TransformerSubService transformerSubService;
	
	
	/**
	 * url: pcTransformersubController/selectTransformersubList
	 * 根据单位名称和电压等级查询变电站列表
	 * @param pageData
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "selectTransformersubList")
	public ModelAndView selectTransformersubList( HttpServletRequest request,PageData pageData) {
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		Admin admin = (Admin) session.getAttribute("admin");
		pageData = PagesUtils.getPageData(pageData);
		try {
			String voltage = admin.getVoltage();
			String companyname = admin.getCompanyId().getCompanyname();
			List<TransformerSub> list = 
					transformerSubService.selectTransformerSubList(pageData,
							voltage, companyname);
			long count = transformerSubService.selectTransformerSubCount(voltage, companyname);
			pageData.setCount( count);
			pageData.setPages(PagesUtils.getPages(pageData.getLimit(), count));;
			mav.addObject("transformersub", list);
			mav.addObject("pageData", pageData);
			mav.setViewName("/transformersub/transformersub_query_success.jsp");
		} catch (Exception e) {
			e.printStackTrace();
			mav.setViewName("/error.jsp");
		}
		return mav;
	}
	
	
	
	/**
	 * url: pcTransformersubController/selectTransformersubById
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "selectTransformersubById")
	public ModelAndView selectTransformersubById(HttpServletRequest request, int id) {
		ModelAndView mav = new ModelAndView();
		try {
			TransformerSub transformerSub = transformerSubService.selectTransfromerSubById(id);
			mav.addObject("transformersub", transformerSub);
			mav.setViewName("/transformersub/transformersub_edit.jsp");
		} catch (Exception e) {
			e.printStackTrace();
			mav.setViewName("/error.jsp");
		}
		return mav;
	}
	
	
	
	/**
	 * url: pcTransformersubController/insertTransformerSub
	 * @param request
	 * @param transformerSub
	 * @return
	 */
	@RequestMapping(value = "insertTransformerSub")
	public String insertTransformerSub(HttpServletRequest request, TransformerSub transformerSub) {
		try {
			int result = transformerSubService.insertTransformerSub(transformerSub);
			if(result == 1) {
				request.setAttribute("result", "添加成功！");
				return "redirect:selectTransformersubList";
			}else {
				request.setAttribute("result", "添加成功！");
				return "/transformer/result.jsp";
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("result", "添加成功！");
			return "/transformer/result.jsp";
		}
	}
	
	
	/**
	 * url: pcTransformersubController/deleteTransformerSubById
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "deleteTransformerSubById")
	public String deleteTransformerSubById(HttpServletRequest request, int id) {
		try {
			
			int result = transformerSubService.deleteTransformerSubById(id);
			if(result == 1) {
				request.setAttribute("result", "删除成功！");
				return "redirect:selectTransformersubList";
			}else {
				request.setAttribute("result", "删除失败！");
				return "/transformersub/result.jsp";
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("result", "删除失败！");
			return "/transformersub/result.jsp";
		}
		
	}
	
	
	
	
	/**
	 * url: pcTransformersubController/updateTransformerSub
	 * @param request
	 * @param transformerSub
	 * @return
	 */
	@RequestMapping(value = "updateTransformerSub")
	public String updateTransformerSub(HttpServletRequest request, TransformerSub transformerSub) {
		try {
			int result = transformerSubService.updateTransformerSub(transformerSub);
			if(result == 1) {
				request.setAttribute("result", "更新成功！");
				return "redirect:selectTransformersubList";
			}else {
				request.setAttribute("result", "更新失败！");
				return "/transformersub/result.jsp";
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("result", "更新失败！");
			return "/transformersub/result.jsp";
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	
	

}
