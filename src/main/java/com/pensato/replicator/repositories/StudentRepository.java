package com.pensato.replicator.repositories;

import com.pensato.replicator.models.Student;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface StudentRepository extends PagingAndSortingRepository<Student, Long> {
}
