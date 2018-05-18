package it.mm.advancedSearch.core.dto;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class AdvancedSearchDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String companyCode;
	private String code;
	private String description;
	private List<AdvancedSearchFilterDTO> filters;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<AdvancedSearchFilterDTO> getFilters() {
		return filters;
	}

	public void setFilters(List<AdvancedSearchFilterDTO> filters) {
		this.filters = filters;
	}

}