package it.mm.advancedSearch.core.handlers;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;

public class InHandler implements AdvancedSearchOperatorHandler {

	@Override
	public void handle(List<Predicate> predicates, CriteriaBuilder cb, Expression<?> path, Object value) {
		if (value != null) {
			if (value instanceof ArrayList<?>) {
				ArrayList<?> values = (ArrayList<?>) value;
				predicates.add(path.in(values));
			} else {
				invalidType();
			}
		}
	}

}
