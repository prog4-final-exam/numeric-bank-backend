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
    public TransactionHave findById(Integer idTransactionHave) {
        return transactionHaveAutoCrudOperation.findFirstOneByKey(
                List.of(new KeyAndValue("idTransactionHave", idTransactionHave.toString())),
                FindSourceType.TABLE,
                null
        );
    }
    public boolean deleteById(int idTransactionHave) {
        return transactionHaveAutoCrudOperation.deleteById(idTransactionHave);
    }
    public TransactionHave saveOrUpdate(TransactionHave toSaveOrUpdate) {
        if (toSaveOrUpdate.getIdTransactionHave() == null) {
            return transactionHaveAutoCrudOperation.save(toSaveOrUpdate);
        } else if (findById(toSaveOrUpdate.getIdTransactionHave()) != null) {
            return transactionHaveAutoCrudOperation.update(toSaveOrUpdate);
        }
        return null;
    }
}
