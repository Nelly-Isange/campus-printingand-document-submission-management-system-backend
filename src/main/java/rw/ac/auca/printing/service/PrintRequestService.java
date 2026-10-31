package rw.ac.auca.printing.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rw.ac.auca.printing.domain.PrintRequest;
import rw.ac.auca.printing.repository.PrintRequestRepository;

@Service
public class PrintRequestService {

    @Autowired
    private PrintRequestRepository repository;

    public String savePrintRequest(PrintRequest value) {
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
        existing.get().setStatus(value.getStatus());
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
}
