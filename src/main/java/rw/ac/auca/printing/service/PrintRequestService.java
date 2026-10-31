package rw.ac.auca.printing.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rw.ac.auca.printing.domain.PrintRequest;
import rw.ac.auca.printing.domain.PrintStatus;
import rw.ac.auca.printing.repository.PrintRequestRepository;

@Service
public class PrintRequestService {

    @Autowired
    private PrintRequestRepository repository;

    public String savePrintRequest(PrintRequest value) {
        if (value.getRequestDate().isBefore(LocalDate.now())) {
            return "Print request date cannot be in the past";
        }
        if (repository.existsByPrintOperator_IdAndRequestDateAndStatusNot(
                value.getPrintOperator().getId(), value.getRequestDate(), PrintStatus.CANCELLED)) {
            return "Print operator already has a request on that date";
        }
        value.setStatus(PrintStatus.SUBMITTED);
        repository.save(value);
        return "Print request saved successfully";
    }

    public List<PrintRequest> getAll() {
        return repository.findAll();
    }

    public Optional<PrintRequest> getById(UUID id) {
        return repository.findById(id);
    }

    public String updatePrintRequest(UUID id, PrintRequest value) {
        Optional<PrintRequest> existing = repository.findById(id);
        if (existing.isEmpty()) {
            return "The print request with that id does not exist";
        }
        existing.get().setRequestDate(value.getRequestDate());
        existing.get().setDocumentName(value.getDocumentName());
        existing.get().setCopies(value.getCopies());
        existing.get().setStudent(value.getStudent());
        existing.get().setPrintOperator(value.getPrintOperator());

        repository.save(existing.get());
        return "Print request updated successfully";
    }

    public String deletePrintRequest(UUID id) {
        if (!repository.existsById(id)) {
            return "The print request with that id does not exist";
        }
        repository.deleteById(id);
        return "Print request deleted successfully";
    }

    public List<PrintRequest> getByStatus(PrintStatus status) {
        return repository.findByStatusOrderByRequestDateAsc(status);
    }

    public List<PrintRequest> getBetween(LocalDate start, LocalDate end) {
        return repository.findByRequestDateBetweenOrderByRequestDateAsc(start, end);
    }

    public String changeStatus(UUID id, PrintStatus status) {
        Optional<PrintRequest> request = repository.findById(id);
        if (request.isEmpty()) {
            return "The print request with that id does not exist";
        }
        PrintStatus current = request.get().getStatus();
        if ((current == PrintStatus.SUBMITTED && (status == PrintStatus.PROCESSING || status == PrintStatus.CANCELLED))
                || (current == PrintStatus.PROCESSING && (status == PrintStatus.READY || status == PrintStatus.CANCELLED))) {
            request.get().setStatus(status);
            repository.save(request.get());
            return "Print request status updated successfully";
        }
        return "Cannot change status from " + current + " to " + status;
    }
}
