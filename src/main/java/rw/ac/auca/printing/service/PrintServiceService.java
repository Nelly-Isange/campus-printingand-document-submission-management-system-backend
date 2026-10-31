package rw.ac.auca.printing.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rw.ac.auca.printing.domain.PrintService;
import rw.ac.auca.printing.repository.PrintServiceRepository;

@Service
public class PrintServiceService {

    @Autowired
    private PrintServiceRepository repository;

    public String savePrintService(PrintService value) {
        repository.save(value);
        return "Print service saved successfully";
    }

    public List<PrintService> getAll() {
        return repository.findAll();
    }

    public Optional<PrintService> getById(UUID id) {
        return repository.findById(id);
    }

    public String updatePrintService(UUID id, PrintService value) {
        Optional<PrintService> existing = repository.findById(id);
        if (existing.isEmpty()) {
            return "The print service with that id does not exist";
        }
        existing.get().setName(value.getName());

        repository.save(existing.get());
        return "Print service updated successfully";
    }

    public String deletePrintService(UUID id) {
        if (!repository.existsById(id)) {
            return "The print service with that id does not exist";
        }
        repository.deleteById(id);
        return "Print service deleted successfully";
    }
}
