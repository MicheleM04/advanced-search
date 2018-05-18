package it.mm.advancedSearch.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = { "companyCode", "code" }))
public class AdvancedSearch {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String companyCode;
	private String code;
	private String description;
	@OneToMany(mappedBy = "search", cascade = CascadeType.ALL)
	private List<AdvancedSearchFilter> filters = new ArrayList<>();

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

	public List<AdvancedSearchFilter> getFilters() {
		return filters;
	}

	public void addFilter(AdvancedSearchFilter filter) {
		this.filters.add(filter);
		filter.setSearch(this);
	}
	
	public void clearFilters() {
		this.filters.forEach(filter -> filter.setSearch(null));
		this.filters.clear();
	}

}
