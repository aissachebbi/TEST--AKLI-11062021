package com.TestAkli.springbatch.service;

import java.util.List;

import com.TestAkli.springbatch.model.Person;

public interface IPersonService {
    void insertPerson(Person person);
    
    List<Person> editPersoneConsole(Person person);
}
