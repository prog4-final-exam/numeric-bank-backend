package my_bank.service;

import my_bank.model.Account;
import my_bank.model.Balance;
import my_bank.model.Enum.TransactionType;
import my_bank.model.Transaction;
import my_bank.repository.AutoCrudOperation;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import my_bank.service.BalanceUpdater;

@Service
public class TransactionService {
    AutoCrudOperation<Transaction> transactionAutoCrudOperation = new AutoCrudOperation<>(new Transaction());
    BalanceUpdater balanceUpdater = new BalanceUpdater();

    public List<Transaction> findAll() {
        return transactionAutoCrudOperation.findAll();
    }
    public Transaction findById(Integer id) {
        return transactionAutoCrudOperation.findFirstOneByKey("id", id.toString());
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
        return transactionAutoCrudOperation.findLastOneByKey("idAccount", idAccount.toString());
    }
    public Transaction findFirstOneByIdAccount(Integer idAccount) {
        return transactionAutoCrudOperation.findFirstOneByKey("idAccount", idAccount.toString());
    }
    public List<Transaction> findManyByIdAccount(Integer idAccount) {
        return transactionAutoCrudOperation.findManyByKey("idAccount", idAccount.toString());
    }
}
