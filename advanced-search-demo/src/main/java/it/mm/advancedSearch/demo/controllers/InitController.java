package it.mm.advancedSearch.demo.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.mm.advancedSearch.demo.domain.Sex;
import it.mm.advancedSearch.demo.entities.Company;
import it.mm.advancedSearch.demo.entities.CostCenter;
import it.mm.advancedSearch.demo.entities.Project;
import it.mm.advancedSearch.demo.entities.User;
import it.mm.advancedSearch.demo.repositories.CompanyRepository;
import it.mm.advancedSearch.demo.repositories.CostCenterRepository;
import it.mm.advancedSearch.demo.repositories.ProjectRepository;
import it.mm.advancedSearch.demo.repositories.UserRepository;

@RestController
@RequestMapping("init")
public class InitController {
	
	private static final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private CompanyRepository companyRepository;
	@Autowired
	private CostCenterRepository costCenterRepository;
	@Autowired
	private ProjectRepository projectRepository;
	
	@PostMapping("/test")
	public void test() throws ParseException {
		Company c1 = createCompany("Google");
		Company c2 = createCompany("Facebook");
		Company c3 = createCompany("Apple");
		
		CostCenter cc1 = createCostCenter("IT", "Information Technology");
		CostCenter cc2 = createCostCenter("HR", "Human Resources");
		
		Project p1 = createProject("Test P1", cc1, "01/01/2018", "31/12/2018");
		Project p2 = createProject("Test P2", cc2, "01/02/2018", "30/06/2018");
		Project p3 = createProject("Test P3", cc1, "01/03/2018", null);

		createUser("Mario", "Rossi", "mario.rossi@test.com", "+39 3334455666", 35, c1, cc1, Sex.MALE, p1, p2);
		createUser("Enzo", "Bianchi", "enzo.bianchi@test.com", "+39 3334455666", 53, c1, cc2, Sex.MALE, p1, p2, p3);
		createUser("Maria", "Rossi", "maria.rossi@test.com", "+39 3334455666", 25, c1, cc2, Sex.FEMALE, p1);
		createUser("Giovanni", "Rossi", "giovanni.rossi@test.com", "+39 3334455666", 45, c2, cc1, Sex.MALE, p3);
		createUser("Stefano", "Monti", "stefano.monti@test.com", "+39 3334455666", 28, c2, cc1, Sex.MALE, p2, p3);
		createUser("Rosa", "Garibaldi", "rosa.garibaldi@test.com", "+39 3334455666", 22, c3, cc2, Sex.FEMALE, p1, p2, p3);
	}
	
	private Company createCompany(String name) {
		Company c = new Company();
		c.setName(name);
		return companyRepository.save(c);
	}

	private CostCenter createCostCenter(String code, String description) {
		CostCenter cc = new CostCenter();
		cc.setCode(code);
		cc.setDescription(description);
		return costCenterRepository.save(cc);
	}

	private Project createProject(String name, CostCenter mainCostCenter, String startDate, String endDate)
			throws ParseException {
		Project p = new Project();
		p.setName(name);
		p.setMainCostCenter(mainCostCenter);
		if (!StringUtils.isEmpty(startDate)) {
			p.setStartDate(sdf.parse(startDate));
		}
		if (!StringUtils.isBlank(endDate)) {
			p.setEndDate(sdf.parse(endDate));
		}
		return projectRepository.save(p);
	}

	private User createUser(String firstName, String lastName, String email, String phone, int age, Company c,
			CostCenter cc, Sex sex, Project... projects) {
		User u = new User();
		u.setFirstName("Mario");
		u.setLastName("Rossi");
		u.setEmail("mario.rossi@test.com");
		u.setPhone("+39 3334455666");
		u.setAges(35);
		u.setCompany(c);
		u.setCostCenter(cc);
		u.setSex(sex);
		if (projects != null) {
			for (Project p : projects) {
				u.addProject(p);
			}
		}
		return userRepository.save(u);
	}
	
}
