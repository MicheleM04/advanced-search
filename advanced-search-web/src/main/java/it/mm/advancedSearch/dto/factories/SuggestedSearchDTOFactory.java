package it.mm.advancedSearch.dto.factories;

import java.util.stream.Collectors;

import it.mm.advancedSearch.core.dto.SuggestedSearchDTO;
import it.mm.advancedSearch.entities.SuggestedSearch;

public class SuggestedSearchDTOFactory {
	
	public static SuggestedSearchDTO convert(SuggestedSearch suggestedSearch) {
		return convert(suggestedSearch, false);
	}
	
	public static SuggestedSearchDTO convert(final SuggestedSearch suggestedSearch, boolean values) {
		SuggestedSearchDTO result = new SuggestedSearchDTO();
		result.setId(suggestedSearch.getId());
		result.setAdvancedSearchId(suggestedSearch.getSearch().getId());
		result.setIcon(suggestedSearch.getIcon());
		result.setDescription(suggestedSearch.getDescription());
		if (values) {
			result.setValues(suggestedSearch.getValues().stream()
				.map(value -> SuggestedSearchValueDTOFactory.convert(value))
				.collect(Collectors.toList()));
		}
		return result;
	}

}
