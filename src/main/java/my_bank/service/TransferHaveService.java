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
    public TransferHave findById(Integer idTransferHave) {
       return transferHaveAutoCrudOperation.findFirstOneByKey(
               List.of(new KeyAndValue("idTransferHave", idTransferHave.toString())),
               FindSourceType.TABLE,
               null
       ) ;
    }
    public TransferHave saveOrUpdate(TransferHave toSaveOrUpdate) {
        if (toSaveOrUpdate.getIdTransferHave() == null) {
            return transferHaveAutoCrudOperation.save(toSaveOrUpdate);
        } else if (findById(toSaveOrUpdate.getIdTransferHave()) != null) {
            return transferHaveAutoCrudOperation.update(toSaveOrUpdate);
        }
        return null;
    }
    public boolean deleteById(int idTransferHave) {
        return transferHaveAutoCrudOperation.deleteById(idTransferHave);
    }
}
