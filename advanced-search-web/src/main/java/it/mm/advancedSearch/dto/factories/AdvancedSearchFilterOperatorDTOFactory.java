package it.mm.advancedSearch.dto.factories;

import it.mm.advancedSearch.core.domains.Operator;
import it.mm.advancedSearch.core.dto.AdvancedSearchFilterOperatorDTO;
import it.mm.advancedSearch.entities.AdvancedSearchFilterOperator;

public class AdvancedSearchFilterOperatorDTOFactory {
	
	public static AdvancedSearchFilterOperatorDTO convert(AdvancedSearchFilterOperator operator) {
		AdvancedSearchFilterOperatorDTO result = new AdvancedSearchFilterOperatorDTO();
		result.setId(operator.getId());
		result.setAdvancedSearchFilterId(operator.getFilter().getId());
		result.setField(operator.getField());
		Operator opt = operator.getOperator();
		result.setOperatorCode(opt.name());
		result.setOperatorUiCode(opt.getUiShortDescription());
		result.setOperatorUiDescription(opt.getUiDescription());
		result.setType(operator.getType());
		return result;
	}

}
