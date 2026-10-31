package rw.ac.auca.printing.repository;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import rw.ac.auca.printing.domain.Printer;

public interface PrinterRepository extends JpaRepository<Printer, UUID> {
}
