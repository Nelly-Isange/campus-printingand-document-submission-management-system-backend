package rw.ac.auca.printing.repository;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import rw.ac.auca.printing.domain.Student;

public interface StudentRepository extends JpaRepository<Student, UUID> {
}
