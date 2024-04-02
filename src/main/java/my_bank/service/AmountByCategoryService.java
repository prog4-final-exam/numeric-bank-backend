package my_bank.service;

import my_bank.model.AmountByCategoryParams;
import my_bank.model.entity.SumAmountsByCategory;
import my_bank.repository.AutoCrudOperation;
import org.springframework.stereotype.Service;

import java.util.List;

import static my_bank.model.Enum.FindSourceType.FUNCTION;

@Service
public class AmountByCategoryService {
    AutoCrudOperation<SumAmountsByCategory> sumAmountsByCategoryAutoCrudOperation = new AutoCrudOperation<>(new SumAmountsByCategory());
    public List<SumAmountsByCategory> getSumAmountsByCategory(AmountByCategoryParams paramsObj) throws IllegalAccessException {
        return sumAmountsByCategoryAutoCrudOperation.findManyByKey(null, FUNCTION, paramsObj);
    }
}
