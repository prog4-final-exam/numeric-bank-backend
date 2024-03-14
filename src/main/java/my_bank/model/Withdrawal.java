package my_bank.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Withdrawal implements Serializable {
    private int id;
    private double amount;
    private LocalDateTime withdrawalDateTime;
    private int idAccount;
}
