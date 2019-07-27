package com.pensato.replicator.services;

import com.pensato.replicator.models.College;
import com.pensato.replicator.repositories.CollegeRepository;
import com.pensato.replicator.repositories.mariadb.MariaDbCollegeRepository;
import com.pensato.replicator.repositories.postgres.PostgresCollegeRepository;
import com.pensato.replicator.support.BaseDualDatabaseService;
import org.springframework.stereotype.Service;

@Service
public class CollegeService extends BaseDualDatabaseService<College, Long, CollegeRepository> {

    public CollegeService(
            PostgresCollegeRepository postgresCollegeRepository,
            MariaDbCollegeRepository mariaDbCollegeRepository) {
        super(postgresCollegeRepository, mariaDbCollegeRepository);
    }
}
