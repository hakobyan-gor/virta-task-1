package com.task.repository.company;

import com.task.entity.company.Company;
import com.task.repository.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends BaseRepository<Company> {

    boolean existsByName(String name);

}