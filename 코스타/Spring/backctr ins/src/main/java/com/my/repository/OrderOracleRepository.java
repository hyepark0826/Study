package com.my.repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.my.dto.OrderInfo;
import com.my.dto.OrderLine;
import com.my.dto.Product;
import com.my.exception.AddException;
import com.my.exception.FindException;
import com.my.sql.MyConnection;

@Repository
public class OrderOracleRepository implements OrderRepository {
	@Autowired
	private SqlSessionFactory sqlSessionFactory;
	
	@Override
	public void insert(OrderInfo info) throws AddException {
		SqlSession session = null;
		try {
			session = sqlSessionFactory.openSession();
			insertInfo(session, info);
			insertLines(session, info.getLines());
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(session != null) {
				session.close();
			}
		}
	}
	private void insertInfo(SqlSession session, OrderInfo info) throws SQLException{
		session.insert("com.my.mapper.OrderMapper.insertInfo", info);
	}
	private void insertLines(SqlSession session, List<OrderLine> lines) throws SQLException{
		for(OrderLine line: lines) {
			session.insert("com.my.mapper.OrderMapper.insertLine", line);
		}
	}
 	@Override
	public List<OrderInfo> selectById(String orderId) throws FindException {
 		SqlSession session = null;
 		try {
 			session = sqlSessionFactory.openSession();
 			List<OrderInfo> infos = session.selectList("com.my.mapper.OrderMapper.selectById", orderId);
 		
			if(infos.size() == 0) {
				throw new FindException("주문내역이 없습니다");
			}
			return infos;
		}catch(Exception e) {
			throw new FindException(e.getMessage());
		}finally {
			session.close();
		}
	}

}
