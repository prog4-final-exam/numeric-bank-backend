package my_bank.model;

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
    private double totalReceipts;
    private double totalExpenses;
}
