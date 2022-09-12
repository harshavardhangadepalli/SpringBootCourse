package com.example.demo.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class StudentConfig {
    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository) {
        return args -> {
            Student name1 = new Student("name1", "harsha@gmail.com", LocalDate.of(2000, Month.JANUARY, 5));
            Student name2 = new Student("name2", "harsha2@gmail.com", LocalDate.of(2001, Month.JANUARY, 5));
            repository.saveAll(
                    List.of(name1, name2)
            );
        };
    }
}
