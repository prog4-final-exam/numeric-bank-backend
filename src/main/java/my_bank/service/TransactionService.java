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
    public Transaction findById(Integer idTransaction) {
        return transactionAutoCrudOperation.findFirstOneByKey(
                List.of(new KeyAndValue("idTransaction", idTransaction.toString())), TABLE, null
        );
    }
    public Transaction saveOrUpdate(Transaction toSaveOrUpdate) {
        if (toSaveOrUpdate.getIdTransaction() == null) {
            if (!balanceUpdater.updateBalance(null, toSaveOrUpdate)) {
                return null;
            }
            return transactionAutoCrudOperation.save(toSaveOrUpdate);
        } else if (findById(toSaveOrUpdate.getIdTransaction()) != null) {
            return transactionAutoCrudOperation.update(toSaveOrUpdate);
        }
        return null;
    }
    public boolean deleteById(int idTransaction) {
        return transactionAutoCrudOperation.deleteById(idTransaction);
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
