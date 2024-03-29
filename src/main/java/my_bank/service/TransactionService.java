package my_bank.service;

import my_bank.model.KeyAndValue;
import my_bank.model.entity.Transaction;
import my_bank.repository.AutoCrudOperation;
import org.springframework.stereotype.Service;

import java.util.List;

import static my_bank.model.Enum.FindSourceType.TABLE;

@Service
public class TransactionService {
    AutoCrudOperation<Transaction> transactionAutoCrudOperation = new AutoCrudOperation<>(new Transaction());
    BalanceUpdater balanceUpdater = new BalanceUpdater();

    public List<Transaction> findAll() {
        return transactionAutoCrudOperation.findAll();
    }
    public Transaction findById(Integer id) {
        return transactionAutoCrudOperation.findFirstOneByKey(
                List.of(new KeyAndValue("id", id.toString())), TABLE, null
        );
    }
    public Transaction save(Transaction toSave) {
        if (!balanceUpdater.updateBalance(null, toSave)) {
            return null;
        }

        return transactionAutoCrudOperation.save(toSave);
    }
    public Transaction update(Transaction toUpdate) {
        return transactionAutoCrudOperation.update(toUpdate);
    }
    public boolean deleteById(int id) {
        return transactionAutoCrudOperation.deleteById(id);
    }
    public Transaction findLastOneByIdAccount(Integer idAccount) {
        return transactionAutoCrudOperation.findLastOneByKey(
                List.of(new KeyAndValue("idAccount", idAccount.toString())), TABLE, null
        );
    }
    public Transaction findFirstOneByIdAccount(Integer idAccount) {
        return transactionAutoCrudOperation.findFirstOneByKey(
                List.of(new KeyAndValue("idAccount", idAccount.toString())), TABLE, null
        );
    }
    public List<Transaction> findManyByIdAccount(Integer idAccount) {
        return transactionAutoCrudOperation.findManyByKey(
                List.of(new KeyAndValue("idAccount", idAccount.toString())), TABLE, null
        );
    }
    public List<Transaction> findManyByKey(List<KeyAndValue> keyAndValueList) {
        return transactionAutoCrudOperation.findManyByKey(keyAndValueList, TABLE, null);
    }
}
