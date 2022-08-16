package com.my.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
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
	private SqlSessionFactory sessionFactory;
	
	@Override
	public void insert(Customer customer) throws AddException {
		SqlSession session = null;
		try {
			session = sessionFactory.openSession();
			session.insert("com.my.mapper.CustomerMapper.insert", customer);
		}catch(Exception e) {
			e.printStackTrace();
			throw new AddException(e.getMessage());
		}finally {
			if(session !=null) {
				session.close();
			}
		}
//		//DB연결
//		Connection con = null;
//		//SQL송신
//		PreparedStatement pstmt = null; //executeUpdate()
//		String insertSQL = "INSERT INTO customer(id,pwd,name, status, buildingno, address) VALUES (?,?,?,1,?,?)";
//				
//		try {
//			con = ds.getConnection();
//			pstmt = con.prepareStatement(insertSQL);
//			pstmt.setString(1, customer.getId());
//			pstmt.setString(2, customer.getPwd());
//			pstmt.setString(3, customer.getName());
//			pstmt.setString(4, customer.getBuildingno());
//			pstmt.setString(5, customer.getAddress());
//			pstmt.executeUpdate();
//		} catch (SQLException e) {
//			e.printStackTrace();
//			throw new AddException(e.getMessage());
//		}finally {
//			//DB연결닫기
//			MyConnection.close(pstmt, con);
//		}
	}

	@Override
	public Customer selectById(String id) throws FindException {
		SqlSession session = null;
		try {
			session = sessionFactory.openSession();//Connection과 같은 뜻
			Customer c = session.selectOne("com.my.mapper.CustomerMapper.selectById",
					                       id);
			if(c == null) {
				throw new FindException("고객이 없습니다");
			}
			return c;
		}catch(Exception e) {
			e.printStackTrace();
			throw new FindException(e.getMessage());			
		}finally {
			if(session != null) {
				session.close(); //DBCP에게 Connection돌려줌
			}
		}
	}
}
