package my_bank.service;

import my_bank.model.Account;
import my_bank.repository.AutoCrudOperation;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService {
    AutoCrudOperation<Account> accountAutoCrudOperation = new AutoCrudOperation<>(new Account());
    AccountNumberManager accountNumberManager = new AccountNumberManager();

    public List<Account> findAll() {
        return accountAutoCrudOperation.findAll();
    }
    public Account findById(Integer id) {
        return accountAutoCrudOperation.findFirstOneByKey("id", id.toString());
    }
    public Account save(Account toSave) {
        toSave.setAccountNumber(
                accountNumberManager.generateAccountNumber(toSave.getId())
        );
        return accountAutoCrudOperation.save(toSave);
    }
    public boolean deleteById(int id) {
        return accountAutoCrudOperation.deleteById(id);
    }
    public Account update(Account toUpdate) {
        return accountAutoCrudOperation.update(toUpdate);
    }
}
