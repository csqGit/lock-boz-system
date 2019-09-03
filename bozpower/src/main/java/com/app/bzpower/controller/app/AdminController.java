package com.app.bzpower.controller.app;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.app.bzpower.entity.Admin;
import com.app.bzpower.entity.RequestResult;
import com.app.bzpower.entity.Requestlog;
import com.app.bzpower.entity.TransformerSub;
import com.app.bzpower.entity.User;
import com.app.bzpower.service.AdminService;
import com.app.bzpower.service.RequestlogService;
import com.app.bzpower.service.TransformerSubService;
import com.app.bzpower.service.UserService;
import com.app.bzpower.util.JPushSHUtils;

@Controller("appAdminController")
@RequestMapping("/appAdmin")
public class AdminController {

	@Autowired
	private RequestlogService requestlogService;

	@Autowired
	private AdminService adminService;

	@Autowired
	private UserService userService;

	@Autowired
	private TransformerSubService transformerSubService;

	/**
	 * 查询用户请求日志
	 * <br />
	 * @param requesttime 申请时间
	 * @param param 待审核/已审核/未审核
	 * @param phone 管理员电话号码
	 * @return JSON对象，code为查询码，
	 * <p>100表示正确查询</p>
	 * <p>200表示错误查询</p>
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@ResponseBody
	@RequestMapping(value = "/selectlog", produces = "application/json;charset=UTF-8")
	@CrossOrigin(maxAge = 3600, origins = "*")
	public RequestResult selectlog(String requesttime, String param, String phone) {
		RequestResult rr = new RequestResult();
		try {
			List<Requestlog> logList = requestlogService.adminSelectlogList(requesttime, param, phone);
			rr.setCode(100);
			rr.setResult(logList);
			return rr;
		} catch (Exception e) {
			rr.setCode(200);
			rr.setResult(null);
			e.printStackTrace();
			return rr;
		}
	}

	/**
	 * 管理员审核用户请求开锁信息
	 * @param requestlog 日志信息
	 * @return 
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/updateRequestlog", produces = "application/json;charset=UTF-8")
	@ResponseBody
	@CrossOrigin(origins = "*", maxAge = 3600)
	public RequestResult updateRequestlog(Requestlog requestlog) {
		RequestResult rr = new RequestResult();
		try {
			Requestlog req = requestlogService.selectRequestLogById(requestlog.getId());
			String phone = req.getPhone();
			
			User user = userService.selectUserByPhone(phone);
			if (user != null) {
				String dtm = req.getDtm();
				String openNum = this.computerDtm(dtm);
				requestlog.setOpennum(openNum);
				requestlogService.updateRequestlog(requestlog);
				
				String username = user.getUsername();
				if (requestlog.getStatus() == 1) {
					// 管理员将审核信息推送给指定用户
					JPushSHUtils.pushMessageToUser(username, "通知",
							"管理员已" + requestlog.getExamineresult() + "开锁,开锁码：" + openNum);
				} else {// 管理员将审核信息推送给指定用户
					JPushSHUtils.pushMessageToUser(username, "通知", "管理员已" + requestlog.getExamineresult() + "开锁");
				}
			} else {
				rr.setCode(200);
				rr.setResult("更新失败");
			}
			rr.setCode(100);
			rr.setResult("你没有权限同意");
		} catch (Exception e) {
			rr.setCode(200);
			e.printStackTrace();
			rr.setResult("更新失败");
		}
		return rr;
	}

	/**
	 * 根据动态码计算开锁码
	 * 
	 * @param dtm 用户申请提交的动态码
	 * @return
	 */
	public String computerDtm(String dtm) {
//		String dtm = "9999";
		int num = Integer.parseInt(dtm);
		int comNum = num * num + 3 * num + 131;
		String numStr = String.valueOf(comNum);
		numStr = numStr.substring(numStr.length() - 4, numStr.length());
		// y = x * x + 3 * x + 131
		// 截取字符串存入数组
		int[] intArray = new int[numStr.length()];

		for (int i = 0; i < numStr.length(); i++) {
			intArray[i] = Integer.parseInt(numStr.substring(i, i + 1));
			switch (intArray[i]) {
			case 0:
				intArray[i] = 1;
				break;

			}
		}
		String returnStr = "";
		for (int x : intArray) {
			returnStr += x;
		}
		return returnStr;
	}

	/**
	 * 根据id查询用户请求日志
	 * @param id 日志请求的id
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/selectRequestlogById", produces = "application/json;charset=UTF-8")
	@ResponseBody
	@CrossOrigin(origins = "*", maxAge = 3600)
	public RequestResult selectRequestlogById(Integer id) {
		RequestResult rr = new RequestResult();
		try {
			Requestlog requestlog = requestlogService.selectRequestLogById(id);
			rr.setCode(100);
			rr.setResult(requestlog);
		} catch (Exception e) {
			rr.setCode(200);
			e.printStackTrace();
			rr.setResult("请求失败");
		}
		return rr;
	}

	/**
	 * 管理员注册
	 * 
	 * @param admin 管理员对象
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/adminLogin", produces = "application/json;charset=UTF-8")
	@ResponseBody
	@CrossOrigin(origins = "*", maxAge = 3600)
	public RequestResult adminRegister(Admin admin) {
		RequestResult rr = new RequestResult();
		try {
			Admin existAdmin = adminService.selectAdminByPhone(admin.getTelephone());
			if (existAdmin != null) {
				rr.setCode(100);// 可以注册
				return rr;
			} else {
				rr.setCode(200);
				rr.setResult("你没有权限注册");
				return rr;
			}
		} catch (Exception e) {
			rr.setCode(200);
			rr.setResult("注册异常");
			return rr;
		}
	}

	/**
	 * 查询电话号码是否已经注册
	 * @param phone 电话号码
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/selectPhone", produces = "application/json;charset=UTF-8")
	@ResponseBody
	@CrossOrigin(origins = "*", maxAge = 3600)
	public RequestResult selectPhone(String phone) {
		RequestResult rr = new RequestResult();
		try {
			Admin admin = adminService.selectAdminByPhone(phone);
			if (admin != null) {
				rr.setCode(100);
				rr.setResult(admin);
				return rr;
			} else {
				rr.setCode(200);
				return rr;
			}
		} catch (Exception e) {
			rr.setCode(200);
			return rr;
		}
	}

	/**
	 * 根据电压等级查询所有用户
	 * @param voltage 电压等级
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/selectUserList", produces = "application/json;charset=UTF-8")
	@ResponseBody
	@CrossOrigin(origins = "*", maxAge = 3600)
	public RequestResult selectUserList(String voltage) {
		RequestResult rr = new RequestResult();
		try {
			List<User> userList = userService.selectUserList(voltage);
			rr.setCode(100);
			rr.setResult(userList);
			return rr;
		} catch (Exception e) {
			rr.setCode(200);
			e.printStackTrace();
			rr.setResult("请求失败");
			return rr;
		}
	}

	/**
	 * 根据id删除用户信息
	 * @param id 用户id
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/deleteUserById", produces = "application/json;charset=UTF-8")
	@ResponseBody
	@CrossOrigin(origins = "*", maxAge = 3600)
	public RequestResult deleteUserById(int id) {
		RequestResult rr = new RequestResult();
		try {
			userService.deleteUser(id);
			rr.setCode(100);
			rr.setResult("删除成功");
			return rr;
		} catch (Exception e) {
			rr.setCode(200);
			e.printStackTrace();
			rr.setResult("删除失败");
			return rr;
		}
	}

	/**
	 * 新增变电站信息
	 * @param transformerSub 变电站对象
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/insertTransformersub", produces = "application/json;charset=UTF-8")
	@ResponseBody
	@CrossOrigin(origins = "*", maxAge = 3600)
	public RequestResult insertTransformersub(TransformerSub transformerSub) {
		RequestResult rr = new RequestResult();
		Map<String, String> map = new HashMap<String, String>();
		try {
//			TransformerSub ts = transformerSubService.selectTransformerSubList(transformerSub.getVoltage(),""
//					);
			TransformerSub ts = transformerSubService.selectTransformerSubList(transformerSub.getVoltage(), transformerSub.getTransformersub(), transformerSub.getCompanyId().getCompanyname());
			if (ts != null) {
				rr.setCode(200);
				map.put("msg", "变电站已存在");
				rr.setResult(map);
				return rr;
			} else {
				transformerSubService.insertTransformerSub(transformerSub);
				rr.setCode(100);
				map.put("msg", "添加成功");
				rr.setResult(map);
				return rr;
			}

		} catch (Exception e) {
			rr.setCode(200);
			map.put("msg", "添加失败");
			rr.setResult(map);
			e.printStackTrace();
			return rr;
		}
	}
	
	
    /**
     * <p>查询历史记录</p>
     * @param phone 管理员电话号码
     * @param voltage 管理员所属电压等级
     * @return
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    @RequestMapping("/selectLogHostoryList")
    @ResponseBody
    @CrossOrigin(origins = "*", maxAge = 3600)
    public RequestResult selectLogHostoryList(String voltage) {
    	RequestResult rr = new RequestResult();
    	Map<String, Object> map = new HashMap<String, Object>();
    	try {
			List<Requestlog> requestlogList = requestlogService.selectLogHositoryList(voltage);
			rr.setCode(100);
			map.put("result", requestlogList);
			rr.setResult(map);
		} catch (Exception e) {
			rr.setCode(200);
			map.put("msg", "查询异常");
			rr.setResult(map);
			e.printStackTrace();
		}
    	return rr;
    }  

}
