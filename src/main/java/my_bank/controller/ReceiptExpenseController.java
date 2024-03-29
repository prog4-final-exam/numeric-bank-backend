package my_bank.controller;

import lombok.AllArgsConstructor;
import my_bank.model.ReceiptExpenseParams;
import my_bank.model.entity.SumReceiptsExpenses;
import my_bank.service.ReceiptExpenseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin
public class ReceiptExpenseController {
    ReceiptExpenseService receiptExpenseService;

    @GetMapping("/dashboard/receipt-and-expense")
    private ResponseEntity<List<SumReceiptsExpenses>> getSumReceiptsExpenses(@RequestBody ReceiptExpenseParams params) throws IllegalAccessException {
        return ResponseEntity.ok(receiptExpenseService.getSumReceiptsExpenses(params));
    }
}
