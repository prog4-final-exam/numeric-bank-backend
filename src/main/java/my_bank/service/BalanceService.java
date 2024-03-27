package my_bank.service;

import my_bank.model.entity.Balance;
import my_bank.repository.AutoCrudOperation;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BalanceService {
    AutoCrudOperation<Balance> balanceAutoCrudOperation = new AutoCrudOperation<>(new Balance());

    public List<Balance> findAll() {
        return balanceAutoCrudOperation.findAll();
    }
    public Balance findById(Integer id) {
        return balanceAutoCrudOperation.findFirstOneByKey("id", id.toString());
    }
    public Balance save(Balance toSave) {
        return balanceAutoCrudOperation.save(toSave);
    }
    public Balance update(Balance toUpdate) {
        return balanceAutoCrudOperation.update(toUpdate);
    }
    public boolean deleteById(int id) {
        return balanceAutoCrudOperation.deleteById(id);
    }
    public Balance findLastOneByIdAccount(Integer idAccount) {
        return balanceAutoCrudOperation.findLastOneByKey("idAccount", idAccount.toString());
    }
    public Balance findFirstOneByIdAccount(Integer idAccount) {
        return balanceAutoCrudOperation.findFirstOneByKey("idAccount", idAccount.toString());
    }
    public List<Balance> findManyByIdAccount(Integer idAccount) {
        return balanceAutoCrudOperation.findManyByKey("idAccount", idAccount.toString());
    }
    public Balance findLastOneByAccountNumber(String accountNumber) {
        return balanceAutoCrudOperation.findLastOneByKey("accountNumber", accountNumber);
    }
    public Balance findFirstOneByAccountNumber(String accountNumber) {
        return balanceAutoCrudOperation.findFirstOneByKey("accountNumber", accountNumber);
    }
}
