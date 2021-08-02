package com.havendan.challengemelicupon.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.havendan.challengemelicupon.client.MeliClient;
import com.havendan.challengemelicupon.dto.Item;
import com.havendan.challengemelicupon.exception.ServiceException;

@Service
public class ApiConnection {
	private static final String URL = "https://api.mercadolibre.com";
	 
	/**
	 * Obtiene los precios  de una lista de items
	 * @param item_ids : lista de items de la que se va a buscar su informacion
	 * @return HashMap*/
	public HashMap<String, Float> getInfoItems(List<String> item_ids) throws ServiceException{	
		
		validarItems(new ArrayList<String>(item_ids));
		
		HashMap<String, Float> items = new HashMap<String, Float>();
		
		MeliClient cliente = new MeliClient(URL);
		
		for(String id : item_ids) {
			
			Item item = cliente.getInfoItem(id);
			items.put(item.getId(), item.getPrice());
			
			
		}
		
		
		return items;
	}
	
	/**Valida que no haya elementos repetidos en la lista y que la lista no este vacia.En caso de que haya elementos
	 *  repetidos o la lista este vacia se lanza una exepcion.
	 * @param item_ids lista de items a validar*/
	private void validarItems(List<String> item_ids) throws ServiceException {
		
		if (item_ids.size() == 0 ) {
			throw new ServiceException("List of Products is Empty", HttpStatus.BAD_REQUEST);
		}else {
			do {
			
				String id = item_ids.get(0);
				item_ids.remove(0);
				if (item_ids.size() > 0 && item_ids.contains(id)) {
					String msg = String.format("Item width Id %s is repeated", id);
					throw new ServiceException(msg , HttpStatus.BAD_REQUEST );
				}
				
				
			}while( item_ids.size() > 0);
			
		}
		
	}

}
