package my_bank.controller;

import lombok.AllArgsConstructor;
import my_bank.model.Balance;
import my_bank.service.BalanceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@AllArgsConstructor
public class BalanceController {
    BalanceService balanceService;

    @GetMapping("/balances")
    public ResponseEntity<List<Balance>> findAll() {
        return ResponseEntity.ok(balanceService.findAll());
    }
    @PostMapping("/balances")
    public ResponseEntity<Balance> save(@RequestBody Balance toSave) {
        return ResponseEntity.ok(balanceService.save(toSave));
    }

    @PutMapping("/balances")
    public ResponseEntity<Balance> update(@RequestBody Balance toUpdate) {
        return ResponseEntity.ok(balanceService.update(toUpdate));
    }
    @GetMapping("/balances/{id}")
    public ResponseEntity<Balance> findById(@PathVariable int id) {
        return ResponseEntity.ok(balanceService.findById(id));
    }
    @DeleteMapping("/balances/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable int id) {
        return ResponseEntity.ok(balanceService.deleteById(id));
    }
    @GetMapping("/account/{idAccount}/balances")
    public ResponseEntity<List<Balance>> findByIdAccount(@PathVariable int idAccount) {
        return ResponseEntity.ok(balanceService.findManyByIdAccount(idAccount));
    }
}
