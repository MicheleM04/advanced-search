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

@Entity
public class SavedSearch {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	@JoinColumn(name = "advancedSearchId")
	private AdvancedSearch search;
	private String description;
	@OneToMany(mappedBy = "search", cascade = CascadeType.ALL)
	private List<SavedSearchValue> values = new ArrayList<>();

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<SavedSearchValue> getValues() {
		return values;
	}

	public void addValue(SavedSearchValue value) {
		this.values.add(value);
		value.setSearch(this);
	}
	
	public void clearValues() {
		this.values.forEach(value -> value.setSearch(null));
		this.values.clear();
	}
	
}
