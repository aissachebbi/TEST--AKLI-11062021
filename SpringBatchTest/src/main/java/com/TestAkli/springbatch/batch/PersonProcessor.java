package com.TestAkli.springbatch.batch;

import org.springframework.batch.item.ItemProcessor;

import com.TestAkli.springbatch.model.Person;

public class PersonProcessor implements ItemProcessor<Person, Person> {

	@Override
	public Person process(Person person) {
        
		person.setFirstName(person.getFirstName());
		person.setLastName(person.getLastName());
		person.setAge(person.getAge());
	

		return person;
	}
}
