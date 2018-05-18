package it.mm.advancedSearch.core.handlers;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;

public class LikeHandler implements AdvancedSearchOperatorHandler {

	@SuppressWarnings("unchecked")
	@Override
	public void handle(List<Predicate> predicates, CriteriaBuilder cb, Expression<?> path, Object value) {
		if (value != null) {
			if (value instanceof String) {
				String stringValue = (String) value;
				if (!stringValue.trim().equals("")) {
					predicates.add(cb.like((Expression<String>) path, "%" + stringValue + "%"));
				}
			} else {
				invalidType();
			}
		}
	}

}
