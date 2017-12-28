package com.demo.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.demo.model.Person;

@Repository	 
public interface PersonRepository extends CrudRepository<Person, Integer>   {

	 public List<Person> findAll();

}
