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
    @GetMapping("/transactions/categories/{id}")
    public ResponseEntity<TransactionHave> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(transactionHaveService.findById(id));
    }
    @PutMapping("/transactions/categories")
    public ResponseEntity<TransactionHave> saveOrUpdate(@RequestBody TransactionHave toSaveOrUpdate){
        return ResponseEntity.ok(transactionHaveService.saveOrUpdate(toSaveOrUpdate));
    }
    @DeleteMapping("/transactions/categories/{id}")
    public ResponseEntity<Boolean> deleteById(@PathVariable int id) {
        return ResponseEntity.ok(transactionHaveService.deleteById(id));
    }
}
