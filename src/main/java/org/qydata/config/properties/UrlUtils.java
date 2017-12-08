package org.qydata.config.properties;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class UrlUtils {

   @Autowired
    private Environment env;

   @Bean
    public int readConf(){
       UrlProperties.setUrl(env.getProperty("org.qydata.url"));
       UrlProperties.setBurl(env.getProperty("org.qydata.b.url"));
       UrlProperties.setAuthId(env.getProperty("org.qydata.authId"));
       UrlProperties.setAuthPass(env.getProperty("org.qydata.authPass"));
       return 1;
   }

}
