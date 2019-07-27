package com.pensato.replicator;

import com.pensato.replicator.services.CollegeService;
import com.pensato.replicator.services.StudentService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

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
	public CommandLineRunner loadData(CollegeService collegeService, StudentService studentService) {
		return (args) -> {
			collegeService.initializeReplication();
			studentService.initializeReplication();
		};
	}

}
