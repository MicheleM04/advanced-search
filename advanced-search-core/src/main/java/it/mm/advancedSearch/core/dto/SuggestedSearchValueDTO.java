package it.mm.advancedSearch.core.dto;

public class SuggestedSearchValueDTO {

	private Long id;
	private Long suggestedSearchId;
	private AdvancedSearchFilterDTO filter;
	private AdvancedSearchFilterOperatorDTO operator;
	private Object value;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getSuggestedSearchId() {
		return suggestedSearchId;
	}

	public void setSuggestedSearchId(Long suggestedSearchId) {
		this.suggestedSearchId = suggestedSearchId;
	}

	public AdvancedSearchFilterDTO getFilter() {
		return filter;
	}

	public void setFilter(AdvancedSearchFilterDTO filter) {
		this.filter = filter;
	}

	public AdvancedSearchFilterOperatorDTO getOperator() {
		return operator;
	}

	public void setOperator(AdvancedSearchFilterOperatorDTO operator) {
		this.operator = operator;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

}
