package com.cg.bankapplication.pojos;

import java.time.LocalDateTime;
import java.util.Date;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Transaction {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int transactionId;
	
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name="account_id")
	private Account accountId;
	private double balance;
	@Column(name="date_of_transaction")
     private LocalDateTime date;
	@Column(name="transaction_amount")
   private double transactionAmount;
	@Column(name="transaction_type")
private String transactionType;
	
	public int getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}

	public Account getAccountId() {
		return accountId;
	}

	public void setAccountId(Account accountId) {
		this.accountId = accountId;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public double getTransactionAmount() {
		return transactionAmount;
	}

	public void setTransactionAmount(double transactionAmount) {
		this.transactionAmount = transactionAmount;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	@Override
	public String toString() {
		return "Transaction [transactionId=" + transactionId + ", accountId=" + accountId.getAccountId() + ", balance=" + balance
				+ ", date=" + date + ", transactionAmount=" + transactionAmount + ", transactionType=" + transactionType
				+ "]";
	}

	

	public Transaction(int transactionId, Account accountId, double balance, LocalDateTime date, double transactionAmount,
			String transactionType) {
		super();
		this.transactionId = transactionId;
		this.accountId = accountId;
		this.balance = balance;
		this.date = date;
		this.transactionAmount = transactionAmount;
		this.transactionType = transactionType;
	}

	public Transaction() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
