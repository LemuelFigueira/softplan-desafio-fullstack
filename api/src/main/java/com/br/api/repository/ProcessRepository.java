package com.br.api.repository;

import java.util.List;

import com.br.api.model.Process;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProcessRepository extends CrudRepository<Process, Long> {

    @Override
    List<Process> findAll();
}
