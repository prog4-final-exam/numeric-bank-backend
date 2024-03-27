package my_bank.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import my_bank.model.entity.*;

@Data
@NoArgsConstructor
public class GenericModel {
    Account account = new Account();
    Balance balance = new Balance();
    Transaction transaction = new Transaction();
    Transfer transfer = new Transfer();
    TransferCategory transferCategory = new TransferCategory();
    AccountStatement accountStatement = new AccountStatement();
}
