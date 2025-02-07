package com.vlms.stock;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableCaching
public class VLMSStockApiApplication{
    public static void main(String[] args) {
        SpringApplication.run(VLMSStockApiApplication.class,args);
    }

    @Bean
    public FilterRegistrationBean<RateLimitingFilter> rateLimitingFilterRegistration(){
        FilterRegistrationBean<RateLimitingFilter> registrationBean = new FilterRegistrationBean<>();

        registrationBean.setFilter(new RateLimitingFilter());
        registrationBean.addUrlPatterns("/products/get/all/*");
        return registrationBean;
    }

}
