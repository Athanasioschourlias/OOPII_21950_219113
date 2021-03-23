package org.hua.dit.oopii_21950_219113.student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository //This is responsible for the data acces layer, for data access
public interface StudentRepository extends JpaRepository<Student, Long> {

    @Query("SELECT s FROM Student s WHERE s.email = ?1")
    //this means -> SELECT * FROM STUDENT WHERE email = ?
    Optional<Student> findStudentByEmail(String email);

}
