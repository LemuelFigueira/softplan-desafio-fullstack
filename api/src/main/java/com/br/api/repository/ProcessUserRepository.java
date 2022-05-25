package com.br.api.repository;

import com.br.api.model.ProcessUser;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProcessUserRepository extends CrudRepository<ProcessUser, Long> {

    List<ProcessUser> findByProcess_id(Long processId);

    @Query(value = "select pu from ProcessUser pu where pu.process.deletedAt is null and (pu.process.title like %?1% or pu.process.subtitle like %?1% or pu.process.description like %?1%)")
    public Page<ProcessUser> findByUserIdAndAnyNameOrProfileOrEmail(
            @Param("query") String query,
            @Param("userId") Long userId,
            Pageable pageable);
}