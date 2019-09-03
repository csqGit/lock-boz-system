package com.app.bzpower.controller.app;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.servlet.ModelAndView;

import com.app.bzpower.entity.Lockinfo;
import com.app.bzpower.entity.PageData;
import com.app.bzpower.entity.RequestResult;
import com.app.bzpower.service.LockService;

/**
 * 锁类
 */
@Controller(value = "/appLockController")
@RequestMapping(value = "/appLock")
public class LockController {

	@Autowired
	private LockService lockService;

	/**
	 * <p>查询锁列表</p>
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/selectLockList", produces = "application/json;charset=UTF-8")
	@ResponseBody
	@CrossOrigin(origins = "*", maxAge = 3600)
	public RequestResult selectLockList(String voltage) {
		RequestResult rr = new RequestResult();
		try {
			PageData pageData = new PageData();
			pageData.setPages(0);
			List<Lockinfo> lockList = lockService.selectLockList(pageData, voltage, "");
			rr.setCode(100);
			rr.setResult(lockList);
		} catch (Exception e) {
			e.printStackTrace();
			rr.setCode(200);
			rr.setResult("没有设备");
		}
		return rr;
	}
	
	


	/**
	 * 根据锁名称查询锁信息
	 * @param lockName 锁名称
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/selectLockByName", produces = "application/json;charset=UTF-8")
	@ResponseBody
	@CrossOrigin(origins = "*", maxAge = 3600)
	public RequestResult selectLockByName(String lockName) {
		RequestResult rr = new RequestResult();

		try {
			if (lockName != null || !"".equals(lockName)) {
				rr.setCode(100);
				Lockinfo lock = lockService.selectLockByName(lockName);
				if (lock != null)
					rr.setResult(lock);
				else
					rr.setResult("该锁不存在");
				return rr;
			} else {
				rr.setCode(300);
				rr.setResult("请输入锁名称");
				return rr;
			}
		} catch (Exception e) {
			e.printStackTrace();
			rr.setCode(200);
			rr.setResult("请重新查询");
			return rr;
		}
	}
	
	
	/**
	 * 根据锁名称查询锁信息
	 * @param lockName 锁名称
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/selectLockByDid", produces = "application/json;charset=UTF-8")
	@ResponseBody
	@CrossOrigin(origins = "*", maxAge = 3600)
	public RequestResult selectLockByDid(String did) {
		RequestResult rr = new RequestResult();

		try {
			if (did != null || !"".equals(did)) {
				
				Lockinfo lock = lockService.selectLockByDid(did);
				if (lock != null) {
					rr.setCode(100);
					rr.setResult(lock);
				}
				else {
					rr.setCode(400);
					rr.setResult("该锁不存在");
				}
				return rr;
			} else {
				rr.setCode(300);
				rr.setResult("请输入锁名称");
				return rr;
			}
		} catch (Exception e) {
			e.printStackTrace();
			rr.setCode(200);
			rr.setResult("请重新查询");
			return rr;
		}
	}
	

	/**
	 * 添加锁
	 * @param lock
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/insertLock", produces = "application/json;charset=UTF-8")
	@ResponseBody
	@CrossOrigin(origins = "*", maxAge = 3600)
	public RequestResult insertLock(Lockinfo Lockinfo) {
		RequestResult rr = new RequestResult();
		try {
			if (Lockinfo != null) {
				lockService.insertLock(Lockinfo);
				rr.setCode(100);
				rr.setResult("添加成功");
				return rr;
			} else {
				rr.setCode(300);
				rr.setResult("请正确填写信息");
				return rr;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			rr.setCode(200);
			rr.setResult("添加失败");
			return rr;
		}
	}

	/**
	 * 删除锁
	 * @param lockName 锁名称
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/deleteLock", produces = "application/json;charset=UTF-8")
	@ResponseBody
	@CrossOrigin(origins = "*", maxAge = 3600)
	public RequestResult deleteLock(String lockName) {
		RequestResult rr = new RequestResult();
		try {
			if (lockName != null || !"".equals(lockName)) {
				lockService.deleteLockByName(lockName);
				rr.setCode(100);
				rr.setResult("删除成功");
				return rr;
			} else {
				rr.setCode(300);
				rr.setResult("请正确填写锁名称");
				return rr;
			}
		} catch (Exception e) {
			e.printStackTrace();
			rr.setCode(200);
			rr.setResult("删除失败");
			return rr;
		}
	}

}
