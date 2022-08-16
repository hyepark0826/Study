package com.my.repository;

import java.util.List;

import com.my.dto.OrderInfo;
import com.my.exception.AddException;
import com.my.exception.FindException;

public interface OrderRepository {
	/**
	 * 주문정보를 추가한다
	 * @param info 주문정보
	 * @throws AddException 
	 */
	public void insert(OrderInfo info) throws AddException;
	
	/**
	 * 주문자아이디가 주문한정보들을 반환한다
	 * @param orderId 주문자아이디
	 * @return 주문한정보들
	 * @throws FindException
	 */
	public List<OrderInfo> selectById(String orderId) throws FindException;
	
}
