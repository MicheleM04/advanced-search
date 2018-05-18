package it.mm.advancedSearch.core.domains;

import it.mm.advancedSearch.core.handlers.AdvancedSearchOperatorHandler;
import it.mm.advancedSearch.core.handlers.BetweenHandler;
import it.mm.advancedSearch.core.handlers.EndWithHandler;
import it.mm.advancedSearch.core.handlers.EqualsHandler;
import it.mm.advancedSearch.core.handlers.GraterEqualThanHandler;
import it.mm.advancedSearch.core.handlers.GraterThanHandler;
import it.mm.advancedSearch.core.handlers.InHandler;
import it.mm.advancedSearch.core.handlers.IsHandler;
import it.mm.advancedSearch.core.handlers.IsNotNullHandler;
import it.mm.advancedSearch.core.handlers.IsNullHandler;
import it.mm.advancedSearch.core.handlers.LessEqualThanHandler;
import it.mm.advancedSearch.core.handlers.LessThanHandler;
import it.mm.advancedSearch.core.handlers.LikeHandler;
import it.mm.advancedSearch.core.handlers.NotEndWithHandler;
import it.mm.advancedSearch.core.handlers.NotEqualsHandler;
import it.mm.advancedSearch.core.handlers.NotLikeHandler;
import it.mm.advancedSearch.core.handlers.NotStartWithHandler;
import it.mm.advancedSearch.core.handlers.StartWithHandler;

public enum Operator {

	EQUALS("=", "Equals", new EqualsHandler()),
	NOT_EQUALS("!=", "Not equals", new NotEqualsHandler()),
	START_WITH("^%", "Start with", new StartWithHandler()),
	END_WITH("%$", "End with", new EndWithHandler()),
	LIKE("%", "Like", new LikeHandler()),
	NOT_START_WITH("!^%", "Not start with", new NotStartWithHandler()),
	NOT_END_WITH("!%$", "Not end with",new NotEndWithHandler()),
	NOT_LIKE("!%", "Not like",new NotLikeHandler()),
	GREATER_THAN(">", "Greater than",new GraterThanHandler()),
	GREATER_EQUAL_THAN(">=", "Greater equals than", new GraterEqualThanHandler()),
	LESS_THAN("<", "Less than", new LessThanHandler()),
	LESS_EQUAL_THAN("<=", "Less equal than", new LessEqualThanHandler()),
	IN("()", "In list", new InHandler()),
	BETWEEN("><", "Between", new BetweenHandler()),
	IS_NULL("NULL", "With no value", new IsNullHandler()),
	IS_NOT_NULL("!NULL", "With some value", new IsNotNullHandler()),
	IS("IS", "Is true or false", new IsHandler());

	private final String uiShortDescription;
	private final String uiDescription;
	private final AdvancedSearchOperatorHandler handler;

	private Operator(String uiShortDescription, String uiDescription, AdvancedSearchOperatorHandler handler) {
		this.uiShortDescription = uiShortDescription;
		this.uiDescription = uiDescription;
		this.handler = handler;
	}

	public String getUiShortDescription() {
		return uiShortDescription;
	}

	public String getUiDescription() {
		return uiDescription;
	}

	public AdvancedSearchOperatorHandler getHandler() {
		return this.handler;
	}

}
