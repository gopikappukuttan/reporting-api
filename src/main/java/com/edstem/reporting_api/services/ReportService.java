package com.edstem.reporting_api.services;

import com.edstem.reporting_api.dtos.PopularProduct;
import com.edstem.reporting_api.dtos.SalesByDate;
import com.edstem.reporting_api.dtos.TopCustomer;
import com.edstem.reporting_api.repositories.CustomerRepository;
import com.edstem.reporting_api.repositories.OrderItemsRepository;
import com.edstem.reporting_api.repositories.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportService {
	private final CustomerRepository customerRepository;
	private final OrderItemsRepository orderItemsRepository;
	private final OrderRepository orderRepository;

	public ReportService(CustomerRepository customerRepository, OrderItemsRepository orderItemsRepository, OrderRepository orderRepository) {
		this.customerRepository = customerRepository;
		this.orderItemsRepository = orderItemsRepository;
		this.orderRepository = orderRepository;
	}

	public List<TopCustomer> getTopSpendingCustomers() {
		return customerRepository.findTopSpendingCustomers();
	}

	public List<PopularProduct> getPopularProducts() {
		return orderItemsRepository.findMostPopularProducts();
	}

	public List<SalesByDate> getSalesByDate() {
		return orderRepository.findSalesByDate();
	}

	public Double getAverageOrderValue() {
		return orderRepository.findAverageOrderValue();
	}
}
