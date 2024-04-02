package my_bank;

import my_bank.model.Enum.OperationType;
import my_bank.model.Enum.StatusType;
import my_bank.model.entity.Balance;
import my_bank.model.entity.Transfer;
import my_bank.service.BalanceService;
import my_bank.service.TransferService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
public class TransferServiceTests {
    TransferService transferService = new TransferService();
    BalanceService balanceService = new BalanceService();
    static int idAccountOwner = 05;
    static int idAccountCorrespondant = 8;
    static Transfer toInsert;
    static Transfer inserted;
    static String wrongAccountNumber = "546545422454564";
    static String accountNumber = "000400030000000001002";
    static Transfer transfer = new Transfer(
            null,
            "test_reason",
            "test_label",
            Double.valueOf(500),
            LocalDateTime.now(),
            LocalDateTime.now(),
            StatusType.COMPLETED,
            null,
            null,
            OperationType.CREDIT,
            null,
            idAccountCorrespondant
    );
    @Test
    void wrongAccountNumberTest() {
        transfer.setCorrespondantAccountNumber(wrongAccountNumber);
        transfer.setIsExternalBank(false);
        Transfer toInsert = transferService.saveOrUpdate(transfer);
        Assertions.assertEquals(toInsert, null);
        System.out.println("Wrong accountNumber --> null");
    }

    @Test
    void lowerTransferTest() {
        Balance balance = balanceService.findLastOneByIdAccount(idAccountOwner);
        Balance balanceCorrespondant = balanceService.findLastOneByIdAccount(idAccountCorrespondant);

        transfer.setAmount(Double.valueOf(balance.getMainBalance() - 500));
        transfer.setCorrespondantAccountNumber(accountNumber);
        transfer.setIsExternalBank(false);

        toInsert = transferService.saveOrUpdate(transfer);
        inserted = transferService.findLastOneByIdAccountOwner(idAccountOwner);

        toInsert.setIdTransfer(inserted.getIdTransfer());
        toInsert.setTransferDatetime(inserted.getTransferDatetime());
        toInsert.setValueDatetime(inserted.getValueDatetime());
        toInsert.setReference(inserted.getReference());

        transferService.deleteById(inserted.getIdTransfer());
        balanceService.deleteById(balance.getIdBalance());
        balanceService.deleteById(balanceCorrespondant.getIdBalance());

        Assertions.assertEquals(inserted, toInsert);
        System.out.println("lowerTransferTest --> OK");
    }

    @Test
    void greaterTransferTest() {
        Balance balance = balanceService.findLastOneByIdAccount(idAccountOwner);

        transfer.setAmount(Double.valueOf(balance.getMainBalance() + 500));
        transfer.setCorrespondantAccountNumber(accountNumber);
        transfer.setIsExternalBank(false);
        toInsert = transferService.saveOrUpdate(transfer);

        Assertions.assertEquals(inserted, null);
        System.out.println("greaterTransferTest --> null");
    }
}
