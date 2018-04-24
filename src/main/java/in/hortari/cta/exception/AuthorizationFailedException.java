package in.hortari.cta.exception;

/**
 * Class to handle AuthorizationFailedException
 * 
 * @author SHUBHAM
 * @since 23-04-2018
 */
public class AuthorizationFailedException extends RuntimeException 
{
	private static final long serialVersionUID = 1L;

	public AuthorizationFailedException(String message) 
	{
		super(message);
	}
}