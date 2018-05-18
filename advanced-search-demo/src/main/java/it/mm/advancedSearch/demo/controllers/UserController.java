package it.mm.advancedSearch.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.mm.advancedSearch.core.dto.AdvancedSearchPredicateDTO;
import it.mm.advancedSearch.demo.entities.User;
import it.mm.advancedSearch.demo.repositories.UserRepository;

@RestController
@RequestMapping("users")
public class UserController {

	@Autowired
	private UserRepository userRepository;

	@GetMapping("")
	public List<User> getAll() {
		return userRepository.findAll();
	}

	@GetMapping("/{userId}")
	public User getAll(@PathVariable Long userId) {
		return userRepository.findById(userId).get();
	}

	@PostMapping("/avdSearch")
	public List<User> avdSearch(@RequestBody List<AdvancedSearchPredicateDTO> predicates) {
		return userRepository.advancedSearch(predicates);
	}

	@PostMapping("/avdSearch/pageSize/{pageSize}/page/{pageIndex}")
	public Page<User> avdSearchPageable(@RequestBody(required = false) List<AdvancedSearchPredicateDTO> predicates,
			@PathVariable int pageSize, @PathVariable int pageIndex, @RequestParam String sortDir,
			@RequestParam String sortParam) {
		if (sortDir != null && sortParam != null) {
			return userRepository.advancedSearch(predicates,
					PageRequest.of(pageIndex, pageSize, Direction.fromString(sortDir), sortParam));
		} else {
			return userRepository.advancedSearch(predicates, PageRequest.of(pageIndex, pageSize));
		}
	}

}
