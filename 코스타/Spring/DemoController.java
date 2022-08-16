package com.my.control;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.my.exception.FindException;
import com.my.repository.BoardRepository;

@Controller
public class DemoController {
	@Autowired
	private BoardRepository repository;
	
	@GetMapping("a")
	@ResponseBody
	public String a(String one, int two) {
		return "one:" + one + ",two:" +two; 
	}
	@GetMapping("greeting")
	@ResponseBody
	public String greeting() {
		return "welcome";
	}
	
	@GetMapping("userepository")
	@ResponseBody
	public Map<String,Integer> useRepository() throws FindException {
		Map<String,Integer> map = new HashMap<>();
		map.put("totalCnt", repository.selectCount());
		return map;
	}
}