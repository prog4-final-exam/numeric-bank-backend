package my_bank.service;

import lombok.AllArgsConstructor;
import my_bank.model.KeyAndValue;
import my_bank.model.TransferWithCategory;
import my_bank.repository.TransferWithCategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class TransferWithCategoryService {
    TransferWithCategoryRepository transferWithCategoryRepository;

    public List<TransferWithCategory> findAll() {
        return transferWithCategoryRepository.findAll();
    }
    public TransferWithCategory findByIdTransfer(Integer idTransfer) {
        return transferWithCategoryRepository.findById(idTransfer);
    }
    public List<TransferWithCategory> findManyByKey(List<KeyAndValue> keyAndValueList) {
        return transferWithCategoryRepository.findManyByKey(keyAndValueList);
    }
}
