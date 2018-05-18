package it.mm.advancedSearch.demo.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import it.mm.advancedSearch.demo.entities.Company;

public interface CompanyRepository extends JpaRepository<Company, Long> {
	
	public Page<Company> findByNameContaining(String searchText, Pageable page);

}
