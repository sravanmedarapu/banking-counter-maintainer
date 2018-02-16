package com.counter.maintainer.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class BranchRepositoryImpl implements BranchRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * Checks if branch exists with given branchName
     *
     * @param branchName
     * @return Boolean
     */
    public Boolean isBranchExists(String branchName) {
        Long count = jdbcTemplate.queryForObject("select count(*) from branch where UPPER(branchName) like UPPER(?1)", new Object[] { branchName }, Long.class);
        return count > 0;
    }
}
