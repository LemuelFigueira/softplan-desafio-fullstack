package com.br.api.repository;

import java.util.List;

import com.br.api.model.Process;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProcessRepository extends CrudRepository<Process, Long> {

    @Override
    List<Process> findAll();

    @Query(value = "select p from Process p where p.deletedAt is null and (p.title like %?1% or p.subtitle like %?1% or p.description like %?1%)")
    public Page<Process> findByAnyTitleOrSubtitleOrDescription(@Param("query") String query, Pageable pageable);
}
