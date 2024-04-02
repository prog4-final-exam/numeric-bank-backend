package my_bank.service;

import my_bank.model.Enum.FindSourceType;
import my_bank.model.KeyAndValue;
import my_bank.model.entity.TransferHave;
import my_bank.repository.AutoCrudOperation;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransferHaveService {
    AutoCrudOperation<TransferHave> transferHaveAutoCrudOperation = new AutoCrudOperation<>(new TransferHave());

    public List<TransferHave> findAll() {
        return transferHaveAutoCrudOperation.findAll();
    }
    public TransferHave findById(Integer id) {
       return transferHaveAutoCrudOperation.findFirstOneByKey(
               List.of(new KeyAndValue("id", id.toString())),
               FindSourceType.TABLE,
               null
       ) ;
    }
    public TransferHave saveOrUpdate(TransferHave toSaveOrUpdate) {
        if (toSaveOrUpdate.getId() == null) {
            return transferHaveAutoCrudOperation.save(toSaveOrUpdate);
        } else if (findById(toSaveOrUpdate.getId()) != null) {
            return transferHaveAutoCrudOperation.update(toSaveOrUpdate);
        }
        return null;
    }
    public boolean deleteById(int id) {
        return transferHaveAutoCrudOperation.deleteById(id);
    }
}
