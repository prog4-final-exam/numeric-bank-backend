package my_bank.service;

import my_bank.model.Enum.FindSourceType;
import my_bank.model.KeyAndValue;
import my_bank.model.entity.TransactionHave;
import my_bank.repository.AutoCrudOperation;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionHaveService {
    AutoCrudOperation<TransactionHave> transactionHaveAutoCrudOperation = new AutoCrudOperation<>(new TransactionHave());

    public List<TransactionHave> findAll() {
        return transactionHaveAutoCrudOperation.findAll();
    }
    public TransactionHave findById(Integer id) {
        return transactionHaveAutoCrudOperation.findFirstOneByKey(
                List.of(new KeyAndValue("id", id.toString())),
                FindSourceType.TABLE,
                null
        );
    }
    public boolean deleteById(int id) {
        return transactionHaveAutoCrudOperation.deleteById(id);
    }
    public TransactionHave saveOrUpdate(TransactionHave toSaveOrUpdate) {
        if (toSaveOrUpdate.getId() == null) {
            return transactionHaveAutoCrudOperation.save(toSaveOrUpdate);
        } else if (findById(toSaveOrUpdate.getId()) != null) {
            return transactionHaveAutoCrudOperation.update(toSaveOrUpdate);
        }
        return null;
    }
}
