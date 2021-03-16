package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Person;

@Repository("personRepository")
public interface PersonRepository extends JpaRepository<Person, Long>{

}
