package com.my.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.my.dto.Customer;
import com.my.exception.AddException;
import com.my.exception.FindException;
import com.my.repository.CustomerRepository;

@Service(value="customerService")
public class CustomerService {
	@Autowired
	private CustomerRepository repository;
//	public CustomerService() {
//		this.repository =  new CustomerOracleRepository();
//	}
//	public CustomerService() {}
//	public CustomerService(CustomerRepository repository) {
//		this.repository=repository;
//	}
//	public CustomerRepository getRepository() {
//		return repository;
//	}
//	public void setRepository(CustomerRepository repository) {
//		this.repository = repository;
//	}
	
	public Customer login(String id, String pwd) 
		throws FindException{
		Customer c = repository.selectById(id);
		if(!c.getPwd().equals(pwd)) {
			throw new FindException();
		}
		return c;	
	}
	public void signup(Customer c) throws AddException{
		repository.insert(c);	
	}
	public Customer iddupchk(String id) throws FindException{
		return repository.selectById(id);
	}
	

	
}
