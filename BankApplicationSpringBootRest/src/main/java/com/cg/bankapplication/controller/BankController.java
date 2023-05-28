package com.cg.bankapplication.controller;

import java.time.LocalDate;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cg.bankapplication.exceptions.InsufficientBalanceException;
import com.cg.bankapplication.exceptions.InvalidAccountNumberException;
import com.cg.bankapplication.exceptions.NegativeAmountException;
import com.cg.bankapplication.exceptions.SameAccountNumberException;
import com.cg.bankapplication.pojos.Account;
import com.cg.bankapplication.pojos.Customer;
import com.cg.bankapplication.pojos.Transaction;
import com.cg.bankapplication.services.BankApplicationServices;

@RestController
public class BankController {
	@Autowired
	private BankApplicationServices bankApplicationService;
	
	@PostMapping(value={"/createAccount"},consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Account> createAccount(@RequestBody Customer customer)
	{
		Account account;
		account=bankApplicationService.createAccount(customer.getfName(), customer.getlName());
		ResponseEntity<Account> responseEntity=new ResponseEntity<Account>(account,HttpStatus.OK);
		return responseEntity;
	}

	@PostMapping(value= {"/deposit"})
	public ResponseEntity<Double> deposit( int accountNumber, int amount) throws InvalidAccountNumberException, NegativeAmountException
	{
		double balance=bankApplicationService.deposit(accountNumber,amount);
		ResponseEntity<Double> responseEntity=new ResponseEntity<Double>(balance,HttpStatus.OK);
		return responseEntity;
	}
	@PostMapping(value= {"/withdraw"}) 
	public ResponseEntity<Double> withdraw( int accountNumber, int amount) throws InvalidAccountNumberException, NegativeAmountException, InsufficientBalanceException
	{
		double balance=bankApplicationService.withdraw(accountNumber,amount);
		ResponseEntity<Double> responseEntity=new ResponseEntity<Double>(balance,HttpStatus.OK);
		return responseEntity;
	}
	
	@GetMapping(value= {"/showBalance/{accountNumber}"})
	public ResponseEntity<Double> checkBalance(@PathVariable int accountNumber) throws InvalidAccountNumberException
	{
		double balance=bankApplicationService.showBalance(accountNumber);
		ResponseEntity<Double> responseEntity=new ResponseEntity<Double>(balance,HttpStatus.OK);
		return responseEntity;
	}
	
	@PostMapping(value= {"/fundTransfer/{accountSender}/{accountReceiver}/{amount}"})
	public ResponseEntity<Double> fundTransfer(@PathVariable int accountSender,@PathVariable int accountReceiver,@PathVariable int amount) throws InvalidAccountNumberException, NegativeAmountException, InsufficientBalanceException, SameAccountNumberException
	{
		double balance=bankApplicationService.fundTransfer(accountSender,accountReceiver,amount);
		ResponseEntity<Double> responseEntity=new ResponseEntity<Double>(balance,HttpStatus.OK);
		return responseEntity;
	}
	@GetMapping(value= {"/showLastTransaction/{accountNumber}"})
	public ResponseEntity<List<Transaction>> showLastTransaction(@PathVariable int accountNumber) throws InvalidAccountNumberException
	{
		List<Transaction> transactions=bankApplicationService.showLastTransaction(accountNumber);
		ResponseEntity<List<Transaction>> response=new ResponseEntity<>(transactions,HttpStatus.OK);
		return response;
	}
	@GetMapping(value= {"/showTransactionBetweenDates/{accountNumber}/{date1}/{date2}"})
	public ResponseEntity<List<Transaction>> showTransactionBetweenDates(@PathVariable int accountNumber,@PathVariable LocalDate date1,@PathVariable LocalDate date2) throws InvalidAccountNumberException{
	return new ResponseEntity<>(bankApplicationService.getRangedTransaction(accountNumber, date1.atStartOfDay(), date2.atStartOfDay()),HttpStatus.OK);
	}
	
}
