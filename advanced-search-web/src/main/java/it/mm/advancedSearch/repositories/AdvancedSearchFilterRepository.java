package it.mm.advancedSearch.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import it.mm.advancedSearch.entities.AdvancedSearchFilter;

public interface AdvancedSearchFilterRepository extends JpaRepository<AdvancedSearchFilter, Long> {

}
