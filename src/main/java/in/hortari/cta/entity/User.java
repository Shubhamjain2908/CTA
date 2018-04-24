package in.hortari.cta.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

/**
 * Entity class used to map table in DB
 * 
 * @author SHUBHAM JAIN
 * @since 23-04-2018
 *
 */
@Entity
@Table(name = "User")
@Data
@JsonInclude(Include.NON_EMPTY)
public class User 
{
	@Id
	@NotBlank
	@Column(name = "username")
	private String username;
	
	@NotBlank
	@Column(name = "name")
	private String name;
	
	@Column(name = "mobile_no")
	private String mobile;
	
	@NotBlank
	@Pattern(regexp = "^([^ @])+@([^ \\.@]+\\.)+([^ \\.@])+$")
	@Size(max = 250)
	@Column(name = "email")
	private String email;
	
	@NotBlank
	@Column(name = "password")
	private String password;
	
	@Column(name = "DOB")
	private String dob;
	
	@ElementCollection
    @CollectionTable(name = "user_favorite_currency")
    @Column(name = "favorite_currency")
    private Set<String> favCoins = new HashSet<>();
	
	@Column(name = "authentication")
	private boolean userAuthentication;
}

