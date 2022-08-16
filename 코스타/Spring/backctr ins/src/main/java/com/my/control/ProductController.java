package com.my.control;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.my.dto.Product;
import com.my.exception.FindException;
import com.my.service.ProductService;

@Controller
public class ProductController {
	@Autowired
	private ProductService service;
	
	@GetMapping("productlist")
	@ResponseBody
	public  List<Product> list() {
		List<Product> products;
		try {
			products = service.list();
		} catch (FindException e) {
			e.printStackTrace();
			products = new ArrayList<>();
		}
		return products;
	}
	
	@GetMapping("viewproduct")
	@ResponseBody
	public Map<String, Object> view(String prod_no) {
		Map<String, Object> map = new HashMap<>();
		try {
			Product p = service.view(prod_no);
			map.put("status", 1);
			map.put("p", p);
		} catch (FindException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("status", 0);
			map.put("msg", e.getMessage());
		}
		return map;
	}
	
	@GetMapping("search")
	@ResponseBody
	public Object search(String word){
		try {
			return service.search(word);
		} catch (FindException e) {
			e.printStackTrace();
			return e.getMessage();
		}
	}
}
