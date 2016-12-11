package org.qydata.config;

import com.google.common.collect.Maps;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.apache.shiro.session.mgt.eis.JavaUuidSessionIdGenerator;
import org.apache.shiro.session.mgt.quartz.QuartzSessionValidationScheduler;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.authc.AnonymousFilter;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.qydata.shirorealm.UserRealm;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.web.filter.DelegatingFilterProxy;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {
	/**
	 * 相当于在web.xml中配置的过滤器
	 * FilterRegistrationBean
	 * @return
	 */
	@Bean
	public FilterRegistrationBean filterRegistrationBean() {
		FilterRegistrationBean filterRegistration = new FilterRegistrationBean();
        filterRegistration.setFilter(new DelegatingFilterProxy("shiroFilter"));
        //该参数表示shiro的生命周期将交由Spring容器进行管理（默认情况下，取值为false）
		//如果将其内容设置为true，则表示由Servlet容器进行管理
        filterRegistration.setEnabled(true);
        filterRegistration.addUrlPatterns("/*");
        filterRegistration.setDispatcherTypes(DispatcherType.REQUEST);
        return filterRegistration;
	}

	/**自定义realm
	 * @return
	 */
	@Bean
	@DependsOn(value="lifecycleBeanPostProcessor")
	public UserRealm userRealm() {
		UserRealm userRealm = new UserRealm();
		userRealm.setCacheManager(cacheManager());
		return userRealm;
	}
	/**
	 * 缓存
	 * @return
	 */
	@Bean
	public EhCacheManager cacheManager() {
		EhCacheManager cacheManager = new EhCacheManager();
		cacheManager.setCacheManagerConfigFile("classpath:ehcache.xml");
		return cacheManager;
	}
	/**
	 * 配置Shiro在Spring中的生命周期的控制操作
	 * @return
	 */
	@Bean(name = "lifecycleBeanPostProcessor")
	public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
		return new LifecycleBeanPostProcessor();
	}
	/**
	 * 启动在Shiro里面进行Annotation的相关验证处理操作
	 * @return
	 */
	@Bean
	@DependsOn(value = "lifecycleBeanPostProcessor")
	public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator(){
		DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
		advisorAutoProxyCreator.setProxyTargetClass(true);
		return advisorAutoProxyCreator;
	}
	/**
	 * 配置SecurityManager的管理
	 * @return
	 */
	@Bean(name="securityManager")
	public DefaultWebSecurityManager securityManager() {
		DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
		manager.setRealm(userRealm());
		manager.setCacheManager(cacheManager());
		manager.setSessionManager(sessionManager());
		return manager;
	}

	/**
	 *针对于安全管理实现的AOP处理操作
	 * @return
	 */
	@Bean
	public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(){
		AuthorizationAttributeSourceAdvisor attributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
		attributeSourceAdvisor.setSecurityManager(securityManager());
		return attributeSourceAdvisor;
	}



	/**
	 * 定义Session  ID生成管理器
	 * @return
	 */
	@Bean
	public JavaUuidSessionIdGenerator javaUuidSessionIdGenerator(){
		JavaUuidSessionIdGenerator sessionIdGenerator = new JavaUuidSessionIdGenerator();
		return sessionIdGenerator;
	}


	/**
	 * 配置Session  DAO操作处理
	 * @return
	 */
	@Bean
	public EnterpriseCacheSessionDAO sessionDAO(){
		EnterpriseCacheSessionDAO cacheSessionDAO = new EnterpriseCacheSessionDAO();
		//设置session缓存的名字
		cacheSessionDAO.setActiveSessionsCacheName("qydata-shiro-activeSessionsCacheName");
		//定义该Session  DAO操作中所使用的的ID生成器
		cacheSessionDAO.setSessionIdGenerator(javaUuidSessionIdGenerator());
		return cacheSessionDAO;
	}

	/**
	 * 配置需要向Cookie中保存数据的配置模板
	 * @return
	 */
	@Bean
	public SimpleCookie simpleCookie(){
		SimpleCookie cookie = new SimpleCookie();
		//定义cookie名字
		cookie.setName("qydata-session-id");
		//保证该系统不会受到跨域的操作攻击
		cookie.setHttpOnly(true);
		//定义cookie的过期时间，设置为1，表示浏览器关闭，cookie消失
		cookie.setMaxAge(-1);
		return cookie;
	}


	/**
	 * 配置Session的定时验证检测程序类，以让无效的Session释放
	 * @return
	 */
	@Bean
	public QuartzSessionValidationScheduler quartzSessionValidationScheduler(){
		QuartzSessionValidationScheduler sessionValidationScheduler = new QuartzSessionValidationScheduler();
		//设置Session失效扫描间隔时间，单位毫秒
		sessionValidationScheduler.setSessionValidationInterval(60000);
		//会话管理器的程序类应用
		sessionValidationScheduler.setSessionManager(sessionManager());
		return sessionValidationScheduler;
	}
	/**
	 * session会话管理器
	 * @return
	 */
	@Bean(name="sessionManager")
	public DefaultWebSessionManager sessionManager() {
		DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();

		//定义的是全局的session会话超时时间，此操作会覆盖web中的超市时间配置
		sessionManager.setGlobalSessionTimeout(432000000);

		//删除所有无效的session对象，此时的session被保存在了内存里面
		sessionManager.setDeleteInvalidSessions(true);

		//需要要使用的无效的Session定时调度器
		//sessionManager.setSessionValidationScheduler(quartzSessionValidationScheduler());

		//需要让此session可以使用该定时调度器进行检测
		sessionManager.setSessionValidationSchedulerEnabled(true);

		//定义Session可以进行序列化的工具类
		sessionManager.setSessionDAO(sessionDAO());


		//所有的Session一定要将ID设置到Cookie中去
		sessionManager.setSessionIdCookie(simpleCookie());

		//定义simpleCookie模板可以进行操作的启用
		sessionManager.setSessionIdCookieEnabled(true);

		return sessionManager;
	}



	/**
	 *  配置shiro过滤器
	 * @see ShiroFilterFactoryBean
	 * @return
	 */
	@Bean(name = "shiroFilter")
	public ShiroFilterFactoryBean shiroFilter(){
		ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
		bean.setSecurityManager(securityManager());
		//认证失败之后的跳转路径
		bean.setLoginUrl("/");
		//授权失败之后的跳转路径
		bean.setUnauthorizedUrl("/view/unauthUrl");
		//登录成功之后跳转访问路径
		bean.setSuccessUrl("/view/successUrl");
		Map<String, Filter> filters = Maps.newHashMap();
		filters.put("perms", urlPermissionsFilter());
		filters.put("anon", new AnonymousFilter());
		bean.setFilters(filters);
		Map<String, String> chains = new HashMap<String,String>();
		chains.put("/", "anon");
		chains.put("/view/successUrl", "authc,roles");
		chains.put("/view/unauthUrl", "authc");
		//静态资源
		chains.put("/js/**", "anon");
		chains.put("/css/**", "anon");
		chains.put("/image/**", "anon");
		//出错页面
		chains.put("/error/404", "authc");
		chains.put("/error/500", "authc");
		//客户管理
		chains.put("/customer/addCustomerViewCommon", "authc,perms");
		chains.put("/customer/insertCustomerCommon", "authc,perms");
		chains.put("/customer/findAllCustomerByDeptNo", "authc,perms");
		chains.put("/customer/addCustomerViewSuper", "authc,perms");
		chains.put("/customer/insertCustomerSuper", "authc,perms");
		chains.put("/customer/findAllCustomer", "authc,perms");
		//客户Api
		chains.put("/customerApi/addCustomerApiView/**", "authc,perms");
		chains.put("/customerApi/addCustomerApiAction", "authc,perms");
		chains.put("/customerApi/customerApiListAction/**", "authc,perms");
		chains.put("/customerApi/updateCustomerApiById", "authc,perms");
		chains.put("/customerApi/findCustomerApiById/**", "authc,perms");
		//客户Ip
		chains.put("/customerIp/addCustomerIpView/**", "authc,perms");
		chains.put("/customerIp/addCustomerIpAction", "authc,perms");
		chains.put("/customerIp/customerIpListAction/**", "authc,perms");
		chains.put("/customerIp/deleteIp/**", "authc,perms");

		//余额变更
		chains.put("/customerBalance/customerBalanceChangeView", "authc,perms");
		chains.put("/customerBalance/customerBalanceChangeAction", "authc,perms");
		chains.put("/customerBalance/customerBalanceChangeSuccess", "authc,perms");

		//dept管理
		chains.put("/dept/addDeptView", "authc,perms");
		chains.put("/dept/addDeptAction", "authc,perms");
		chains.put("/dept/findAllDept", "authc,perms");
		chains.put("/dept/allotDeptView/**", "authc,perms");
		chains.put("/dept/allotDeptAction", "authc,perms");

		//角色管理
		chains.put("/role/allotRoleView/**", "authc,perms");
		chains.put("/role/allotRoleAction", "authc,perms");

		//用户管理
		chains.put("/user/addUserViewCommon", "authc,perms");
		chains.put("/user/addUserCommonAction", "authc,perms");
		chains.put("/user/addUserView", "authc,perms");
		chains.put("/user/addUserAction", "authc,perms");
		chains.put("/user/findAllUser", "authc,perms");
		chains.put("/user/findAllUserCommon", "authc,perms");
		chains.put("/user/statusStart/**", "authc,perms");
		chains.put("/user/statusForbid/**", "authc,perms");
		chains.put("/user/resetPassword", "authc,perms");

		//修改密码
		chains.put("/user/updatePasswordView", "authc");
		chains.put("/user/updatePasswordAction", "authc");

		bean.setFilterChainDefinitionMap(chains);
		return bean;
	}

	@Bean
	public URLPermissionsFilter urlPermissionsFilter() {
		return new URLPermissionsFilter();
	}
}