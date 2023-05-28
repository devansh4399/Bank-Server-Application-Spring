package com.cg.bankapplication.pojos;

import java.util.ArrayList;
import java.util.List;



import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
@Entity
public class Account {
	@Id 
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="account_id")
	private int accountId;
	@Column(name="balance")
	private double balance;
	
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="customer_id")
	private Customer customer;
	
	@ElementCollection
	@OneToMany(targetEntity=Transaction.class,mappedBy="accountId",fetch=FetchType.EAGER,cascade=CascadeType.ALL)
	private List<Transaction> transactions=new ArrayList<Transaction>();

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public List<Transaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<Transaction> transaction) {
		this.transactions=transaction;
	}

	@Override
	public String toString() {
		return "Account [accountId=" + accountId + ", balance=" + balance + ", customer=" + customer + ", transactions="
				+ transactions + "]";
	}

	public Account(int accountId, double balance, com.cg.bankapplication.pojos.Customer customer,
			List<com.cg.bankapplication.pojos.Transaction> transactions) {
		super();
		this.accountId = accountId;
		this.balance = balance;
		this.customer = customer;
		this.transactions = transactions;
	}

	public Account() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
