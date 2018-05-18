package it.mm.advancedSearch.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import it.mm.advancedSearch.entities.AdvancedSearch;

public interface AvdSearchRepository extends JpaRepository<AdvancedSearch, Long> {
	
	public Optional<AdvancedSearch> findByCompanyCodeAndCode(String companyCode, String code);

}
