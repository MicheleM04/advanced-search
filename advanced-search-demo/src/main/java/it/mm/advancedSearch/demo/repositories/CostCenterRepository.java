package it.mm.advancedSearch.demo.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import it.mm.advancedSearch.demo.entities.CostCenter;

public interface CostCenterRepository extends JpaRepository<CostCenter, Long> {
	
	public Page<CostCenter> findByDescriptionContaining(String searchText, Pageable page);

}
