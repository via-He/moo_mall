package com.via.mall.config;

import com.via.mall.filter.AdminFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;

/**
 * @author Qingqing.He
 * @date 2021/1/4 13:39
 * 描述：admin过滤器
 */
@Configuration
public class AdminFilterConfig {
    @Bean
    public AdminFilter adminFilter(){
        return new AdminFilter();
    }

    @Bean(name="adminFilterConf")
    public FilterRegistrationBean adminFilterConfig(){
        FilterRegistrationBean<Filter> filterFilterRegistrationBean =
            new FilterRegistrationBean<>();
        filterFilterRegistrationBean.setFilter(adminFilter());
        filterFilterRegistrationBean.addUrlPatterns("/category/admin/*");
        filterFilterRegistrationBean.addUrlPatterns("/product/admin/*");
        filterFilterRegistrationBean.addUrlPatterns("/order/admin/*");
        filterFilterRegistrationBean.setName("adminFilterConf");
        return filterFilterRegistrationBean;
    }
}
