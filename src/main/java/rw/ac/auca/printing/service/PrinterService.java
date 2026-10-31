package rw.ac.auca.printing.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rw.ac.auca.printing.domain.Printer;
import rw.ac.auca.printing.repository.PrinterRepository;

@Service
public class PrinterService {

    @Autowired
    private PrinterRepository repository;

    public String savePrinter(Printer value) {
        repository.save(value);
        return "Printer saved successfully";
    }

    public List<Printer> getAll() {
        return repository.findAll();
    }

    public Optional<Printer> getById(UUID id) {
        return repository.findById(id);
    }

    public String updatePrinter(UUID id, Printer value) {
        Optional<Printer> existing = repository.findById(id);
        if (existing.isEmpty()) {
            return "The printer with that id does not exist";
        }
        existing.get().setPrinterNumber(value.getPrinterNumber());
        existing.get().setName(value.getName());

        repository.save(existing.get());
        return "Printer updated successfully";
    }

    public String deletePrinter(UUID id) {
        if (!repository.existsById(id)) {
            return "The printer with that id does not exist";
        }
        repository.deleteById(id);
        return "Printer deleted successfully";
    }

    public List<Printer> getFreePrinters() {
        return repository.findFreePrinters();
    }
}
