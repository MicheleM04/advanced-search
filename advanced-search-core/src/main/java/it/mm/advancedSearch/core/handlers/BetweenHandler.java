package it.mm.advancedSearch.core.handlers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;

import it.mm.advancedSearch.core.util.AdvancedSearchUtil;

public class BetweenHandler implements AdvancedSearchOperatorHandler {

	@SuppressWarnings("unchecked")
	@Override
	public void handle(List<Predicate> predicates, CriteriaBuilder cb, Expression<?> path, Object value) {
		if (value != null) {
			if (value instanceof ArrayList<?>) {
				ArrayList<?> values = (ArrayList<?>) value;
				if (values.size() == 2) {
					Object first = values.get(0);
					Object last = values.get(1);
					if (first instanceof Date && last instanceof Date) {
						Date firstDate = (Date) first;
						Date lastDate = (Date) last;
						predicates.add(cb.between((Expression<Date>) path, firstDate, lastDate));
					} else if (first instanceof String && last instanceof String) {
						String firstStringValue = (String) first;
						Date firstDate = AdvancedSearchUtil.parseDate(firstStringValue);
						String lastStringValue = (String) last;
						Date lastDate = AdvancedSearchUtil.parseDate(lastStringValue);
						predicates.add(cb.between((Expression<Date>) path, firstDate, lastDate));
					} else if (first instanceof Number && last instanceof Number) {
						Number firstNumber = (Number) first;
						Number lastNumber = (Number) last;
						predicates.add(cb.and(cb.ge((Expression<Number>) path, firstNumber),
								cb.le((Expression<Number>) path, lastNumber)));
					} else {
						invalidType();
					}
				} else {
					invalidType();
				}
			} else {
				invalidType();
			}
		}
	}

}
