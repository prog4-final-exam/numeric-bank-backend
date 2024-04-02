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
    private Integer idBalance;
    private Double mainBalance;
    private Double loanInterest;
    private Double loanAmount;
    private LocalDateTime balanceDatetime;
    private Integer idAccount;
}
