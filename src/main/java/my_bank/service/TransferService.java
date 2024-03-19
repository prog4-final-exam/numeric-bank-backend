package my_bank.service;

import my_bank.model.Transfer;
import my_bank.repository.AutoCrudOperation;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransferService {
    AutoCrudOperation<Transfer> transferAutoCrudOperation = new AutoCrudOperation<>(new Transfer());

    public List<Transfer> findAll() {
        return transferAutoCrudOperation.findAllOrById(null);
    }
    public Transfer findById (int id) {
        return transferAutoCrudOperation.findAllOrById(id).getFirst();
    }
    public Transfer save(Transfer toSave) {
        return transferAutoCrudOperation.save(toSave);
    }
    public Transfer update(Transfer toUpdate) {
        return transferAutoCrudOperation.update(toUpdate);
    }
    public boolean deleteById(int id) {
        return transferAutoCrudOperation.deleteById(id);
    }
}
