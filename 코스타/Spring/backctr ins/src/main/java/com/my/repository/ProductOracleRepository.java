package com.my.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.my.dto.Product;
import com.my.exception.AddException;
import com.my.exception.FindException;

@Repository(value="productRepository")
public class ProductOracleRepository implements ProductRepository {
	@Autowired
	private SqlSessionFactory sqlSessionFactory;

	private Logger logger = Logger.getLogger(this.getClass());
	
	@Override
	public void insert(Product product) throws AddException {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Product> selectAll() throws FindException {
		List<Product> products = new ArrayList<>();
		SqlSession session = null;
		try {
			session = sqlSessionFactory.openSession();
			products = session.selectList("com.my.mapper.ProductMapper.selectAll");
			if(products.size() == 0) {
				throw new FindException("상품이 없습니다");
			}
			return products;
		}catch(Exception e) {
			e.printStackTrace();
			throw new FindException(e.getMessage());
		}finally {
			if(session != null) {
				session.close();
			}
		}
	
	}

	@Override
	public Product selectByProdNo(String prodNo) throws FindException {
		SqlSession session = null;
		try {
			//System.out.println("SYSOUT : prodNo in productoraclerepository selectByProdNo:" + prodNo);
			logger.debug("debug prodNo:" + prodNo);
			logger.info("info prodNo:" + prodNo);
			logger.warn("warn prodNo:" + prodNo);
			logger.error("error prodNo:" + prodNo);
			session = sqlSessionFactory.openSession();
			Product p = session.selectOne("com.my.mapper.ProductMapper.selectByProdNo", prodNo);
			if(p == null) {
				throw new FindException("상품이 없습니다");
			}
			return p;
		} catch (Exception e) {
			e.printStackTrace();
//			System.out.println();
//			logger.error(e.getMessage());
			throw new FindException(e.getMessage());
		} finally {
			if(session != null) {
				session.close();
			}
		}
	}

	@Override
	public List<Product> selectByProdNoOrProdName(String word) throws FindException {
		List<Product> products = new ArrayList<>();
		SqlSession session = null;
		try {
			session = sqlSessionFactory.openSession();
			HashMap<String, String> hashMap = new HashMap<>();
			hashMap.put("word", word);
			hashMap.put("order", "prod_name DESC");
			products = session.selectList("com.my.mapper.ProductMapper.selecyByProdNoOrProdName",
					                      //"%"+word+"%" );
										//	word);
										hashMap);
					
			return products;
		}catch(Exception e) {
			e.printStackTrace();
			throw new FindException(e.getMessage());
		}finally {
			if(session != null) {
				session.close();
			}
		}
	}

}
