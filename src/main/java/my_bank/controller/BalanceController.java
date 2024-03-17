package my_bank.controller;

import my_bank.model.Balance;
import my_bank.repository.AutoCrudOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class BalanceController {
    AutoCrudOperation<Balance> balanceAutoCrudOperation;

    @PostMapping("/balances")
    public ResponseEntity<Balance> save(@RequestBody Balance toSave) {
        return ResponseEntity.ok(balanceAutoCrudOperation.save(toSave));
    }

    @PutMapping("/balances")
    public ResponseEntity<Balance> update(@RequestBody Balance toUpdate) {
        return ResponseEntity.ok(balanceAutoCrudOperation.update(toUpdate));
    }

    @DeleteMapping("/balances/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable int id) {
        return ResponseEntity.ok(balanceAutoCrudOperation.deleteById(id));
    }
}
