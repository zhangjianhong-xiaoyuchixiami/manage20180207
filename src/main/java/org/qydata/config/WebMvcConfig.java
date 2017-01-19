package org.qydata.config;

import freemarker.template.TemplateException;
import org.qydata.freemarker.ShiroTagFreeMarkerConfigurer;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.servlet.ErrorPage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.ui.freemarker.FreeMarkerConfigurationFactory;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

import java.io.IOException;

/**
 * Created by Administrator on 2016/11/20.
 */

@Configuration

public class WebMvcConfig extends WebMvcConfigurerAdapter implements EmbeddedServletContainerCustomizer {

    @Bean
    public ShiroTagFreeMarkerConfigurer shiroTagFreeMarkerConfigurer(){
        ShiroTagFreeMarkerConfigurer bean =new ShiroTagFreeMarkerConfigurer();
        FreeMarkerConfigurationFactory factory  = new FreeMarkerConfigurationFactory();
        factory.setTemplateLoaderPaths("classpath:templates", "src/main/resource/templates");
        factory.setDefaultEncoding("UTF-8");
        try {
            bean.setConfiguration(factory.createConfiguration());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        }
        return bean;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
    }

    @Override
    public void customize(ConfigurableEmbeddedServletContainer configurableEmbeddedServletContainer) {
        configurableEmbeddedServletContainer.setSessionTimeout(432000000);
        configurableEmbeddedServletContainer.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND,"/error/404"));
        configurableEmbeddedServletContainer.addErrorPages(new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR,"/error/500"));
        configurableEmbeddedServletContainer.addErrorPages(new ErrorPage(HttpStatus.BAD_REQUEST,"/error/400"));
    }

    @Bean
    public ViewResolver viewResolver() {
        FreeMarkerViewResolver resolver = new FreeMarkerViewResolver();
        resolver.setCache(true);
        resolver.setPrefix("");
        resolver.setSuffix(".ftl");
        resolver.setContentType("text/html; charset=UTF-8");
        return resolver;
    }


}
