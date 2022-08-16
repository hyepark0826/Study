package com.example.demo.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.entity.Account;
import com.example.demo.exception.ModifyException;
@SpringBootTest
class AccountServiceTest {
	@Autowired
	AccountService service;
	@Test
	void testOpen() {
		Account a1 = new Account();
		a1.setAccountNo("101");
		a1.setAccountBalance(1000);
		service.open(a1); //tx1
		
		Account a2 = new Account();
		a2.setAccountNo("102");
		a2.setAccountBalance(1000);
		service.open(a2);//tx2
	}
	
	@Test
	void testTransferSuccess() throws ModifyException {
		String from ="101";
		String to = "102";
		int amount = 10;
		service.transfer(from, to, amount);
	}
	@Test
	void testTransferFail() throws ModifyException {
		String from ="101";
		String to = "999";
		int amount = 10;
		service.transfer(from, to, amount);
	}
}
