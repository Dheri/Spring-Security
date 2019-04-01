/**
 * 
 */
package in.parteek;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

/**
 * Created on : 2019-03-26, 3:21:34 p.m.
 *
 * @author Parteek Dheri
 */
@Component
public class LoginAccessDenied implements AccessDeniedHandler {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.security.web.access.AccessDeniedHandler#handle(javax.
	 * servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse,
	 * org.springframework.security.access.AccessDeniedException)
	 */
	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
			AccessDeniedException accessDeniedException) throws IOException, ServletException {
		// TODO Auto-generated method stub
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		if (auth != null) {
			System.out.println(auth.getName() + "was trying to access protected resource" + request.getRequestURI());

		}
		response.sendRedirect(request.getContextPath() + "/access-denied");

	}

}
