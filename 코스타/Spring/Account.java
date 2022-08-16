package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="account_jpa")
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter 
public class Account {
	@Id
	private String accountNo;
	
	@Column(columnDefinition="number(4)")//잔액은 9999원까지 가능
	private int accountBalance;
	
}
