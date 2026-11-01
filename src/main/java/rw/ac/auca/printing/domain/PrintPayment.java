package rw.ac.auca.printing.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "print_payment")
public class PrintPayment {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String method;

    private String note;

    private int amountRwf;

    private LocalDate paidDate;

    @ManyToOne
    @JoinColumn(name = "print_request_id")
    private PrintRequest printRequest;

    public UUID getId() {
        return id;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getAmountRwf() {
        return amountRwf;
    }

    public void setAmountRwf(int amountRwf) {
        this.amountRwf = amountRwf;
    }

    public LocalDate getPaidDate() {
        return paidDate;
    }

    public void setPaidDate(LocalDate paidDate) {
        this.paidDate = paidDate;
    }

    public PrintRequest getPrintRequest() {
        return printRequest;
    }

    public void setPrintRequest(PrintRequest printRequest) {
        this.printRequest = printRequest;
    }
}
