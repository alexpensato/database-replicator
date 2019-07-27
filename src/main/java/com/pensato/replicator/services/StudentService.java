package com.pensato.replicator.services;

import com.pensato.replicator.models.Student;
import com.pensato.replicator.repositories.StudentRepository;
import com.pensato.replicator.repositories.mariadb.MariaDbStudentRepository;
import com.pensato.replicator.repositories.postgres.PostgresStudentRepository;
import com.pensato.replicator.support.BaseDualDatabaseService;
import org.springframework.stereotype.Service;

@Service
public class StudentService extends BaseDualDatabaseService<Student, Long, StudentRepository> {

    public StudentService(
            PostgresStudentRepository postgresStudentRepository,
            MariaDbStudentRepository mariaDbStudentRepository) {
        super(postgresStudentRepository, mariaDbStudentRepository);
    }
}
