package it.mm.advancedSearch.core.dto;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class SavedSearchDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private Long advancedSearchId;
	private String description;
	private List<SavedSearchValueDTO> values;

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<SavedSearchValueDTO> getValues() {
		return values;
	}

	public void setValues(List<SavedSearchValueDTO> values) {
		this.values = values;
	}

}
