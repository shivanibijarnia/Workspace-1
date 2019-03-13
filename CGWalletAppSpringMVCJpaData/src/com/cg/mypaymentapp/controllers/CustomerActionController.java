package com.cg.mypaymentapp.controllers;

import java.math.BigDecimal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.cg.mypaymentapp.beans.Customer;
import com.cg.mypaymentapp.exception.InvalidInputException;
import com.cg.mypaymentapp.service.WalletService;

@Controller
public class CustomerActionController 
{
	@Autowired
	WalletService walletService;
	
	@RequestMapping(value="/registerCustomer")
	public ModelAndView registerCustomer(@Valid@ModelAttribute("customer")Customer customer, BindingResult result){
		
		if(result.hasErrors())return new ModelAndView("registration");
		
		customer=walletService.createAccount(customer);
		return new ModelAndView("registraionSuccessPage","customer",customer);
	}
	@RequestMapping(value="/loginCustomer")
	public ModelAndView loginCustomer(@ModelAttribute("customer")Customer customer, BindingResult result) {
			if(result.hasErrors())return new ModelAndView("login");
	
	
		customer=walletService.showBalance(customer.getMobileNo());
		return new ModelAndView("viewBalanceSuccess","customer",customer);
	}
	
	@RequestMapping(value="/fundTransferCustomer")
	public ModelAndView fundTransferCustomer(@RequestParam("mobileNo1")String mobileNo1, @RequestParam("mobileNo2")String mobileNo2, @RequestParam("wallet.balance")BigDecimal amount ) {
			
	

		Customer customer = walletService.fundTransfer(mobileNo1, mobileNo2, amount);
		return new ModelAndView("viewBalanceSuccess ","customer",customer);
	}
	
	@RequestMapping(value="/depositCustomer")
	public ModelAndView depositCustomer(@RequestParam("mobileNo")String mobileNo1,@RequestParam("wallet.balance")BigDecimal amount) {
			//if(result.hasErrors())return new ModelAndView("login");
	Customer customer=null;
		
		customer=walletService.depositAmount(mobileNo1,  amount);
		return new ModelAndView("viewBalanceSuccess","customer",customer);
	}
	
	@RequestMapping(value="/withdrawCustomer")
	public ModelAndView withdrawCustomer(@RequestParam("mobileNo")String mobileNo1,@RequestParam("wallet.balance")BigDecimal amount) {
			//if(result.hasErrors())return new ModelAndView("login");
	Customer customer=null;
		
		customer=walletService.withdrawAmount(mobileNo1,  amount);
		return new ModelAndView("viewBalanceSuccess","customer",customer);
	}
}
