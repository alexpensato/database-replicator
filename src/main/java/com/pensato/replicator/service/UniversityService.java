package com.pensato.replicator.service;

import com.pensato.replicator.models.College;
import com.pensato.replicator.models.Student;
import com.pensato.replicator.repositories.pgsql.CollegeRepository;
import com.pensato.replicator.repositories.pgsql.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class UniversityService
{
    private CollegeRepository pgSqlCollegeRepository;

    private com.pensato.replicator.repositories.mariadb.CollegeRepository mariaDbCollegeRepository;

    private StudentRepository pgSqlStudentRepository;

    private com.pensato.replicator.repositories.mariadb.StudentRepository mariaDbStudentRepository;

    /**
     *
     * [Colleges section]
     *
     */

    @Transactional(transactionManager="pgSqlTransactionManager")
    public List<College> getCollegesFromOriginDb()
    {
        List<College> list = new ArrayList<>();
        pgSqlCollegeRepository.findAll().forEach(list::add);
        return list;
    }

    @Transactional(transactionManager="mariaDbTransactionManager")
    public List<College> getCollegesInDestinationDb()
    {
        List<College> list = new ArrayList<>();
        mariaDbCollegeRepository.findAll().forEach(list::add);
        return list;
    }

    @Transactional(transactionManager="mariaDbTransactionManager")
    public void createOrUpdateCollege(College entity)
    {
        mariaDbCollegeRepository.save(entity);
    }

    /**
     *
     * [Students section]
     *
     */

    @Transactional(transactionManager="pgSqlTransactionManager")
    public List<Student> getStudentsFromOriginDb()
    {
        List<Student> list = new ArrayList<>();
        pgSqlStudentRepository.findAll().forEach(list::add);
        return list;
    }

    @Transactional(transactionManager="mariaDbTransactionManager")
    public List<Student> getStudentsInDestinationDb()
    {
        List<Student> list = new ArrayList<>();
        mariaDbStudentRepository.findAll().forEach(list::add);
        return list;
    }

    @Transactional(transactionManager="mariaDbTransactionManager")
    public void createOrUpdateStudent(Student entity)
    {
        mariaDbStudentRepository.save(entity);
    }
}
