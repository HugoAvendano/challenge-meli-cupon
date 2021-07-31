package com.havendan.challengemelicupon;


import java.util.Arrays;
import java.util.List;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.havendan.challengemelicupon.dto.Cupon;
import com.havendan.challengemelicupon.request.RequestCupon;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CuponControllerTest {
	
	private static final Logger logger = LoggerFactory.getLogger(CuponControllerTest.class);
    
    @Autowired
    private MockMvc mockMvc;
 
    @Autowired
    ObjectMapper objectmapper;
    
    private String stringResponseTest1;

	@Before
	public void setUp() throws Exception {
		this.stringResponseTest1 ="{\r\n" + 
				"    \"item_ids\": [\"MLA811600001\"],\r\n" + 
				"    \"amount\" : 5000.00\r\n" + 
				"}";
	}
	
	/**
	 * TEST 1: CASO SATISFACTORIO UN PRODUCTO
	 * */
	
	@Test
	public void testGetCupon() throws  Exception {
		List<String> item_ids = Arrays.asList("MLA811600001");
		
		
		
		RequestCupon cuponRequest = new RequestCupon();
		cuponRequest.setItem_ids(item_ids);
		cuponRequest.setAmount(5000.00f);
		
		Cupon cuponResponseExpected = objectmapper.readValue(this.stringResponseTest1, Cupon.class);
		
		
        
        String Cuponresponse = mockMvc.perform(post("http://localhost:8080/coupon/")
                .content(objectmapper.writeValueAsString(cuponRequest))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(HttpStatus.OK.value()))
                .andReturn().getResponse().getContentAsString();
        assertEquals(cuponResponseExpected, objectmapper.readValue(Cuponresponse, Cupon.class));
        
        logger.info(Cuponresponse);
	}

}
