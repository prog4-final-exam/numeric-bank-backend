package my_bank.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TransferHave {
    private Integer idTransferHave;
    private Integer idTransfer;
    private Integer idCategory;
}
