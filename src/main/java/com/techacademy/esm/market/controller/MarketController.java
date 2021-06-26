package com.techacademy.esm.market.controller;

import java.time.LocalDateTime;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techacademy.esm.market.model.Companies;
import com.techacademy.esm.market.model.Company;
import com.techacademy.esm.market.model.Stock;
import com.techacademy.esm.market.model.Stocks;
import com.techacademy.esm.market.service.MarketService;

@RestController
@RequestMapping("/api/v1.0/market")
public class MarketController {
	
	Logger logger = LoggerFactory.getLogger(MarketController.class);
	
	@Autowired
	private MarketService marketService;
	
	@PostMapping("/company/register")
	public void registerCompany(@RequestBody Company company) {
		logger.info("MarketController :: Invoked registerCompany method");
		marketService.registerCompany(company);
	}
	
	@GetMapping("/company/info/{companyCode}")
	public Company getCompanyByCode(@PathVariable String companyCode) {
		logger.info("MarketController :: Invoked getCompanyByCode method");
		return marketService.getCompanyByCode(companyCode);
	}

	@GetMapping("/company/getall")
	public Companies getAllCompanies() {
		logger.info("MarketController :: Invoked getAllCompanies method");
		return marketService.getAllCompanies();
	}

	@DeleteMapping("/company/delete/{companyCode}")
	public void deleteCompanyByCode(@PathVariable String companyCode) {
		logger.info("MarketController :: Invoked deleteCompanyByCode method");
		marketService.deleteCompanyByCode(companyCode);
	}
	
	@PostMapping("/stock/add/{companyCode}")
	public void addStock(@PathVariable String companyCode, @RequestBody Stock stock) {
		logger.info("MarketController :: Invoked addStock method");
		marketService.addStock(companyCode, stock);
	}

	@GetMapping(value = {"/stock/get/{companyCode}", "/stock/get/{companyCode}/{startDate}/{endDate}"})
	public Stocks getStock(@PathVariable String companyCode,
			@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Optional<LocalDateTime> startDate,
			@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Optional<LocalDateTime> endDate) {
		logger.info("MarketController :: Invoked getStock method");
		
		return marketService.getStock(companyCode, startDate, endDate);
	}

}
