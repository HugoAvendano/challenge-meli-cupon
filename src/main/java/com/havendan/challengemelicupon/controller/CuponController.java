package com.havendan.challengemelicupon.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.havendan.challengemelicupon.dto.Cupon;
import com.havendan.challengemelicupon.exception.ServiceException;
import com.havendan.challengemelicupon.request.RequestCupon;
import com.havendan.challengemelicupon.response.ErrorResponse;
import com.havendan.challengemelicupon.service.ApiConnection;
import com.havendan.challengemelicupon.service.CuponService;

@RestController
@RequestMapping(path="/coupon")
public class CuponController {
	
	@Autowired
	private CuponService serviceManager;
	
	@Autowired
	private ApiConnection apiConnection;
	
	@PostMapping(path="/", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> getCupon(@RequestBody RequestCupon productos) {
		
		
		try {
			HashMap<String,Float> items = apiConnection.getInfoItems(productos.getItem_ids());			
			Cupon cupon = serviceManager.calculate(items, productos.getAmount());
			
			return   ResponseEntity.status(HttpStatus.OK).body(cupon) ;
		}catch (ServiceException e) {
			
			//e.printStackTrace();
			
			return  ResponseEntity.status(e.getHttpStatus()).body(new ErrorResponse( e.getMessage(),e.getHttpStatus().name(),e.getHttpStatus().value()));

		}
		
		
		
		
	} 

}
