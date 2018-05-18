package it.mm.advancedSearch.core.handlers;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;

public class IsHandler implements AdvancedSearchOperatorHandler {

	@SuppressWarnings("unchecked")
	@Override
	public void handle(List<Predicate> predicates, CriteriaBuilder cb, Expression<?> path, Object value) {
		if (value != null) {
			if (value instanceof Boolean) {
				boolean booleanValue = (Boolean) value;
				if (booleanValue) {
					predicates.add(cb.isTrue((Expression<Boolean>) path));
				} else {
					predicates.add(cb.isFalse((Expression<Boolean>) path));
				}
			} else {
				invalidType();
			}
		}
	}

}
