package com.cg.bankapplication.services;


import java.time.LocalDateTime;
import java.util.ArrayList;

import java.util.List;

import org.hibernate.type.descriptor.java.LocalDateTimeJavaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cg.bankapplication.dao.BankApplicationDao;
import com.cg.bankapplication.exceptions.InsufficientBalanceException;
import com.cg.bankapplication.exceptions.InvalidAccountNumberException;
import com.cg.bankapplication.exceptions.NegativeAmountException;
import com.cg.bankapplication.exceptions.SameAccountNumberException;
import com.cg.bankapplication.pojos.Account;
import com.cg.bankapplication.pojos.Customer;
import com.cg.bankapplication.pojos.Transaction;

@Component(value="BankApplicationServices")
public class BankApplicationServicesImpl implements BankApplicationServices
{
	@Autowired
	BankApplicationDao bankApplicationDaoImpl;
	int MINIMUMBALANCE=500;
	//for depositing money
		public double deposit(int accountNumber, int amount) throws InvalidAccountNumberException, NegativeAmountException 
		{
			Account account= bankApplicationDaoImpl.findById(accountNumber).orElseThrow(() -> new InvalidAccountNumberException("Invalid Account Number"));	
			if(account==null)
			{
				throw new InvalidAccountNumberException("Invalid Account Number");
			}
			if(amount<0)
			{
				throw new NegativeAmountException("Negative Amount");
			}
			double balanceAfterDeposit=account.getBalance()+amount;
			account.setBalance(balanceAfterDeposit);
			String transactionType="CREDIT";
			List<Transaction> transaction=account.getTransactions();
			Transaction tempTrasanction=new Transaction();
			tempTrasanction.setDate(LocalDateTime.now());
			tempTrasanction.setBalance(balanceAfterDeposit);
			tempTrasanction.setTransactionAmount(amount);
			tempTrasanction.setTransactionType(transactionType);
			tempTrasanction.setAccountId(account);
			transaction.add(tempTrasanction);
			bankApplicationDaoImpl.save(account);
			return account.getBalance();
		}

		//for withdraw money
		public double withdraw(int accountNumber, int amount) throws InvalidAccountNumberException, NegativeAmountException, InsufficientBalanceException {
			Account account= bankApplicationDaoImpl.findById(accountNumber).orElseThrow(() -> new InvalidAccountNumberException("Invalid Account Number"));	
			if(account==null)
			{
				throw new InvalidAccountNumberException("Invalid Account Number");
			}
			if(amount<0)
			{
				throw new NegativeAmountException("Negative Amount");
			}
			if((account.getBalance()-amount)<MINIMUMBALANCE || account.getBalance()<amount)
			{
				throw new InsufficientBalanceException("Insufficient Balance in Account");
			}
			double balanceAfterDeposit=account.getBalance()-amount;
			account.setBalance(balanceAfterDeposit);
			String transactionType="DEBIT";
			List<Transaction> transaction=account.getTransactions();
			Transaction tempTrasanction=new Transaction();
			tempTrasanction.setDate(LocalDateTime.now());
			tempTrasanction.setBalance(balanceAfterDeposit);
			tempTrasanction.setTransactionAmount(amount);
			tempTrasanction.setTransactionType(transactionType);
			tempTrasanction.setAccountId(account);
			transaction.add(tempTrasanction);
			bankApplicationDaoImpl.save(account);
			return account.getBalance();
		}

		//for fund transfer
		public double fundTransfer(int accountNumberSender, int accountNumberReceiver, int amount) throws InvalidAccountNumberException, NegativeAmountException, InsufficientBalanceException, SameAccountNumberException 
		{
			Account accountSender= bankApplicationDaoImpl.findById(accountNumberSender).orElseThrow(() -> new InvalidAccountNumberException("Invalid Account Number"));	
			Account accountReceiver= bankApplicationDaoImpl.findById(accountNumberReceiver).orElseThrow(() -> new InvalidAccountNumberException("Invalid Account Number"));	
 
			if(accountSender==accountReceiver)
				throw new SameAccountNumberException("Same Account Number");
			if(amount<0)
			{
				throw new NegativeAmountException("Negative Amount");
			}
				
				if(accountSender==null || accountReceiver==null)
				{
					throw new InvalidAccountNumberException("Invalid Account Number");

				}
			
			withdraw(accountSender.getAccountId(),amount);
			deposit(accountReceiver.getAccountId(),amount);
			return accountSender.getBalance();
		}

		//for show balance
		public double showBalance(int accountNumber) throws InvalidAccountNumberException {
			Account account= bankApplicationDaoImpl.findById(accountNumber).orElseThrow(() -> new InvalidAccountNumberException("Invalid Account Number"));	

			return account.getBalance();
			
		}
//show last transaction
		public List<Transaction> showLastTransaction(int accountNumber) throws InvalidAccountNumberException 
		{
			Account account= bankApplicationDaoImpl.findById(accountNumber).orElseThrow(() -> new InvalidAccountNumberException("Invalid Account Number"));
			if(account==null)
			throw new InvalidAccountNumberException("Please give porper account");
		List<Transaction> transaction=account.getTransactions();
		return transaction;
			
		}
		public Account createAccount(String fName,String lName)
		{
			Customer customer=new Customer();
			customer.setfName(fName);
			customer.setlName(lName);
			Account account=new Account();
			account.setBalance(0);
			account.setCustomer(customer);
			ArrayList<Transaction> transaction=new ArrayList<>();
			transaction.add(new Transaction(0,account,0.0, LocalDateTime.now(),0.0,"CREDIT"));
			account.setTransactions(transaction);
			bankApplicationDaoImpl.save(account);
			return account;
			
		}
		// ranged transaction
		public List<Transaction> getRangedTransaction(int accountNumber,LocalDateTime date1,LocalDateTime date2)throws InvalidAccountNumberException
		{
			Account account= bankApplicationDaoImpl.findById(accountNumber).orElseThrow(() -> new InvalidAccountNumberException("Invalid Account Number"));
			List<Transaction> transactions=account.getTransactions();
			List<Transaction> rangedTransactions=new ArrayList<Transaction>();
			for(Transaction tempTransaction:transactions)
				if(tempTransaction!=null && tempTransaction.getDate().compareTo(date1)>=0 && tempTransaction.getDate().compareTo(date2)<=0)
					rangedTransactions.add(tempTransaction);
			return rangedTransactions;
		}

}
