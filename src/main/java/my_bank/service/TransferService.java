package my_bank.service;

import my_bank.model.Transfer;
import my_bank.repository.AutoCrudOperation;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransferService {
    AutoCrudOperation<Transfer> transferAutoCrudOperation = new AutoCrudOperation<>(new Transfer());
    ReferenceGenerator referenceGenerator = new ReferenceGenerator();
    BalanceUpdater balanceUpdater = new BalanceUpdater();

    public List<Transfer> findAll() {
        return transferAutoCrudOperation.findAll();
    }
    public Transfer findById(Integer id) {
        return transferAutoCrudOperation.findFirstOneByKey("id", id.toString());
    }
    public Transfer save(Transfer toSave) {
        toSave.setReference(referenceGenerator.generateReference());
        if (!balanceUpdater.updateBalance(toSave, null)) {
            return null;
        }
        return transferAutoCrudOperation.save(toSave);
    }
    public Transfer update(Transfer toUpdate) {
        return transferAutoCrudOperation.update(toUpdate);
    }
    public boolean deleteById(int id) {
        return transferAutoCrudOperation.deleteById(id);
    }
    public List<Transfer> findManyByIdAccountSource(Integer idAccountSource) {
        return transferAutoCrudOperation.findManyByKey("idAccountSource", idAccountSource.toString());
    }
    public Transfer findFirstOneByIdAccountSource(Integer idAccountSource) {
        return transferAutoCrudOperation.findFirstOneByKey("idAccountSource", idAccountSource.toString());
    }
    public Transfer findLastOneByIdAccountSource(Integer idAccountSource) {
        return transferAutoCrudOperation.findLastOneByKey("idAccountSource", idAccountSource.toString());
    }
}
