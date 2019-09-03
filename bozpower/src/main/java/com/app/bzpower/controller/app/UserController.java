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
import com.app.bzpower.entity.Lockinfo;
import com.app.bzpower.entity.PageData;
import com.app.bzpower.entity.RequestResult;
import com.app.bzpower.entity.Requestlog;
import com.app.bzpower.entity.TransformerSub;
import com.app.bzpower.entity.User;
import com.app.bzpower.service.AdminService;
import com.app.bzpower.service.LockService;
import com.app.bzpower.service.RequestlogService;
import com.app.bzpower.service.TransformerSubService;
import com.app.bzpower.service.UserService;
import com.app.bzpower.util.JPushSQUtils;
import com.app.bzpower.util.Md5Utils;

/**
 * 用户appController
 */
@Controller("appController")
@RequestMapping("/appUser")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private LockService lockService;

    @Autowired
    private RequestlogService requestlogService;
    
    @Autowired
    private AdminService adminService;
    
    @Autowired
    private TransformerSubService transfromerSubService;
    

//	

    /**
     * 用户app登录
     *
     * @param user
     * @return
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    @RequestMapping(value = "/selectUser", produces = "application/json;charset=utf-8")
    @ResponseBody
    @CrossOrigin(origins = "*", maxAge = 3600)
    public RequestResult selectUser(User user) {
        Map map = new HashMap();
        RequestResult rr = new RequestResult();
        try {
            User oldUser = userService.selectUserByPhone(user.getPhone());
            
            if (oldUser != null) {
                //密码加密时根据用户名和密码，防止用户密码相同
                String pass = Md5Utils.encodingMd5(user.getPhone()+ user.getPassword());
                System.out.println("生成密码为：pass = " + pass);
                System.out.println("旧密码 = " + oldUser.getPassword());
                boolean flag = Md5Utils.checkPassword(user.getPhone() + user.getPassword(), oldUser.getPassword());
                if (flag) {
                    rr.setCode(100);
                    map.put("msg", "登录成功");
                    map.put("user", oldUser);
                    rr.setResult(map);
                } else {
                    rr.setCode(200);
                    map.put("msg", "密码错误");
                    rr.setResult(map);
                }
            } else {
                rr.setCode(200);
                map.put("msg", "用户名错误");
                rr.setResult(map);
            }

        } catch (Exception e) {
            e.printStackTrace();
            rr.setCode(200);
            map.put("msg", "登录失败");
            rr.setResult(null);
            return rr;
        }
        return rr;
    }


    /**
     * 用户扫码根据mac查询锁信息
     *
     * @param mac
     * @return
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    @RequestMapping(value = "/selectLock", produces = "application/json;charset=utf-8")
    @ResponseBody
    @CrossOrigin(origins = "*", maxAge = 3600)
    public RequestResult selectLock(String mac) {
        RequestResult rr = new RequestResult();
        try {
            Lockinfo lock = lockService.selectLockByMac(mac);
            System.out.println("lock = " + lock);
            rr.setCode(100);
            rr.setResult(lock);
        } catch (Exception e) {
            e.printStackTrace();
            rr.setCode(200);
            rr.setResult("请求失败！！！");
            return rr;
        }
        return rr;
    }


    /**
     * 	用户查询当前审核的信息结果，如果requesttime 为空，则表示查询全部
     * @param phone用户电话号码
     * @param requesttime请求时间
     * @param examinetype 请求状态
     * @return
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    @RequestMapping(value = "/selectExamineList", produces = "application/json;charset=utf-8")
    @ResponseBody
    @CrossOrigin(origins = "*", maxAge = 3600)
    public RequestResult selectExamineList(String phone, String requesttime, String examinetype) {
        RequestResult rr = new RequestResult();
        try {
            List<Requestlog> logList = requestlogService.selectRequestLogList(requesttime, phone, examinetype);
            rr.setCode(100);
            rr.setResult(logList);
        } catch (Exception e) {
            e.printStackTrace();
            rr.setCode(200);
            return rr;
        }
        return rr;
    }


    /**
     * 新增用户请求开锁信息
     *
     * @param requestlog
     * @return
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    @RequestMapping(value = "/insertRequestlog", produces = "application/json;charset=UTF-8")
    @ResponseBody
    @CrossOrigin(origins = "*", maxAge = 3600)
    public RequestResult insertRequestlog(Requestlog requestlog) {
        RequestResult rr = new RequestResult();
//        System.out.println(requestlog.toString());
        try {
            Lockinfo lock = lockService.selectLockByMac(requestlog.getMac());
            requestlog.setDid(lock.getDid());
//            requestlog.setCompanyId(lock.getCompanyId().getCompanyname());
//            requestlog.setLockname(lock.getCompanyId().getCompanyname());
//            requestlog.setTransformersub(lock.getTransformersubId().getTransformersub());
            requestlog.setCompanyId(lock.getCompanyId());
            requestlog.setLockinfoId(new Lockinfo());
            
            requestlog.setVoltage(lock.getVoltage());
            String phone = requestlog.getPhone();
            User user = userService.selectUserByPhone(phone);
            requestlog.setRealname(user.getRealname());
            requestlogService.insertRequestlog(requestlog);
            //调用推送
            List<Admin> adminList = adminService.selectAdminByVoltage(lock.getVoltage());
            //用户将申请信息推送给管理员app
            for(int i = 0; i < adminList.size(); i ++ ) {
            	String adminPhone = adminList.get(i).getTelephone();
            	String voltage = adminList.get(i).getVoltage();
            	if(requestlog.getVoltage().equals(voltage)) {
            		JPushSQUtils.pushMessageToAdmin(adminPhone,"消息","用户" + user.getRealname() + "请求开锁");
            	}else {}
            }
            rr.setCode(100);
            rr.setResult("请求开锁成功");
        } catch (Exception e) {
            rr.setCode(200);
            e.printStackTrace();
            rr.setResult("请求开锁失败");
        }
        return rr;
    }
    
    
    /**
     * 查询所有的变电站
     * @return
     */
    @RequestMapping(value = "/selectTransfromerSub")
    @ResponseBody
    @SuppressWarnings({ "unchecked", "rawtypes" })
	public RequestResult selectTransfromerSub(String voltage) {
		RequestResult rr = new RequestResult();
		try {
			List<TransformerSub> transformerList = transfromerSubService.selectTransformerSubList(new PageData(),voltage, "山西长治");
			System.out.println(transformerList);
			if(transformerList.size() > 0) {
				rr.setCode(100);
		    	rr.setResult(transformerList);
			}else {
				rr.setCode(300);
				rr.setResult("暂无变电站信息");
			}
	    	
	    	return rr;
		} catch (Exception e) {
			rr.setCode(200);
			e.printStackTrace();
			return rr;
		}
    	
    }
    
    
   

    /**
     * app用户注册
     *
     * @param user
     * @return
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    @RequestMapping(value = "/registerUser", produces = "application/json;charset=UTF-8")
    @ResponseBody
    @CrossOrigin(origins = "*", maxAge = 3600)
    public RequestResult registerUser(User user) {
        RequestResult rr = new RequestResult();
        Map map = new HashMap();
        try {
            //判断用户名是否已经存在，保证每一个用户注册用户名都是唯一的
            String phone = user.getPhone();
            if("".equals(phone) || phone == null) {
            	rr.setCode(200);
            	map.put("msg", "电话号码不能为空");
                rr.setResult(map);
    			return rr;
    		}
            User newUser = userService.selectUserByPhone(phone);
            if (newUser != null) {
                rr.setCode(200);
                map.put("msg", "电话号码已经存在");
                rr.setResult(map);
                return rr;
            } else {
                String newPass = user.getPassword();
                String newPhone = user.getPhone();
                newPass = Md5Utils.encodingMd5(newPhone + newPass);//密码加密
                user.setPassword(newPass);
                user.setUsername(user.getPhone());
                userService.insertUser(user);
                rr.setCode(100);
                map.put("msg", "注册成功");
                map.put("user", user);
                rr.setResult(map);
            }
        } catch (Exception e) {
        	e.printStackTrace();
            rr.setCode(200);
            map.put("msg", "注册失败");
            rr.setResult(map);
        }
        return rr;
    }
    
    
    /**
     * 查询历史记录
     * @param phone
     * @param voltage
     * @return
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    @RequestMapping("/selectLogHostoryList")
    @ResponseBody
    @CrossOrigin(origins = "*", maxAge = 3600)
    public RequestResult selectLogHostoryList(String phone, String voltage) {
    	RequestResult rr = new RequestResult();
    	Map<String, Object> map = new HashMap<String, Object>();
    	try {
			List<Requestlog> requestlogList = requestlogService.selectLogHositoryList(phone, voltage);
			rr.setCode(100);
			map.put("result", requestlogList);
			rr.setResult(map);
		} catch (Exception e) {
			rr.setCode(200);
			map.put("msg", "查询异常");
			rr.setResult(map);
		}
    	return rr;
    }  

}
