package in.hortari.cta.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import in.hortari.cta.entity.User;

/**
 * Repository for student entity
 * 
 * @author SHUBHAM JAIN
 * @since 23-04-2018
 *
 */
public interface UserRepository extends JpaRepository<User, String>
{	
	public User findByUsername(String username);
}