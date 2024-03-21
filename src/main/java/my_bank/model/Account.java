package my_bank.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Account implements Serializable {
    private int id;
    private String firstname;
    private String lastname;
    private LocalDate birthdate;
    private String accountNumber;
    private boolean overdraftAllowed;
    private double netMonthlyPay;
}
