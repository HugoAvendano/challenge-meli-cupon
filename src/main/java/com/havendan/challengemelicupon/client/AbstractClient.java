package com.havendan.challengemelicupon.client;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import org.apache.log4j.Logger;


public class AbstractClient {
	private String url;
    
    protected static final Logger log = Logger.getLogger(AbstractClient.class.getName());
 
    public AbstractClient(String url) {
        this.url = url;        
    }
 
    protected WebTarget createClient(String path) {
        String assembledPath = assembleEndpoint(path);
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(assembledPath);
        return target;
    }
 
    private String assembleEndpoint(String path) {
        String endpoint = url.concat(path);
        log.info(String.format("Calling endpoint %s", endpoint));
        return endpoint;
    }

}
