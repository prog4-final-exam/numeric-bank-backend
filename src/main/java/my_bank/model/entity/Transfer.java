package my_bank.model.entity;

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
    private Integer idTransfer;
    private String reason;
    private String label;
    private Double amount;
    private LocalDateTime transferDatetime;
    private LocalDateTime valueDatetime;
    private StatusType status;
    private String destinationAccountNumber;
    private String reference;
    private Boolean isExternalBank;
    private Integer idAccountSource;
}
