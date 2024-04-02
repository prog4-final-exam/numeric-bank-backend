package my_bank;

import my_bank.model.entity.TransactionHave;
import my_bank.service.TransactionHaveService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TransactionHaveServiceTests {
    TransactionHaveService transactionHaveService = new TransactionHaveService();
    static TransactionHave toInsert;
    static TransactionHave inserted;
    static TransactionHave updated;
    static int id;
    static TransactionHave transferHave = new TransactionHave(
            null,
            2,
            3
    );
    @Test
    void crudTest() {
        toInsert = transactionHaveService.saveOrUpdate(transferHave);
        inserted = transactionHaveService.findAll().getLast();
        id = inserted.getId();
        toInsert.setId(id);


        System.out.println("Insert");
        Assertions.assertEquals(inserted, toInsert);

        System.out.println("Find by id");
        Assertions.assertEquals(transactionHaveService.findById(id), inserted);

        System.out.println("Update");
        updated = transferHave;
        updated.setId(id);
        updated.setIdTransaction(5);
        updated.setIdCategory(2);

        Assertions.assertEquals(transactionHaveService.saveOrUpdate(updated), transactionHaveService.findById(id));

        System.out.println("Delete");
        Assertions.assertEquals(transactionHaveService.deleteById(id), true);

    }
}
