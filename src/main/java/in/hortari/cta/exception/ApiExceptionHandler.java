package in.hortari.cta.exception;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.transaction.TransactionSystemException;

/**
 * Global exception handler for all APIs
 * 
 * @author SHUBHAM JAIN
 * @since 23-04-2018
 *
 */
@ControllerAdvice 
@RestController
public class ApiExceptionHandler 
{
	
	/**
	 * Method used to handle {@link BadRequestException}
	 * 
	 * @param req
	 * @param ex
	 * @return
	 */
	@ExceptionHandler({ BadRequestException.class })
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ApiErrorResponse handleBadRequestException(HttpServletRequest req, BadRequestException ex) 
	{
		Logger.getLogger(BadRequestException.class.getName()).error(ex.getMessage());;
		ApiErrorResponse response = new ApiErrorResponse();
		response.setMessage(ex.getMessage());
		response.setError(HttpStatus.BAD_REQUEST.name());
		response.setStatus(HttpStatus.BAD_REQUEST.value());
		response.setUrl(req.getRequestURI());
		return response;
	}

	/**
	 * Method used to handle {@link AuthorizationFailedException}
	 * 
	 * @param req
	 * @param ex
	 * @return
	 */
	@ExceptionHandler({ AuthorizationFailedException.class })
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	@ResponseBody
	public ApiErrorResponse handleAuthorizationFailedException(HttpServletRequest req, AuthorizationFailedException ex) 
	{
		Logger.getLogger(AuthorizationFailedException.class.getName()).error(ex.getMessage());;
		ApiErrorResponse response = new ApiErrorResponse();
		response.setMessage(ex.getMessage());
		response.setError(HttpStatus.UNAUTHORIZED.name());
		response.setStatus(HttpStatus.UNAUTHORIZED.value());
		response.setUrl(req.getRequestURI());
		return response;
	}
	
	/**
	 * Method used to handle {@link TransactionSystemException}
	 * @param req
	 * @param ex
	 * @return
	 */
	@ExceptionHandler({ org.springframework.transaction.TransactionSystemException.class })
	@ResponseStatus(HttpStatus.PARTIAL_CONTENT)
	@ResponseBody
	public ApiErrorResponse TransactionSystemException(HttpServletRequest req, org.springframework.transaction.TransactionSystemException ex) 
	{
		Logger.getLogger(org.springframework.transaction.TransactionSystemException.class.getName()).error(ex.getMessage());;
		ApiErrorResponse response = new ApiErrorResponse();
		response.setMessage(ex.getMessage());
		response.setError(HttpStatus.PARTIAL_CONTENT.name());
		response.setStatus(HttpStatus.PARTIAL_CONTENT.value());
		response.setUrl(req.getRequestURI());
		return response;
	}
	
	/**
	 * Method used to handle {@link NullPointerException}
	 * @param req
	 * @param ex
	 * @return
	 */
	@ExceptionHandler({ java.lang.NullPointerException.class })
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ResponseBody
	public ApiErrorResponse NullPointerException(HttpServletRequest req, java.lang.NullPointerException ex) 
	{
		Logger.getLogger(java.lang.NullPointerException.class.getName()).error(ex.getMessage());;
		ApiErrorResponse response = new ApiErrorResponse();
		response.setMessage(ex.getMessage());
		response.setError(HttpStatus.NO_CONTENT.name());
		response.setStatus(HttpStatus.NO_CONTENT.value());
		response.setUrl(req.getRequestURI());
		return response;
	}
	
	/**
	 * Method used to handle {@link MailException}
	 * @param req
	 * @param ex
	 * @return
	 */
	@ExceptionHandler({ MailException.class })
	@ResponseStatus(HttpStatus.NETWORK_AUTHENTICATION_REQUIRED)
	@ResponseBody
	public ApiErrorResponse MailException(HttpServletRequest req, MailException ex) 
	{
		Logger.getLogger(MailException.class.getName()).error(ex.getMessage());;
		ApiErrorResponse response = new ApiErrorResponse();
		response.setMessage(ex.getMessage());
		response.setError(HttpStatus.NETWORK_AUTHENTICATION_REQUIRED.name());
		response.setStatus(HttpStatus.NETWORK_AUTHENTICATION_REQUIRED.value());
		response.setUrl(req.getRequestURI());
		return response;
	}
}
