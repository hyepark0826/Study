package com.example.demo.direction.uni;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="order_info_jpa")
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class OrderInfo {
	@Id
	@Column(name="order_info_no")
	private Long orderNo;
	private Date orderDt;
	private String orderId;
	
//	@ManyToOne
	@OneToMany//(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//	@JoinColumn(name="order_line")
//	@JoinColumn(name="order_line_no")
	private List<OrderLine>lines;
}
