package my_bank.repository;

import my_bank.model.Enum.FindSourceType;
import my_bank.model.KeyAndValue;
import my_bank.model.TransactionWithCategory;
import org.springframework.stereotype.Repository;

import java.util.List;

import static my_bank.model.Enum.FindSourceType.TABLE;

@Repository
public class TransactionWithCategoryRepository {
    private static String  customRequest = """
        SELECT t.*,
            c.*,
            th.comment
        FROM transaction_have th
        RIGHT JOIN transaction t
        ON th.id_transaction = t.id_transaction
        LEFT JOIN category c
        ON th.id_category = c.id_category
        ORDER BY t.id_transaction
    """;
    AutoCrudOperation<TransactionWithCategory> transactionWithCategoryAutoCrudOperation = new AutoCrudOperation<>(new TransactionWithCategory());

    public List<TransactionWithCategory> findAll() {
        return transactionWithCategoryAutoCrudOperation.findAll(customRequest);
    }
    public TransactionWithCategory findById(Integer idTransaction) {
        return transactionWithCategoryAutoCrudOperation.findFirstOneByKey(
                List.of(new KeyAndValue("idTransaction", idTransaction.toString())),
                FindSourceType.TABLE,
                null,
                customRequest
        );
    }
    public TransactionWithCategory findLastOneByIdAccount(Integer idAccount) {
        return transactionWithCategoryAutoCrudOperation.findLastOneByKey(
                List.of(new KeyAndValue("idAccount", idAccount.toString())),
                TABLE,
                null,
                customRequest
        );
    }
    public TransactionWithCategory findFirstOneByIdAccount(Integer idAccount) {
        return transactionWithCategoryAutoCrudOperation.findFirstOneByKey(
                List.of(new KeyAndValue("idAccount", idAccount.toString())),
                TABLE,
                null,
                customRequest
        );
    }
    public List<TransactionWithCategory> findManyByIdAccount(Integer idAccount) {
        return transactionWithCategoryAutoCrudOperation.findManyByKey(
                List.of(new KeyAndValue("idAccount", idAccount.toString())),
                TABLE, null,
                customRequest
        );
    }
    public List<TransactionWithCategory> findManyByKey(List<KeyAndValue> keyAndValueList) {
        return transactionWithCategoryAutoCrudOperation.findManyByKey(
                keyAndValueList,
                TABLE,
                null,
                customRequest
        );
    }
}
