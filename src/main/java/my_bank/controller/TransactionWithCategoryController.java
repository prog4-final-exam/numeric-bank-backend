package my_bank.controller;

import lombok.AllArgsConstructor;
import my_bank.model.KeyAndValue;
import my_bank.model.TransactionWithCategory;
import my_bank.service.TransactionWithCategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@RestController
@CrossOrigin
public class TransactionWithCategoryController {
    TransactionWithCategoryService transactionWithCategoryService;

    @GetMapping("/transactions/with-category")
    public ResponseEntity<List<TransactionWithCategory>> findAll() {
        return ResponseEntity.ok(transactionWithCategoryService.findAll());
    }
    @GetMapping("/transactions/with-category/{idTransaction}")
    public ResponseEntity<TransactionWithCategory>  findById(@PathVariable int idTransaction) {
        return ResponseEntity.ok(transactionWithCategoryService.findByIdTransaction(idTransaction));
    }
    @GetMapping("/accounts/{idAccount}/transactions/with-category")
    public ResponseEntity<List<TransactionWithCategory>> findByIdAccount(
            @PathVariable Integer idAccount,
            @RequestParam(required = false) Integer idTransaction,
            @RequestParam(required = false) LocalDateTime transactionDatetime,
            @RequestParam(required = false) String transactionType,
            @RequestParam(required = false) Integer idCategory,
            @RequestParam(required = false) String categoryName,
            @RequestParam(required = false) String categoryType
            
    ) {
        List<KeyAndValue> keyAndValueList = new ArrayList<>();
        keyAndValueList.add(new KeyAndValue("idAccount", idAccount.toString()));
        if (idTransaction != null) {
            keyAndValueList.add(new KeyAndValue("idTransaction", idTransaction.toString()));
        }
        if (transactionDatetime != null) {
            keyAndValueList.add(new KeyAndValue("transactionDatetime", transactionDatetime.toString()));
        }
        if (transactionType != null) {
            keyAndValueList.add(new KeyAndValue("transactionType", transactionType));
        }
        if (idCategory != null) {
            keyAndValueList.add(new KeyAndValue("idCategory", idCategory.toString()));
        }
        if (categoryName != null) {
            keyAndValueList.add(new KeyAndValue("categoryName", categoryName));
        }
        if (categoryType != null) {
            keyAndValueList.add(new KeyAndValue("categoryType", categoryType));
        }
        return ResponseEntity.ok(transactionWithCategoryService.findManyByKey(keyAndValueList));
    }
}
