package com.javatechie.spring.batch.config;

import com.javatechie.spring.batch.entity.Customer;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.batch.item.ItemProcessor;

public class CustomerProcessor implements ItemProcessor<Customer,Customer> {

    @Override
    public Customer process(Customer customer) throws Exception {
    	int year = getYearFromDob(customer.getDob());
    	if ((year>= 2000)&&(customer.getCountry().equals("China")) && 
    	           customer.getGender().equals("Male")) {
    		return customer;
    	} else {
    		return null;
    	} 
    }

	private int getYearFromDob(String dob) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		LocalDate date = LocalDate.parse(dob, formatter);
		return date.getYear();
	}
   
}
