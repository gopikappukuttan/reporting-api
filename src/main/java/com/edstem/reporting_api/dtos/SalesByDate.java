package com.edstem.reporting_api.dtos;

import java.time.LocalDate;

public interface SalesByDate {
	LocalDate getDate();
	Double getTotalSales();
}
