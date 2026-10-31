package rw.ac.auca.printing.controller;

import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import rw.ac.auca.printing.domain.PrintOperator;
import rw.ac.auca.printing.service.PrintOperatorService;

@RestController
@RequestMapping("/api/print-operators")
public class PrintOperatorController {

    @Autowired
    private PrintOperatorService service;

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody PrintOperator value) {
        String message = service.savePrintOperator(value);
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable UUID id) {
        Optional<PrintOperator> value = service.getById(id);
        if (value.isPresent()) {
            return new ResponseEntity<>(value.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("The print operator with that id does not exist", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable UUID id, @RequestBody PrintOperator value) {
        String message = service.updatePrintOperator(id, value);
        if (message.equals("Print operator updated successfully")) {
            return new ResponseEntity<>(message, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id) {
        String message = service.deletePrintOperator(id);
        if (message.equals("Print operator deleted successfully")) {
            return new ResponseEntity<>(message, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/by-print-service")
    public ResponseEntity<?> getByPrintService(@RequestParam String name) {
        return new ResponseEntity<>(service.getByPrintService(name), HttpStatus.OK);
    }

    @PutMapping("/{printOperatorId}/print-services/{printServiceId}")
    public ResponseEntity<?> addPrintService(@PathVariable UUID printOperatorId, @PathVariable UUID printServiceId) {
        String message = service.addPrintService(printOperatorId, printServiceId);
        if (message.equals("Print service added to the print operator")) {
            return new ResponseEntity<>(message, HttpStatus.OK);
        } else if (message.equals("The print operator already has that print service")) {
            return new ResponseEntity<>(message, HttpStatus.CONFLICT);
        } else {
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping("/{printOperatorId}/printer")
    public ResponseEntity<?> changePrinter(@PathVariable UUID printOperatorId, @RequestParam int printerNumber) {
        String message = service.changePrinter(printOperatorId, printerNumber);
        if (message.equals("Printer changed successfully")) {
            return new ResponseEntity<>(message, HttpStatus.OK);
        } else if (message.equals("That printer is taken by another print operator")) {
            return new ResponseEntity<>(message, HttpStatus.CONFLICT);
        } else {
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
    }
}
