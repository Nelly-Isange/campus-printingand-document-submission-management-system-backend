package rw.ac.auca.printing.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import rw.ac.auca.printing.domain.PrintRequest;
import rw.ac.auca.printing.domain.PrintStatus;

public interface PrintRequestRepository extends JpaRepository<PrintRequest, UUID> {

    List<PrintRequest> findByStatusOrderByRequestDateAsc(PrintStatus status);

    List<PrintRequest> findByRequestDateBetweenOrderByRequestDateAsc(LocalDate start, LocalDate end);

    boolean existsByPrintOperator_IdAndRequestDateAndStatusNot(UUID printOperatorId, LocalDate requestDate, PrintStatus status);

    @Query("SELECT r.status, COUNT(r) FROM PrintRequest r GROUP BY r.status")
    List<Object[]> countRequestsByStatus();

    @Modifying
    @Query("UPDATE PrintRequest r SET r.status = :cancelled WHERE r.printOperator.id = :printOperatorId AND r.requestDate = :date AND r.status <> :ready")
    int cancelDay(@Param("printOperatorId") UUID printOperatorId,
                  @Param("date") LocalDate date,
                  @Param("cancelled") PrintStatus cancelled,
                  @Param("ready") PrintStatus ready);
}
