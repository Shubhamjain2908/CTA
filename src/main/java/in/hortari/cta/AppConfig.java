package in.hortari.cta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import in.hortari.cta.interceptor.ApiInterceptor;
import in.hortari.cta.service.AuthorizationManager;


/**
 * Application configuration class
 * 
 * @author SHUBHAM JAIN
 * @since 23-04-2018
 *
 */
@Configuration
@EnableWebMvc
@EnableAsync
public class AppConfig extends WebMvcConfigurerAdapter {

	@Autowired 
    private AuthorizationManager authorizationManager; 

    @Override
    public void addInterceptors(InterceptorRegistry registry) 
    {
        registry.addInterceptor(new ApiInterceptor(authorizationManager))
                .addPathPatterns("/**");
    }
}