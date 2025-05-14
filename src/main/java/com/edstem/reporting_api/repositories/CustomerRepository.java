package com.edstem.reporting_api.repositories;

import com.edstem.reporting_api.dtos.TopCustomer;
import com.edstem.reporting_api.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
	@Query("SELECT c.name AS customerName, SUM(oi.price * oi.quantity) AS totalSpent " +
			"FROM Customer c JOIN c.orders o JOIN o.orderItems oi " +
			"GROUP BY c.id, c.name ORDER BY totalSpent DESC")
	List<TopCustomer> findTopSpendingCustomers();
}
