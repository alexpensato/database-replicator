package com.pensato.replicator.repositories;

import com.pensato.replicator.models.College;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CollegeRepository extends PagingAndSortingRepository<College, Long> {
}
