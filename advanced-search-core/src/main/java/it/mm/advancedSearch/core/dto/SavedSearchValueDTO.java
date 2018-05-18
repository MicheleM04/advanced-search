package it.mm.advancedSearch.core.dto;

public class SavedSearchValueDTO {

	private Long id;
	private Long savedSearchId;
	private AdvancedSearchFilterDTO filter;
	private AdvancedSearchFilterOperatorDTO operator;
	private Object value;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getSavedSearchId() {
		return savedSearchId;
	}

	public void setSavedSearchId(Long savedSearchId) {
		this.savedSearchId = savedSearchId;
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
