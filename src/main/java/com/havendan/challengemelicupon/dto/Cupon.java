package com.havendan.challengemelicupon.dto;

import java.util.ArrayList;
import java.util.List;


public class Cupon {
	
	private List<String> item_ids;	
	private Float amount;
	
	public Cupon() {
		this.item_ids = new ArrayList<String>();
		this.amount = (float) 0;
	}
	
	public Cupon (ArrayList<String> item_ids,Float amount ) {
		this.item_ids = item_ids;
		this.amount = amount;
	}
	
	public List<String> getItem_ids() {
		return item_ids;
	}
	
	public void setItem_ids(List<String> listItems) {
		this.item_ids = listItems;
	}
	
	public Float getAmount() {
		return amount;
	}
	
	public void setAmount(Float amount) {
		this.amount = amount;
	}	
	
	public void removeItem (String item, Float amount ) {
		if (this.item_ids.remove(item)) {
			this.amount -= amount;
		}
		
	}
	
	public void addItem (String item, Float amount) {
		this.item_ids.add(item);
		this.amount += amount;
	}
	
	public void clear () {
		this.item_ids.clear();
		this.amount = (float) 0;
	}
	
	public boolean contiene (String item) {
		return this.item_ids.contains(item);
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Cupon) {
			Cupon otherCupon = (Cupon) obj;
			if (this.amount.equals(otherCupon.getAmount()) && this.item_ids.size() == otherCupon.getItem_ids().size()){
				for (int i = 0; i < this.item_ids.size(); i++) {
					if (!this.item_ids.get(i).equals(otherCupon.getItem_ids().get(i))) {
						return false;
					}
				}
				return true;
			}
			return false;
			
		}
		return false;
	}
	
	
}
