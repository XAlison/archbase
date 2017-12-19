package org.soho.pay.domain;

public class ExPay{
	private String id;
	private String userId;
	private Double amount;

	public String getId(){
		return  this.id;
	}

	public void setId(String id){
		this.id=id;
	}

	public String getUserId(){
		return  this.userId;
	}

	public void setUserId(String userId){
		this.userId=userId;
	}

	public Double getAmount(){
		return  this.amount;
	}

	public void setAmount(Double amount){
		this.amount=amount;
	}

}
