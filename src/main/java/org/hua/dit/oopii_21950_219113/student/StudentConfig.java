package org.hua.dit.oopii_21950_219113.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repo) {
        return args -> {

            Student thanos = new Student(
                    1L,
                    "thanos",
                    "thanos@gmail.com",
                    LocalDate.of(2001, Month.JANUARY, 5)
            );

            List<Student> iterable = new java.util.ArrayList<>();
            iterable.add(thanos);
            List<Student> students = repo.saveAll(
                    iterable
            );
        };
    }

}
