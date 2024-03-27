package my_bank.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SumAmountsByCategory implements Serializable {
    private String category;
    private LocalDateTime period;
    private double totalAmount;
}
