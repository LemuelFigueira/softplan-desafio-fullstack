package com.br.api.repository;

import com.br.api.model.User;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    @Override
    List<User> findAll();

    Optional<User> findByEmail(String email);

    public Page<User> findAll(Pageable pageable);

    @Query(value = "select u from User u where u.name like %?1% or u.email like %?1% or u.profile like %?1%")
    public Page<User> findByAnyNameOrProfileOrEmail(@Param("query") String query, Pageable pageable);
}