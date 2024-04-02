package my_bank.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import my_bank.model.entity.Category;
import my_bank.model.entity.Transaction;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TransactionWithCategory implements Serializable {
    private Transaction transaction;
    private Category category;
    private String comment;
}
