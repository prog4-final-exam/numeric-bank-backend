package my_bank.service;

import my_bank.repository.AutoCrudOperation;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransferCategory {
    AutoCrudOperation<TransferCategory> transferCategoryAutoCrudOperation = new AutoCrudOperation<>(new TransferCategory());

    public List<TransferCategory> findAll() {
        return transferCategoryAutoCrudOperation.findAll();
    }
    public TransferCategory findById(int id) {
        return transferCategoryAutoCrudOperation.findById(id);
    }
    public TransferCategory save(TransferCategory toSave) {
        return transferCategoryAutoCrudOperation.save(toSave);
    }
    public TransferCategory update(TransferCategory toUpdate) {
        return transferCategoryAutoCrudOperation.update(toUpdate);
    }
    public boolean deleteById(int id) {
        return transferCategoryAutoCrudOperation.deleteById(id);
    }
}
