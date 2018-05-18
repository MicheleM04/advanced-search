package it.mm.advancedSearch.core.customRepositories;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import it.mm.advancedSearch.core.dto.AdvancedSearchPredicateDTO;

@NoRepositoryBean
public interface AdvancedSearchRepository<T, ID extends Serializable> extends JpaRepository<T, ID> {

	public List<T> advancedSearch(List<AdvancedSearchPredicateDTO> predicates);
	public Page<T> advancedSearch(List<AdvancedSearchPredicateDTO> predicates, Pageable pageable);

}
