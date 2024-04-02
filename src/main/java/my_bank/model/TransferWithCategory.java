package my_bank.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import my_bank.model.entity.Category;
import my_bank.model.entity.Transfer;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TransferWithCategory implements Serializable {
    private Transfer transfer;
    private Category category;
    private String comment;
}
