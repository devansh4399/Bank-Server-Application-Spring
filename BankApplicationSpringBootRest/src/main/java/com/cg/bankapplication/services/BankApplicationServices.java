package com.cg.bankapplication.services;



import java.time.LocalDateTime;
import java.util.List;

import com.cg.bankapplication.exceptions.InsufficientBalanceException;
import com.cg.bankapplication.exceptions.InvalidAccountNumberException;
import com.cg.bankapplication.exceptions.NegativeAmountException;
import com.cg.bankapplication.exceptions.SameAccountNumberException;
import com.cg.bankapplication.pojos.Account;
import com.cg.bankapplication.pojos.Transaction;

public interface BankApplicationServices {
	double deposit(int accountId,int amount) throws InvalidAccountNumberException, NegativeAmountException;
	double withdraw(int accountId,int amount) throws InvalidAccountNumberException, NegativeAmountException, InsufficientBalanceException;
	double fundTransfer(int accountNumberSender,int accountNumberReceiver,int amount) throws InvalidAccountNumberException, NegativeAmountException, InsufficientBalanceException, SameAccountNumberException;
	double showBalance(int accountNumber) throws InvalidAccountNumberException;
	List<Transaction> showLastTransaction(int accountNumber) throws InvalidAccountNumberException;
	public Account createAccount(String fName,String lName);
	public List<Transaction> getRangedTransaction(int accountNumber,LocalDateTime date1,LocalDateTime date2) throws InvalidAccountNumberException;
}
