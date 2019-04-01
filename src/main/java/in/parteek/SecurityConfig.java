/**
 * 
 */
package in.parteek;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.AntPathMatcher;

/**
 * Created on : 2019-03-19, 4:10:15 p.m.
 *
 * @author Parteek Dheri
 */
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	@Autowired
	private LoginAccessDenied ladh;
	
	@Autowired
	private MyUserDetailsService userDetailsSVC;
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
		
	}
	@Override
	public void configure(AuthenticationManagerBuilder auth) 
			throws Exception {
		/*
		
		auth.inMemoryAuthentication().passwordEncoder(NoOpPasswordEncoder.getInstance())
		.withUser("parteek").password("root").roles("USER")
		.and()
		.withUser("strange").password("root").roles("MANAGER");
		*/
		
		auth.userDetailsService(userDetailsSVC).passwordEncoder(passwordEncoder());
	}
	
	@Override
	public void configure(HttpSecurity http) 
			throws Exception {
		http.authorizeRequests()
			.antMatchers("/user/**").hasRole("USER")
			.antMatchers("/", "/js/**", "/css/**","/img/**", "/**").permitAll()
				.anyRequest().authenticated()
		.and()
			.formLogin().loginPage("/login").permitAll()
		.and()
			.logout().invalidateHttpSession(true).clearAuthentication(true)
			.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
			.logoutSuccessUrl("/login?logut").permitAll()
		.and().exceptionHandling().
			accessDeniedHandler(ladh);
	}
}
