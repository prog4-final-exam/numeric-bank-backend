package my_bank.service;

import my_bank.model.KeyAndValue;
import my_bank.model.ReceiptExpenseParams;
import my_bank.model.entity.SumAmountsByCategory;
import my_bank.model.entity.SumReceiptsExpenses;
import my_bank.repository.AutoCrudOperation;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import static my_bank.model.Enum.FindSourceType.FUNCTION;

@Service
public class ReceiptExpenseService {
    AutoCrudOperation<SumReceiptsExpenses> sumReceiptsExpensesAutoCrudOperation = new AutoCrudOperation<>(new SumReceiptsExpenses());

    public List<SumReceiptsExpenses> getSumReceiptsExpenses(ReceiptExpenseParams paramsObj) throws IllegalAccessException {
        return sumReceiptsExpensesAutoCrudOperation.findManyByKey(null, FUNCTION, paramsObj);
    }
}
