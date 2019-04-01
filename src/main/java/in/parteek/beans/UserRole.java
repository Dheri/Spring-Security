/**
 * 
 */
package in.parteek.beans;

import java.util.Set;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created on : 2019-03-28, 8:40:37 a.m.
 *
 * @author Parteek Dheri
 */
@Entity
@Table(name="user_role", uniqueConstraints=@UniqueConstraint(columnNames= {"role","username"}))
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserRole {
	@Id
	@GeneratedValue(strategy=javax.persistence.GenerationType.IDENTITY)
	@Column(name="user_role_id", unique=true, nullable=false)
	private int role_id;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="username", nullable=true)
	private User user;
	
	@Column(name="role", nullable=false, length=45)
	private String role;
 
	public UserRole(User user, String role) {
		this.user = user;
		this.role = role;
	}
	
}
