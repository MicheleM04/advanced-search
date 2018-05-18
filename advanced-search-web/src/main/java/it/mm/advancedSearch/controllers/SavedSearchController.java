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

import it.mm.advancedSearch.core.dto.SavedSearchDTO;
import it.mm.advancedSearch.dto.factories.SavedSearchDTOFactory;
import it.mm.advancedSearch.entities.AdvancedSearch;
import it.mm.advancedSearch.entities.AdvancedSearchFilter;
import it.mm.advancedSearch.entities.AdvancedSearchFilterOperator;
import it.mm.advancedSearch.entities.SavedSearch;
import it.mm.advancedSearch.entities.SavedSearchValue;
import it.mm.advancedSearch.repositories.AdvancedSearchFilterOperatorRepository;
import it.mm.advancedSearch.repositories.AdvancedSearchFilterRepository;
import it.mm.advancedSearch.repositories.AvdSearchRepository;
import it.mm.advancedSearch.repositories.SavedSearchRepository;

@RestController
@RequestMapping("savedSearch")
public class SavedSearchController {
	
	@Autowired
	private SavedSearchRepository savedSearchRepository;
	@Autowired
	private AvdSearchRepository advSearchRepository;
	@Autowired
	private AdvancedSearchFilterRepository filterRepository;
	@Autowired
	private AdvancedSearchFilterOperatorRepository operatorRepository;

	@GetMapping("")
	public List<SavedSearchDTO> findAll() {
		List<SavedSearch> queryResult = savedSearchRepository.findAll();
		return queryResult.stream()
			.map(SavedSearchDTOFactory::convert)
			.collect(Collectors.toList());
	}
	
	@GetMapping("/byAdvancedSearch/{advancedSearchId}")
	public List<SavedSearchDTO> findByAdvancedSearchId(@PathVariable Long advancedSearchId) {
		List<SavedSearch> queryResult = savedSearchRepository.findBySearchId(advancedSearchId);
		return queryResult.stream()
			.map(SavedSearchDTOFactory::convert)
			.collect(Collectors.toList());
	}

	@Transactional
	@GetMapping("/{savedSearchId}")
	public SavedSearchDTO findById(@PathVariable Long savedSearchId) {
		Optional<SavedSearch> findById = savedSearchRepository.findById(savedSearchId);
		if (!findById.isPresent()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,
				"Unable to find SavedSearch with id: " + savedSearchId);
		}
		return SavedSearchDTOFactory.convert(findById.get(), true);
	}
	
	@PostMapping("")
	public Long create(@RequestBody SavedSearchDTO savedSearch) {
		SavedSearch toSave = new SavedSearch();
		Long advancedSearchId = savedSearch.getAdvancedSearchId();
		Optional<AdvancedSearch> findById = advSearchRepository.findById(advancedSearchId);
		if (!findById.isPresent()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,
				"Unable to find AdvancedSearch with id: " + advancedSearchId);
		}
		toSave.setSearch(findById.get());
		toSave.setDescription(savedSearch.getDescription());
		if (savedSearch.getValues() != null) {
			savedSearch.getValues().forEach(value -> {
				SavedSearchValue v = new SavedSearchValue();
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
		SavedSearch result = savedSearchRepository.save(toSave);
		return result.getId();
	}
	
	@PutMapping("")
	public SavedSearchDTO update(@RequestBody SavedSearchDTO savedSearch) {
		Long id = savedSearch.getId();
		Optional<SavedSearch> findById = savedSearchRepository.findById(id);
		if (!findById.isPresent()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,
				"Unable to find SavedSearch with id: " + id);
		}
		SavedSearch toUpdate = findById.get();
		if (toUpdate.getSearch().getId() != savedSearch.getAdvancedSearchId()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
				"Cant change referenced AdvancedSearch");
		}
		toUpdate.setDescription(savedSearch.getDescription());
		toUpdate.clearValues();
		if (savedSearch.getValues() != null) {
			savedSearch.getValues().forEach(value -> {
				SavedSearchValue v = new SavedSearchValue();
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
		SavedSearch result = savedSearchRepository.save(toUpdate);
		return SavedSearchDTOFactory.convert(result, true);
	}
	
	@DeleteMapping("/{savedSearchId}")
	public void delete(@PathVariable Long savedSearchId) {
		Optional<SavedSearch> findById = savedSearchRepository.findById(savedSearchId);
		if (!findById.isPresent()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,
				"Unable to find SavedSearch with id: " + savedSearchId);
		}
		savedSearchRepository.delete(findById.get());
	}
	
}
