package com.br.api.repository;

import com.br.api.model.ProcessUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProcessUserRepository extends CrudRepository<ProcessUser, Long> {

    List<ProcessUser> findByProcess_id(Long processId);
}