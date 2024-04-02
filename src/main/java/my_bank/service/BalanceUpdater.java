package my_bank.service;

import my_bank.model.entity.Account;
import my_bank.model.entity.Balance;
import my_bank.model.Enum.TransactionType;
import my_bank.model.entity.Transaction;
import my_bank.model.entity.Transfer;

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

        Balance sourceCurrentBalance = balanceService.findLastOneByIdAccount(idAccount);
        double sourceMainBalance = sourceCurrentBalance.getMainBalance();

        if (transactionToSave != null && transactionToSave.getTransactionType() == TransactionType.CREDIT) {
            sourceCurrentBalance.setMainBalance(sourceMainBalance + amount);
        } else {
            Account sourceAccount = accountService.findById(idAccount);
            if (amount > sourceMainBalance) {
                if (isTransaction && sourceAccount.getOverdraftAllowed()) {
                    sourceCurrentBalance.setMainBalance(Double.valueOf(0));
                    sourceCurrentBalance.setLoanAmount((sourceMainBalance - amount) * -1);
                    sourceCurrentBalance.setLoanInterest(0.01);
                } else {
                    return false;
                }
            } else {
                sourceCurrentBalance.setMainBalance(sourceMainBalance - amount);

                // update destination account balance

                if (isTransaction == false && transferToSave.getIsExternalBank() == false) {
                    Integer destinationAccountId = accountNumberManager.extractAccountId(transferToSave.getDestinationAccountNumber());
                    if (destinationAccountId == null) {
                        return false;
                    } else if (accountService.findById(destinationAccountId) == null){
                        return false;
                    } else {
                        Balance destinationCurrentBalance = balanceService.findLastOneByIdAccount(destinationAccountId);
                        destinationCurrentBalance.setMainBalance(
                                destinationCurrentBalance.getMainBalance() + amount
                        );
                        destinationCurrentBalance.setBalanceDatetime(LocalDateTime.now());
                        destinationCurrentBalance.setIdBalance(null);
                        if (balanceService.saveOrUpdate(destinationCurrentBalance) == null) {
                            return false;
                        }
                    }
                }

            }

            sourceCurrentBalance.setIdBalance(null);
            sourceCurrentBalance.setBalanceDatetime(LocalDateTime.now());
            if (balanceService.saveOrUpdate(sourceCurrentBalance) == null) {
                return false;
            }
        }
        return  true;
    }
}
