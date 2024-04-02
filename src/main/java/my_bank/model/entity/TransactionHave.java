package my_bank.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TransactionHave {
    private Integer id;
    private Integer idTransaction;
    private Integer idCategory;
}
