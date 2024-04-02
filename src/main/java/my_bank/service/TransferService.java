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
        return transferAutoCrudOperation.findAll(null);
    }
    public Transfer findById(Integer idTransfer) {
        return transferAutoCrudOperation.findFirstOneByKey(
                List.of(new KeyAndValue("idTransfer", idTransfer.toString())),
                TABLE,
                null,
                null
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
    public List<Transfer> findManyByIdAccountOwner(List<KeyAndValue> keyAndValueList) {
        return transferAutoCrudOperation.findManyByKey(
                keyAndValueList,
                TABLE,
                null,
                null
        );
    }
    public Transfer findFirstOneByIdAccountOwner(Integer idAccountOwner) {
        return transferAutoCrudOperation.findFirstOneByKey(
                List.of(new KeyAndValue("idAccountOwner", idAccountOwner.toString())),
                TABLE,
                null,
                null
        );
    }
    public Transfer findLastOneByIdAccountOwner(Integer idAccountOwner) {
        return transferAutoCrudOperation.findLastOneByKey(
                List.of(new KeyAndValue("idAccountOwner", idAccountOwner.toString())),
                TABLE,
                null,
                null
        );
    }
}
