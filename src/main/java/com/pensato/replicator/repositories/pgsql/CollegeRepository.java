package com.pensato.replicator.repositories.pgsql;

import com.pensato.replicator.models.College;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository("pgSqlCollegeRepository")
public interface CollegeRepository extends PagingAndSortingRepository<College, Long> {
}
