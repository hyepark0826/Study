package com.my.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.my.dto.Product;
import com.my.exception.AddException;
import com.my.exception.FindException;
import com.my.sql.MyConnection;

@Repository(value="productRepository")
public class ProductOracleRepository implements ProductRepository {
	@Autowired
	@Qualifier(value="dataSource")
	private DataSource ds;
//	public ProductOracleRepository() {}
//	public ProductOracleRepository(DataSource ds) {
//		this.ds=ds;
//	}
	
	@Override
	public void insert(Product product) throws AddException {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Product> selectAll() throws FindException {
//	    List<Map<String,Object>> sample = new ArrayList<>();
	    List<Product> products = new ArrayList<>();	    
	    Connection con = null;
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    String selectProductAllSQL = "SELECT * FROM product ORDER BY prod_no ASC";
	    try {
	        //con = MyConnection.getConnection();
	    	con=ds.getConnection();
	        pstmt = con.prepareStatement(selectProductAllSQL);
	        //pstmt.setString(1, ~~~);
	        rs = pstmt.executeQuery();
	        while(rs.next()) {
	        	String prod_no = rs.getString("prod_no");
	        	String prod_name = rs.getString("prod_name");
	        	int prod_price = rs.getInt("prod_price");
//	        	Map<String,Object> map1 = new HashMap<>();
//	        	map1.put("prod_no", prod_no);
//	    	    map1.put("prod_name", prod_name);
//	    	    map1.put("prod_price", prod_price);
	        	Product p = new Product(prod_no, prod_name, prod_price);
	        	
//	    	    sample.add(map1);
	        	products.add(p);
	        }
	        if(products.size() == 0) {
	        	throw new FindException("상품이 없습니다");
	        }
	        return products;
	    } catch (SQLException e) {
	    	e.printStackTrace();
	    	throw new FindException(e.getMessage());
	    } finally {
	    	MyConnection.close(rs, pstmt, con);
	    }
		//return null;
	}

	@Override
	public Product selectByProdNo(String prodNo) throws FindException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String selectProdNoSQL = "SELECT * FROM product WHERE prod_no=?";
		try {
			//con = MyConnection.getConnection();
			con=ds.getConnection();
			pstmt = con.prepareStatement(selectProdNoSQL);
			pstmt.setString(1, prodNo);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				String prodName = rs.getString("prod_Name");
				int prodPrice = rs.getInt("prod_price");
				String prodInfo = rs.getString("prod_Info");
				java.sql.Date prodMFD = rs.getDate("prod_MFD");
				
				Product p = new Product(prodNo,
						                prodName,
						                prodPrice,
						                prodInfo,
						                prodMFD);
				return p;
			}else {
				throw new FindException("상품이 없습니다");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new FindException(e.getMessage());
		} finally {
			MyConnection.close(rs,  pstmt, con);
		}
	}

	@Override
	public List<Product> selectByProdNoOrProdName(String word) throws FindException {
		// TODO Auto-generated method stub
		return null;
	}

}
