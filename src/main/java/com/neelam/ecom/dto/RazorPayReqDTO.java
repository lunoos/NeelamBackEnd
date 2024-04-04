package com.neelam.ecom.dto;

import java.math.BigDecimal;

public class RazorPayReqDTO {
	private BigDecimal amount;
	
	public BigDecimal getAmount() {
		return amount;
	}
	
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	
	@Override
	public String toString() {
		return "RazorPayReqDTO [amount=" + amount + "]";
	}
	
	
	
}
