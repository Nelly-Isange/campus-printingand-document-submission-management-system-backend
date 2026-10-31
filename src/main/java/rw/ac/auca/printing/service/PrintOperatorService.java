package rw.ac.auca.printing.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rw.ac.auca.printing.domain.PrintOperator;
import rw.ac.auca.printing.domain.PrintService;
import rw.ac.auca.printing.domain.Printer;
import rw.ac.auca.printing.repository.PrintOperatorRepository;
import rw.ac.auca.printing.repository.PrintServiceRepository;
import rw.ac.auca.printing.repository.PrinterRepository;

@Service
public class PrintOperatorService {

    @Autowired
    private PrintOperatorRepository repository;

    @Autowired
    private PrintServiceRepository printServiceRepository;

    @Autowired
    private PrinterRepository printerRepository;

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

    @Transactional
    public String addPrintService(UUID printOperatorId, UUID printServiceId) {
        Optional<PrintOperator> operator = repository.findById(printOperatorId);
        if (operator.isEmpty()) {
            return "The print operator with that id does not exist";
        }
        Optional<PrintService> printService = printServiceRepository.findById(printServiceId);
        if (printService.isEmpty()) {
            return "That print service does not exist";
        }
        if (operator.get().getPrintServices().contains(printService.get())) {
            return "The print operator already has that print service";
        }
        operator.get().getPrintServices().add(printService.get());
        repository.save(operator.get());
        return "Print service added to the print operator";
    }

    public String changePrinter(UUID printOperatorId, int printerNumber) {
        Optional<PrintOperator> operator = repository.findById(printOperatorId);
        if (operator.isEmpty()) {
            return "The print operator with that id does not exist";
        }
        Optional<Printer> printer = printerRepository.findByPrinterNumber(printerNumber);
        if (printer.isEmpty()) {
            return "The printer with that printerNumber does not exist";
        }
        Printer current = operator.get().getPrinter();
        if ((current == null || !current.getId().equals(printer.get().getId()))
                && repository.existsByPrinter(printer.get())) {
            return "That printer is taken by another print operator";
        }
        operator.get().setPrinter(printer.get());
        repository.save(operator.get());
        return "Printer changed successfully";
    }
}
