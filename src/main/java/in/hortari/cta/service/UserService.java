package in.hortari.cta.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.hortari.cta.entity.User;
import in.hortari.cta.exception.AuthorizationFailedException;
import in.hortari.cta.repository.UserRepository;
import in.hortari.cta.service.utils.PasswordEncrypter;

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
		user.setUserAuthentication(false);	//initially it will be false
		user.setPassword(PasswordEncrypter.encryptPassword(user.getPassword()));	//Password Encryption
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
		if(!user.isUserAuthentication() || !PasswordEncrypter.checkPassword(user.getPassword(), u.getPassword()))
		{
			throw new AuthorizationFailedException("Password does not matched or you are not Authorized to use this service");
		}
		return u;
	}
	
	/**
	 * Method to update user
	 * @param user
	 * @return
	 */
	public User updateUser(User user) 
	{	
		if(!user.isUserAuthentication() || !PasswordEncrypter.checkPassword(user.getPassword(), userRepository.findByUsername(user.getUsername()).getPassword()))
		{
			throw new AuthorizationFailedException("Password does not matched or you are not Authorized to use this service");
		}
		user.setPassword(PasswordEncrypter.encryptPassword(user.getPassword()));	//Password Encryption
		return userRepository.save(user);
	}
}
