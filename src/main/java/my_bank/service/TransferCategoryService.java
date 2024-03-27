package my_bank.service;

import my_bank.model.KeyAndValue;
import my_bank.model.entity.TransferCategory;
import my_bank.repository.AutoCrudOperation;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransferCategoryService {
    AutoCrudOperation<TransferCategory> transferCategoryAutoCrudOperation = new AutoCrudOperation<>(new TransferCategory());

    public List<TransferCategory> findAll() {
        return transferCategoryAutoCrudOperation.findAll();
    }
    public TransferCategory findById(Integer id) {
        return transferCategoryAutoCrudOperation.findFirstOneByKey(
                List.of(new KeyAndValue("id", id.toString()))
        );
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
    public TransferCategory findOneByIdTransfer(Integer idTransfer) {
        return transferCategoryAutoCrudOperation.findFirstOneByKey(
                List.of(new KeyAndValue("idTransfer", idTransfer.toString()))
        );
    }
}
