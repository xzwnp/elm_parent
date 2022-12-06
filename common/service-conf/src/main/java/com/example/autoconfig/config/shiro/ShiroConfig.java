package com.example.autoconfig.config.shiro;

import org.apache.shiro.authz.Authorizer;
import org.apache.shiro.authz.ModularRealmAuthorizer;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.apache.shiro.mgt.SecurityManager;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * com.example.shirojwtdemo.config
 *
 * @author xiaozhiwei
 * 2022/11/28
 * 14:57
 */
@Configuration
public class ShiroConfig {
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
        ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();

        /*
         * 配置filter,相应配置规则参考官网
         * http://shiro.apache.org/web.html#urls-
         * 默认过滤器对照表
         * https://shiro.apache.org/web.html#default-filters
         */

        Map<String, String> filterRuleMap = new HashMap<>();

        filterRuleMap.put("/static/*", "anon");
        filterRuleMap.put("/error", "anon");
        filterRuleMap.put("/user/register", "anon");
        filterRuleMap.put("/user/login/**", "anon");
        //↑配置不参与验证的映射路径。


        // 关键：配置jwt验证过滤器。原来的authc全部改成这个
        //↓ 此处即为shiro1.8新增的默认过滤器：authcBearer-BearerHttpAuthenticationFilter。jwt验证的很多操作都由该filter自动完成，以致我们只需理解其机制而无需亲手实现。
//        filterRuleMap.put("/**/admin/**", "authcBearer");

        filterRuleMap.put("/**", "anon");


        //关键：全局配置NoSessionCreationFilter，把整个项目切换成无状态服务。
        factoryBean.setGlobalFilters(Arrays.asList("noSessionCreation"));

        factoryBean.setSecurityManager(securityManager);
        factoryBean.setFilterChainDefinitionMap(filterRuleMap);

        return factoryBean;
    }

    @Bean
    protected Authorizer authorizer() {
        return new ModularRealmAuthorizer();
    }

}
