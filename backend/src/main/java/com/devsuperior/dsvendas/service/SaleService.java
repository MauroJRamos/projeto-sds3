package com.devsuperior.dsvendas.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.dsvendas.dto.SaleDTO;
import com.devsuperior.dsvendas.entities.Sale;
import com.devsuperior.dsvendas.repositories.SaleRepository;
import com.devsuperior.dsvendas.repositories.SellerRepository;

@Service
public class SaleService {
	//Busca paginada dos vendas, com o s vendedores.
	@Autowired
	private SaleRepository repository;
	
	@Autowired
	private SellerRepository sellerRepository;//Com a instância dos sellerRepository.findAll a consulta trará de uma fez só todas as vendas de cada vendedor
	
	@Transactional(readOnly = true) // Para não fazer locking no banco
	public Page<SaleDTO> findAll(Pageable pageable){
		sellerRepository.findAll();
		Page<Sale> result = repository.findAll(pageable);
		return result.map(x -> new SaleDTO(x));
	}

}
