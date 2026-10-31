package rw.ac.auca.printing.repository;

import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import rw.ac.auca.printing.domain.Printer;

public interface PrinterRepository extends JpaRepository<Printer, UUID> {

    @Query("SELECT p FROM Printer p WHERE NOT EXISTS (SELECT o FROM PrintOperator o WHERE o.printer = p) ORDER BY p.printerNumber")
    List<Printer> findFreePrinters();
}
