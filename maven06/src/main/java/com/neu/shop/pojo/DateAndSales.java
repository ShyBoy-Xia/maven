package com.neu.shop.pojo;


public class DateAndSales {
	private String date;
	
	private double sales;
	

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public double getSales() {
		return sales;
	}

	public void setSales(double sales) {
		this.sales = sales;
	}

	@Override
	public String toString() {
		return "DateAndSales [date=" + date + ", sales=" + sales + "]";
	}
	
	
	

}
