package com.pensato.replicator.repositories.mariadb;

import com.pensato.replicator.models.College;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository("mariaDbCollegeRepository")
public interface CollegeRepository extends PagingAndSortingRepository<College, Long> {
}
