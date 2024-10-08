package com.devsuperior.dsmeta.services;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.projections.SaleMinProjection;
import com.devsuperior.dsmeta.repositories.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Optional;

@Service
public class SaleService {

	@Autowired
	private SaleRepository repository;

	public SaleMinDTO findById(Long id) {
		Optional<Sale> result = repository.findById(id);
		Sale entity = result.get();
		return new SaleMinDTO(entity);
	}

	public Page<SaleMinDTO> searchPagedSalesWithInitialDateFinalDateAnSellerPartialName(
			String minDateStr, String maxDateStr, String name, Pageable pageable) {

		LocalDate minDate;
		LocalDate maxDate;

		try {
			minDate = LocalDate.parse(minDateStr);
			maxDate = LocalDate.parse(maxDateStr);
		} catch (DateTimeParseException e) {
			throw new IllegalArgumentException("Invalid date format");
		}
		Page<SaleMinProjection> saleMinProjectionPage = repository
				.searchByInitialDateFinalDateAndSellerPartialName(minDate, maxDate, name, pageable);
        return saleMinProjectionPage.map(SaleMinDTO::new);
	}
}
