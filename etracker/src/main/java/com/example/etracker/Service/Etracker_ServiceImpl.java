package com.example.etracker.Service;


import java.math.BigInteger;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.etracker.Dao.Etracker_Dao;
import com.example.etracker.Model.User;


@Service
public class Etracker_ServiceImpl implements Etracker_Service{
	
	@Autowired
	Etracker_Dao epTrackDao;
	
	public Map<String, java.lang.Object> graph1(int userId){
		return epTrackDao.graph1(userId);
	}
	public Map<String, java.lang.Object> graph2(int userId){
		return epTrackDao.graph2(userId);
	}
	public Collection<Map<String,java.lang.Object>>  graph3(int userId){
		return epTrackDao.graph3(userId);
	}
	
	public Collection<Map<String,java.lang.Object>> graph4(int userId){
		return epTrackDao.graph4(userId);
	}
	
	public int addincome(BigInteger userId, String item, BigInteger categoryId, double amount, Date transactionDate) {
			return epTrackDao.addincome(userId,item,categoryId,amount,transactionDate);
		
	}
	

	public int addexpense(BigInteger userId, String item, BigInteger categoryId, double amount, Date transactionDate) {
		  return  epTrackDao.addexpense(userId,item,categoryId,amount,transactionDate);
	
	}
	
	public int addincomecategory( String categoryName,BigInteger userId) {
		   return  epTrackDao.addincomecategory(categoryName,userId);
	 
    }
    public int addexpensecategory(String categoryName,BigInteger userId) {
	     return epTrackDao.addexpensecategory(categoryName,userId);

    }

	public List<Map<String, Object>> monthlycategorysum(int userId) {
		return epTrackDao.monthlycategorysum(userId);
	}

	public List<Map<String, Object>> yearlycategorysum(int userId) {
		return epTrackDao.yearlycategorysum(userId);
	}
	
	public Collection<Map<String, Object>> getIncome(int userId) {
		return epTrackDao.getIncome(userId);
	}


	
	public Collection<Map<String, Object>> getExpense(int userId) {
		return epTrackDao.getExpense(userId);
	}


	public Collection<Map<String, Object>> getIncomeExpense(int userId) {
		return epTrackDao.getIncomeExpense(userId);
	}
	
	public int addUser(long id, String name, String emailId, String password ) {
		return epTrackDao.addUser(id, name, emailId, password);
		
	}
	public List<User> getUser(String emailId, String password) {
		return epTrackDao.selectUser(emailId,password);
	}
	public int resetPassword(String emailId, String password) {
		return epTrackDao.resetPassword(emailId,password);
	}
	@Override
	public List<Map<String, Object>> liscategoryexpense(int userId) {
		return epTrackDao.liscategoryexpense(userId);

	}
	@Override
	public List<Map<String, Object>> liscategoryincome(int userId) {
		return epTrackDao.liscategoryincome(userId);

	}
	

	
}
