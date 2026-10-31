package rw.ac.auca.printing.repository;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import rw.ac.auca.printing.domain.PrintRequest;

public interface PrintRequestRepository extends JpaRepository<PrintRequest, UUID> {
}
