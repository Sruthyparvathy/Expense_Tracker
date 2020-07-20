package com.example.etracker.Model;

import java.math.BigInteger;

public class Category {

	
BigInteger id;
int transactionType;
String categoryName;
BigInteger userId;

public BigInteger getId() {
	return id;
}
public void setId(BigInteger id) {
	this.id = id;
}
public int getTransactionType() {
	return transactionType;
}
public void setTransactionType(int transactionType) {
	this.transactionType = transactionType;
}
public String getCategoryName() {
	return categoryName;
}
public void setCategoryName(String categoryName) {
	this.categoryName = categoryName;
}
public BigInteger getUserId() {
	return userId;
}
public void setUserId(BigInteger userId) {
	this.userId = userId;
}

}






