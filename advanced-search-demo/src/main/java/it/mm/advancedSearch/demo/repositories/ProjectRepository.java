package it.mm.advancedSearch.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import it.mm.advancedSearch.demo.entities.Project;

public interface ProjectRepository extends JpaRepository<Project, Long> {

}
