package com.pensato.replicator.repositories.pgsql;

import com.pensato.replicator.models.Student;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository("pgSqlStudentRepository")
public interface StudentRepository extends PagingAndSortingRepository<Student, Long> {
}
