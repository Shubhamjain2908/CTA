package in.hortari.cta.exception;

/**
 * Class to handle BadRequestException
 * 
 * @author SHUBHAM
 * @since 23-04-2018
 */
public class BadRequestException extends RuntimeException
{
	private static final long serialVersionUID = 758342286647924480L;
	
	public BadRequestException(String message) 
	{
		super(message);
	}	
}
