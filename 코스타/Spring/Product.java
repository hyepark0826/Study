package com.example.demo.direction.uni;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="product_jpa")

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class Product {
	@Id
	private String prodNo;
	private String prodName;
	private int prodPrice;
	
	
}
