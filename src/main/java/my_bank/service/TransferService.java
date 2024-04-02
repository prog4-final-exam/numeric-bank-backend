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
    BalanceUpdater balanceUpdater = new BalanceUpdater();

    public List<Transfer> findAll() {
        return transferAutoCrudOperation.findAll();
    }
    public Transfer findById(Integer idTransfer) {
        return transferAutoCrudOperation.findFirstOneByKey(
                List.of(new KeyAndValue("idTransfer", idTransfer.toString())), TABLE, null
        );
    }
    public Transfer saveOrUpdate(Transfer toSaveOrUpdate) {
        if (toSaveOrUpdate.getIdTransfer() == null) {
            if (!balanceUpdater.updateBalance(toSaveOrUpdate, null)) {
                return null;
            }
            return transferAutoCrudOperation.save(toSaveOrUpdate);
        } else if (findById(toSaveOrUpdate.getIdTransfer()) != null) {
            return transferAutoCrudOperation.update(toSaveOrUpdate);
        }
        return null;
    }
    public boolean deleteById(int idTransfer) {
        return transferAutoCrudOperation.deleteById(idTransfer);
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
