package rw.ac.auca.printing.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rw.ac.auca.printing.domain.Student;
import rw.ac.auca.printing.repository.StudentRepository;

@Service
public class StudentService {

    @Autowired
    private StudentRepository repository;

    public String saveStudent(Student value) {
        repository.save(value);
        return "Student saved successfully";
    }

    public List<Student> getAll() {
        return repository.findAll();
    }

    public Optional<Student> getById(UUID id) {
        return repository.findById(id);
    }

    public String updateStudent(UUID id, Student value) {
        Optional<Student> existing = repository.findById(id);
        if (existing.isEmpty()) {
            return "The student with that id does not exist";
        }
        existing.get().setFirstName(value.getFirstName());
        existing.get().setLastName(value.getLastName());
        existing.get().setRegNumber(value.getRegNumber());
        existing.get().setEmail(value.getEmail());

        repository.save(existing.get());
        return "Student updated successfully";
    }

    public String deleteStudent(UUID id) {
        if (!repository.existsById(id)) {
            return "The student with that id does not exist";
        }
        repository.deleteById(id);
        return "Student deleted successfully";
    }

    public List<Student> getByLastName(String lastName) {
        return repository.findByLastNameIgnoreCaseOrderByFirstNameAsc(lastName);
    }
}
