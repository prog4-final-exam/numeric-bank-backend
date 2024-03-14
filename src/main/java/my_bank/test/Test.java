package my_bank.test;

import my_bank.model.Account;
import my_bank.model.Enum.CategoryType;
import my_bank.model.Enum.StatusType;
import my_bank.model.Transfer;
import my_bank.model.TransferCategory;
import my_bank.repository.AutoCrudOperation;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Test {
    public static void main(String[] args) {
        /*
        AutoCrudOperation<Account> accountAutoCrudOperation = new AutoCrudOperation<>(new Account());
        System.out.println(accountAutoCrudOperation.save(new Account(
                10,
                "Fanambinantsoa",
                "Andriamparantina",
                Timestamp.valueOf("1999-01-01 00:00:00").toLocalDateTime().toLocalDate(),
                "account10",
                true,
                Double.valueOf("2000"),
                "BOA"
        )));

        System.out.println(accountAutoCrudOperation.findAll());

        AutoCrudOperation<Account> accountAutoCrudOperation = new AutoCrudOperation<>(new Account());
        System.out.println(accountAutoCrudOperation.save(new Account(
                11,
                "Ria",
                "Rnd",
                Timestamp.valueOf("1999-01-01 00:00:00").toLocalDateTime().toLocalDate(),
                "account11",
                true,
                Double.valueOf("2000"),
                "BOA"
        )));
        AutoCrudOperation<Transfer> transferAutoCrudOperation = new AutoCrudOperation<>(new Transfer());
        System.out.println(
                transferAutoCrudOperation.save(new Transfer(
                        10,
                        "Gift",
                        Double.valueOf("10000"),
                        LocalDateTime.now(),
                        LocalDateTime.now(),
                        StatusType.DONE,
                        10,
                        11,
                        "transfer1O"

                ))
        );


        System.out.println(transferCategoryAutoCrudOperation.save(new TransferCategory(
                10,
                "Foods",
                CategoryType.EXPENSE,
                null,
                10
        )));
        */
        AutoCrudOperation<TransferCategory> transferCategoryAutoCrudOperation = new AutoCrudOperation<>(new TransferCategory());
        /*System.out.println(transferCategoryAutoCrudOperation.findAll());*/
        System.out.println(transferCategoryAutoCrudOperation.findAllOrById(10));
        AutoCrudOperation<Transfer> transferAutoCrudOperation = new AutoCrudOperation<>(new Transfer());
        /*System.out.println(transferAutoCrudOperation.findAll());*/
        System.out.println(transferAutoCrudOperation.findAllOrById(10));
    }
}
