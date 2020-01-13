package com.zhiyou100.hospital.config;

import com.zhiyou100.hospital.security.ShiroRealm;
import net.sf.ehcache.CacheException;
import net.sf.ehcache.CacheManager;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.io.ResourceUtils;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * ClassName: ShiroConfig
 */
@Configuration
@EnableTransactionManagement
public class ShiroConfig {

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager){

        ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();
        System.out.println("-------------shiro拦截器工厂类开始注入-----------");
        factoryBean.setSecurityManager(securityManager);
        //指定要求登录时的链接
        factoryBean.setLoginUrl("/go");
        //指定登陆成功的链接
        factoryBean.setSuccessUrl("/index");
        //指定未授权时的页面
        factoryBean.setUnauthorizedUrl("/templates/error");
        //拦截器
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        // 配置退出过滤器,具体的退出代码Shiro已经实现
        filterChainDefinitionMap.put("/logout", "logout");
        // 配置记住我或认证通过可以访问的地址
        filterChainDefinitionMap.put("/user/userList", "user");
        filterChainDefinitionMap.put("/", "user");
        // // 配置不会被拦截的链接 从上向下顺序判断  anon的不需要拦截
        filterChainDefinitionMap.put("/login", "anon");
        filterChainDefinitionMap.put("/roles", "anon");
        filterChainDefinitionMap.put("/Css/*", "anon");
        filterChainDefinitionMap.put("/Js/*", "anon");
        filterChainDefinitionMap.put("/Js/*/*", "anon");
        filterChainDefinitionMap.put("/Js/*/*/*", "anon");
        filterChainDefinitionMap.put("/Images/*/**", "anon");
        filterChainDefinitionMap.put("/layui/*", "anon");
        filterChainDefinitionMap.put("/layui/*/**", "anon");
        filterChainDefinitionMap.put("/treegrid/*", "anon");
        filterChainDefinitionMap.put("/treegrid/*/*", "anon");
        filterChainDefinitionMap.put("/fragments/*", "anon");
        filterChainDefinitionMap.put("/layout", "anon");

        filterChainDefinitionMap.put("/user/sendMsg", "anon");
        filterChainDefinitionMap.put("/user/login", "anon");
        filterChainDefinitionMap.put("/home", "anon");
        // /*filterChainDefinitionMap.put("/page", "anon");
        // filterChainDefinitionMap.put("/channel/record", "anon");*/
        //
        // //add操作，该用户必须有【addOperation】权限
        // filterChainDefinitionMap.put("/user/setUser", "roles[superman]");
        filterChainDefinitionMap
                .put("/user/delUser", "authc,perms[usermanage]");
        //
        // // <!-- authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问【放行】-->
        filterChainDefinitionMap.put("/*", "authc");
        filterChainDefinitionMap.put("/*/*", "authc");
        filterChainDefinitionMap.put("/*/*/*", "authc");
        filterChainDefinitionMap.put("/*/*/*/**", "authc");
        factoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        System.out.println("-----------Shiro拦截器注入成功---------------");
        return factoryBean;
    }


    /**
     *shiro安全管理器设置realm认证和ehcache缓存管理
     * @return
     */
    @Bean
    public SecurityManager securityManager(){
        //创建DefaultWebSecurityManager对象,有个对象叫DefaultSecurityManager
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        // //注入ehcache缓存管理器;
        securityManager.setCacheManager(ehCacheManager());
        // 注入Cookie记住我管理器
        securityManager.setRememberMeManager(rememberMeManager());
        //设置realm
        securityManager.setRealm(shiroRealm());
        return securityManager;
    }


    @Bean
    public ShiroRealm shiroRealm(){
        return new ShiroRealm();
    }
    /**
     * ehcache缓存管理器；shiro整合ehcache：
     * 通过安全管理器：securityManager
     * 单例的cache防止热部署重启失败
     * @return EhCacheManager
     */
    @Bean
    public EhCacheManager ehCacheManager() {
        EhCacheManager ehcache = new EhCacheManager();
        CacheManager cacheManager = CacheManager.getCacheManager("es");
        if (cacheManager == null) {
            try {

                cacheManager = CacheManager.create(ResourceUtils
                        .getInputStreamForPath("classpath:config/ehcache.xml"));

            } catch (CacheException | IOException e) {
                e.printStackTrace();
            }
        }
        ehcache.setCacheManager(cacheManager);
        return ehcache;
    }
    /**
     * 配置cookie记住我管理器
     * @return
     */
    @Bean
    public CookieRememberMeManager rememberMeManager() {
        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
        cookieRememberMeManager.setCookie(remeberMeCookie());
        return cookieRememberMeManager;
    }
    /**
     * 设置记住我cookie过期时间
     * @return
     */
    @Bean
    public SimpleCookie remeberMeCookie() {
        // cookie名称;对应前端的checkbox的name = rememberMe
        SimpleCookie scookie = new SimpleCookie("rememberMe");
        // 记住我cookie生效时间30天 ,单位秒 [1小时]
        scookie.setMaxAge(3600);
        return scookie;
    }
    /**
     * 开启Shiro的注解(如@RequiresRoles,@RequiresPermissions),需借助SpringAOP扫描使用Shiro注解的类,并在必要时进行安全逻辑验证
     * 配置以下两个bean(DefaultAdvisorAutoProxyCreator(可选)和AuthorizationAttributeSourceAdvisor)即可实现此功能
     * @return
     */
    @Bean
    public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator(){
        DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        advisorAutoProxyCreator.setProxyTargetClass(true);
        return advisorAutoProxyCreator;
    }
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(){
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager());
        return authorizationAttributeSourceAdvisor;
    }

}
