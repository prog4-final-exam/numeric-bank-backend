package my_bank.service;

import my_bank.model.Transfer;
import my_bank.repository.AutoCrudOperation;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransferService {
    AutoCrudOperation<Transfer> transferAutoCrudOperation = new AutoCrudOperation<>(new Transfer());
    ReferenceGenerator referenceGenerator = new ReferenceGenerator();

    public List<Transfer> findAll() {
        return transferAutoCrudOperation.findAll();
    }
    public Transfer findById (Integer id) {
        return transferAutoCrudOperation.findOneByKey("id", id.toString());
    }
    public Transfer save(Transfer toSave) {
        toSave.setReference(referenceGenerator.generateReference());
        return transferAutoCrudOperation.save(toSave);
    }
    public Transfer update(Transfer toUpdate) {
        return transferAutoCrudOperation.update(toUpdate);
    }
    public boolean deleteById(int id) {
        return transferAutoCrudOperation.deleteById(id);
    }
}
