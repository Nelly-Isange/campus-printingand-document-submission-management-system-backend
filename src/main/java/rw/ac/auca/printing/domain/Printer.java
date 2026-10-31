package rw.ac.auca.printing.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "printer")
public class Printer {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private int printerNumber;

    private String name;

    public UUID getId() {
        return id;
    }

    public int getPrinterNumber() {
        return printerNumber;
    }

    public void setPrinterNumber(int printerNumber) {
        this.printerNumber = printerNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
