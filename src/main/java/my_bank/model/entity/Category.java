package my_bank.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import my_bank.model.Enum.CategoryType;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Category {
    private Integer idCategory;
    private String categoryName;
    private CategoryType categoryType;
}
