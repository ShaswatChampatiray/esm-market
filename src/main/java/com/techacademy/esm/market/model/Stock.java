package com.techacademy.esm.market.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Stock {

	private Long stockId;
	private BigDecimal stockPrice;
	private LocalDate stockPurchaseDate;
	private String companyCode;

	public Long getStockId() {
		return stockId;
	}

	public void setStockId(Long stockId) {
		this.stockId = stockId;
	}

	public BigDecimal getStockPrice() {
		return stockPrice;
	}

	public void setStockPrice(BigDecimal stockPrice) {
		this.stockPrice = stockPrice;
	}

	public LocalDate getStockPurchaseDate() {
		return stockPurchaseDate;
	}

	public void setStockPurchaseDate(LocalDate stockPurchaseDate) {
		this.stockPurchaseDate = stockPurchaseDate;
	}

	public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

}
