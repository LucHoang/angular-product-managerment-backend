package com.company;

import com.company.fomatter.CategoryFormatter;
import com.company.service.ICategoryService;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class WebConfig implements WebMvcConfigurer, ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addFormatter(new CategoryFormatter(applicationContext.getBean(ICategoryService.class)));
    }
}
