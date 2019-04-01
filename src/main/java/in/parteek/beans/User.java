/**
 * 
 */
package in.parteek.beans;

import java.util.*;

import javax.persistence.*;

import lombok.*;

/**
 * Created on : 2019-03-28, 8:24:21 a.m.
 *
 * @author Parteek Dheri
 */
@Entity
@Table(name="users")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User {
	@Id
	@Column(name="username", unique=true, nullable=false, length=45)
	private String userName;
	@Column(name="password", nullable=false, length=60)
	private String password;
	@Column(name="enabled", nullable=false)
	private boolean enabled;
	@OneToMany(fetch=FetchType.LAZY, mappedBy="user")
	private Set<UserRole> userRole = new HashSet<UserRole>(0);


	public User(String userName, String password, boolean enabled) {
		this.userName = userName;
		this.password = password;
		this.enabled = enabled;
	}
	 
}
