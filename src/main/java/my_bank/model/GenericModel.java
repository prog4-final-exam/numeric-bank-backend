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
    Category category = new Category();
    TransactionHave transactionHave = new TransactionHave();
    TransferHave transferHave = new TransferHave();
    AccountStatement accountStatement = new AccountStatement();
    SumAmountsByCategory sumAmountsByCategory = new SumAmountsByCategory();
    SumReceiptsExpenses sumReceiptsExpenses = new SumReceiptsExpenses();
    TransactionWithCategory transactionWithCategory = new TransactionWithCategory();
    TransferWithCategory transferWithCategory  = new TransferWithCategory();
}
