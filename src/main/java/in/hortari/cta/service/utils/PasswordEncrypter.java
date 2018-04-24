package in.hortari.cta.service.utils;

import org.springframework.security.crypto.bcrypt.BCrypt;

/**
 * Class to encrypt the users Password
 * 
 * @author SHUBHAM
 * @since 23-04-2018
 */
public class PasswordEncrypter 
{
	/**
	 * Method to encrypt password using BCrypt Encryption Algorithm
	 * @param password
	 * @return
	 */
	public static String encryptPassword(String password) 
	{
		return BCrypt.hashpw(password, BCrypt.gensalt(12));
	}
	
	/**
	 * Method to check the password
	 * @param plainText
	 * @param hash
	 * @return
	 */
	public static boolean checkPassword(String plainText, String hash) 
	{
		return BCrypt.checkpw(plainText, hash);
	} 
}
