package com.example.authenusser.config;

import com.example.authenusser.filter.AuthorizationFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {
    @Bean
    public FilterRegistrationBean<AuthorizationFilter> filterRegistrationBean() {
        FilterRegistrationBean < AuthorizationFilter > registrationBean = new FilterRegistrationBean();
        AuthorizationFilter authorizationFilter = new AuthorizationFilter();

        registrationBean.setFilter(authorizationFilter);
        registrationBean.addUrlPatterns("/filterExample/*");
        registrationBean.setOrder(2); //set precedence
        return registrationBean;
    }

}
