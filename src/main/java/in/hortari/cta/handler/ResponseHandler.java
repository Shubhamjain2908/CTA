package in.hortari.cta.handler;


import org.springframework.core.MethodParameter;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import in.hortari.cta.controller.UserController;
import in.hortari.cta.entity.User;
import in.hortari.cta.exception.ApiErrorResponse;

/**
 * Global response handler for all APIs
 * 
 * @author SHUBHAM JAIN
 * @since 24-04-2018
 *
 */
@ControllerAdvice(basePackageClasses=UserController.class)
public class ResponseHandler implements ResponseBodyAdvice<Object> 
{
	/**
	 * 
	 * @param methodParameter
	 * @param request
	 * @return
	 */
	@Override
	public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> request) 
	{
		return true;
	}
	
	/**
	 * 
	 * @param body
	 * @param returnType
	 * @param selectedContentType
	 * @param selectedConverterType
	 * @param request
	 * @param response
	 * @return
	 */
	@Override
	public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
			Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request,
			ServerHttpResponse response) 
	{
		if (body instanceof ApiErrorResponse || body instanceof Exception) 
		{
			return body;
		}

		ServletServerHttpRequest httpRequest = (ServletServerHttpRequest) request;
		ServletServerHttpResponse httpResponse = (ServletServerHttpResponse) response;

		ApiResponse apiResponse = new ApiResponse();
		
		int statusCode = httpResponse.getServletResponse().getStatus();
		apiResponse.setCode(String.valueOf(statusCode));
		apiResponse.setStatus(HttpStatus.valueOf(statusCode).name());

		if (httpRequest.getMethod().equals(HttpMethod.POST) && body instanceof User) 
		{
			apiResponse.setMessage("User have been successfully created : "
					+ "& an email has been sent to your register email address to verify yoir account");
			apiResponse.setUser((User) body);
		}
		
		if (httpRequest.getMethod().equals(HttpMethod.PUT) && body instanceof User) 
		{
			apiResponse.setMessage("User have been successfully updated");
			apiResponse.setUser((User) body);
		}
		
		if (httpRequest.getMethod().equals(HttpMethod.GET) && body instanceof User) 
		{
			apiResponse.setMessage("User have been successfully found");
			apiResponse.setUser((User) body);
		}
		
		return apiResponse;
	}

}