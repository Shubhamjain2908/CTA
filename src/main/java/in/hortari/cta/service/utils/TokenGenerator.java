package in.hortari.cta.service.utils;

import java.util.Base64;
import static in.hortari.cta.ApplicationConstants.BASE64_UTF;
import in.hortari.cta.exception.BadRequestException;

/**
 * Class which give Token(of Username) for authentication
 * @author SHUBHAM
 * @since 26-04-2018
 */
public class TokenGenerator 
{
	/**
	 * Encoding using 'mime'
	 * @param data
	 * @return
	 */
	public static String mimeEncoding(String data)
    {
        try 
        {
            return Base64.getMimeEncoder().encodeToString(data.getBytes(BASE64_UTF));
        }
        catch (Exception ex) 
        {
        	throw new BadRequestException("String cannot be encrypted :" + ex.getMessage());
        }
    }
	
	/**
	 * Decoding using 'mime'
	 * @param encryptedString
	 * @return
	 */
	public static String mimeDecoding(String encryptedString) 
	{
		try 
		{
			byte[] mi=Base64.getMimeDecoder().decode(encryptedString);
	        return new String(mi,BASE64_UTF);
		}
		catch (Exception ex) 
        {
            throw new BadRequestException("Invalid encrypted string");
        }
	}
}
