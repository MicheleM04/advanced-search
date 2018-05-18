package it.mm.advancedSearch.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import it.mm.advancedSearch.entities.SuggestedSearch;

public interface SuggestedSearchRepository extends JpaRepository<SuggestedSearch, Long> {
	
	public List<SuggestedSearch> findBySearchId(Long advancedSearchId);

}
	