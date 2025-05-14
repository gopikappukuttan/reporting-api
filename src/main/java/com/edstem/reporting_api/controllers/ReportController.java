package com.edstem.reporting_api.controllers;

import com.edstem.reporting_api.dtos.*;
import com.edstem.reporting_api.models.Customer;
import com.edstem.reporting_api.models.Order;
import com.edstem.reporting_api.models.OrderItems;
import com.edstem.reporting_api.repositories.CustomerRepository;
import com.edstem.reporting_api.repositories.OrderItemsRepository;
import com.edstem.reporting_api.repositories.OrderRepository;
import com.edstem.reporting_api.services.ReportService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reports")
public class ReportController {

	private final CustomerRepository customerRepository;
	private final OrderRepository orderRepository;
	private final OrderItemsRepository orderItemsRepository;
	private final ReportService reportService;

	public ReportController(CustomerRepository customerRepository, OrderRepository orderRepository, OrderItemsRepository orderItemsRepository, ReportService reportService) {
		this.customerRepository = customerRepository;
		this.orderRepository = orderRepository;
		this.orderItemsRepository = orderItemsRepository;
		this.reportService = reportService;
	}

	@GetMapping("/top-customers")
	public List<TopCustomer> getTopCustomers() {
		return reportService.getTopSpendingCustomers();
	}

	@GetMapping("/popular-products")
	public List<PopularProduct> getPopularProducts() {
		return reportService.getPopularProducts();
	}

	@GetMapping("/sales-by-date")
	public List<SalesByDate> getSalesByDate() {
		return reportService.getSalesByDate();
	}

	@PostMapping("/customers")
	public ResponseEntity<?> createCustomer(@RequestBody Customer customer) {
		Customer savedCustomer = customerRepository.save(customer);
		CustomerDTO customerDTO = new CustomerDTO(savedCustomer.getId(), savedCustomer.getName(), savedCustomer.getEmail());
		return ResponseEntity.status(HttpStatus.CREATED).body(customerDTO);
	}

	@PostMapping("/orders")
	public ResponseEntity<?> createOrder(@RequestBody Order order) {
		Order savedOrder = orderRepository.save(order);
		OrderDTO orderDTO = new OrderDTO(savedOrder.getId(), savedOrder.getOrderDate(), savedOrder.getCustomer().getId());
		return ResponseEntity.status(HttpStatus.CREATED).body(orderDTO);
	}

	@PostMapping("/order-items")
	public ResponseEntity<?> createOrderItem(@RequestBody OrderItems orderItem) {
		OrderItems savedOrderItem = orderItemsRepository.save(orderItem);
		OrderItemsDTO orderItemsDTO = new OrderItemsDTO(savedOrderItem.getId(),
				savedOrderItem.getProductName(), savedOrderItem.getQuantity(),
				savedOrderItem.getPrice(), savedOrderItem.getOrder().getId());
		return ResponseEntity.status(HttpStatus.CREATED).body(orderItemsDTO);
	}

	@GetMapping("/average-order-value")
	public ResponseEntity<Double> getAverageOrderValue() {
		Double avg = reportService.getAverageOrderValue();
		return ResponseEntity.ok(avg);
	}
}

