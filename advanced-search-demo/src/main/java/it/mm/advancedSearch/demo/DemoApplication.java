package it.mm.advancedSearch.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import it.mm.advancedSearch.core.customRepositories.AdvancedSearchRepositoryImpl;

@EnableAutoConfiguration
@SpringBootApplication
@EnableJpaRepositories(value = "it.mm.advancedSearch.demo", repositoryBaseClass = AdvancedSearchRepositoryImpl.class)
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
	
}
