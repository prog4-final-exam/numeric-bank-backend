package my_bank.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Balance implements Serializable {
    private int id;
    private double mainBalance;
    private double loanInterest;
    private double loanAmount;
    private LocalDateTime balanceDatetime;
    private int idAccount;
}
