package com.pensato.replicator.repositories.mariadb;

import com.pensato.replicator.models.Student;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository("mariaDbStudentRepository")
public interface StudentRepository extends PagingAndSortingRepository<Student, Long> {
}
