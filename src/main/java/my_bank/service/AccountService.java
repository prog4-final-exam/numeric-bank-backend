package my_bank.service;

import my_bank.model.KeyAndValue;
import my_bank.model.entity.Account;
import my_bank.repository.AutoCrudOperation;
import org.springframework.stereotype.Service;

import java.util.List;

import static my_bank.model.Enum.FindSourceType.TABLE;

@Service
public class AccountService {
    AutoCrudOperation<Account> accountAutoCrudOperation = new AutoCrudOperation<>(new Account());

    public List<Account> findAll() {
        return accountAutoCrudOperation.findAll();
    }
    public Account findById(Integer id) {
        return accountAutoCrudOperation.findFirstOneByKey(
                List.of(new KeyAndValue("id", id.toString())), TABLE, null
        );
    }
    public Account saveOrUpdate(Account toSaveOrUpdate) {
        if (toSaveOrUpdate.getId() == null) {
            return accountAutoCrudOperation.save(toSaveOrUpdate);
        } else if (findById(toSaveOrUpdate.getId()) != null) {
            return accountAutoCrudOperation.update(toSaveOrUpdate);
        }
        return null;
    }
    public boolean deleteById(int id) {
        return accountAutoCrudOperation.deleteById(id);
    }
}
