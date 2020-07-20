package com.example.etracker.Dao;

import java.math.BigInteger;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.example.etracker.Model.User;

public interface Etracker_Dao {
  
  public Map<String, java.lang.Object> graph1(int userId);
	public Map<String, java.lang.Object> graph2(int userId);
	public Collection<Map<String,java.lang.Object>>  graph3(int userId);
	public Collection<Map<String,java.lang.Object>> graph4(int userId);
	public int addincome(BigInteger userId, String item, BigInteger categoryId, double amount, Date transactionDate);
	public int addexpense(BigInteger userId, String item, BigInteger categoryId, double amount, Date transactionDate);
	public int addincomecategory(String categoryName,BigInteger userId);
	public int addexpensecategory(String categoryName,BigInteger userId);
	public List<Map<String, Object>> monthlycategorysum(int userId);
	public List<Map<String, Object>> yearlycategorysum(int userId);

	public Collection<Map<String,Object>> getIncome(int userId);

	public Collection<Map<String, Object>> getExpense(int userId);

	public Collection<Map<String, Object>> getIncomeExpense(int userId);
	
	List<User> selectUser(String emailId, String password);

	int resetPassword(String emailId, String password);
	int addUser(long id, String name, String emailId, String password );
	public List<Map<String, Object>> liscategoryexpense(int userId);
	public List<Map<String, Object>> liscategoryincome(int userId);


}
