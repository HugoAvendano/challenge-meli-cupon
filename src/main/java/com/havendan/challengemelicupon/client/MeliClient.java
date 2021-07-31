package com.havendan.challengemelicupon.client;


import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;


import org.springframework.http.HttpStatus;

import com.havendan.challengemelicupon.dto.Item;
import com.havendan.challengemelicupon.exception.ServiceException;
import com.havendan.challengemelicupon.response.ErrorResponse;
import com.havendan.challengemelicupon.util.ApplicationEndPoint;



public class MeliClient extends AbstractClient {
	
	public MeliClient(String url) {
        super(url);
    }
 
    public Item getInfoItem(String id) throws ServiceException {
        log.info("Getting item information");
        WebTarget client = createClient(ApplicationEndPoint.getPathItem(id));
        Response response = client.request(MediaType.APPLICATION_JSON).get();
        log.info("Status " + response.getStatus());
       // CoinPrice result = null;
        Item result = null;
       //String resultado; 
        Integer status = response.getStatus();
        if (Status.OK.getStatusCode() == status) {
        	
        	result = response.readEntity(Item.class);
        	
        } else {
        	ErrorResponse error =  response.readEntity(ErrorResponse.class);       	
            throw new ServiceException(error.getMessage(),HttpStatus.valueOf(status));
        }
        //System.out.println(result);
        return result;
    }
    
   
}
