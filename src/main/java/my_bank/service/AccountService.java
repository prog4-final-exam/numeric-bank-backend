package my_bank.service;

import my_bank.model.Account;
import my_bank.repository.AutoCrudOperation;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {
    AutoCrudOperation<Account> accountAutoCrudOperation = new AutoCrudOperation<>(new Account());

    public List<Account> findAll() {
        return accountAutoCrudOperation.findAll();
    }
    public Account findById(int id) {
        return accountAutoCrudOperation.findById(id);
    }
    public Account save(Account toSave) {
        return accountAutoCrudOperation.save(toSave);
    }
    public boolean deleteById(int id) {
        return accountAutoCrudOperation.deleteById(id)
    }
    public Account update(Account toUpdate) {
        return accountAutoCrudOperation.update(toUpdate);
    }
}
