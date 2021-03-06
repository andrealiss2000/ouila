package com.lpiot.ouila;

import com.lpiot.ouila.domain.Course;
import com.lpiot.ouila.domain.Role;
import com.lpiot.ouila.domain.User;
import com.lpiot.ouila.repositories.CourseRepository;
import com.lpiot.ouila.repositories.UserRepository;
import com.lpiot.ouila.services.StorageService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class OuilaApplication {
	@Autowired
	private CourseRepository courseRepository;
	@Autowired
	private UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(OuilaApplication.class, args);
	}

	@Bean
	CommandLineRunner init(StorageService storageService) {
		return (args) -> {
			storageService.deleteAll();
			storageService.init();
		};
	}

	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		PasswordEncoder pe = new BCryptPasswordEncoder();
		return args -> {
			Course c = new Course();
			c.setName("LPIOT");
			c.setSpeciality("IOT");
			courseRepository.save(c);

			User u = new User();
			u.setFirstName("Ward");
			u.setLastName("Odney");
			u.setAddress("12 Dapin Way");
			u.setCardNumber("55418882");
			u.setEmail("wodney6@reference.com");
			u.setPassword(pe.encode("admin"));
			u.setPhone("9819912223");
			u.setUsername("admin");
			u.setRole(Role.ADMIN);
			u.setCourse(c);
			userRepository.save(u);
		};
	}
}
