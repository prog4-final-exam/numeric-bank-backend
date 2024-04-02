package my_bank.service;

import my_bank.model.KeyAndValue;
import my_bank.model.entity.Category;
import my_bank.repository.AutoCrudOperation;
import org.springframework.stereotype.Service;

import java.util.List;

import static my_bank.model.Enum.FindSourceType.TABLE;

@Service
public class CategoryService {
    AutoCrudOperation<Category> transferCategoryAutoCrudOperation = new AutoCrudOperation<>(new Category());

    public List<Category> findAll() {
        return transferCategoryAutoCrudOperation.findAll(null);
    }
    public Category findById(Integer idCategory) {
        return transferCategoryAutoCrudOperation.findFirstOneByKey(
                List.of(new KeyAndValue("idCategory", idCategory.toString())),
                TABLE,
                null,
                null
        );
    }
    public Category saveOrUpdate(Category toSaveOrUpdate) {
        if (toSaveOrUpdate.getIdCategory() == null) {
            return transferCategoryAutoCrudOperation.save(toSaveOrUpdate);
        } else if (findById(toSaveOrUpdate.getIdCategory()) != null) {
            return transferCategoryAutoCrudOperation.update(toSaveOrUpdate);
        }
        return null;
    }
    public boolean deleteById(int idCategory) {
        return transferCategoryAutoCrudOperation.deleteById(idCategory);
    }
}
