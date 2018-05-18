package it.mm.advancedSearch.core.dto;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class SuggestedSearchDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private Long advancedSearchId;
	private String icon;
	private String description;
	private List<SuggestedSearchValueDTO> values;

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

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<SuggestedSearchValueDTO> getValues() {
		return values;
	}

	public void setValues(List<SuggestedSearchValueDTO> values) {
		this.values = values;
	}

}
