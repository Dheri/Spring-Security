/**
 * 
 */
package in.parteek;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import in.parteek.dao.Dao;

/**
 * Created on : 2019-03-19, 3:20:43 p.m.
 *
 * @author Parteek Dheri
 */
@Controller
public class HomeController {

	@GetMapping("/")
	public String goHome(Model model) {
		return "th_index";
	}

	@GetMapping("/user")
	public String goUser(Model model) {
		return "user/th_index";
	}

	@GetMapping("/login")
	public String goLogin(Model model) {
		return "user/th_login";
	}

	@GetMapping("/access-denied")
	public String goError(Model model) {
		return "error/th_access-denied";
	}

	@GetMapping("/home")
	public String gohome(Model model) {
		return "home";
	}

	@GetMapping("/home2")
	public String gohome2(Model model) {
		new Dao().addDummyUser();
		return "user/home";
	}

	@GetMapping("/user/home")
	public String goUserHome(Model model) {
		return "user/home";
	}
}
