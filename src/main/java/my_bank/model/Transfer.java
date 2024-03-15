package my_bank.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import my_bank.model.Enum.TransferStatus;

import java.io.Serializable;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Transfer implements Serializable {
    private int id;
    private int idAccountSource;
    private int idAccountDestination;
    private String reason;
    private double amount;
    private LocalDateTime registerDateTime;
    private LocalDateTime valueDateTime;
    private TransferStatus status;
    private String reference;
}
