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
        return transferCategoryAutoCrudOperation.findAll();
    }
    public Category findById(Integer id) {
        return transferCategoryAutoCrudOperation.findFirstOneByKey(
                List.of(new KeyAndValue("id", id.toString())), TABLE, null
        );
    }
    public Category saveOrUpdate(Category toSaveOrUpdate) {
        if (toSaveOrUpdate.getId() == null) {
            return transferCategoryAutoCrudOperation.save(toSaveOrUpdate);
        } else if (findById(toSaveOrUpdate.getId()) != null) {
            return transferCategoryAutoCrudOperation.update(toSaveOrUpdate);
        }
        return null;
    }
    public boolean deleteById(int id) {
        return transferCategoryAutoCrudOperation.deleteById(id);
    }
    public Category findOneByIdTransfer(Integer idTransfer) {
        return transferCategoryAutoCrudOperation.findFirstOneByKey(
                List.of(new KeyAndValue("idTransfer", idTransfer.toString())), TABLE, null
        );
    }
    public Category findOneByIdTransaction(Integer idTransaction) {
        return transferCategoryAutoCrudOperation.findFirstOneByKey(
                List.of(new KeyAndValue("idTransaction", idTransaction.toString())),
                TABLE,
                null
        );
    }
}
