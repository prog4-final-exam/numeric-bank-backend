package my_bank.service;

import my_bank.model.KeyAndValue;
import my_bank.model.entity.Balance;
import my_bank.repository.AutoCrudOperation;
import org.springframework.stereotype.Service;

import java.util.List;

import static my_bank.model.Enum.FindSourceType.TABLE;

@Service
public class BalanceService {
    AutoCrudOperation<Balance> balanceAutoCrudOperation = new AutoCrudOperation<>(new Balance());

    public List<Balance> findAll() {
        return balanceAutoCrudOperation.findAll(null);
    }
    public Balance findById(Integer idBalance) {
        return balanceAutoCrudOperation.findFirstOneByKey(
                List.of(new KeyAndValue("idBalance", idBalance.toString())),
                TABLE,
                null,
                null
        );
    }
    public Balance saveOrUpdate(Balance toSaveOrUpdate) {
        if (toSaveOrUpdate.getIdBalance() == null) {
            return balanceAutoCrudOperation.save(toSaveOrUpdate);
        } else if (findById(toSaveOrUpdate.getIdBalance()) != null) {
            return balanceAutoCrudOperation.update(toSaveOrUpdate);
        }
        return null;
    }
    public boolean deleteById(int idBalance) {
        return balanceAutoCrudOperation.deleteById(idBalance);
    }
    public Balance findLastOneByIdAccount(Integer idAccount) {
        return balanceAutoCrudOperation.findLastOneByKey(
                List.of(new KeyAndValue("idAccount", idAccount.toString())),
                TABLE,
                null,
                null
        );
    }
    public Balance findFirstOneByIdAccount(Integer idAccount) {
        return balanceAutoCrudOperation.findFirstOneByKey(
                List.of(new KeyAndValue("idAccount", idAccount.toString())),
                TABLE,
                null,
                null
        );
    }
    public List<Balance> findManyByIdAccount(List<KeyAndValue> keyAndValueList) {
        return balanceAutoCrudOperation.findManyByKey(keyAndValueList,
                TABLE,
                null,
                null
        );
    }
}
