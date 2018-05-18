package it.mm.advancedSearch.dto.factories;

import java.util.stream.Collectors;

import it.mm.advancedSearch.core.dto.AdvancedSearchDTO;
import it.mm.advancedSearch.entities.AdvancedSearch;

public class AdvancedSearchDTOFactory {
	
	public static AdvancedSearchDTO convert(AdvancedSearch advancedSearch) {
		return convert(advancedSearch, false, false);
	}
	
	public static AdvancedSearchDTO convert(AdvancedSearch advancedSearch, boolean filters) {
		return convert(advancedSearch, filters, false);
	}
	
	public static AdvancedSearchDTO convert(AdvancedSearch advancedSearch, boolean filters, boolean operators) {
		AdvancedSearchDTO result = new AdvancedSearchDTO();
		result.setId(advancedSearch.getId());
		result.setCompanyCode(advancedSearch.getCompanyCode());
		result.setCode(advancedSearch.getCode());
		result.setDescription(advancedSearch.getDescription());
		if (filters) {
			result.setFilters(advancedSearch.getFilters().stream()
				.map(filter -> AdvancedSearchFilterDTOFactory.convert(filter, operators))
				.collect(Collectors.toList()));
		}
		return result;
	}

}
