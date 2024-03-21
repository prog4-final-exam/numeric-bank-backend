package my_bank.service;

import my_bank.model.Account;
import my_bank.model.Balance;
import my_bank.model.Enum.TransactionType;
import my_bank.model.Transaction;
import my_bank.repository.AutoCrudOperation;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TransactionService {
    AutoCrudOperation<Transaction> transactionAutoCrudOperation = new AutoCrudOperation<>(new Transaction());
    AutoCrudOperation<Balance> balanceAutoCrudOperation = new AutoCrudOperation<>(new Balance());
    AutoCrudOperation<Account> accountAutoCrudOperation = new AutoCrudOperation<>(new Account());

    public List<Transaction> findAll() {
        return transactionAutoCrudOperation.findAll();
    }
    public Transaction findById(Integer id) {
        return transactionAutoCrudOperation.findOneByKey("id", id.toString());
    }
    public Transaction save(Transaction toSave) {
        int accountId = toSave.getIdAccount();
        Balance accountBalance = balanceAutoCrudOperation.findAllOrById(accountId).getFirst();
        double mainBalance = accountBalance.getMainBalance();
        double amount = toSave.getAmount();

        if (toSave.getTransactionType() == TransactionType.CREDIT) {
            accountBalance.setMainBalance(mainBalance + amount);
        } else {
            Account account = accountAutoCrudOperation.findAllOrById(accountId).getFirst();
            if (amount > mainBalance) {
                if (account.isOverdraftAllowed()) {
                    accountBalance.setMainBalance(0);
                    accountBalance.setLoanAmount((mainBalance - amount) * -1);
                    accountBalance.setLoanInterest(0.01);
                } {
                    return null;
                }
            } else {
                accountBalance.setMainBalance(mainBalance - amount);
            }
        }
        accountBalance.setBalanceDatetime(LocalDateTime.now());
        Transaction transactionSaved = transactionAutoCrudOperation.save(toSave);

        if (
                balanceAutoCrudOperation.update(accountBalance) != null
                && transactionSaved != null
        ) {
           return transactionSaved;
        } else {
            return null;
        }
    }
    public Transaction update(Transaction toUpdate) {
        return transactionAutoCrudOperation.update(toUpdate);
    }
    public boolean deleteById(int id) {
        return transactionAutoCrudOperation.deleteById(id);
    }
}
