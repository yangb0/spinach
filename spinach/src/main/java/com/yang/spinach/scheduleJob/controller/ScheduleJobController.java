package com.yang.spinach.scheduleJob.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.quartz.CronExpression;
import org.quartz.SchedulerException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yang.spinach.frame.filter.WebContext;
import com.yang.spinach.frame.utils.Const;
import com.yang.spinach.scheduleJob.entity.ScheduleJob;
import com.yang.spinach.scheduleJob.service.ScheduleJobService;

@Controller
@RequestMapping("/scheduleJob")
public class ScheduleJobController {

	@Resource
	private ScheduleJobService scheduleJobService;

	/**
	 * 任务列表
	 * 
	 * @return
	 */

	@RequestMapping("/list")
	public String list() {
		List<ScheduleJob> list = scheduleJobService.getAllScheduleJob();
		WebContext.setAttribute("list", list);
		return "scheduleJob/list";
	}

	@RequestMapping("add")
	public String add() {
		return "scheduleJob/add";
	}

	@ResponseBody
	@RequestMapping("/save.json")
	public Object create(ScheduleJob scheduleJob) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(Const.STATUS, -1);
		// 判断表达式
		boolean f = CronExpression.isValidExpression(scheduleJob
				.getCronExpression());
		if (!f) {
			map.put(Const.MSG, "cron表达式有误，不能被解析！");
			return map;
		}
		try {
			scheduleJob.setStatus("1");
			scheduleJobService.add(scheduleJob);
			map.put(Const.STATUS, 0);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			map.put(Const.MSG, "找不到指定的类");
		} catch (SchedulerException e) {
			e.printStackTrace();
			if (e.getMessage().contains(
					"because one already exists with this identification")) {
				map.put(Const.MSG, "任务组中存在同样的任务名");
			} else {
				map.put(Const.MSG, "未知原因,添加任务失败");
			}
		} catch (Exception e) {
			map.put(Const.MSG, Const.DEFAULT_ERROR);
			e.printStackTrace();
		}
		return map;
	}

	/**
	 * 暂停任务
	 */

	@ResponseBody
	@RequestMapping("/stopJob")
	public Object stop(String name, String group) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(Const.STATUS, -1);
		try {
			scheduleJobService.stopJob(name, group);
			map.put(Const.STATUS, 0);
		} catch (SchedulerException e) {
			e.printStackTrace();
			map.put(Const.MSG, Const.DEFAULT_ERROR);
		}
		return map;
	}

	@ResponseBody
	@RequestMapping("/delete")
	public Object delete(String name, String group) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(Const.STATUS, -1);
		try {
			scheduleJobService.delJob(name, group);
			map.put(Const.STATUS, 0);
		} catch (SchedulerException e) {
			e.printStackTrace();
			map.put(Const.MSG, Const.DEFAULT_ERROR);
		}
		return map;
	}

	/**
	 * 修改表达式
	 */

	@ResponseBody
	@RequestMapping("/update")
	public Object update(ScheduleJob scheduleJob) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(Const.STATUS, -1);
		// 验证cron表达式
		boolean f = CronExpression.isValidExpression(scheduleJob
				.getCronExpression());
		if (!f) {
			map.put(Const.MSG, "cron表达式有误，不能被解析！");
			return map;
		}
		try {
			scheduleJobService.modifyTrigger(scheduleJob.getName(),
					scheduleJob.getGroup(), scheduleJob.getCronExpression());
			map.put(Const.STATUS, 0);
		} catch (SchedulerException e) {
			e.printStackTrace();
			map.put(Const.MSG, Const.DEFAULT_ERROR);
		}
		return map;
	}

	/**
	 * 立即运行一次
	 */

	@ResponseBody
	@RequestMapping("/startNow")
	public Object stratNow(String name, String group) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(Const.STATUS, -1);
		try {
			scheduleJobService.startNowJob(name, group);
			map.put(Const.STATUS, 0);
		} catch (SchedulerException e) {
			e.printStackTrace();
			map.put(Const.MSG, Const.DEFAULT_ERROR);
		}
		return map;
	}

	/**
	 * 恢复
	 */

	@ResponseBody
	@RequestMapping("/resume")
	public Object resume(String name, String group) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(Const.STATUS, -1);
		try {
			scheduleJobService.restartJob(name, group);
			map.put(Const.STATUS, 0);
		} catch (SchedulerException e) {
			e.printStackTrace();
			map.put(Const.MSG, Const.DEFAULT_ERROR);
		}
		return map;
	}

	/**
	 * 获取所有trigger
	 */
	public void getTriggers(HttpServletRequest request) {
		List<ScheduleJob> scheduleJobs = scheduleJobService.getTriggersInfo();
		request.setAttribute("triggers", scheduleJobs);
	}

}
