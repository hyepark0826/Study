package com.my.repository;


import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.my.dto.Customer;
import com.my.exception.FindException;

@RunWith(SpringRunner.class)
//Spring 컨테이너용 XML파일 설정
@ContextConfiguration(locations={
		"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
public class b {
	@Autowired
	private CustomerRepository repository;
	
	@Test
	public void testSelectById1() throws FindException {
		String id = "id1";
		String expectedPwd = "p1";
		
		Customer c = repository.selectById(id);
		assertEquals(expectedPwd, c.getPwd());
	}
	
	@Test(expected = FindException.class)
	public void testSelectById2() throws FindException {
		String id = "x";
		repository.selectById(id);
	}

}
