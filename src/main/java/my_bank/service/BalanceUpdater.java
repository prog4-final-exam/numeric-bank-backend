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
    private AccountNumberManager accountNumberManager = new AccountNumberManager();

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

        Balance sourceCurrentBalance = balanceService.findByIdAccount(idAccount);
        double sourceMainBalance = sourceCurrentBalance.getMainBalance();

        if (transactionToSave.getTransactionType() == TransactionType.CREDIT) {
            sourceCurrentBalance.setMainBalance(sourceMainBalance + amount);
        } else {
            Account sourceAccount = accountService.findById(idAccount);
            if (amount > sourceMainBalance) {
                if (isTransaction && sourceAccount.isOverdraftAllowed()) {
                    sourceCurrentBalance.setMainBalance(0);
                    sourceCurrentBalance.setLoanAmount((sourceMainBalance - amount) * -1);
                    sourceCurrentBalance.setLoanInterest(0.01);
                } else {
                    return false;
                }
            } else {
                sourceCurrentBalance.setMainBalance(sourceMainBalance - amount);

                // update destination account balance

                if (isTransaction == false && transferToSave.isExternalBank() == false) {
                    Integer destinationAccountId = accountNumberManager.extractAccountId(transferToSave.getDestinationAccountNumber());
                    if (destinationAccountId == null) {
                        return false;
                    } else if (accountService.findById(destinationAccountId) == null){
                        return false;
                    } else {
                        Balance destinationCurrentBalance = balanceService.findByIdAccount(destinationAccountId);
                        destinationCurrentBalance.setMainBalance(
                                destinationCurrentBalance.getMainBalance() + amount
                        );
                        destinationCurrentBalance.setBalanceDatetime(LocalDateTime.now());
                        if (balanceService.save(destinationCurrentBalance) == null) {
                            return false;
                        }
                    }
                }

            }

            sourceCurrentBalance.setBalanceDatetime(LocalDateTime.now());
            if (balanceService.save(sourceCurrentBalance) == null) {
                return false;
            }
        }
        return  true;
    }
}
