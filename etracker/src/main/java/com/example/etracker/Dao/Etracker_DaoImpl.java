package com.example.etracker.Dao;


import java.math.BigInteger;


import java.sql.ResultSet;

import java.sql.SQLException;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.example.etracker.Model.User;
import com.example.etracker.utils.Sql;





@Repository
public class Etracker_DaoImpl implements Etracker_Dao {
	
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	PasswordAuthentication passwordAuthentication=new PasswordAuthentication();
	
       
    public Map<String, java.lang.Object> graph1(int YEAR,int userId) {
		
		return jdbcTemplate.queryForMap(Sql.MetadataSql.TOTAL_BAR_YEAR,YEAR,userId);
	}
    
	public Map<String, java.lang.Object> graph2(int year, String month,int userId) {
		
		return jdbcTemplate.queryForMap(Sql.MetadataSql.TOTAL_BAR_MONTH,year,month,userId);
	}
	
    public Collection<Map<String,java.lang.Object>> graph3(int YEAR,int userId) {
		
		return jdbcTemplate.queryForList(Sql.MetadataSql.TOTAL_LINE_YEAR,YEAR,userId);
	}
    
    public Collection<Map<String,java.lang.Object>> graph4(String month,int year, int userId) {
		
		return jdbcTemplate.queryForList(Sql.MetadataSql.TOTAL_LINE_MONTH,month,year,userId);
	}

	public int addincome(BigInteger userId, String item, BigInteger categoryId, double amount, Date transactionDate) {
		String sql = Sql.MetadataSql.ADDINCOME;
		 jdbcTemplate.update(sql,userId,  item,  categoryId,  amount,transactionDate);
		return 1;
	}
	public int addexpense(BigInteger userId, String item, BigInteger categoryId, double amount, Date transactionDate) {
		String sql = Sql.MetadataSql.ADDEXPENSE;
		 jdbcTemplate.update(sql,userId,item,categoryId,amount,transactionDate);
		return 1;
	}
	

	public int addincomecategory( String categoryName, BigInteger userId) {

		 String sql = Sql.MetadataSql.ADDINCOMECATEGORY;
		 jdbcTemplate.update(sql, categoryName,userId);
		 return 1;
	}
	public int addexpensecategory( String categoryName, BigInteger userId) {
		String sql = Sql.MetadataSql.ADDEXPENSECATEGORY;
		 jdbcTemplate.update(sql, categoryName,userId);
		return 1; 
	}

	

	public List<Map<String, Object>> monthlycategorysum(int year, String month,int userId) {
		return jdbcTemplate.queryForList(Sql.MetadataSql.CATEGORY_BAR_MONTH,year, month, userId);

	}

	public List<Map<String, Object>> yearlycategorysum(int YEAR,int userId) {
		return jdbcTemplate.queryForList(Sql.MetadataSql.CATEGORY_BAR_YEAR,YEAR,userId);

	}
	
	

	public Collection<Map<String, Object>> getIncome(int userId) {
		return jdbcTemplate.queryForList(Sql.MetadataSql.FETCH_INCOME, userId);
	}


	@Override
	public Collection<Map<String, Object>> getExpense(int userId) {
		return jdbcTemplate.queryForList(Sql.MetadataSql.FETCH_EXPENSE, userId);
	}


	@Override
	public Collection<Map<String, Object>> getIncomeExpense(int userId) {
		return jdbcTemplate.queryForList(Sql.MetadataSql.FETCH_INCOME_EXPENSE, userId);
	}
	
	
	public List<Map<String, Object>> liscategoryexpense(int userId) {
		String sql = Sql.MetadataSql.LIST_CATEGORY_EXPENSE;
		return jdbcTemplate.queryForList(sql,userId);

	}

	
	public List<Map<String, Object>> liscategoryincome(int userId) {
		String sql = Sql.MetadataSql.LIST_CATEGORY_INCOME;
		return jdbcTemplate.queryForList(sql,userId);

	}


	@Override
	public int addUser(long id, String name, String emailId, String password ) {
		String password1 =password;
		char[] ch = new char[password1.length()]; 
		for (int i = 0; i < password1.length(); i++) { 
            ch[i] = password1.charAt(i); 
        } 
		String pass=passwordAuthentication.hash(ch);
		
		String sql= Sql.MetadataSql.ADD_USER;
		jdbcTemplate.update(sql,emailId,name,pass);
		return 1;
	}



	


	@Override
	public List<User> selectUser(String emailId, String password) {
		String checkSql = Sql.MetadataSql.CHECK_USER;
		Object[] input = new Object[] {emailId};
		int status = jdbcTemplate.queryForObject(checkSql,input,Integer.class);
		
		if(status==1) {
			String getPassSql = Sql.MetadataSql.GET_PASSWORD;
			Object[] in = new Object[] {emailId};
			String dbPass=jdbcTemplate.queryForObject(getPassSql,in, String.class);
			
			char[] ch = new char[password.length()]; 
			for (int i = 0; i < password.length(); i++) { 
	            ch[i] = password.charAt(i); 
	        } 
			
			
			boolean check = passwordAuthentication.authenticate(ch, dbPass);
			if(!check) {
				
				return Collections.emptyList();
			}
			else {
				String fetchSql= Sql.MetadataSql.FETCH_USER;
				Object[] inputs = new Object[] {emailId};
				return jdbcTemplate.query(fetchSql,inputs, new RegisterMapper());
			}
		}
		else {
			return Collections.emptyList();
		}
	}

	



	@Override
	public int resetPassword(String emailId, String password) {

	String password2 =password;
	char[] ch = new char[password2.length()];
	for (int i = 0; i < password2.length(); i++) {
	            ch[i] = password2.charAt(i);
	        }
	String passwd=passwordAuthentication.hash(ch);

	String updateSql=Sql.MetadataSql.UPDATE_PASSWORD;
	Object[] inputs = new Object[] {passwd,emailId};
	return jdbcTemplate.update(updateSql,inputs);
	}



class RegisterMapper implements RowMapper<User>{
	@Override
	public User mapRow(ResultSet rs, int rowNum) throws SQLException {
		User register = new User();
		register.setId(rs.getLong("ID"));
		register.setName(rs.getString("NAME"));
		return register;
	}

}
}
