package com.techacademy.esm.market.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.techacademy.esm.market.model.Companies;
import com.techacademy.esm.market.model.Company;
import com.techacademy.esm.market.model.Stock;
import com.techacademy.esm.market.model.Stocks;

@Service
public class MarketService {

	@Autowired
	RestTemplate restTemplate;

	public void registerCompany(Company company) {
		restTemplate.postForObject("http://esm-company/company/register", company, Company.class);
	}

	public Company getCompanyByCode(String companyCode) {
		Company company = restTemplate.getForObject("http://esm-company/company/info/" + companyCode, Company.class);
		Stock stock = restTemplate.getForObject("http://esm-stock/stock/get/latest/" + companyCode, Stock.class);
		company.setStock(stock);
		return company;
	}

	public Companies getAllCompanies() {
		Companies companies = restTemplate.getForObject("http://esm-company/company/getall/", Companies.class);
		companies.getCompanies().forEach(company -> {
			company.setStock(restTemplate.getForObject("http://esm-stock/stock/get/latest/" + company.getCompanyCode(), Stock.class));
		});
		return companies;
	}

	public void deleteCompanyByCode(String companyCode) {
		restTemplate.delete("http://esm-company/company/delete/" + companyCode);
		restTemplate.delete("http://esm-stock/stock/delete/company/" + companyCode);

	}

	public void addStock(String companyCode, Stock stock) {

		stock.setCompanyCode(companyCode);
		stock.setStockPurchaseDate(LocalDate.now());
		restTemplate.postForObject("http://esm-stock/stock/add/"+ companyCode, stock, Stock.class);
	}

	public Stocks getStock(String companyCode, Optional<LocalDateTime> startDate, Optional<LocalDateTime> endDate) {
		Stocks stocks;
		
		if (startDate.isPresent() && endDate.isPresent()) 
			stocks = restTemplate.getForObject("http://esm-stock/stock/get/" + companyCode + "/" + startDate.get() + "/" + endDate.get(), Stocks.class);
	    else 
	    	stocks = restTemplate.getForObject("http://esm-stock/stock/get/" + companyCode, Stocks.class);
		
		return stocks;
	}

}
