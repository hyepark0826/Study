package com.my.service;

import com.my.repository.ProductRepository;

public class ProductService {
	private ProductRepository repository;
	public ProductService() {}
	public ProductService(ProductRepository repository) {
		this.repository = repository;
	}
	public void setRepository(ProductRepository repository) {
		this.repository = repository;
	}
	public ProductRepository getRepository() {
		return repository;
	}
	public void list() {
		//repository.selectAll();
	}
	public void view(String prodNo) {
//		repository.selectByProdNo(prodNo);
	}
}
