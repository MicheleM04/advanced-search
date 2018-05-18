package it.mm.advancedSearch.dto.factories;

import java.util.stream.Collectors;

import it.mm.advancedSearch.core.dto.AdvancedSearchFilterDTO;
import it.mm.advancedSearch.entities.AdvancedSearchFilter;

public class AdvancedSearchFilterDTOFactory {
	
	public static AdvancedSearchFilterDTO convert(AdvancedSearchFilter filter) {
		return convert(filter, false);
	}
	
	public static AdvancedSearchFilterDTO convert(AdvancedSearchFilter filter, boolean operators) {
		AdvancedSearchFilterDTO result = new AdvancedSearchFilterDTO();
		result.setId(filter.getId());
		result.setAdvancedSearchId(filter.getSearch().getId());
		result.setUiDescription(filter.getUiDescription());
		result.setUiShortDescription(filter.getUiShortDescription());
		if (operators) {
			result.setOperators(filter.getOperators().stream()
				.map(AdvancedSearchFilterOperatorDTOFactory::convert)
				.collect(Collectors.toList()));
		}
		return result;
	}
	
}
