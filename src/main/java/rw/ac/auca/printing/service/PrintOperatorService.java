package rw.ac.auca.printing.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rw.ac.auca.printing.domain.PrintOperator;
import rw.ac.auca.printing.repository.PrintOperatorRepository;

@Service
public class PrintOperatorService {

    @Autowired
    private PrintOperatorRepository repository;

    public String savePrintOperator(PrintOperator value) {
        repository.save(value);
        return "Print operator saved successfully";
    }

    public List<PrintOperator> getAll() {
        return repository.findAll();
    }

    public Optional<PrintOperator> getById(UUID id) {
        return repository.findById(id);
    }

    public String updatePrintOperator(UUID id, PrintOperator value) {
        Optional<PrintOperator> existing = repository.findById(id);
        if (existing.isEmpty()) {
            return "The print operator with that id does not exist";
        }
        existing.get().setFirstName(value.getFirstName());
        existing.get().setLastName(value.getLastName());
        existing.get().setPhone(value.getPhone());
        existing.get().setPrinter(value.getPrinter());
        existing.get().setPrintServices(value.getPrintServices());

        repository.save(existing.get());
        return "Print operator updated successfully";
    }

    public String deletePrintOperator(UUID id) {
        if (!repository.existsById(id)) {
            return "The print operator with that id does not exist";
        }
        repository.deleteById(id);
        return "Print operator deleted successfully";
    }

    public List<PrintOperator> getByPrintService(String name) {
        return repository.findByPrintServiceName(name);
    }
}
