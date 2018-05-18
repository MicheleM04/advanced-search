package it.mm.advancedSearch.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = { "advancedSearchId", "uiShortDescription" }))
public class AdvancedSearchFilter {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	@JoinColumn(name = "advancedSearchId")
	private AdvancedSearch search;
	private String uiDescription;
	private String uiShortDescription;
	@OneToMany(mappedBy = "filter", cascade = CascadeType.ALL)
	private List<AdvancedSearchFilterOperator> operators = new ArrayList<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public AdvancedSearch getSearch() {
		return search;
	}

	public void setSearch(AdvancedSearch search) {
		this.search = search;
	}

	public String getUiDescription() {
		return uiDescription;
	}

	public void setUiDescription(String uiDescription) {
		this.uiDescription = uiDescription;
	}

	public String getUiShortDescription() {
		return uiShortDescription;
	}

	public void setUiShortDescription(String uiShortDescription) {
		this.uiShortDescription = uiShortDescription;
	}

	public List<AdvancedSearchFilterOperator> getOperators() {
		return operators;
	}

	public void add(AdvancedSearchFilterOperator operator) {
		this.operators.add(operator);
		operator.setFilter(this);
	}

}
