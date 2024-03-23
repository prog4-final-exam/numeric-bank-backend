package my_bank.service;

import lombok.AllArgsConstructor;
import my_bank.model.Account;
import my_bank.model.Balance;
import my_bank.model.Enum.TransactionType;
import my_bank.model.Transaction;
import my_bank.model.Transfer;

import java.time.LocalDateTime;

public class BalanceUpdater {
    private BalanceService balanceService = new BalanceService();
    private AccountService accountService = new AccountService();

    public boolean updateBalance(Transfer transferToSave, Transaction transactionToSave) {
        double amount;
        int idAccount;
        boolean isTransaction = false;

        if (transactionToSave != null) {
            amount = transactionToSave.getAmount();
            idAccount = transactionToSave.getIdAccount();
            isTransaction = true;
        } else {
            amount = transferToSave.getAmount();
            idAccount = transferToSave.getIdAccountSource();
        }

        Balance currentBalance = balanceService.findByIdAccount(idAccount);
        double mainBalance = currentBalance.getMainBalance();

        if (transactionToSave.getTransactionType() == TransactionType.CREDIT) {
            currentBalance.setMainBalance(mainBalance + amount);
        } else {
            Account account = accountService.findById(idAccount);
            if (amount > mainBalance) {
                if (isTransaction && account.isOverdraftAllowed()) {
                    currentBalance.setMainBalance(0);
                    currentBalance.setLoanAmount((mainBalance - amount) * -1);
                    currentBalance.setLoanInterest(0.01);
                } else {
                    return false;
                }
            } else {
                currentBalance.setMainBalance(mainBalance - amount);
            }

            currentBalance.setBalanceDatetime(LocalDateTime.now());
            if (balanceService.save(currentBalance) == null) {
                return false;
            }
        }
        return  true;
    }
}
