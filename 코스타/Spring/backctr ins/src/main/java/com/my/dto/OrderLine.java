package com.my.dto;

public class OrderLine {
	private int orderNo;
	private Product orderP;
	private int orderQuantity;
	public OrderLine() {}
	public OrderLine(int orderNo, Product orderP, int orderQuantity) {
		super();
		this.orderNo = orderNo;
		this.orderP = orderP;
		this.orderQuantity = orderQuantity;
	}

	public int getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}

	public Product getOrderP() {
		return orderP;
	}

	public void setOrderP(Product orderP) {
		this.orderP = orderP;
	}

	public int getOrderQuantity() {
		return orderQuantity;
	}

	public void setOrderQuantity(int orderQuantity) {
		this.orderQuantity = orderQuantity;
	}

	@Override
	public String toString() {
		return "OrderLine [orderNo=" + orderNo + ", orderP=" + orderP + ", orderQuantity=" + orderQuantity + "]";
	}
	
	
	
}
