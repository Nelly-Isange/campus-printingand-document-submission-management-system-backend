package rw.ac.auca.printing.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import rw.ac.auca.printing.domain.Student;
import rw.ac.auca.printing.service.StudentService;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    @Autowired
    private StudentService service;

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody Student value) {
        String message = service.saveStudent(value);
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable UUID id) {
        Optional<Student> value = service.getById(id);
        if (value.isPresent()) {
            return new ResponseEntity<>(value.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("The student with that id does not exist", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable UUID id, @RequestBody Student value) {
        String message = service.updateStudent(id, value);
        if (message.equals("Student updated successfully")) {
            return new ResponseEntity<>(message, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id) {
        String message = service.deleteStudent(id);
        if (message.equals("Student deleted successfully")) {
            return new ResponseEntity<>(message, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/by-last-name")
    public ResponseEntity<?> getByLastName(@RequestParam String lastName) {
        return new ResponseEntity<>(service.getByLastName(lastName), HttpStatus.OK);
    }

    @GetMapping("/of-print-operator/{printOperatorId}")
    public ResponseEntity<?> getStudentsOfPrintOperator(@PathVariable UUID printOperatorId) {
        Optional<List<Student>> students = service.getStudentsOfPrintOperator(printOperatorId);
        if (students.isPresent()) {
            return new ResponseEntity<>(students.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("The print operator with that id does not exist", HttpStatus.NOT_FOUND);
        }
    }
}
