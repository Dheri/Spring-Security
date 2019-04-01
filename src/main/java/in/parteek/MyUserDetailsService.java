/**
 * 
 */
package in.parteek;

import java.util.*;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import in.parteek.beans.UserRole;
import in.parteek.dao.Dao;

/**
 * Created on : 2019-03-28, 9:40:46 a.m.
 *
 * @author Parteek Dheri
 */
@Service
public class MyUserDetailsService implements UserDetailsService {

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		in.parteek.beans.User user = new Dao().findUserbyName(username);
		
		List<GrantedAuthority> authorities = buildUserAuthority(user.getUserRole());
		
		return buildUserForAuthentication(user, authorities);
	}

	private User buildUserForAuthentication(in.parteek.beans.User user, List<GrantedAuthority> authorities) {

		return new User(user.getUserName(), user.getPassword(), user.isEnabled(), 
				true, true, true, authorities);

	}

	private List<GrantedAuthority> buildUserAuthority(Set<UserRole> userRoles) {
		Set<GrantedAuthority> grantedAuthorities = new HashSet<GrantedAuthority>();

		for (UserRole role : userRoles) {
			grantedAuthorities.add(new SimpleGrantedAuthority(role.getRole()));
		}

		return new ArrayList<GrantedAuthority>(grantedAuthorities);

	}
}
