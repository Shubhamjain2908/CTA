package in.hortari.cta.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.AllArgsConstructor;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * This layer is used to intercept requests coming from client and 
 * send them to the server if they are valid. 
 * 
 * @author SHUBHAM JAIN
 * @since 23-04-2018
 *
 */
@AllArgsConstructor
public class ApiInterceptor extends HandlerInterceptorAdapter 
{
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		return true;
	}

}