package com.demo.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.demo.model.Person;
import com.demo.repository.PersonRepository;


@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace=Replace.NONE)
public class TestRepository {
	
 
	@Autowired
    private PersonRepository personRepository;
     
    @Test
    public void testFindByName() {
    	List<Person> person = personRepository.findAll();
    	System.out.println(person);
    }
}