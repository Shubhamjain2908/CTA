package in.hortari.cta.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.hortari.cta.exception.AuthorizationFailedException;
import in.hortari.cta.repository.UserRepository;

/**
 * This layer is used to validate secretKey from DB
 * 
 * @author SHUBHAM JAIN
 * @since 23-04-2018
 *
 */
@Service
public class AuthorizationManager 
{	
	@Autowired
	private UserRepository userRepository;
	
	/**
	 * Method to check valid studentId and secret key 
	 * 
	 * @param secretKey
	 * @return
	 */
	public boolean checkAuthorization(String username) {
		
        //Validate user
        if (null == userRepository.findByUsername(username)) {  
            throw new AuthorizationFailedException("Unauthorized to access the service");
        }
        return true;
    }

}