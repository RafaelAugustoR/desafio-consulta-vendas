package com.devsuperior.dsmeta.controllers;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.services.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sales")
public class SaleController {

	@Autowired
	private SaleService service;

	@GetMapping("/{id}")
	public ResponseEntity<SaleMinDTO> findById(@PathVariable Long id) {
		SaleMinDTO dto = service.findById(id);
		return ResponseEntity.ok(dto);
	}

	@GetMapping("/report")
	public ResponseEntity<Page<?>> getSalesDateSeller(
			@RequestParam(name = "minDate", defaultValue = "") String minDate,
			@RequestParam(name = "maxDate", defaultValue = "") String maxDate,
			@RequestParam(name = "name", defaultValue = "") String name,
			Pageable pageable) {

		if (minDate.equals("") && maxDate.equals("") && name.equals("")) {
			return ResponseEntity.ok(service.getReportSalesForSeller(pageable));
		} else {
			return ResponseEntity.ok(service.getReportSaleByNameAndDate(minDate, maxDate, name, pageable));
		}
	}

	@GetMapping("/summary")
	public ResponseEntity<Page<?>> getSummaryForDate(
		 @RequestParam(name = "minDate" ,defaultValue = "") String minDate,
		 @RequestParam(name = "maxDate" ,defaultValue = "") String maxDate,
		 Pageable pageable) {

		if (minDate.equals("") && maxDate.equals("") ) {
			return ResponseEntity.ok(service.getSummarySellerNameAmount(pageable));
		} else {
			return ResponseEntity.ok(service.getSummarySellerNameAmountForDate(minDate, maxDate, pageable));
		}
	}
}
