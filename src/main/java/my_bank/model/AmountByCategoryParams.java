package my_bank.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AmountByCategoryParams implements Serializable {
    private int accountId;
    private String typeCategory;
    private LocalDate startDate;
    private LocalDate endDate;
}
