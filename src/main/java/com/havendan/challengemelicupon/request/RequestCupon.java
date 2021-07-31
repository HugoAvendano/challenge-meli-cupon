package com.havendan.challengemelicupon.request;

import java.util.List;

public class RequestCupon {
	
	private List<String> item_ids;
	private Float amount;
	public List<String> getItem_ids() {
		return item_ids;
	}
	public void setItem_ids(List<String> items_ids) {
		this.item_ids = items_ids;
	}
	public Float getAmount() {
		return amount;
	}
	public void setAmount(Float amount) {
		this.amount = amount;
	}
	
	
	
	
	

}
