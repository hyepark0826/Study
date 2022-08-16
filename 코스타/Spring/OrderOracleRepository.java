package com.my.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import com.my.dto.OrderInfo;
import com.my.dto.OrderLine;
import com.my.exception.AddException;
import com.my.exception.FindException;
import com.my.sql.MyConnection;

public class OrderOracleRepository implements OrderRepository {

	@Override
	public void insert(OrderInfo info) throws AddException {
		Connection con = null;
		try {
			con = MyConnection.getConnection();
			insertInfo(con, info);
			insertLines(con, info.getLines());
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			MyConnection.close(null, con);
		}
	}
	private void insertInfo(Connection con, OrderInfo info) throws SQLException{
		PreparedStatement pstmt = null;
		String insertInfoSQL = 
				"INSERT INTO order_info(ORDER_NO,ORDER_ID,ORDER_DT) VALUES (order_seq.NEXTVAL, ?, SYSDATE)";
		pstmt = con.prepareStatement(insertInfoSQL);
		pstmt.setString(1, info.getOrderId());
		pstmt.executeUpdate();
	}
	private void insertLines(Connection con, List<OrderLine> lines) throws SQLException{
		PreparedStatement pstmt = null;
		String insertLineSQL = 
				"INSERT INTO order_line(ORDER_NO, ORDER_PROD_NO,ORDER_QUANTITY) VALUES (order_seq.CURRVAL, ?, ?)";
		pstmt = con.prepareStatement(insertLineSQL);
		for(OrderLine line: lines) {
			String prodNo = line.getOrderP().getProdNo();
			int orderQuantity = line.getOrderQuantity();
			pstmt.setString(1, prodNo);
			pstmt.setInt(2, orderQuantity);
			pstmt.addBatch();
		}
		pstmt.executeBatch();
	}
 	@Override
	public List<OrderInfo> selectById(String orderId) throws FindException {
		
		return null;
	}

}
