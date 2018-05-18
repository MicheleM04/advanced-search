package it.mm.advancedSearch.core.handlers;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;

public class IsNullHandler implements AdvancedSearchOperatorHandler {

	@Override
	public void handle(List<Predicate> predicates, CriteriaBuilder cb, Expression<?> path, Object value) {
		predicates.add(cb.isNull(path));
	}

}
