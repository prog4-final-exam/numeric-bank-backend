package my_bank.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import my_bank.model.Enum.CategoryType;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TransferCategory {
    private Integer id;
    private String name;
    private CategoryType categoryType;
    private String comment;
    private Integer idTransfer;
}
