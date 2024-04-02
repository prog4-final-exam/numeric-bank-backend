package my_bank.service;

import lombok.AllArgsConstructor;
import my_bank.model.KeyAndValue;
import my_bank.model.TransactionWithCategory;
import my_bank.repository.TransactionWithCategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TransactionWithCategoryService {
    TransactionWithCategoryRepository transactionWithCategoryRepository;

    public List<TransactionWithCategory> findAll() {
        return transactionWithCategoryRepository.findAll();
    }
    public TransactionWithCategory findByIdTransaction(Integer idTransaction) {
        return transactionWithCategoryRepository.findById(idTransaction);
    }
    public List<TransactionWithCategory> findManyByKey(List<KeyAndValue> keyAndValueList) {
        return transactionWithCategoryRepository.findManyByKey(keyAndValueList);
    }
}
