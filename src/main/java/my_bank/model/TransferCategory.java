package my_bank.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import my_bank.model.Enum.CategoryType;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TransferCategory {
    private int id;
    private String name;
    private CategoryType type;
    private String comment;
    private int idTransfer;
}
