package com.neelam.ecom.controller;

import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.neelam.ecom.dto.RazorPayReqDTO;
import com.neelam.ecom.dto.RazorPayResDTO;
import com.neelam.ecom.entity.Product;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;

@RestController
@RequestMapping("razorpay")
public class RazorpayController {
	
	@PostMapping("/order")
	public ResponseEntity<RazorPayResDTO> getOrder(@RequestBody RazorPayReqDTO razorPayReqDTO) throws JsonMappingException, JsonProcessingException{
		RazorPayResDTO userDto = null;
		try {
			RazorpayClient razorpay = new RazorpayClient("rzp_test_av1wpTOyUA2Hbx", "FG2KYxeR9RNuxrvrZKbc3dzZ");

			JSONObject orderRequest = new JSONObject();
			int amount = razorPayReqDTO.getAmount().intValue();
			orderRequest.put("amount", amount); // amount in the smallest currency unit
			orderRequest.put("currency", "INR");
			orderRequest.put("receipt", "order_rcptid_11");

			Order order = razorpay.orders.create(orderRequest);
			JSONObject jsonObject = order.toJson();
			ObjectMapper objectMapper = new ObjectMapper();

	        // Convert the JSON object to a DTO object
			userDto =  objectMapper.readValue(jsonObject.toString(), RazorPayResDTO.class);

			} catch (RazorpayException e) {
			// Handle Exception
			System.out.println(e.getMessage());
			}
		return new ResponseEntity<>(userDto,HttpStatus.OK);
	}
}
