package com.my.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.my.repository.ProductRepository;

@Service(value="productService")
public class ProductService {
	@Autowired
	private ProductRepository repository;
// 	public ProductService() {}
//	public ProductService(ProductRepository repository) {
//		this.repository=repository;
//	}
//	public ProductRepository getRepository() {
//		return repository;
//	}
//	public void setRepository(ProductRepository repository) {
//		this.repository = repository;
//	}
	public void list() {
		//repository.selectAll();
	}
	public void view(String prodNo) {
		//repository.selectByProdNo(prodNo);
	}

}
