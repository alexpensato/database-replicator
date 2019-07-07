package com.pensato.replicator;

import com.pensato.replicator.models.College;
import com.pensato.replicator.models.Student;
import com.pensato.replicator.service.UniversityService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.List;

@SpringBootApplication(
		exclude = { DataSourceAutoConfiguration.class,
				HibernateJpaAutoConfiguration.class,
				DataSourceTransactionManagerAutoConfiguration.class })
@EnableTransactionManagement
public class ReplicatorApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReplicatorApplication.class, args);
	}

	@Bean
	public CommandLineRunner loadData(UniversityService universityService) {
		return (args) -> {
			List<College> originColleges = universityService.getCollegesFromOriginDb();
			List<College> destinationColleges = universityService.getCollegesInDestinationDb();
			for(College e: originColleges) {
				if(!destinationColleges.contains(e)) {
					universityService.createOrUpdateCollege(e.toBuilder().build());
				}
			}

			List<Student> originStudents = universityService.getStudentsFromOriginDb();
			List<Student> destinationStudents = universityService.getStudentsInDestinationDb();
			for(Student e: originStudents) {
				if(!destinationStudents.contains(e)) {
					universityService.createOrUpdateStudent(e.toBuilder().build());
				}
			}
		};
	}

}
