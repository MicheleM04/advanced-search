package it.mm.advancedSearch.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.mm.advancedSearch.demo.entities.CostCenter;
import it.mm.advancedSearch.demo.repositories.CostCenterRepository;

@RestController
@RequestMapping("costCenter")
public class CostCenterController {

	@Autowired
	private CostCenterRepository costCenterRepository;

	@GetMapping("")
	public List<CostCenter> getAll() {
		return costCenterRepository.findAll();
	}

	@GetMapping("searchByText")
	public Page<CostCenter> searchByString(@RequestParam() String searchText) {
		if (searchText == null) {
			searchText = "";
		}
		return costCenterRepository.findByDescriptionContaining(searchText,
			PageRequest.of(0, 5, Direction.ASC, "description"));
	}

}
