package rw.ac.auca.printing.repository;

import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import rw.ac.auca.printing.domain.PrintOperator;

public interface PrintOperatorRepository extends JpaRepository<PrintOperator, UUID> {

    @Query("SELECT p FROM PrintOperator p JOIN p.printServices s WHERE LOWER(s.name) = LOWER(:name)")
    List<PrintOperator> findByPrintServiceName(@Param("name") String name);
}
