package my_bank.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import my_bank.model.Enum.StatusType;

import java.io.Serializable;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Transfer implements Serializable {
    private int id;
    private String reason;
    private double amount;
    private LocalDateTime registerDatetime;
    private LocalDateTime valueDatetime;
    private StatusType status;
    private int idAccountSource;
    private int idAccountDestination;
    private String reference;
}
