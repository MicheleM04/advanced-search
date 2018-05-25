package it.mm.advancedSearch.core.customRepositories;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import it.mm.advancedSearch.core.domains.Operator;
import it.mm.advancedSearch.core.dto.AdvancedSearchPredicateDTO;

public class AdvancedSearchRepositoryImpl<T, ID extends Serializable> extends SimpleJpaRepository<T, ID>
		implements AdvancedSearchRepository<T, ID> {

	public AdvancedSearchRepositoryImpl(JpaEntityInformation<T, ID> entityInformation, EntityManager entityManager) {
		super(entityInformation, entityManager);
	}

	@Override
	public List<T> advancedSearch(List<AdvancedSearchPredicateDTO> predicates) {
		if (predicates != null && !predicates.isEmpty()) {
			return findAll(getSpecification(predicates));
		} else {
			return findAll();
		}
	}

	@Override
	public Page<T> advancedSearch(List<AdvancedSearchPredicateDTO> predicates, Pageable pageable) {
		if (predicates != null && !predicates.isEmpty()) {
			return findAll(getSpecification(predicates), pageable);
		} else {
			return findAll(pageable);
		}
	}

	private Specification<T> getSpecification(List<AdvancedSearchPredicateDTO> predicates) {
		return new Specification<T>() {
			private static final long serialVersionUID = 1L;
			@Override
			public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				query.distinct(true);
				List<Predicate> queryPredicates = new ArrayList<>();
				Map<String, Join<Object, Object>> joins = new HashMap<>();
				predicates.forEach(predicate -> {
					Expression<String> path = getPath(root, predicate.getField(), joins);
					Operator operator = Operator.valueOf(predicate.getOperatorCode());
					operator.getHandler().handle(queryPredicates, criteriaBuilder, path, predicate.getValue());
				});
				Predicate[] queryPredicateArray = new Predicate[queryPredicates.size()];
				return criteriaBuilder.and(queryPredicates.toArray(queryPredicateArray));
			}
		};
	}

	private Expression<String> getPath(Root<T> root, String path, Map<String, Join<Object, Object>> joins) {
		String[] paths = path.split("\\.");
		if (paths.length > 1) {
			Join<Object, Object> join = findOrCreateJoin(root, paths, joins);
			return join.get(paths[paths.length - 1]);
		} else {
			return root.get(path);
		}
	}

	private Join<Object, Object> findOrCreateJoin(Root<T> root, String[] paths, Map<String, Join<Object, Object>> joins) {
		String path = getPath(paths, 0);
		Join<Object, Object> result = joins.get(path);
		if (result == null) {
			result = root.join(paths[0]);
			joins.put(path, result);
		}
		for (int i = 1; i < paths.length - 1; i++) {
			path = getPath(paths, i);
			Join<Object, Object> nextResult = joins.get(path);
			if (nextResult == null) {
				nextResult = result.join(paths[i]);
				joins.put(path, nextResult);
			}
			result = nextResult;
		}
		return result;
	}
	
	private String getPath(String[] paths, int index) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i <= index; i++) {
			sb.append(paths[i])
				.append(".");
		}
		sb.deleteCharAt(sb.lastIndexOf("."));
		return sb.toString();
	}

}
