package hello.thymeleaf.basic;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpSession;

import java.time.LocalDateTime;
import java.util.*;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.Data;

@Controller
@RequestMapping("/basic")
public class BasicController {
	//기본
	@GetMapping("/text-basic")
	public String textBasic(Model model) {
		model.addAttribute("data", "Hello Spring!");
		return "basic/text-basic";
	}

	//unescaped
	@GetMapping("/text-unescaped")
	public String textUnescaped(Model model) {
		model.addAttribute("data", "Hello <b>Spring!</b>");
		return "basic/text-unescaped";
	}

	//객체사용
	@GetMapping("/variable")
	public String variable(Model model) {
		User userA = new User("userA", 10);
		User userB = new User("userB", 20);

		List<User> list = new ArrayList<>();
		list.add(userA);
		list.add(userB);

		Map<String, User> map = new HashMap<>();
		map.put("userA", userA);
		map.put("userB", userB);

		model.addAttribute("user", userA);
		model.addAttribute("users", list);
		model.addAttribute("userMap", map);

		return "basic/variable";
	}

	@Data
	static class User {

		private String username;
		private int age;

		public User(String username, int age) {
			this.username = username;
			this.age = age;
		}

	}

	//session사용하기
	@GetMapping("/basic-objects")
	public String textObject(HttpSession session) {

		session.setAttribute("sessionData", "Hello Session");

		return "basic/basic-objects";
	}

	@Component("helloBean")
	static class HelloBean {
		public String hello(String data) {
			return "Hello" + data;
		}
	}

	//LocalDateTime사용하기
	@GetMapping("/date")
	public String date(Model model) {
		model.addAttribute("localDateTime", LocalDateTime.now());
		return "basic/date";
	}

	//URL링크
	@GetMapping("/link")
	public String link(Model model) {
		model.addAttribute("param1", "data1");
		model.addAttribute("param2", "data2");

		return "basic/link";
	}

	//리터널
	@GetMapping("/literal")
	public String literal(Model model) {
		model.addAttribute("data", "Spring!");
		return "basic/literal";
	}

	//연산
	@GetMapping("/operation")
	public String operation(Model model) {
		model.addAttribute("nullData", null);
		model.addAttribute("data", "무얼까용~");
		return "basic/operation";
	}

	//Attribute
	@GetMapping("/attribute")
	public String attribute(Model model) {
		
		return "basic/attribute";
	}
	
	//each 반복문
	@GetMapping("/each")
	public String each(Model model) {
		addUser(model);
		return "basic/each";
	}
	
	private void addUser(Model model) {
		List<User> list = new ArrayList<BasicController.User>();
		list.add(new User("UserA", 10));
		list.add(new User("UserB", 20));
		list.add(new User("UserC", 30));
		
		model.addAttribute("users",list);
	}
	
	//if
	@GetMapping("/condition")
	public String condition(Model model) {
		
		addUser(model);
		return "basic/condition";
	}
	
	//주석
	@GetMapping("/comments")
	public String comments(Model model) {
		model.addAttribute("data", "주석데이터");
		return "basic/comments";
	}
	
	//블럭
	@GetMapping("/block")
	public String blocks(Model model) {
		addUser(model);
		return "basic/block";
	}
	
	//자바스크립트 인라인
	@GetMapping("/javascript")
	public String javascript(Model model) {
		
		model.addAttribute("user", new User("user\"A\"",10));
		addUser(model);
		
		return "basic/javascript";
	}
	
	
	
}
