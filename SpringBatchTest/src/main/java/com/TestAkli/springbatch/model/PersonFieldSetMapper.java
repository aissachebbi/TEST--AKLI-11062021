package com.TestAkli.springbatch.model;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;

public class PersonFieldSetMapper implements FieldSetMapper<Person> {
    @Override
    public Person mapFieldSet(FieldSet fieldSet) {
        return Person.builder()
                .age(fieldSet.readInt(0))
                .firstName(fieldSet.readString(1))
                .lastName(fieldSet.readString(2))
               
                .build();
    }
}

