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
@Table(uniqueConstraints = @UniqueConstraint(columnNames = { "advancedSearchId", "description" }))
public class SuggestedSearch {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	@JoinColumn(name = "advancedSearchId")
	private AdvancedSearch search;
	private String icon;
	private String description;
	@OneToMany(mappedBy = "search", cascade = CascadeType.ALL)
	private List<SuggestedSearchValue> values = new ArrayList<>();

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

	public List<SuggestedSearchValue> getValues() {
		return values;
	}

	public void addValue(SuggestedSearchValue value) {
		this.values.add(value);
		value.setSearch(this);
	}
	
	public void clearValues() {
		this.values.forEach(value -> value.setSearch(null));
		this.values.clear();
	}

}
