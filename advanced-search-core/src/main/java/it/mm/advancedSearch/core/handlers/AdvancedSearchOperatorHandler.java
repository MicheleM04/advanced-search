package it.mm.advancedSearch.core.handlers;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;

public interface AdvancedSearchOperatorHandler {

	void handle(List<Predicate> predicates, CriteriaBuilder cb, Expression<?> path, Object value);

	public default void invalidType() {
		throw new RuntimeException("Invalid value type for operator " + this.getClass().getName());
	}

}
