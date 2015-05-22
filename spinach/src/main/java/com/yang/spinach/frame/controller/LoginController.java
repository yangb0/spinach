package com.yang.spinach.frame.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yang.spinach.account.entity.Account;
import com.yang.spinach.frame.filter.WebContext;
import com.yang.spinach.frame.shiro.ShiroSessionUtils;
import com.yang.spinach.frame.utils.CaptchaUtils;
import com.yang.spinach.frame.utils.Const;

@Controller
public class LoginController {
	private Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * 登陆页的映射
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/")
	public String login(Model model) throws Exception {
		return "/login";
	}

	/**
	 * 首页
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/index")
	public String index(Model model) throws Exception {
		return "/index";
	}

	// @RequestMapping(value = "/login", method = RequestMethod.GET)
	// public String login(Model model, HttpServletRequest request)
	// throws Exception {
	// if (SecurityUtils.getSubject().isAuthenticated()) {
	// return "redirect:/index";
	// }
	// return "/login";
	// }

	@ResponseBody
	@RequestMapping(value = "/login.json")
	public Object loginPost(Account account, Model model) throws Exception {
		// type 类型用来标注会员类型,0表示普通会员
		ShiroSessionUtils.setAttribute("type", "0");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status", -1);
		// 判断验证码
		/*
		 * String code = (String)
		 * ShiroSessionUtils.getAttribute(Const.DEFAULT_CAPTCHA_PARAM); if
		 * (StringUtils.isNotBlank(code)) { code =
		 * code.toLowerCase().toString(); } String submitCode =
		 * WebUtils.getCleanParam(request, Const.DEFAULT_CAPTCHA_PARAM); if
		 * (StringUtils.isNotBlank(submitCode)) { submitCode =
		 * submitCode.toLowerCase().toString(); } if
		 * (StringUtils.isBlank(submitCode) || StringUtils.isBlank(code) ||
		 * !code.equals(submitCode)) { model.addAttribute("message", "验证码错误!");
		 * return "/login"; }
		 */

		UsernamePasswordToken token = new UsernamePasswordToken(
				account.getUsername(), account.getPassword());

		HttpServletRequest request = WebContext.currentRequest();
		String rememberme = request.getParameter("rememberme");
		if (StringUtils.isNotBlank(rememberme)) {
			token.setRememberMe(true);
		} else {
			token.setRememberMe(false);
		}

		try {
			Subject subject = SecurityUtils.getSubject();
			subject.login(token);
			if (subject.isAuthenticated()) {
				Account a = ShiroSessionUtils.getLoginAccount();
				map.put("status", 0);
				map.put("data", a);
				map.put("msg", "登陆成功");
				return map;
				// model.addAttribute("user", map);
			}
		} catch (UnknownAccountException uae) {
			map.put("msg", "账号不存在!");
		} catch (IncorrectCredentialsException ice) {
			// 密码不正确
			int num = (Integer) ShiroSessionUtils.getAttribute("loginNum");
			token.clear();
			map.put("msg", "用户名或密码错误,你还可以输入" + (5 - num) + "次");
		} catch (ExcessiveAttemptsException eae) {
			// 输入用户名或密码错误次数过多
			ShiroSessionUtils.setAsLogout();
			token.clear();
			map.put("msg", "输入用户名密码或次数过多,账户已被锁定,半小时后解锁");
		} catch (LockedAccountException lae) {
			map.put("msg", "账号被锁定!");
		} catch (Exception e) {
			map.put("msg", "未知错误,请联系管理员.");
		}
		return map;
	}

	/**
	 * 没有权限
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/unauth")
	public String unauth(Model model) throws Exception {
		if (SecurityUtils.getSubject().isAuthenticated() == false) {
			return "redirect:/";
		}
		return "/unauth";

	}

	/**
	 * 退出
	 * 
	 * @param request
	 */
	@RequestMapping(value = "/logout")
	public void logout(HttpServletRequest request) {
		Subject subject = SecurityUtils.getSubject();
		if (subject != null) {
			subject.logout();
		}
		// request.getSession().invalidate();
	}

	/**
	 * 生成验证码
	 * 
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/getCaptcha")
	public void getCaptcha(HttpServletResponse response) throws IOException {

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.IMAGE_JPEG);

		CaptchaUtils tool = new CaptchaUtils();
		StringBuffer code = new StringBuffer();
		BufferedImage image = tool.genRandomCodeImage(code);
		ShiroSessionUtils.removeAttribute(Const.DEFAULT_CAPTCHA_PARAM);
		ShiroSessionUtils.setAttribute(Const.DEFAULT_CAPTCHA_PARAM,
				code.toString());

		// 将内存中的图片通过流动形式输出到客户端
		ImageIO.write(image, "JPEG", response.getOutputStream());
		return;
	}

	/**
	 * 公共下载方法
	 * 
	 * @param response
	 * @param file
	 *            下载的文件
	 * @param fileName
	 *            下载时显示的文件名
	 * @return
	 * @throws Exception
	 */
	public HttpServletResponse downFile(HttpServletResponse response,
			File file, String fileName, boolean delFile) throws Exception {
		response.setContentType("application/x-download");
		response.setHeader("Pragma", "public");
		response.setHeader("Cache-Control",
				"must-revalidate, post-check=0, pre-check=0");
		OutputStream out = null;
		InputStream in = null;
		// 下面一步不可少
		fileName = new String(fileName.getBytes("GBK"), "ISO-8859-1");
		response.addHeader("Content-disposition", "attachment;filename="
				+ fileName);// 设定输出文件头

		try {
			out = response.getOutputStream();
			in = new FileInputStream(file);
			int len = in.available();
			byte[] b = new byte[len];
			in.read(b);
			out.write(b);
			out.flush();

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new Exception("下载失败!");
		} finally {
			if (in != null) {
				in.close();
			}
			if (out != null) {
				out.close();
			}
			if (delFile) {
				file.delete();
			}
		}

		return response;
	}
}
