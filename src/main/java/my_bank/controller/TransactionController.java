package my_bank.controller;

import lombok.AllArgsConstructor;
import my_bank.model.entity.Transaction;
import my_bank.service.TransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}
