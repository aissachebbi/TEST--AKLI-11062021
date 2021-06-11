package com.TestAkli.springbatch.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.TestAkli.springbatch.model.Person;
import com.TestAkli.springbatch.repository.IPersonRepository;
import com.TestAkli.springbatch.service.IPersonService;

@Service
public class PersonService implements IPersonService {

    @Autowired
    private IPersonRepository personRepository;

    @Override
    public void insertPerson(Person person) {
        personRepository.save(person);
    }

    
	public List<Person> editPersoneConsole(Person p) {
		
	return (List<Person>) p;
		
	
	}
}
