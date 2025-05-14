package com.edstem.reporting_api.repositories;

import com.edstem.reporting_api.dtos.SalesByDate;
import com.edstem.reporting_api.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
	@Query(value = "SELECT o.order_date AS date, SUM(oi.price * oi.quantity) AS total_sales " +
			"FROM orders o JOIN order_items oi ON o.id = oi.order_id " +
			"GROUP BY o.order_date ORDER BY o.order_date", nativeQuery = true)
	List<SalesByDate> findSalesByDate();

	@Query(value = "SELECT AVG(order_total) FROM (" +
			"SELECT o.id, SUM(oi.price * oi.quantity) AS order_total " +
			"FROM orders o " +
			"JOIN order_items oi ON o.id = oi.order_id " +
			"GROUP BY o.id) AS order_totals", nativeQuery = true)
	Double findAverageOrderValue();
}

