
package com.cg.mypaymentapp.service;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cg.mypaymentapp.beans.Customer;
import com.cg.mypaymentapp.beans.Wallet;
import com.cg.mypaymentapp.exception.InsufficientBalanceException;
import com.cg.mypaymentapp.exception.InvalidInputException;
import com.cg.mypaymentapp.repo.WalletRepo;

@Component(value="walletservices")
public class WalletServiceImpl implements WalletService {

	@Autowired(required=true)
	private WalletRepo repo;

	public WalletServiceImpl() {
	
	}
	@Transactional(propagation=Propagation.REQUIRED)
	public Customer showBalance(String mobileNo) {
		Customer customer = repo.findOne(mobileNo);
		if (customer != null)
			return customer;
		else
			throw new InvalidInputException("Invalid mobile no ");
	}
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public Customer fundTransfer(String sourceMobileNo, String targetMobileNo, BigDecimal amount) {

		if (sourceMobileNo.equals(targetMobileNo))
			throw new InvalidInputException("Source and target mobile number cannot be same");

		if (sourceMobileNo == null || targetMobileNo == null || amount == null)
			throw new NullPointerException();

		Customer sourceCustomer = withdrawAmount(sourceMobileNo, amount);
		depositAmount(targetMobileNo, amount);
		System.out.println("Fund Transfer suuccessfully");

		return sourceCustomer;
	}
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public Customer depositAmount(String mobileNo, BigDecimal amount) {
		// TODO Auto-generated method stub
		Customer customer = repo.findOne(mobileNo);

		if (customer == null)
			throw new InvalidInputException("Mobile number does not exist: " + mobileNo);

		Wallet wallet = customer.getWallet();
		BigDecimal oldamount = wallet.getBalance();
		wallet.setBalance(oldamount.add(amount));
		customer.setWallet(wallet);
//		repo.startTransaction();
//		
//		try {
//			repo.commitTransaction();
//		} catch (Exception e) {
//			throw new InvalidInputException(
//					"Mobile number does not Exist:" + mobileNo);
//		}
		return repo.save(customer);
	}
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public Customer withdrawAmount(String mobileNo, BigDecimal amount) {

		Customer customer = repo.findOne(mobileNo);
		if (customer == null)
			throw new InvalidInputException("Mobile number does not exist: " + mobileNo);

		Wallet wallet = customer.getWallet();
		BigDecimal oldamount = wallet.getBalance();
		int res = amount.compareTo(oldamount);
		if (res == 1) {
			throw new InsufficientBalanceException("Cannot withdraw Insufficient Balance");
		}

		wallet.setBalance(oldamount.subtract(amount));
		customer.setWallet(wallet);
//		repo.startTransaction();
//		
//		try {
//			repo.commitTransaction();
//		} catch (Exception e) {
//			throw new InvalidInputException(
//					"Mobile number does not Exist:" + mobileNo);
//		}

		return repo.save(customer);
	}
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public Customer createAccount(Customer customer) {
//		// TODO Auto-generated method stub
//		Wallet wallet = new Wallet(amount);
//		Customer customer = new Customer(name, mobileno, wallet);
//
//		if (name == null || mobileno == null || amount == null)
//			throw new NullPointerException();
//
//		else if (!isValidMobile(mobileno))
//			throw new InvalidInputException("Mobile no. should be of 10 digits");
//
//		else if (!isValidName(name))
//			throw new InvalidInputException("First letter should be capital of name");
////
////		repo.startTransaction();
////		
////		try {
////			repo.commitTransaction();
////		} catch (Exception e) {
////			throw new InvalidInputException(
////					"Account already exists with above mobile number:" + customer.getMobileNo());
////		}

		return repo.save(customer);
	}

	public boolean isValidMobile(String mobile) {
		String pattern = "[1-9][0-9]{9}";
		if (mobile.matches(pattern))
			return true;

		return false;
	}

	public boolean isValidName(String name) {
		String pattern = "[A-Z][a-z]*";
		if (name.matches(pattern))
			return true;

		return false;
	}
}
