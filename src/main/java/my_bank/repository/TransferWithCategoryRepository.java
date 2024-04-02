package my_bank.repository;

import my_bank.model.Enum.FindSourceType;
import my_bank.model.KeyAndValue;
import my_bank.model.TransferWithCategory;
import org.springframework.stereotype.Repository;

import java.util.List;

import static my_bank.model.Enum.FindSourceType.TABLE;

@Repository
public class TransferWithCategoryRepository {
    private static String customRequest = """
       SELECT tr.*,
            c.*,
            trh.comment
        FROM transfer_have trh
        RIGHT JOIN transfer tr
        ON trh.id_transaction = t.id_transaction
        LEFT JOIN category c
        ON trh.id_category = c.id_category
        ORDER BY tr.id_transaction
    """;
    AutoCrudOperation<TransferWithCategory> transferWithCategoryAutoCrudOperation = new AutoCrudOperation<>(new TransferWithCategory());

    public List<TransferWithCategory> findAll() {
        return transferWithCategoryAutoCrudOperation.findAll(customRequest);
    }
    public TransferWithCategory findById(Integer idTransfer) {
        return transferWithCategoryAutoCrudOperation.findFirstOneByKey(
                List.of(new KeyAndValue("idTransfer", idTransfer.toString())),
                FindSourceType.TABLE,
                null,
                customRequest
        );
    }
    public TransferWithCategory findLastOneByIdAccount(Integer idAccount) {
        return transferWithCategoryAutoCrudOperation.findLastOneByKey(
                List.of(new KeyAndValue("idAccount", idAccount.toString())),
                TABLE,
                null,
                customRequest
        );
    }
    public TransferWithCategory findFirstOneByIdAccount(Integer idAccount) {
        return transferWithCategoryAutoCrudOperation.findFirstOneByKey(
                List.of(new KeyAndValue("idAccount", idAccount.toString())),
                TABLE,
                null,
                customRequest
        );
    }
    public List<TransferWithCategory> findManyByIdAccount(Integer idAccount) {
        return transferWithCategoryAutoCrudOperation.findManyByKey(
                List.of(new KeyAndValue("idAccount", idAccount.toString())),
                TABLE, null,
                customRequest
        );
    }
    public List<TransferWithCategory> findManyByKey(List<KeyAndValue> keyAndValueList) {
        return transferWithCategoryAutoCrudOperation.findManyByKey(
                keyAndValueList,
                TABLE,
                null,
                customRequest
        );
    }
}
