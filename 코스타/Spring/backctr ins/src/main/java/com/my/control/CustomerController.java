package com.my.control;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.my.dto.Customer;
import com.my.exception.AddException;
import com.my.exception.FindException;
import com.my.service.CustomerService;
@Controller
public class CustomerController {
	@Autowired
	private CustomerService service;
	
	@PostMapping("signup")
	@ResponseBody
	public Map signup(Customer c) {
		Map<String, Object>map = new HashMap<>();
		map.put("status", 0);
		map.put("msg", "가입실패");
		try {
			service.signup(c);
			map.put("status", 1);
			map.put("msg", "가입성공");
		} catch (AddException e) {
			e.printStackTrace();
		}
		return map;
	}
	
	@PostMapping("login")
	@ResponseBody
	public Map login(String id, String pwd, HttpSession session){
		System.out.println("in login id:"+  id + ", pwd:" + pwd);
		Map<String, Object>map = new HashMap<>();
		map.put("status", 0);
		session.removeAttribute("loginInfo");

		//비지니스로직 호출
		try {
			Customer c = service.login(id, pwd);
			map.put("status", 1);
			session.setAttribute("loginInfo", id);
		}catch(FindException e) {	
			e.printStackTrace();
		}	
		return map;
	}
	
//	@PostMapping("signup")
//	:
//	public  signup( ) {
//		:
//	}
	//완성하세요
	//CustomerController :고객(로그인, 가입, 아이디중복확인, 로그아웃)완성
	//ProductController  :상품
	
}
