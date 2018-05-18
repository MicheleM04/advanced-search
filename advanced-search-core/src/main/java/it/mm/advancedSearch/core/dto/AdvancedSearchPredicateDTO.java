package it.mm.advancedSearch.core.dto;

public class AdvancedSearchPredicateDTO {
	
	private String field;
	private String operatorCode;
	private Object value; //TODO: maybe better JsonObject

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getOperatorCode() {
		return operatorCode;
	}

	public void setOperatorCode(String operatorCode) {
		this.operatorCode = operatorCode;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

}
