package com.yang.spinach.frame.shiro;

import java.util.Set;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yang.spinach.account.entity.Account;
import com.yang.spinach.account.service.AccountService;

/**
 * 用户认证
 * 
 * @author yang
 *
 */
public class ShiroAccountRealm extends AuthorizingRealm {
	public Logger logger = LoggerFactory.getLogger(getClass());

	@Resource
	private CacheManager shiroCacheManager;
	@Resource
	private AccountService accountService;

	public static final String HASH_ALGORITHM = "MD5";
	public static final int HASH_INTERATIONS = 1;

	// 授权
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principalCollection) {
		// 因为非正常退出，即没有显式调用 SecurityUtils.getSubject().logout()
		// (可能是关闭浏览器，或超时)，但此时缓存依旧存在(principals)，所以会自己跑到授权方法里。
		if (!SecurityUtils.getSubject().isAuthenticated()) {
			doClearCache(principalCollection);
			SecurityUtils.getSubject().logout();
			return null;
		}
		String username = (String) principalCollection.getPrimaryPrincipal();
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		Account account = null;
		Long type = Long.valueOf((String) ShiroSessionUtils
				.getAttribute("type"));
		account = accountService.selectByUsername(username, type);
		// 获取权限信息
		if (null != account) {
			Set<String> permissions = accountService.findPermissions(username,
					type);
			if (permissions != null && permissions.size() > 0) {
				authorizationInfo.addStringPermissions(permissions);
			}
			return authorizationInfo;
		} else {
			throw new AuthorizationException();
		}
	}

	// 认证
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
		UsernamePasswordToken upToken = (UsernamePasswordToken) token;
		String username = upToken.getUsername();
		// 根据用户名查找用户
		// Account account =
		// accountService.findAccountByUsername(token2.getUsername());
		Account account = null;
		Long type = Long.valueOf((String) ShiroSessionUtils
				.getAttribute("type"));
		account = accountService.selectByUsername(username, type);
		if (null != account) {
			AuthenticationInfo authcInfo = new SimpleAuthenticationInfo(
					username, account.getPassword(), this.getName());
			ShiroSessionUtils.setAsLogin(account);

			/**
			 * 关闭浏览器，再打开后，虽然授权缓存了，但是认证是必须的，在认证成功后，清除之前的缓存。
			 */
			clearCache(authcInfo.getPrincipals());

			return authcInfo;
		} else {
			// 认证没有通过
			throw new UnknownAccountException();// 没帐号
		}
	}

}
