package it.mm.advancedSearch.core.dto;

import java.io.Serializable;

public class AdvancedSearchFilterOperatorDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private Long advancedSearchFilterId;
	private String field;
	private String operatorCode;
	private String operatorUiCode;
	private String operatorUiDescription;
	private String type;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getAdvancedSearchFilterId() {
		return advancedSearchFilterId;
	}

	public void setAdvancedSearchFilterId(Long advancedSearchFilterId) {
		this.advancedSearchFilterId = advancedSearchFilterId;
	}

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

	public String getOperatorUiCode() {
		return operatorUiCode;
	}

	public void setOperatorUiCode(String operatorUiCode) {
		this.operatorUiCode = operatorUiCode;
	}

	public String getOperatorUiDescription() {
		return operatorUiDescription;
	}

	public void setOperatorUiDescription(String operatorUiDescription) {
		this.operatorUiDescription = operatorUiDescription;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
