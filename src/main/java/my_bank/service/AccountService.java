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
        return accountAutoCrudOperation.findAll(null);
    }
    public Account findById(Integer idAccount) {
        return accountAutoCrudOperation.findFirstOneByKey(
                List.of(new KeyAndValue("idAccount", idAccount.toString())),
                TABLE,
                null,
                null
        );
    }
    public Account saveOrUpdate(Account toSaveOrUpdate) {
        if (toSaveOrUpdate.getIdAccount() == null) {
            return accountAutoCrudOperation.save(toSaveOrUpdate);
        } else if (findById(toSaveOrUpdate.getIdAccount()) != null) {
            return accountAutoCrudOperation.update(toSaveOrUpdate);
        }
        return null;
    }
    public boolean deleteById(int idAccount) {
        return accountAutoCrudOperation.deleteById(idAccount);
    }
}
