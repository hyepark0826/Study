package com.my.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.my.dto.Customer;
import com.my.exception.AddException;
import com.my.exception.FindException;
import com.my.sql.MyConnection;

@Repository(value="customerRepository")
public class CustomerOracleRepository implements CustomerRepository {
	@Autowired
	@Qualifier(value="dataSource")
	private DataSource ds;
	@Override
	public void insert(Customer customer) throws AddException {
		//DB연결
		Connection con = null;
		//SQL송신
		PreparedStatement pstmt = null; //executeUpdate()
		String insertSQL = "INSERT INTO customer(id,pwd,name, status, buildingno, address) VALUES (?,?,?,1,?,?)";
				
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(insertSQL);
			pstmt.setString(1, customer.getId());
			pstmt.setString(2, customer.getPwd());
			pstmt.setString(3, customer.getName());
			pstmt.setString(4, customer.getBuildingno());
			pstmt.setString(5, customer.getAddress());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AddException(e.getMessage());
		}finally {
			//DB연결닫기
			MyConnection.close(pstmt, con);
		}
	}

	@Override
	public Customer selectById(String id) throws FindException {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String selectIdDupChkSQL="SELECT * FROM customer WHERE id = ?";
				
		try {
			con=ds.getConnection();
			pstmt=con.prepareStatement(selectIdDupChkSQL);
			pstmt.setString(1, id);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				return new Customer(
						rs.getString("id"),
						rs.getString("pwd"),
						rs.getString("name"),
						rs.getString("address"),
						rs.getInt("status"),
						rs.getString("buildingno"));
			}
			throw new FindException("고객이 없습니다");
		}catch(SQLException e){
			throw new FindException(e.getMessage());
		}finally {
			MyConnection.close(rs,pstmt,con);
		}
	}
}
