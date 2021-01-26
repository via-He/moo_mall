package com.via.mall.config;

import com.via.mall.filter.AdminFilter;
import com.via.mall.filter.UserFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;

/**
 * @author Qingqing.He
 * @date 2021/1/4 13:39
 * 描述：User过滤器
 */
@Configuration
public class UserFilterConfig {
    @Bean
    public UserFilter userFilter(){
        return new UserFilter();
    }

    @Bean(name="userFilterConf")
    public FilterRegistrationBean UserFilterConfig(){
        FilterRegistrationBean<Filter> filterFilterRegistrationBean =
            new FilterRegistrationBean<>();
        filterFilterRegistrationBean.setFilter(userFilter());
        filterFilterRegistrationBean.addUrlPatterns("/cart/*");
        filterFilterRegistrationBean.addUrlPatterns("/order/*");
        filterFilterRegistrationBean.setName("userFilterConf");
        return filterFilterRegistrationBean;
    }
}
