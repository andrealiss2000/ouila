package com.lpiot.ouila;

import java.util.Date;

import com.lpiot.ouila.domain.Course;
import com.lpiot.ouila.domain.ERole;
import com.lpiot.ouila.domain.Lesson;
import com.lpiot.ouila.domain.Role;
import com.lpiot.ouila.domain.Subject;
import com.lpiot.ouila.domain.User;
import com.lpiot.ouila.repositories.CourseRepository;
import com.lpiot.ouila.repositories.LessonRepository;
import com.lpiot.ouila.repositories.RoleRepository;
import com.lpiot.ouila.repositories.SubjectRepository;
import com.lpiot.ouila.repositories.UserRepository;
import com.lpiot.ouila.services.StorageService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class OuilaApplication {
	@Autowired
	private LessonRepository lessonRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private SubjectRepository subjectRepository;
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
		return args -> {

			Role r1 = new Role();
			r1.setName(ERole.ADMIN);
			Role r2 = new Role();
			r2.setName(ERole.TEACHER);
			Role r3 = new Role();
			r3.setName(ERole.STUDENT);
			roleRepository.save(r1);
			roleRepository.save(r2);
			roleRepository.save(r3);

			Subject s = new Subject();
			s.setName("Anglais");
			subjectRepository.save(s);

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
			u.setPassword("xA3CYg9m");
			u.setPhone("9819912223");
			u.setUsername("wodney6");
			u.setRole(r2);
			u.setCourse(c);
			userRepository.save(u);

			Lesson l = new Lesson();
			l.setStartDateTime(new Date());
			l.setEndDateTime(new Date());
			l.setSubject(s);
			l.setTeacher(u);
			lessonRepository.save(l);
		};
	}
}
