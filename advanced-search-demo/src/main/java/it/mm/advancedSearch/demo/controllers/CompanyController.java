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

import it.mm.advancedSearch.demo.entities.Company;
import it.mm.advancedSearch.demo.repositories.CompanyRepository;

@RestController
@RequestMapping("company")
public class CompanyController {

	@Autowired
	private CompanyRepository companyRepository;

	@GetMapping("")
	public List<Company> getAll() {
		return companyRepository.findAll();
	}

	@GetMapping("searchByText")
	public Page<Company> searchByString(@RequestParam() String searchText) {
		if (searchText == null) {
			searchText = "";
		}
		return companyRepository.findByNameContaining(searchText,
				PageRequest.of(0, 5, Direction.ASC, "name"));
	}

}
