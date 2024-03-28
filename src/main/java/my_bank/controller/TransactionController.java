package my_bank.controller;

import lombok.AllArgsConstructor;
import my_bank.model.Enum.TransactionType;
import my_bank.model.KeyAndValue;
import my_bank.model.entity.Transaction;
import my_bank.service.TransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@AllArgsConstructor
public class TransactionController {
    TransactionService transactionService;

    @GetMapping("/transactions")
    public ResponseEntity<List<Transaction>> findAll() {
        return ResponseEntity.ok(transactionService.findAll());
    }
    @PostMapping("/transactions")
    public ResponseEntity<Transaction> save(@RequestBody Transaction toSave) {
        return ResponseEntity.ok(transactionService.save(toSave));
    }

    @PutMapping("/transactions")
    public ResponseEntity<Transaction> update(@RequestBody Transaction toUpdate) {
        return ResponseEntity.ok(transactionService.update(toUpdate));
    }
    @GetMapping("/transactions/{id}")
    public ResponseEntity<Transaction> findById(@PathVariable int id) {
        return ResponseEntity.ok(transactionService.findById(id));
    }
    @DeleteMapping("/transactions/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable int id) {
        return ResponseEntity.ok(transactionService.deleteById(id));
    }
    @GetMapping("/accounts/{idAccount}/transactions")
    public ResponseEntity<List<Transaction>> findByIdAccount(
            @PathVariable Integer idAccount,
            @RequestParam(required = false) LocalDateTime transactionDatetime,
            @RequestParam(required = false) Integer idTransaction,
            @RequestParam(required = false) TransactionType transactionType
            ) {
       List<KeyAndValue> keyAndValueList = new ArrayList<>();
       keyAndValueList.add(new KeyAndValue("idAccount", idAccount.toString()));
       if (idTransaction != null) {
           keyAndValueList.add(new KeyAndValue("id", idTransaction.toString()));
       }
       if (transactionDatetime != null) {
           keyAndValueList.add(new KeyAndValue("transactionDatetime", transactionDatetime.toString()));
       }
       if (transactionType != null) {
           keyAndValueList.add(new KeyAndValue("transactionType", transactionType.toString()));
       }
       return ResponseEntity.ok(transactionService.findManyByKey(keyAndValueList));
    }
}
