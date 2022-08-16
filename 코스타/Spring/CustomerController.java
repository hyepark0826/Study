package com.my.control;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.my.dto.Customer;
import com.my.exception.FindException;
import com.my.service.CustomerService;
@Controller
public class CustomerController {
	@Autowired
	private CustomerService service;
	
	@PostMapping("/login")
	public String login(HttpServletRequest request, HttpServletResponse response) 
			throws IOException{
		//요청전달데이터 얻기
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		//응답결과
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object>map = new HashMap<>();
		map.put("status", 0);
		String result = null;//"{\"status\": 0}"; //실패

		//세션(클라이언트별 객체)얻기
		HttpSession session = request.getSession();
		session.removeAttribute("loginInfo");

		//비지니스로직 호출
		try {
			Customer c = service.login(id, pwd);
			map.put("status", 1);
			session.setAttribute("loginInfo", id);
			//session.setAttribute("loginInfo", c);
		}catch(FindException e) {			
		}	
		result = mapper.writeValueAsString(map);
		return result;
	}
}
