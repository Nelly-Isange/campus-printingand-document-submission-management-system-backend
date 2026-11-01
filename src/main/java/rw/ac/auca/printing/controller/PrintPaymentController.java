package rw.ac.auca.printing.controller;

import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import rw.ac.auca.printing.domain.PrintPayment;
import rw.ac.auca.printing.service.PrintPaymentService;

@RestController
@RequestMapping("/api/print-payments")
public class PrintPaymentController {

    @Autowired
    private PrintPaymentService service;

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestParam UUID printRequestId, @RequestBody PrintPayment payment) {
        String message = service.savePrintPayment(printRequestId, payment);
        if (message.equals("Print payment saved successfully")) {
            return new ResponseEntity<>(message, HttpStatus.CREATED);
        } else if (message.equals("The print request with that id does not exist")) {
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/student/{regNumber}")
    public ResponseEntity<?> getStudentPayments(@PathVariable String regNumber) {
        return new ResponseEntity<>(service.getStudentPayments(regNumber), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id) {
        String message = service.deletePrintPayment(id);
        if (message.equals("Print payment deleted successfully")) {
            return new ResponseEntity<>(message, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
    }
}
