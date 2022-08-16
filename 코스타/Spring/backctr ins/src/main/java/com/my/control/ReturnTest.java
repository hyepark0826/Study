package com.my.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ReturnTest {
	@GetMapping("a1")
	public ModelAndView a() {
		ModelAndView mnv = new ModelAndView();
		mnv.addObject("greeting", "HELLO");
		mnv.setViewName("/WEB-INF/jsp/a.jsp");
		return mnv;
	}
	@GetMapping("b1")
	public String b(Model model) {
		model.addAttribute("greeting", "안녕하세요");
		return "/WEB-INF/jsp/a.jsp"; //뷰이름으로 /WEB-INF/jsp/a.jsp를 반환
	}
	
	@GetMapping("c1") //  return "/WEB-INF/jsp/c1.jsp"와 같음 
	public void c() {
		
	}
	
	@GetMapping(value = "d1", produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String d() {
		String responseData = "응답내용입니다";
		return responseData; //뷰이름으로 응답내용입니다를 반환, 
		                     //@ResponseBody를 추가하면 "응답내용입니다"를 응답
	}
}
