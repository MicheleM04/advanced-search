package it.mm.advancedSearch.dto.factories;

import it.mm.advancedSearch.core.dto.SavedSearchValueDTO;
import it.mm.advancedSearch.entities.SavedSearchValue;

public class SavedSearchValueDTOFactory {

	public static SavedSearchValueDTO convert(SavedSearchValue value) {
		SavedSearchValueDTO result = new SavedSearchValueDTO();
		result.setId(value.getId());
		result.setSavedSearchId(value.getSearch().getId());
		result.setFilter(AdvancedSearchFilterDTOFactory.convert(value.getFilter(), true));
		result.setOperator(AdvancedSearchFilterOperatorDTOFactory.convert(value.getOperator()));
		result.setValue(value.getValue());
		return result;
	}

}
