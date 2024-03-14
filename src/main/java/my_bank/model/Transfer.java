package my_bank.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Transfer implements Serializable {
    private int id;
    private int idAccount;
    private int idTransferCategory;
    private String reason;
    private double amount;
    private LocalDateTime registerDate;
    private LocalDateTime valueDateTime;
    private boolean isCanceled;
    private boolean isExternal;
    private String reference;
}
