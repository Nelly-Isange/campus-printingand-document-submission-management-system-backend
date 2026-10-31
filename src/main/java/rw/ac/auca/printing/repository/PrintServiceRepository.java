package rw.ac.auca.printing.repository;

import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import rw.ac.auca.printing.domain.PrintService;

public interface PrintServiceRepository extends JpaRepository<PrintService, UUID> {

    @Query("SELECT s FROM PrintService s WHERE s.printOperators IS EMPTY")
    List<PrintService> findUnusedPrintServices();
}
