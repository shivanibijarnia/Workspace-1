package com.cg.mypaymentapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cg.mypaymentapp.beans.Customer;

@Controller
public class URIController{
	@RequestMapping(value="/")
	public String getIndexPage() {
		return "indexPage";
	}
	
	@RequestMapping(value="/login")
	public String getLoginPage() {
		return "login";
	}
	
	@RequestMapping(value="/registration")
	public String getRegistrationPage() {
		return "registration";
	}
	
	@RequestMapping(value="/viewBalance")
	public String getBalancePage() {
		return "viewBalance";
	}
	
	@RequestMapping(value="/fundTransfer")
	public String getFundTransferPage() {
		return "fundTransfer";
	}
	@ModelAttribute("customer")
	public Customer getCustomer() {
		return new Customer();
	}
	@RequestMapping(value="/deposit")
	public String getDepositPage() {
		return "deposit";
	}
	@RequestMapping(value="/withdraw")
	public String getWithdrawPage() {
		return "withdraw";
	}
}
