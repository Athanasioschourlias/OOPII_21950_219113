package org.hua.dit.oopii_21950_219113.student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository //This is responsible for the data acces layer, for data access
public interface StudentRepository extends JpaRepository<Student, Long> {



}
