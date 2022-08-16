package com.my.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.my.dto.OrderInfo;
import com.my.exception.AddException;
import com.my.exception.FindException;
import com.my.repository.OrderRepository;

@Service
public class OrderService {
	@Autowired
	private OrderRepository repository;
	
	public void addOrder(OrderInfo info) throws AddException{
		repository.insert(info);
	}
	public List<OrderInfo> viewOrder(String orderId) throws FindException{
		return repository.selectById(orderId);
	}
}
