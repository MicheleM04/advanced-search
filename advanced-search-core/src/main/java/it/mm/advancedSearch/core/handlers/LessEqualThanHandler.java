package it.mm.advancedSearch.core.handlers;

import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;

import it.mm.advancedSearch.core.util.AdvancedSearchUtil;

public class LessEqualThanHandler implements AdvancedSearchOperatorHandler {

	@SuppressWarnings("unchecked")
	@Override
	public void handle(List<Predicate> predicates, CriteriaBuilder cb, Expression<?> path, Object value) {
		if (value != null) {
			if (value instanceof Date) {
				Date dateValue = (Date) value;
				predicates.add(cb.lessThanOrEqualTo((Expression<Date>) path, dateValue));
			} else if (value instanceof String) {
				String stringValue = (String) value;
				Date dateValue = AdvancedSearchUtil.parseDate(stringValue);
				predicates.add(cb.lessThanOrEqualTo((Expression<Date>) path, dateValue));
			} else if (value instanceof Number) {
				Number numberValue = (Number) value;
				predicates.add(cb.le((Expression<Number>) path, numberValue));
			} else {
				invalidType();
			}
		}
	}

}