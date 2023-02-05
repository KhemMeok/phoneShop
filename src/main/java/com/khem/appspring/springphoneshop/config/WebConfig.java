package com.khem.appspring.springphoneshop.config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
@Configuration
public class Webconfig implements WebMvcConfigurer{

	@Value("${application.rest.allowed-origins}")
	private String[] allowedOrgins;
 
	@Override
   public void addCorsMappings(CorsRegistry registry) {
	   registry.addMapping("/**")
	   			.allowedOrigins(allowedOrgins)
	   			.allowedMethods("GET","PUT","POST","DELETE","OPTION")
	   			.allowedHeaders("*");
}
@Bean
    public InternalResourceViewResolver defaultViewResolver() {
        return new InternalResourceViewResolver();
    }
}
