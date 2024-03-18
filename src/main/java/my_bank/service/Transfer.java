package my_bank.service;

import my_bank.repository.AutoCrudOperation;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Transfer {
    AutoCrudOperation<Transfer> transferAutoCrudOperation = new AutoCrudOperation<>(new Transfer());

    public List<Transfer> findAll() {
        return transferAutoCrudOperation.findAll();
    }
    public Transfer findById (int id) {
        return transferAutoCrudOperation.findById(id);
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
