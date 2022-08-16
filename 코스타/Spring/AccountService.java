package com.example.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.Account;
import com.example.demo.exception.ModifyException;
import com.example.demo.repository.AccountRepository;

@Service
public class AccountService {
	@Autowired
	private AccountRepository repository;
	public void open(Account a) {
		repository.save(a);
	}
	/**
	 * 입금
	 * @param no 계좌번호
	 * @param amount 입금액
	 */
	public void deposit(String no, int amount) throws ModifyException{
		Optional<Account> optA = repository.findById(no);
//		optA.orElseThrow(()->new ModifyException(no+"계좌가 없습니다"));
		optA.orElseThrow(()->new IllegalArgumentException(no+"계좌가 없습니다"));
		Account a = optA.get();
		int aBalance = a.getAccountBalance(); 
		a.setAccountBalance(aBalance + amount );
		repository.save(a);
	}
	/**
	 * 출금
	 * @param no 계좌번호
	 * @param amount 출금액
	 * @throws ModifyException 
	 */
	public void withdraw(String no, int amount) throws ModifyException {
		Optional<Account> optA = repository.findById(no);
		Account a = optA.get();
		int aBalance = a.getAccountBalance(); 
		if(amount > aBalance ) {
			throw new ModifyException("잔액이 부족합니다");
//			throw new IllegalArgumentException("잔액이 부족합니다");
		}
		a.setAccountBalance(aBalance - amount );
		repository.save(a);
	}
	/**
	 * 계좌이체
	 * @param from
	 * @param to
	 * @param amount
	 * @throws ModifyException
	 */
	@Transactional//(rollbackFor = ModifyException.class)
	public void transfer(String from, String to, int amount) throws ModifyException{
		withdraw(from, amount);
		deposit(to, amount);
	}
}