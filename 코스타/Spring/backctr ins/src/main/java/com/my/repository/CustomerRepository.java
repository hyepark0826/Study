package com.my.repository;

import com.my.dto.Customer;
import com.my.exception.AddException;
import com.my.exception.FindException;

public interface CustomerRepository {

//	Customer selectByIdAndPwd(String id, String pwd) throws FindException;

	/**
	 * 고객정보를 추가한다
	 * @param customer 고객정보
	 * @throws AddException
	 */
	void insert(Customer customer)throws AddException;

	/**
	 * 아이디에 해당하는 고객을 검색한다
	 * @param id 아이디
	 * @return 고객
	 * @throws FindException
	 */
	Customer selectById(String id) throws FindException;

}
