package com.devsuperior.dsmeta.services;

import com.devsuperior.dsmeta.dto.SaleAmountDateDTO;
import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.dto.SalesDateAmountNameSellerDTO;
import com.devsuperior.dsmeta.dto.SellerNameAmountDTO;
import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.repositories.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Optional;

@Service
public class SaleService {

	@Autowired
	private SaleRepository repository;

	private LocalDate today = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());

	@Transactional(readOnly = true)
	public SaleMinDTO findById(Long id) {
		Optional<Sale> result = repository.findById(id);
		Sale entity = result.get();
		return new SaleMinDTO(entity);
	}

	@Transactional(readOnly = true)
	public Page<SaleAmountDateDTO> getReportSalesForSeller(Pageable pageable) {
		Page<SaleAmountDateDTO> result = repository.searchSaleForSeller(pageable);
		return result.map(x->new SaleAmountDateDTO(x));
	}

	@Transactional(readOnly = true)
	public Page<SalesDateAmountNameSellerDTO> getReportSaleByNameAndDate(String minDate, String maxDate, String name, Pageable pageable) {

		LocalDate dataFinal = maxDate.equals("") ? today : LocalDate.parse(maxDate);
		LocalDate dataInicial = minDate.equals("") ? dataFinal.minusYears(1L): LocalDate.parse(minDate);

		Page<SalesDateAmountNameSellerDTO> result= repository.searchSaleByNameAndDate(dataInicial,dataFinal,name,pageable);
		return result.map(x->new SalesDateAmountNameSellerDTO(x));
	}

	@Transactional(readOnly = true)
	public Page<SellerNameAmountDTO> getSummarySellerNameAmount(Pageable pageable) {
		Page<SellerNameAmountDTO> result= repository.searchSellerNameAmount(pageable);
		return result.map(x->new SellerNameAmountDTO(x));
	}

	@Transactional(readOnly = true)
	public Page<SellerNameAmountDTO> getSummarySellerNameAmountForDate(String minDate, String maxDate, Pageable pageable){

		LocalDate dataFinal = maxDate.equals("") ? today : LocalDate.parse(maxDate);
		LocalDate dataInicial = minDate.equals("") ? dataFinal.minusYears(1L): LocalDate.parse(minDate);

		Page<SellerNameAmountDTO> result= repository.searchSellerNameAmount(dataInicial, dataFinal, pageable);

		return result.map(x->new SellerNameAmountDTO(x));
	}


}