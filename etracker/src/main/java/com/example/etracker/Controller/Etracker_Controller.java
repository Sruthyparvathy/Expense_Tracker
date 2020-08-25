package com.example.etracker.Controller;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.etracker.Model.Category;
import com.example.etracker.Model.Transaction;
import com.example.etracker.Model.User;
import com.example.etracker.Service.Etracker_Service;
import com.example.etracker.utils.PathRoutes;


@RestController
@RequestMapping(PathRoutes.SearchSQL.SEARCHSQL_ROOT)
public class Etracker_Controller {
	
	
	
	@Autowired
	private Etracker_Service exp;
	
	@GetMapping(PathRoutes.SearchSQL.TOTAL_BAR_YEAR)
	public Map<String, java.lang.Object> graph1(@RequestParam int YEAR, @RequestParam int userId ){
		return exp.graph1(YEAR,userId);
	}
	
	@GetMapping(PathRoutes.SearchSQL.TOTAL_BAR_MONTH)
	public Map<String, java.lang.Object> graph2(@RequestParam int year, @RequestParam String month,@RequestParam int userId ){
		return exp.graph2(year,month,userId);
	}
	@GetMapping(PathRoutes.SearchSQL.TOTAL_LINE_YEAR)
	public Collection<Map<String,java.lang.Object>>  graph3(@RequestParam int YEAR,@RequestParam int userId ){
		return exp.graph3(YEAR,userId);
	}
	@GetMapping(PathRoutes.SearchSQL.TOTAL_LINE_MONTH)
	public Collection<Map<String,java.lang.Object>> graph4( @RequestParam String month,@RequestParam int year,@RequestParam int userId ){
		return exp.graph4(month,year,userId);
	}


	
	@PostMapping(PathRoutes.SearchSQL.ADD_INCOME)
	 public int addincome(@RequestBody Transaction income) {
	 exp.addincome(income.getUserId(),income.getItem(),income.getCategoryId(),income.getAmount(),income.getTransactionDate());
	 return 1;
	 }
	@PostMapping(PathRoutes.SearchSQL.ADD_EXPENSE)
	 public int addexpense(@RequestBody  Transaction expense ) {
	 exp.addexpense(expense.getUserId(),expense.getItem(),expense.getCategoryId(),expense.getAmount(),expense.getTransactionDate());
	 return 1;
	 }
	
	@PostMapping(PathRoutes.SearchSQL.ADD_EXPENSE_CATEGORY)
	 public int addexpensecategory(@RequestBody Category category ) {
	 exp.addexpensecategory(category.getCategoryName(),category.getUserId());
	 return 1;
	 }
	@PostMapping(PathRoutes.SearchSQL.ADD_INCOME_CATEGORY)
	 public int addincomecategory(@RequestBody Category category ) {
	 exp.addincomecategory(category.getCategoryName(),category.getUserId());
	return 1;
	 }
	@PostMapping
	public int addUser(@RequestBody User user ) {
		try {int status = exp.addUser(user.getId(),user.getName(),user.getEmailId(),user.getPassword());
			return status;}	
catch(DataIntegrityViolationException e) {
	return 2;}
catch (Exception e) {
	return 0;
}
	}
	
	
	
	
	@GetMapping(PathRoutes.SearchSQL.LIST_CATEGORY_EXPENSE)
	public List<Map<String, Object>> liscategoryexpense(@RequestParam int userId)
	{
		return exp.liscategoryexpense(userId);
	}
	@GetMapping(PathRoutes.SearchSQL.LIST_CATEGORY_INCOME)
	public List<Map<String, Object>> liscategoryincome(@RequestParam int userId)
	{
		return exp.liscategoryincome(userId);
	}
	@GetMapping(PathRoutes.SearchSQL.CATEGORY_BAR_MONTH)
	public List<Map<String, Object>> monthlycategorysum( @RequestParam int userId, @RequestParam String month,@RequestParam int year )
	{
		return exp.monthlycategorysum(userId,month,year);
	}
	@GetMapping(PathRoutes.SearchSQL.CATEGORY_BAR_YEAR)
	public List<Map<String, Object>> yearlycategorysum( @RequestParam int userId, @RequestParam int YEAR)
	{
		return exp.yearlycategorysum(userId,YEAR);
	}
	
	@GetMapping(PathRoutes.SearchSQL.GET_INCOME)
	public Collection<Map<String,Object>> getIncome(@RequestParam int userId){
		return exp.getIncome(userId);
	}
	

	@GetMapping(PathRoutes.SearchSQL.GET_EXPENSE)
	public Collection<Map<String,Object>> getExpense(@RequestParam int userId){
		return exp.getExpense(userId);
	}
	
	@GetMapping(PathRoutes.SearchSQL.GET_INCOME_EXPENSE)
	public Collection<Map<String,Object>> getIncomeExpense(@RequestParam int userId){
		return exp.getIncomeExpense(userId);
	}

	
	@GetMapping(PathRoutes.SearchSQL.GET_EMAIL_PASSWORD)
	public List<User> getUser(@PathVariable String emailId,@PathVariable String password) {
		return exp.getUser(emailId,password);
	}
	@PutMapping(PathRoutes.SearchSQL.GET_EMAIL_PASSWORD)
	public int resetPassword(@PathVariable String emailId,@PathVariable String password) {
		return exp.resetPassword(emailId,password);
	}
	
	
}
