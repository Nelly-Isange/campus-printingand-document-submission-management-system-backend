package rw.ac.auca.printing.service;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rw.ac.auca.printing.domain.PrintStatus;
import rw.ac.auca.printing.repository.PrintOperatorRepository;
import rw.ac.auca.printing.repository.PrintPaymentRepository;
import rw.ac.auca.printing.repository.PrintRequestRepository;
import rw.ac.auca.printing.repository.PrinterRepository;
import rw.ac.auca.printing.repository.StudentRepository;

@Service
public class DashboardService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private PrintOperatorRepository printOperatorRepository;

    @Autowired
    private PrinterRepository printerRepository;

    @Autowired
    private PrintRequestRepository printRequestRepository;

    @Autowired
    private PrintPaymentRepository printPaymentRepository;

    public Map<String, Long> getSummary() {
        Map<String, Long> summary = new LinkedHashMap<>();
        summary.put("students", studentRepository.count());
        summary.put("printOperators", printOperatorRepository.count());
        summary.put("freePrinters", (long) printerRepository.findFreePrinters().size());
        summary.put("requestsToday", printRequestRepository.countByRequestDate(LocalDate.now()));
        summary.put("submitted", printRequestRepository.countByStatus(PrintStatus.SUBMITTED));
        summary.put("payments", printPaymentRepository.count());
        return summary;
    }
}
