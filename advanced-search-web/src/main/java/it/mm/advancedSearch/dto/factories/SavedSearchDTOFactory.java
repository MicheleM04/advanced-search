package it.mm.advancedSearch.dto.factories;

import java.util.stream.Collectors;

import it.mm.advancedSearch.core.dto.SavedSearchDTO;
import it.mm.advancedSearch.entities.SavedSearch;

public class SavedSearchDTOFactory {
	
	public static SavedSearchDTO convert(SavedSearch savedSearch) {
		return convert(savedSearch, false);
	}
	
	public static SavedSearchDTO convert(final SavedSearch savedSearch, boolean values) {
		SavedSearchDTO result = new SavedSearchDTO();
		result.setId(savedSearch.getId());
		result.setAdvancedSearchId(savedSearch.getSearch().getId());
		result.setDescription(savedSearch.getDescription());
		if (values) {
			result.setValues(savedSearch.getValues().stream()
				.map(value -> SavedSearchValueDTOFactory.convert(value))
				.collect(Collectors.toList()));
		}
		return result;
	}

}
