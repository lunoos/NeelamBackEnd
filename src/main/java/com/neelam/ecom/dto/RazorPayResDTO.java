package com.neelam.ecom.dto;

import java.util.List;

public class RazorPayResDTO {
	private Double amount;
	private Double amount_due;
	private Double amount_paid;
	private String currency;
	private String id;
	private List<String> notes;
	private Long created_at;
	private String receipt;
	private String entity;
	private String offer_id;
	private String status;
	private Number attempts;
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public Double getAmount_due() {
		return amount_due;
	}
	public void setAmount_due(Double amount_due) {
		this.amount_due = amount_due;
	}
	public Double getAmount_paid() {
		return amount_paid;
	}
	public void setAmount_paid(Double amount_paid) {
		this.amount_paid = amount_paid;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public List<String> getNotes() {
		return notes;
	}
	public Long getCreated_at() {
		return created_at;
	}
	public void setCreated_at(Long created_at) {
		this.created_at = created_at;
	}
	public String getReceipt() {
		return receipt;
	}
	public void setReceipt(String receipt) {
		this.receipt = receipt;
	}
	public String getEntity() {
		return entity;
	}
	public void setEntity(String entity) {
		this.entity = entity;
	}
	public String getOffer_id() {
		return offer_id;
	}
	public void setOffer_id(String offer_id) {
		this.offer_id = offer_id;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Number getAttempts() {
		return attempts;
	}
	public void setAttempts(Number attempts) {
		this.attempts = attempts;
	}
	public void setNotes(List<String> notes) {
		this.notes = notes;
	}
	@Override
	public String toString() {
		return "RazorPayResDTO [amount=" + amount + ", amount_due=" + amount_due + ", amount_paid=" + amount_paid
				+ ", currency=" + currency + ", id=" + id + "]";
	}
	
	
}
