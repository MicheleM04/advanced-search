package it.mm.advancedSearch.controllers;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import it.mm.advancedSearch.core.dto.SuggestedSearchDTO;
import it.mm.advancedSearch.dto.factories.SuggestedSearchDTOFactory;
import it.mm.advancedSearch.entities.AdvancedSearch;
import it.mm.advancedSearch.entities.AdvancedSearchFilter;
import it.mm.advancedSearch.entities.AdvancedSearchFilterOperator;
import it.mm.advancedSearch.entities.SuggestedSearch;
import it.mm.advancedSearch.entities.SuggestedSearchValue;
import it.mm.advancedSearch.repositories.AdvancedSearchFilterOperatorRepository;
import it.mm.advancedSearch.repositories.AdvancedSearchFilterRepository;
import it.mm.advancedSearch.repositories.AvdSearchRepository;
import it.mm.advancedSearch.repositories.SuggestedSearchRepository;

@RestController
@RequestMapping("suggestedSearch")
public class SuggestedSearchController {
	
	@Autowired
	private SuggestedSearchRepository suggestedSearchRepository;
	@Autowired
	private AvdSearchRepository advSearchRepository;
	@Autowired
	private AdvancedSearchFilterRepository filterRepository;
	@Autowired
	private AdvancedSearchFilterOperatorRepository operatorRepository;

	@GetMapping("")
	public List<SuggestedSearchDTO> findAll() {
		List<SuggestedSearch> queryResult = suggestedSearchRepository.findAll();
		return queryResult.stream()
			.map(SuggestedSearchDTOFactory::convert)
			.collect(Collectors.toList());
	}
	
	@GetMapping("/byAdvancedSearch/{advancedSearchId}")
	public List<SuggestedSearchDTO> findByAdvancedSearchId(@PathVariable Long advancedSearchId) {
		List<SuggestedSearch> queryResult = suggestedSearchRepository.findBySearchId(advancedSearchId);
		return queryResult.stream()
			.map(SuggestedSearchDTOFactory::convert)
			.collect(Collectors.toList());
	}

	@Transactional
	@GetMapping("/{suggestedSearchId}")
	public SuggestedSearchDTO findById(@PathVariable Long suggestedSearchId) {
		Optional<SuggestedSearch> findById = suggestedSearchRepository.findById(suggestedSearchId);
		if (!findById.isPresent()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,
				"Unable to find SuggestedSearch with id: " + suggestedSearchId);
		}
		return SuggestedSearchDTOFactory.convert(findById.get(), true);
	}
	
	@PostMapping("")
	public Long create(@RequestBody SuggestedSearchDTO suggestedSearch) {
		SuggestedSearch toSave = new SuggestedSearch();
		Long advancedSearchId = suggestedSearch.getAdvancedSearchId();
		Optional<AdvancedSearch> findById = advSearchRepository.findById(advancedSearchId);
		if (!findById.isPresent()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,
				"Unable to find AdvancedSearch with id: " + advancedSearchId);
		}
		toSave.setSearch(findById.get());
		toSave.setDescription(suggestedSearch.getDescription());
		if (suggestedSearch.getValues() != null) {
			suggestedSearch.getValues().forEach(value -> {
				SuggestedSearchValue v = new SuggestedSearchValue();
				Long filteId = value.getFilter().getId();
				Optional<AdvancedSearchFilter> filterById = filterRepository.findById(filteId);
				if (filterById.isPresent()) {
					v.setFilter(filterById.get());
				} else {
					throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
							"Unable to find filter with id: " + filteId); 
				}
				Long operatorId = value.getOperator().getId();
				Optional<AdvancedSearchFilterOperator> operatorById = operatorRepository.findById(operatorId);
				if (operatorById.isPresent()) {
					v.setOperator(operatorById.get());
				} else {
					throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
							"Unable to find operator with id: " + operatorId); 
				}
				v.setValue(value.getValue());
				toSave.addValue(v);
			});
		}
		if (toSave.getValues().isEmpty()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
				"At least one values is required");
		}
		SuggestedSearch result = suggestedSearchRepository.save(toSave);
		return result.getId();
	}
	
	@PutMapping("")
	public SuggestedSearchDTO update(@RequestBody SuggestedSearchDTO suggestedSearch) {
		Long id = suggestedSearch.getId();
		Optional<SuggestedSearch> findById = suggestedSearchRepository.findById(id);
		if (!findById.isPresent()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,
				"Unable to find SuggestedSearch with id: " + id);
		}
		SuggestedSearch toUpdate = findById.get();
		if (toUpdate.getSearch().getId() != suggestedSearch.getAdvancedSearchId()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
				"Cant change referenced AdvancedSearch");
		}
		toUpdate.setDescription(suggestedSearch.getDescription());
		toUpdate.clearValues();
		if (suggestedSearch.getValues() != null) {
			suggestedSearch.getValues().forEach(value -> {
				SuggestedSearchValue v = new SuggestedSearchValue();
				Long filteId = value.getFilter().getId();
				Optional<AdvancedSearchFilter> filterById = filterRepository.findById(filteId);
				if (filterById.isPresent()) {
					v.setFilter(filterById.get());
				} else {
					throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
							"Unable to find filter with id: " + filteId); 
				}
				Long operatorId = value.getOperator().getId();
				Optional<AdvancedSearchFilterOperator> operatorById = operatorRepository.findById(operatorId);
				if (operatorById.isPresent()) {
					v.setOperator(operatorById.get());
				} else {
					throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
							"Unable to find operator with id: " + operatorId); 
				}
				v.setValue(value.getValue());
				toUpdate.addValue(v);
			});
		}
		if (toUpdate.getValues().isEmpty()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
				"At least one values is required");
		}
		SuggestedSearch result = suggestedSearchRepository.save(toUpdate);
		return SuggestedSearchDTOFactory.convert(result, true);
	}
	
	@DeleteMapping("/{suggestedSearchId}")
	public void delete(@PathVariable Long suggestedSearchId) {
		Optional<SuggestedSearch> findById = suggestedSearchRepository.findById(suggestedSearchId);
		if (!findById.isPresent()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,
				"Unable to find SuggestedSearch with id: " + suggestedSearchId);
		}
		suggestedSearchRepository.delete(findById.get());
	}

}
