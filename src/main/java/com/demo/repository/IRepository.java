package com.demo.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.demo.model.Address;


@Repository	 
public interface IRepository extends CrudRepository<Address, Integer>   {

	 public List<Address> findAll();
	 @SuppressWarnings("unchecked")
	 public Address save(Address entity);
	 List<Address> findByName(String name);
}


 