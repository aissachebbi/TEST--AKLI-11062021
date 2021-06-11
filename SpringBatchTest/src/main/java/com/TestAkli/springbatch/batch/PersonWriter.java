package com.TestAkli.springbatch.batch;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import com.TestAkli.springbatch.model.Person;
import com.TestAkli.springbatch.service.IPersonService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PersonWriter implements ItemWriter<Person> {

	  Logger log = LoggerFactory.getLogger(this.getClass());
	  
    @Autowired
    private IPersonService personService;

    @Override
    public void write(List<? extends Person> persons) {
    	
    	List<Person> prs = new  ArrayList<>() ;
    	for(Person p : persons) {
    		if(p.getAge()>40) {
    			personService.insertPerson(p);
    		}else {
    			prs.add(p);
   			
    		}
    	}
    	
    	
    	
    	
    	
//    	persons.stream()
//          .filter(x ->x.getAge()>40)
//        .forEach(person -> {
//            log.info("Enregistrement en base de l'objet {}", persons);
//            personService.insertPerson(person);
//        });
////    	
    	prs.stream()     
        .forEach(ps -> {
            log.info("Editer les enregistrement dans la console {}", persons);
            personService.editPersoneConsole((Person) prs);
        });
   }
}
