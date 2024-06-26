package my_bank.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SumReceiptsExpenses implements Serializable {
    private LocalDateTime period;
    private Double totalReceipts;
    private Double totalExpenses;
}
