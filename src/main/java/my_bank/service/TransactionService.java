package my_bank.service;

import my_bank.model.KeyAndValue;
import my_bank.model.entity.Transaction;
import my_bank.repository.AutoCrudOperation;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {
    AutoCrudOperation<Transaction> transactionAutoCrudOperation = new AutoCrudOperation<>(new Transaction());
    BalanceUpdater balanceUpdater = new BalanceUpdater();

    public List<Transaction> findAll() {
        return transactionAutoCrudOperation.findAll();
    }
    public Transaction findById(Integer id) {
        return transactionAutoCrudOperation.findFirstOneByKey(
                List.of(new KeyAndValue("id", id.toString()))
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
                List.of(new KeyAndValue("idAccount", idAccount.toString()))
        );
    }
    public Transaction findFirstOneByIdAccount(Integer idAccount) {
        return transactionAutoCrudOperation.findFirstOneByKey(
                List.of(new KeyAndValue("idAccount", idAccount.toString()))
        );
    }
    public List<Transaction> findManyByIdAccount(Integer idAccount) {
        return transactionAutoCrudOperation.findManyByKey(
                List.of(new KeyAndValue("idAccount", idAccount.toString()))
        );
    }
}
