package it.mm.advancedSearch.dto.factories;

import it.mm.advancedSearch.core.dto.SuggestedSearchValueDTO;
import it.mm.advancedSearch.entities.SuggestedSearchValue;

public class SuggestedSearchValueDTOFactory {

	public static SuggestedSearchValueDTO convert(SuggestedSearchValue value) {
		SuggestedSearchValueDTO result = new SuggestedSearchValueDTO();
		result.setId(value.getId());
		result.setSuggestedSearchId(value.getSearch().getId());
		result.setFilter(AdvancedSearchFilterDTOFactory.convert(value.getFilter(), true));
		result.setOperator(AdvancedSearchFilterOperatorDTOFactory.convert(value.getOperator()));
		result.setValue(value.getValue());
		return result;
	}

}
