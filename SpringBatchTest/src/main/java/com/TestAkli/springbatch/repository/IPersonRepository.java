package com.TestAkli.springbatch.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.TestAkli.springbatch.model.Person;

@Repository
public interface IPersonRepository extends JpaRepository<Person, Long> {
}
