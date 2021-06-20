package com.techacademy.esm.market.model;

import java.util.List;

public class Companies {
	
	List<Company> companies;
	
	public Companies() {
		
	}

	public Companies(List<Company> companies) {
		super();
		this.companies = companies;
	}

	public List<Company> getCompanies() {
		return companies;
	}

	public void setCompanies(List<Company> companies) {
		this.companies = companies;
	}
	
	

}
