package my_bank.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReceiptExpenseParams implements Serializable {
    private LocalDate startDate;
    private LocalDate endDate;
    private String intervalType;
}
