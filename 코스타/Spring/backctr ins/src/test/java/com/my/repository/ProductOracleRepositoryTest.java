package com.my.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.my.dto.Product;
import com.my.exception.FindException;

//스프링컨테이너(ApplicationContext)구동
@RunWith(SpringRunner.class)

//Spring 컨테이너용 XML파일 설정
@ContextConfiguration(locations={
		"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
public class ProductOracleRepositoryTest {
	private Logger logger = Logger.getLogger(getClass());
	@Autowired
	private ProductRepository repository;
	
	@Test
	public void testSelectByProdNo() throws FindException {
		//fail("Not yet implemented");
		String prodNo = "C0001";
		String expectedProdName = "아메리카노";
		int expectedProdPrice = 4000;
		Product p = repository.selectByProdNo(prodNo);
		//logger.debug(expectedProdName.equals(p.getProdName())); //true, false
		assertNotNull(p);
		assertEquals(expectedProdName, p.getProdName());
		//assertEquals(expectedProdPrice, p.getProdPrice());
		assertTrue(expectedProdPrice == p.getProdPrice());
	}
	
	@Test
	public void testSelectAll() throws FindException {
		int expectedSize = 4; //상품수
		List<Product> list = repository.selectAll();
		assertTrue(expectedSize == list.size());
	}
}
