package my_bank.controller;

import lombok.AllArgsConstructor;
import my_bank.model.entity.TransactionHave;
import my_bank.service.TransactionHaveService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@CrossOrigin
@RestController
public class TransactionHaveController {
    TransactionHaveService transactionHaveService;

    @GetMapping("/transactions/categories")
    public ResponseEntity<List<TransactionHave>> findAll() {
        return ResponseEntity.ok(transactionHaveService.findAll());
    }
    @GetMapping("/transactions/categories/{idTransactionHave}")
    public ResponseEntity<TransactionHave> findById(@PathVariable Integer idTransactionHave) {
        return ResponseEntity.ok(transactionHaveService.findById(idTransactionHave));
    }
    @PutMapping("/transactions/categories")
    public ResponseEntity<TransactionHave> saveOrUpdate(@RequestBody TransactionHave toSaveOrUpdate){
        return ResponseEntity.ok(transactionHaveService.saveOrUpdate(toSaveOrUpdate));
    }
    @DeleteMapping("/transactions/categories/{idTransactionHave}")
    public ResponseEntity<Boolean> deleteById(@PathVariable int idTransactionHave) {
        return ResponseEntity.ok(transactionHaveService.deleteById(idTransactionHave));
    }
}
