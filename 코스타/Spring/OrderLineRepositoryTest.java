package com.example.demo.direction.bi.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.direction.bi.OrderInfo;
import com.example.demo.direction.bi.OrderLine;
import com.example.demo.direction.bi.OrderLinePK;
import com.example.demo.direction.bi.Product;
@SpringBootTest
class OrderLineRepositoryTest {
	@Autowired
	private OrderLineRepository lineRepo;
	@Autowired
	private OrderInfoRepository infoRepo;
	
	@Autowired
	private ProductRepository prodRepo;
	@Test
	void testInsert() {
	
		
	}

	@Test
	void testFindById() {
		OrderLinePK pk = new OrderLinePK();
		OrderInfo info = 
				infoRepo.findById(3L).get();
		Product p = prodRepo.findById("C0001").get();
		pk.setOrderInfo(info);
		pk.setOrderP(p);
		OrderLine line = lineRepo.findById(pk).get();
		assertEquals(1, line.getOrderQuantity());
	}

}
