package it.mm.advancedSearch.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import it.mm.advancedSearch.entities.SavedSearch;

public interface SavedSearchRepository extends JpaRepository<SavedSearch, Long> {
	
	public List<SavedSearch> findBySearchId(Long advancedSearchId);

}
