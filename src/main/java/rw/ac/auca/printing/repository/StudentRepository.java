package rw.ac.auca.printing.repository;

import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import rw.ac.auca.printing.domain.Student;

public interface StudentRepository extends JpaRepository<Student, UUID> {

    List<Student> findByLastNameIgnoreCaseOrderByFirstNameAsc(String lastName);

    @Query("SELECT DISTINCT r.student FROM PrintRequest r WHERE r.printOperator.id = :printOperatorId")
    List<Student> findStudentsOfPrintOperator(@Param("printOperatorId") UUID printOperatorId);
}
