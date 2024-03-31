package my_bank.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import my_bank.model.Enum.TransactionType;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Transaction implements Serializable {
    private Integer id;
    private double amount;
    private LocalDateTime transactionDatetime;
    private int idAccount;
    private TransactionType transactionType;
}
