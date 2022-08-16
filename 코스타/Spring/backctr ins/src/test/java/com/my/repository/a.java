package com.my.repository;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.my.dto.Product;
import com.my.exception.FindException;

@RunWith(SpringRunner.class)
//Spring 컨테이너용 XML파일 설정
@ContextConfiguration(locations={
		"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
public class a {
	@Autowired
	private ProductRepository repository;
	
	@Test
	public void testSelectAll() throws FindException{
		int expectedSize = 4;
		List<Product> list = repository.selectAll();
		assertEquals(expectedSize, list.size());
	}
	@Test
	public void testSelectByProdNo() throws FindException {
		String prodNo = "C0001";
		String expectedProdName = "아메리카노";
		int expectedProdPrice = 1000;
		Product p = repository.selectByProdNo(prodNo);
		assertEquals(expectedProdName, p.getProdName());
		assertEquals(expectedProdPrice, p.getProdPrice());
	}
	
}
