package com.edstem.reporting_api.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class OrderDTO {
	private Long id;
	private LocalDate orderDate;
	private Long customer_id;
}
