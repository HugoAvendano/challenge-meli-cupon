package com.havendan.challengemelicupon.service;




import java.util.HashMap;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;


import com.havendan.challengemelicupon.dto.Cupon;
import com.havendan.challengemelicupon.exception.ServiceException;

@Service
public class CuponService {
	
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
		getMaximizacion(cupon,cuponOptimizado, items, false, amount);
		
		return cuponOptimizado;		
		
	}


	private void getMaximizacion(Cupon cupon, Cupon cuponOptimizado, HashMap<String, Float> items, boolean carritoLleno,
			Float amount) {
		
		if (carritoLleno) {
			if (cuponOptimizado.getAmount() < cupon.getAmount()) {
				cuponOptimizado.clear();
				for (String item: cupon.getItem_ids()) {
					cuponOptimizado.addItem(item, items.get(item));
				}
				
			}
			
		}else {
			for(String id: items.keySet()) {
				if (!cupon.contiene(id)) {
					if (cupon.getAmount() + items.get(id) <= amount) {
						cupon.addItem(id,items.get(id));
						getMaximizacion(cupon, cuponOptimizado, items, false, amount);
						cupon.removeItem(id,items.get(id));
						
					}else {
						getMaximizacion(cupon, cuponOptimizado, items, true, amount);
					}
				}
				
				
			}
			getMaximizacion(cupon, cuponOptimizado, items, true, amount);
			
		}
		
	}

}
