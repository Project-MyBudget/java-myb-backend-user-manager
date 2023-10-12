package br.com.mybudget.usermanager.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebCorsConfig implements WebMvcConfigurer {

	@Override
	public void addCorsMappings(CorsRegistry corsRegistry) {
		corsRegistry.addMapping("/**")
					.allowedOrigins("*")
					.allowedMethods("GET", "POST", "DELETE", "PUT")
					.allowedHeaders("*")
					.maxAge(3600);
	}
}
