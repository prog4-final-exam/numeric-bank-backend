package my_bank.controller;

import lombok.AllArgsConstructor;
import my_bank.model.AmountByCategoryParams;
import my_bank.model.entity.SumAmountsByCategory;
import my_bank.service.AmountByCategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
@AllArgsConstructor
public class AmountByCategoryController {
    private AmountByCategoryService amountByCategoryService;

    @GetMapping("/dashboard/amount-per-category")
    public ResponseEntity<List<SumAmountsByCategory>> getSumAmountsByCategory(@RequestBody AmountByCategoryParams params) throws IllegalAccessException {
        return ResponseEntity.ok(amountByCategoryService.getSumAmountsByCategory(params));
    }
}
