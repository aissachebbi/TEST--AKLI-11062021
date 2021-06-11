package com.TestAkli.springbatch.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.TestAkli.springbatch.batch.PersonProcessor;
import com.TestAkli.springbatch.batch.PersonWriter;
import com.TestAkli.springbatch.model.Person;
import com.TestAkli.springbatch.model.PersonFieldSetMapper;

@Configuration
@EnableBatchProcessing
@EnableScheduling
public class BatchConfig {

    private static final String FILE_NAME = "results.csv";
    private static final String JOB_NAME = "listPersonsJob";
    private static final String STEP_NAME = "processingStep";
    private static final String READER_NAME = "personItemReader";

    @Value("${header.names}")
    private String names;

    @Value("${line.delimiter}")
    private String delimiter;

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    public Step studentStep() {
        return stepBuilderFactory.get(STEP_NAME)
                .<Person, Person>chunk(5)
                .reader(personItemReader())
                .processor(personItemProcessor())
                .writer(personItemWriter())
                .build();
    }

    @Bean
    public Job listPersonJob(Step step1) {
        return jobBuilderFactory.get(JOB_NAME)
                .start(step1)
                .build();
    }
    
    @Bean
    public ItemReader<Person> personItemReader() {
        FlatFileItemReader<Person> reader = new FlatFileItemReader<>();
        reader.setResource(new ClassPathResource(FILE_NAME));
        reader.setName(READER_NAME);
        reader.setLinesToSkip(1);
        reader.setLineMapper(lineMapper());
        return reader;

    }

    @Bean
    public LineMapper<Person> lineMapper() {

        final DefaultLineMapper<Person> defaultLineMapper = new DefaultLineMapper<>();
        final DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
        lineTokenizer.setDelimiter(delimiter);
        lineTokenizer.setStrict(false);
        lineTokenizer.setNames(names.split(delimiter));

        final PersonFieldSetMapper fieldSetMapper = new PersonFieldSetMapper();
        defaultLineMapper.setLineTokenizer(lineTokenizer);
        defaultLineMapper.setFieldSetMapper(fieldSetMapper);

        return defaultLineMapper;
    }

    @Bean
    public ItemProcessor<Person, Person> personItemProcessor() {
        return new PersonProcessor();
    }

    @Bean
    public ItemWriter<Person> personItemWriter() {
        return new PersonWriter();
    }
}
