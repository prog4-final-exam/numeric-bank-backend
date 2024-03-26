package my_bank.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SumAmountsByCategory {
    private String category;
    private LocalDateTime period;
    private double totalAmount;
}
