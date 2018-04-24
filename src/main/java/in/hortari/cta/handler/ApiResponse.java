package in.hortari.cta.handler;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import in.hortari.cta.entity.User;
import lombok.Data;

/**
 * Class used when API return response
 * @author SHUBHAM JAIN
 * @since 24-04-2018
 */
@Data
@JsonInclude(Include.NON_EMPTY)
public class ApiResponse 
{	
	private String status;
	private String code;
	private User user;
	private String message;
}
