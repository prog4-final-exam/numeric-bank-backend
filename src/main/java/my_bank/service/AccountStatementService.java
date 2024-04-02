package my_bank.service;

import my_bank.model.KeyAndValue;
import my_bank.model.entity.AccountStatement;
import my_bank.repository.AutoCrudOperation;
import org.springframework.stereotype.Service;

import java.util.List;

import static my_bank.model.Enum.FindSourceType.FUNCTION;

@Service
public class AccountStatementService {
    AutoCrudOperation<AccountStatement>  accountStatementServiceAutoCrudOperation = new AutoCrudOperation<>(new AccountStatement());

    public List<AccountStatement> getAccountStatement(Integer accountId) {
        return accountStatementServiceAutoCrudOperation.findManyByKey(
                List.of(new KeyAndValue("accountId", accountId.toString())),
                FUNCTION,
                null,
                null
        );
    }
}
