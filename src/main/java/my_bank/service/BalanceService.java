package my_bank.service;

import my_bank.model.Balance;
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
        return balanceAutoCrudOperation.findOneByKey("id", id.toString());
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
    public Balance findByIdAccount(Integer idAccount) {
        return balanceAutoCrudOperation.findOneByKey("idAccount", idAccount.toString());
    }
    public List<Balance> findManyByIdAccount(Integer idAccount) {
        return balanceAutoCrudOperation.findManyByKey("idAccount", idAccount.toString());
    }
}
