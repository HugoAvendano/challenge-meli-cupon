package com.havendan.challengemelicupon.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;


import com.havendan.challengemelicupon.dto.Cupon;
import com.havendan.challengemelicupon.exception.ServiceException;

@Service
public class CuponService {
	
	/**
	 *@param  items conjunto de ids de productos con sus respectivos precios
	 *@param  amount valor maximo
	 *@return Cupon que contiene un lista de items que maximizan el total
	 **/
	
	public Cupon calculate(HashMap<String, Float> items, Float amount) throws ServiceException {
		
		Cupon cuponMaximizado = getCuponMaximizado (items,amount );
		if (cuponMaximizado.getItem_ids().size() == 0) {
			throw new ServiceException("List Result empty", HttpStatus.NOT_FOUND);
		}
	
		return cuponMaximizado;	
		
		
	}	
	
	
	private Cupon getCuponMaximizado(HashMap<String, Float> items, Float amount) throws ServiceException {
		
		Cupon cupon = new Cupon();
		Cupon cuponOptimizado = new Cupon();
		List<String> listIds= new ArrayList<String>(items.keySet());
		getMaximizacion(cupon,cuponOptimizado, items, listIds, false, amount);
		
		return cuponOptimizado;		
		
	}


	private void getMaximizacion(Cupon cupon, Cupon cuponOptimizado, HashMap<String, Float> items,List<String> listIds, boolean carritoLleno,
			Float amount) {
		
		if (carritoLleno) {
			
			if (cuponOptimizado.getAmount() < cupon.getAmount()) {
				cuponOptimizado.clear();
				for (String item: cupon.getItem_ids()) {
					cuponOptimizado.addItem(item, items.get(item));
				}
				
			}
			
			
		}else if (listIds.size() == 1) {
			if (cupon.getAmount() + items.get(listIds.get(0)) <= amount){
					cupon.addItem(listIds.get(0), items.get(listIds.get(0)));
					getMaximizacion(cupon, cuponOptimizado, items, null, true, amount);
					cupon.removeItem(listIds.get(0), items.get(listIds.get(0)));
					getMaximizacion(cupon, cuponOptimizado, items, null, true, amount);
			}else{
				getMaximizacion(cupon, cuponOptimizado, items, null, true, amount);
			}
			
			
		}else {
			if (cupon.getAmount() + items.get(listIds.get(0)) <= amount){
				cupon.addItem(listIds.get(0), items.get(listIds.get(0)));
				getMaximizacion(cupon, cuponOptimizado, items, listIds.subList(1, listIds.size()), false, amount);
				cupon.removeItem(listIds.get(0), items.get(listIds.get(0)));
				getMaximizacion(cupon, cuponOptimizado, items, listIds.subList(1, listIds.size()), false, amount);
			}else {
				getMaximizacion(cupon, cuponOptimizado, items, listIds.subList(1, listIds.size()), false, amount);
			}
			
		}
		
	}
}
