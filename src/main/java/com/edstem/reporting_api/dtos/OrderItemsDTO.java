package com.edstem.reporting_api.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class OrderItemsDTO {private Long id;
	private String productName;
	private Integer quantity;
	private Double price;
	private Long order_id;
}
