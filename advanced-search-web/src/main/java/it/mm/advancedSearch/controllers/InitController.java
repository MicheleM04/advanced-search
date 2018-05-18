package it.mm.advancedSearch.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.mm.advancedSearch.core.domains.Operator;
import it.mm.advancedSearch.core.domains.OperatorTypes;
import it.mm.advancedSearch.entities.AdvancedSearch;
import it.mm.advancedSearch.entities.AdvancedSearchFilter;
import it.mm.advancedSearch.entities.AdvancedSearchFilterOperator;
import it.mm.advancedSearch.entities.SuggestedSearch;
import it.mm.advancedSearch.entities.SuggestedSearchValue;
import it.mm.advancedSearch.repositories.AvdSearchRepository;
import it.mm.advancedSearch.repositories.SuggestedSearchRepository;

@RestController
@RequestMapping("init")
public class InitController {
	
	private static final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	@Autowired
	private AvdSearchRepository searchRepository;
	@Autowired
	private SuggestedSearchRepository suggestedSearchRepository;
	
	@PostMapping("/test")
	public void test() throws ParseException {
		createAdvancedSearch("C001", "S001","Advanced Search Test",
			createFilter("First Name", "firstName",
				createOperator("firstName", Operator.LIKE, "inputString"),
				createOperator("firstName",Operator.NOT_LIKE, "inputString")
			),
			createFilter("Last Name", "lastName",
				createOperator("lastName", Operator.LIKE, "inputString"),
				createOperator("lastName", Operator.NOT_LIKE, "inputString")
			),
			createFilter("Company", "company", 
				createOperator("company", Operator.LIKE, "inputString"),
				createOperator("company", Operator.IN, "companyEntity")
			),
			createFilter("Ages", "ages", 
				createOperator("ages", Operator.LESS_EQUAL_THAN, "inputNumber"),
				createOperator("ages", Operator.LESS_THAN, "inputNumber"),
				createOperator("ages", Operator.GREATER_THAN, "inputNumber"),
				createOperator("ages", Operator.GREATER_EQUAL_THAN, "inputNumber"),
				createOperator("ages", Operator.BETWEEN, "inputNumber")
			)
		);
		
		AdvancedSearchFilterOperator s1f1o1 = createOperator("numberField", Operator.LESS_EQUAL_THAN, OperatorTypes.DATE);
		AdvancedSearchFilterOperator s1f1o2 = createOperator("numberField", Operator.LESS_THAN, OperatorTypes.DATE);
		AdvancedSearchFilterOperator s1f1o3 = createOperator("numberField", Operator.GREATER_THAN, OperatorTypes.DATE);
		AdvancedSearchFilterOperator s1f1o4 = createOperator("numberField", Operator.GREATER_EQUAL_THAN, OperatorTypes.NUMBER);
		AdvancedSearchFilterOperator s1f1o5 = createOperator("numberField", Operator.BETWEEN, OperatorTypes.NUMBER_BETWEEN);
		AdvancedSearchFilter s1f1 = createFilter("Some Number Property", "numberField", s1f1o1, s1f1o2, s1f1o3, s1f1o4, s1f1o5);
		AdvancedSearchFilterOperator s1f2o1 = createOperator("stringField", Operator.EQUALS, OperatorTypes.STRING);
		AdvancedSearchFilterOperator s1f2o2 = createOperator("stringField", Operator.NOT_EQUALS, OperatorTypes.STRING);
		AdvancedSearchFilterOperator s1f2o3 = createOperator("stringField", Operator.START_WITH, OperatorTypes.STRING);
		AdvancedSearchFilterOperator s1f2o4 = createOperator("stringField", Operator.END_WITH, OperatorTypes.STRING);
		AdvancedSearchFilterOperator s1f2o5 = createOperator("stringField", Operator.LIKE, OperatorTypes.STRING);
		AdvancedSearchFilterOperator s1f2o6 = createOperator("stringField", Operator.NOT_START_WITH, OperatorTypes.STRING);
		AdvancedSearchFilterOperator s1f2o7 = createOperator("stringField", Operator.NOT_END_WITH, OperatorTypes.STRING);
		AdvancedSearchFilterOperator s1f2o8 = createOperator("stringField", Operator.NOT_LIKE, OperatorTypes.STRING);
		AdvancedSearchFilter s1f2 = createFilter("Some String Property", "stringField", s1f2o1, s1f2o2, s1f2o3, s1f2o4, s1f2o5, s1f2o6, s1f2o7, s1f2o8);
		AdvancedSearchFilterOperator s1f3o1 = createOperator("dateField", Operator.LESS_EQUAL_THAN, OperatorTypes.DATE);
		AdvancedSearchFilterOperator s1f3o2 = createOperator("dateField", Operator.LESS_THAN, OperatorTypes.DATE);
		AdvancedSearchFilterOperator s1f3o3 = createOperator("dateField", Operator.GREATER_THAN, OperatorTypes.DATE);
		AdvancedSearchFilterOperator s1f3o4 = createOperator("dateField", Operator.GREATER_EQUAL_THAN, OperatorTypes.DATE);
		AdvancedSearchFilterOperator s1f3o5 = createOperator("dateField", Operator.BETWEEN, OperatorTypes.DATE_BETWEEN);
		AdvancedSearchFilter s1f3 = createFilter("Some Date Property", "dateField", s1f3o1, s1f3o2, s1f3o3, s1f3o4, s1f3o5);
		AdvancedSearchFilterOperator s1f4o1 = createOperator("booleanField", Operator.IS, OperatorTypes.BOOLEAN);
		AdvancedSearchFilter s1f4 = createFilter("Some Boolean Property", "booleanField", s1f4o1);
		// REAL EXAMPLE - START
		AdvancedSearchFilterOperator s1f5o1 = createOperator("costCenter.description", Operator.LIKE, OperatorTypes.STRING);
		AdvancedSearchFilterOperator s1f5o2 = createOperator("costCenter.id", Operator.IN, "entity:CostCenter");
		AdvancedSearchFilter s1f5 = createFilter("Search by Cost Center", "costCenter", s1f5o1, s1f5o2);
		// REAL EXAMPLE - END
		AdvancedSearch f = createAdvancedSearch("C001", "S002","Advanced Search Test", s1f1, s1f2, s1f3, s1f4, s1f5);
		
		createSuggestedSearch(f, "label_important", "Suggested search test 001", 
			createValue(s1f1, s1f1o5, new Integer[] {10, 40}),
			createValue(s1f2, s1f2o5, "Test"),
			createValue(s1f4, s1f4o1, true)
		);
		createSuggestedSearch(f, "attach_file", "Suggested search test 002", 
			createValue(s1f1, s1f1o3, 25L),
			createValue(s1f2, s1f2o1, "Test2")
		);
		createSuggestedSearch(f, "today", "Suggested search test 003", 
			createValue(s1f3, s1f3o5, new Date[] {sdf.parse("01/01/2018"), sdf.parse("31/12/2018")}),
			createValue(s1f4, s1f4o1, true)
		);
	}
	
	private AdvancedSearch createAdvancedSearch(String companyCode, String code, String description,
			AdvancedSearchFilter... filters) {
		AdvancedSearch s = new AdvancedSearch();
		s.setCompanyCode(companyCode);
		s.setCode(code);
		s.setDescription(description);
		for (AdvancedSearchFilter filter : filters) {
			s.addFilter(filter);
		}
		return searchRepository.save(s);
	}

	private AdvancedSearchFilter createFilter(String uiDescription, String uiShortDescription, AdvancedSearchFilterOperator... operators) {
		AdvancedSearchFilter f = new AdvancedSearchFilter();
		f.setUiDescription(uiDescription);
		f.setUiShortDescription(uiShortDescription);
		for (AdvancedSearchFilterOperator operator : operators) {
			f.add(operator);
		}
		return f;
	}

	private AdvancedSearchFilterOperator createOperator(String field, Operator operator, String type) {
		AdvancedSearchFilterOperator o = new AdvancedSearchFilterOperator();
		o.setField(field);
		o.setOperator(operator);
		o.setType(type);
		return o;
	}
	
	private SuggestedSearch createSuggestedSearch(AdvancedSearch search, String icon, String description, SuggestedSearchValue... values) {
		SuggestedSearch s = new SuggestedSearch();
		s.setSearch(search);
		s.setIcon(icon);
		s.setDescription(description);
		for (SuggestedSearchValue v : values) {
			s.addValue(v);
		}
		return suggestedSearchRepository.save(s);
	}
	
	private SuggestedSearchValue createValue(AdvancedSearchFilter filter, AdvancedSearchFilterOperator operator, Object value) {
		SuggestedSearchValue v = new SuggestedSearchValue();
		v.setFilter(filter);
		v.setOperator(operator);
		v.setValue(value);
		return v;
	}

}
