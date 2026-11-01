package rw.ac.auca.printing.repository;

import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import rw.ac.auca.printing.domain.PrintPayment;
import rw.ac.auca.printing.domain.PrintRequest;

public interface PrintPaymentRepository extends JpaRepository<PrintPayment, UUID> {
    @Query("SELECT d FROM PrintPayment d WHERE d.printRequest.student.regNumber = :regNumber ORDER BY d.paidDate DESC")
    List<PrintPayment> findStudentPayments(@Param("regNumber") String regNumber);

    boolean existsByPrintRequest(PrintRequest printRequest);
}
