package rw.ac.auca.printing.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import rw.ac.auca.printing.domain.PrintRequest;
import rw.ac.auca.printing.domain.PrintStatus;

public interface PrintRequestRepository extends JpaRepository<PrintRequest, UUID> {

    List<PrintRequest> findByStatusOrderByRequestDateAsc(PrintStatus status);

    List<PrintRequest> findByRequestDateBetweenOrderByRequestDateAsc(LocalDate start, LocalDate end);

    boolean existsByPrintOperator_IdAndRequestDateAndStatusNot(UUID printOperatorId, LocalDate requestDate, PrintStatus status);
}
