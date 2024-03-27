package my_bank.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AccountStatement implements Serializable {
    private int id;
    private LocalDate date;
    private String reference;
    private String reason;
    private double creditAmount;
    private double debitAmount;
    private double balance;
    private int idAccount;
}
