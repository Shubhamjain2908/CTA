package in.hortari.cta.service;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.hortari.cta.entity.User;
import in.hortari.cta.exception.AuthorizationFailedException;
import in.hortari.cta.repository.UserRepository;
import in.hortari.cta.service.utils.MailProcessor;
import in.hortari.cta.service.utils.PasswordEncrypter;
import in.hortari.cta.service.utils.TokenGenerator;

import static in.hortari.cta.ApplicationConstants.EMAIL_TEMPLATE;
import static in.hortari.cta.ApplicationConstants.VERIFY_LINK;

/**
 * This is a service layer which generates response
 * 
 * @author SHUBHAM JAIN
 * @since 23-04-2018
 */
@Service
public class UserService 
{
	@Autowired
	private UserRepository userRepository;
	
	/**
	 * Method to save user  
	 * @param user
	 * @return
	 */
	public User createUser(User user) 
	{
		if(null != userRepository.findByUsername(user.getUsername())) 
		{
			throw new AuthorizationFailedException("Username already Exists !!!");
		}
		user.setPassword(PasswordEncrypter.encryptPassword(user.getPassword()));	//Password Encryption
		sendMail(user);
		return userRepository.save(user);
	}
	
	/**
	 * Method to get User 
	 * @param user
	 * @return
	 */
	public User getUser(User user) 
	{
		User u=userRepository.findByUsername(user.getUsername());
		if (null==u) 
		{
			throw new AuthorizationFailedException("No user exists with Username "+ user.getUsername());
		}
		if(u.getUserAuthentication().equals(1) && PasswordEncrypter.checkPassword(user.getPassword(), u.getPassword()))
		{
			return u;
		}
		throw new AuthorizationFailedException("Password does not matched or you are not Authorized to use this service");
		
	}
	
	/**
	 * Method to update user
	 * @param user
	 * @return
	 */
	public User updateUser(User user) 
	{	
		if(!user.getUserAuthentication().equals(1) || !PasswordEncrypter.checkPassword(user.getPassword(), userRepository.findByUsername(user.getUsername()).getPassword()))
		{
			throw new AuthorizationFailedException("Password does not matched or you are not Authorized to use this service");
		}
		user.setPassword(PasswordEncrypter.encryptPassword(user.getPassword()));	//Password Encryption
		return userRepository.save(user);
	}
	
	/**
	 * Method to verify user
	 * @param username
	 * @return
	 */
	public User verifyUser(String username) 
	{
		User u=userRepository.findByUsername(TokenGenerator.mimeDecoding(username));
		if (null==u) 
		{
			throw new AuthorizationFailedException("No user exists with Username "+ username);
		}
		u.setUserAuthentication(1);
		return userRepository.save(u);
	}
	
	/**
	 * Method to send Email
	 * @param user
	 */
	public void sendMail(User user) 
	{
		try 
		{
			MailProcessor mail = new MailProcessor();
			mail.sendMail(user.getEmail(), "Email Verification", "", EMAIL_TEMPLATE.replaceAll("Shubham", StringUtils.capitalize(user.getName().toLowerCase())).replaceAll("VERIFY_API", VERIFY_LINK.replaceAll("UserNameToken", TokenGenerator.mimeEncoding(user.getUsername()))), null);
		}
		catch (Exception e) 
		{
			Logger.getLogger(UserService.class.getName()).error(e.getMessage());
		}
	}
}
