package in.hortari.cta.interceptor;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.AllArgsConstructor;

import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import in.hortari.cta.exception.AuthorizationFailedException;
import in.hortari.cta.service.AuthorizationManager;

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
	
	private AuthorizationManager authManager;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

//		if (request.getMethod().equals(RequestMethod.POST.name())) {
//			return true;
//		}
//		
//		if (request.getMethod().equals(RequestMethod.PUT.name())) {
//			return true;
//		}
//		@SuppressWarnings("unchecked")
//		Map<String, String> queryParams = (Map<String, String>) request.getParameterNames();
//		String token = queryParams.get("token");
		
//		if (request.getMethod().equals(RequestMethod.GET.name())) {
//			return true;
//		}
		
//		@SuppressWarnings("unchecked")
//		Map<String, String> pathVariables = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		
//		String username = pathVariables.get("username");
//		String secretKey = pathVariables.get("password");
//		
//		if (StringUtils.isEmpty(secretKey)) {
//            throw new AuthorizationFailedException("Unauthorized to access the service");
//		}
//				
//		if (StringUtils.isEmpty(username)) {
//			throw new AuthorizationFailedException("Unauthorized to access the service");
//		}
//		
//		return authManager.checkAuthorization(token); 
		return true;
	}

}