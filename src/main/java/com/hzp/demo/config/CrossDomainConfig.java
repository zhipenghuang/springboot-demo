package com.hzp.demo.config;

import lombok.Data;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
public class CrossDomainConfig {

    @Bean
    public FilterRegistrationBean setFilter() {

        FilterRegistrationBean filterBean = new FilterRegistrationBean();
        filterBean.setFilter(new CrossDomainFilter());
        filterBean.setName("CrossDomainFilter");
        filterBean.addUrlPatterns("/*");
        return filterBean;
    }
}
