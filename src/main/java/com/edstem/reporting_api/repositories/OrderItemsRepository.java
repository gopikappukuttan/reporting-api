package com.edstem.reporting_api.repositories;

import com.edstem.reporting_api.dtos.PopularProduct;
import com.edstem.reporting_api.models.OrderItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderItemsRepository extends JpaRepository<OrderItems, Long> {
	@Query("SELECT oi.productName AS productName, SUM(oi.quantity) AS totalSold " +
			"FROM OrderItems oi GROUP BY oi.productName ORDER BY totalSold DESC")
	List<PopularProduct> findMostPopularProducts();
}
