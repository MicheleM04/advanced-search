package it.mm.advancedSearch.core.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class AdvancedSearchFilterDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private Long advancedSearchId;
	private String uiDescription;
	private String uiShortDescription;
	private List<AdvancedSearchFilterOperatorDTO> operators = new ArrayList<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getAdvancedSearchId() {
		return advancedSearchId;
	}

	public void setAdvancedSearchId(Long advancedSearchId) {
		this.advancedSearchId = advancedSearchId;
	}

	public String getUiShortDescription() {
		return uiShortDescription;
	}

	public void setUiShortDescription(String uiShortDescription) {
		this.uiShortDescription = uiShortDescription;
	}

	public String getUiDescription() {
		return uiDescription;
	}

	public void setUiDescription(String uiDescription) {
		this.uiDescription = uiDescription;
	}

	public List<AdvancedSearchFilterOperatorDTO> getOperators() {
		return operators;
	}

	public void setOperators(List<AdvancedSearchFilterOperatorDTO> operators) {
		this.operators = operators;
	}

}
