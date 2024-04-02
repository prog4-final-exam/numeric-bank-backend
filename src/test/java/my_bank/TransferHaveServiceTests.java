package my_bank;

import my_bank.model.entity.TransferHave;
import my_bank.service.TransferHaveService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TransferHaveServiceTests {
    TransferHaveService transferHaveService = new TransferHaveService();
    static TransferHave toInsert;
    static TransferHave inserted;
    static TransferHave updated;
    static int id;
    static TransferHave transferHave = new TransferHave(
            null,
            2,
            3
    );
    @Test
    void crudTest() {
        toInsert = transferHaveService.saveOrUpdate(transferHave);
        inserted = transferHaveService.findAll().getLast();
        id = inserted.getId();
        toInsert.setId(id);


        System.out.println("Insert");
        Assertions.assertEquals(inserted, toInsert);

        System.out.println("Find by id");
        Assertions.assertEquals(transferHaveService.findById(id), inserted);

        System.out.println("Update");
        updated = transferHave;
        updated.setId(id);
        updated.setIdTransfer(5);
        updated.setIdCategory(2);

        Assertions.assertEquals(transferHaveService.saveOrUpdate(updated), transferHaveService.findById(id));

        System.out.println("Delete");
        Assertions.assertEquals(transferHaveService.deleteById(id), true);

    }
}
