package com.my.control;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.my.dto.OrderInfo;
import com.my.dto.OrderLine;
import com.my.dto.Product;
import com.my.exception.AddException;
import com.my.exception.FindException;
import com.my.service.OrderService;

@Controller
public class OrderController {
	@Autowired
	private OrderService service;
	
	@GetMapping("addorder")
	@ResponseBody
	public Map<String, Object> addOrder(HttpSession session){
		Map<Product, Integer> cart = (Map)session.getAttribute("cart");                                                                                     
		if(cart == null) {
			cart = new HashMap<>();
			session.setAttribute("cart", cart);
		}
		
		Product p1 = new Product(); p1.setProdNo("C0001");
		int quantity1 = 1;
		cart.put(p1, quantity1);
		Product p2 = new Product(); p2.setProdNo("C0002");
		int quantity2 = 2;
		cart.put(p2, quantity2);
		//---샘플 장바구니 -----
		
		session.setAttribute("loginInfo", "id1");
		//---샘플 로그인아이디 ----
		
		Map<String, Object> map = new HashMap<>();
		if(cart == null || cart.size()== 0) {//장바구니없거나 비어있는 경우
			map.put("status", -1);
			map.put("msg", "주문실패: 장바구니가 비었습니다");
		}else {
			//로그인된 사용자인가 확인
			String loginedId = (String)session.getAttribute("loginInfo");
			if(loginedId == null) { //로그인 안한 사용자인 경우
				map.put("status", 0);
				map.put("msg", "로그인하세요");
			}else {
				OrderInfo info = new OrderInfo();
				info.setOrderId(loginedId);
				List<OrderLine> lines = new ArrayList<>();
				for(Product p : cart.keySet()) {
					Integer quantity = cart.get(p);
					OrderLine line = new OrderLine();
					line.setOrderP(p);
					line.setOrderQuantity(quantity);
					lines.add(line);
				}
				info.setLines(lines);
				try {
					service.addOrder(info);//주문추가
					session.removeAttribute("cart");//장바구니 비우기
					map.put("status", 1);
					map.put("msg", "주문성공");
				}catch(AddException e) {
					e.printStackTrace();
				}
			}
		}
		return map;
	}
	
	@GetMapping("vieworder")
	@ResponseBody
	public Object viewOrder(HttpSession session){
		session.setAttribute("loginInfo", "id1");
		//---샘플 로그인아이디 ----
		
		Map<String,Object> map = new HashMap<>();
		String loginedId = (String)session.getAttribute("loginInfo");
		if(loginedId == null) {
			map.put("status", 0);
			map.put("msg", "로그인하세요");
			return map;
		}else {
			try {
				List<OrderInfo> infos = service.viewOrder(loginedId);
				return infos;
			} catch (FindException e) {				
				e.printStackTrace();
				map.put("status", -1);
				map.put("msg", e.getMessage());
				return map;
			}
		}
	}
}
