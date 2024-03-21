package my_bank.service;

import my_bank.model.Transaction;
import my_bank.repository.AutoCrudOperation;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {
    AutoCrudOperation<Transaction> transactionAutoCrudOperation = new AutoCrudOperation<>(new Transaction());

    public List<Transaction> findAll() {
        return transactionAutoCrudOperation.findAll();
    }
    public Transaction findById(Integer id) {
        return transactionAutoCrudOperation.findOneByKey("id", id.toString());
    }
    public Transaction save(Transaction toSave) {
        return transactionAutoCrudOperation.save(toSave);
    }
    public Transaction update(Transaction toUpdate) {
        return transactionAutoCrudOperation.update(toUpdate);
    }
    public boolean deleteById(int id) {
        return transactionAutoCrudOperation.deleteById(id);
    }
}
