package my_bank.service;

import my_bank.model.KeyAndValue;
import my_bank.model.entity.Transfer;
import my_bank.repository.AutoCrudOperation;
import org.springframework.stereotype.Service;

import java.util.List;

import static my_bank.model.Enum.FindSourceType.TABLE;

@Service
public class TransferService {
    AutoCrudOperation<Transfer> transferAutoCrudOperation = new AutoCrudOperation<>(new Transfer());
    ReferenceGenerator referenceGenerator = new ReferenceGenerator();
    BalanceUpdater balanceUpdater = new BalanceUpdater();

    public List<Transfer> findAll() {
        return transferAutoCrudOperation.findAll();
    }
    public Transfer findById(Integer id) {
        return transferAutoCrudOperation.findFirstOneByKey(
                List.of(new KeyAndValue("id", id.toString())), TABLE, null
        );
    }
    public Transfer saveOrUpdate(Transfer toSaveOrUpdate) {
        if (toSaveOrUpdate.getId() == null) {
            toSaveOrUpdate.setReference(referenceGenerator.generateReference());
            if (!balanceUpdater.updateBalance(toSaveOrUpdate, null)) {
                return null;
            }
            return transferAutoCrudOperation.save(toSaveOrUpdate);
        } else if (findById(toSaveOrUpdate.getId()) != null) {
            return transferAutoCrudOperation.update(toSaveOrUpdate);
        }
        return null;
    }
    public boolean deleteById(int id) {
        return transferAutoCrudOperation.deleteById(id);
    }
    public List<Transfer> findManyByIdAccountSource(List<KeyAndValue> keyAndValueList) {
        return transferAutoCrudOperation.findManyByKey(keyAndValueList, TABLE, null);
    }
    public Transfer findFirstOneByIdAccountSource(Integer idAccountSource) {
        return transferAutoCrudOperation.findFirstOneByKey(
                List.of(new KeyAndValue("idAccountSource", idAccountSource.toString())), TABLE, null
        );
    }
    public Transfer findLastOneByIdAccountSource(Integer idAccountSource) {
        return transferAutoCrudOperation.findLastOneByKey(
                List.of(new KeyAndValue("idAccountSource", idAccountSource.toString())), TABLE, null
        );
    }
}
