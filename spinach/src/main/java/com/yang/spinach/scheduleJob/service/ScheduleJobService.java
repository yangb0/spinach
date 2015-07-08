package com.yang.spinach.scheduleJob.service;

import java.util.List;

import org.quartz.JobDetail;
import org.quartz.SchedulerException;

import com.yang.spinach.scheduleJob.entity.ScheduleJob;

/**
 * 定时任务 service
 * 
 * @author ty
 * @date 2015年1月13日
 */
public interface ScheduleJobService {

	/**
	 * 添加定时任务
	 * 
	 * @param ScheduleJob
	 */
	public void add(ScheduleJob scheduleJob) throws ClassNotFoundException,
			SchedulerException;

	/**
	 * 获取所有JobDetail
	 * 
	 * @return 结果集合
	 */
	public List<JobDetail> getJobs();

	/**
	 * 获取所有计划中的任务
	 * 
	 * @return 结果集合
	 */
	public List<ScheduleJob> getAllScheduleJob();

	/**
	 * 获取所有运行中的任务
	 * 
	 * @return 结果集合
	 */
	public List<ScheduleJob> getAllRuningScheduleJob();

	/**
	 * 获取所有的触发器
	 * 
	 * @return 结果集合
	 */
	public List<ScheduleJob> getTriggersInfo();

	/**
	 * 暂停任务
	 * 
	 * @param name
	 *            任务名
	 * @param group
	 *            任务组
	 */
	public void stopJob(String name, String group) throws SchedulerException;

	/**
	 * 恢复任务
	 * 
	 * @param name
	 *            任务名
	 * @param group
	 *            任务组
	 */
	public void restartJob(String name, String group) throws SchedulerException;

	/**
	 * 立马执行一次任务
	 * 
	 * @param name
	 *            任务名
	 * @param group
	 *            任务组
	 */
	public void startNowJob(String name, String group)
			throws SchedulerException;

	/**
	 * 删除任务
	 * 
	 * @param name
	 *            任务名
	 * @param group
	 *            任务组
	 */
	public void delJob(String name, String group) throws SchedulerException;

	/**
	 * 修改触发器时间
	 * 
	 * @param name
	 *            任务名
	 * @param group
	 *            任务组
	 * @param cron
	 *            cron表达式
	 */
	public void modifyTrigger(String name, String group, String cron)throws SchedulerException ;

	/**
	 * 暂停调度器
	 */
	public void stopScheduler()throws SchedulerException ;
}
