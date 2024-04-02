package my_bank;

import my_bank.model.Enum.OperationType;
import my_bank.model.entity.Balance;
import my_bank.model.entity.Transaction;
import my_bank.service.BalanceService;
import my_bank.service.TransactionService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
public class TransactionServiceTests {
    TransactionService transactionService = new TransactionService();
    BalanceService balanceService = new BalanceService();
    static int idAccount = 03;
    static int idAccountOverdraft = 02;
    static Transaction toInsert;
    static Transaction inserted;
    static Transaction debitTransaction = new Transaction(
            null,
            null,
            LocalDateTime.now(),
            idAccount,
            OperationType.DEBIT
    );

    @Test
    void greaterDebitTransactionTest() {
        Balance actualBalance = balanceService.findLastOneByIdAccount(idAccount);
        debitTransaction.setAmount(Double.valueOf(actualBalance.getMainBalance() + 2000));

        toInsert = transactionService.saveOrUpdate(debitTransaction);
        Assertions.assertEquals(toInsert, null);
        System.out.println("Transaction mount > Main balance ---> null ");
    }

    @Test
    void overdraftTransactionTest() {
        Balance actualBalance = balanceService.findLastOneByIdAccount(idAccount);
        debitTransaction.setIdAccount(idAccountOverdraft);
        debitTransaction.setAmount(Double.valueOf(actualBalance.getMainBalance() + 500));

        toInsert = transactionService.saveOrUpdate(debitTransaction);
        inserted = transactionService.findLastOneByIdAccount(idAccountOverdraft);

        toInsert.setIdTransaction(inserted.getIdTransaction());
        toInsert.setTransactionDatetime(inserted.getTransactionDatetime());

        transactionService.deleteById(inserted.getIdTransaction());
        Assertions.assertEquals(inserted, toInsert);
        System.out.println("Transaction mount > Main balance && overdraft  ---> OK");
    }

    @Test
    void lowerDebitTransactionTest() {
        Balance actualBalance = balanceService.findLastOneByIdAccount(idAccount);
        debitTransaction.setAmount(Double.valueOf(actualBalance.getMainBalance() - 500));

        toInsert = transactionService.saveOrUpdate(debitTransaction);
        inserted = transactionService.findLastOneByIdAccount(idAccount);

        toInsert.setIdTransaction(inserted.getIdTransaction());
        toInsert.setTransactionDatetime(inserted.getTransactionDatetime());

        transactionService.deleteById(inserted.getIdTransaction());
        Assertions.assertEquals(inserted, toInsert);
        System.out.println("Transaction amount =< Balance amount --> Ok");
    }
}
