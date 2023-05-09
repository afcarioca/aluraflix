package br.com.aluraflix;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import br.com.aluraflix.infraestructure.adapaters.repositories.SpringVideoRepository;

@SpringBootApplication
@EnableJpaRepositories(basePackageClasses = SpringVideoRepository.class)
public class AluraflixApplication {

	public static void main(String[] args) {
		SpringApplication.run(AluraflixApplication.class, args);
	}

}
