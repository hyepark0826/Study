package com.my.repository;

import java.util.List;

import com.my.dto.Product;
import com.my.exception.AddException;
import com.my.exception.FindException;

public interface ProductRepository {
	/**
	 * 상품을 추가한다
	 * @param product 상품
	 * @throws AddException 상품번호가 중복될 경우 "이미 존재하는 상품입니다"상세메시지를 갖는 예외가 발생한다
	 */
	public void insert(Product product) throws AddException;
	/**
	 * 상품을 모두 검색한다
	 * @return 상품들
	 * @throws FindException 상품이 없으면 "상품이 없습니다"상세메시지를 갖는 예외가 발생한다
	 */
	public List<Product> selectAll() throws FindException;
	
	/**
	 * 상품번호로 상품검색한다
	 * @param prodNo 상품번호
	 * @return 상품객체
	 * @throws FindException 상품번호에 해당하는 상품이 없으면 "상품이 없습니다"상세메시지를 갖는 예외가 발생한다
	 */
	public Product selectByProdNo(String prodNo) throws FindException;
	/**
	 * 상품번호나 상품명에 검색어를 포함한 상품들을 반환한다
	 * @param word 검색어
	 * @return
	 * @throws FindException 검색어를 포함한 상품들이 없으면 "검색어를 포함한 상품이 없습니다"라는 상세메시지를 갖는 예외가 발생한다 
	 */
	public List<Product> selectByProdNoOrProdName(String word) throws FindException;
}
