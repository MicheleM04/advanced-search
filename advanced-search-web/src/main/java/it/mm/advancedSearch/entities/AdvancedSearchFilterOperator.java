package it.mm.advancedSearch.entities;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import it.mm.advancedSearch.core.domains.Operator;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = { "advancedSearchFilterId", "field", "operator", "type" }))
public class AdvancedSearchFilterOperator {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	@JoinColumn(name = "advancedSearchFilterId")
	private AdvancedSearchFilter filter;
	private String field;
	@Enumerated(EnumType.STRING)
	private Operator operator;
	private String type; // key for FE

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public AdvancedSearchFilter getFilter() {
		return filter;
	}

	public void setFilter(AdvancedSearchFilter filter) {
		this.filter = filter;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public Operator getOperator() {
		return operator;
	}

	public void setOperator(Operator operator) {
		this.operator = operator;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
