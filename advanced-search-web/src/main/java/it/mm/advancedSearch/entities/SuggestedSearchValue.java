package it.mm.advancedSearch.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import com.fasterxml.jackson.databind.ObjectMapper;

@Entity
public class SuggestedSearchValue {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	@JoinColumn(name = "suggestedSearchId")
	private SuggestedSearch search;
	@ManyToOne
	@JoinColumn(name = "filterId")
	private AdvancedSearchFilter filter;
	@ManyToOne
	@JoinColumn(name = "operatorId")
	private AdvancedSearchFilterOperator operator;
	@Column(name = "value")
	private String stringValue;
	@Transient
	private Object value;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public SuggestedSearch getSearch() {
		return search;
	}

	public void setSearch(SuggestedSearch search) {
		this.search = search;
	}

	public AdvancedSearchFilter getFilter() {
		return filter;
	}

	public void setFilter(AdvancedSearchFilter filter) {
		this.filter = filter;
	}

	public AdvancedSearchFilterOperator getOperator() {
		return operator;
	}

	public void setOperator(AdvancedSearchFilterOperator operator) {
		this.operator = operator;
	}

	public Object getValue() {
		try {
			if (this.value != null) {
				return value;
			} else {
				ObjectMapper mapper = new ObjectMapper();
				return mapper.readValue(stringValue, Object.class);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public void setValue(Object value) {
		try {
			this.value = value;
			ObjectMapper mapper = new ObjectMapper();
			this.stringValue = mapper.writeValueAsString(value);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
