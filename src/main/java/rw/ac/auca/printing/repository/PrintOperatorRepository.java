package rw.ac.auca.printing.repository;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import rw.ac.auca.printing.domain.PrintOperator;

public interface PrintOperatorRepository extends JpaRepository<PrintOperator, UUID> {
}
