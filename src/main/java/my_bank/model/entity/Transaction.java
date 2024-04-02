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
    private Integer idTransaction;
    private Double amount;
    private LocalDateTime transactionDatetime;
    private Integer idAccount;
    private TransactionType transactionType;
}
