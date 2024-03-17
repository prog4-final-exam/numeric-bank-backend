package my_bank.controller;

import my_bank.model.Transaction;
import my_bank.repository.AutoCrudOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class TransactionController {
    AutoCrudOperation<Transaction> transactionAutoCrudOperation;

    @PostMapping("/transactions")
    public ResponseEntity<Transaction> save(@RequestBody Transaction toSave) {
        return ResponseEntity.ok(transactionAutoCrudOperation.save(toSave));
    }

    @PutMapping("/transactions")
    public ResponseEntity<Transaction> update(@RequestBody Transaction toUpdate) {
        return ResponseEntity.ok(transactionAutoCrudOperation.update(toUpdate));
    }

    @DeleteMapping("/transactions/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable int id) {
        return ResponseEntity.ok(transactionAutoCrudOperation.deleteById(id));
    }
}
