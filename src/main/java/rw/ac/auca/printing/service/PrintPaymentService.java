package rw.ac.auca.printing.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rw.ac.auca.printing.domain.PrintPayment;
import rw.ac.auca.printing.domain.PrintRequest;
import rw.ac.auca.printing.domain.PrintStatus;
import rw.ac.auca.printing.repository.PrintPaymentRepository;
import rw.ac.auca.printing.repository.PrintRequestRepository;

@Service
public class PrintPaymentService {

    @Autowired
    private PrintPaymentRepository repository;

    @Autowired
    private PrintRequestRepository printRequestRepository;

    public String savePrintPayment(UUID printRequestId, PrintPayment payment) {
        Optional<PrintRequest> request = printRequestRepository.findById(printRequestId);
        if (request.isEmpty()) {
            return "The print request with that id does not exist";
        }
        if (request.get().getStatus() != PrintStatus.READY) {
            return "Print payments are only for READY print requests";
        }
        payment.setPaidDate(LocalDate.now());
        payment.setPrintRequest(request.get());
        repository.save(payment);
        return "Print payment saved successfully";
    }

    public List<PrintPayment> getStudentPayments(String regNumber) {
        return repository.findStudentPayments(regNumber);
    }

    public String deletePrintPayment(UUID id) {
        if (!repository.existsById(id)) {
            return "The print payment with that id does not exist";
        }
        repository.deleteById(id);
        return "Print payment deleted successfully";
    }
}
