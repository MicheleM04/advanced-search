package it.mm.advancedSearch.controllers;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import it.mm.advancedSearch.core.dto.AdvancedSearchDTO;
import it.mm.advancedSearch.dto.factories.AdvancedSearchDTOFactory;
import it.mm.advancedSearch.entities.AdvancedSearch;
import it.mm.advancedSearch.repositories.AvdSearchRepository;

@RestController
@RequestMapping("advancedSearch")
public class AdvancedSearchController {

	@Autowired
	private AvdSearchRepository advSearchRepository;

	@GetMapping("")
	public List<AdvancedSearchDTO> findAll() {
		List<AdvancedSearch> queryResult = advSearchRepository.findAll();
		return queryResult.stream()
			.map(AdvancedSearchDTOFactory::convert)
			.collect(Collectors.toList());
	}
	
	@GetMapping("/byCompany/{companyCode}/andCode/{code}")
	public AdvancedSearchDTO findByCompanyCodeAndCode(@PathVariable String companyCode, @PathVariable String code) {
		Optional<AdvancedSearch> findByCompanyCodeAndCode = advSearchRepository.findByCompanyCodeAndCode(companyCode, code);
		if (!findByCompanyCodeAndCode.isPresent()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, 
				"Unable to find AdvancedSearch for companyCode: " + companyCode + " and code: " + code);
		}
		return AdvancedSearchDTOFactory.convert(findByCompanyCodeAndCode.get(), true, true);
	}

	@Transactional
	@GetMapping("/{advancedSearchId}")
	public AdvancedSearchDTO findById(@PathVariable Long advancedSearchId) {
		Optional<AdvancedSearch> findById = advSearchRepository.findById(advancedSearchId);
		if (!findById.isPresent()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,
				"Unable to find AdvancedSearch with id: " + advancedSearchId);
		}
		return AdvancedSearchDTOFactory.convert(findById.get(), true, true);
	}

}
