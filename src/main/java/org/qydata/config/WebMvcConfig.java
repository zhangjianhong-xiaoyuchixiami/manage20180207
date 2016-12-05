package org.qydata.config;

import org.qydata.freemarker.ShiroTagFreeMarkerConfigurer;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.servlet.ErrorPage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/11/20.
 */

@Configuration

public class WebMvcConfig extends WebMvcConfigurerAdapter implements EmbeddedServletContainerCustomizer {

   @Bean
    public ShiroTagFreeMarkerConfigurer shiroTagFreeMarkerConfigurer(){
       ShiroTagFreeMarkerConfigurer bean =new ShiroTagFreeMarkerConfigurer();
       bean.setTemplateLoaderPath("classpath:/templates");
       Map<String,Object> map = new HashMap<String,Object>();
       map.put("defaultEncoding","UTF-8");
       map.put("classic_compatible","true");
       bean.setFreemarkerVariables(map);
       return bean;
   }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
    }

    @Override
    public void customize(ConfigurableEmbeddedServletContainer configurableEmbeddedServletContainer) {
        configurableEmbeddedServletContainer.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND,"/error/404"));
        configurableEmbeddedServletContainer.addErrorPages(new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR,"/error/500"));
        configurableEmbeddedServletContainer.addErrorPages(new ErrorPage(HttpStatus.BAD_REQUEST,"/error/400"));
    }


}
